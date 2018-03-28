package com.example.youri.dylive.view.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.youri.dylive.R
import com.example.youri.dylive.modle.RoomInfo

/**
 * Created by youri on 2018/1/8.
 */
class RoomInfoAdapter(context: Context,roomInfos: MutableList<RoomInfo>): RecyclerView.Adapter<RoomInfoAdapter.ViewHoler>(){
    var mContext: Context = context
    var mDatas: MutableList<RoomInfo> = roomInfos;
    private var mLisenter: RoomInfoAdapter.OnItemClickLisenter? = null

    override fun getItemCount(): Int {
      return if (mDatas == null) 0 else mDatas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHoler {
       return ViewHoler(LayoutInflater.from(mContext).inflate(R.layout.room_info_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHoler?, position: Int) {
        holder?.bindData(mDatas.get(position))
    }


    inner class ViewHoler(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        var roomSrc: ImageView
        var nickname: TextView
        var online: TextView
        init {
            roomSrc = itemView.findViewById(R.id.iv_room)
            nickname = itemView.findViewById(R.id.nickname)
            online = itemView.findViewById(R.id.online)
            itemView.setOnClickListener(this@ViewHoler)
       }

        override fun onClick(v: View?) {
            if (mLisenter != null){
                val tag = itemView.tag;
                mLisenter!!.onItemClick(tag as RoomInfo,adapterPosition)
            }
        }

        fun bindData(roomInfo: RoomInfo) {
            itemView.tag = roomInfo
            roomSrc.setImageURI(Uri.parse(roomInfo.roomSrc))
            nickname.setText(roomInfo.nickname)
            val onlineStr = roomInfo.online.toString() + "位观众"
            online.setText(onlineStr)
        }
    }

    public interface OnItemClickLisenter{
        fun onItemClick(data: RoomInfo,position: Int)
    }


    public fun setOnItemClickLisenter(listener: OnItemClickLisenter){
        this@RoomInfoAdapter.mLisenter = listener
    }
}