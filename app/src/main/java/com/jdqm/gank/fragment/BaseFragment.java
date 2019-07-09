package com.jdqm.gank.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdqm.gank.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.text.SimpleDateFormat;

/**
 * Created by Jdqm on 2019-07-09.
 */
public class BaseFragment extends RxFragment {
    public static final SimpleDateFormat mSourceDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public static final SimpleDateFormat mDestFormat = new SimpleDateFormat("yyyy-MM-dd HH:m");

    public static final int PAGE_SIZE = 20;
    protected int mCurrentPage = 1;


    protected SmartRefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_basic, container, false);

        initView(inflater, view);

        return view;
    }

    public void initView(LayoutInflater inflater, View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
    }
}
