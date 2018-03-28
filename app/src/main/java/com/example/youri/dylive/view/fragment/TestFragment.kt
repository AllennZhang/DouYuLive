package com.example.youri.dylive.view.fragment

import com.example.youri.dylive.R
import com.example.youri.dylive.base.BaseFragment

/**
 * Created by youri on 2018/1/11.
 */
class TestFragment: BaseFragment(){

    companion object {
        fun newInstance(): TestFragment{
            return  TestFragment()
        }
    }
    override fun getLayoutResId(): Int {
        return R.layout.fragment_home_otherlist
    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun initData() {

    }

    override fun lazyLoadData() {

    }
}