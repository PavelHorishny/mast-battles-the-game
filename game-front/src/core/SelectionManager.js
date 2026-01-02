export class SelectionManager {
    constructor() {
        this.selectedUnit = null;
    }

    select(unit) {
        if(this.selectedUnit){
            this.selectedUnit.selected = false;
        }
        this.selectedUnit = unit;
        if(unit){
            unit.selected = true;
        }
    }

    clear(){
        if(this.selectedUnit){
            this.selectedUnit.selected = false;
        }
        this.selectedUnit = null;
    }
}