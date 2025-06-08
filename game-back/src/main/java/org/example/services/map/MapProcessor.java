package org.example.services.map;

import org.example.game_elements.Fortification;
import org.example.game_elements.Unit;
import org.example.map.CardinalPoint;
import org.example.map.Coordinates;
import org.example.map.Surface;
import org.example.map.SurfaceType;
import org.example.mockedData.MockedData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import static java.util.stream.Collectors.groupingBy;

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
                //case VESSEL -> placeVesselOnMap(unit);
            }
        });
    }
    @Override
    public void placeVesselOnMap(Stack<Unit> vessels, ArrayList<Unit> fortifications) {
         /*   fortifications.stream().filter(unit -> unit.getUnitType().equals(UnitType.FORTIFICATION))
                    .map(Fortification.class::cast)
                    .filter(unit -> unit.getFortificationType().equals(FortificationType.SECOND_LINE_FORT))
                    .forEach(unit -> System.out.println(unit.toString()));*/
/*            fortifications.stream().map(Fortification.class::cast)
                    .collect(groupingBy(Fortification::getFortificationType,collectingAndThen(Collectors.toList(),list -> {
                        list.forEach(unit->{
                                        System.out.println("From placeVesselOnMap: ");
                                System.out.println(unit.toString());
                        });
                        return null;
                    })));*/
        fortifications.stream()
                .filter(f -> f instanceof Fortification)
                .map(Fortification.class::cast)
                .collect(groupingBy(Fortification::getFortificationType))
                .forEach((type, list) -> {
                    list.forEach(unit -> {
                        System.out.println("From placeVesselOnMap: ");
                        System.out.println(unit);
                        System.out.println("Port size: " + unit.getPort().size());
                    });
                });
        fortifications.stream().filter(f-> f instanceof Fortification).map(Fortification.class::cast).forEach(fortification -> placeVesselInPort(fortification,vessels));

    }
    private void placeVesselInPort(Fortification fortification, Stack<Unit> vessels) {
       switch (fortification.getFortificationType()){
           case FIRST_LINE_FORT -> {
               //add 4 vessels to each port
               for (int i = 0;i<4;i++){
                   fortification.getPort().get(i).setUnit(vessels.pop());
               }
           }
           case SECOND_LINE_FORT -> {
               int size = fortification.getPort().size()>5 ? 6 : 5;
               for (int i = 0; i<size;i++){
                   fortification.getPort().get(i).setUnit(vessels.pop());
               }
           }
       }
    }
    @Override
    public void placeFortificationOnMap(Unit unit) {
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
    public boolean isSurfaceWater(Coordinates coordinates){
        return map[coordinates.axisX()][coordinates.axisY()].getType() == SurfaceType.WATER;
    }
    public void placeFortificationOnSurface(Fortification fortification, Coordinates coordinates){
        map[coordinates.axisX()][coordinates.axisY()].setUnit(fortification);
        fortification.place(coordinates);
        fortification.setPort(getPort(fortification));
    }

    public ArrayList<Surface> getPort(Fortification fortification){
        ArrayList <Surface> tmp = new ArrayList<>();

        Arrays.stream(CardinalPoint.cardinalPoints).forEach(cardinalPoint -> {
            Coordinates coordinates = new Coordinates(fortification.getCoordinates().axisX()+cardinalPoint.getValue().axisX(),fortification.getCoordinates().axisY()+cardinalPoint.getValue().axisY());
            if(checkIfPositionIsValid(coordinates)){
                if (isSurfaceWater(coordinates)){
                     map[coordinates.axisX()][coordinates.axisY()].setType(SurfaceType.PORT);
                     tmp.add(map[coordinates.axisX()][coordinates.axisY()]);
                }
            }
        });
        return tmp;
    }

    public boolean checkIfPositionIsValid(Coordinates coordinates) {
        return coordinates.axisX()<map.length && coordinates.axisX()>=0 && coordinates.axisY()<map[0].length && coordinates.axisY()>=0;
    }

}
