package org.example.game_elements;

public class FortificationFactory {
    public static <T> Unit createFortification(T fortificationType, boolean firstPlayer) {
        return new Fortification(fortificationType, firstPlayer);
    }
}
