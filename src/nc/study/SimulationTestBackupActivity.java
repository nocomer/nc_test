package nc.study;

import nc.lib.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.jdsc.R;

import android.app.Activity;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SimulationTestBackupActivity extends Activity {
	private ViewPager viewPager;

	private ArrayList<View> pageViewList;
	private List<String> titleList;

	private PagerTabStrip pagerTabStrip;

	//private ImageView imageView;

	/** ��СԲ���ͼƬ�������ʾ */
	//private ImageView[] imageViews;

	// ���ͼƬ��LinearLayout
	private ViewGroup viewPics;
	// ���СԲ���LinearLayout
	//private ViewGroup viewPoints;

	private SimulationDBManager db;

	List<SingleSel> SingleSels;
	SingleSel SingleSel;
	TextView tvReferenceAnswer;

	/*
	 * RadioButton radioButton1; RadioButton radioButton2; RadioButton
	 * radioButton3; RadioButton radioButton4;
	 */

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simulation_test);

		/* �����ޱ����� */
		// requestWindowFeature(Window.FEATURE_NO_TITLE);

		LayoutInflater main_inflater = LayoutInflater.from(this);
		View simulation_test = main_inflater.inflate(R.layout.simulation_test,
				null);

		pagerTabStrip = (PagerTabStrip) simulation_test
				.findViewById(R.id.pagertab);
		pagerTabStrip.setTabIndicatorColor(getResources()
				.getColor(R.color.gold));
		pagerTabStrip.setDrawFullUnderline(false);
		pagerTabStrip
				.setBackgroundColor(getResources().getColor(R.color.azure));
		pagerTabStrip.setTextSpacing(50);

		pageViewList = new ArrayList<View>();
		titleList = new ArrayList<String>();

		LayoutInflater inflater = LayoutInflater.from(this);

		db = new SimulationDBManager(this);
		init();

		/* Get the single choice problems data */
		List<SimulationQuestionPaper> SimulationQuestionPaper = db
				.query_question_all_papers();

		SimulationQuestionPaper QuestionPaper01 = SimulationQuestionPaper
				.get(0);

		List<RightOrWrong> RightOrWrongs = db
				.query_right_or_wrong_by_paper_id(QuestionPaper01.id);
		List<Filling> Fillings = db
				.query_filling_by_paper_id(QuestionPaper01.id);
		SingleSels = db.query_singlesel_by_paper_id(QuestionPaper01.id);
		List<MultiSel> MultiSels = db
				.query_multisel_by_paper_id(QuestionPaper01.id);
		List<EssayQuestion> EssayQuestions = db
				.query_essayquestion_by_paper_id(QuestionPaper01.id);

		// ������ѡ�ⴰ��
		int SingleSelsNum = SingleSels.size();

		for (int n = 0; n < SingleSelsNum; n++) {
			/* Title */
			// SingleSel SingleSel = SingleSels.get(n);
			SingleSel = SingleSels.get(n);

			titleList.add("" + SingleSel.question_num);

			/* Content */
			View view = inflater.inflate(
					R.layout.simulation_page_single_selection, null);

			/* The question */
			TextView tvQuestion = (TextView) view.findViewById(R.id.tvQuestion);

			String tmp = SingleSel.id + SingleSel.type + SingleSel.question
					+ SingleSel.choiseA + SingleSel.choiseB + SingleSel.choiseC
					+ SingleSel.choiseD + SingleSel.reference_answer
					+ SingleSel.score + SingleSel.ques_difficult
					+ SingleSel.question_num + SingleSel.paper_id;

			tvQuestion.setText(tmp);

			TextView tvChoiseA = (TextView) view.findViewById(R.id.tvChoiseA);
			TextView tvChoiseB = (TextView) view.findViewById(R.id.tvChoiseB);
			TextView tvChoiseC = (TextView) view.findViewById(R.id.tvChoiseC);
			TextView tvChoiseD = (TextView) view.findViewById(R.id.tvChoiseD);
			tvChoiseA.setText("A." + SingleSel.choiseA);
			tvChoiseB.setText("B." + SingleSel.choiseB);
			tvChoiseC.setText("C." + SingleSel.choiseC);
			tvChoiseD.setText("D." + SingleSel.choiseD);

			tvReferenceAnswer = (TextView) view
					.findViewById(R.id.tvReferenceAnswer);
			Button btReferenceAnswer = (Button) view
					.findViewById(R.id.btReferenceAnswer);

			tvReferenceAnswer.setText("�ο���:" + SingleSel.reference_answer);
			tvReferenceAnswer.setVisibility(View.INVISIBLE);

			TextView tvAnalysisAnswer = (TextView) view
					.findViewById(R.id.tvAnalysisAnswer);
			tvAnalysisAnswer.setText("�𰸽���:��");
			tvAnalysisAnswer.setVisibility(View.INVISIBLE);
			
			// TODO
			btReferenceAnswer.setOnClickListener(new Button.OnClickListener() {
				@Override
				public void onClick(View v) {
					/*
					 * TextView tvReferenceAnswer = (TextView) v
					 * .findViewById(R.id.tvReferenceAnswer);
					 */
					// tvReferenceAnswer.setVisibility(View.GONE);
					DisplayToast("�ο���:" + SingleSel.reference_answer);
					tvReferenceAnswer.setText("�ο���:"
							+ SingleSel.reference_answer);
				}
			});

			// btReferenceAnswer.setOnClickListener(ButtonListener);

			/* The answer */
			RadioGroup rgAnswer = (RadioGroup) view.findViewById(R.id.rgAnswer);

			rgAnswer.setOnCheckedChangeListener(radiogpchange);

			pageViewList.add(view);

		}

		// ����imageviews���飬��С��Ҫ��ʾ��ͼƬ������
		//imageViews = new ImageView[pageViewList.size()];
		// ��ָ����XML�ļ�������ͼ
		viewPics = (ViewGroup) inflater.inflate(R.layout.simulation_test, null);

		

		// ʵ��СԲ���linearLayout��viewpager
		//viewPoints = (ViewGroup) viewPics.findViewById(R.id.viewGroup);

		// ���СԲ���ͼƬ
/*		for (int i = 0; i < pageViewList.size(); i++) {
			imageView = new ImageView(SimulationTestActivity.this);
			// ����СԲ��imageview�Ĳ���
			imageView.setLayoutParams(new LayoutParams(20, 20));// ����һ����߾�Ϊ20

			imageView.setPadding(20, 0, 20, 0);
			// ��СԲ��layout��ӵ�������
			imageViews[i] = imageView;

			// Ĭ��ѡ�е��ǵ�һ��ͼƬ����ʱ��һ��СԲ����ѡ��״̬��������
			if (i == 0) {
				imageViews[i]
						.setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				imageViews[i].setBackgroundResource(R.drawable.page_indicator);
			}

			// ��imageviews��ӵ�СԲ����ͼ��
			viewPoints.addView(imageViews[i]);
		}*/

		// ��ʾ����ͼƬ����ͼ
		setContentView(viewPics);
		
		viewPager = (ViewPager) viewPics.findViewById(R.id.viewPager);
		
		viewPager.setAdapter(new GuidePageAdapter());
		viewPager.setCurrentItem(0);
		
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());
	}

	public void init() {
		db.init_recreate_db();
	}

	public void db_update(View view) {
		QuestionPaper QuestionPaper = new QuestionPaper();
		QuestionPaper.name = "һ�����05������";
		QuestionPaper.name = "һ�����2015������";
		// db.updateAge(QuestionPaper);
	}

	public void db_delete(View view) {
		QuestionPaper QuestionPaper = new QuestionPaper();
		QuestionPaper.name = "һ�����2015������";
		// db.deleteOldQuestionPaper(QuestionPaper);
	}

	/*
	 * public void v_db_query(View view) { //List<QuestionPaper> QuestionPapers
	 * = db.query();
	 * 
	 * ArrayList<Map<String, String>> list = new ArrayList<Map<String,
	 * String>>();
	 * 
	 * for (QuestionPaper QuestionPaper : QuestionPapers) { HashMap<String,
	 * String> map = new HashMap<String, String>(); map.put("name",
	 * QuestionPaper.name); map.put("question", QuestionPaper.question +
	 * QuestionPaper.question); list.add(map); }
	 * 
	 * SimpleAdapter adapter = new SimpleAdapter(this, list,
	 * android.R.layout.simple_list_item_2, new String[]{"name", "question"},
	 * new int[]{android.R.id.text1, android.R.id.text2});
	 * listView.setAdapter(adapter);
	 * 
	 * }
	 */

	/*
	 * public void db_queryTheCursor(View view) { Cursor c =
	 * db.queryTheCursor(); startManagingCursor(c); //
	 * �и���activity����Լ�����������ȥ����Cursor���������� CursorWrapper cursorWrapper = new
	 * CursorWrapper(c) {
	 * 
	 * @Override public String getString(int columnIndex) { // �����ǰ�������� if
	 * (getColumnName(columnIndex).equals("info")) { int age =
	 * getInt(getColumnIndex("age")); return age + " years old, " +
	 * super.getString(columnIndex); } return super.getString(columnIndex); } };
	 * // ȷ����ѯ�������"_id"��
	 * 
	 * SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
	 * android.R.layout.simple_list_item_2, cursorWrapper, new String[]{"name",
	 * "info"}, new int[]{android.R.id.text1, android.R.id.text2}); ListView
	 * listView = (ListView) findViewById(R.id.listView);
	 * listView.setAdapter(adapter);
	 * 
	 * }
	 */

	@Override
	protected void onDestroy() {
		super.onDestroy();
		/* Free the db when activity close */
		db.closeDB();
	}

	class GuidePageAdapter extends PagerAdapter {

		// ���positionλ�õĽ���
		@Override
		public void destroyItem(View v, int position, Object arg2) {
			// TODO Auto-generated method stub
			((ViewPager) v).removeView(pageViewList.get(position));

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		// ��ȡ��ǰ���������
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pageViewList.size();
		}

		// ��ʼ��positionλ�õĽ���
		@Override
		public Object instantiateItem(View v, int position) {
			// TODO Auto-generated method stub
			((ViewPager) v).addView(pageViewList.get(position));
			return pageViewList.get(position);
		}

		// �ж��Ƿ��ɶ�����ɽ���
		@Override
		public boolean isViewFromObject(View v, Object arg1) {
			// TODO Auto-generated method stub
			return v == arg1;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			SpannableStringBuilder ssb = new SpannableStringBuilder("  "
					+ titleList.get(position)); // space added before text
			// for
			Drawable myDrawable = getResources().getDrawable(
					R.drawable.ic_launcher);
			myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(),
					myDrawable.getIntrinsicHeight());
			ImageSpan span = new ImageSpan(myDrawable, ImageSpan.ALIGN_BASELINE);

			ForegroundColorSpan fcs = new ForegroundColorSpan(Color.GREEN);// ������ɫ����Ϊ��ɫ
			// ssb.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//
			// ����ͼ��
			// ssb.setSpan(fcs, 1, ssb.length(),
			// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);// ����������ɫ
			ssb.setSpan(new RelativeSizeSpan(1.2f), 1, ssb.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			// return titleList.get(position);
			return ssb;
		}

		@Override
		public void setPrimaryItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			Log.i("setPrimaryItem", "setPrimaryItem position:" + position);
			// super.setPrimaryItem(container, position, object);
		}

		@Override
		public void notifyDataSetChanged() {
			int key = 0;
			for (int i = 0; i < pageViewList.size(); i++) {
				// key = pageViewList.keyAt(i);
				// View view = pageViewList.get(key);
				// <refresh view with new data>
			}
			super.notifyDataSetChanged();
		}
	}

	class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
		/*	for (int i = 0; i < imageViews.length; i++) {
				imageViews[position]
						.setBackgroundResource(R.drawable.page_indicator_focused);
				// ���ǵ�ǰѡ�е�page����СԲ������Ϊδѡ�е�״̬
				if (position != i) {
					imageViews[i]
							.setBackgroundResource(R.drawable.page_indicator);
				}
			}*/

		}

	}

	/*
	 * private RadioGroup.OnCheckedChangeListener radiogpchange = new
	 * RadioGroup.OnCheckedChangeListener() {
	 * 
	 * @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
	 * if (checkedId == radioButton1.getId()) {
	 * Toast.makeText(getApplicationContext(), "A", 1).show(); } else if
	 * (checkedId == radioButton2.getId()) {
	 * Toast.makeText(getApplicationContext(), "B", 1).show(); } else if
	 * (checkedId == radioButton3.getId()) {
	 * Toast.makeText(getApplicationContext(), "C", 1).show(); } else if
	 * (checkedId == radioButton4.getId()) {
	 * Toast.makeText(getApplicationContext(), "D", 1).show(); } } };
	 */

	private RadioGroup.OnCheckedChangeListener radiogpchange = new RadioGroup.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			int RadioButtonId = group.getCheckedRadioButtonId();
			switch (RadioButtonId) {
			case R.id.radioButton1:
				DisplayToast("��ѡ�����: A");
				break;
			case R.id.radioButton2:
				DisplayToast("��ѡ�����: B");
				break;
			case R.id.radioButton3:
				DisplayToast("��ѡ�����: C");
				break;
			case R.id.radioButton4:
				DisplayToast("��ѡ�����: D");
				break;
			default:
				DisplayToast("��δѡ��!");
				break;
			}

		}
	};

	public void DisplayToast(String str) {
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
		// ����toast��ʾ��λ��
		toast.setGravity(Gravity.TOP, 0, 220);
		// ��ʾ��Toast
		toast.show();
	}

	View.OnClickListener ButtonListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btReferenceAnswer:
				TextView tvReferenceAnswer = (TextView) v
						.findViewById(R.id.tvReferenceAnswer);
				// tvReferenceAnswer.setVisibility(View.GONE);
				// tvReferenceAnswer.setText("�ο���:" +
				// SingleSel.reference_answer);
				DisplayToast("fuck");
				// Log.d(TAG, "Start to recorder video\n");
				// start_recorde();
				break;
			default:
				break;
			}
		}
	};

}