package com.sloydev.sevibus.presentation.presenter;

import com.sloydev.sevibus.domain.NearbyCard;
import com.sloydev.sevibus.domain.interactor.GetCardListInteractor;
import com.sloydev.sevibus.domain.interactor.Interactor;
import com.sloydev.sevibus.presentation.model.NearbyCardModel;
import com.sloydev.sevibus.presentation.model.mapper.NearbyCardModelMapper;
import com.sloydev.sevibus.presentation.view.CardListView;
import java.util.List;
import javax.inject.Inject;

public class CardListPresenter {

    private final GetCardListInteractor getCardListInteractor;
    private final NearbyCardModelMapper nearbyCardModelMapper;

    private CardListView cardListView;

    @Inject
    public CardListPresenter(GetCardListInteractor getCardListInteractor, NearbyCardModelMapper nearbyCardModelMapper) {
        this.getCardListInteractor = getCardListInteractor;
        this.nearbyCardModelMapper = nearbyCardModelMapper;
    }

    protected void setView(CardListView cardListView) {
        this.cardListView = cardListView;
    }

    public void initialize(CardListView cardListView) {
        this.setView(cardListView);
        this.loadCards();
    }

    protected void loadCards() {
        getCardListInteractor.loadCards(new Interactor.Callback<List<NearbyCard>>() {
            @Override public void onLoaded(List<NearbyCard> nearbyCards) {
                List<NearbyCardModel> cardModels = nearbyCardModelMapper.transform(nearbyCards);
                cardListView.setCards(cardModels);
            }
        });
    }
}
