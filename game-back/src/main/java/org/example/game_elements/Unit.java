package org.example.game_elements;

import org.example.map.Coordinates;

public interface Unit {
    void place(Coordinates coordinates);
    void takeDamage(int damage);
    void repair();
    void destroy();
    void makeShot(int damage);
    void build();
    UnitType getUnitType();
    void setId(String id);
    String getId();
}
