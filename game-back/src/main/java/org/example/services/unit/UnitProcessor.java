package org.example.services.unit;

import org.example.FleetSettings;
import org.example.game_elements.*;
import org.example.services.map.MapProcessor;
import org.example.services.map.MapService;
import org.example.services.name.NameProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public final class UnitProcessor implements UnitService {

    private static final Logger logger = LoggerFactory.getLogger(UnitProcessor.class);
    public static volatile UnitProcessor instance;
    private final MapService mapProcessor;

    private UnitProcessor() {
        // private constructor to prevent instantiation
        this.mapProcessor = MapProcessor.getInstance();
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
        ArrayList<Unit> fleet = new ArrayList<>();
        fleet.addAll(createFleet(settings, true));
        fleet.addAll(createFleet(settings, false));

        fleet.forEach(unit -> NameProcessor.getInstance().getName(unit));
        //mapProcessor.placeUnitsOnMap(fleet);
        //fleet.forEach(unit -> System.out.println(unit.toString()));
        //System.out.println(fleet.size());
        System.out.println("Setting up all units with the provided settings.");
    }
    private ArrayList<Unit> createFleet(FleetSettings settings, boolean firstPlayer) {

        ArrayList<Unit> fortifications = new ArrayList<>();
        for (FortificationType fortificationType : settings.getFortifications()) {
            fortifications.add(UnitFactory.create(firstPlayer, fortificationType));
        }
        fortifications.forEach(mapProcessor::placeFortificationOnMap);

        /**
         * Algorithm to create vessels
         * get standard fleet from settings
         * shuffle it
         * create vessels
         * put a shuffled list to stack
         * place vessels on a map
         * add a vessel to port*/
        //Collections.shuffle(Arrays.asList(settings.getVessels()));
        ArrayList<VesselType> vesselTypes = new ArrayList<>(Arrays.asList(settings.getVessels()));
        Collections.shuffle(vesselTypes);
        Stack<Unit> vesselsStack = new Stack<>();
        ArrayList<Unit> vessels = new ArrayList<>();

        for(VesselType vesselType : vesselTypes) {
            Unit vessel = UnitFactory.create(firstPlayer, vesselType);
            vessels.add(vessel);
            vesselsStack.push(vessel);
        }
        mapProcessor.placeVesselOnMap(vesselsStack, fortifications);
        //vessels.forEach(unit -> logger.debug(unit.toString()));
        //TODO : add logging


      /*  for (VesselType vesselType : settings.getVessels()) {
            vessels.add(UnitFactory.create(firstPlayer, vesselType));
        }
        mapProcessor.placeVesselOnMap(shufflePlayersVessels(vessels),fortifications);
        fleet.addAll(shufflePlayersVessels(vessels));*/
        return new ArrayList<>(fortifications);
    }
    private Stack <Unit> shufflePlayersVessels(ArrayList<Unit> vessels){
        Stack <Unit> fleet = new Stack<>();
        int end = vessels.size();
        while (fleet.size()<end){
            int x = (int) (Math.random()*(vessels.size()));
            fleet.push(vessels.get(x));
            vessels.remove(x);
        }
        return fleet;
    }
}