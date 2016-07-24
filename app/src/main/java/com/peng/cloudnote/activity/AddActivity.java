package com.peng.cloudnote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.peng.cloudnote.R;

import database.NoteDao;
import utils.AboutTime;

/**
 * Created by Peng on 2016/7/23.
 * 增加记事的activity
 */
public class AddActivity extends Activity implements View.OnClickListener {

    private ImageView iv_back;
    private ImageView iv_save;
    private EditText et_title;
    private EditText et_content;
    private String modle;
    private String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();
        initData();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_save = (ImageView) findViewById(R.id.iv_save);
        et_title = (EditText) findViewById(R.id.et_title);
        et_content = (EditText) findViewById(R.id.et_content);

        /**初始化界面的时候,先判断模式是不是UPDATE ,如果是,就先将原先的数据展示出来*/
        Intent intent_model=getIntent();
        modle = intent_model.getStringExtra("modle");
        _id = intent_model.getStringExtra("_id");
//        System.out.println("-----------------------------模式"+modle);
        String title=intent_model.getStringExtra("title");
//        System.out.println("-----------------------------标题"+title);
        String content=intent_model.getStringExtra("content");
//        System.out.println("-----------------------------内容"+content);
        /**如果模式是更新*/
        if ("UPDATE".equals(modle)){
            et_title.setText(title);
            et_content.setText(content);
        }
    }

    private void initData() {
        iv_back.setOnClickListener(this);
        iv_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                //返回主界面
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_save:
                //保存数据到数据库
                NoteDao dao=new NoteDao(this);
                String newTitle=et_title.getText().toString().trim();
                String newContent=et_content.getText().toString();
                String time= AboutTime.getTime();
                if(!"UPDATE".equals(modle)) {       //增加新记事
                    dao.add(newTitle, newContent, time);
                }else{          //更新已有记事
                    dao.update(newTitle,newContent,time,_id);
                }

                //返回主界面
                Intent intent_save = new Intent(this, MainActivity.class);
                startActivity(intent_save);
                finish();
                break;
        }
    }
}
