package org.example.services;

public final class GameProcessor implements GameService {

    private static volatile GameProcessor instance;

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
        //System.out.println("Starting a new game...");
        return "New game started!";
    }
}
