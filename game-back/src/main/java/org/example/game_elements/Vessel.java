package org.example.game_elements;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Vessel implements Unit,Movable {
    private final UnitType unitType;
    private final VesselType vesselType;
    private String type;
    private final boolean isFirstPlayer;
    private int breeze_move_points;
    private int calm_move_points;
    private int storm_move_points;
    private int shots;
    private int fire_range;
    private int hit_points;

    public <T> Vessel(T vesselType, boolean firstPlayer) {
        this.unitType = UnitType.VESSEL;
        this.vesselType= (VesselType) vesselType;
        this.isFirstPlayer = firstPlayer;
        build();
    }

    @Override
    public void place() {

    }

    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public void repair() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void makeShot(int damage) {

    }

    @Override
    public void build() {
        this.type = vesselType.getType();
        this.hit_points = vesselType.getHit_points();
        this.shots = vesselType.getShots();
        this.fire_range = vesselType.getFire_range();
        this.breeze_move_points = vesselType.getBreeze_move_points();
        this.calm_move_points = vesselType.getCalm_move_points();
        this.storm_move_points = vesselType.getStorm_move_points();
        System.out.println("Vessel is built type: " + type);
    }

    @Override
    public void move(int x, int y) {

    }
}
