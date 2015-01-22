package nc.study;

import java.util.List;

import android.view.View;

//试题窗口
public class SimulationSingleSelWin {
	public SimulationDBManager db; // 数据库
	public List<View> pageViewList; // 所有页面信息 view paper list
	public View v_Current_Page;// 当前页面

	public SimulationQuestionPaper QuestionPaper; // 试卷信息

	public byte QuestionType; // 题型包含哪些? =>包含单选,多选时: 00001100
	public List<RightOrWrong> RightOrWrongs;
	public List<Filling> Fillings;
	public List<SingleSel> SingleSels; // 试题数据
	public List<MultiSel> MultiSels;
	public List<EssayQuestion> EssayQuestions;
	
	public SimulationSingleSelWin() {

	}

	public SimulationSingleSelWin(SimulationDBManager db,SimulationQuestionPaper QuestionPaper) {

	}

	public SimulationQuestionPaper getQuestionPaper(String name){
		SimulationQuestionPaper QuestionPaper = null;
		
		
		return QuestionPaper;
	}

}