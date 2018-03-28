package com.example.youri.dylive.modle

import com.example.youri.dylive.appContext
import com.example.youri.dylive.base.BaseResponse
import com.example.youri.dylive.exception.ApiException
import com.example.youri.dylive.exception.ApiExceptionType
import com.google.gson.Gson
import retrofit2.adapter.rxjava.HttpException
import rx.Subscriber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by youri on 2017/12/18.
 */
abstract class HttpRespone<T> : Subscriber<BaseResponse<T>>() {


    override fun onNext(t: BaseResponse<T>) {
        if (t.data != null) {
            onSuccess(t.data!!)
        }else{
            onError(Throwable("response is null"))
        }
    }



    override fun onCompleted() {

    }

    override fun onError(e: Throwable) {
         if (e is HttpException){
             val exp: ApiException = when(e.code()){
                 ApiExceptionType.INTERNAL_SERVER_ERROR.code -> ApiExceptionType.INTERNAL_SERVER_ERROR.getApiException(appContext())
                 ApiExceptionType.BAD_GATEWAY.code -> ApiExceptionType.BAD_GATEWAY.getApiException(appContext())
                 ApiExceptionType.NOT_FOUND.code -> ApiExceptionType.NOT_FOUND.getApiException(appContext())
                 else -> otherError(e)

             }
             onFailure(exp)
         }else{
             val apiExp: ApiException = when (e) {  //发送网络问题或其它未知问题，请根据实际情况进行修改
                 is UnknownHostException -> ApiExceptionType.NETWORK_NOT_CONNECT.getApiException(appContext())
                 is ConnectException -> ApiExceptionType.NETWORK_NOT_CONNECT.getApiException(appContext())
                 is SocketTimeoutException -> ApiExceptionType.CONNECTION_TIMEOUT.getApiException(appContext())
                 else -> ApiExceptionType.UNEXPECTED_ERROR.getApiException(appContext())
             }
             onFailure(apiExp)
         }
    }

    private fun otherError(e: HttpException): ApiException {

        return Gson().fromJson(e.response().errorBody()?.charStream(),ApiException::class.java)
    }


    abstract fun onSuccess(t: T)

    abstract fun onFailure( e: ApiException)


}