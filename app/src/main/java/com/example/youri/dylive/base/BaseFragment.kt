package com.example.youri.dylive.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by youri on 2017/12/17.
 */
abstract class BaseFragment :Fragment(){
    protected lateinit var  rootView: View
    protected var  mActivity:Activity? = null
    private var isViewPrepared: Boolean = false //视图是否已经渲染完毕
    private var hasFetchData: Boolean = false  //是否加载过数据
    private var isUIVisable: Boolean = false //是否可见
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as Activity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView  =inflater!!.inflate(getLayoutResId(),container,false)
        Log.e("Fragment",this.javaClass.simpleName+" onCreateView")
        return rootView
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        isViewPrepared = true
        Log.e("Fragment","isViewPrepared:"+isViewPrepared)
        initView()
        initData()
        initListener()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isUIVisable = isVisibleToUser
        lazyLoad()
        Log.e("Fragment","fragmentName:"+this.javaClass.simpleName+" isUIVisable: "+isUIVisable)
    }

    private fun lazyLoad() {
        Log.e("Fragment","lazyload---isViewPrepared:"+isViewPrepared+" isUIVisable: "+isUIVisable+" hasFetchData:"+hasFetchData)
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewPrepared && isUIVisable && !hasFetchData){
            lazyLoadData()
            Log.e("Fragment","fragmentName:"+this.javaClass.simpleName+" lazyLoad: ")
            //数据加载完毕,恢复标记,防止重复加载
//            isUIVisable = false
            hasFetchData = true

        }
    }


    abstract fun lazyLoadData()


    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun initListener()
    abstract fun initData()

    fun getViewRoot(): View = rootView



}