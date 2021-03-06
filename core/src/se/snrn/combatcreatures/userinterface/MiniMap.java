package se.snrn.combatcreatures.userinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.enemies.Creature;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;

import static se.snrn.combatcreatures.ResourceManager.*;


public class MiniMap implements Updatable, Renderable {

    private int margin;
    private float miniMapWidth;
    private float miniMapHeight;
    private float startY;
    private float startX;
    private TrainStopMap trainStopMap;
    private Player player;
    private float mapScale;


    public MiniMap(Player player) {


        this.player = player;


        trainStopMap = player.getTrainStopMap();
        mapScale = 4;
        margin = 8;

        miniMapWidth = mapScale * trainStopMap.getWidth();
        miniMapHeight = mapScale * trainStopMap.getHeight();

        startX = Gdx.graphics.getWidth() - miniMapWidth - margin;
        startY = Gdx.graphics.getHeight() - miniMapHeight - margin;

    }

    public void setMapScale(float mapScale) {
        if(this.mapScale == mapScale){
            mapScale = 4;
        }
        this.mapScale = mapScale;
        miniMapWidth = mapScale * trainStopMap.getWidth();
        miniMapHeight = mapScale * trainStopMap.getHeight();

        startX = Gdx.graphics.getWidth() - miniMapWidth - margin;
        startY = Gdx.graphics.getHeight() - miniMapHeight - margin;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {

        ResourceManager.pinkBox.draw(batch, startX-margin, startY-margin, miniMapWidth+margin*2, miniMapHeight+margin*2);
        for (int x = 0; x < trainStopMap.getWidth(); x++) {
            for (int y = 0; y < trainStopMap.getHeight(); y++) {
                Tile point = trainStopMap.getTile(x, y);
                if (point.getType() == TileType.DOOR && point.isExplored()) {
                    red.setSize(mapScale, mapScale);
                    red.setPosition(startX + point.getX() * mapScale, startY + point.getY() * mapScale);
                    red.draw(batch);
                }
                if (point.getType() == TileType.FLOOR && point.isExplored()) {
                    white.setSize(mapScale, mapScale);
                    white.setPosition(startX + point.getX() * mapScale, startY + point.getY() * mapScale);
                    white.draw(batch);
                }

                if (point.getType() == TileType.TRACK_TOP || point.getType() == TileType.TRACK_BOTTOM || point.getType() == TileType.TRACK_MIDDLE && point.isExplored()) {
                    white.setSize(mapScale, mapScale);
                    white.setPosition(startX + point.getX() * mapScale, startY + point.getY() * mapScale);
                    white.draw(batch);
                }

                if (point.getType() == TileType.WALL || point.getType() == TileType.EARTH && point.isExplored()) {
                    pink.setSize(mapScale, mapScale);
                    pink.setPosition(startX + point.getX() * mapScale, startY + point.getY() * mapScale);
                    pink.draw(batch);
                }

                if (point.getMapped() != null && point.getMapped() instanceof Player) {
                    blue.setSize(mapScale, mapScale);
                    blue.setPosition(startX + point.getX() * mapScale, startY + point.getY() * mapScale);
                    blue.draw(batch);
                }
                if (point.getMapped() != null && point.getMapped() instanceof Creature) {
                    black.setSize(mapScale, mapScale);
                    black.setPosition(startX + point.getX() * mapScale, startY + point.getY() * mapScale);
                    black.draw(batch);
                }

            }
        }

    }

    public float getMiniMapWidth() {
        return miniMapWidth;
    }

    public float getMiniMapHeight() {
        return miniMapHeight;
    }
}
