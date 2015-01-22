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
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName,
		// 0,mFactory);
		// 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		db = helper.getWritableDatabase();
	}

	public void addQuestionPaper(
			List<SimulationQuestionPaper> SimulationQuestionPapers) {

		db.beginTransaction(); // 开始事务
		try {
			for (SimulationQuestionPaper SimulationQuestionPaper : SimulationQuestionPapers) {
				db.execSQL("INSERT INTO QuestionPaper VALUES(null,?,?,?,?,?)",
						new Object[] { SimulationQuestionPaper.type,
								SimulationQuestionPaper.paper_serial,
								SimulationQuestionPaper.name,
								SimulationQuestionPaper.user_answer,
								SimulationQuestionPaper.score, });
			}
			db.setTransactionSuccessful(); // 设置事务成功完成

		} finally {
			db.endTransaction(); // 结束事务
		}

	}

	/* Insert the data to the table */
	public int InsertOneQuestionPaper(
			SimulationQuestionPaper SimulationQuestionPaper) {

		int lastId = 0;
		db.beginTransaction(); // 开始事务
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

			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}

		return lastId;
	}

	public void addRightOrWrong(List<RightOrWrong> RightOrWrongs) {
		db.beginTransaction(); // 开始事务
		try {
			for (RightOrWrong RightOrWrong : RightOrWrongs) {
				db.execSQL("INSERT INTO RightOrWrong VALUES(null,?,?,?,?,?)",
						new Object[] { RightOrWrong.type,
								RightOrWrong.question,
								RightOrWrong.reference_answer,
								RightOrWrong.score,
								RightOrWrong.ques_difficult, });
			}
			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void InsertOneRightOrWrong(int id, RightOrWrong RightOrWrong) {
		db.beginTransaction(); // 开始事务
		try {

			db.execSQL("INSERT INTO RightOrWrong VALUES(null,?,?,?,?,?,?,?,?)",
					new Object[] { RightOrWrong.type, RightOrWrong.question,
							RightOrWrong.reference_answer,
							RightOrWrong.analysis_answer, RightOrWrong.score,
							RightOrWrong.ques_difficult,
							RightOrWrong.question_num, RightOrWrong.paper_id });

			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void InsertOneFilling(int id, Filling Filling) {
		db.beginTransaction(); // 开始事务
		try {

			db.execSQL("INSERT INTO Filling VALUES(null,?,?,?,?,?,?,?,?)",
					new Object[] { Filling.type, Filling.question,
							Filling.reference_answer, Filling.analysis_answer,
							Filling.score, Filling.ques_difficult,
							Filling.question_num, Filling.paper_id });

			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void addFilling(List<Filling> Fillings) {
		db.beginTransaction(); // 开始事务
		try {
			for (Filling Filling : Fillings) {
				db.execSQL("INSERT INTO Filling VALUES(null,?,?,?,?,?,?)",
						new Object[] { Filling.type, Filling.question,
								Filling.reference_answer, Filling.score,
								Filling.ques_difficult, });
			}
			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void InsertOneSingleSel(int id, SingleSel SingleSel) {
		db.beginTransaction(); // 开始事务
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

			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void addSingleSel(List<SingleSel> SingleSels) {
		db.beginTransaction(); // 开始事务
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
			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void InsertOneMultiSel(int id, MultiSel MultiSel) {
		db.beginTransaction(); // 开始事务
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

			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void addMultiSel(List<MultiSel> MultiSels) {
		db.beginTransaction(); // 开始事务
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
			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void InsertOneEssayQuestion(int id, EssayQuestion EssayQuestion) {
		db.beginTransaction(); // 开始事务
		try {

			db.execSQL(
					"INSERT INTO EssayQuestion VALUES(null,?,?,?,?,?,?,?,?)",
					new Object[] { EssayQuestion.type, EssayQuestion.question,
							EssayQuestion.reference_answer,
							EssayQuestion.analysis_answer, EssayQuestion.score,
							EssayQuestion.ques_difficult,
							EssayQuestion.question_num, EssayQuestion.paper_id });

			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void addEssayQuestion(List<EssayQuestion> EssayQuestions) {
		db.beginTransaction(); // 开始事务
		try {
			for (EssayQuestion EssayQuestion : EssayQuestions) {
				db.execSQL("INSERT INTO EssayQuestion VALUES(null,?,?,?,?,?)",
						new Object[] { EssayQuestion.type,
								EssayQuestion.question,
								EssayQuestion.reference_answer,
								EssayQuestion.score,
								EssayQuestion.ques_difficult, });
			}
			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void execSqlite(String sql) {
		db.beginTransaction(); // 开始事务
		try {
			db.execSQL(sql);
			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
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

	// 按试id查找试卷
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

	// 按试卷名查找试卷
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

	// 按试卷名查找试卷
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

	// 根据题目编号查判断题
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

		// 试卷表-> 试卷id,试卷编号，题目编号，试卷类型，试卷名称，用户答案，标准答案，成绩，用户选项，难度
		String qp_sql = "CREATE TABLE if not exists QuestionPaper(id INTEGER PRIMARY KEY AUTOINCREMENT,paper_serial INT KEY NOT NULL,type TEXT  NOT NULL,"
				+ "name TEXT  NOT NULL,user_answer TEXT,score int)";

		execSqlite(qp_sql);

		// 判断题
		String rw_sql = "CREATE TABLE if not exists RightOrWrong(id INTEGER PRIMARY KEY AUTOINCREMENT,type TEXT  NOT NULL,question TEXT  NOT NULL,"
				+ "reference_answer TEXT  NOT NULL,analysis_answer TEXT,score int,ques_difficult int,question_num int  NOT NULL,paper_id int NOT NULL)";

		execSqlite(rw_sql);

		// 填空题
		String filling_sql = "CREATE TABLE if not exists Filling(id INTEGER PRIMARY KEY AUTOINCREMENT,type TEXT  NOT NULL,question TEXT  NOT NULL,"
				+ "reference_answer TEXT  NOT NULL,analysis_answer TEXT,score int,ques_difficult int,question_num int  NOT NULL,paper_id int NOT NULL)";

		execSqlite(filling_sql);

		// 单选题
		String sq_sql = "CREATE TABLE if not exists SingleSel(id INTEGER PRIMARY KEY AUTOINCREMENT,type TEXT  NOT NULL,question TEXT  NOT NULL,"
				+ "choiseA TEXT  NOT NULL,choiseB TEXT  NOT NULL,choiseC TEXT  NOT NULL,choiseD TEXT  NOT NULL,"
				+ "reference_answer TEXT  NOT NULL,analysis_answer TEXT,score int,ques_difficult int,question_num int  NOT NULL,paper_id int NOT NULL)";
		execSqlite(sq_sql);

		// 多选题
		String mq_sql = "CREATE TABLE if not exists MultiSel(id INTEGER PRIMARY KEY AUTOINCREMENT,type TEXT  NOT NULL,question TEXT  NOT NULL,"
				+ "choiseA TEXT  NOT NULL,choiseB TEXT  NOT NULL,choiseC TEXT  NOT NULL,choiseD TEXT  NOT NULL,choiseE TEXT  NOT NULL,"
				+ "reference_answer TEXT  NOT NULL,analysis_answer TEXT,score int,ques_difficult int,question_num int  NOT NULL,paper_id int NOT NULL)";

		execSqlite(mq_sql);

		// 问答题
		String eq_sql = "CREATE TABLE if not exists EssayQuestion(id INTEGER PRIMARY KEY AUTOINCREMENT,type TEXT  NOT NULL,question TEXT  NOT NULL,"
				+ "reference_answer TEXT  NOT NULL,analysis_answer TEXT,score int,ques_difficult int,question_num int  NOT NULL,paper_id int NOT NULL)";

		execSqlite(eq_sql);
	}

	public void init_recreate_db() {
		// 先清空表
		init_drop_tables();
		init_create_tables();
		init_insert_data();

	}

	public void init_insert_data1() {

		// 试卷表
		List<SimulationQuestionPaper> SimulationQuestionPapers = new ArrayList<SimulationQuestionPaper>();

		SimulationQuestionPaper SimulationQuestionPaper1 = new SimulationQuestionPaper(
				1, 1, "一建机电09年真题", "09年真题", "用户答案1", 100);
		SimulationQuestionPaper SimulationQuestionPaper2 = new SimulationQuestionPaper(
				2, 2, "一建机电09年真题", "10年真题", "用户答案2", 100);
		SimulationQuestionPaper SimulationQuestionPaper3 = new SimulationQuestionPaper(
				3, 3, "一建机电09年真题", "11年真题", "用户答案3", 100);
		SimulationQuestionPaper SimulationQuestionPaper4 = new SimulationQuestionPaper(
				4, 4, "一建机电09年真题", "12年真题", "用户答案4", 100);
		SimulationQuestionPaper SimulationQuestionPaper5 = new SimulationQuestionPaper(
				5, 5, "一建机电09年真题", "13年真题", "用户答案5", 100);

		SimulationQuestionPapers.add(SimulationQuestionPaper1);
		SimulationQuestionPapers.add(SimulationQuestionPaper2);
		SimulationQuestionPapers.add(SimulationQuestionPaper3);
		SimulationQuestionPapers.add(SimulationQuestionPaper4);
		SimulationQuestionPapers.add(SimulationQuestionPaper5);

		addQuestionPaper(SimulationQuestionPapers);

		List<RightOrWrong> RightOrWrongs = new ArrayList<RightOrWrong>();

		RightOrWrong RightOrWrong1 = new RightOrWrong(1, "一建机电09年真题", "问题1",
				"参考答案1", "解析", 40, 1, 1, 1);

		RightOrWrong RightOrWrong2 = new RightOrWrong(2, "一建机电10年真题", "问题2",
				"参考答案2", "解析", 40, 1, 1, 1);
		RightOrWrong RightOrWrong3 = new RightOrWrong(3, "一建机电11年真题", "问题3",
				"参考答案3", "解析", 40, 1, 1, 1);
		RightOrWrong RightOrWrong4 = new RightOrWrong(4, "一建机电12年真题", "问题4",
				"参考答案4", "解析", 40, 1, 1, 1);
		RightOrWrong RightOrWrong5 = new RightOrWrong(5, "一建机电13年真题", "问题5",
				"参考答案5", "解析", 40, 1, 1, 1);

		RightOrWrongs.add(RightOrWrong1);
		RightOrWrongs.add(RightOrWrong2);
		RightOrWrongs.add(RightOrWrong3);
		RightOrWrongs.add(RightOrWrong4);
		RightOrWrongs.add(RightOrWrong5);

		addRightOrWrong(RightOrWrongs);

		// 填空题
		List<Filling> Fillings = new ArrayList<Filling>();

		Filling Filling1 = new Filling(1, "一建机电09年真题", "问题1", "参考答案1", "解析",
				40, 1, 1, 1);
		Filling Filling2 = new Filling(2, "一建机电10年真题", "问题2", "参考答案2", "解析",
				40, 1, 1, 1);
		Filling Filling3 = new Filling(3, "一建机电11年真题", "问题3", "参考答案3", "解析",
				40, 1, 1, 1);
		Filling Filling4 = new Filling(4, "一建机电12年真题", "问题4", "参考答案4", "解析",
				40, 1, 1, 1);
		Filling Filling5 = new Filling(5, "一建机电13年真题", "问题5", "参考答案5", "解析",
				40, 1, 1, 1);

		Fillings.add(Filling1);
		Fillings.add(Filling2);
		Fillings.add(Filling3);
		Fillings.add(Filling4);
		Fillings.add(Filling5);

		addFilling(Fillings);

		// 单选题
		List<SingleSel> SingleSels = new ArrayList<SingleSel>();

		SingleSel SingleSel = new SingleSel(1, "一建机电09年真题", "问题1", "选项A",
				"选项B", "选项C", "选项D", "参考答案1", "解析", 40, 1, 1, 1);
		SingleSel SingleSe2 = new SingleSel(2, "一建机电10年真题", "问题2", "选项A",
				"选项B", "选项C", "选项D", "参考答案2", "解析", 40, 1, 1, 1);
		SingleSel SingleSe3 = new SingleSel(3, "一建机电11年真题", "问题3", "选项A",
				"选项B", "选项C", "选项D", "参考答案3", "解析", 40, 1, 1, 1);
		SingleSel SingleSe4 = new SingleSel(4, "一建机电12年真题", "问题4", "选项A",
				"选项B", "选项C", "选项D", "参考答案4", "解析", 40, 1, 1, 1);
		SingleSel SingleSe5 = new SingleSel(5, "一建机电13年真题", "问题5", "选项A",
				"选项B", "选项C", "选项D", "参考答案5", "解析", 40, 1, 1, 1);

		SingleSels.add(SingleSel);
		SingleSels.add(SingleSe2);
		SingleSels.add(SingleSe3);
		SingleSels.add(SingleSe4);
		SingleSels.add(SingleSe5);

		addSingleSel(SingleSels);

		// 多选题
		List<MultiSel> MultiSels = new ArrayList<MultiSel>();

		MultiSel MultiSel = new MultiSel(1, "一建机电09年真题", "问题1", "选项A", "选项B",
				"选项C", "选项D", "选项E", "参考答案1", "解析", 40, 1, 1, 1);
		MultiSel MultiSe2 = new MultiSel(2, "一建机电10年真题", "问题2", "选项A", "选项B",
				"选项C", "选项D", "选项E", "参考答案2", "解析", 40, 1, 1, 1);
		MultiSel MultiSe3 = new MultiSel(3, "一建机电11年真题", "问题3", "选项A", "选项B",
				"选项C", "选项D", "选项E", "参考答案3", "解析", 40, 1, 1, 1);
		MultiSel MultiSe4 = new MultiSel(4, "一建机电12年真题", "问题4", "选项A", "选项B",
				"选项C", "选项D", "选项E", "参考答案4", "解析", 40, 1, 1, 1);
		MultiSel MultiSe5 = new MultiSel(5, "一建机电13年真题", "问题5", "选项A", "选项B",
				"选项C", "选项D", "选项E", "参考答案5", "解析", 40, 1, 1, 1);

		MultiSels.add(MultiSel);
		MultiSels.add(MultiSe2);
		MultiSels.add(MultiSe3);
		MultiSels.add(MultiSe4);
		MultiSels.add(MultiSe5);

		addMultiSel(MultiSels);

		// 案例题
		List<EssayQuestion> EssayQuestions = new ArrayList<EssayQuestion>();

		EssayQuestion EssayQuestion1 = new EssayQuestion(1, "一建机电09年真题", "问题1",
				"参考答案1", "解析", 40, 1, 1, 1);
		EssayQuestion EssayQuestion2 = new EssayQuestion(2, "一建机电10年真题", "问题2",
				"参考答案2", "解析", 40, 1, 1, 1);
		EssayQuestion EssayQuestion3 = new EssayQuestion(3, "一建机电11年真题", "问题3",
				"参考答案3", "解析", 40, 1, 1, 1);
		EssayQuestion EssayQuestion4 = new EssayQuestion(4, "一建机电12年真题", "问题4",
				"参考答案4", "解析", 40, 1, 1, 1);
		EssayQuestion EssayQuestion5 = new EssayQuestion(5, "一建机电13年真题", "问题5",
				"参考答案5", "解析", 40, 1, 1, 1);

		EssayQuestions.add(EssayQuestion1);
		EssayQuestions.add(EssayQuestion2);
		EssayQuestions.add(EssayQuestion3);
		EssayQuestions.add(EssayQuestion4);
		EssayQuestions.add(EssayQuestion5);

		addEssayQuestion(EssayQuestions);

	}

	public void init_insert_data() {

		// 试卷表 example: 一张试卷 = 40选题题 + 20多选题 + 5案例题
		SimulationQuestionPaper SimulationQuestionPaper = new SimulationQuestionPaper(
				1, 1, "一建机电09年真题", "09年真题", "用户答案1", 100);
		int lastId = InsertOneQuestionPaper(SimulationQuestionPaper);

		Log.v("lastId", String.valueOf(lastId));

		// 判断题 10 道
		for (int n = 0; n < 10; n++) {
			RightOrWrong RightOrWrong = new RightOrWrong(lastId, "一建机电09年真题",
					"问题" + (n + 1), "参考答案" + (n + 1), "解析" + (n + 1), 40, 1,
					n + 1, lastId);

			InsertOneRightOrWrong(lastId, RightOrWrong);
		}

		// 填空题
		for (int n = 0; n < 5; n++) {
			Filling Filling = new Filling(lastId, "一建机电09年真题", "问题" + (n + 1),
					"参考答案" + (n + 1), "解析" + (n + 1), 40, 1, n + 1, lastId);
			InsertOneFilling(lastId, Filling);
		}

		// 单选题
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

			SingleSel SingleSel = new SingleSel(lastId, "一建机电09年真题", "问题"
					+ (n + 1), "选项A", "选项B", "选项C", "选项D", reference_answer
					+ (n + 1), "解析" + (n + 1), 40, 1, n + 1, lastId);
			InsertOneSingleSel(lastId, SingleSel);
		}

		// 多选题
		for (int n = 0; n < 20; n++) {
			MultiSel MultiSel = new MultiSel(lastId, "一建机电09年真题", "问题"
					+ (n + 1), "选项A", "选项B", "选项C", "选项D", "选项E", "参考答案"
					+ (n + 1), "解析" + (n + 1), 40, 1, n + 1, lastId);
			InsertOneMultiSel(lastId, MultiSel);
		}

		// 案例题
		for (int n = 0; n < 5; n++) {
			EssayQuestion EssayQuestion = new EssayQuestion(lastId,
					"一建机电09年真题", "问题" + (n + 1), "参考答案" + (n + 1), "解析"
							+ (n + 1), 40, 1, n + 1, lastId);
			InsertOneEssayQuestion(lastId, EssayQuestion);
		}

	}
}