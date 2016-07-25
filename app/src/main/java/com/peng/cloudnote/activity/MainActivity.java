package com.peng.cloudnote.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.peng.cloudnote.R;

import java.util.List;

import adapter.MyGvAdapter;
import adapter.MyLvAdapter;
import bean.MyNote;
import database.NoteDao;

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
    private List<MyNote> list;
    private LinearLayout ll_setting;
    private MyLvAdapter myLvAdapter;
    private boolean isListView=true;
    private GridView gv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new NoteDao(this).query();

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
        gv_content = (GridView) findViewById(R.id.gv_note_content);
        ll_setting = (LinearLayout) findViewById(R.id.ll_setting);

        /**lv设置适配器*/
        myLvAdapter = new MyLvAdapter(list, this);
        lv_content.setAdapter(myLvAdapter);
        MyGvAdapter myGvAdapter=new MyGvAdapter(list,this);
        gv_content.setAdapter(myGvAdapter);
        /**短按更新*/
        lv_content.setOnItemClickListener(clickListener);
        gv_content.setOnItemClickListener(clickListener);

        /**长按更新或删除*/
        lv_content.setOnItemLongClickListener(longClickListener);
    }

    public void initData() {

        iv_settings.setOnClickListener(this);
        iv_searh.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        iv_grid.setOnClickListener(this);
        iv_sort.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
//                System.out.println("-----------------增加");
                Intent intent = new Intent(this, AddActivity.class);
                intent.putExtra("mode", "ADD");
                startActivity(intent);
                finish();
                break;
            case R.id.iv_settings:
//                System.out.println("-----------------设置");
                ll_setting.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_grid:
//                System.out.println("-----------------列表模式");
                if (isListView) {
                    lv_content.setVisibility(View.INVISIBLE);
                    gv_content.setVisibility(View.VISIBLE);
                    isListView=false;
                } else{
                    lv_content.setVisibility(View.VISIBLE);
                    gv_content.setVisibility(View.INVISIBLE);
                    isListView=true;
                }

                break;
            case R.id.iv_sort:
//                System.out.println("-----------------排序");
                break;
            case R.id.iv_search:
//                System.out.println("-----------------搜索");
                break;
        }
    }

    AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            goToAddActivity(position);
        }
    };


    AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            operationDialog(position);
            return true;
        }
    };

    /**
     * 点击之后弹出对话框,在对话框中有编辑和删除
     */
    private void operationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择操作");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(new CharSequence[]{"编辑", "删除"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:     //编辑
                        goToAddActivity(position);
                        break;
                    case 1:     //删除,并通知lv刷新
                        String id = list.get(position).get_id();
                        new NoteDao(MainActivity.this).delete(id);
                        /**删除的时候,集合中也要删除*/
                        list.remove(position);
                        myLvAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        builder.show();
    }

    /**
     * 自定义方法,跳转到AddActivity的界面
     */
    private void goToAddActivity(int position) {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        String title = list.get(position).getTitle();
        String content = list.get(position).getContent();
        String _id = list.get(position).get_id();
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("_id", _id);
        System.out.println("-----------------id" + _id);
        intent.putExtra("modle", "UPDATE");   //模式为更新
        startActivity(intent);
        finish();
    }
}


