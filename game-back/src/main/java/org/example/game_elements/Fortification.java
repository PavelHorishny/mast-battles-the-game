package org.example.game_elements;

public class Fortification implements Unit {
    private UnitType unitType;
    private FortificationType fortificationType;
    private String type;
    private int fire_range;
    private int hit_points;
    private int shots;
    private int movePoints;

    public <T> Fortification(T fortificationType) {
        this.unitType = UnitType.FORTIFICATION;
        this.fortificationType = (FortificationType) fortificationType;
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
        this.type = fortificationType.getType();
        this.hit_points = fortificationType.getHit_points();
        this.shots = fortificationType.getShots();
        this.fire_range = fortificationType.getFire_range();
        this.movePoints = fortificationType.getMovePoints();
        System.out.println("Fortification is built type: " + type);
    }
}
