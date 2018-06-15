package com.warm.livelive.douyu.ui.live.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.warm.livelive.R;
import com.warm.livelive.douyu.data.bean.douyu.live.LiveCate1;
import com.warm.livelive.utils.DisplayUtil;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-12 10:32
 * 描述：
 */
public class LiveTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int EMPTY = -1;
    private FragmentManager mFragmentManager;
    private List<LiveCate1> liveCate1s;
    private int pageNum = 1;

    public LiveTypeAdapter(@NonNull List<LiveCate1> liveCate1s, @NonNull FragmentManager fragmentManager) {
        this.liveCate1s = liveCate1s;
        this.mFragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_live_type, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == EMPTY) {
            onBindEmpty((EmptyViewHolder) holder);
        } else {
            onBindItem((ItemViewHolder) holder, position);
        }
    }

    private void onBindEmpty(EmptyViewHolder holder) {


    }

    private void onBindItem(ItemViewHolder holder, int position) {
        holder.cateName.setText(liveCate1s.get(position).getCate_name());

        int line = getLine(position);

        resetLayoutParams(holder, line);

        TypePagerAdapter adapter = new TypePagerAdapter(mFragmentManager, liveCate1s.get(position));

        holder.pager.setAdapter(adapter);
    }

    private int getLine(int position) {
        int line = liveCate1s.get(position).getCate2_list().size() / 4;
        if (liveCate1s.get(position).getCate2_list().size() % 4 != 0) {
            line += 1;
        }
        return line;
    }

    private void resetLayoutParams(ItemViewHolder holder, int line) {
        ViewGroup.LayoutParams lp = holder.pager.getLayoutParams();
        lp.width = ViewPager.LayoutParams.MATCH_PARENT;
        lp.height = DisplayUtil.dp2px(holder.itemView.getContext(), line * 96);
        holder.pager.setLayoutParams(lp);
    }


    @Override
    public int getItemCount() {
        return liveCate1s.size();
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView cateName;
        ViewPager pager;

        ItemViewHolder(View itemView) {
            super(itemView);
            cateName = itemView.findViewById(R.id.cate_name);
            pager = itemView.findViewById(R.id.pager);
            pager.setId(pageNum);
            pageNum++;
        }
    }


}
