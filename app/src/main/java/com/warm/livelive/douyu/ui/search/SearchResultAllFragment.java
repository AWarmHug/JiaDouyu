package com.warm.livelive.douyu.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.warm.flowlayout.flow.FlowLayout;
import com.warm.livelive.MyApp;
import com.warm.livelive.R;
import com.warm.livelive.base.fragment.BaseFragment;
import com.warm.livelive.douyu.data.bean.search.Anchor;
import com.warm.livelive.douyu.data.bean.search.Room;
import com.warm.livelive.douyu.data.bean.search.SearchData;
import com.warm.livelive.douyu.data.bean.search.Video;
import com.warm.livelive.douyu.mvp.SearchContract;
import com.warm.livelive.douyu.mvp.SearchPresenter;
import com.warm.livelive.douyu.ui.search.adapter.AnchorViewHolder;
import com.warm.livelive.douyu.ui.search.adapter.RoomViewHolder;
import com.warm.livelive.douyu.ui.search.adapter.VideoViewHolder;
import com.warm.livelive.utils.CheckUtil;
import com.warm.livelive.utils.NumUtil;
import com.warm.livelive.utils.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：warm
 * 时间：2018-06-22 18:20
 * 描述：
 */
public class SearchResultAllFragment extends BaseFragment implements SearchContract.ResultView {

    private static final String KEY_KW = "kw";
    @BindView(R.id.grid_anchor)
    GridLayout gridAnchor;
    @BindView(R.id.grid_video)
    GridLayout gridVideo;
    @BindView(R.id.grid_room)
    GridLayout gridRoom;
    @BindView(R.id.tv_more_anchor)
    TextView tvMoreAnchor;
    @BindView(R.id.flow_anchor)
    FlowLayout flowAnchor;
    @BindView(R.id.tv_more_video)
    TextView tvMoreVideo;
    @BindView(R.id.flow_video)
    FlowLayout flowVideo;
    @BindView(R.id.tv_more_room)
    TextView tvMoreRoom;
    @BindView(R.id.flow_room)
    FlowLayout flowRoom;

    private OnSelectActionListener onSelectActionListener;

    private SearchContract.Presenter mPresenter;

    private String kw;


    public static SearchResultAllFragment newInstance(String kw) {

        Bundle args = new Bundle();
        args.putString(KEY_KW, kw);
        SearchResultAllFragment fragment = new SearchResultAllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SearchResultAllFragment() {
        mPresenter = new SearchPresenter();
        mPresenter.attachResultView(this);
    }


    public void setOnSelectActionListener(OnSelectActionListener onSelectActionListener) {
        this.onSelectActionListener = onSelectActionListener;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            kw = bundle.getString(KEY_KW);
        }
        mPresenter.mobileSearch(1, 1, kw, 0, 20);
    }


    @Override
    public void showSearchResult(SearchData data) {
        showAnchor(data.getAnchor());
        showVideo(data.getVideo());
        showRoom(data.getRoom());
    }


    /*<Button
    app:layout_column="1"
    app:layout_columnWeight="1"
    app:layout_gravity="fill" />*/
    private void showAnchor(List<Anchor> anchors) {
        gridAnchor.setVisibility(CheckUtil.listIsEmpty(anchors) ? View.VISIBLE : View.GONE);
        if (gridAnchor.isShown()) {
            for (Anchor anchor : anchors) {
               View item = LayoutInflater.from(flowAnchor.getContext()).inflate(R.layout.holder_anchor, flowAnchor, false);
                AnchorViewHolder viewHolder = new AnchorViewHolder(item);
                int width = (flowAnchor.getMeasuredWidth() - (flowAnchor.getHorizontalSize() - 1) * flowAnchor.getSpaceH()) / flowAnchor.getHorizontalSize();
                viewHolder.ivAvatar.getLayoutParams().width = width;
                viewHolder.ivAvatar.getLayoutParams().height = width;
                MyApp.getInstance().getImageLoader().loadImage(SearchResultAllFragment.this, viewHolder.ivAvatar, anchor.getAvatar(), 4);
                viewHolder.isLive.setVisibility(anchor.getIsLive() == 1 ? View.VISIBLE : View.GONE);
                viewHolder.tvName.setText(anchor.getNrNickname());
                viewHolder.tvFollow.setText(String.format("%s人关注", NumUtil.mini(anchor.getFollow())));
                flowAnchor.addView(viewHolder.itemView);
            }
        }
    }

    private void showVideo(List<Video> videos) {
        gridVideo.setVisibility(CheckUtil.listIsEmpty(videos) ? View.VISIBLE : View.GONE);
        if (gridVideo.isShown()) {
            for (int i = 0; i < videos.size(); i++) {
                Video video = videos.get(i);
                View view = LayoutInflater.from(flowVideo.getContext()).inflate(R.layout.holder_video, flowVideo, false);
                VideoViewHolder viewHolder = new VideoViewHolder(view);
                MyApp.getInstance().getImageLoader().loadImage(SearchResultAllFragment.this, viewHolder.ivPic, video.getVideo_pic());
                viewHolder.tvTime.setText(TimeUtil.time2Duration(video.getVideo_duration()));
                viewHolder.tvTitle.setText(video.getTitle());
                viewHolder.tvName.setText(video.getNickname());
                flowVideo.addView(viewHolder.itemView);

            }
        }
    }

    private void showRoom(List<Room> rooms) {
        gridRoom.setVisibility(CheckUtil.listIsEmpty(rooms) ? View.VISIBLE : View.GONE);
        if (gridRoom.isShown()) {
            for (int i = 0; i < rooms.size(); i++) {
                Room room = rooms.get(i);
                View view = LayoutInflater.from(flowRoom.getContext()).inflate(R.layout.holder_room, flowRoom, false);
                RoomViewHolder viewHolder = new RoomViewHolder(view);
                MyApp.getInstance().getImageLoader().loadImage(SearchResultAllFragment.this, viewHolder.ivPic, room.getRoomSrc());
                viewHolder.isLive.setVisibility(room.getIsLive() == 1 ? View.VISIBLE : View.GONE);
                viewHolder.tvName.setText(room.getNickname());
                viewHolder.tvTitle.setText(room.getNoRed());
                viewHolder.tvHeat.setText(String.format("%s热度", NumUtil.mini(room.getPopularity())));
                //使用Spec定义子控件的位置和比重
                flowRoom.addView(viewHolder.itemView);
            }
        }
    }


    @OnClick({R.id.tv_more_anchor, R.id.tv_more_video, R.id.tv_more_room})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_more_anchor:
                if (onSelectActionListener != null) {
                    onSelectActionListener.onSelect(3);
                }
                break;
            case R.id.tv_more_video:
                if (onSelectActionListener != null) {
                    onSelectActionListener.onSelect(2);
                }
                break;
            case R.id.tv_more_room:
                if (onSelectActionListener != null) {
                    onSelectActionListener.onSelect(1);
                }
                break;
        }
    }

    @Override
    public int layoutResId() {
        return R.layout.fragment_search_result_all;
    }


    public interface OnSelectActionListener {
        void onSelect(int position);
    }
}