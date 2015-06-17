package com.sloydev.sevibus.presentation.presenter;

import com.sloydev.sevibus.domain.NearbyCard;
import com.sloydev.sevibus.domain.interactor.GetCardListInteractor;
import com.sloydev.sevibus.domain.interactor.Interactor;
import com.sloydev.sevibus.presentation.model.mapper.NearbyCardModelMapper;
import com.sloydev.sevibus.presentation.view.CardListView;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class CardListPresenterTest {

    @Mock GetCardListInteractor getCardListInteractor;
    @Mock CardListView cardListView;

    private CardListPresenter presenter;

    @Before public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        NearbyCardModelMapper nearbyCardModelMapper = new NearbyCardModelMapper();
        presenter = new CardListPresenter(getCardListInteractor, nearbyCardModelMapper);
        presenter.setView(cardListView);
    }

    @Test public void shouldSetCardsInViewWhenCallbackReceived() throws Exception {
        setupInteractorCallbacks(stubCards());

        presenter.loadCards();

        verify(cardListView).setCards(anyList());
    }

    private List<NearbyCard> stubCards() {
        return Collections.emptyList();
    }

    private Interactor.Callback anyCallback() {
        return any(Interactor.Callback.class);
    }

    private void setupInteractorCallbacks(final List<NearbyCard> cards) {
        doAnswer(new Answer() {
            @Override public Object answer(InvocationOnMock invocation) throws Throwable {
                Interactor.Callback<List<NearbyCard>> callback =
                  (Interactor.Callback<List<NearbyCard>>) invocation.getArguments()[0];
                callback.onLoaded(cards);
                return null;
            }
        }).when(getCardListInteractor).loadCards(anyCallback());
    }
}