package com.example.youri.dylive.base

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by youri on 2017/12/18.
 */
 class AppApplication : Application() {

    //伴生对象，Kotlin中没有java中的stataic
    companion object {
        //真正用到的时候初始化
        lateinit var  instance : AppApplication
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Fresco.initialize(applicationContext)
//        Stetho.initializeWithDefaults(this);
    }

}