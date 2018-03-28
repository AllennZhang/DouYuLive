package com.example.youri.dylive.base

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

/**
 * Created by youri on 2017/12/17.
 */
abstract class BaseActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowFeature()
        setContentView(getLayoutResId())
        initView()
        initData()
        initLisenter()

    }


    /**
     * 集合的函数式API,filter筛选符合条件的子集，map分别对子集的每个元素进行操作
     */
    fun addFragment(fm: FragmentManager,fragment: Fragment,containerId : Int ){
        val fragTran = supportFragmentManager.beginTransaction()
        if (!fragment .isAdded) fragTran.add(containerId,fragment)
        fm.fragments.filter { it.id == containerId }.map { fragTran.hide(it) }
        fragTran.show(fragment)
        fragTran.commit()
    }

    fun startActivity(targetActivity: Class<*>) {
        startActivity(Intent(this, targetActivity))
    }

    fun startActivity(targetActivity: Class<*>, bundle: Bundle?) {
        startActivity(Intent(this, targetActivity).putExtras(bundle))
    }

    fun startActivityForResult(cls: Class<*>, bundle: Bundle?,
                               requestCode: Int) {
        val intent = Intent()
        intent.setClass(this, cls)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }

     fun setWindowFeature(){}


    abstract fun getLayoutResId(): Int

    abstract fun initView()

    abstract fun initLisenter()

    abstract fun initData()

}