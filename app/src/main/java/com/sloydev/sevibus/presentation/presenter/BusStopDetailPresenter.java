package com.sloydev.sevibus.presentation.presenter;

import com.sloydev.sevibus.domain.interactor.ArrivalTimesInteractor;
import com.sloydev.sevibus.domain.interactor.BusStopDetailInteractor;
import com.sloydev.sevibus.presentation.model.ArrivalTimesModel;
import com.sloydev.sevibus.presentation.model.mapper.ArrivalTimesModelMapper;
import com.sloydev.sevibus.presentation.model.mapper.BusStopModelMapper;
import com.sloydev.sevibus.presentation.view.BusStopDetailView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BusStopDetailPresenter implements Presenter {

    private final BusStopDetailInteractor busStopDetailInteractor;
    private final ArrivalTimesInteractor arrivalTimesInteractor;
    private final BusStopModelMapper busStopModelMapper;
    private final ArrivalTimesModelMapper arrivalTimesModelMapper;

    private BusStopDetailView busStopDetailView;
    private Integer busStopNumber;

    @Inject public BusStopDetailPresenter(BusStopDetailInteractor busStopDetailInteractor, ArrivalTimesInteractor arrivalTimesInteractor, BusStopModelMapper busStopModelMapper, ArrivalTimesModelMapper arrivalTimesModelMapper) {
        this.busStopDetailInteractor = busStopDetailInteractor;
        this.arrivalTimesInteractor = arrivalTimesInteractor;
        this.busStopModelMapper = busStopModelMapper;
        this.arrivalTimesModelMapper = arrivalTimesModelMapper;
    }

    public void initialize(BusStopDetailView busStopDetailView, Integer busStopNumber) {
        this.busStopDetailView = busStopDetailView;
        this.busStopNumber = busStopNumber;
        this.loadBusStopDetails();
        this.loadArrivalTimes();
    }

    private void loadBusStopDetails() {
        busStopDetailView.showArrivals();
        busStopDetailInteractor.loadBusStopDetail(busStopNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(busStopModelMapper::transform)
                .subscribe(busStopModel -> busStopDetailView.renderDetails(busStopModel));
    }

    private void loadArrivalTimes() {
        arrivalTimesInteractor.loadArrivals(busStopNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(arrivalTimesModelMapper::transform)
                .subscribe(arrivalModel -> renderArrival(arrivalModel),
                        error -> showViewConnectionError(error));
    }

    private void showViewConnectionError(Throwable error) {
        busStopDetailView.hideArrivals();
        busStopDetailView.showConnectionError();
    }

    private void renderArrival(ArrivalTimesModel arrivalModel) {
        busStopDetailView.hideConnectionError();
        busStopDetailView.updateArrival(arrivalModel);
    }

    public void retry() {
        busStopDetailView.hideConnectionError();
        this.loadBusStopDetails();
    }
}
