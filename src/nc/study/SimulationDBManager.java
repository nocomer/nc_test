package nc.study;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SimulationDBManager {
	private SimulationDBHelper helper;
	private SQLiteDatabase db;

	public SimulationDBManager(Context context) {
		helper = new SimulationDBHelper(context);
		// ��ΪgetWritableDatabase�ڲ�������mContext.openOrCreateDatabase(mName,
		// 0,mFactory);
		// ����Ҫȷ��context�ѳ�ʼ��,���ǿ��԰�ʵ��DBManager�Ĳ������Activity��onCreate��
		db = helper.getWritableDatabase();
	}

	public void addQuestionPaper(
			List<SimulationQuestionPaper> SimulationQuestionPapers) {

		db.beginTransaction(); // ��ʼ����
		try {
			for (SimulationQuestionPaper SimulationQuestionPaper : SimulationQuestionPapers) {
				db.execSQL("INSERT INTO QuestionPaper VALUES(null,?,?,?,?,?)",
						new Object[] { SimulationQuestionPaper.type,
								SimulationQuestionPaper.paper_serial,
								SimulationQuestionPaper.name,
								SimulationQuestionPaper.user_answer,
								SimulationQuestionPaper.score, });
			}
			db.setTransactionSuccessful(); // ��������ɹ����

		} finally {
			db.endTransaction(); // ��������
		}

	}

	/* Insert the data to the table */
	public int InsertOneQuestionPaper(
			SimulationQuestionPaper SimulationQuestionPaper) {

		int lastId = 0;
		db.beginTransaction(); // ��ʼ����
		try {

			db.execSQL("INSERT INTO QuestionPaper VALUES(null,?,?,?,?,?)",
					new Object[] { SimulationQuestionPaper.type,
							SimulationQuestionPaper.paper_serial,
							SimulationQuestionPaper.name,
							SimulationQuestionPaper.user_answer,
							SimulationQuestionPaper.score, });

			// get last id
			Cursor c = db.rawQuery(
					"select last_insert_rowid() from QuestionPaper", null);
			if (c.moveToFirst()) {
				lastId = c.getInt(0);
				Log.i("lastId", lastId + "");
			}
			c.close();

			db.setTransactionSuccessful(); // ��������ɹ����
		} finally {
			db.endTransaction(); // ��������
		}

		return lastId;
	}

	public void addRightOrWrong(List<RightOrWrong> RightOrWrongs) {
		db.beginTransaction(); // ��ʼ����
		try {
			for (RightOrWrong RightOrWrong : RightOrWrongs) {
				db.execSQL("INSERT INTO RightOrWrong VALUES(null,?,?,?,?,?)",
						new Object[] { RightOrWrong.type,
								RightOrWrong.question,
								RightOrWrong.reference_answer,
								RightOrWrong.score,
								RightOrWrong.ques_difficult, });
			}
			db.setTransactionSuccessful(); // ��������ɹ����
		} finally {
			db.endTransaction(); // ��������
		}
	}

	public void InsertOneRightOrWrong(int id, RightOrWrong RightOrWrong) {
		db.beginTransaction(); // ��ʼ����
		try {

			db.execSQL("INSERT INTO RightOrWrong VALUES(null,?,?,?,?,?,?,?,?)",
					new Object[] { RightOrWrong.type, RightOrWrong.question,
							RightOrWrong.reference_answer,
							RightOrWrong.analysis_answer, RightOrWrong.score,
							RightOrWrong.ques_difficult,
							RightOrWrong.question_num, RightOrWrong.paper_id });

			db.setTransactionSuccessful(); // ��������ɹ����
		} finally {
			db.endTransaction(); // ��������
		}
	}

	public void InsertOneFilling(int id, Filling Filling) {
		db.beginTransaction(); // ��ʼ����
		try {

			db.execSQL("INSERT INTO Filling VALUES(null,?,?,?,?,?,?,?,?)",
					new Object[] { Filling.type, Filling.question,
							Filling.reference_answer, Filling.analysis_answer,
							Filling.score, Filling.ques_difficult,
							Filling.question_num, Filling.paper_id });

			db.setTransactionSuccessful(); // ��������ɹ����
		} finally {
			db.endTransaction(); // ��������
		}
	}

	public void addFilling(List<Filling> Fillings) {
		db.beginTransaction(); // ��ʼ����
		try {
			for (Filling Filling : Fillings) {
				db.execSQL("INSERT INTO Filling VALUES(null,?,?,?,?,?,?)",
						new Object[] { Filling.type, Filling.question,
								Filling.reference_answer, Filling.score,
								Filling.ques_difficult, });
			}
			db.setTransactionSuccessful(); // ��������ɹ����
		} finally {
			db.endTransaction(); // ��������
		}
	}

	public void InsertOneSingleSel(int id, SingleSel SingleSel) {
		db.beginTransaction(); // ��ʼ����
		try {

			db.execSQL(
					"INSERT INTO SingleSel VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] { SingleSel.type, SingleSel.question,
							SingleSel.choiseA, SingleSel.choiseB,
							SingleSel.choiseC, SingleSel.choiseD,
							SingleSel.reference_answer,
							SingleSel.analysis_answer, SingleSel.score,
							SingleSel.ques_difficult, SingleSel.question_num,
							SingleSel.paper_id });

			db.setTransactionSuccessful(); // ��������ɹ����
		} finally {
			db.endTransaction(); // ��������
		}
	}

	public void addSingleSel(List<SingleSel> SingleSels) {
		db.beginTransaction(); // ��ʼ����
		try {
			for (SingleSel SingleSel : SingleSels) {
				db.execSQL(
						"INSERT INTO SingleSel VALUES(null,?,?,?,?,?,?,?,?,?)",
						new Object[] { SingleSel.type, SingleSel.question,
								SingleSel.choiseA, SingleSel.choiseB,
								SingleSel.choiseC, SingleSel.choiseD,
								SingleSel.reference_answer, SingleSel.score,
								SingleSel.ques_difficult, });
			}
			db.setTransactionSuccessful(); // ��������ɹ����
		} finally {
			db.endTransaction(); // ��������
		}
	}

	public void InsertOneMultiSel(int id, MultiSel MultiSel) {
		db.beginTransaction(); // ��ʼ����
		try {

			db.execSQL(
					"INSERT INTO MultiSel VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] { MultiSel.type, MultiSel.question,
							MultiSel.choiseA, MultiSel.choiseB,
							MultiSel.choiseC, MultiSel.choiseD,
							MultiSel.choiseE, MultiSel.reference_answer,
							MultiSel.analysis_answer, MultiSel.score,
							MultiSel.ques_difficult, MultiSel.question_num,
							MultiSel.paper_id });

			db.setTransactionSuccessful(); // ��������ɹ����
		} finally {
			db.endTransaction(); // ��������
		}
	}

	public void addMultiSel(List<MultiSel> MultiSels) {
		db.beginTransaction(); // ��ʼ����
		try {
			for (MultiSel MultiSel : MultiSels) {
				db.execSQL(
						"INSERT INTO MultiSel VALUES(null,?,?,?,?,?,?,?,?,?,?)",
						new Object[] { MultiSel.type, MultiSel.question,
								MultiSel.choiseA, MultiSel.choiseB,
								MultiSel.choiseC, MultiSel.choiseD,
								MultiSel.choiseE, MultiSel.reference_answer,
								MultiSel.score, MultiSel.ques_difficult, });
			}
			db.setTransactionSuccessful(); // ��������ɹ����
		} finally {
			db.endTransaction(); // ��������
		}
	}

	public void InsertOneEssayQuestion(int id, EssayQuestion EssayQuestion) {
		db.beginTransaction(); // ��ʼ����
		try {

			db.execSQL(
					"INSERT INTO EssayQuestion VALUES(null,?,?,?,?,?,?,?,?)",
					new Object[] { EssayQuestion.type, EssayQuestion.question,
							EssayQuestion.reference_answer,
							EssayQuestion.analysis_answer, EssayQuestion.score,
							EssayQuestion.ques_difficult,
							EssayQuestion.question_num, EssayQuestion.paper_id });

			db.setTransactionSuccessful(); // ��������ɹ����
		} finally {
			db.endTransaction(); // ��������
		}
	}

	public void addEssayQuestion(List<EssayQuestion> EssayQuestions) {
		db.beginTransaction(); // ��ʼ����
		try {
			for (EssayQuestion EssayQuestion : EssayQuestions) {
				db.execSQL("INSERT INTO EssayQuestion VALUES(null,?,?,?,?,?)",
						new Object[] { EssayQuestion.type,
								EssayQuestion.question,
								EssayQuestion.reference_answer,
								EssayQuestion.score,
								EssayQuestion.ques_difficult, });
			}
			db.setTransactionSuccessful(); // ��������ɹ����
		} finally {
			db.endTransaction(); // ��������
		}
	}

	public void execSqlite(String sql) {
		db.beginTransaction(); // ��ʼ����
		try {
			db.execSQL(sql);
			db.setTransactionSuccessful(); // ��������ɹ����
		} finally {
			db.endTransaction(); // ��������
		}
	}

	public long getCountByType(String type) {

		String sql = "select count(*) from SimulationExamination where type = "
				+ type;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(
				"select count(*) from SimulationExamination", null);
		cursor.moveToFirst();
		return cursor.getLong(0);
	}

	/**
	 * update SimulationExamination's age
	 * 
	 * @param SimulationExamination
	 */
	public void updateAge(SimulationExamination SimulationExamination) {
		ContentValues cv = new ContentValues();
		cv.put("name", SimulationExamination.name);
		db.update("SimulationExamination", cv, "name = ?",
				new String[] { SimulationExamination.name });
	}

	/**
	 * delete old SimulationExamination
	 * 
	 * @param SimulationExamination
	 */
	/*
	 * public void deleteOldSimulationExamination(SimulationExamination
	 * SimulationExamination) { db.delete("SimulationExamination", "age >= ?",
	 * new String[]{String.valueOf(SimulationExamination.age)}); }
	 */

	public List<SimulationQuestionPaper> query_question_all_papers() {
		ArrayList<SimulationQuestionPaper> SimulationQuestionPapers = new ArrayList<SimulationQuestionPaper>();
		Cursor c = db.rawQuery("SELECT * FROM QuestionPaper", null);
		while (c.moveToNext()) {
			SimulationQuestionPaper SimulationQuestionPaper = new SimulationQuestionPaper();
			SimulationQuestionPaper.id = c.getInt(c.getColumnIndex("id"));
			SimulationQuestionPaper.type = c
					.getString(c.getColumnIndex("type"));
			SimulationQuestionPaper.name = c
					.getString(c.getColumnIndex("name"));
			SimulationQuestionPaper.paper_serial = c.getInt(c
					.getColumnIndex("paper_serial"));
			SimulationQuestionPaper.user_answer = c.getString(c
					.getColumnIndex("user_answer"));
			SimulationQuestionPaper.score = c.getInt(c.getColumnIndex("score"));

			SimulationQuestionPapers.add(SimulationQuestionPaper);
		}
		c.close();
		return SimulationQuestionPapers;
	}

	// ����id�����Ծ�
	public SimulationQuestionPaper query_questionpaper_by_id(int id) {
		SimulationQuestionPaper SimulationQuestionPaper = null;
		Cursor c = db.rawQuery("SELECT * FROM QuestionPaper where id = " + id,
				null);
		c.moveToFirst();
		SimulationQuestionPaper = new SimulationQuestionPaper();
		SimulationQuestionPaper.id = c.getInt(c.getColumnIndex("id"));
		SimulationQuestionPaper.type = c.getString(c.getColumnIndex("type"));
		SimulationQuestionPaper.name = c.getString(c.getColumnIndex("name"));
		SimulationQuestionPaper.paper_serial = c.getInt(c
				.getColumnIndex("paper_serial"));
		SimulationQuestionPaper.user_answer = c.getString(c
				.getColumnIndex("user_answer"));
		SimulationQuestionPaper.score = c.getInt(c.getColumnIndex("score"));

		c.close();

		return SimulationQuestionPaper;
	}

	// ���Ծ�������Ծ�
	public SimulationQuestionPaper query_questionpaper_by_name(String name) {
		SimulationQuestionPaper SimulationQuestionPaper = null;
		Cursor c = db.rawQuery("SELECT * FROM QuestionPaper where name = "
				+ name, null);
		c.moveToFirst();
		SimulationQuestionPaper = new SimulationQuestionPaper();
		SimulationQuestionPaper.id = c.getInt(c.getColumnIndex("id"));
		SimulationQuestionPaper.type = c.getString(c.getColumnIndex("type"));
		SimulationQuestionPaper.name = c.getString(c.getColumnIndex("name"));
		SimulationQuestionPaper.paper_serial = c.getInt(c
				.getColumnIndex("paper_serial"));
		SimulationQuestionPaper.user_answer = c.getString(c
				.getColumnIndex("user_answer"));
		SimulationQuestionPaper.score = c.getInt(c.getColumnIndex("score"));

		c.close();

		return SimulationQuestionPaper;
	}

	// ���Ծ�������Ծ�
	public SimulationQuestionPaper query_questionpaper_by_like_name(String name) {
		SimulationQuestionPaper SimulationQuestionPaper = null;
		Cursor c = db.rawQuery("SELECT * FROM QuestionPaper where name like "
				+ "'%" + name + "%'", null);
		c.moveToFirst();
		SimulationQuestionPaper = new SimulationQuestionPaper();
		SimulationQuestionPaper.id = c.getInt(c.getColumnIndex("id"));
		SimulationQuestionPaper.type = c.getString(c.getColumnIndex("type"));
		SimulationQuestionPaper.name = c.getString(c.getColumnIndex("name"));
		SimulationQuestionPaper.paper_serial = c.getInt(c
				.getColumnIndex("paper_serial"));
		SimulationQuestionPaper.user_answer = c.getString(c
				.getColumnIndex("user_answer"));
		SimulationQuestionPaper.score = c.getInt(c.getColumnIndex("score"));

		c.close();

		return SimulationQuestionPaper;
	}

	// �����Ŀ��Ų��ж���
	public List<RightOrWrong> query_right_or_wrong_by_paper_id(int paper_id) {

		ArrayList<RightOrWrong> RightOrWrongs = new ArrayList<RightOrWrong>();
		Cursor c = db.rawQuery("SELECT * FROM RightOrWrong where paper_id ="
				+ paper_id, null);
		while (c.moveToNext()) {
			RightOrWrong RightOrWrong = new RightOrWrong();
			RightOrWrong.id = c.getInt(c.getColumnIndex("id"));
			RightOrWrong.type = c.getString(c.getColumnIndex("type"));
			RightOrWrong.question = c.getString(c.getColumnIndex("question"));
			RightOrWrong.reference_answer = c.getString(c
					.getColumnIndex("reference_answer"));
			RightOrWrong.analysis_answer = c.getString(c
					.getColumnIndex("analysis_answer"));
			RightOrWrong.score = c.getInt(c.getColumnIndex("score"));
			RightOrWrong.ques_difficult = c.getInt(c
					.getColumnIndex("ques_difficult"));
			RightOrWrong.question_num = c.getInt(c
					.getColumnIndex("question_num"));
			RightOrWrong.paper_id = c.getInt(c.getColumnIndex("paper_id"));

			RightOrWrongs.add(RightOrWrong);
		}
		c.close();
		return RightOrWrongs;
	}

	public List<Filling> query_filling_by_paper_id(int paper_id) {

		ArrayList<Filling> Fillings = new ArrayList<Filling>();
		Cursor c = db.rawQuery("SELECT * FROM Filling where paper_id ="
				+ paper_id, null);
		while (c.moveToNext()) {
			Filling Filling = new Filling();
			Filling.id = c.getInt(c.getColumnIndex("id"));
			Filling.type = c.getString(c.getColumnIndex("type"));
			Filling.question = c.getString(c.getColumnIndex("question"));
			Filling.reference_answer = c.getString(c
					.getColumnIndex("reference_answer"));
			Filling.analysis_answer = c.getString(c
					.getColumnIndex("analysis_answer"));
			Filling.score = c.getInt(c.getColumnIndex("score"));
			Filling.ques_difficult = c.getInt(c
					.getColumnIndex("ques_difficult"));
			Filling.question_num = c.getInt(c.getColumnIndex("question_num"));
			Filling.paper_id = c.getInt(c.getColumnIndex("paper_id"));

			Fillings.add(Filling);
		}
		c.close();
		return Fillings;
	}

	public List<SingleSel> query_singlesel_by_paper_id(int paper_id) {

		ArrayList<SingleSel> SingleSels = new ArrayList<SingleSel>();
		Cursor c = db.rawQuery("SELECT * FROM SingleSel where paper_id ="
				+ paper_id, null);
		while (c.moveToNext()) {
			SingleSel SingleSel = new SingleSel();
			SingleSel.id = c.getInt(c.getColumnIndex("id"));
			SingleSel.type = c.getString(c.getColumnIndex("type"));
			SingleSel.question = c.getString(c.getColumnIndex("question"));
			SingleSel.choiseA = c.getString(c.getColumnIndex("choiseA"));
			SingleSel.choiseB = c.getString(c.getColumnIndex("choiseB"));
			SingleSel.choiseC = c.getString(c.getColumnIndex("choiseC"));
			SingleSel.choiseD = c.getString(c.getColumnIndex("choiseD"));
			SingleSel.reference_answer = c.getString(c
					.getColumnIndex("reference_answer"));
			SingleSel.analysis_answer = c.getString(c
					.getColumnIndex("analysis_answer"));
			SingleSel.score = c.getInt(c.getColumnIndex("score"));
			SingleSel.ques_difficult = c.getInt(c
					.getColumnIndex("ques_difficult"));
			SingleSel.question_num = c.getInt(c.getColumnIndex("question_num"));
			SingleSel.paper_id = c.getInt(c.getColumnIndex("paper_id"));

			SingleSels.add(SingleSel);
		}
		c.close();
		return SingleSels;
	}

	public List<MultiSel> query_multisel_by_paper_id(int paper_id) {

		ArrayList<MultiSel> MultiSels = new ArrayList<MultiSel>();
		Cursor c = db.rawQuery("SELECT * FROM MultiSel where paper_id ="
				+ paper_id, null);
		while (c.moveToNext()) {
			MultiSel MultiSel = new MultiSel();
			MultiSel.id = c.getInt(c.getColumnIndex("id"));
			MultiSel.type = c.getString(c.getColumnIndex("type"));
			MultiSel.question = c.getString(c.getColumnIndex("question"));
			MultiSel.choiseA = c.getString(c.getColumnIndex("choiseA"));
			MultiSel.choiseB = c.getString(c.getColumnIndex("choiseB"));
			MultiSel.choiseC = c.getString(c.getColumnIndex("choiseC"));
			MultiSel.choiseD = c.getString(c.getColumnIndex("choiseD"));
			MultiSel.choiseE = c.getString(c.getColumnIndex("choiseE"));
			MultiSel.reference_answer = c.getString(c
					.getColumnIndex("reference_answer"));
			MultiSel.analysis_answer = c.getString(c
					.getColumnIndex("analysis_answer"));
			MultiSel.score = c.getInt(c.getColumnIndex("score"));
			MultiSel.ques_difficult = c.getInt(c
					.getColumnIndex("ques_difficult"));
			MultiSel.question_num = c.getInt(c.getColumnIndex("question_num"));
			MultiSel.paper_id = c.getInt(c.getColumnIndex("paper_id"));

			MultiSels.add(MultiSel);
		}
		c.close();
		return MultiSels;
	}

	public List<EssayQuestion> query_essayquestion_by_paper_id(int paper_id) {

		ArrayList<EssayQuestion> EssayQuestions = new ArrayList<EssayQuestion>();
		Cursor c = db.rawQuery("SELECT * FROM EssayQuestion where paper_id ="
				+ paper_id, null);
		while (c.moveToNext()) {
			EssayQuestion EssayQuestion = new EssayQuestion();
			EssayQuestion.id = c.getInt(c.getColumnIndex("id"));
			EssayQuestion.type = c.getString(c.getColumnIndex("type"));
			EssayQuestion.question = c.getString(c.getColumnIndex("question"));
			EssayQuestion.reference_answer = c.getString(c
					.getColumnIndex("reference_answer"));
			EssayQuestion.analysis_answer = c.getString(c
					.getColumnIndex("analysis_answer"));
			EssayQuestion.score = c.getInt(c.getColumnIndex("score"));
			EssayQuestion.ques_difficult = c.getInt(c
					.getColumnIndex("ques_difficult"));
			EssayQuestion.question_num = c.getInt(c
					.getColumnIndex("question_num"));
			EssayQuestion.paper_id = c.getInt(c.getColumnIndex("paper_id"));

			EssayQuestions.add(EssayQuestion);
		}
		c.close();
		return EssayQuestions;
	}

	/**
	 * close database
	 */
	public void closeDB() {
		db.close();
	}

	public void init_drop_tables() {
		execSqlite("drop  table if exists QuestionPaper");
		execSqlite("drop table if exists RightOrWrong");
		execSqlite("drop table if exists Filling");
		execSqlite("drop table if exists SingleSel");
		execSqlite("drop table if exists MultiSel");
		execSqlite("drop table if exists EssayQuestion");
	}

	public void init_create_tables() {

		// �Ծ��-> �Ծ�id,�Ծ��ţ���Ŀ��ţ��Ծ����ͣ��Ծ���ƣ��û��𰸣���׼�𰸣��ɼ����û�ѡ��Ѷ�
		String qp_sql = "CREATE TABLE if not exists QuestionPaper(id INTEGER PRIMARY KEY AUTOINCREMENT,paper_serial INT KEY NOT NULL,type TEXT  NOT NULL,"
				+ "name TEXT  NOT NULL,user_answer TEXT,score int)";

		execSqlite(qp_sql);

		// �ж���
		String rw_sql = "CREATE TABLE if not exists RightOrWrong(id INTEGER PRIMARY KEY AUTOINCREMENT,type TEXT  NOT NULL,question TEXT  NOT NULL,"
				+ "reference_answer TEXT  NOT NULL,analysis_answer TEXT,score int,ques_difficult int,question_num int  NOT NULL,paper_id int NOT NULL)";

		execSqlite(rw_sql);

		// �����
		String filling_sql = "CREATE TABLE if not exists Filling(id INTEGER PRIMARY KEY AUTOINCREMENT,type TEXT  NOT NULL,question TEXT  NOT NULL,"
				+ "reference_answer TEXT  NOT NULL,analysis_answer TEXT,score int,ques_difficult int,question_num int  NOT NULL,paper_id int NOT NULL)";

		execSqlite(filling_sql);

		// ��ѡ��
		String sq_sql = "CREATE TABLE if not exists SingleSel(id INTEGER PRIMARY KEY AUTOINCREMENT,type TEXT  NOT NULL,question TEXT  NOT NULL,"
				+ "choiseA TEXT  NOT NULL,choiseB TEXT  NOT NULL,choiseC TEXT  NOT NULL,choiseD TEXT  NOT NULL,"
				+ "reference_answer TEXT  NOT NULL,analysis_answer TEXT,score int,ques_difficult int,question_num int  NOT NULL,paper_id int NOT NULL)";
		execSqlite(sq_sql);

		// ��ѡ��
		String mq_sql = "CREATE TABLE if not exists MultiSel(id INTEGER PRIMARY KEY AUTOINCREMENT,type TEXT  NOT NULL,question TEXT  NOT NULL,"
				+ "choiseA TEXT  NOT NULL,choiseB TEXT  NOT NULL,choiseC TEXT  NOT NULL,choiseD TEXT  NOT NULL,choiseE TEXT  NOT NULL,"
				+ "reference_answer TEXT  NOT NULL,analysis_answer TEXT,score int,ques_difficult int,question_num int  NOT NULL,paper_id int NOT NULL)";

		execSqlite(mq_sql);

		// �ʴ���
		String eq_sql = "CREATE TABLE if not exists EssayQuestion(id INTEGER PRIMARY KEY AUTOINCREMENT,type TEXT  NOT NULL,question TEXT  NOT NULL,"
				+ "reference_answer TEXT  NOT NULL,analysis_answer TEXT,score int,ques_difficult int,question_num int  NOT NULL,paper_id int NOT NULL)";

		execSqlite(eq_sql);
	}

	public void init_recreate_db() {
		// ����ձ�
		init_drop_tables();
		init_create_tables();
		init_insert_data();

	}

	public void init_insert_data1() {

		// �Ծ��
		List<SimulationQuestionPaper> SimulationQuestionPapers = new ArrayList<SimulationQuestionPaper>();

		SimulationQuestionPaper SimulationQuestionPaper1 = new SimulationQuestionPaper(
				1, 1, "һ�����09������", "09������", "�û���1", 100);
		SimulationQuestionPaper SimulationQuestionPaper2 = new SimulationQuestionPaper(
				2, 2, "һ�����09������", "10������", "�û���2", 100);
		SimulationQuestionPaper SimulationQuestionPaper3 = new SimulationQuestionPaper(
				3, 3, "һ�����09������", "11������", "�û���3", 100);
		SimulationQuestionPaper SimulationQuestionPaper4 = new SimulationQuestionPaper(
				4, 4, "һ�����09������", "12������", "�û���4", 100);
		SimulationQuestionPaper SimulationQuestionPaper5 = new SimulationQuestionPaper(
				5, 5, "һ�����09������", "13������", "�û���5", 100);

		SimulationQuestionPapers.add(SimulationQuestionPaper1);
		SimulationQuestionPapers.add(SimulationQuestionPaper2);
		SimulationQuestionPapers.add(SimulationQuestionPaper3);
		SimulationQuestionPapers.add(SimulationQuestionPaper4);
		SimulationQuestionPapers.add(SimulationQuestionPaper5);

		addQuestionPaper(SimulationQuestionPapers);

		List<RightOrWrong> RightOrWrongs = new ArrayList<RightOrWrong>();

		RightOrWrong RightOrWrong1 = new RightOrWrong(1, "һ�����09������", "����1",
				"�ο���1", "����", 40, 1, 1, 1);

		RightOrWrong RightOrWrong2 = new RightOrWrong(2, "һ�����10������", "����2",
				"�ο���2", "����", 40, 1, 1, 1);
		RightOrWrong RightOrWrong3 = new RightOrWrong(3, "һ�����11������", "����3",
				"�ο���3", "����", 40, 1, 1, 1);
		RightOrWrong RightOrWrong4 = new RightOrWrong(4, "һ�����12������", "����4",
				"�ο���4", "����", 40, 1, 1, 1);
		RightOrWrong RightOrWrong5 = new RightOrWrong(5, "һ�����13������", "����5",
				"�ο���5", "����", 40, 1, 1, 1);

		RightOrWrongs.add(RightOrWrong1);
		RightOrWrongs.add(RightOrWrong2);
		RightOrWrongs.add(RightOrWrong3);
		RightOrWrongs.add(RightOrWrong4);
		RightOrWrongs.add(RightOrWrong5);

		addRightOrWrong(RightOrWrongs);

		// �����
		List<Filling> Fillings = new ArrayList<Filling>();

		Filling Filling1 = new Filling(1, "һ�����09������", "����1", "�ο���1", "����",
				40, 1, 1, 1);
		Filling Filling2 = new Filling(2, "һ�����10������", "����2", "�ο���2", "����",
				40, 1, 1, 1);
		Filling Filling3 = new Filling(3, "һ�����11������", "����3", "�ο���3", "����",
				40, 1, 1, 1);
		Filling Filling4 = new Filling(4, "һ�����12������", "����4", "�ο���4", "����",
				40, 1, 1, 1);
		Filling Filling5 = new Filling(5, "һ�����13������", "����5", "�ο���5", "����",
				40, 1, 1, 1);

		Fillings.add(Filling1);
		Fillings.add(Filling2);
		Fillings.add(Filling3);
		Fillings.add(Filling4);
		Fillings.add(Filling5);

		addFilling(Fillings);

		// ��ѡ��
		List<SingleSel> SingleSels = new ArrayList<SingleSel>();

		SingleSel SingleSel = new SingleSel(1, "һ�����09������", "����1", "ѡ��A",
				"ѡ��B", "ѡ��C", "ѡ��D", "�ο���1", "����", 40, 1, 1, 1);
		SingleSel SingleSe2 = new SingleSel(2, "һ�����10������", "����2", "ѡ��A",
				"ѡ��B", "ѡ��C", "ѡ��D", "�ο���2", "����", 40, 1, 1, 1);
		SingleSel SingleSe3 = new SingleSel(3, "һ�����11������", "����3", "ѡ��A",
				"ѡ��B", "ѡ��C", "ѡ��D", "�ο���3", "����", 40, 1, 1, 1);
		SingleSel SingleSe4 = new SingleSel(4, "һ�����12������", "����4", "ѡ��A",
				"ѡ��B", "ѡ��C", "ѡ��D", "�ο���4", "����", 40, 1, 1, 1);
		SingleSel SingleSe5 = new SingleSel(5, "һ�����13������", "����5", "ѡ��A",
				"ѡ��B", "ѡ��C", "ѡ��D", "�ο���5", "����", 40, 1, 1, 1);

		SingleSels.add(SingleSel);
		SingleSels.add(SingleSe2);
		SingleSels.add(SingleSe3);
		SingleSels.add(SingleSe4);
		SingleSels.add(SingleSe5);

		addSingleSel(SingleSels);

		// ��ѡ��
		List<MultiSel> MultiSels = new ArrayList<MultiSel>();

		MultiSel MultiSel = new MultiSel(1, "һ�����09������", "����1", "ѡ��A", "ѡ��B",
				"ѡ��C", "ѡ��D", "ѡ��E", "�ο���1", "����", 40, 1, 1, 1);
		MultiSel MultiSe2 = new MultiSel(2, "һ�����10������", "����2", "ѡ��A", "ѡ��B",
				"ѡ��C", "ѡ��D", "ѡ��E", "�ο���2", "����", 40, 1, 1, 1);
		MultiSel MultiSe3 = new MultiSel(3, "һ�����11������", "����3", "ѡ��A", "ѡ��B",
				"ѡ��C", "ѡ��D", "ѡ��E", "�ο���3", "����", 40, 1, 1, 1);
		MultiSel MultiSe4 = new MultiSel(4, "һ�����12������", "����4", "ѡ��A", "ѡ��B",
				"ѡ��C", "ѡ��D", "ѡ��E", "�ο���4", "����", 40, 1, 1, 1);
		MultiSel MultiSe5 = new MultiSel(5, "һ�����13������", "����5", "ѡ��A", "ѡ��B",
				"ѡ��C", "ѡ��D", "ѡ��E", "�ο���5", "����", 40, 1, 1, 1);

		MultiSels.add(MultiSel);
		MultiSels.add(MultiSe2);
		MultiSels.add(MultiSe3);
		MultiSels.add(MultiSe4);
		MultiSels.add(MultiSe5);

		addMultiSel(MultiSels);

		// ������
		List<EssayQuestion> EssayQuestions = new ArrayList<EssayQuestion>();

		EssayQuestion EssayQuestion1 = new EssayQuestion(1, "һ�����09������", "����1",
				"�ο���1", "����", 40, 1, 1, 1);
		EssayQuestion EssayQuestion2 = new EssayQuestion(2, "һ�����10������", "����2",
				"�ο���2", "����", 40, 1, 1, 1);
		EssayQuestion EssayQuestion3 = new EssayQuestion(3, "һ�����11������", "����3",
				"�ο���3", "����", 40, 1, 1, 1);
		EssayQuestion EssayQuestion4 = new EssayQuestion(4, "һ�����12������", "����4",
				"�ο���4", "����", 40, 1, 1, 1);
		EssayQuestion EssayQuestion5 = new EssayQuestion(5, "һ�����13������", "����5",
				"�ο���5", "����", 40, 1, 1, 1);

		EssayQuestions.add(EssayQuestion1);
		EssayQuestions.add(EssayQuestion2);
		EssayQuestions.add(EssayQuestion3);
		EssayQuestions.add(EssayQuestion4);
		EssayQuestions.add(EssayQuestion5);

		addEssayQuestion(EssayQuestions);

	}

	public void init_insert_data() {

		// �Ծ�� example: һ���Ծ� = 40ѡ���� + 20��ѡ�� + 5������
		SimulationQuestionPaper SimulationQuestionPaper = new SimulationQuestionPaper(
				1, 1, "һ�����09������", "09������", "�û���1", 100);
		int lastId = InsertOneQuestionPaper(SimulationQuestionPaper);

		Log.v("lastId", String.valueOf(lastId));

		// �ж��� 10 ��
		for (int n = 0; n < 10; n++) {
			RightOrWrong RightOrWrong = new RightOrWrong(lastId, "һ�����09������",
					"����" + (n + 1), "�ο���" + (n + 1), "����" + (n + 1), 40, 1,
					n + 1, lastId);

			InsertOneRightOrWrong(lastId, RightOrWrong);
		}

		// �����
		for (int n = 0; n < 5; n++) {
			Filling Filling = new Filling(lastId, "һ�����09������", "����" + (n + 1),
					"�ο���" + (n + 1), "����" + (n + 1), 40, 1, n + 1, lastId);
			InsertOneFilling(lastId, Filling);
		}

		// ��ѡ��
		for (int n = 0; n < 40; n++) {

			String reference_answer = "A";
			if (n % 4 == 1)
				reference_answer = "A";
			if (n % 4 == 2)
				reference_answer = "B";
			if (n % 4 == 3)
				reference_answer = "C";
			if (n % 4 == 4)
				reference_answer = "D";

			SingleSel SingleSel = new SingleSel(lastId, "һ�����09������", "����"
					+ (n + 1), "ѡ��A", "ѡ��B", "ѡ��C", "ѡ��D", reference_answer
					+ (n + 1), "����" + (n + 1), 40, 1, n + 1, lastId);
			InsertOneSingleSel(lastId, SingleSel);
		}

		// ��ѡ��
		for (int n = 0; n < 20; n++) {
			MultiSel MultiSel = new MultiSel(lastId, "һ�����09������", "����"
					+ (n + 1), "ѡ��A", "ѡ��B", "ѡ��C", "ѡ��D", "ѡ��E", "�ο���"
					+ (n + 1), "����" + (n + 1), 40, 1, n + 1, lastId);
			InsertOneMultiSel(lastId, MultiSel);
		}

		// ������
		for (int n = 0; n < 5; n++) {
			EssayQuestion EssayQuestion = new EssayQuestion(lastId,
					"һ�����09������", "����" + (n + 1), "�ο���" + (n + 1), "����"
							+ (n + 1), 40, 1, n + 1, lastId);
			InsertOneEssayQuestion(lastId, EssayQuestion);
		}

	}
}