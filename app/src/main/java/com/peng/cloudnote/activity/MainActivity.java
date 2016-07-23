package com.peng.cloudnote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.peng.cloudnote.R;

import java.util.ArrayList;
import java.util.List;

import adapter.MyLvAdapter;

/**
 * Created by Peng on 2016/7/23.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView iv_grid;
    private ImageView iv_sort;
    private ImageView iv_settings;
    private ImageView iv_add;
    private ImageView iv_searh;
    private ListView lv_content;
    /**模拟假数据*/
    private List <String> list;
    private LinearLayout ll_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    /**
     * 自定义方法,完成视图初始化,获取对应控件
     */
    private void initView() {
        /**列表模式*/
        iv_grid = (ImageView) findViewById(R.id.iv_grid);
        /**排序模式*/
        iv_sort = (ImageView) findViewById(R.id.iv_sort);
        iv_settings = (ImageView) findViewById(R.id.iv_settings);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        iv_searh = (ImageView) findViewById(R.id.iv_search);
        lv_content = (ListView) findViewById(R.id.lv_note_content);
        ll_setting = (LinearLayout) findViewById(R.id.ll_setting);
    }

    public void initData(){
        /**初始化假数据的集合*/
        list=new ArrayList<>();
        for(int i=0;i<50;i++) {
            list.add(i+"无标题记事无标题记事无标题记事无标题记事无标题记事");
        }
        /**lv设置适配器*/
        MyLvAdapter myLvAdapter=new MyLvAdapter(list,this);
        lv_content.setAdapter(myLvAdapter);

        iv_settings.setOnClickListener(this);
        iv_searh.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        iv_grid.setOnClickListener(this);
        iv_sort.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add:
                System.out.println("-----------------增加");
                Intent intent =new Intent(this,AddActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_settings:
                System.out.println("-----------------设置");
                ll_setting.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_grid:
                System.out.println("-----------------列表模式");
                break;
            case R.id.iv_sort:
                System.out.println("-----------------排序");
                break;
            case R.id.iv_search:
                System.out.println("-----------------搜索");
                break;

        }
    }
}
