package se.snrn.combatcreatures.entities;

import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.pathfinding.AStar;

import java.util.ArrayList;

public class AiCore {
    public void act(Creature creature, Player player) {
        if (AStar.isReachable(creature.getTile(), player.getTile(), creature.getMap())) {
            ArrayList<Tile> path = AStar.calculateAStarNoTerrain(creature.getTile(), player.getTile(), creature.getMap());
            if (path != null) {
                Direction dir = creature.getMap().getDirectionFromTile(creature.getTile(), path.get(0));
                creature.move(dir);
            }
        }
    }

}
