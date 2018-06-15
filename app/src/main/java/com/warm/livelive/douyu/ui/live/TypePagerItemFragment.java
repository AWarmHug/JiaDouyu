package com.warm.livelive.douyu.ui.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.warm.livelive.LiveApp;
import com.warm.livelive.R;
import com.warm.livelive.base.fragment.LazyRxFragment;
import com.warm.livelive.douyu.data.bean.douyu.live.LiveCate1;
import com.warm.livelive.douyu.data.bean.douyu.live.LiveCate2;
import com.warm.livelive.douyu.ui.live.adapter.TypePagerAdapter;
import com.warm.livelive.error.KnownException;
import com.warm.livelive.utils.rx.ThrowableConsumer;
import com.warm.livelive.widget.load.DouyuLoadView;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：warm
 * 时间：2018-06-12 12:58
 * 描述：
 */
public class TypePagerItemFragment extends LazyRxFragment {
    private static final String KEY_CATE1 = "CATE1";
    private static final String KEY_PAGE_POSITION = "PAGE_POSITION";

    @BindView(R.id.grid)
    GridView mGrid;
    @BindView(R.id.load)
    DouyuLoadView mLoad;
    private LiveCate1 mLiveCate1;
    private int pagePosition;

    public static TypePagerItemFragment newInstance(@Nullable LiveCate1 liveCate1, int pagePosition) {

        Bundle args = new Bundle();
        args.putParcelable(KEY_CATE1, liveCate1);
        args.putInt(KEY_PAGE_POSITION, pagePosition);
        TypePagerItemFragment fragment = new TypePagerItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mLiveCate1 = bundle.getParcelable(KEY_CATE1);
            pagePosition = bundle.getInt(KEY_PAGE_POSITION);
        }
    }

    @Override
    protected void doFirstVisible() {
        if (pagePosition == 0) {
            mLoad.loadBegan("正在加载");
            setAdapter(mLiveCate1.getCate2_list());
        } else {
            mLoad.loadBegan("正在加载");
            int offset = TypePagerAdapter.ITEM_COUNT * pagePosition;
            Disposable disposable = mDataManager.getLiveCate2ByCate1(mLiveCate1.getCate1_id(), Math.min(TypePagerAdapter.ITEM_COUNT, mLiveCate1.getCate2_count() - offset), offset)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::setAdapter
                            , new ThrowableConsumer(this){
                                @Override
                                public void handle(KnownException exception) {
                                    mLoad.loadFail("加载失败", v -> doFirstVisible());
//                                    super.handle(exception);
                                }
                            });
            addDisposable(disposable);
        }
    }

    private void setAdapter(List<LiveCate2> cate2s) {
        mLoad.loadSuccess("加载成功");
        mGrid.setAdapter(new GridAdapter(cate2s));
    }

    @Override
    protected void doVisible() {

    }

    @Override
    protected void doInVisible() {

    }

    class GridAdapter extends BaseAdapter {
        List<LiveCate2> cate2s;

        public GridAdapter(List<LiveCate2> cate2s) {
            this.cate2s = cate2s;
        }

        @Override
        public int getCount() {
            return cate2s.size();
        }

        @Override
        public Object getItem(int position) {
            return cate2s.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_live_type_item, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            LiveApp.getInstance().getImageLoader().loadImage(TypePagerItemFragment.this, holder.image, cate2s.get(position).getSquare_icon_url());
            holder.text.setText(cate2s.get(position).getCate2_name());
            return convertView;
        }

        class ViewHolder {
            View itemView;
            TextView text;
            ImageView image;

            ViewHolder(View itemView) {
                this.itemView = itemView;
                text = itemView.findViewById(R.id.text);
                image = itemView.findViewById(R.id.image);
            }
        }
    }


    @Override
    public int layoutResId() {
        return R.layout.fragment_type_pager;
    }
}