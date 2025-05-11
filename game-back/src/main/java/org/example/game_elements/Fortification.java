package org.example.game_elements;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.map.Coordinates;

@ToString
@Getter
public class Fortification implements Unit {
    private final UnitType unitType;
    private final FortificationType fortificationType;
    @Setter
    private boolean firstPlayer;
    private String type;
    private String id;
    private Coordinates coordinates;
    private int fire_range;
    private int hit_points;
    private int shots;
    private int movePoints;

    public <T> Fortification(T fortificationType, boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
        this.unitType = UnitType.FORTIFICATION;
        this.fortificationType = (FortificationType) fortificationType;
        build();
    }

    @Override
    public void place(Coordinates coordinates) {
        this.coordinates = coordinates;
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
        this.type = fortificationType.getType();
        this.hit_points = fortificationType.getHit_points();
        this.shots = fortificationType.getShots();
        this.fire_range = fortificationType.getFire_range();
        this.movePoints = fortificationType.getMovePoints();
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
