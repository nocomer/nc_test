package nc_study;

import java.util.ArrayList;
import java.util.List;
import cn.com.jdsc.R;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SimulationTestMainActivity extends FragmentActivity {

	private ViewPager viewPager;
	private ArrayList<View> pageViewList;
	private int mCurrentPage = 0;

	private PagerTabStrip pagerTabStrip;

	private ArrayList<String> titleList;
	private ArrayList<Fragment> fragmentList;

	private SimulationDBManager db;

	List<SingleSel> SingleSels;
	SingleSel SingleSel;
	TextView tvReferenceAnswer;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simulation_fragment_main);
		/* 设置无标题栏 */
		// requestWindowFeature(Window.FEATURE_NO_TITLE);

		pageViewList = new ArrayList<View>();
		titleList = new ArrayList<String>();
		fragmentList = new ArrayList<Fragment>();

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

		// 建立单选题窗口
		int SingleSelsNum = SingleSels.size();

		for (int n = 0; n < SingleSelsNum; n++) {
			SingleSel = SingleSels.get(n);

			SimulationSingleSelFragment s = new SimulationSingleSelFragment();
			fragmentList.add(s);
			titleList.add("【"+(n + 1) + "】");
			s.setQuestion(SingleSel.question);
			s.setChoiseA(SingleSel.choiseA);
			s.setChoiseB(SingleSel.choiseB);
			s.setChoiseC(SingleSel.choiseC);
			s.setChoiseD(SingleSel.choiseD);
			s.setReferenceAnswer("【参考答案】" + SingleSel.reference_answer);
			s.setAnalysisAnswer("【答案解析】 " + SingleSel.analysis_answer);
		}

		/*
		 * LayoutInflater main_inflater = LayoutInflater.from(this); View
		 * simulation_test =
		 * main_inflater.inflate(R.layout.simulation_test,null);
		 */

		pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagertab);

		pagerTabStrip.setTabIndicatorColor(getResources()
				.getColor(R.color.green));
		pagerTabStrip.setDrawFullUnderline(false);
		pagerTabStrip
				.setBackgroundColor(getResources().getColor(R.color.darkgray));
		pagerTabStrip.setTextSpacing(50);

		try {
			viewPager = (ViewPager) findViewById(R.id.viewpager);
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("nc:" + e.getMessage());
		}

		viewPager.setAdapter(new FragmentViewPagerAdapter(
				getSupportFragmentManager()));
		viewPager.setCurrentItem(0);
	}

	public void init() {
		db.init_recreate_db();
	}

	public void db_update(View view) {
		QuestionPaper QuestionPaper = new QuestionPaper();
		QuestionPaper.name = "一建机电05年真题";
		QuestionPaper.name = "一建机电2015年真题";
		// db.updateAge(QuestionPaper);
	}

	public void db_delete(View view) {
		QuestionPaper QuestionPaper = new QuestionPaper();
		QuestionPaper.name = "一建机电2015年真题";
		// db.deleteOldQuestionPaper(QuestionPaper);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		/* Free the db when activity close */
		db.closeDB();
	}

	public void DisplayToast(String str) {
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
		// 设置toast显示的位置
		toast.setGravity(Gravity.TOP, 0, 220);
		// 显示该Toast
		toast.show();
	}

	public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
		public FragmentViewPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return titleList.get(position);
		}

	}

}