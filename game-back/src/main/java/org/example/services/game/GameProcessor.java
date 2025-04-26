package org.example.services.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.State;
import org.example.services.map.MapProcessor;
import org.example.services.map.MapService;

public final class GameProcessor implements GameService {

    private static volatile GameProcessor instance;
    private static final MapService mapProcessor = MapProcessor.getInstance();
    private final State state = new State();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private GameProcessor() {

    }

    public static GameProcessor getInstance() {
        GameProcessor result = instance;
        if (result != null) {
            return result;
        }

        synchronized (GameProcessor.class) {
            if (instance == null) {
                instance = new GameProcessor();
            }
            return instance;
        }
    }

    @Override
    public String startNewGame() {
        state.setMap(mapProcessor.getMap());
        //System.out.println("Starting a new game...");
        return gson.toJson(state);
    }
}
