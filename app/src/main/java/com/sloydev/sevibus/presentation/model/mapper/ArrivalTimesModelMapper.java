package com.sloydev.sevibus.presentation.model.mapper;

import android.app.Application;

import com.sloydev.sevibus.R;
import com.sloydev.sevibus.domain.ArrivalTimes;
import com.sloydev.sevibus.presentation.model.ArrivalTimesModel;

import javax.inject.Inject;

public class ArrivalTimesModelMapper {

    private final String nextTimeEstimationFormat;
    private final String secondTimeEstimationFormat;
    private final String imminentArrivalText;
    private final String noEstimationText;
    private final String notAvailableText;
    private final String loadingText;

    @Inject public ArrivalTimesModelMapper(Application application) {
        nextTimeEstimationFormat = application.getString(R.string.arrival_next_estimate_format);
        secondTimeEstimationFormat = application.getString(R.string.arrival_second_estimate_format);
        imminentArrivalText = application.getString(R.string.arrival_imminent);
        noEstimationText = application.getString(R.string.arrival_no_estimation);
        notAvailableText = application.getString(R.string.arrival_not_available);
        loadingText = application.getString(R.string.arrival_loading);
    }

    public ArrivalTimesModel transform(ArrivalTimes arrivalTimes) {
        ArrivalTimesModel arrivalTimesModel = new ArrivalTimesModel();
        arrivalTimesModel.setLineName(arrivalTimes.getBusLineName());

        if (arrivalTimes.isLoading()) {
            setLoadingValues(arrivalTimes, arrivalTimesModel);
        }else if (arrivalTimes.isAvailable()) {
            //TODO distance
            setAvailableValues(arrivalTimes, arrivalTimesModel);
        } else {
            setNotAvailableValues(arrivalTimesModel);
        }

        return arrivalTimesModel;
    }

    private void setLoadingValues(ArrivalTimes arrivalTimes, ArrivalTimesModel arrivalTimesModel) {
        arrivalTimesModel.setNextBusTime(loadingText);
        arrivalTimesModel.setLoading(true);
    }

    private void setAvailableValues(ArrivalTimes arrivalTimes, ArrivalTimesModel arrivalTimesModel) {
        ArrivalTimes.BusArrival nextBus = arrivalTimes.getNextBus();
        arrivalTimesModel.setNextBusTime(nextBusTimeText(nextBus));

        ArrivalTimes.BusArrival secondBus = arrivalTimes.getSecondBus();
        arrivalTimesModel.setSecondBusTime(secondBusEstimateTime(secondBus));
    }

    private void setNotAvailableValues(ArrivalTimesModel arrivalTimesModel) {
        arrivalTimesModel.setNextBusTime(notAvailableText);
    }

    private String nextBusTimeText(ArrivalTimes.BusArrival nextBus) {
        return genericBusTimeText(nextBus, nextTimeEstimationFormat);
    }

    private String secondBusEstimateTime(ArrivalTimes.BusArrival secondBus) {
        return genericBusTimeText(secondBus, secondTimeEstimationFormat);
    }

    private String genericBusTimeText(ArrivalTimes.BusArrival nextBus, String estimateFormat) {
        switch (nextBus.getStatus()) {
            case ESTIMATE:
                return String.format(estimateFormat, nextBus.getTimeInMinutes());
            case IMMINENT:
                return imminentArrivalText;
            case NO_ESTIMATION:
                return noEstimationText;
        }
        return null;
    }
}
