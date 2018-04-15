package lyhoangvinh.com.mymvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import lyhoangvinh.com.mymvp.R;
import lyhoangvinh.com.mymvp.model.Address;

/**
 * Created by LyHoangVinh on 25/03/2018.
 */

public class TestAdapter extends BaseAdapter {

    private List<Address> list;

    public TestAdapter(List<Address> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder{
        TextView tv;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.tv = view.findViewById(R.id.tvLable);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tv.setText(list.get(i).getLabel());
        return view;
    }
}
