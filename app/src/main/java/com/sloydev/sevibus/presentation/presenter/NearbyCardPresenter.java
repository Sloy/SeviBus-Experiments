package com.sloydev.sevibus.presentation.presenter;

import com.sloydev.sevibus.domain.interactor.ArrivalTimesInteractor;
import com.sloydev.sevibus.presentation.model.NearbyCardModel;
import com.sloydev.sevibus.presentation.view.NearbyCardView;
import javax.inject.Inject;

public class NearbyCardPresenter implements Presenter {

    private final ArrivalTimesInteractor arrivalTimesInteractor;

    private NearbyCardModel nearbyCardModel;
    private NearbyCardView nearbyCardView;

    @Inject public NearbyCardPresenter(ArrivalTimesInteractor arrivalTimesInteractor) {
        this.arrivalTimesInteractor = arrivalTimesInteractor;
    }

    public void initialize(NearbyCardView nearbyCardView, NearbyCardModel nearbyCardModel) {
        this.nearbyCardView = nearbyCardView;
        this.nearbyCardModel = nearbyCardModel;
        this.displayBusStopInfo();
        this.loadArrivals();
    }

    private void displayBusStopInfo() {
        nearbyCardView.showNumber(nearbyCardModel.getBusStopNumber());
        nearbyCardView.showDistance(nearbyCardModel.getDistance());
    }

    private void loadArrivals() {
        //TODO
    }
}
