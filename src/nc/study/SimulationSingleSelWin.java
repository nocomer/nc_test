package nc.study;

import java.util.List;

import android.view.View;

//���ⴰ��
public class SimulationSingleSelWin {
	public SimulationDBManager db; // ��ݿ�
	public List<View> pageViewList; // ����ҳ����Ϣ view paper list
	public View v_Current_Page;// ��ǰҳ��

	public SimulationQuestionPaper QuestionPaper; // �Ծ���Ϣ

	public byte QuestionType; // ���Ͱ���Щ? =>��ѡ,��ѡʱ: 00001100
	public List<RightOrWrong> RightOrWrongs;
	public List<Filling> Fillings;
	public List<SingleSel> SingleSels; // �������
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