package com.example.youri.dylive.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.youri.dylive.modle.LiveCategory

/**
 * Created by youri on 2017/12/25.
 */
class HomePageAdapter(fm: FragmentManager, titles:List<LiveCategory>,datas: List<Fragment>): FragmentPagerAdapter(fm) {
     var mTiles: List<LiveCategory>
     var fragments: List<Fragment>
    init {
       mTiles = titles
      fragments = datas
    }
    override fun getCount(): Int {
     return if(fragments == null) 0 else fragments.size
    }

    fun setTitles(titles: List<LiveCategory>){
        mTiles = titles
    }

    fun setDatas(datas: List<Fragment>){
        fragments =datas
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTiles?.get(position).name
    }

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }
}