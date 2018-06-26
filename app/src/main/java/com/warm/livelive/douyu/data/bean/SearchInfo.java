package com.warm.livelive.douyu.data.bean;

import com.warm.livelive.douyu.data.bean.dao.HistoryKeyWord;
import com.warm.livelive.douyu.data.bean.search.RecFavor;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-22 11:20
 * 描述：
 */
public class SearchInfo {
    private List<HistoryKeyWord> historyKeyWords;
    private List<KeyWord> keyWords;
    private List<RecFavor> recFavors;

    public SearchInfo(List<HistoryKeyWord> historyKeyWords, List<KeyWord> keyWords, List<RecFavor> recFavors) {
        this.historyKeyWords = historyKeyWords;
        this.keyWords = keyWords;
        this.recFavors = recFavors;
    }

    public List<HistoryKeyWord> getHistoryKeyWords() {
        return historyKeyWords;
    }

    public void setHistoryKeyWords(List<HistoryKeyWord> historyKeyWords) {
        this.historyKeyWords = historyKeyWords;
    }

    public List<KeyWord> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<KeyWord> keyWords) {
        this.keyWords = keyWords;
    }

    public List<RecFavor> getRecFavors() {
        return recFavors;
    }

    public void setRecFavors(List<RecFavor> recFavors) {
        this.recFavors = recFavors;
    }
}
