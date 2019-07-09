package com.jdqm.gank.api;


import com.jdqm.gank.http.BaseService;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jdqm on 2019-07-08.
 */
public class GankService extends BaseService<GankService.GankApi> {

    public Observable<GankResponse> getAndroidData(int pageSize, int page) {
        return observe(mBaseServices.getGankDataWithType("Android", pageSize, page));
    }


    public Observable<GankResponse> getIosData(int pageSize, int page) {
        return observe(mBaseServices.getGankDataWithType("iOS", pageSize, page));
    }

    public Observable<GankResponse> getExResourceData(int pageSize, int page) {
        return observe(mBaseServices.getGankDataWithType("拓展资源", pageSize, page));
    }

    public Observable<GankResponse> getRecommandData(int pageSize, int page) {
        return observe(mBaseServices.getGankDataWithType("瞎推荐", pageSize, page));
    }

    public Observable<GankResponse> getAppData(int pageSize, int page) {
        return observe(mBaseServices.getGankDataWithType("App", pageSize, page));
    }

    public Observable<GankResponse> getWebData(int pageSize, int page) {
        return observe(mBaseServices.getGankDataWithType("前端", pageSize, page));
    }

    public Observable<GankResponse> getFuliData(int pageSize, int page) {
        return observe(mBaseServices.getGankDataWithType("福利", pageSize, page));
    }

    public Observable<GankResponse> getRelaxVideoData(int pageSize, int page) {
        return observe(mBaseServices.getGankDataWithType("休息视频", pageSize, page));
    }

    public interface GankApi {

        @GET("{dataType}/{pageSize}/{page}")
        Observable<GankResponse> getGankDataWithType(@Path("dataType") String dataType, @Path("pageSize") int pageSize, @Path("page") int page);
    }
}
