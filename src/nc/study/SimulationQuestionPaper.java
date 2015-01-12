package nc.study;

public class SimulationQuestionPaper {

	public int id; // �Ծ�id
	public int paper_serial; // �������к�
	public String type; // �Ծ�����
	public String name; // �Ծ����
	public String user_answer; // �û��ش�
	public int score; // ��׼��

	public SimulationQuestionPaper() {

	}

	public SimulationQuestionPaper(int id, int paper_serial, String type,
			String name, String user_answer, int score) {

		this.id = id;
		this.paper_serial = paper_serial;
		this.type = type;
		this.name = name;
		this.user_answer = user_answer;
		this.score = score;
	}

	public RightOrWrong newRightOrWrong() {
		return new RightOrWrong();
	}

	public Filling newFilling() {
		return new Filling();
	}

	public SingleSel newSingleSel() {
		return new SingleSel();
	}

	public MultiSel newMultiSel() {
		return new MultiSel();
	}

	public EssayQuestion newEssayQuestion() {
		return new EssayQuestion();
	}

}

class QuestionPaper {

	public int id; 		//�Ծ���
	public int paper_serial; // �������к�
	public String type; // �Ծ�����
	public String name; // �Ծ����
	public String user_answer; // �û��ش�
	public int score; // ��׼��

	public QuestionPaper() {

	}

	public QuestionPaper(int id, int paper_serial, String type,
			String name, String user_answer, int score) {

		this.id = id;
		this.paper_serial = paper_serial;
		this.type = type;
		this.name = name;
		this.user_answer = user_answer;
		this.score = score;
	}

}



class RightOrWrong {

	public int id;
	public String type;
	public String question;
	public String reference_answer;
	public String analysis_answer;
	public int score;
	public int ques_difficult;
	public int question_num; //��Ŀ���
	public int paper_id; //�Ծ���-���

	public RightOrWrong() {

	}

	public RightOrWrong(int id, String type, String question,
			String reference_answer,String analysis_answer, int score, int ques_difficult,int question_num,int paper_id) {

		this.id = id;
		this.type = type;
		this.question = question;
		this.reference_answer = reference_answer;
		this.analysis_answer = analysis_answer;
		this.score = score;
		this.ques_difficult = ques_difficult;
		this.question_num = question_num;
		this.paper_id = paper_id;
	}
}

class Filling {

	public int id;
	public String type;
	public String question;
	public String reference_answer;
	public String analysis_answer;
	public int score;
	public int ques_difficult;
	public int question_num; //��Ŀ���
	public int paper_id; //�Ծ���
	
	public Filling() {

	}

	public Filling(int id, String type, String question,
			String reference_answer,String analysis_answer, int score, int ques_difficult,int question_num,int paper_id) {

		this.id = id;
		this.type = type;
		this.question = question;
		this.reference_answer = reference_answer;
		this.analysis_answer = analysis_answer;
		this.score = score;
		this.ques_difficult = ques_difficult;
		this.question_num = question_num;
		this.paper_id = paper_id;
	}
}

class SingleSel {

	public int id;
	public String type;
	public String question;

	public String choiseA;
	public String choiseB;
	public String choiseC;
	public String choiseD;

	public String reference_answer;
	public String analysis_answer;
	public int score;
	public int ques_difficult;
	public int question_num; //��Ŀ���
	public int paper_id; //�Ծ���

	public SingleSel() {

	}

	public SingleSel(int id, String type, String question, String choiseA,
			String choiseB, String choiseC, String choiseD,
			String reference_answer,String analysis_answer, int score, int ques_difficult,int question_num,int paper_id) {

		this.id = id;
		this.type = type;
		this.question = question;
		this.choiseA = choiseA;
		this.choiseB = choiseB;
		this.choiseC = choiseC;
		this.choiseD = choiseD;
		this.reference_answer = reference_answer;
		this.analysis_answer = analysis_answer;
		this.score = score;
		this.ques_difficult = ques_difficult;
		this.question_num = question_num;
		this.paper_id = paper_id;
	}
}

class MultiSel {

	public int id;
	public String type;
	public String question;

	public String choiseA;
	public String choiseB;
	public String choiseC;
	public String choiseD;
	public String choiseE;

	public String reference_answer;
	public String analysis_answer;
	public int score;
	public int ques_difficult;
	public int question_num; //��Ŀ���
	public int paper_id; //�Ծ���

	public MultiSel() {

	}

	public MultiSel(int id, String type, String question, String choiseA,
			String choiseB, String choiseC, String choiseD, String choiseE,
			String reference_answer,String analysis_answer, int score, int ques_difficult,int question_num,int paper_id) {

		this.id = id;
		this.type = type;
		this.question = question;
		this.choiseA = choiseA;
		this.choiseB = choiseB;
		this.choiseC = choiseC;
		this.choiseD = choiseD;
		this.choiseE = choiseE;
		this.reference_answer = reference_answer;
		this.analysis_answer = analysis_answer;
		this.score = score;
		this.ques_difficult = ques_difficult;
		this.question_num = question_num;
		this.paper_id = paper_id;
	}
}

class EssayQuestion {

	public int id;
	public String type;
	public String question;
	public String reference_answer;
	public String analysis_answer;
	public int score;
	public int ques_difficult;
	public int question_num; //��Ŀ���
	public int paper_id; //�Ծ���

	public EssayQuestion() {

	}

	public EssayQuestion(int id, String type, String question,
			String reference_answer,String analysis_answer, int score, int ques_difficult,int question_num,int paper_id) {

		this.id = id;
		this.type = type;
		this.question = question;
		this.reference_answer = reference_answer;
		this.analysis_answer = analysis_answer;
		this.score = score;
		this.ques_difficult = ques_difficult;
		this.question_num = question_num;
		this.paper_id = paper_id;
	}
}
