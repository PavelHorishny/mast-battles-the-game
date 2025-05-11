package org.example.services.unit;

import org.example.FleetSettings;
import org.example.game_elements.*;
import org.example.services.name.NameProcessor;

import java.util.ArrayList;

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
        ArrayList<Unit> firstFleet = new ArrayList<>();
        firstFleet.addAll(createFleet(settings, true));
        firstFleet.addAll(createFleet(settings, false));

        firstFleet.forEach(unit -> NameProcessor.getInstance().getName(unit));
        firstFleet.forEach(unit -> System.out.println(unit.toString()));
        System.out.println(firstFleet.size());
        System.out.println("Setting up all units with the provided settings.");
    }
    private ArrayList<Unit> createFleet(FleetSettings settings, boolean firstPlayer) {

        ArrayList<Unit> fleet = new ArrayList<>();
        for (FortificationType fortificationType : settings.getFortifications()) {
            fleet.add(UnitFactory.create(firstPlayer, fortificationType));
        }
        for (VesselType vesselType : settings.getVessels()) {
            fleet.add(UnitFactory.create(firstPlayer, vesselType));
        }
        return fleet;
    }
}