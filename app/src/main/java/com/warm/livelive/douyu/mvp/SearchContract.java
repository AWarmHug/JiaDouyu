package com.warm.livelive.douyu.mvp;

import com.warm.livelive.base.BasePresenter;
import com.warm.livelive.base.BaseView;
import com.warm.livelive.douyu.data.bean.KeyWord;
import com.warm.livelive.douyu.data.bean.dao.HistoryKeyWord;
import com.warm.livelive.douyu.data.bean.search.RecFavor;
import com.warm.livelive.douyu.data.bean.search.SearchData;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-21 16:55
 * 描述：
 */
public interface SearchContract {

    interface Presenter extends BasePresenter<View> {

        void attachResultView(ResultView resultView);


        void getSearchRecAndHot();

        void mobileSearch(int sort1, int sort2, String sk, int offset, int limit);

    }

    interface View extends BaseView {
        void setPresenter(Presenter presenter);

        void showHistory(List<HistoryKeyWord> historyKeyWord);

        void showHot(List<KeyWord> keyWord);

        void showRecFavor(List<RecFavor> recFavor);
    }

    interface ResultView extends BaseView {
        void showSearchResult( SearchData data);
    }


}
