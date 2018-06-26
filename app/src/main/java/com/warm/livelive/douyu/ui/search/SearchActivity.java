package com.warm.livelive.douyu.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.warm.livelive.R;
import com.warm.livelive.base.actiivity.BaseActivity;
import com.warm.livelive.douyu.mvp.SearchContract;
import com.warm.livelive.douyu.mvp.SearchPresenter;
import com.warm.livelive.utils.FragmentUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：warm
 * 时间：2018-06-21 15:45
 * 描述：
 */
public class SearchActivity extends BaseActivity implements SearchFragment.OnSearchActionListener {
    @BindView(R.id.bt_search)
    Button btSearch;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.frag)
    FrameLayout frag;
    private SearchContract.Presenter mPresenter;

    @BindView(R.id.edit)
    EditText edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SearchPresenter();
        SearchFragment fragment = SearchFragment.newInstance();
        fragment.setOnSearchActionListener(this);
        mPresenter.attach(fragment);
        FragmentUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.frag);
        edit.setOnEditorActionListener(((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch();
                return true;
            } else {
                return false;
            }
        }));
    }

    public void doSearch() {
        String kw = edit.getText().toString().trim();
        onSearch(kw);
    }

    @Override
    public void onSearch(String kw) {
        if (!edit.getText().toString().equals(kw)) {
            edit.setText(kw);
        }
        if (TextUtils.isEmpty(kw)) {
            kw = edit.getHint().toString().trim();
        }
        SearchResultFragment resultFragment = SearchResultFragment.newInstance(kw);
        FragmentUtils.addFragmentToBackStack(getSupportFragmentManager(), resultFragment, R.id.frag);
    }

    @Override
    public int layoutResID() {
        return R.layout.activity_search;
    }

    @OnClick(R.id.bt_search)
    public void onViewClicked() {
        doSearch();
    }

}
