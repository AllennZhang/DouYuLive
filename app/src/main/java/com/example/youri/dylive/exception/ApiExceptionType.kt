package com.example.youri.dylive.exception

import android.content.Context
import android.support.annotation.StringRes
import com.example.youri.dylive.R

/**
 * Created by youri on 2017/12/18.
 */
enum class ApiExceptionType(val code: Int, @StringRes val msgId: Int){
    INTERNAL_SERVER_ERROR(500, R.string.service_error),
    BAD_GATEWAY(502, R.string.service_error),
    NOT_FOUND(404, R.string.not_found),
    CONNECTION_TIMEOUT(408, R.string.timeout),
    NETWORK_NOT_CONNECT(499, R.string.network_wrong),
    UNEXPECTED_ERROR(700, R.string.unexpected_error);

    private val DEFAULT_CODE = 1

    fun getApiException(context: Context): ApiException {
        return ApiException(DEFAULT_CODE, context.getString(msgId))
    }
}