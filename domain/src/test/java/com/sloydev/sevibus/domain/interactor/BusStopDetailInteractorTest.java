package com.sloydev.sevibus.domain.interactor;

import com.sloydev.sevibus.domain.BusStop;
import com.sloydev.sevibus.domain.SpyCallback;
import com.sloydev.sevibus.domain.TestInteractorHandler;
import com.sloydev.sevibus.domain.exception.SevibusException;
import com.sloydev.sevibus.domain.repository.BusStopRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class BusStopDetailInteractorTest {

    private static final Integer BUS_STOP_NUMBER = 50;

    private BusStopDetailInteractor busStopDetailInteractor;
    @Mock private BusStopRepository busStopRepository;
    @Spy SpyCallback<BusStop> spyCallback;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        InteractorHandler interactorHandler = new TestInteractorHandler();
        busStopDetailInteractor = new BusStopDetailInteractor(busStopRepository, interactorHandler);
    }


    @Test
    public void busStopSentThroughCallbackWhenReturnedByRepository() {
        setupRepositoryReturnsValidBusStop();

        busStopDetailInteractor.loadBusStopDetail(BUS_STOP_NUMBER, spyCallback);
        BusStop busStopSent = spyCallback.results.get(0);

        assertThat(busStopSent).isNotNull();
        assertThat(busStopSent.getNumber()).isEqualTo(BUS_STOP_NUMBER);
    }


    @Test(expected = SevibusException.class)
    public void errorThrownWhenNotFound() {
        setupRepositoryNotReturnBusStop();
        busStopDetailInteractor.loadBusStopDetail(BUS_STOP_NUMBER, spyCallback);
    }

    private void setupRepositoryReturnsValidBusStop() {
        when(busStopRepository.getBusStopByNumber(BUS_STOP_NUMBER)).thenReturn(busStop());
    }

    private void setupRepositoryNotReturnBusStop() {
        when(busStopRepository.getBusStopByNumber(BUS_STOP_NUMBER)).thenReturn(null);
    }

    private BusStop busStop() {
        BusStop busStop = new BusStop();
        busStop.setNumber(BUS_STOP_NUMBER);
        return busStop;
    }


}
