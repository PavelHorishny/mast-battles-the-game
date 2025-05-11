package org.example.services.map;

import org.example.map.Surface;

public interface MapService {
    Surface [] [] getMap();
    void initializeStandardMap();

}
