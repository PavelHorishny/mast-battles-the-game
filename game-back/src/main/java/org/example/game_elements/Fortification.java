package org.example.game_elements;

public class Fortification implements Unit {
    private UnitType unitType;
    private FortificationType fortificationType;

    public <T> Fortification(T fortificationType) {
        this.fortificationType = (FortificationType) fortificationType;
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

    }
}
