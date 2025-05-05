package org.example.game_elements;

public class VesselFactory {
    public  static <T> Unit createVessel(T vesselType) {
        return new Vessel(vesselType);
    }
}
