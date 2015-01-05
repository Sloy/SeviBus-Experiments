package com.sloydev.sevibus.data.dagger;

import com.sloydev.sevibus.data.repository.TussamArrivalTimesRepository;
import com.sloydev.sevibus.data.repository.TussamArrivalsSaxHandler;

import javax.inject.Singleton;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                TussamArrivalTimesRepository.class,
        }
)
public class TussamModule {

    @Provides @Singleton SAXParser provideSaxParser() {
        try {
            return SAXParserFactory.newInstance().newSAXParser();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Provides @Singleton TussamArrivalsSaxHandler provideArrivalsSaxHandler() {
        return new TussamArrivalsSaxHandler();
    }
}
