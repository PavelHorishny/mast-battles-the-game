export class Unit {
    constructor({id,player,type,x,y,direction = 0}){
        this.id = id;
        this.player = player;
        this.type = type;

        this.x = x;
        this.y = y;
        this.direction = direction;
        this.selected = false;
    }

    setPosition(x,y){
        this.x = x;
        this.y = y;
    }

    setDirection(direction){
        this.direction = direction;
    }
}