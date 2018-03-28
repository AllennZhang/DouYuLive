package com.example.youri.dylive.base

/**
 * Created by youri on 2017/12/17.
 */
interface BaseView<T>{
    fun setPresenter(presenter: T)
    fun showError(msg: String)
}