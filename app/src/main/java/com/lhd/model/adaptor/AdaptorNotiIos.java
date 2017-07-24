package com.lhd.model.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhd.demolock.R;
import com.lhd.model.object.ItemNotification;

import java.util.ArrayList;

/**
 * Created by D on 7/24/2017.
 */

public class AdaptorNotiIos extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<ItemNotification> itemNotifications;
    private LayoutInflater inflater;
    public AdaptorNotiIos(Context context, ArrayList<ItemNotification> itemNotifications) {
        this.context = context;
        this.itemNotifications = itemNotifications;
        inflater=LayoutInflater.from(context);
    }
    class ItemNotiHove extends RecyclerView.ViewHolder {
        ImageView imvIcon;
        TextView txvTitle;
        TextView txvDate;
        TextView txvContent;
        public ItemNotiHove(View itemView) {
            super(itemView);
            imvIcon = itemView.findViewById(R.id.noti_ios_imv_icon);
            txvTitle = itemView.findViewById(R.id.noti_ios_txt_title);
            txvDate = itemView.findViewById(R.id.noti_ios_txt_date);
            txvContent = itemView.findViewById(R.id.noti_ios_txt_content);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate( R.layout.item_view_notication, parent,false);
        return new ItemNotiHove(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemNotiHove itemNotiHove= (ItemNotiHove) holder;
        itemNotiHove.imvIcon.setImageDrawable(context.getResources().getDrawable(itemNotifications.get(position).getIcon()));
        itemNotiHove.txvContent.setText(itemNotifications.get(position).getContent());
        itemNotiHove.txvDate.setText(itemNotifications.get(position).getDate());
        itemNotiHove.txvTitle.setText(itemNotifications.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        try {
            return itemNotifications.size();
        }catch (NullPointerException e){
            return 0;
        }

    }
}