package com.example.youri.dylive.contract

import com.example.youri.dylive.base.BasePresenter
import com.example.youri.dylive.base.BaseView
import com.example.youri.dylive.modle.*

/**
 * Created by youri on 2017/12/27.
 */

interface HomeCateContract{
    interface View: BaseView<Presenter>{
        //轮播图
         fun getViewCarousel(mHomeCarousel: List<HomeCarousel>)

        //最热栏目
         fun getViewHotColumn(mHomeHotColumn: List<HomeHotColumn>)

        //颜值栏目
         fun getViewFaceScoreColumn(homeFaceScoreColumns: List<HomeFaceScoreColumn>)

        //热门栏目
         fun getViewHotCate(homeRecommendHotCates: MutableList<HomeRecommendHotCate>)

    }

    interface Presenter: BasePresenter{
        //轮播
         fun getPresenterCarousel()

        //最热栏目
         fun getPresenterHotColumn()
        //颜值
         fun getPresenterFaceScoreColumn(offset: Int, limit: Int)

         fun getPresenterHotCate()



    }
}
