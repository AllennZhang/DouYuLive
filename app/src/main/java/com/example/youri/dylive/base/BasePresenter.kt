package com.example.youri.dylive.base

/**
 * Created by youri on 2017/12/17.
 */
interface  BasePresenter{

    fun start()
    fun destory()

//    protected  var  mView: T? = null
//    protected var  mCompositeSubscription: CompositeSubscription? = null;
//
//    protected fun unSubscribe() {
//        if (mCompositeSubscription != null) {
//            mCompositeSubscription?.unsubscribe();
//        }
//    }
//    protected fun addSubscribe(subscription: Subscription) {
//        if (mCompositeSubscription == null) {
//            mCompositeSubscription =  CompositeSubscription();
//        }
//        mCompositeSubscription?.add(subscription);
//    }
//
//    override fun attachView(view: T) {
//      mView = view
//    }
//
//   override  fun detachView() {
//      mView = null
//      unSubscribe()
//    }

}