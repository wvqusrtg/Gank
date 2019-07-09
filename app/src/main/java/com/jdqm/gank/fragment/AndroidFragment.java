package com.jdqm.gank.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jdqm.gank.R;
import com.jdqm.gank.WebViewActivity;
import com.jdqm.gank.api.GankEntry;
import com.jdqm.gank.api.GankResponse;
import com.jdqm.gank.api.GankService;
import com.jdqm.gank.api.MyObserver;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Jdqm on 2019-07-08.
 */
public class AndroidFragment extends BaseFragment {

    private SlimAdapter mSlimAdapter;
    private List<GankEntry> mData = new ArrayList<>();

    public static AndroidFragment newInstance() {

        Bundle args = new Bundle();

        AndroidFragment fragment = new AndroidFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(LayoutInflater inflater, View view) {
        super.initView(inflater, view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        mSlimAdapter = SlimAdapter.create()
                .register(R.layout.item_android, new SlimInjector<GankEntry>() {
                    @Override
                    public void onInject(GankEntry data, IViewInjector injector) {
                        injector.text(R.id.title_tv, data.getDesc());
                        injector.text(R.id.source_tv, data.getWho());

                        try {
                            Date date = mSourceDateFormat.parse(data.getPublishedAt());
                            injector.text(R.id.time_tv, mDestFormat.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                            injector.text(R.id.time_tv, "");
                        }


                        if (data.getImages() == null || data.getImages().isEmpty()) {
                            injector.gone(R.id.poster_iv);
                        } else {
                            ImageView posterIv = (ImageView) injector.findViewById(R.id.poster_iv);
                            posterIv.setVisibility(View.VISIBLE);
                            Glide.with(inflater.getContext())
                                    .load(data.getImages().get(0))
                                    .into(posterIv);
                        }

                        injector.clicked(R.id.item_view, v -> WebViewActivity.start(getActivity(), data.getUrl(), data.getDesc()));
                    }
                }).attachTo(mRecyclerView);
        mSlimAdapter.updateData(mData);

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mCurrentPage++;
                loadData(true);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mCurrentPage = 1;
                loadData(false);
            }
        });

        loadData(false);
    }

    private void loadData(boolean isLoadMore) {
        new GankService().getAndroidData(PAGE_SIZE, mCurrentPage)
                .compose(this.bindToLifecycle())
                .subscribe(new MyObserver<GankResponse>() {
                    @Override
                    public void onNext(GankResponse response) {
                        if (mCurrentPage == 1) {
                            mData.clear();
                        }
                        mData.addAll(response.getResults());
                        mSlimAdapter.updateData(mData);

                        if (isLoadMore) {
                            mRefreshLayout.finishLoadMore();
                        } else {
                            mRefreshLayout.finishRefresh();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isLoadMore) {
                            mRefreshLayout.finishLoadMore(false);
                        } else {
                            mRefreshLayout.finishRefresh(false);
                        }
                    }
                });
    }
}
