package com.sloydev.sevibus.domain.interactor;

import com.sloydev.sevibus.domain.BusStop;
import com.sloydev.sevibus.domain.NearbyCard;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class GetCardListInteractor implements Interactor {

    private final InteractorHandler interactorHandler;
    private Callback<List<NearbyCard>> callback;

    @Inject public GetCardListInteractor(InteractorHandler interactorHandler) {
        this.interactorHandler = interactorHandler;
    }

    public void loadCards(Callback<List<NearbyCard>> callback) {
        this.callback = callback;
        interactorHandler.execute(this);
    }

    @Override public void run() {
        // TODO real implementation
        List<NearbyCard> nearbyCards = new ArrayList<>();
        nearbyCards.add(dummyCard(24, 50, "Av. Cruz Roja (Clínica)"));
        nearbyCards.add(dummyCard(128, 51, "Av. Cruz Roja (Antonio Machín)"));
        nearbyCards.add(dummyCard(532, 62, "Reina Mercedes (E.S.I. Informática"));
        notifyResult(nearbyCards);
    }

    private void notifyResult(final List<NearbyCard> nearbyCards) {
        interactorHandler.postResponse(new Interactor() {
            @Override public void run() {
                callback.onLoaded(nearbyCards);
            }
        });
    }

    private NearbyCard dummyCard(int distance, int number, String name) {
        BusStop busStop = new BusStop();
        busStop.setName(name);
        busStop.setNumber(number);
        NearbyCard nearbyCard = new NearbyCard();
        nearbyCard.setBusStop(busStop);
        nearbyCard.setDistance(distance);
        return nearbyCard;
    }
}
