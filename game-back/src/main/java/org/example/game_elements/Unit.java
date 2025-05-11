package org.example.game_elements;

public interface Unit {
    void place();
    void takeDamage(int damage);
    void repair();
    void destroy();
    void makeShot(int damage);
    void build();

}
