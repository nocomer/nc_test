﻿package cube.table;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import cn.com.jdsc.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.Toast;

//游戏界面
public class CubeTableActivity extends Activity {
	// 开始键判断
	private static String START_GAME = "GO";
	// 填充数值容器
	private HashMap<Integer, String> mHashMap;
	// 填充按钮容器
	private HashMap<Integer, myButton> mList;
	// 定义数字按钮
	private myButton mButton1, mButton2, mButton3, mButton4, mButton5,
			mButton6, mButton7, mButton8, mButton9, mButton10, mButton11,
			mButton12, mButton13, mButton14, mButton15, mButton16, mButton17,
			mButton18, mButton19, mButton20, mButton21, mButton22, mButton23,
			mButton24, mButton25;

	private float mTime;
	private float mRecord;
	private int flagNum;

	private DatabaseHelper dbHelper;
	private SQLiteDatabase dbControl;

	private Timer myTime;
	private boolean isChild = false;

	// 消息处理
	final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				mTime++;

				if (mTime / 100 >= 25) {
					DisplayToast("Fail:" + "Score > 25s");
					myTime.cancel();
					preStart();

				}

				break;
			case 2: // restart
				mTime = 0;
				break;
			}
			super.handleMessage(msg);
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tool_cube_table);
		// 模式判定
		isChild = getIntent().getBooleanExtra("isChild", false);
		initButton();
		initDB();
	}

	static {
		// AdManager.init("bef250a2bc952beb", "0bcd890f060f6154", 30,
		// false,"1.0");
	}

	// 数据库操作对象初始化
	private void initDB() {
		if (dbHelper == null) {
			dbHelper = new DatabaseHelper(CubeTableActivity.this,
					DatabaseHelper.DB_NAME, null, DatabaseHelper.dbVer);
		}
	}

	// 初始化数字按钮
	private void initButton() {
		mList = new HashMap<Integer, myButton>();
		mButton1 = (myButton) findViewById(R.id.button1);
		mButton2 = (myButton) findViewById(R.id.button2);
		mButton3 = (myButton) findViewById(R.id.button3);
		mButton4 = (myButton) findViewById(R.id.button4);
		mButton5 = (myButton) findViewById(R.id.button5);
		mButton6 = (myButton) findViewById(R.id.button6);
		mButton7 = (myButton) findViewById(R.id.button7);
		mButton8 = (myButton) findViewById(R.id.button8);
		mButton9 = (myButton) findViewById(R.id.button9);
		mButton10 = (myButton) findViewById(R.id.button10);
		mButton11 = (myButton) findViewById(R.id.button11);
		mButton12 = (myButton) findViewById(R.id.button12);
		mButton13 = (myButton) findViewById(R.id.button13);
		mButton14 = (myButton) findViewById(R.id.button14);
		mButton15 = (myButton) findViewById(R.id.button15);
		mButton16 = (myButton) findViewById(R.id.button16);
		mButton17 = (myButton) findViewById(R.id.button17);
		mButton18 = (myButton) findViewById(R.id.button18);
		mButton19 = (myButton) findViewById(R.id.button19);
		mButton20 = (myButton) findViewById(R.id.button20);
		mButton21 = (myButton) findViewById(R.id.button21);
		mButton22 = (myButton) findViewById(R.id.button22);
		mButton23 = (myButton) findViewById(R.id.button23);
		mButton24 = (myButton) findViewById(R.id.button24);
		mButton25 = (myButton) findViewById(R.id.button25);

		mList.put(1, mButton1);
		mList.put(2, mButton2);
		mList.put(3, mButton3);
		mList.put(4, mButton4);
		mList.put(5, mButton5);
		mList.put(6, mButton6);
		mList.put(7, mButton7);
		mList.put(8, mButton8);
		mList.put(9, mButton9);
		mList.put(10, mButton10);
		mList.put(11, mButton11);
		mList.put(12, mButton12);
		mList.put(13, mButton13);
		mList.put(14, mButton14);
		mList.put(15, mButton15);
		mList.put(16, mButton16);
		mList.put(17, mButton17);
		mList.put(18, mButton18);
		mList.put(19, mButton19);
		mList.put(20, mButton20);
		mList.put(21, mButton21);
		mList.put(22, mButton22);
		mList.put(23, mButton23);
		mList.put(24, mButton24);
		mList.put(25, mButton25);

		// 设置按钮监听
		OnTouchListener mClick = new OnTouchListener() {
			// 按钮按下
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					myButton clickButton = (myButton) v;
					String buttonValue = (String) clickButton.getText();
					// 设置按钮边框闪烁
					clickButton.animateClickFeedback();
					// 开始游戏
					if (buttonValue.equals(START_GAME)) {
						startGame();
					} else if (Integer.valueOf(buttonValue) == 25) {
						String mScore = String.valueOf(mTime / 100) + "s";
						DisplayToast("Score:" + mScore);
						myTime.cancel();
						// try {
						// Thread.sleep(1000);
						// } catch (InterruptedException e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
						// }
						// startGame();
						preStart();
					} else {
						// 判断是否为完成
						if (checkNum(buttonValue, clickButton)) {
							String mScore = "耗时" + String.valueOf(mRecord)
									+ "秒，保存？";
							Context mContext = CubeTableActivity.this;
							LayoutInflater inflater = (LayoutInflater) mContext
									.getSystemService(LAYOUT_INFLATER_SERVICE);
							View textEntryView = inflater.inflate(
									R.layout.tool_alert, null);
							final EditText edtInput = (EditText) textEntryView
									.findViewById(R.id.edtInput);
							AlertDialog.Builder builder = new AlertDialog.Builder(
									mContext);
							// 判断是否为保存记录
							builder.setCancelable(true);
							builder.setIcon(R.drawable.icon_sdcard);
							builder.setTitle(mScore);
							builder.setView(textEntryView);
							builder.setPositiveButton("保存",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int whichButton) {
											// 保存记录
											markRecord(mRecord, edtInput
													.getText().toString());
											preStart();
										}
									});
							builder.setNegativeButton("取消",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int whichButton) {
											preStart();
										}
									});
							builder.show();
						}
					}
				}
				return true;
			}
		};

		for (int i = 1; i <= mList.size(); i++) {
			myButton mButton = mList.get(i);
			mButton.setOnTouchListener(mClick);
			if (i == 13) {
				mButton.setText(START_GAME);
			} else {
				mButton.setText("");
			}
		}
	}

	public void DisplayToast(String str) {
		Toast toast = Toast.makeText(getApplicationContext(), str,
				Toast.LENGTH_LONG);
		toast.setGravity(Gravity.TOP, 0, 220);
		toast.show();
	}

	private void markRecord(float mRecord, String mName) {
		if (dbControl == null) {
			dbControl = dbHelper.getWritableDatabase();
		}
		// Date now = new Date();
		// SimpleDateFormat myFormat = new SimpleDateFormat("yyyy"+"MM"+"dd");
		// String mdate = myFormat.format(now);
		// String sql = "insert into tops (mname,mtime,mdate) VALUES ('" + mName
		// + "','" + mRecord + "','" + mdate + "')";
		String sql = "insert into tops (mname,mtime) VALUES ('" + mName + "','"
				+ mRecord + "')";
		dbControl.execSQL(sql);
	}

	// 判断下一键
	private Boolean checkNum(String strNum, myButton v) {
		String mNum = String.valueOf(flagNum);
		if (mNum.equals(strNum)) {
			if (isChild) {
				v.changeColor();
			}
			if (flagNum == 25) {
				// 计时结束
				myTime.cancel();
				mRecord = mTime / 100;
				return true;
			}
			flagNum++;
		}
		return false;
	}

	// 重置开始
	private void preStart() {
		mTime = 0;
		flagNum = 1;
		for (int i = 1; i <= mList.size(); i++) {
			myButton mButton = mList.get(i);
			// 模式FLAG
			mButton.mFlag = false;
			if (i == 13) {
				mButton.setText(START_GAME);
			} else {
				mButton.setText("");
			}
		}
	}

	// 重绘数字按钮
	private void reDrawUI() {
		mHashMap = logicNum.getNumbers();
		for (int i = 1; i <= mList.size(); i++) {
			myButton mButton = mList.get(i);
			mButton.setText(mHashMap.get(i));
		}
	}

	// 开始游戏
	private void startGame() {
		mTime = 0;
		flagNum = 1;
		reDrawUI();
		// 计时器
		myTime = new Timer(true);
		TimerTask task = new TimerTask() {
			public void run() {
				Message message = new Message();
				message.what = 1;
				handler.sendMessage(message);
			}
		};
		myTime.schedule(task, 10, 10);
	}
}
