package com.example.youri.dylive.view.adapter;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.youri.dylive.R;
import com.example.youri.dylive.modle.HomeFaceScoreColumn;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;


public class HomeFaceAdapter extends RecyclerView.Adapter<HomeFaceAdapter.FaceScoreColumnHolder> {
    private List<HomeFaceScoreColumn> mHomeFaceScoreColumn;
    private Context context;

    public HomeFaceAdapter(Context context) {
        this.context = context;
        this.mHomeFaceScoreColumn = new ArrayList<HomeFaceScoreColumn>();
    }

    public void getFaceScoreColumn(List<HomeFaceScoreColumn> mHomeFaceScoreColumn) {
        this.mHomeFaceScoreColumn.clear();
        this.mHomeFaceScoreColumn.addAll(mHomeFaceScoreColumn);
        notifyDataSetChanged();
    }

    public void getFaceScoreColumnLoadMore(List<HomeFaceScoreColumn> mHomeFaceScoreColumn) {
        this.mHomeFaceScoreColumn.addAll(mHomeFaceScoreColumn);
        notifyDataSetChanged();
    }



    private void bindFaceScoreHolder(FaceScoreColumnHolder holder, int position) {
        holder.img_item_gridview.setImageURI(Uri.parse(mHomeFaceScoreColumn.get(position).getVertical_src()));
        holder.tv_column_item_nickname.setText(mHomeFaceScoreColumn.get(position).getNickname());
//        holder.tv_online_num.setText(CalculationUtils.getOnLine(mHomeFaceScoreColumn.get(position).getOnline()));
        holder.tv_facescore_city.setText(mHomeFaceScoreColumn.get(position).getAnchor_city());

        holder.img_item_gridview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, PhoneLiveVideoActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("Room_id", mHomeFaceScoreColumn.get(position).getRoom_id());
//                intent.putExtras(bundle);
//                context.startActivity(intent);
            }
        });

    }


    @Override
    public HomeFaceAdapter.FaceScoreColumnHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FaceScoreColumnHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_recommend_facescore, parent, false));
    }

    @Override
    public void onBindViewHolder(FaceScoreColumnHolder holder, int position) {
        bindFaceScoreHolder((FaceScoreColumnHolder) holder, position);
    }


    @Override
    public int getItemCount() {
      return   mHomeFaceScoreColumn.size();
    }

    public class FaceScoreColumnHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView img_item_gridview;
        public TextView tv_column_item_nickname;
        public TextView tv_online_num;
        public TextView tv_facescore_city;

        public FaceScoreColumnHolder(View view) {
            super(view);
            img_item_gridview = (SimpleDraweeView) view.findViewById(R.id.img_item_gridview);
            tv_column_item_nickname = (TextView) view.findViewById(R.id.tv_column_item_nickname);
            tv_online_num = (TextView) view.findViewById(R.id.tv_online_num);
            tv_facescore_city = (TextView) view.findViewById(R.id.tv_facescore_city);
        }
    }
}
