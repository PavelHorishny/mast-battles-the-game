package org.example.services.map;

public final class MapProcessor implements MapService {

    private static volatile MapProcessor instance;

    private MapProcessor() {}

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
}
