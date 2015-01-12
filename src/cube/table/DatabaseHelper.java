package cube.table;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseHelper extends SQLiteOpenHelper {
	// ��ݿ�汾
	public static int dbVer = 1;
	// ��ݿ����
	public static String DB_NAME = "QBOX_DB";

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	
	public void onCreate(SQLiteDatabase db) {						  		
		String sql = "create table tops (mname varchar(10),mtime float)";
        db.execSQL(sql);
        sql ="insert into tops (mname,mtime) VALUES ('����',19.60)";
        db.execSQL(sql);
	}

	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		  String sql = "drop table if exists tops";
          db.execSQL(sql);
          onCreate(db);
	}		
}
