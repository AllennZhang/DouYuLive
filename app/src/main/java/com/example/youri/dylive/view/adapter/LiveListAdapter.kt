package com.example.youri.dylive.view.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.youri.dylive.R
import com.example.youri.dylive.api.Constants
import com.example.youri.dylive.modle.LiveInfo
import com.example.youri.dylive.view.activity.LiveActivity
import com.example.youri.dylive.view.activity.ShowingLiveActivity
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by youri on 2018/1/11.
 */
class LiveListAdapter(context: Context): RecyclerView.Adapter<LiveListAdapter.ViewHolder>(){
     var mContext = context
     var mDatas: MutableList<LiveInfo>  = ArrayList()

    fun setDatas(datas: List<LiveInfo>){
        mDatas.clear()
        mDatas.addAll(datas)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
       return  mDatas.size
    }



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
      return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_recommend_view,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
         holder?.bindData(mDatas.get(position))
    }



    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //        图片
        var img_item_gridview: SimpleDraweeView
        //        房间名称
        var tv_column_item_nickname: TextView
        //        在线人数
        var tv_online_num: TextView
        //        昵称
        var tv_nickname: TextView
        //        Icon
        var rl_live_icon: RelativeLayout
        init {
            img_item_gridview = view.findViewById<View>(R.id.img_item_gridview) as SimpleDraweeView
            tv_column_item_nickname = view.findViewById<View>(R.id.tv_column_item_nickname) as TextView
            tv_online_num = view.findViewById<View>(R.id.tv_online_num) as TextView
            tv_nickname = view.findViewById<View>(R.id.tv_nickname) as TextView
            rl_live_icon = view.findViewById<View>(R.id.rl_live_icon) as RelativeLayout
            itemView.setOnClickListener(){view ->
               startRoom(itemView.getTag() as LiveInfo)
            }
        }

         fun bindData(data: LiveInfo) {
             img_item_gridview.setImageURI(data.thumb)
             tv_column_item_nickname.setText(data.title)
             tv_online_num.setText(data.view)
             tv_nickname.setText(data.nick)
             itemView.tag = data

         }
    }

     fun startRoom(liveInfo: LiveInfo) {
        val intent = Intent()
        var fragmentKey = Constants.ROOM_FRAGMENT
        if (Constants.SHOWING.equals(liveInfo.category_slug)) {
            fragmentKey = Constants.FULL_ROOM_FRAGMENT
            intent.setClass(mContext,ShowingLiveActivity::class.java)
        }else{
            intent.setClass(mContext,LiveActivity::class.java)
        }
        intent.putExtra(Constants.KEY_FRAGMENT,fragmentKey)
        intent.putExtra(Constants.KEY_UID, liveInfo.uid)
        intent.putExtra(Constants.KEY_COVER, liveInfo.thumb)
        mContext.startActivity(intent)
    }

}