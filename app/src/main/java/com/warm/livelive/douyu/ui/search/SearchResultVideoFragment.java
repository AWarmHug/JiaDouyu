package com.warm.livelive.douyu.ui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.warm.livelive.R;
import com.warm.livelive.base.fragment.BaseFragment;
import com.warm.livelive.douyu.data.bean.search.SearchData;
import com.warm.livelive.douyu.data.bean.search.Video;
import com.warm.livelive.douyu.mvp.SearchContract;
import com.warm.livelive.douyu.mvp.SearchPresenter;
import com.warm.livelive.douyu.ui.search.adapter.VideoAdapter;
import com.warm.livelive.utils.DisplayUtil;
import com.warm.livelive.widget.recycleview2.LoadRecyclerView;
import com.warm.livelive.widget.recycleview2.decoration.GridItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2018-06-22 18:21
 * 描述：
 */
public class SearchResultVideoFragment extends BaseFragment implements SearchContract.ResultView, LoadRecyclerView.OnLoadMoreListener {

    private static final String KEY_KW = "KW";
    @BindView(R.id.one)
    RadioButton one;
    @BindView(R.id.two)
    RadioButton two;
    @BindView(R.id.three)
    RadioButton three;
    @BindView(R.id.four)
    RadioButton four;
    @BindView(R.id.radio)
    RadioGroup radio;
    @BindView(R.id.list)
    LoadRecyclerView mList;

    private VideoAdapter mAdapter;

    public static final int SPAN_COUNT = 2;

    public static final int LIMIT = 20;
    private int offset;
    private int mSort2 = 1;
    private SearchContract.Presenter mPresenter;

    private String kw;


    public static SearchResultVideoFragment newInstance(String kw) {

        Bundle args = new Bundle();
        args.putString(KEY_KW, kw);
        SearchResultVideoFragment fragment = new SearchResultVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SearchResultVideoFragment() {
        mPresenter = new SearchPresenter();
        mPresenter.attachResultView(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.one:
                        mSort2 = 1;
                        loadData(0);
                        break;
                    case R.id.two:
                        mSort2 = 2;
                        loadData(0);
                        break;
                    case R.id.three:
                        mSort2 = 3;
                        loadData(0);
                        break;
                    case R.id.four:
                        mSort2 = 4;
                        loadData(0);
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            kw = bundle.getString(KEY_KW);
        }
        mAdapter = new VideoAdapter();
        mList.setAdapter(mAdapter);
        GridLayoutManager grid = new GridLayoutManager(getBVContext(), SPAN_COUNT);
        grid.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == mAdapter.getItemCount() - 1 ? SPAN_COUNT : 1;
            }
        });
        mList.setLayoutManager(grid);
        mList.addItemDecoration(new GridItemDecoration(DisplayUtil.dp2px(getBVContext(), 4), SPAN_COUNT));
        mList.setOnLoadMoreListener(this);
        radio.check(R.id.one);
    }

    private void loadData() {
        mAdapter.loadBegan("正在加载,请稍后");
        if (offset == 0) {
            mList.scrollToPosition(0);
        }
        mPresenter.mobileSearch(3, mSort2, kw, offset, LIMIT);
    }

    private void loadData(int offset) {
        this.offset = offset;
        loadData();
    }


    @Override
    public void onLoadMore() {
        loadData();
    }


    @Override
    public void showSearchResult(SearchData data) {
        List<Video> video = data.getVideo();
        if (video != null) {
            if (offset == 0) {
                mAdapter.refreshAll(video);
            } else {
                mAdapter.insertRange(video);
            }
            if (video.size() == LIMIT) {
                offset += LIMIT;
                mList.setLoadMoreAble(true);
                mAdapter.loadSuccess("加载成功");
            } else {
                mAdapter.loadSuccess("全部加载完成");
            }

        }
    }


    @Override
    public int layoutResId() {
        return R.layout.fragment_search_result_video;
    }

}