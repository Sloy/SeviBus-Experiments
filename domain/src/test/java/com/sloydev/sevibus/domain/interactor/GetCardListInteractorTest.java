package com.sloydev.sevibus.domain.interactor;

import com.sloydev.sevibus.domain.NearbyCard;
import com.sloydev.sevibus.domain.TestInteractorHandler;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class GetCardListInteractorTest {

    @Mock Interactor.Callback<List<NearbyCard>> callback;

    private GetCardListInteractor interactor;

    @Before public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        interactor = new GetCardListInteractor(new TestInteractorHandler());
    }

    @Test public void shouldNotExplodeOrAnything() throws Exception {
        interactor.loadCards(callback);
        assertEquals(true, true);
    }
}