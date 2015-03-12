package com.sloydev.sevibus.presentation;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class SevibusApplication extends Application {

    private ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        buildObjectGraphAndInject();
    }

    private void buildObjectGraphAndInject() {
        objectGraph = ObjectGraph.create(Modules.list(this));
        objectGraph.inject(this);
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    /**
     * Injects the members of {@code instance}, including injectable members
     * inherited from its supertypes.
     *
     * @throws IllegalArgumentException  if the runtime type of {@code instance} is
     *     not one of this object graph's {@link dagger.Module#injects injectable types}.
     */
    public void inject(Object o) {
        objectGraph.inject(o);
    }

    public static SevibusApplication get(Context context) {
        return (SevibusApplication) context.getApplicationContext();
    }
}
