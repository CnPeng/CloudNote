package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Peng on 2016/7/24.
 */
public class NoteDatabaseHepler extends SQLiteOpenHelper {
//    public NoteDatabaseHepler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, "notedb.db", null, 1);
//    }
    public NoteDatabaseHepler(Context context) {
        super(context,"notedb.db", null, 1);
    }


    @Override   //初始化数据库
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table note (_id integer primary key autoincrement,title varchar(40),content varchar(40),time varchar(40) )");

    }

    @Override   //版本升级时使用
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("alter table note add time varchar(40)"); 运行时报错,提示不存在的行
    }
}
