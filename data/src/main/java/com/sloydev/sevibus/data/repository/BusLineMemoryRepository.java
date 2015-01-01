package com.sloydev.sevibus.data.repository;

import android.util.SparseArray;

import com.sloydev.sevibus.domain.BusLine;
import com.sloydev.sevibus.domain.repository.BusLineRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class BusLineMemoryRepository implements BusLineRepository {

    private final SparseArray<List<BusLine>> stopLineMap;
    private final List<BusLine> busLineList;

    @Inject public BusLineMemoryRepository() {
        stopLineMap = new SparseArray<>();
        busLineList = new ArrayList<>();
        initializeMockData();
    }

    @Override public List<BusLine> getAllBusLines() {
        return new ArrayList<>(busLineList);
    }

    @Override public List<BusLine> getBusLinesFromStop(Integer busStopNumber) {
        List<BusLine> busLines = stopLineMap.get(busStopNumber);
        if (busLines != null) {
            return busLines;
        } else {
            return new ArrayList<>(0);
        }
    }

    private void initializeMockData() {
        BusLine L01 = new BusLine();
        L01.setId(1);
        L01.setName("01");
        L01.setDescription("Pgno.Norte-Glorieta Plus Ultra");

        BusLine L11 = new BusLine();
        L11.setId(11);
        L11.setName("11");

        BusLine L12 = new BusLine();
        L12.setId(12);
        L12.setName("12");

        BusLine L16 = new BusLine();
        L16.setId(16);
        L16.setName("16");

        BusLine LA1 = new BusLine();
        LA1.setId(101);
        LA1.setName("A1");

        busLineList.add(L01);
        busLineList.add(L11);
        busLineList.add(L12);
        busLineList.add(L16);
        busLineList.add(LA1);
        stopLineMap.put(50, Arrays.asList(L01, L11, L12, L16, LA1));
        stopLineMap.put(62, Arrays.asList(L01, LA1));
    }
}
