package com.example.youri.dylive.contract

import com.example.youri.dylive.base.BasePresenter
import com.example.youri.dylive.base.BaseView
import com.example.youri.dylive.modle.LiveInfo

/**
 * Created by youri on 2018/1/11.
 */
interface LiveContract{

    interface View: BaseView<Presenter>{
        fun getLiveList(data: List<LiveInfo>)
//        fun loadMore(data: List<LiveInfo>)
        fun enterRoom(url: String?)
    }

    interface Presenter: BasePresenter{
        fun getLiveList( key: String?)
//        fun loadMore(key: String,  page: Int,  pageSize: Int)

        fun getRoomInfo(uid: String)
    }
}