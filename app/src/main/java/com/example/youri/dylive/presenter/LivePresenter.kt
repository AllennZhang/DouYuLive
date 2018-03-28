package com.example.youri.dylive.presenter

import com.example.youri.dylive.contract.LiveContract
import com.example.youri.dylive.modle.LiveListResult
import com.example.youri.dylive.modle.Room
import com.example.youri.dylive.utils.NetworkScheduler
import com.example.youri.dylive.utils.RetrofitHelper
import rx.Subscriber

/**
 * Created by youri on 2018/1/11.
 */
class LivePresenter(view: LiveContract.View): LiveContract.Presenter{

    var mView: LiveContract.View
    init {
       mView =  view
       view.setPresenter(this)
    }
    override fun start() {

    }

    override fun destory() {
    }

    override fun getLiveList(key: String?) {
      if (key == null){
          getLives()
      }else{
          getLiveBySlug(key)
      }
    }

    fun getLives(){
        RetrofitHelper.getLiveApi()
                .getLiveListResult()
                .compose(NetworkScheduler.compose())
                .subscribe(object :Subscriber<LiveListResult>(){
                    override fun onCompleted() {

                    }

                    override fun onNext(resp: LiveListResult) {
                       if (resp.data != null) mView.getLiveList(resp.data!!)
                    }

                    override fun onError(e: Throwable) {
                       if (e.message != null) mView.showError(e.message!!)
                    }

                })
    }

    fun getLiveBySlug(slug: String){
        RetrofitHelper.getLiveApi()
        .getLiveListResultByCategories(slug)
                .compose(NetworkScheduler.compose())
                .subscribe(object :Subscriber<LiveListResult>(){
                    override fun onCompleted() {

                    }

                    override fun onNext(resp: LiveListResult) {
                        if (resp.data != null) mView.getLiveList(resp.data!!)
                    }

                    override fun onError(e: Throwable) {
                        if (e.message != null) mView.showError(e.message!!)
                    }

                })
    }

    override fun getRoomInfo(uid: String) {
        RetrofitHelper.getLiveApi()
                .enterRoom(uid)
                .compose(NetworkScheduler.compose())
                .subscribe(object : Subscriber<Room>(){
                    override fun onCompleted() {

                    }

                    override fun onNext(data: Room?) {
                        if (data != null){
                            var url: String?
                            val roomLine = data.live.ws
                            if (roomLine.flv != null){
                                url = roomLine.flv.getValue(false).src
                            }else{
                                url = roomLine.hls?.getValue(false)?.src
                            }
                            mView.enterRoom(url)
                        }
                    }

                    override fun onError(e: Throwable) {
                        if (e.message != null) mView.showError(e.message!!)
                    }
                })
    }

}