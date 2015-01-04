package com.sloydev.sevibus.presentation.view.activity;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

import com.sloydev.sevibus.R;
import com.sloydev.sevibus.presentation.model.ArrivalTimesModel;
import com.sloydev.sevibus.presentation.model.BusStopModel;
import com.sloydev.sevibus.presentation.presenter.BusStopDetailPresenter;
import com.sloydev.sevibus.presentation.view.BusStopDetailView;
import com.sloydev.sevibus.presentation.view.component.ArrivalTimesDetailedView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class BusStopDetailActivity extends BaseToolbarActivity implements BusStopDetailView {

    public static final String EXTRA_STOP_NUMBER = "number";

    @InjectView(R.id.bus_stop_name) TextView name;
    @InjectView(R.id.bus_stop_number) TextView number;
    @InjectView(R.id.bus_stop_arrivals) ArrivalTimesDetailedView arrivals;
    @InjectView(R.id.bus_stop_arrivals_error) View errorView;
    @InjectView(R.id.error_title) TextView errorTitle;
    @InjectView(R.id.error_subtitle) TextView errorSubtitle;

    @Inject BusStopDetailPresenter presenter;

    @Override protected int getLayoutResource() {
        return R.layout.activity_bus_stop_detail;
    }

    @Override protected void initializeViews() {
        ButterKnife.inject(this);
    }

    @Override protected void setupActionBar(ActionBar actionBar) {
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    @Override protected void created() {
        Integer busStopNumber = busStopNumberFromIntent();
        presenter.initialize(this, busStopNumber);
    }

    private Integer busStopNumberFromIntent() {
        Integer busStopNumber = getIntent().getIntExtra(EXTRA_STOP_NUMBER, 0);
        if (busStopNumber == 0) {
            //TODO Nunca debería pasar. Lanzo excepción? O informo de otra forma?
        }
        return busStopNumber;
    }

    @OnClick(R.id.error_retry)
    public void onRetryClick() {
        presenter.retry();
    }

    @Override public void renderDetails(BusStopModel busStopModel) {
        name.setText(busStopModel.getName());
        number.setText(String.valueOf(busStopModel.getNumber()));
    }

    @Override public void updateArrival(ArrivalTimesModel arrival) {
        arrivals.addArrival(arrival);
    }

    @Override public void showArrivals() {
        arrivals.setVisibility(View.VISIBLE);
    }

    @Override public void hideArrivals() {
        arrivals.setVisibility(View.GONE);
    }

    @Override public void showConnectionError() {
        errorView.setVisibility(View.VISIBLE);
        errorTitle.setText(R.string.error_oops);
        errorSubtitle.setText(R.string.error_connection);
    }

    @Override public void hideConnectionError() {
        errorView.setVisibility(View.GONE);
    }
}
