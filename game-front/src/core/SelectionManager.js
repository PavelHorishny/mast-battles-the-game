export class SelectionManager {
    constructor() {
        this.selectedUnit = null;
    }

    select(unit) {
        this.selectedUnit = unit;
    }

    clear(){
        this.selectedUnit = null;
    }
}