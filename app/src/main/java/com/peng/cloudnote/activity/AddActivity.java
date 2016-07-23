package com.peng.cloudnote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.peng.cloudnote.R;

/**
 * Created by Peng on 2016/7/23.
 */
public class AddActivity extends Activity implements View.OnClickListener {

    private ImageView iv_back;
    private ImageView iv_save;
    private EditText et_title;
    private EditText et_content;

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
                break;
        }
    }
}
