package com.example.youri.dylive.presenter

import com.example.youri.dylive.api.HomeApi
import com.example.youri.dylive.contract.HomeCateListContract
import com.example.youri.dylive.exception.ApiException
import com.example.youri.dylive.getParamsMap
import com.example.youri.dylive.modle.HomeCateList
import com.example.youri.dylive.modle.HttpRespone
import com.example.youri.dylive.modle.LiveCategory
import com.example.youri.dylive.utils.NetworkScheduler
import com.example.youri.dylive.utils.RetrofitHelper
import rx.Subscriber

/**
 * Created by youri on 2017/12/25.
 * kotlin 中抽象类 想实例化子类，不能直接使用new 而是采用 匿名对象 object关键字
 */
class HomePresenter(var view: HomeCateListContract.View): HomeCateListContract.Presenter{
    var mView: HomeCateListContract.View
    init {
        mView = view
        view.setPresenter(this)
    }


    override fun start() {

    }

    override fun destory() {

    }

    override fun getAllCates() {
        RetrofitHelper.creatService(HomeApi::class.java)
                .getAllCategories(HomeApi.homeCatesUrl)
                .compose(NetworkScheduler.compose())
                .subscribe(object: Subscriber<List<LiveCategory>>(){
                    override fun onCompleted() {

                    }
                    override fun onNext(data: List<LiveCategory>?) {
                        if (data != null) {
                            mView.getAllCates(data)
                        }else{
                            mView.showNetWorkError()
                        }
                    }

                    override fun onError(e: Throwable?) {
                        if (e?.message !=null) mView.showError(e.message!!)
                        mView.showNetWorkError()

                    }
                })
    }

    override fun getHomeCateList() {

        RetrofitHelper.creatService(HomeApi::class.java)
                .getHomeCateList(getParamsMap())
                .compose(NetworkScheduler.compose())
                .subscribe(object : HttpRespone<List<HomeCateList>>(){
                    override fun onSuccess(t: List<HomeCateList>) {
                        mView.getHomeAllList(t)
                    }

                    override fun onFailure(e: ApiException) {
                        mView.showError(e.message)
                    }
                })



    }
}