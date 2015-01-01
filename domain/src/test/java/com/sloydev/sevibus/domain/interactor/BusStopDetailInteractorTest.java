package com.sloydev.sevibus.domain.interactor;

import com.sloydev.sevibus.domain.BusStop;
import com.sloydev.sevibus.domain.exception.BusStopNotFoundException;
import com.sloydev.sevibus.domain.repository.BusStopRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.when;

public class BusStopDetailInteractorTest {

    private static final Integer BUS_STOP_NUMBER = 50;

    private BusStopDetailInteractor busStopDetailInteractor;
    @Mock private BusStopRepository busStopRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        busStopDetailInteractor = new BusStopDetailInteractor(busStopRepository);
    }
    

    @Test
    public void busStopReturnedWhenFound() {
        setupRepositoryReturnsValidBusStop();
        busStopDetailInteractor.loadBusStopDetail(BUS_STOP_NUMBER)
                .subscribe(busStop -> {
                    assertThat(busStop.getNumber()).isEqualTo(BUS_STOP_NUMBER);
                });
    }


    @Test
    public void errorCalledWhenNotFound() {
        setupRepositoryNotReturnBusStop();
        busStopDetailInteractor.loadBusStopDetail(BUS_STOP_NUMBER)
                .subscribe(busStop -> {
                    fail("onNext should not be called when bus stop is not found");
                }, error -> {
                    assertThat(error).isExactlyInstanceOf(BusStopNotFoundException.class);
                });
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
