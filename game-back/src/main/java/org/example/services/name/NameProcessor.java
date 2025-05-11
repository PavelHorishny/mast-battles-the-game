package org.example.services.name;

import org.example.game_elements.Fortification;
import org.example.game_elements.Unit;
import org.example.game_elements.Vessel;
import org.example.mockedData.MockedData;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class NameProcessor implements NameService {

    private static volatile NameProcessor instance;

    private final Stack<String> royalPortNames = new Stack<>();
    private final Stack <String> fortificationsNames = new Stack<>();
    private final Stack <String> bigBattleshipsNames = new Stack<>();
    private final Stack <String> smallBattleshipsNames = new Stack<>();
    private final Stack <String> frigatesNames = new Stack<>();
    private final Stack <String> tendersNames = new Stack<>();
    private final Stack <String> brigNames = new Stack<>();
    private final Stack <String> galleonsNames = new Stack<>();
    private final Stack <String> steamFrigateNames = new Stack<>();
    private final Stack <String> navalBatteryNames = new Stack<>();
    private final Stack <String> galleysName = new Stack<>();
    private final Stack <String> steamCorvetteNames = new Stack<>();
    private final Stack <String> monitorNames = new Stack<>();
    private final Stack <String> steamshipNames = new Stack<>();

    private NameProcessor() {
        // private constructor to prevent instantiation
    }

    public static NameProcessor getInstance() {
        NameProcessor result = instance;
        if (result != null) {
            return result;
        }
        synchronized (NameProcessor.class) {
            if (instance == null) {
                instance = new NameProcessor();
            }
            return instance;
        }
    }

    @Override
    public void generateRandomListName() {
        royalPortNames.addAll(randomizeNames(MockedData.ROYAL_PORTS_NAME));
        fortificationsNames.addAll(randomizeNames(MockedData.FORTIFICATIONS_NAMES));
        bigBattleshipsNames.addAll(randomizeNames(MockedData.THREE_DECKER_SHIP_OF_LINE_NAMES));
        smallBattleshipsNames.addAll(randomizeNames(MockedData.TWO_DECKER_SHIPS_OF_THE_LINE_NAMES));
        frigatesNames.addAll(randomizeNames(MockedData.FRIGATE_NAMES));
        tendersNames.addAll(randomizeNames(MockedData.TENDER_NAMES));
        brigNames.addAll(randomizeNames(MockedData.BRIG_NAMES));
        galleonsNames.addAll(randomizeNames(MockedData.GALLEON_NAMES));
        steamFrigateNames.addAll(randomizeNames(MockedData.STEAM_FRIGATE_NAMES));
        navalBatteryNames.addAll(randomizeNames(MockedData.NAVAL_BATTERY_NAMES));
        galleysName.addAll(randomizeNames(MockedData.GALLEY_NAMES));
        steamCorvetteNames.addAll(randomizeNames(MockedData.STEAM_CORVETTE_NAMES));
        monitorNames.addAll(randomizeNames(MockedData.MONITOR_NAMES));
        steamshipNames.addAll(randomizeNames(MockedData.STEAMSHIP_NAMES));
    }

    @Override
    public void getName(Unit unit) {
        switch (unit.getUnitType()) {
            case VESSEL -> {
                Vessel vessel = (Vessel) unit;
                switch (vessel.getVesselType()){
                    case THREE_DECKER_SHIP_OF_LINE -> vessel.setId(bigBattleshipsNames.pop());
                    case TWO_DECKER_SHIP_OF_LINE -> vessel.setId(smallBattleshipsNames.pop());
                    case FRIGATE -> vessel.setId(frigatesNames.pop());
                    case TENDER -> vessel.setId(tendersNames.pop());
                    case BRIG -> vessel.setId(brigNames.pop());
                    case GALLEON -> vessel.setId(galleonsNames.pop());
                    case STEAM_FRIGATE -> vessel.setId(steamFrigateNames.pop());
                    case BATTERY -> vessel.setId(navalBatteryNames.pop());
                    case GALLEY -> vessel.setId(galleysName.pop());
                    case CORVETTE -> vessel.setId(steamCorvetteNames.pop());
                    case MONITOR -> vessel.setId(monitorNames.pop());
                    case STEAMSHIP -> vessel.setId(steamshipNames.pop());
                }
            }
            case FORTIFICATION -> {
                Fortification fortification = (Fortification) unit;
                switch (fortification.getFortificationType()){
                    case ROYAL_PORT -> fortification.setId(royalPortNames.pop());
                    case FIRST_LINE_FORT, SECOND_LINE_FORT -> fortification.setId(fortificationsNames.pop());
                }
            }
        }

    }

    private Stack <String> randomizeNames(List<String> nameList) {
        Stack<String> randomizedNames = new Stack<>();
        List<String> copyList = new ArrayList<>(nameList);
        int size = copyList.size();
        for (int i = 0; i < size; i++) {
            int randomIndex = (int) (Math.random() * copyList.size());
            randomizedNames.push(copyList.get(randomIndex));
            copyList.remove(randomIndex);
        }
        return randomizedNames;
    }
}
