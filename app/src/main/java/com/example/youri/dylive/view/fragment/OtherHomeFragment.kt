package com.example.youri.dylive.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.bigkoo.svprogresshud.SVProgressHUD
import com.example.youri.dylive.R
import com.example.youri.dylive.base.BaseFragment
import com.example.youri.dylive.contract.LiveContract
import com.example.youri.dylive.modle.LiveInfo
import com.example.youri.dylive.presenter.LivePresenter
import com.example.youri.dylive.toast
import com.example.youri.dylive.view.adapter.LiveListAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_home_otherlist.*

/**
 * Created by youri on 2017/12/25.
 */
class OtherHomeFragment: BaseFragment(),LiveContract.View{

    lateinit var mPresenter: LiveContract.Presenter
    lateinit var mAdapter: LiveListAdapter
    lateinit var svProgressHUD: SVProgressHUD
    lateinit var  key: String
    companion object {

        fun newInstance(slug: String?): OtherHomeFragment{
            val mInstance = OtherHomeFragment()
            val bundle = Bundle()
            bundle.putString("slug", slug)
            mInstance.setArguments(bundle)
            return mInstance
        }
    }
    override fun getLayoutResId(): Int {
      return R.layout.fragment_home_otherlist
    }

    override fun initView() {
        refreshLt.setRefreshHeader(ClassicsHeader(mActivity))
        refreshLt.setRefreshFooter(ClassicsFooter(mActivity))
        svProgressHUD = SVProgressHUD(context)
        otherRecycer.layoutManager = GridLayoutManager(mActivity,2)
        mAdapter=LiveListAdapter(mActivity as Context)
        otherRecycer.adapter = mAdapter

    }

    override fun initListener() {
        refreshLt.setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh(refreshlayout: RefreshLayout?) {
                refresh()
            }
        })
        refreshLt.setOnLoadmoreListener(object : OnLoadmoreListener {
            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                refreshlayout?.finishLoadmore()
            }
        })
    }

    override fun initData() {
        var presenter = LivePresenter(this)
//        presenter.getLiveList(key)
        if (arguments != null){
           key = arguments.getString("slug")
        }
        refresh()
        //==========cause crash===========
        //java.lang.NullPointerException: Attempt to read from field 'int android.view.View.mViewFlags' on a null object reference
//        svProgressHUD.showErrorWithStatus("正在拼命加载中")
    }



    override fun setPresenter(presenter: LiveContract.Presenter) {
      this.mPresenter = presenter
    }

    override fun showError(msg: String) {
        activity?.toast(msg)
    }

    override fun getLiveList(data: List<LiveInfo>) {
 //导入其他页面的view，不会报错
//        refreshLayout?.finishRefresh()
//        refreshLayout?.finishLoadmore()
        refreshLt.finishRefresh()
        refreshLt.finishLoadmore()
        mAdapter.setDatas(data)
    }

    private fun refresh() {
        mPresenter.getLiveList(key)
    }

    override fun enterRoom(url: String?) {

    }

    override fun lazyLoadData() {
//        refresh()
    }
}