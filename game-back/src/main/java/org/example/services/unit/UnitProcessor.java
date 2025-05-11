package org.example.services.unit;

import org.example.FleetSettings;
import org.example.game_elements.*;

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

/*        FortificationType[] tmp = settings.getFortifications();
        VesselType [] tmp2 = settings.getVessels();
        for (FortificationType fortificationType : tmp) {
            firstFleet.add(UnitFactory.create(true, fortificationType));
            //firstFleet.add(FortificationFactory.createFortification(fortificationType));
        }
        for (VesselType vesselType : tmp2) {
            firstFleet.add(UnitFactory.create(true, vesselType));
            //firstFleet.add(VesselFactory.createVessel(vesselType));
        }*/

        // Implementation for setting up all units
        // This is a placeholder implementation
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