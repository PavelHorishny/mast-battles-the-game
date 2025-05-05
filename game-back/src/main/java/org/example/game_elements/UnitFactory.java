package org.example.game_elements;

public abstract class UnitFactory {
    public static <T> Unit create(UnitType unitType, T someType) {
        return switch (unitType) {
            case VESSEL -> VesselFactory.createVessel(someType);
            case FORTIFICATION -> FortificationFactory.createFortification(someType);
        };
    }
}
