package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bean.MyNote;

/**
 * Created by Peng on 2016/7/24.
 */
public class NoteDao {

    private final NoteDatabaseHepler helper;
    Context context;
    public NoteDao(Context context) {
        helper = new NoteDatabaseHepler(context);
       this.context=context;
    }



    /**增加*/
    public void add(String title,String content,String time){
        SQLiteDatabase db= helper.getWritableDatabase();

        if (content!=null){
           //  db.execSQL("insert into note(title,content,time)values(?,?,?)",new String[]{title,content,time});
            ContentValues values=new ContentValues();
            values.put("title",title);
            values.put("content",content);
            values.put("time",time);
            db.insert("note",null,values);

            Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "内容不能为空", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
    /**删除*/
    public void delete(String id){
        SQLiteDatabase db=helper.getWritableDatabase();
        int affectRows=db.delete("note","_id=?",new String[]{id});
        if (affectRows>0){
            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
        }

    }

    /**修改*/
    public void update(String newTitle,String newContent,String newTime,String id){
        SQLiteDatabase db=helper.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("title",newTitle);
        values.put("content",newContent);
        values.put("time",newTime);
        db.update("note",values,"_id=?",new String[]{id});


    }
    /**查询*/
    public List<MyNote> query(){
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor_title=db.query("note",null,null,null,null,null,null);
        List<MyNote> titleList=new ArrayList<>();

        while(cursor_title.moveToNext()){
            MyNote note=new MyNote();

            String id=cursor_title.getString(0);
            String title=cursor_title.getString(1);
            String content=cursor_title.getString(2);
            String time=cursor_title.getString(3);

            note.setTitle(title);
            note.set_id(id);
            note.setContent(content);
            note.setTime(time);

            titleList.add(note);
        }

        db.close();
        cursor_title.close();
        return  titleList;
    }

}
