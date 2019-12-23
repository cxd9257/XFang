package com.demo.fang.net;


import com.demo.fang.bean.StockModle;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 怎么使用动态URLs
 * 它只需要你加入一个单独的注解 @Url 在你的定义的结点
 * 如
 * @Post    //不能再拼接
 * Observable<BaseModel> bookByRx( @Url String xx,@Query("page") String page, @Query("count") String count);
 */
public interface ApiServer {
//    @GET("/xx/{id}")
//    Observable<BookModle> bookByRx(@Path("id") int id);
//    /**
//     * 获取新闻列表
//     */
//    @POST("/toutiao/index")
//    Observable<NewsDetailModle> newsByRx(@Query("type") String id, @Query("key") String key);
//
    /**
     * 获取股票数据
     */
    @POST("http://web.juhe.cn:8080/finance/stock/hs")
    Observable<StockModle> getStockData(@Query("gid") String gid, @Query("key") String key);
//    /**
//     * 随机获取段子
//     */
//    @POST("https://api.apiopen.top/getJoke")
//    Observable<JokeModle> getJokeDate(@Query("page") int page, @Query("count") int count, @Query("type") String type);
//
//    /**
//     * 获取福利图片
//     */
//    @GET("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/{count}/{page}")
//    Observable<ImageModle> getImageDate(@Path("count") int count, @Path("page") int page);
//
//    /**
//     * 更新app
//     */
//    @Streaming
//    @GET
//    Observable<ResponseBody> download(@Url String url);
}
