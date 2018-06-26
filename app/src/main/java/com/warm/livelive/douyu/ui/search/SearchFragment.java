package com.warm.livelive.douyu.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warm.flowlayout.flow.FlowLayout;
import com.warm.livelive.MyApp;
import com.warm.livelive.R;
import com.warm.livelive.base.fragment.BaseFragment;
import com.warm.livelive.douyu.data.bean.KeyWord;
import com.warm.livelive.douyu.data.bean.RoomInfo;
import com.warm.livelive.douyu.data.bean.dao.HistoryKeyWord;
import com.warm.livelive.douyu.data.bean.search.RecFavor;
import com.warm.livelive.douyu.mvp.SearchContract;
import com.warm.livelive.douyu.ui.PlayActivity;
import com.warm.livelive.douyu.ui.search.adapter.RecFavorViewHolder;
import com.warm.livelive.utils.NumUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2018-06-22 09:39
 * 描述：
 */
public class SearchFragment extends BaseFragment implements SearchContract.View {
    @BindView(R.id.flow_kw_history)
    FlowLayout flowKwHistory;
    @BindView(R.id.flow_kw_hot)
    FlowLayout flowKwHot;
    @BindView(R.id.line_recFavors)
    LinearLayout lineRecFavors;
    @BindView(R.id.line_kw_history)
    LinearLayout lineKwHistory;
    @BindView(R.id.line_kw_hot)
    LinearLayout lineKwHot;
    @BindView(R.id.line_like)
    LinearLayout lineLike;
    private SearchContract.Presenter mPresenter;

    private OnSearchActionListener onSearchActionListener;

    public void setOnSearchActionListener(OnSearchActionListener onSearchActionListener) {
        this.onSearchActionListener = onSearchActionListener;
    }

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SearchFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getSearchRecAndHot();
    }

    @Override
    public int layoutResId() {
        return R.layout.fragment_search;
    }


    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showHistory(List<HistoryKeyWord> historyKeyWord) {
        FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
        lineKwHistory.setVisibility(historyKeyWord.size() != 0 ? View.VISIBLE : View.GONE);
        if (lineKwHistory.isShown()) {
            for (int i = 0; i < historyKeyWord.size(); i++) {
                TextView tv = new TextView(getContext());
                tv.setLayoutParams(lp);
                tv.setBackgroundResource(R.drawable.search_bg_kw);
                tv.setPadding(dp2px(12), dp2px(3), dp2px(12), dp2px(3));
                tv.setText(historyKeyWord.get(i).getKw());
                tv.setTextSize(12);
                tv.setTextColor(getColor(R.color.white));
                tv.setOnClickListener(v -> {
                    if (onSearchActionListener != null) {
                        onSearchActionListener.onSearch(tv.getText().toString());
                    }
                });
                flowKwHistory.addView(tv);
            }
        }
    }

    @Override
    public void showHot(List<KeyWord> keyWord) {
        FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);

        lineKwHot.setVisibility(keyWord.size() != 0 ? View.VISIBLE : View.GONE);
        if (lineKwHot.isShown()) {
            for (int i = 0; i < keyWord.size(); i++) {
                TextView tv = new TextView(getContext());
                tv.setLayoutParams(lp);
                tv.setBackgroundResource(R.drawable.search_bg_kw);
                tv.setPadding(dp2px(12), dp2px(2), dp2px(12), dp2px(2));
                tv.setText(keyWord.get(i).getKw());
                tv.setTextSize(12);
                tv.setTextColor(getColor(R.color.white));
                tv.setOnClickListener(v -> {
                    if (onSearchActionListener != null) {
                        onSearchActionListener.onSearch(tv.getText().toString());
                    }
                });
                flowKwHot.addView(tv);
            }
        }
    }

    @Override
    public void showRecFavor(List<RecFavor> recFavors) {
        lineLike.setVisibility(recFavors.size() != 0 ? View.VISIBLE : View.GONE);
        if (lineLike.isShown()) {
            for (RecFavor recFavor : recFavors) {
               View item = LayoutInflater.from(lineRecFavors.getContext()).inflate(R.layout.holder_recfavors, lineRecFavors, false);

                RecFavorViewHolder viewHolder = new RecFavorViewHolder(item);
                MyApp.getInstance().getImageLoader().loadImage(this, viewHolder.ivAvatar, recFavor.getAvatar(),4);
                viewHolder.tvName.setText(recFavor.getNickname());
                viewHolder.tvLevel.setText(String.format("%d级", recFavor.getAnchorLevel()));
                viewHolder.tvFollowNum.append(NumUtil.mini(recFavor.getFollowNum()));
                viewHolder.btIsLive.setText(recFavor.getIsLive() == 1 ? "直播中" : "休息中");
                viewHolder.tvOnline.setText(String.format("%s个观众", NumUtil.mini(recFavor.getOnline())));
                lineRecFavors.addView(viewHolder.itemView);
                viewHolder.itemView.setOnClickListener(v -> {
                    RoomInfo info = new RoomInfo(recFavor.getRoomId(), recFavor.getRoomName(), recFavor.getIsVertical(), recFavor.getRoomSrc());
                    startActivity(PlayActivity.goPlay(getContext(), info));
                });
            }
        }
    }

    public interface OnSearchActionListener {
        void onSearch(String kw);
    }
}
