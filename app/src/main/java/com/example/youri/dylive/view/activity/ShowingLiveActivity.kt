package com.example.youri.dylive.view.activity

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.youri.dylive.R
import com.example.youri.dylive.view.fragment.VideoFragment
import kotlinx.android.synthetic.main.activity_showing_live.*
import java.lang.ref.WeakReference

/**
 * Created by youri on 2018/1/24.
 */
class ShowingLiveActivity: AppCompatActivity(){
   lateinit var handler: HeartHandler
    val  MSG_HEART : Int = 10001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_showing_live)
        initView()
        hideSystemUI()
    }


    fun initView() {
        val videoFragment = VideoFragment.newInstance(intent.extras)
        supportFragmentManager.beginTransaction().replace(R.id.liveContainer,videoFragment).commit()
        hideSystemUI()
        handler = HeartHandler(this)
        flutteringLayout.postDelayed({
            handler.sendEmptyMessageDelayed(MSG_HEART,300)
        },3000);
    }


    private fun hideSystemUI() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    override fun onDestroy() {
        if (handler != null){
            handler.removeCallbacks(null)
        }
        super.onDestroy()
    }


    inner class HeartHandler: Handler{
       private lateinit var ref: WeakReference<Activity>
        private var taskExcuted: Int = 0
        constructor( activity: Activity){
            ref = WeakReference(activity)
        }



        override fun handleMessage(msg: Message?) {
            if (ref.get() != null){
                when(msg?.what){
                    MSG_HEART ->{
                                 if (taskExcuted <100) {
                                     flutteringLayout.addHeart()
                                     sendEmptyMessageDelayed(MSG_HEART, 100)
                                 }else{
                                     return
                                 }
                                 taskExcuted++
                                 }
                }
            }
        }
    }
}