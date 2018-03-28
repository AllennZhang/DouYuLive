package com.example.youri.dylive.view.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.TableLayout
import com.bigkoo.svprogresshud.SVProgressHUD
import com.example.youri.dylive.R
import com.example.youri.dylive.api.Constants
import com.example.youri.dylive.base.BaseFragment
import com.example.youri.dylive.contract.LiveContract
import com.example.youri.dylive.modle.LiveInfo
import com.example.youri.dylive.presenter.LivePresenter

import tv.danmaku.ijk.media.player.IjkMediaPlayer
import tv.danmaku.ijkplayer.media.widget.media.IjkVideoView


/**
 * Created by youri on 2018/1/14.
 */
class VideoFragment: BaseFragment(),LiveContract.View{

    var uid: String? = null
    lateinit var mPresenter: LiveContract.Presenter
    lateinit var svProgressHUD: SVProgressHUD
    lateinit var  videoView: IjkVideoView
    companion object {
        fun newInstance(args: Bundle?):VideoFragment{
            val fragment: VideoFragment = VideoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_video
    }

    override fun initView() {
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        svProgressHUD = SVProgressHUD(context)
        videoView=rootView.findViewById(R.id.videoView)
        var hubView = rootView.findViewById<TableLayout>(R.id.hudView)
        videoView.setHudView(null)
//        svProgressHUD.showErrorWithStatus("正在获取房间信息...")

    }

    override fun onResume() {
        super.onResume()
        svProgressHUD.showErrorWithStatus("正在获取房间信息...")
        videoView.postDelayed({
            svProgressHUD.dismiss()
        },1000)
    }

    override fun onStop() {
        super.onStop()
        if(!videoView.isBackgroundPlayEnabled()){
            videoView.stopPlayback();

            videoView.release(true);

            videoView.stopBackgroundPlay();
        }else{
            videoView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }

    override fun initListener() {


    }

    override fun initData() {
        var presenter = LivePresenter(this)
        if (arguments != null){
            uid = arguments.getString(Constants.KEY_UID)
        }
        if (!TextUtils.isEmpty(uid)){
            mPresenter.getRoomInfo(uid!!)
        }
    }

    override fun setPresenter(presenter: LiveContract.Presenter) {
        mPresenter= presenter
    }

    override fun showError(msg: String) {
        svProgressHUD.showErrorWithStatus(msg)

    }

    override fun getLiveList(data: List<LiveInfo>) {

    }

    override fun enterRoom(url: String?) {
//        svProgressHUD.dismiss()
        if(url != null) {
            try {
                //设置播放路径
                //开始播放
                videoView.setVideoPath(url)
                videoView.start()
            }catch (e: Exception){
                Log.e("Video","error:" +e.toString())
            }
        }
    }

    override fun lazyLoadData() {

    }
}