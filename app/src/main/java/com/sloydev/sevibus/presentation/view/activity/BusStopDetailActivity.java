package com.sloydev.sevibus.presentation.view.activity;

import android.support.v7.app.ActionBar;

import com.sloydev.sevibus.R;

import butterknife.ButterKnife;

public class BusStopDetailActivity extends BaseToolbarActivity {


    @Override protected int getLayoutResource() {
        return R.layout.activity_bus_stop_detail;
    }

    @Override protected void initializeViews() {
        ButterKnife.inject(this);
    }

    @Override protected void setupActionBar(ActionBar actionBar) {

    }

    @Override protected void created() {
        Integer busStopNumber = busStopFromIntent();

    }

    public Integer busStopFromIntent() {
        return 50; //TODO real
    }
}
