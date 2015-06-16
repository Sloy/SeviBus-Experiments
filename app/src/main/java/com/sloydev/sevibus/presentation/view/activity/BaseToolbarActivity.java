package com.sloydev.sevibus.presentation.view.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.sloydev.sevibus.R;

import static butterknife.ButterKnife.findById;

public abstract class BaseToolbarActivity extends BaseActivity {

    private Toolbar toolbar;

    protected void inflateLayout() {
        ViewGroup toolbarDecor = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_toolbar_decor, container);
        bindToolbar();
        ViewGroup activityContent = findById(toolbarDecor, R.id.action_bar_activity_content);
        getLayoutInflater().inflate(getLayoutResource(), activityContent);
        setupActionBar(getSupportActionBar());
    }

    private void bindToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_actionbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        } else {
            throw new IllegalStateException("Toolbar view (R.id.toolbar_actionbar) not found in BaseToolbarActivity");
        }
    }

    protected abstract void setupActionBar(ActionBar actionBar);

    @Override protected abstract void initializePresenter();

    public Toolbar getToolbar() {
        return toolbar;
    }

}
