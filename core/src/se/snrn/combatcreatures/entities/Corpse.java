package se.snrn.combatcreatures.entities;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.combatcreatures.interfaces.Container;
import se.snrn.combatcreatures.interfaces.Mapped;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;

import java.util.ArrayList;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class Corpse implements Updatable, Renderable, Mapped, Container {


    private final Tile tile;
    private final TileMap tileMap;
    private final Sprite sprite;
    private ArrayList<Item> loot;

    public Corpse(Tile tile, TileMap tileMap, Sprite sprite, ArrayList<Item> loot) {

        this.tile = tile;
        this.tileMap = tileMap;
        this.sprite = sprite;
        this.loot = loot;
    }

    @Override
    public void update(float delta) {
        sprite.setPosition(tile.getX() * TILE_SIZE, tile.getY() * TILE_SIZE);
    }

    @Override
    public void render(Batch batch) {
        sprite.draw(batch);
    }

    @Override
    public Tile getTile() {
        return null;
    }

    @Override
    public TileMap getMap() {
        return null;
    }

    @Override
    public void changeTile(Tile tile) {

    }

    @Override
    public boolean move(Direction direction) {
        return false;
    }

    @Override
    public ArrayList<Item> getLoot() {
        return loot;
    }
}
