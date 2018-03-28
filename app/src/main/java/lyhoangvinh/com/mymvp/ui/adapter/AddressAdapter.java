package lyhoangvinh.com.mymvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import lyhoangvinh.com.mymvp.R;
import lyhoangvinh.com.mymvp.model.object.Address;

/**
 * Created by LyHoangVinh on 22/03/2018.
 */

public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Address> list;

    private static AddressAdapter adapter;

//    private AdapterCallBack callBack;

    public static synchronized AddressAdapter getInstance(List<Address> list){
        if (adapter == null){
            adapter = new AddressAdapter(list);
        }
        return adapter;
    }

    public AddressAdapter(List<Address> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customs_view, parent, false);
        return new ListItem(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItem listItem = (ListItem) holder;
        listItem.mealTV.setText(list.get(position).getLabel());
        listItem.infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });
        listItem.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void removeItem(int position){ //
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ListItem extends RecyclerView.ViewHolder {

        protected TextView mealTV;
        protected ImageButton infoButton;
        protected ImageButton editButton;

        public ListItem(View itemView) {
            super(itemView);
            mealTV = itemView.findViewById(R.id.meal_tv);
            infoButton = itemView.findViewById(R.id.info_button);
            editButton = itemView.findViewById(R.id.edit_button);
        }
    }
}
