package se.snrn.combatcreatures.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.interfaces.*;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;
import se.snrn.combatcreatures.map.TileType;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class Creature implements Updatable, Renderable, Mapped, Ai, Living {
    private Tile tile;
    private TileMap tileMap;
    private Sprite sprite;
    private AiCore aiCore;
    private boolean finished;
    private int health;
    private boolean alive;

    public Creature(Tile tile, TileMap tileMap, Sprite sprite) {
        this.tile = tile;
        this.tileMap = tileMap;
        this.sprite = sprite;
        aiCore = new AiCore();
        health = 3;
        alive = true;
    }

    @Override
    public void update(float delta) {
        if(health <= 0){
            die();
        }
        sprite.setPosition(tile.getX() * TILE_SIZE, tile.getY() * TILE_SIZE);
    }

    @Override
    public void render(Batch batch) {
        sprite.draw(batch);
    }


    @Override
    public Tile getTile() {
        return tile;
    }

    @Override
    public TileMap getMap() {
        return tileMap;
    }

    @Override
    public void changeTile(Tile tile) {
        this.tile.setMapped(null);
        tile.setMapped(this);
        this.tile = tile;
    }

    @Override
    public boolean move(Direction direction) {
        Tile newTile = tileMap.getTile(tile.getX()+direction.getX(),tile.getY()+direction.getY());
        if(newTile != null && newTile.getType() == TileType.FLOOR) {
            if(newTile.getMapped() instanceof Player){
                Player player = (Player)newTile.getMapped();
                player.takeDamage(1);
                return true;
            }
            changeTile(newTile);
            return true;
        }
        return false;
    }


    @Override
    public void setAi(AiCore aiCore) {
        this.aiCore = aiCore;
    }

    @Override
    public AiCore getAiCore() {
        return aiCore;
    }

    @Override
    public void act(Player player) {
        aiCore.act(this, player);
        finished = true;
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public void healDamage(int damage) {
        health += damage;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void die() {
        sprite = ResourceManager.creatureDead;
        setAlive(false);
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}

