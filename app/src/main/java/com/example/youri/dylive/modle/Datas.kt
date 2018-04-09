package com.example.youri.dylive.modle

import java.io.Serializable

/**
 * Created by youri on 2017/12/20.
 */
data class HomeCateList( val title: String , val show_order: String, val identification: String, val is_video: Int) : Serializable

data class HomeRecommendHotCate(val push_vertical_screen: String,val icon_url: String,val tag_name: String,val push_nearby: String,val tag_id: String,var room_list: List<RoomListEntity>){}

data class HomeCarousel( val id: Int, val title: String, val pic_url: String, val tv_pic_url: String)

data class HomeHotColumn(val specific_catalog: String,
                         val vertical_src: String ,
                         val ranktype: String,
                         val nickname: String,
                         val subject: String,
                         val room_src: String,
                         val rpos: String,
                         val cate_id: String,
                         val specific_status: String,
                         val game_name: String,
                         val push_ios: String,
                         val avatar_small: String,
                         val online: Int,
                         val recomType: String,
                         val avatar_mid: String,
                         val vod_quality: String,
                         val child_id: String,
                         val room_name: String,
                         val room_id: String,
                         val isVertical: Int,
                         val show_time: String,
                         val show_status: String,
                         val jumpUrl: String)

data class HomeFaceScoreColumn(val room_id: String,
                               val room_src: String,
                               val vertical_src: String,
                               val isVertical: Int,
                               val cate_id: String,
                               val room_name: String,
                               val show_status: String,
                               val subject: String,
                               val show_time: String,
                               val owner_uid: String,
                               val specific_catalog:String,
                               val specific_status: String,
                               val vod_quality: String,
                               val nickname: String,
                               val online: Int,
                               val game_name: String,
                               val child_id: String,
                               val avatar_mid: String,
                               val avatar_small: String,
                               val jumpUrl: String,
                               val ranktype: Int,
                               val show_type: Int,
                               val anchor_city: String)

data class HomeColumnMoreTwoCate(val id: String ,val name: String)

data class HomeColumnMoreOtherList(val room_id: String,
                                   val room_src: String,
                                   val vertical_src: String,
                                   val isVertical: Int,
                                   val cate_id: Int,
                                   val room_name: String,
                                   val show_status: String,
                                   val subject: String,
                                   val show_time: String,
                                   val owner_uid: String,
                                   val specific_catalog: String,
                                   val specific_status: String,
                                   val vod_quality: String,
                                   val nickname: String,
                                   val online: Int,
                                   val url: String,
                                   val game_url: String,
                                   val game_name: String,
                                   val  child_id: Int,
                                   val avatar: String,
                                   val avatar_mid: String,
                                   val avatar_small: String,
                                   val jumpUrl: String)

data class HomeColumnMoreAllList(val room_id: String,
                                 val room_src: String,
                                 val vertical_src: String,
                                 val isVertical: Int,
                                 val cate_id: Int,
                                 val room_name: String,
                                 val show_status: String,
                                 val subject: String,
                                 val show_time: String,
                                 val owner_uid: String,
                                 val specific_catalog: String,
                                 val specific_status: String,
                                 val vod_quality: String,
                                 val nickname: String,
                                 val online: Int,
                                 val url: String,
                                 val game_url: String,
                                 val game_name: String,
                                 val child_id: Int,
                                 val avatar: String,
                                 val avatar_mid: String,
                                 val avatar_small: String,
                                 val jumpUrl: String,
                                 val fans: String,
                                 val ranktype: Int,
                                 val is_noble_rec: Int,
                                 val anchor_city: String)


class RoomListEntity : Serializable {
    val specific_catalog: String? = null
    val vertical_src: String? = null
    val ranktype: String? = null
    val nickname: String? = null
    val subject: String? = null
    val room_src: String? = null
    val cate_id: String? = null
    val specific_status: String? = null
    val game_name: String? = null
    val avatar_small: String? = null
    val online: Int = 0
    val avatar_mid: String? = null
    val vod_quality: String? = null
    val room_name: String? = null
    val child_id: String? = null
    val room_id: String? = null
    val show_time: String? = null
    val isVertical: Int = 0
    val show_status: String? = null
    val jumpUrl: String? = null
}

abstract class Foo{
    fun Zoo(){}
}

object Boo{
    fun boo(){}
}

data class RoomInfo( var roomId: Int = 0,
                     var roomSrc: String,
                     var roomName: String? = null,
                     var nickname: String? = null,
                     var online: Int = 0)

data class LiveCategory( var id: Long = 0,
                         var name: String,
                         var is_default: Int = 0,
                         var sort: Int = 0,
                         var icon_gray: String? = null,
                         var icon_red: String? = null,
                         var slug: String? = null,
                         var type: Int = 0,
                         var screen: Int = 0){

}


data class LiveInfo(
                     var no: String?,
                     var nick: String? ,
                     var avatar: String? ,
                     var follow: Int,
                     var id: String,
                     var uid: String? ,
                     var title: String?,
                     var category_id: String? ,
                     var slug: String? ,
                     var intro: String? ,
                     var announcement: String? ,
                     var cover: String? ,
                     var play_at: String? ,
                     var last_play_at: String? ,
                     var view: String? ,
                     var status: String? ,
                     var priv: String? ,
                     var landscape: String? ,
                     var position: String? ,
                     var weight: String? ,
                     var check: String? = null,
                     var recommend_image: String? = null,
                     var videoQuality: String? = null,
                     var category_name: String? = null,
                     var screen: Int = 0,
                     var start_time: String? = null,
                     var stream: String? = null,
                     var thumb: String? = null,
                     var video: String? = null,
                     var app_shuffling_image: String? = null,
                     var categoryId: String? = null,
                     var hidden: Boolean ,
                     var play_status: Boolean ,
                     var icontext: String? ,
                     var category_slug: String? ,
                     var love_cover: String?)


//=================================================LiveList===================


data class LiveListResult( var total: Int = 0,
                           var pageCount: Int = 0,
                           var page: Int = 0,
                           var size: Int = 0,
                           var icon: String? = null,
                           var recommend: RecommendBean? = null,
                           var data: List<LiveInfo>? = null)
data class RecommendBean( var id: Long = 0,
                          var name: String? = null,
                          var icon: String? = null,
                          var data: List<DataBean>? = null)
data class DataBean( var id: Int = 0,
                     var title: String? = null,
                     var thumb: String? = null,
                     var link: String? = null,
                     var create_at: String? = null,
                     var status: Int = 0,
                     var fk: String? = null,
                     var subtitle: String? = null,
                     var content: String? = null,
                     var ext: Any? = null,
                     var slot_id: Int = 0,
                     var priority: Int = 0)



//data class RoomLine(var name: String,
//                    var def_mobile: String,
//                    var def_pc: String,
//                    var v: String,
//                    var flv: FlvBean,
//                    var hls: HlsBean)
//
//data class FlvBean(var main_mobile: Int,
//                   var main_pc: Int,
//                   var value3: StreamSrc )
//data class HlsBean()
//data class StreamSrc(var name: String,var src: String)