package com.sloydev.sevibus.presentation.view;

import com.sloydev.sevibus.presentation.model.ArrivalTimesModel;
import com.sloydev.sevibus.presentation.model.BusStopModel;

public interface BusStopDetailView {
    void renderDetails(BusStopModel busStopModel);

    void updateArrival(ArrivalTimesModel arrival);

    void showArrivals();

    void clearArrivals();

    void hideArrivals();

    void showConnectionError();

    void hideConnectionError();

    void showLoading();

    void hideLoading();
}
