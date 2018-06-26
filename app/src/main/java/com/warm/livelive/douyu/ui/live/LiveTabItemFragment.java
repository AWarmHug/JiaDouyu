package com.warm.livelive.douyu.ui.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.warm.livelive.R;
import com.warm.livelive.base.fragment.LazyFragment;
import com.warm.livelive.douyu.data.bean.Activity;
import com.warm.livelive.douyu.data.bean.Cate3;
import com.warm.livelive.douyu.data.bean.Component;
import com.warm.livelive.douyu.data.bean.Promotion;
import com.warm.livelive.douyu.data.bean.Slide;
import com.warm.livelive.douyu.data.bean.TabCate1;
import com.warm.livelive.douyu.data.bean.TabCate2;
import com.warm.livelive.douyu.mvp.LiveTabItemContract;
import com.warm.livelive.douyu.mvp.LiveTabItemPresenter;
import com.warm.livelive.widget.ActivityLayout;
import com.warm.livelive.widget.OnItemClickListener;
import com.warm.livelive.widget.PromotionLayout;
import com.warm.tablayout.TabLayout;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2018-06-14 10:46
 * 描述：
 */
public class LiveTabItemFragment extends LazyFragment implements LiveTabItemContract.TabItemView {

    private static final String KEY_TAB_CATE_1 = "TabCate1";
    @BindView(R.id.promo)
    PromotionLayout promo;
    @BindView(R.id.flipper)
    ActivityLayout flipper;
    @BindView(R.id.type)
    TabLayout type;
    @BindView(R.id.tab)
    TabLayout tab;
//    @BindView(R.id.fragment)
//    FrameLayout fragment;

    private LiveTabItemPresenter mPresenter;
    private TabCate1 tabCate1;

    public static LiveTabItemFragment newInstance(TabCate1 tabCate1) {

        Bundle args = new Bundle();
        args.putParcelable(KEY_TAB_CATE_1, tabCate1);
        LiveTabItemFragment fragment = new LiveTabItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LiveTabItemFragment() {
        mPresenter = new LiveTabItemPresenter();
        mPresenter.attach(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            tabCate1 = bundle.getParcelable(KEY_TAB_CATE_1);
        }
    }

    @Override
    protected void doInVisible() {

    }

    @Override
    protected void doFirstVisible() {
        if (tabCate1.getLevel() == 2) {
//            mPresenter.getAllComponentList(tabCate1.getCate_id());
            mPresenter.getPromo(tabCate1.getCate_id());
        }
        if (tabCate1.getCate_id() == 181) {
            mPresenter.getActivityList(tabCate1.getCate_id());
        }
        if (tabCate1.getLevel() == 1) {
            //颜值==26,颜值获取Cate2
            if (tabCate1.getTab_id() == 26) {
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.frag, LiveTabListFragment.newInstance(tabCate1.getLevel(), tabCate1.getCate_id()))
                        .commit();
            } else {
                mPresenter.getTabCate2List(tabCate1.getTab_id());
            }
        } else {
            mPresenter.getThreeCate(tabCate1.getCate_id());
        }
    }

    @Override
    protected void doVisible() {

    }

    @Override
    public int layoutResId() {
        return R.layout.fragment_live_tabitem;
    }


    @Override
    public void showAllComponentList(List<Component> components) {
        if (components != null && components.size() != 0) {
            type.setVisibility(View.VISIBLE);
            for (Component component : components) {
                type.addTab(type.newTab().setText(component.getTitle()));
            }
        }
    }

    @Override
    public void showSlideLists(List<Slide> slides) {
        promo.setSlide(slides, position -> {

        });
    }

    @Override
    public void showPromotions(Promotion promotion) {
        promo.setPromotion(promotion, v -> {
            Toast.makeText(getBVContext(), "点击了下载", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public void showTabCate2List(List<TabCate2> tabCate2s) {
        if (tabCate2s.size() != 0) {
            tab.setVisibility(tabCate2s.size() > 1 ? View.VISIBLE : View.GONE);
            for (TabCate2 tabCate2 : tabCate2s) {
                tab.addTab(tab.newTab().setText(tabCate2.getCate2_name()));
            }
            tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                   int pos= tab.getPosition();
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.frag, LiveTabListFragment.newInstance(tabCate1.getLevel()+1, tabCate2s.get(pos).getCate2_id()))
                            .commit();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.frag, LiveTabListFragment.newInstance(tabCate1.getLevel()+1, tabCate2s.get(0).getCate2_id()))
                    .commit();
        }
    }

    @Override
    public void showThreeCate(List<Cate3> cate3s) {
        if (cate3s.size() != 0) {
            tab.setVisibility(cate3s.size() > 1 ? View.VISIBLE : View.GONE);
            for (Cate3 cate3 : cate3s) {
                tab.addTab(tab.newTab().setText(cate3.getName()));
            }
            tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab.getPosition() == 0) {
                        getChildFragmentManager().beginTransaction()
                                .replace(R.id.frag, LiveTabListFragment.newInstance(tabCate1.getLevel(), tabCate1.getCate_id()))
                                .commit();
                    } else {
                        //设置listfragment
                        getChildFragmentManager().beginTransaction()
                                .replace(R.id.frag, LiveTabListFragment.newInstance(tabCate1.getLevel() + 1, cate3s.get(tab.getPosition()).getCate_id()))
                                .commit();
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.frag, LiveTabListFragment.newInstance(tabCate1.getLevel(), tabCate1.getCate_id()))
                    .commit();
        }
    }

    @Override
    public void showActivityList(List<Activity> activities) {
        flipper.setActivity(activities, new OnItemClickListener() {
            @Override
            public void itemClick(int position) {

            }
        });
    }

}
