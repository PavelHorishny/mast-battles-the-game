package org.example.services.map;

import org.example.map.Coordinates;
import org.example.map.Surface;
import org.example.map.SurfaceType;
import org.example.mockedData.MockedData;

public final class MapProcessor implements MapService {

    private static volatile MapProcessor instance;
    private Surface [][] map = new Surface[34][32];

    private MapProcessor() {
        initializeMap(); // temp action
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

    private void initializeMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Surface(new Coordinates(i, j), SurfaceType.WATER);
            }
        }
        MockedData.LAND.forEach(coordinates -> map[coordinates.axisX()][coordinates.axisY()] = new Surface(coordinates, SurfaceType.LAND));
    }

    @Override
    public Surface[][] getMap() {
        return map;
    }
}
