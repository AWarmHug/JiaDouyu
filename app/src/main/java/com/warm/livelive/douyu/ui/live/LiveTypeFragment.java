package com.warm.livelive.douyu.ui.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.warm.livelive.R;
import com.warm.livelive.base.fragment.LazyFragment;
import com.warm.livelive.douyu.data.bean.live.LiveCate1;
import com.warm.livelive.douyu.mvp.LiveTypeContract;
import com.warm.livelive.douyu.mvp.LiveTypePresenter;
import com.warm.livelive.douyu.ui.live.adapter.LiveTypeAdapter;
import com.warm.livelive.widget.recycleview2.LoadRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2018-06-11 09:54
 * 描述：
 */
public class LiveTypeFragment extends LazyFragment implements LiveTypeContract.View {

    @BindView(R.id.list)
    LoadRecyclerView mList;

    private LiveTypePresenter mPresenter;
    private LiveTypeAdapter mAdapter;

    public static LiveTypeFragment newInstance() {

        Bundle args = new Bundle();

        LiveTypeFragment fragment = new LiveTypeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LiveTypeFragment() {
        mPresenter = new LiveTypePresenter();
        mPresenter.attach(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void doInVisible() {

    }

    @Override
    protected void doFirstVisible() {
        mPresenter.getLiveCate1List();
    }

    @Override
    protected void doVisible() {

    }


    @Override
    public void showLiveCate1List(List<LiveCate1> liveCate1s) {
        if (mAdapter == null) {
            mAdapter = new LiveTypeAdapter(liveCate1s, getChildFragmentManager());
            mList.setAdapter(mAdapter);
            mList.setLayoutManager(new LinearLayoutManager(getBVContext()));
        }
    }

    @Override
    public int layoutResId() {
        return R.layout.fragment_live_type;
    }
}
