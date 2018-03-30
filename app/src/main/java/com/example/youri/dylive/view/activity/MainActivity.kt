package com.example.youri.dylive.view.activity

import android.content.Intent
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import com.bigkoo.svprogresshud.SVProgressHUD
import com.example.youri.dylive.R
import com.example.youri.dylive.base.BaseActivity
import com.example.youri.dylive.contract.HomeCateListContract
import com.example.youri.dylive.modle.HomeCateList
import com.example.youri.dylive.modle.LiveCategory
import com.example.youri.dylive.presenter.HomePresenter
import com.example.youri.dylive.toast
import com.example.youri.dylive.view.adapter.HomePageAdapter
import com.example.youri.dylive.view.fragment.HomeFragment
import com.example.youri.dylive.view.fragment.OtherHomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() ,HomeCateListContract.View{


    lateinit var svProgressHUD: SVProgressHUD
    lateinit var mTiles: MutableList<LiveCategory>
    lateinit var fragments: MutableList<Fragment>
    lateinit var pagerAdapter: HomePageAdapter

    var lastTime: Long = 0L
    var mPresenter: HomeCateListContract.Presenter? = null
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        svProgressHUD = SVProgressHUD(this)

    }

    override fun initLisenter() {

    }

    override fun initData() {
//        svProgressHUD.showErrorWithStatus("拼命加载中...")
       val presenter: HomePresenter = HomePresenter(this)
       mPresenter?.getAllCates()

        mTiles = ArrayList()
        fragments = ArrayList()

        pagerAdapter = HomePageAdapter(supportFragmentManager,mTiles,fragments)
        viewpager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewpager)

    }

    override fun setPresenter(presenter: HomeCateListContract.Presenter) {
        mPresenter = presenter
    }

    override fun getAllCates(cates: List<LiveCategory>) {
          errorHolderView?.visibility = View.GONE
          if (svProgressHUD.isShowing){
              svProgressHUD.dismiss()
          }
          mTiles.clear()
          fragments.clear()
          var homeCate: LiveCategory = LiveCategory(name = "推荐")
          mTiles.add(homeCate)
          mTiles.addAll(cates)
          var homefragment: HomeFragment = HomeFragment()
          fragments.add(homefragment)
          cates.forEach { fragments.add(OtherHomeFragment.newInstance(it.slug)) }
          pagerAdapter.setTitles(mTiles)
          pagerAdapter.setDatas(fragments)
          pagerAdapter.notifyDataSetChanged()
    }



    override fun getHomeAllList(cates: List<HomeCateList>?) {

    }

    override fun showError(msg: String) {
//        svProgressHUD.showErrorWithStatus(msg)
         toast(msg)
    }

    override fun showNetWorkError() {
        errorHolderView?.visibility = View.VISIBLE
        retryBtn.setOnClickListener{
            view ->
                    val currentTime = System.currentTimeMillis()
                   //防止重复点击
                    if (currentTime - lastTime > 1000L){
                        retryWhenError()
                        lastTime = currentTime;
                    }
        }

    }

    private fun  retryWhenError(){
        if (!svProgressHUD.isShowing) {
            svProgressHUD.showErrorWithStatus("拼命加载中...")
        }
           mPresenter?.getAllCates()
    }


    override fun onBackPressed() {
        try {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("Mainactivity","backpress:"+e.toString())
            finish()
        }

    }

}
