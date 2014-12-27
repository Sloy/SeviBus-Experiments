package com.sloydev.sevibus.presentation;

public class Modules {
    static Object[] list(SevibusApplication app) {
        return new Object[] {
                new SevibusModule(app)
        };
    }

    private Modules() {
        // No instances.
    }
}
