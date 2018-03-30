package com.example.youri.dylive.view.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.bgabanner.BGABanner
import com.bigkoo.svprogresshud.SVProgressHUD
import com.example.youri.dylive.R
import com.example.youri.dylive.base.BaseFragment
import com.example.youri.dylive.contract.HomeCateContract
import com.example.youri.dylive.modle.HomeCarousel
import com.example.youri.dylive.modle.HomeFaceScoreColumn
import com.example.youri.dylive.modle.HomeHotColumn
import com.example.youri.dylive.modle.HomeRecommendHotCate
import com.example.youri.dylive.presenter.HomeCatePresenter
import com.example.youri.dylive.toast
import com.example.youri.dylive.view.adapter.HomeCarouselAdapter
import com.example.youri.dylive.view.adapter.HomeListAdapter
import com.facebook.drawee.view.SimpleDraweeView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_home_recommend.*

/**
 * Created by youri on 2017/12/21.
 */
class HomeFragment: BaseFragment(),HomeCateContract.View, BGABanner.Delegate<SimpleDraweeView, String> {

    lateinit var mPresenter: HomeCateContract.Presenter
    lateinit var adapter: HomeListAdapter
    private var mRecommedBannerAdapter: HomeCarouselAdapter? = null
    private var recommed_banner: BGABanner? = null
    private lateinit var headerView: View
    private var mHomeCarousels: MutableList<HomeCarousel> = ArrayList()

    lateinit var svProgressHUD: SVProgressHUD

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home_recommend
    }

    override fun initView() {
      refreshLayout.setRefreshHeader(ClassicsHeader(mActivity))
      refreshLayout.setRefreshFooter(ClassicsFooter(mActivity))
       svProgressHUD = SVProgressHUD(context)
       adapter = HomeListAdapter(context)
       headerView = LayoutInflater.from(context).inflate(R.layout.item_home_recommend_banner,getViewRoot() as ViewGroup,false)
       recommed_banner = headerView?.findViewById(R.id.recommed_banner)
       mRecommedBannerAdapter = HomeCarouselAdapter()
       recommed_banner?.setDelegate(this)
       recommed_banner?.setAdapter(mRecommedBannerAdapter)
       adapter.setHeader(headerView)
       homeRecycer.layoutManager = LinearLayoutManager(context)
       homeRecycer.adapter = adapter

    }

    override fun initListener() {
        refreshLayout.setOnRefreshListener(object : OnRefreshListener{
            override fun onRefresh(refreshlayout: RefreshLayout?) {
//                refreshlayout?.finishRefresh()
                refresh()

            }
        })
        refreshLayout.setOnLoadmoreListener(object : OnLoadmoreListener{
            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                refreshlayout?.finishLoadmore()
            }
        })

    }

    override fun initData() {
        HomeCatePresenter(this)
        refresh()
    }

    fun refresh(){
        mPresenter.getPresenterCarousel()
        mPresenter.getPresenterHotColumn()
        mPresenter.getPresenterFaceScoreColumn(0, 4)
        mPresenter.getPresenterHotCate()
    }



    override fun setPresenter(presenter: HomeCateContract.Presenter) {
       mPresenter = presenter
    }

    override fun showError(msg: String) {
        activity?.toast(msg)
    }

   override fun getViewCarousel(data: List<HomeCarousel>) {
        refreshLayout.finishRefresh()
        mHomeCarousels.clear()
        mHomeCarousels.addAll(data)
        val bannerImgs: MutableList<String> = ArrayList()
        data?.forEach {  bannerImgs.add(it.pic_url) }
        recommed_banner?.setData(R.layout.item_image_carousel,bannerImgs,null)
        adapter.notifyDataSetChanged()
    }

    override fun getViewHotColumn(data: List<HomeHotColumn>) {
       adapter.getHomeHotColumn(data)
    }

    override fun getViewFaceScoreColumn(data: List<HomeFaceScoreColumn>) {
       adapter.getFaceScoreColmun(data)
    }

    override fun getViewHotCate(data: MutableList<HomeRecommendHotCate>) {
        //去掉颜值栏目
        data.removeAt(1)
        adapter.getAllColumn(data)
    }

    override fun onBannerItemClick(banner: BGABanner?, itemView: SimpleDraweeView?, model: String?, position: Int) {

    }

    override fun lazyLoadData() {
//        refresh()
    }

    override fun onResume() {
        super.onResume()

    }

}