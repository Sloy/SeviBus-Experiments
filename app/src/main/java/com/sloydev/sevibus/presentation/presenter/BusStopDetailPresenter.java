package com.sloydev.sevibus.presentation.presenter;

import com.sloydev.sevibus.domain.interactor.BusStopDetailInteractor;
import com.sloydev.sevibus.presentation.model.mapper.BusStopModelMapper;
import com.sloydev.sevibus.presentation.view.BusStopDetailView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BusStopDetailPresenter implements Presenter {

    private final BusStopDetailInteractor busStopDetailInteractor;
    private final BusStopModelMapper busStopModelMapper;

    private BusStopDetailView busStopDetailView;
    private Integer busStopNumber;

    @Inject public BusStopDetailPresenter(BusStopDetailInteractor busStopDetailInteractor, BusStopModelMapper busStopModelMapper) {
        this.busStopDetailInteractor = busStopDetailInteractor;
        this.busStopModelMapper = busStopModelMapper;
    }

    public void initialize(BusStopDetailView busStopDetailView, Integer busStopNumber) {
        this.busStopDetailView = busStopDetailView;
        this.busStopNumber = busStopNumber;
        this.loadBusStopDetails();
    }

    private void loadBusStopDetails() {
        busStopDetailInteractor.loadBusStopDetail(busStopNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(busStopModelMapper::transform)
                .subscribe(busStopModel -> busStopDetailView.renderDetails(busStopModel));

    }
}
