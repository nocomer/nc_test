package nc.study;

public class SimulationExamination {

	public int id;
	public String type;
	public String name;
	public String question;
	public String question_opt;
	public String standard_answer;
	public String user_answer;

	public SimulationExamination() {

	}

	public SimulationExamination(int id, String type, String name,
			String question, String question_opt, String standard_answer,
			String user_answer) {

		this.id = id;
		this.type = type;
		this.name = name;
		this.question = question;
		this.question_opt = question_opt;
		this.standard_answer = standard_answer;
		this.user_answer = user_answer;
	}
}
