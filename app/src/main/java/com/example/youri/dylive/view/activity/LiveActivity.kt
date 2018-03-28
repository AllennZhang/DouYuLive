package com.example.youri.dylive.view.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.youri.dylive.R
import com.example.youri.dylive.view.fragment.VideoFragment

/**
 * Created by youri on 2018/1/14.
 */
class LiveActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContentView(R.layout.activity_live)
        initView()
        hideSystemUI()
    }


     fun initView() {
        val videoFragment = VideoFragment.newInstance(intent.extras)
        supportFragmentManager.beginTransaction().replace(R.id.liveContainer,videoFragment).commit()
        hideSystemUI()
    }


    private fun hideSystemUI() {
        val decorView = window.decorView
         decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

}