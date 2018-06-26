package com.warm.livelive.douyu.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.warm.livelive.R;
import com.warm.livelive.base.fragment.BaseFragment;
import com.warm.livelive.douyu.data.bean.search.Anchor;
import com.warm.livelive.douyu.data.bean.search.SearchData;
import com.warm.livelive.douyu.mvp.SearchContract;
import com.warm.livelive.douyu.mvp.SearchPresenter;
import com.warm.livelive.douyu.ui.search.adapter.AnchorAdapter;
import com.warm.livelive.utils.DisplayUtil;
import com.warm.livelive.widget.recycleview2.LoadRecyclerView;
import com.warm.livelive.widget.recycleview2.decoration.LineItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2018-06-22 18:23
 * 描述：
 */
public class SearchResultAnchorFragment extends BaseFragment implements SearchContract.ResultView, LoadRecyclerView.OnLoadMoreListener {

    private static final String KEY_KW = "kw";
    @BindView(R.id.list)
    LoadRecyclerView mList;

    public static final int SPAN_COUNT = 2;
    public static final int LIMIT = 20;
    private int offset;
    private String kw;

    private AnchorAdapter mAdapter;
    private SearchContract.Presenter mPresenter;

    public static SearchResultAnchorFragment newInstance(String kw) {
        Bundle args = new Bundle();
        args.putString(KEY_KW, kw);
        SearchResultAnchorFragment fragment = new SearchResultAnchorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SearchResultAnchorFragment() {
        mPresenter = new SearchPresenter();
        mPresenter.attachResultView(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            kw = bundle.getString(KEY_KW);
        }
        mAdapter = new AnchorAdapter();
        mList.setAdapter(mAdapter);
        mList.setLayoutManager(new LinearLayoutManager(getContext()));
        mList.addItemDecoration(new LineItemDecoration(getContext(), LinearLayoutManager.VERTICAL, DisplayUtil.dp2px(getContext(), 4), getColor(R.color.white_bg)));
        mList.setOnLoadMoreListener(this);
        loadData();
    }

    private void loadData() {
        mAdapter.loadBegan("正在加载,请稍后");
        if (offset == 0) {
            mList.scrollToPosition(0);
        }
        mPresenter.mobileSearch(4, 1, kw, offset, LIMIT);
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
        List<Anchor> anchor = data.getAnchor();
        if (anchor != null) {
            if (offset == 0) {
                mAdapter.refreshAll(anchor);
            } else {
                mAdapter.insertRange(anchor);
            }
            if (anchor.size() == LIMIT) {
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
        return R.layout.fragment_search_result_anchor;
    }

}