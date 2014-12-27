package com.sloydev.sevibus.presentation.view.activity;

import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.sloydev.sevibus.R;
import com.sloydev.sevibus.presentation.model.BusStopModel;
import com.sloydev.sevibus.presentation.presenter.BusStopDetailPresenter;
import com.sloydev.sevibus.presentation.view.BusStopDetailView;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class BusStopDetailActivity extends BaseToolbarActivity implements BusStopDetailView {

    @Inject BusStopDetailPresenter presenter;

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
        presenter.initialize(this, busStopNumber);
    }

    public Integer busStopFromIntent() {
        return 50; //TODO real
    }

    @Override public void renderDetails(BusStopModel busStopModel) {
        Toast.makeText(this, busStopModel.getName(), Toast.LENGTH_SHORT).show();
    }
}
