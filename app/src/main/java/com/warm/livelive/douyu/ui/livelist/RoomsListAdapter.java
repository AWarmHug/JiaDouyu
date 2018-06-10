package com.warm.livelive.douyu.ui.livelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.warm.livelive.GlideApp;
import com.warm.livelive.R;
import com.warm.livelive.douyu.data.bean.LiveRoom;
import com.warm.livelive.douyu.data.bean.SubChannel;
import com.warm.livelive.widget.recyadapter.BaseViewHolder;
import com.warm.livelive.widget.recyadapter.adapter.BaseAdapter;
import com.warm.livelive.utils.NumUtils;
import com.warm.livelive.utils.ScreenUtils;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2017-12-26 10:52
 * 描述：
 */

public class RoomsListAdapter extends BaseAdapter<LiveRoom, BaseViewHolder> {
    public static final int HEADER = -1;

    private SubChannel mSubChannel;


    public RoomsListAdapter(List<LiveRoom> list,SubChannel subChannel) {
        super(list);
        this.mSubChannel=subChannel;
    }

    @Override
    public int getHeaderNum() {
        return 1;
    }

    @Override
    public int getBottomBum() {
        return 0;
    }

    @Override
    protected void onBindItem(BaseViewHolder holder, int position) {

        if (getItemViewType(position)==HEADER){
            headerBind((HeaderViewHolder)holder);
        }else {
            defaultBind((ViewHolder) holder, position-getHeaderNum());
        }
    }

    private void headerBind(HeaderViewHolder holder) {
        GlideApp.with(holder.subChannel)
                .load(mSubChannel.getPic_url())
                .transition(DrawableTransitionOptions.withCrossFade(300))
                .into(holder.subChannel);
        holder.tagName.setText(mSubChannel.getTag_name());
        holder.tagIntroduce.setText(mSubChannel.getTag_introduce());
    }

    private void defaultBind(ViewHolder holder, int position) {
        if (onItemClickListener!=null){
            holder.setItemClickListener(onItemClickListener,getHeaderNum());
        }
        GlideApp.with(holder.roomSrc)
                .load(list.get(position).getRoom_src())
                .transition(DrawableTransitionOptions.withCrossFade(300))
                .into(holder.roomSrc);
        holder.online.setText(NumUtils.mini(list.get(position).getOnline()));
        holder.nickname.setText(list.get(position).getNickname());
        holder.title.setText(list.get(position).getRoom_name());

    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rooms_header, parent, false));

        } else {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rooms_list, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else {
            return super.getItemViewType(position);
        }
    }

    class HeaderViewHolder extends BaseViewHolder {
        @BindView(R.id.rooms_header)
        LinearLayout roomsHeader;
        @BindView(R.id.subChannel)
        ImageView subChannel;
        @BindView(R.id.tag_name)
        TextView tagName;
        @BindView(R.id.tag_introduce)
        TextView tagIntroduce;
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }


    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.rooms)
        RelativeLayout rooms;
        @BindView(R.id.room_src)
        ImageView roomSrc;
        @BindView(R.id.online)
        TextView online;
        @BindView(R.id.nickname)
        TextView nickname;
        @BindView(R.id.title)
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            int screenHeight = ScreenUtils.getScreenHeight(itemView.getContext());
            int screenWidth = ScreenUtils.getScreenWidth(itemView.getContext());
            int width = 100;
            if (screenHeight != 0 && screenWidth != 0) {
                width = (screenWidth - itemView.getResources().getDimensionPixelOffset(R.dimen.grid_space) * 3) / 2;
            }
            rooms.getLayoutParams().width = width;
        }
    }
}
