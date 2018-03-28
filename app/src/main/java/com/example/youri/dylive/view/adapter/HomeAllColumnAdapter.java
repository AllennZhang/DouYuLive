package com.example.youri.dylive.view.adapter;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.youri.dylive.R;
import com.example.youri.dylive.modle.RoomListEntity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 *  全部栏目
 *
 */
public class HomeAllColumnAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RoomListEntity> mRommListEntity;
    private Context context;

    public HomeAllColumnAdapter(Context context, List<RoomListEntity> mRommListEntity)
    {
          this.context=context;
          this.mRommListEntity=mRommListEntity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotColumnHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_recommend_view,parent,false));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof HotColumnHolder)
            {
                bindHotColumun((HotColumnHolder) holder,position);
            }
    }
    private void bindHotColumun(HotColumnHolder holder, int position) {
         holder.img_item_gridview.setImageURI(Uri.parse(mRommListEntity.get(position).getVertical_src()));
         holder.tv_column_item_nickname.setText(mRommListEntity.get(position).getRoom_name());
         holder.tv_nickname.setText(mRommListEntity.get(position).getNickname());
//        holder.tv_online_num.setText(CalculationUtils.getOnLine(mRommListEntity.get(position).getOnline()));
        if(mRommListEntity.get(position).getCate_id().equals("201"))
        {
            holder.rl_live_icon.setBackgroundResource(R.drawable.search_header_live_type_mobile);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
////                颜值栏目 竖屏播放
//                if(mRommListEntity.get(position).getCate_id().equals("201"))
//                {
//                    Intent intent = new Intent(context, PhoneLiveVideoActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("Room_id",mRommListEntity.get(position).getRoom_id());
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
//                }else {
//                    Intent intent = new Intent(context, PcLiveVideoActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("Room_id", mRommListEntity.get(position).getRoom_id());
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
//                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mRommListEntity.size();
    }
    public class HotColumnHolder extends BaseViewHolder
    {
//        图片
        public SimpleDraweeView img_item_gridview;
//        房间名称
        public TextView tv_column_item_nickname;
//        在线人数
        public TextView tv_online_num;
//        昵称
        public TextView tv_nickname;
//        Icon
        public RelativeLayout rl_live_icon;

        public HotColumnHolder(View view) {
            super(view);
            img_item_gridview=(SimpleDraweeView)view.findViewById(R.id.img_item_gridview);
            tv_column_item_nickname=(TextView)view.findViewById(R.id.tv_column_item_nickname);
            tv_online_num=(TextView)view.findViewById(R.id.tv_online_num);
            tv_nickname=(TextView)view.findViewById(R.id.tv_nickname);
            rl_live_icon=(RelativeLayout)view.findViewById(R.id.rl_live_icon);
        }
    }
}
