package com.peng.cloudnote;

import android.test.AndroidTestCase;

import java.util.List;

import bean.MyNote;
import database.NoteDao;

/**
 * Created by Peng on 2016/7/24.
 */
public class DbTest extends AndroidTestCase{

    public void testQuery(){
        NoteDao dao=new NoteDao(getContext());
        List<MyNote>list=dao.query();
        if (list.size()>0){
            System.out.println("-------------------有数据,查询成功");
        }else{
            System.out.println("--------------------失败");
        }
    }
}
