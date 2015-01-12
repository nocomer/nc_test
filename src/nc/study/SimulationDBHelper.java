package nc.study;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SimulationDBHelper extends SQLiteOpenHelper {

	//private static final String DATABASE_NAME = "SimulationExamination.db";
	private static final String DATABASE_NAME = "se.db";
	private static final int DATABASE_VERSION = 1;

	public SimulationDBHelper(Context context) {
		// CursorFactory����Ϊnull,ʹ��Ĭ��ֵ
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// ��ݿ��һ�α�����ʱonCreate�ᱻ����
	@Override
	public void onCreate(SQLiteDatabase db) {
		/*db.execSQL("CREATE TABLE IF NOT EXISTS SimulationExamination"
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,type VARCHAR,name VARCHAR,question VARCHAR,question_opt VARCHAR,standard_answer VARCHAR,user_answer VARCHAR)");*/
	}

	// ���DATABASE_VERSIONֵ����Ϊ2,ϵͳ����������ݿ�汾��ͬ,�������onUpgrade
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("ALTER TABLE SimulationExamination ADD COLUMN other STRING");
	}
}