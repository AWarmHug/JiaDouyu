package com.warm.livelive.douyu.ui.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;

import com.warm.livelive.R;
import com.warm.livelive.base.fragment.BaseFragment;
import com.warm.livelive.douyu.data.bean.douyu.live.LiveRoomItem;
import com.warm.livelive.douyu.mvp.live.LiveTabItemContract;
import com.warm.livelive.douyu.mvp.live.LiveTabItemPresenter;
import com.warm.livelive.douyu.ui.live.adapter.LiveAllListAdapter;
import com.warm.livelive.utils.DisplayUtil;
import com.warm.livelive.widget.recycleview2.LoadRecyclerView;
import com.warm.livelive.widget.recycleview2.decoration.GridItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2018-06-13 11:46
 * 描述：
 */
public class LiveTabListFragment extends BaseFragment implements LiveTabItemContract.ListView {

    @BindView(R.id.list)
    LoadRecyclerView mList;

    public static final int SPAN_COUNT = 2;
    private static final int LIMIT = 20;
    private static final String KEY_LEVEL = "level";
    private static final String KEY_CATE_ID = "cate_id";

    private LiveTabItemPresenter mPresenter;
    private int level;
    private int cate_id;
    private int offset;
    private LiveAllListAdapter mAllListAdapter;


    public static LiveTabListFragment newInstance(int level, int cate_id) {
        Bundle args = new Bundle();
        args.putInt(KEY_LEVEL, level);
        args.putInt(KEY_CATE_ID, cate_id);
        LiveTabListFragment fragment = new LiveTabListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public LiveTabListFragment() {
        mPresenter = new LiveTabItemPresenter();
        mPresenter.attachListView(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            level = bundle.getInt(KEY_LEVEL, 0);
            cate_id = bundle.getInt(KEY_CATE_ID, 0);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAllListAdapter = new LiveAllListAdapter(this);
        mList.setAdapter(mAllListAdapter);
        GridLayoutManager grid = new GridLayoutManager(getBVContext(), SPAN_COUNT);
        grid.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == mAllListAdapter.getItemCount() - 1 ? SPAN_COUNT : 1;
            }
        });
        mList.setLayoutManager(grid);
        mList.addItemDecoration(new GridItemDecoration(DisplayUtil.dp2px(getBVContext(), 4), SPAN_COUNT));
        mList.setOnLoadMoreListener(() -> {
            mPresenter.getRoomList(level, cate_id, offset, LIMIT);
        });
        mPresenter.getRoomList(level, cate_id, offset, LIMIT);
    }

    @Override
    public int layoutResId() {
        return R.layout.fragment_live_all;
    }

    @Override
    public void showRoomList(List<LiveRoomItem> liveRoomItems) {
        if (offset == 0) {
            mAllListAdapter.refreshAll(liveRoomItems);
        } else {
            mAllListAdapter.insertRange(liveRoomItems);
        }
        offset += LIMIT;
        mList.setLoadMoreAble(true);
        mAllListAdapter.loadSuccess("加载成功");
    }
}
