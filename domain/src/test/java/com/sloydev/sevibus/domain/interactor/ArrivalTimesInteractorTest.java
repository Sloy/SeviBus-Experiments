package com.sloydev.sevibus.domain.interactor;

import com.sloydev.sevibus.domain.ArrivalTimes;
import com.sloydev.sevibus.domain.BusLine;
import com.sloydev.sevibus.domain.SpyCallback;
import com.sloydev.sevibus.domain.TestException;
import com.sloydev.sevibus.domain.TestInteractorHandler;
import com.sloydev.sevibus.domain.exception.SevibusException;
import com.sloydev.sevibus.domain.repository.ArrivalTimesRepository;
import com.sloydev.sevibus.domain.repository.BusLineRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArrivalTimesInteractorTest {

    private static final Integer ANY_STOP_NUMBER = 1;
    private static final String ANY_LINE_NAME = "line";

    @Mock private BusLineRepository busLineRepository;
    @Mock private ArrivalTimesRepository arrivalsRepository;
    @Mock Interactor.CompleteCallback completeCallback;
    @Mock Interactor.ErrorCallback errorCallback;
    @Spy SpyCallback<ArrivalTimes> spyCallback = new SpyCallback<>();

    private ArrivalTimesInteractor interactor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        TestInteractorHandler interactorHandler = new TestInteractorHandler();
        interactor = new ArrivalTimesInteractor(busLineRepository, arrivalsRepository, interactorHandler);
    }

    @Test
    public void testCallbackErrorWhenArrivalsRepositoryFails() throws Exception {
        when(busLineRepository.getBusLinesFromStop(ANY_STOP_NUMBER)).thenReturn(singleBusLinesList());
        when(arrivalsRepository.getArrivalsForBusStopAndLine(anyInt(), anyString())).thenThrow(new TestException());

        interactor.loadArrivals(ANY_STOP_NUMBER, spyCallback, errorCallback, completeCallback);

        verify(errorCallback).onError(any(SevibusException.class));
    }

    @Test
    public void testEmptyArrivalReceivedBeforeRealOne() throws Exception {
        when(busLineRepository.getBusLinesFromStop(ANY_STOP_NUMBER)).thenReturn(singleBusLinesList());
        when(arrivalsRepository.getArrivalsForBusStopAndLine(ANY_STOP_NUMBER, ANY_LINE_NAME)).thenReturn(new ArrivalTimes());

        interactor.loadArrivals(ANY_STOP_NUMBER, spyCallback, errorCallback, completeCallback);
        ArrivalTimes emptyArrival = spyCallback.results.get(0);

        assertThat(emptyArrival.getNextBus()).isNull();
    }

    @Test
    public void testArrivalsReceivedForAllLinesAfterEmpty() throws Exception {

    }

    @Test
    public void testCallbackCompletedWhenAllLinesReceived() throws Exception {
        when(busLineRepository.getBusLinesFromStop(ANY_STOP_NUMBER)).thenReturn(singleBusLinesList());
        when(arrivalsRepository.getArrivalsForBusStopAndLine(ANY_STOP_NUMBER, ANY_LINE_NAME)).thenReturn(new ArrivalTimes());

        interactor.loadArrivals(ANY_STOP_NUMBER, spyCallback, errorCallback, completeCallback);

        verify(completeCallback).onCompleted();
    }

    @Test
    public void testEmptyArrivalsAreLoading() throws Exception {
        when(busLineRepository.getBusLinesFromStop(ANY_STOP_NUMBER)).thenReturn(singleBusLinesList());

        interactor.loadArrivals(ANY_STOP_NUMBER, spyCallback, errorCallback, completeCallback);
        ArrivalTimes emptyArrival = spyCallback.results.get(0);

        assertThat(emptyArrival.isLoading()).isTrue();
    }

    private List<BusLine> singleBusLinesList() {
        return Arrays.asList(new BusLine());
    }
}
