package org.example.map;

public class Surface {
    private Coordinates coordinates;
    private SurfaceType type;
    //private GameUnit unit;
    //private Fortification fortification;
    private boolean isUnderAttack;

    public Surface (Coordinates coordinates,SurfaceType type){
        this.coordinates=coordinates;
        this.type = type;
    }
}
