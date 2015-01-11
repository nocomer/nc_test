package nc_study;

public class SimulationQuestionPaper {

	public int id; // 试卷id
	public int paper_serial; // 试题序列号
	public String type; // 试卷类型
	public String name; // 试卷名称
	public String user_answer; // 用户回答
	public int score; // 标准分

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

	public int id; 		//试卷编号
	public int paper_serial; // 试题序列号
	public String type; // 试卷类型
	public String name; // 试卷名称
	public String user_answer; // 用户回答
	public int score; // 标准分

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
	public int question_num; //题目编号
	public int paper_id; //试卷编号-外键

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
	public int question_num; //题目编号
	public int paper_id; //试卷编号
	
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
	public int question_num; //题目编号
	public int paper_id; //试卷编号

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
	public int question_num; //题目编号
	public int paper_id; //试卷编号

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
	public int question_num; //题目编号
	public int paper_id; //试卷编号

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
