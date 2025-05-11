package org.example.services.map;

import org.example.game_elements.Unit;
import org.example.map.Surface;

import java.util.ArrayList;

public interface MapService {
    Surface [] [] getMap();
    void initializeStandardMap();

    void placeUnitsOnMap(ArrayList<Unit> fleet);
}
