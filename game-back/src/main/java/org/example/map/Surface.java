package org.example.map;

import lombok.Getter;
import lombok.Setter;
import org.example.game_elements.Unit;
@Getter
@Setter
public class Surface {
    private Coordinates coordinates;
    private SurfaceType type;
    private Unit unit;
    //private Fortification fortification;
    private boolean isUnderAttack;

    public Surface (Coordinates coordinates,SurfaceType type){
        this.coordinates=coordinates;
        this.type = type;
    }

    public boolean isEmpty(){
        return unit == null;
    }
}
