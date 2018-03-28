package com.example.youri.dylive.utils

import android.content.Context
import android.net.Uri
import com.example.youri.dylive.BuildConfig
import com.example.youri.dylive.api.LiveApi
import com.example.youri.dylive.api.NetWorkApi
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.request.ImageRequestBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by youri on 2017/12/18.
 */
/**
 * retrofit 单例  使用 object关键字标识 也表匿名对象
 */
object RetrofitHelper{

    fun <T>creatService(clzz: Class<T>): T{
        val okhttpClient = OkHttpClient.Builder()
                                       .addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
                                       .readTimeout(15, TimeUnit.SECONDS)
                                       .connectTimeout(15, TimeUnit.SECONDS)
//                                       .addNetworkInterceptor( StethoInterceptor())
                                       .build()
        val retrofit = Retrofit.Builder()
                               .baseUrl(NetWorkApi.baseUrl)
                               .addConverterFactory(GsonConverterFactory.create())
                               .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                               .client(okhttpClient)
                               .build()
      return retrofit.create(clzz);
    }

    fun  getLiveApi(): LiveApi{
        val okhttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

        return  Retrofit.Builder()
                .baseUrl(LiveApi.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okhttpClient)
                .build()
                .create(LiveApi::class.java)
    }
}


object NetworkScheduler{
    fun <T> compose(): Observable.Transformer<T, T> {
        return Observable.Transformer { observale -> observale.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
    }
}

object FrescoUtil{
    private var isInit = false
    fun showThumb(draweeView: SimpleDraweeView?, url: String?, resizeWidthDp: Int, resizeHeightDp: Int) {
        if (url == null || "" == url)
            return
        if (draweeView == null)
            return
        initialize(draweeView.context)
        val request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setResizeOptions(ResizeOptions(resizeWidthDp, resizeHeightDp))
                .build()
        val controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView.controller)
                .setControllerListener(BaseControllerListener<Any>())
                .build()
        draweeView.controller = controller
    }

    fun initialize(context: Context) {
        if (isInit)
            return
        val config = ImagePipelineConfig.newBuilder(context)
                .setDownsampleEnabled(true)
                .build()
        Fresco.initialize(context, config)
        isInit = true
    }
}