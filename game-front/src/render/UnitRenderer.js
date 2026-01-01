import {Container, Texture} from "pixi.js";
import {ShipSprite } from "@/render/UnitSprite";

export class UnitRenderer {
    constructor(tileSize) {
        this.container = new Container();
        this.tileSize = tileSize;

        this.texture = {
            brig : Texture.from('brig'),
            galleon: Texture.from('galleon'),
            battery: Texture.from('battery'),
            corvette: Texture.from('corvette'),
            frigate: Texture.from('frigate'),
            galley: Texture.from('galley'),
            monitor: Texture.from('monitor'),
            steamer: Texture.from('steamer'),
            steamFrigate: Texture.from('steamFrigate'),
            tender: Texture.from('tender'),
            twoDeckerShipOfLine: Texture.from('twoDeckerShipOfLine'),
            threeDeckerShipOfLine: Texture.from('threeDeckerShipOfLine'),
            _brig : Texture.from('_brig'),
            _galleon: Texture.from('_galleon'),
            _battery: Texture.from('_battery'),
            _corvette: Texture.from('_corvette'),
            _frigate: Texture.from('_frigate'),
            _galley: Texture.from('_galley'),
            _monitor: Texture.from('_monitor'),
            _steamer: Texture.from('_steamer'),
            _steamFrigate: Texture.from('_steamFrigate'),
            _tender: Texture.from('_tender'),
            _twoDeckerShipOfLine: Texture.from('_twoDeckerShipOfLine'),
            _threeDeckerShipOfLine: Texture.from('_threeDeckerShipOfLine'),
            royalPort: Texture.from('royalPort'),
            _royalPort: Texture.from('_royalPort'),
            firstLineFort: Texture.from('firstLineFort'),
            _firstLineFort: Texture.from('_firstLineFort'),
            secondLineFort: Texture.from('secondLineFort'),
            _secondLineFort: Texture.from('_secondLineFort'),
            __firstLineFort: Texture.from('__firstLineFort'),
            __secondLineFort: Texture.from('__secondLineFort')
        };

        this.units = [];
    }

    addUnit(unit) {
        const sprite = new ShipSprite(
            this.texture[unit.type],
            unit.x,
            unit.y,
            this.tileSize
        );

        sprite.setDirection(unit.direction ?? 0);

        this.units.push({unit,sprite});
        this.container.addChild(sprite);
    }

    update() {
        for(const u of this.units) {
            u.sprite.setTilePosition(u.unit.x, u.unit.y);
            u.sprite.setDirection(u.unit.direction);
        }
    }
}