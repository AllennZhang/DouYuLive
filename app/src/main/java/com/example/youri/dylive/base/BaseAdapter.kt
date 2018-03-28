package com.example.youri.dylive.base

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * Created by youri on 2017/12/26.
 */
abstract class BaseAdapter< VH : RecyclerView.ViewHolder >: RecyclerView.Adapter<VH>() {
    protected var customFooterView: View? = null
    protected var customHeaderView: View? = null
    private var isFooterEnable = true
    private var removeFooter = false


    override fun onBindViewHolder(holder: VH, position: Int) {
        onBindViewHolder(holder,position,true)
    }

    override fun getItemCount(): Int {
        var count = getAdapterItemCount()
        count += getStart()
        if (customFooterView != null && !removeFooter) {
            count++
        }
        return count
    }


    override fun getItemViewType(position: Int): Int {
        if (isHeader(position)) {
            return VIEW_TYPES.HEADER
        } else if (isFooter(position)) {
            return VIEW_TYPES.FOOTER
        } else {
           val pos = if (getStart() > 0) position - 1 else position
            return getAdapterItemViewType(pos)
        }
    }

    fun getAdapterItemViewType(position: Int): Int {
        return VIEW_TYPES.NORMAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        if (viewType == VIEW_TYPES.FOOTER) {
            removeViewFromParent(customFooterView)
            return getViewHolder(customFooterView)
        } else if (viewType == VIEW_TYPES.HEADER) {
            removeViewFromParent(customHeaderView)
            return getViewHolder(customHeaderView)
        }
        return onCreateViewHolder(parent, viewType, true)
    }


    fun setHeaderView(headerView: View, recyclerView: RecyclerView?) {
        if (recyclerView == null) return
        removeViewFromParent(headerView)
        customHeaderView = headerView
        notifyDataSetChanged()
    }

    fun setHeaderView(@LayoutRes id: Int, recyclerView: RecyclerView?): View? {
        if (recyclerView == null) return null
        val context = recyclerView.context
        val resourceTypeName = context.resources.getResourceTypeName(id)
        if (!resourceTypeName.contains("layout")) {
            throw RuntimeException(context.resources.getResourceName(id) + " is a illegal layoutid , please check your layout id first !")
        }
        val headerview = FrameLayout(recyclerView.context)
        customHeaderView = LayoutInflater.from(context).inflate(id, headerview, false)
        notifyDataSetChanged()
        return customHeaderView
    }


    fun setFooterView(footerView: View,recyclerView: RecyclerView?) {
        if (recyclerView == null) return
        removeViewFromParent(footerView)
        customFooterView = footerView
        notifyDataSetChanged()
    }

    fun isFooterShowing(): Boolean {
        return !removeFooter
    }

    fun removeFooterView() {
        if (!removeFooter) {
            notifyItemRemoved(itemCount - 1)
            removeFooter = true
        }
    }


    fun isFooter(position: Int): Boolean {
        val start = getStart()
        return customFooterView != null && position >= getAdapterItemCount() + start
    }

    fun isHeader(position: Int): Boolean {
        return getStart() > 0 && position == 0
    }


    fun getStart(): Int {
        return if (customHeaderView == null) 0 else 1
    }


    fun removeViewFromParent(view: View?){
        if (view == null) return
        if (view.parent != null){
            val viewGroup = view.parent as ViewGroup
            viewGroup.removeView(view)
        }
    }


    abstract fun getAdapterItemCount(): Int
    abstract fun getViewHolder(view: View?): VH
    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int, isItem: Boolean): VH
    abstract fun onBindViewHolder(holder: VH, position: Int, isItem: Boolean)

    protected object VIEW_TYPES {
        val FOOTER = -1
        val HEADER = -3
        val NORMAL = -4
    }
}