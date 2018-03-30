package com.example.youri.dylive.contract

import com.example.youri.dylive.base.BasePresenter
import com.example.youri.dylive.base.BaseView
import com.example.youri.dylive.modle.HomeCateList
import com.example.youri.dylive.modle.LiveCategory

/**
 * Created by youri on 2017/12/21.
 */

interface HomeCateListContract{
    interface View : BaseView<Presenter>{
       fun getHomeAllList(cateLists: List<HomeCateList>? )

        //所有分类
        fun getAllCates(cates: List<LiveCategory>)

        fun showNetWorkError()
    }

    interface Presenter: BasePresenter{
        fun getHomeCateList()

        //所有分类
        fun getAllCates()
    }
}
