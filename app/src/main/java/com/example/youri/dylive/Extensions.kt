@file:JvmName("Extensions")

package com.example.youri.dylive

import android.content.Context
import com.example.youri.dylive.base.AppApplication
import com.example.youri.dylive.utils.MD5Util
import java.util.*

/**
 * Created by youri on 2017/12/25.
 * 包级函数，顶层函数 避免冗余的工具类
 * 编译后生成的java类 名为 ExtensionsKt,如果在java中调用则可以 ExtensionsKt.getParamsMap()
 * 如果指定类名 在package前加 @file:JvmName("Extensions") 则以 Extensions.getParamsMap()调用
 *
 */



/**
 * 公共的参数集合
 *
 * @return
 */
fun getParamsMap(): MutableMap<String, String> {
    val paramsmap = LinkedHashMap<String, String>()
    paramsmap.put("client_sys", "android")
    paramsmap.put("aid", "android1")
    paramsmap.put("time", "${System.currentTimeMillis()}" )
    return paramsmap
}



fun appContext(): Context{
    return AppApplication.instance.applicationContext
}

object ParamsMake{

    lateinit var mapparam: MutableMap<String, String>

    /**
     * 默认参数
     *
     * @return
     */
    fun getDefaultParams(): MutableMap<String, String> {
        return getParamsMap()
    }

    /**
     * 首页 列表详情
     * @param identification
     * @return
     */
    fun getHomeCate(identification: String): Map<String, String> {
        mapparam = getDefaultParams()
        mapparam.put("identification", identification)
        return mapparam
    }

    fun getHomeCarousel(): Map<String, String> {
        mapparam = getDefaultParams()
        mapparam.put("version", "2.421")
        return mapparam
    }

    /**
     * 首页--推荐--颜值
     * 默认  :4条数据
     * @return
     */
    fun getHomeFaceScoreColumn(offset: Int, limit: Int): Map<String, String> {
        mapparam = getDefaultParams()
        mapparam.put("offset", offset.toString() + "")
        mapparam.put("limit", limit.toString() + "")
        return mapparam
    }

    /**
     * 其他栏目二级分类
     * @param ColumnName
     * @return
     */
    fun getLiveOtherTwoColumn(ColumnName: String): Map<String, String> {
        mapparam = getDefaultParams()
        mapparam.put("shortName", ColumnName)
        return mapparam
    }

    /**
     * 栏目 更多 二级分类列表
     * @param cate_id
     * @return
     */
    fun getColumnMoreCate(cate_id: String): Map<String, String> {
        mapparam = getDefaultParams()
        mapparam.put("tag_id", cate_id)
        return mapparam
    }

    /**
     * 房间信息
     * @param room_id
     * @return
     */
    fun getLiveVideoInfo(room_id: String): Map<String, String> {
        mapparam = HashMap()
        val time = System.currentTimeMillis() / (1000 * 60)
        var did = UUID.randomUUID().toString().toUpperCase()
        did = did.replace("-", "")
        val str = room_id + did + "A12Svb&%1UUmf@hC" + time
        val sign = MD5Util.getToMd5Low32(str)
        mapparam.put("client_sys", "android")
        mapparam.put("aid", "android1")
        mapparam.put("cdn", "ws")
        mapparam.put("rate", "0")
        mapparam.put("tt", time.toString())
        mapparam.put("did", did)
        mapparam.put("sign", sign)
        return mapparam
    }

    /**
     * 首页--推荐--颜值
     * 默认  :4条数据
     * @return
     */
    fun getHomeColumnMoreOtherList(cate_id: String, offset: Int, limit: Int): Map<String, String> {
        mapparam = getDefaultParams()
        mapparam.put("cate_id", cate_id)
        mapparam.put("offset", offset.toString() + "")
        mapparam.put("limit", limit.toString() + "")
        return mapparam
    }

    /**
     * *******************************视频模块****************************************
     */

    fun getVideoOtherTwoColumn(ColumnName: String): Map<String, String> {
        mapparam = getDefaultParams()
        mapparam.put("cid1", ColumnName)
        return mapparam
    }

//    cid1=1&cid2=5&offset=0&limit=20&action=hot

    fun getVideoOtherList(cid1: String, cid2: String, offset: Int, limit: Int, action: String): Map<String, String> {
        mapparam = getDefaultParams()
        mapparam.put("cid1", cid1)
        mapparam.put("cid2", cid2)
        mapparam.put("offset", offset.toString() + "")
        mapparam.put("limit", limit.toString() + "")
        mapparam.put("action", action)
        return mapparam
    }

    fun getPersonInfo(userName: String, password: String): Map<String, String> {
        mapparam = getDefaultParams()
        mapparam.put("username", userName)
        mapparam.put("password", password)
        return mapparam
    }
}
