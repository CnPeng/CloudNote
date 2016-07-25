package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.peng.cloudnote.R;

import java.util.List;

import bean.MyNote;

/**
 * Created by Peng on 2016/7/23.
 * 自定义LV的适配器,继承自BaseAdapter
 */
public class MyGvAdapter extends BaseAdapter {

    List<MyNote> list;
    Context context;
    public MyGvAdapter(List<MyNote> list, Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View  view ;
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            view=View.inflate(context, R.layout.item_gv_content,null);
            holder.tv_time= (TextView)view.findViewById(R.id.tv_time);
            holder.tv_title= (TextView)view.findViewById(R.id.tv_title);
            view.setTag(holder);
        }else {
            view=convertView;
            holder= (ViewHolder) view.getTag();
        }
        MyNote note=list.get(position);
        String title=note.getTitle();
        String time=note.getTime();
        holder.tv_title.setText(title);
        holder.tv_time.setText(time);

        return view;
    }

    class ViewHolder{
        TextView tv_time;
        TextView tv_title;
    }
}
