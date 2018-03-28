package com.example.youri.dylive.view.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.youri.dylive.R
import com.example.youri.dylive.modle.HomeFaceScoreColumn
import com.example.youri.dylive.modle.HomeHotColumn
import com.example.youri.dylive.modle.HomeRecommendHotCate

/**
 * Created by youri on 2017/12/26.
 */
class HomeListAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    /**
     * 指定 type类型
     */
    //    最热栏目
    val TYPE_0 = 0xff04
    val TYPE_1 = 0xff01
    val TYPE_2 = 0xff02
    val TYPE_3 = 0xff03


    var mHomeHotColumn : MutableList<HomeHotColumn>
    var mHomeFaceScoreColumn: MutableList<HomeFaceScoreColumn>
    var mHomeRecommendHotCate: MutableList<HomeRecommendHotCate>
    var mContext : Context
    lateinit var headerView: View

    //    最热adapter
    private var mHotColumnAdapter: HomeHotColumnAdapter? = null
    //    颜值
    lateinit var mFaceScoreColumnAdapter: HomeFaceAdapter
    private var mAllColumnAdapter: HomeAllColumnAdapter? = null

    init {
        mContext = context
        mHomeHotColumn = ArrayList<HomeHotColumn>()
        mHomeFaceScoreColumn = ArrayList<HomeFaceScoreColumn>()
        mHomeRecommendHotCate = ArrayList<HomeRecommendHotCate>()
        mFaceScoreColumnAdapter = HomeFaceAdapter(context)
    }


    fun setHeader(view: View){
        this.headerView = view
    }

    /**
     * 最热栏目
     *
     * @param mHomeHotColumn
     */
    fun getHomeHotColumn(mHomeHotColumn: List<HomeHotColumn>) {
        this.mHomeHotColumn.clear()
        this.mHomeHotColumn.addAll(mHomeHotColumn)
        notifyDataSetChanged()
    }

    /**
     * 颜值栏目
     *
     * @param mHomeFaceScoreColumn
     */
    fun getFaceScoreColmun(mHomeFaceScoreColumn: List<HomeFaceScoreColumn>) {
        this.mHomeFaceScoreColumn.clear()
        this.mHomeFaceScoreColumn.addAll(mHomeFaceScoreColumn)
        mFaceScoreColumnAdapter.getFaceScoreColumn(mHomeFaceScoreColumn)
        notifyDataSetChanged()
    }

    /**
     * 全部栏目
     *
     * @param mHomeRecommendHotCate
     */
    fun getAllColumn(mHomeRecommendHotCate: List<HomeRecommendHotCate>) {
        this.mHomeRecommendHotCate.clear()
        this.mHomeRecommendHotCate.addAll(mHomeRecommendHotCate)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (position == 0 ) return
        if ( position == 1 && holder is ColumnViewHolder) {
            bindColumnHolder(holder, position)
        } else if ( position == 2 && holder is ColumnViewHolder) {
            bindFaceScoreColumnHolder(holder, position, true)
        } else {
            bindAllColumnHolder(holder as ColumnViewHolder , position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
      var viewHolder:RecyclerView.ViewHolder? = when(viewType){
                                               TYPE_0 -> BannerViewHolder(headerView)
                                               TYPE_1 -> ColumnViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_recommend,parent,false))
                                               TYPE_2 -> ColumnViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_recommend,parent,false))
                                               TYPE_3 -> ColumnViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_recommend,parent,false))
                                                else -> null
                                           }
        return viewHolder
    }

    override fun getItemCount(): Int {
        var count = mHomeRecommendHotCate.size
      return  count+1
    }

    override fun getItemViewType(position: Int): Int {
        var type:Int = when(position){
            0 -> TYPE_0
            1 -> TYPE_1
            2 -> TYPE_2
            else -> TYPE_3
        }
        return type
    }


    /**
     * 栏目
     *
     * @param holder
     * @param position
     */
    private fun bindColumnHolder(holder: ColumnViewHolder?, position: Int) {
        if (holder == null) return
        holder.img_column_icon.setImageResource(R.drawable.reco_game_txt_icon)
        holder.tv_column_name.setText("最热")
        holder.rv_column_list.setLayoutManager(FullyGridLayoutManager(holder.rv_column_list.getContext(), 2, GridLayoutManager.VERTICAL, false))
        mHotColumnAdapter = HomeHotColumnAdapter(holder.rv_column_list.getContext(), mHomeHotColumn)
        holder.rv_column_list.setAdapter(mHotColumnAdapter)

    }

    /**
     * 全部栏目
     *
     * @param holder
     * @param position
     */
    private fun bindAllColumnHolder(holder: ColumnViewHolder?, position: Int) {
        if (holder == null) return
        holder.img_column_icon.setImageResource(R.drawable.icon_column)
        holder.tv_column_name.setText(mHomeRecommendHotCate[position - 1].tag_name)
        holder.rv_column_list.setLayoutManager(FullyGridLayoutManager(holder.rv_column_list.getContext(), 2, GridLayoutManager.VERTICAL, false))
        mAllColumnAdapter = HomeAllColumnAdapter(holder.rv_column_list.getContext(), mHomeRecommendHotCate[position - 1].room_list)
        holder.rv_column_list.setAdapter(mAllColumnAdapter)
        holder.rl_column_more.setOnClickListener(View.OnClickListener {
//            val intent = Intent(context, HomeColumnMoreListActivity::class.java)
//            val bundle = Bundle()
//            bundle.putString("title", holder.tv_column_name.getText().toString())
//            bundle.putString("cate_id", mHomeRecommendHotCate[position - 2].getTag_id())
//            intent.putExtras(bundle)
//            context.startActivity(intent)
        })
    }

    /**
     * 颜值 栏目
     *
     * @param holder
     * @param position
     * @param isItem
     */
    private fun bindFaceScoreColumnHolder(holder: ColumnViewHolder?, position: Int, isItem: Boolean) {
        if (holder == null) return
        holder.img_column_icon.setImageResource(R.drawable.icon_reco_mobile)
        holder.tv_column_name.setText("颜值")
        holder.rv_column_list.setLayoutManager(FullyGridLayoutManager(holder.rv_column_list.getContext(), 2, GridLayoutManager.VERTICAL, false))
        holder.rv_column_list.setAdapter(mFaceScoreColumnAdapter)
        holder.rl_column_more.setOnClickListener(View.OnClickListener {
//            val intent = Intent(mContext, HomeRecommendFaceScoreActivity::class.java)
//            val bundle = Bundle()
//            bundle.putString("title", holder.tv_column_name.getText().toString())
//            intent.putExtras(bundle)
//            mContext.startActivity(intent)
        })
    }

    inner class BannerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }


    inner class ColumnViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //       栏目 Icon
        var img_column_icon: ImageView
        //        栏目 名称
        var tv_column_name: TextView
        //        加载更多
        var rl_column_more: RelativeLayout
        //        栏目列表
        var rv_column_list: RecyclerView

        var item_home_recommed_girdview: LinearLayout

        init {
            img_column_icon = itemView.findViewById(R.id.img_column_icon)
            tv_column_name = itemView.findViewById(R.id.tv_column_name)
            rl_column_more = itemView.findViewById(R.id.rl_column_more)
            rv_column_list = itemView.findViewById(R.id.rv_column_list)
            item_home_recommed_girdview = itemView.findViewById(R.id.item_home_recommed_girdview)
        }
    }
}