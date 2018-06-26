package com.warm.livelive.douyu.ui.live.adapter;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.warm.livelive.MyApp;
import com.warm.livelive.R;
import com.warm.livelive.douyu.data.bean.live.LiveRoomItem;
import com.warm.livelive.utils.NumUtil;
import com.warm.livelive.widget.recycleview2.DouyuExpandAdapter;
import com.warm.livelive.widget.recycleview2.adpter.BaseViewHolder;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-13 13:54
 * 描述：
 */
public class LiveAllListAdapter extends DouyuExpandAdapter<LiveRoomItem> {
    private Fragment mFragment;

    public LiveAllListAdapter(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    @Override
    protected void onBindDefaultItem(BaseViewHolder holder, int position, List<Object> payloads) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            MyApp.getInstance().getImageLoader().loadImage(mFragment, itemViewHolder.room_src, mDatas.get(position).getRoom_src());
            itemViewHolder.online_num.setText(NumUtil.mini(mDatas.get(position).getOnline_num()));
            itemViewHolder.nickname.setText(mDatas.get(position).getNickname());
            itemViewHolder.room_name.setText(mDatas.get(position).getRoom_name());
        }
    }

    @Override
    protected BaseViewHolder onCreateDefaultViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rooms_list, parent, false);
        return new ItemViewHolder(view);
    }

    static class ItemViewHolder extends BaseViewHolder {
        ImageView room_src;
        TextView online_num;
        TextView nickname;
        TextView room_name;

        public ItemViewHolder(View itemView) {
            super(itemView);
//            int screenHeight = ScreenUtil.getScreenHeight(itemView.getContext());
//            int screenWidth = ScreenUtil.getScreenWidth(itemView.getContext());
//            int width = 100;
//            if (screenHeight != 0 && screenWidth != 0) {
//                width = (screenWidth - itemView.getResources().getDimensionPixelOffset(R.dimen.grid_space) * 3) / LiveListFragment.SPAN_COUNT;
//            }
//            itemView.getLayoutParams().width = width;
            room_src = itemView.findViewById(R.id.room_src);
            online_num = itemView.findViewById(R.id.online_num);
            nickname = itemView.findViewById(R.id.nickname);
            room_name = itemView.findViewById(R.id.room_name);
        }
    }
}
