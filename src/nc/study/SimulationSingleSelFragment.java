package nc.study;

import nc.lib.*;

import java.util.List;

import cn.com.jdsc.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SimulationSingleSelFragment extends Fragment {
	private static final String TAG = "SingleSelFragment";
	private View view;

	private TextView tvQuestion;
	private TextView tvChoiseA;
	private TextView tvChoiseB;
	private TextView tvChoiseC;
	private TextView tvChoiseD;
	private RadioGroup rgAnswer;
	private TextView tvReferenceAnswer;
	private TextView tvAnalysisAnswer;
	private Button btReferenceAnswer;
	
	
	private String Question = null;
	private String ChoiseA = null;
	private String ChoiseB = null;
	private String ChoiseC = null;
	private String ChoiseD = null;

	private String ReferenceAnswer = null;
	private String AnalysisAnswer = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.v("tag", "fragment1-->onCreate()");
		//init();
	}

	private void init() {
		setChoiseA("null");
		setChoiseB("null");
		setChoiseC("null");
		setChoiseD("null");
		setReferenceAnswer("null");
		setAnalysisAnswer("null");
	}

	public void setQuestion(String msg) {
		Question = msg;
	}
	
	public void setChoiseA(String msg) {
		ChoiseA = msg;
	}

	public void setChoiseB(String msg) {
		ChoiseB = msg;
	}

	public void setChoiseC(String msg) {
		ChoiseC = msg;
	}

	public void setChoiseD(String msg) {
		ChoiseD = msg;
	}

	public void setReferenceAnswer(String msg) {
		ReferenceAnswer = msg;
	}

	public void setAnalysisAnswer(String msg) {
		AnalysisAnswer = msg;
	}

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v("tag", "fragment1-->onCreateView()");
		
		/*
		 * ViewGroup p = (ViewGroup) view.getParent(); if (p != null) {
		 * p.removeAllViewsInLayout(); Log.v("tag", "fragment1-->锟狡筹拷锟窖达拷锟节碉拷View"); }
		 */

		view = inflater.inflate(R.layout.simulation_page_single_selection,
				container, false);
		
		
		tvQuestion = (TextView) view.findViewById(R.id.tvQuestion);
		tvQuestion.setText(Question);
		

		tvChoiseA = (TextView) view.findViewById(R.id.tvChoiseA);
		tvChoiseB = (TextView) view.findViewById(R.id.tvChoiseB);
		tvChoiseC = (TextView) view.findViewById(R.id.tvChoiseC);
		tvChoiseD = (TextView) view.findViewById(R.id.tvChoiseD);

		tvChoiseA.setText(ChoiseA);
		tvChoiseB.setText(ChoiseB);
		tvChoiseC.setText(ChoiseC);
		tvChoiseD.setText(ChoiseD);

		tvReferenceAnswer = (TextView) view
				.findViewById(R.id.tvReferenceAnswer);

		tvReferenceAnswer.setText("【参考答案】");

		tvAnalysisAnswer = (TextView) view.findViewById(R.id.tvAnalysisAnswer);
		tvAnalysisAnswer.setText("【答案解析】");
		
		
		
		rgAnswer = (RadioGroup) view.findViewById(R.id.rgAnswer);

		rgAnswer.setOnCheckedChangeListener(radiogpchange);
		

		btReferenceAnswer = (Button) view.findViewById(R.id.btReferenceAnswer);
		btReferenceAnswer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DisplayToast(ReferenceAnswer);
				if (ReferenceAnswer != null)
					tvReferenceAnswer.setText(ReferenceAnswer);
					tvAnalysisAnswer.setText(AnalysisAnswer);
				// tvReferenceAnswer.setVisibility(View.GONE);
			}
		});

		return view;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v("tag", "fragment1-->onDestroy()");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v("tag", "fragment1-->onPause()");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("tag", "fragment1-->onResume()");
	}

	@Override
	public void onStart() {

		// TODO Auto-generated method stub
		super.onStart();
		Log.v("tag", "fragment1-->onStart()");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v("tag", "fragment1-->onStop()");

	}

	public void DisplayToast(String str) {
		Toast toast = Toast.makeText(getActivity(), str, Toast.LENGTH_LONG);
		// 锟斤拷锟斤拷toast锟斤拷示锟斤拷位锟斤拷
		toast.setGravity(Gravity.TOP, 0, 220);
		// 锟斤拷示锟斤拷Toast
		toast.show();
	}
	
	private RadioGroup.OnCheckedChangeListener radiogpchange = new RadioGroup.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			int RadioButtonId = group.getCheckedRadioButtonId();
			switch (RadioButtonId) {
			case R.id.radioButton1:
				DisplayToast("你选择的是A");
				break;
			case R.id.radioButton2:
				DisplayToast("你选择的是B");
				break;
			case R.id.radioButton3:
				DisplayToast("你选择的是C");
				break;
			case R.id.radioButton4:
				DisplayToast("你选择的是 D");
				break;
			default:
				DisplayToast("你未选择选项!");
				break;
			}

		}
	};
}
