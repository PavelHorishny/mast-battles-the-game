package org.example.services.unit;

import org.example.game_elements.FleetSettings;

public final class UnitProcessor implements UnitService {
    public static volatile UnitProcessor instance;

    private UnitProcessor() {
        // private constructor to prevent instantiation
    }

    public static UnitProcessor getInstance() {
        if (instance == null) {
            synchronized (UnitProcessor.class) {
                if (instance == null) {
                    instance = new UnitProcessor();
                }
            }
        }
        return instance;
    }

    @Override
    public void setUpAllUnits(FleetSettings settings) {

    }
}
