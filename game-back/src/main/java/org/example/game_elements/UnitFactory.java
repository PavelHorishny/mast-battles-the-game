package org.example.game_elements;

public abstract class UnitFactory {
    public static <T> Unit create(boolean firstPlayer, T unitType) {
 /*       if(unitType instanceof VesselType) {
            return VesselFactory.createVessel((VesselType) unitType);
        } else if (unitType instanceof FortificationType) {
            return FortificationFactory.createFortification((FortificationType) unitType);
        }*/
/*        return switch (unitType) {
            case VESSEL -> VesselFactory.createVessel(unitType);
            case FORTIFICATION -> FortificationFactory.createFortification(unitType);
        };*/
        if(unitType instanceof VesselType){
            return VesselFactory.createVessel(unitType, firstPlayer);
        }else {
            return FortificationFactory.createFortification(unitType, firstPlayer);
        }

    }
}
