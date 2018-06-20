package com.warm.livelive.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.warm.livelive.R;
import com.warm.livelive.douyu.data.bean.douyu.Activity;
import com.warm.livelive.utils.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：warm
 * 时间：2018-06-15 09:16
 * 描述：
 */
public class ActivityLayout extends ViewFlipper {


    public ActivityLayout(Context context) {
        this(context, null);
    }

    public ActivityLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void setActivity(List<Activity> activities, OnItemClickListener onItemClickListener) {
        if (activities != null && activities.size() != 0) {
            for (int i = 0; i < activities.size(); i++) {
                ItemLayout item = new ItemLayout(getContext());
                int finalI = i;
                item.setActivity(activities.get(i), v -> {
                    onItemClickListener.itemClick(finalI);
                });
                addView(item);
            }
            setAnimateFirstView(false);
            setAutoStart(activities.size() > 1);
        }
    }

    static class ItemLayout extends RelativeLayout {
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.info)
        TextView info;
        @BindView(R.id.bt_action)
        Button btAction;

        public ItemLayout(Context context) {
            this(context, null);
        }

        public ItemLayout(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public ItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            inflate(context, R.layout.layout_activity, this);
            ButterKnife.bind(this);
        }

        public void setActivity(Activity activity, OnClickListener clickListener) {
            title.setText(activity.getAct_name());
            StringBuilder sb = new StringBuilder();

            sb.append(TimeUtil.time2Activity(activity.getAct_start_time()))
                    .append("开始");
            long now = System.currentTimeMillis() / 1000;
            sb.append(" ");
            if (now < activity.getAct_start_time()) {
                sb.append(String.valueOf(activity.getSub_num())).append("预订");
            } else if (activity.getAct_start_time() < now && now < activity.getAct_end_time()) {
                sb.append("进行中");
            } else if (now > activity.getAct_end_time()) {
                sb.append("已结束");
            }
            int start = sb.indexOf("开始");
            int end = sb.length();
            SpannableString ss = new SpannableString(sb);
            ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.colorPrimary)), start + 2, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            info.setText(ss);
            btAction.setOnClickListener(clickListener);
        }

    }
}
