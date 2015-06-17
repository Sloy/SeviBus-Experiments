package com.sloydev.sevibus.presentation.model.mapper;

import com.sloydev.sevibus.domain.NearbyCard;
import com.sloydev.sevibus.presentation.model.NearbyCardModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class NearbyCardModelMapper {

    @Inject public NearbyCardModelMapper() {
    }

    public NearbyCardModel transform(NearbyCard nearbyCard) {
        NearbyCardModel nearbyCardModel = new NearbyCardModel();
        nearbyCardModel.setDistance(nearbyCard.getDistance());
        nearbyCardModel.setBusStopNumber(nearbyCard.getBusStop().getNumber());
        return nearbyCardModel;
    }

    public List<NearbyCardModel> transform(List<NearbyCard> nearbyCards) {
        List<NearbyCardModel> models = new ArrayList<>(nearbyCards.size());
        for (NearbyCard nearbyCard : nearbyCards) {
            models.add(transform(nearbyCard));
        }
        return models;
    }
}
