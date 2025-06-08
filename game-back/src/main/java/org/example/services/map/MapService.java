package org.example.services.map;

import org.example.game_elements.Unit;
import org.example.map.Surface;

import java.util.ArrayList;
import java.util.Stack;

public interface MapService {
    Surface [] [] getMap();
    void initializeStandardMap();

    void placeUnitsOnMap(ArrayList<Unit> fleet);

    void placeFortificationOnMap(Unit fortifications);

    void placeVesselOnMap(Stack<Unit> vessels,ArrayList<Unit> fortifications);
}
