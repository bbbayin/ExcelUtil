package com.bunny.weishang;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bunny.weishang.ui.UpdateActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyHolder> {
    private List<Info> mList;
    private Context context;


    public OrderAdapter(List<Info> mList) {
        this.mList = mList;
    }

    public void refresh(List<Info> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final Info order = mList.get(position);
        holder.tv_send_man.setText("发货人："+order.getSender());
        holder.tv_size.setText("尺寸："+order.getSize());
        holder.tv_kuaidi.setText("快递："+order.getExpress());
        holder.tv_address.setText("地址："+order.getAddress());
        holder.tv_number.setText("订单编号："+order.getNumber());
        holder.tv_date.setText("日期："+order.getDate());
        holder.tv_phone.setText("电话："+order.getTel());
        holder.tv_ps.setText("备注："+order.getRemark());
        if (order.getImage()!=null){

            Bitmap bitmap = BitmapFactory.decodeByteArray(order.getImage(), 0, order.getImage().length);

            holder.image.setImageBitmap(bitmap);
        }else
            holder.image.setImageResource(R.mipmap.insert_mipmap);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改信息
                UpdateActivity.launchMe(context,order,true);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null) return mList.size();
        return 0;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView tv_send_man;
        private final TextView tv_kuaidi;
        private final TextView tv_date;
        private final TextView tv_number;
        private final TextView tv_phone;
        private final TextView tv_address;
        private final TextView tv_ps;
        private final TextView tv_size;

        public MyHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_pic);
            tv_send_man = (TextView) itemView.findViewById(R.id.tv_send_man);
            tv_kuaidi = (TextView) itemView.findViewById(R.id.tv_kuaidi);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            tv_phone = (TextView) itemView.findViewById(R.id.tv_phone);
            tv_address = (TextView) itemView.findViewById(R.id.tv_address);
            tv_ps = (TextView) itemView.findViewById(R.id.tv_ps);
            tv_size = (TextView) itemView.findViewById(R.id.tv_size);
        }
    }
}
