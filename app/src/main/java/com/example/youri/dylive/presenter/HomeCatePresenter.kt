package com.example.youri.dylive.presenter

import com.example.youri.dylive.ParamsMake
import com.example.youri.dylive.api.HomeApi
import com.example.youri.dylive.contract.HomeCateContract
import com.example.youri.dylive.exception.ApiException
import com.example.youri.dylive.modle.*
import com.example.youri.dylive.utils.NetworkScheduler
import com.example.youri.dylive.utils.RetrofitHelper

/**
 * Created by youri on 2017/12/27.
 */
class HomeCatePresenter(var view: HomeCateContract.View) : HomeCateContract.Presenter{

    var mView: HomeCateContract.View
    init {
        mView = view
        view.setPresenter(this)
    }
    override fun start() {

    }

    override fun destory() {

    }


    override fun getPresenterCarousel() {

        RetrofitHelper.creatService(HomeApi::class.java)
                       .getCarousel(ParamsMake.getHomeCarousel())
                .compose(NetworkScheduler.compose())
                .subscribe(object : HttpRespone<List<HomeCarousel>>(){
                    override fun onSuccess(t: List<HomeCarousel>) {
                         mView.getViewCarousel(t)
                    }

                    override fun onFailure(e: ApiException) {
                        mView.showError(e.message)
                    }
                })

    }

    override fun getPresenterHotColumn() {
      RetrofitHelper.creatService(HomeApi::class.java)
              .getHotColumn(ParamsMake.getDefaultParams())
              .compose(NetworkScheduler.compose())
              .subscribe(object : HttpRespone<List<HomeHotColumn>>(){
                  override fun onSuccess(t: List<HomeHotColumn>) {
                       mView.getViewHotColumn(t)
                  }

                  override fun onFailure(e: ApiException) {
                      mView.showError(e.message)
                  }
              })
    }

    override fun getPresenterFaceScoreColumn(offset: Int, limit: Int) {
       RetrofitHelper.creatService(HomeApi::class.java)
               .getFaceScoreColumn(ParamsMake.getHomeFaceScoreColumn(offset,limit))
               .compose(NetworkScheduler.compose())
               .subscribe(object : HttpRespone<List<HomeFaceScoreColumn>>(){
                   override fun onSuccess(t: List<HomeFaceScoreColumn>) {
                      mView.getViewFaceScoreColumn(t)
                   }

                   override fun onFailure(e: ApiException) {
                       mView.showError(e.message)
                   }
               })
    }

    override fun getPresenterHotCate() {
        RetrofitHelper.creatService(HomeApi::class.java)
                      .getHotCate(ParamsMake.getDefaultParams())
                      .compose(NetworkScheduler.compose())
                      .subscribe(object : HttpRespone<MutableList<HomeRecommendHotCate>>(){
                          override fun onSuccess(t: MutableList<HomeRecommendHotCate>) {
                                  mView.getViewHotCate(t)
                          }

                          override fun onFailure(e: ApiException) {
                              mView.showError(e.message)
                          }
                      })
    }
}