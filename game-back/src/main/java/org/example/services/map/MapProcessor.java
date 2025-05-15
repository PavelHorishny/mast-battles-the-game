package org.example.services.map;

import org.example.game_elements.Fortification;
import org.example.game_elements.Unit;
import org.example.map.Coordinates;
import org.example.map.Surface;
import org.example.map.SurfaceType;
import org.example.mockedData.MockedData;

import java.util.ArrayList;

public final class MapProcessor implements MapService {

    private static volatile MapProcessor instance;
    private Surface [][] map = new Surface[34][32];

    private MapProcessor() {
    }

    public static MapProcessor getInstance() {
        MapProcessor result = instance;
        if (result != null) {
            return result;
        }

        synchronized (MapProcessor.class) {
            if (instance == null) {
                instance = new MapProcessor();
            }
            return instance;
        }
    }

    @Override
    public void initializeStandardMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Surface(new Coordinates(i, j), SurfaceType.WATER);
            }
        }
        MockedData.LAND.forEach(coordinates -> map[coordinates.axisX()][coordinates.axisY()] = new Surface(coordinates, SurfaceType.LAND));
    }

    @Override
    public void placeUnitsOnMap(ArrayList<Unit> fleet) {
        fleet.forEach(unit -> {
            switch (unit.getUnitType()){
                case FORTIFICATION -> placeFortificationOnMap(unit);
                case VESSEL -> placeVesselOnMap(unit);
            }
        });
    }

    private void placeVesselOnMap(Unit unit) {

    }

    private void placeFortificationOnMap(Unit unit) {
        Fortification fortification = (Fortification) unit;
        switch (fortification.getFortificationType()){
            case FIRST_LINE_FORT -> {
                if(fortification.isFirstPlayer()){
                     MockedData.FIRST_PLAYER_FIRST_LINE_FORTS_POSITIONS.stream().filter(coordinates ->
                                    isSurfaceEmpty(coordinates) && isSurfaceLand(coordinates))
                            .findFirst().ifPresent(c-> placeFortificationOnSurface(fortification,c));
                }else {
                    MockedData.SECOND_PLAYER_FIRST_LINE_FORTS_POSITIONS.stream().filter(coordinates ->
                                    isSurfaceEmpty(coordinates) && isSurfaceLand(coordinates))
                            .findFirst().ifPresent(c-> placeFortificationOnSurface(fortification,c));
                }
            }
            case SECOND_LINE_FORT -> {
               // Coordinates c;
                if(fortification.isFirstPlayer()){
                     MockedData.FIRST_PLAYER_SECOND_LINE_FORTS_POSITIONS.stream().filter(coordinates ->
                                    isSurfaceEmpty(coordinates) && isSurfaceLand(coordinates))
                            .findFirst().ifPresent(c-> placeFortificationOnSurface(fortification,c));
                }else {
                    MockedData.SECOND_PLAYER_SECOND_LINE_FORTS_POSITIONS.stream().filter(coordinates ->
                                    isSurfaceEmpty(coordinates) && isSurfaceLand(coordinates))
                            .findFirst().ifPresent(coordinates -> placeFortificationOnSurface(fortification,coordinates));
                }
            }
            case ROYAL_PORT -> {
                if(fortification.isFirstPlayer()){
                    for(Coordinates coordinates : MockedData.FIRST_PLAYER_ROYAL_PORT_POSITIONS){
                        if(isSurfaceEmpty(coordinates)){
                            if(isSurfaceLand(coordinates)){
                                placeFortificationOnSurface(fortification,coordinates);
                            }
                        }
                    }
                }else {
                    for(Coordinates coordinates : MockedData.SECOND_PLAYER_ROYAL_PORT_POSITIONS){
                        if(isSurfaceEmpty(coordinates)){
                            if(isSurfaceLand(coordinates)){
                                placeFortificationOnSurface(fortification,coordinates);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Surface[][] getMap() {
        return map;
    }
    public boolean isSurfaceEmpty(Coordinates coordinates){
        return map[coordinates.axisX()][coordinates.axisY()].isEmpty();
    }
    public boolean isSurfaceLand(Coordinates coordinates){
        return map[coordinates.axisX()][coordinates.axisY()].getType() == SurfaceType.LAND;
    }
    public void placeFortificationOnSurface(Fortification fortification, Coordinates coordinates){
        map[coordinates.axisX()][coordinates.axisY()].setUnit(fortification);
        fortification.place(coordinates);
    }
}
