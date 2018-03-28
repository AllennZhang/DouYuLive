package com.example.youri.dylive.api;


import com.example.youri.dylive.base.BaseResponse;
import com.example.youri.dylive.modle.HomeCarousel;
import com.example.youri.dylive.modle.HomeCateList;
import com.example.youri.dylive.modle.HomeColumnMoreAllList;
import com.example.youri.dylive.modle.HomeColumnMoreOtherList;
import com.example.youri.dylive.modle.HomeColumnMoreTwoCate;
import com.example.youri.dylive.modle.HomeFaceScoreColumn;
import com.example.youri.dylive.modle.HomeHotColumn;
import com.example.youri.dylive.modle.HomeRecommendHotCate;
import com.example.youri.dylive.modle.LiveCategory;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;



/**
 * 作者：gaoyin
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2016/12/12 下午4:12
 **/
public interface HomeApi {
    String baseUrl = "http://www.quanmin.tv/";
    String homeCatesUrl = baseUrl+"json/app/index/category/info-android.json?v=3.0.1&os=1&ver=4";
    /**
     * 获取分类列表
     * @return
     *
     * categories/list.json
     */
    @GET
    Observable<List<LiveCategory>> getAllCategories(@Url String url);

    /**
     * 首页分类列表
     *
     * @return
     */
    @GET(NetWorkApi.getHomeCateList)
    Observable<BaseResponse<List<HomeCateList>>> getHomeCateList(@QueryMap Map<String, String> params);

    /**
     * 首页 列表详情页
     *
     * @return
     */
    @GET(NetWorkApi.getHomeCate)
    Observable<BaseResponse<List<HomeRecommendHotCate>>> getHomeCate(@QueryMap Map<String, String> params);

    /**
     * 首页   推荐轮播图
     *
     * @return
     */
    @GET(NetWorkApi.getCarousel)
    Observable<BaseResponse<List<HomeCarousel>>> getCarousel(@QueryMap Map<String, String> params);

    /**
     * 推荐---最热
     *
     * @return
     */
    @GET(NetWorkApi.getHomeHotColumn)
    Observable<BaseResponse<List<HomeHotColumn>>> getHotColumn(@QueryMap Map<String, String> params);

    /**
     * 推荐---颜值
     *
     * @return
     */
    @GET(NetWorkApi.getHomeFaceScoreColumn)
    Observable<BaseResponse<List<HomeFaceScoreColumn>>> getFaceScoreColumn(@QueryMap Map<String, String> params);

    /**
     * 推荐---热门 种类
     *
     * @return
     */
    @GET(NetWorkApi.getHomeRecommendHotCate)
    Observable<BaseResponse<List<HomeRecommendHotCate>>> getHotCate(@QueryMap Map<String, String> params);


    /**
     * 栏目 更多   --二级分类列表
     *
     * @return
     */
    @GET(NetWorkApi.getHomeColumnMoreCate)
    Observable<BaseResponse<List<HomeColumnMoreTwoCate>>> getHomeColumnMoreCate(@QueryMap Map<String, String> params);

    /**
     * 栏目 更多   --其他列表
     *
     * @return
     */
    @GET(NetWorkApi.getHomeColumnMoreOtherList)
    Observable<BaseResponse<List<HomeColumnMoreOtherList>>> getHomeColumnMoreOtherList(@QueryMap Map<String, String> params);

    /**
     * 栏目 更多   --全部列表
     *
     * @return
     */
    @GET(NetWorkApi.getHomeColumnMoreAllList + "{cate_id}")
    Observable<BaseResponse<List<HomeColumnMoreAllList>>> getHomeColumnMoreAllList(@Path("cate_id") String cate_id, @QueryMap Map<String, String> params);



}
