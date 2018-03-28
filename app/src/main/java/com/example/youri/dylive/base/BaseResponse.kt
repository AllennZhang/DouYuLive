package com.example.youri.dylive.base

/**
 * Created by youri on 2017/12/20.
 */
 class BaseResponse<T> {


    var error: Int = 0

    var data: T? = null

    override fun toString(): String {
        return "{" +
                "error:'" + error + '\'' +
                ", data:" + data +
                '}'
    }
}