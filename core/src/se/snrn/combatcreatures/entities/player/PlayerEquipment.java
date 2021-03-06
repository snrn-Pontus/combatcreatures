package se.snrn.combatcreatures.entities.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.effects.PermanentBuffEffect;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.items.Ability;
import se.snrn.combatcreatures.items.Equipment.Equipment;

import java.util.ArrayList;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class PlayerEquipment implements Renderable{

    private Equipment head;
    private Equipment back;
    private Equipment chest;
    private Player player;

    public PlayerEquipment(Player player) {
        this.player = player;
        head = null;
        back = null;
        chest = null;
    }

    ArrayList<PermanentBuffEffect> getPermanentBuffEffects(){
        ArrayList<PermanentBuffEffect> permanentBuffEffects = new ArrayList<>();
        if (head.getPermanentBuffEffect() != null) {
            permanentBuffEffects.add(head.getPermanentBuffEffect());
        }
        if (back.getPermanentBuffEffect() != null) {
            permanentBuffEffects.add(back.getPermanentBuffEffect());
        }
        if (chest.getPermanentBuffEffect() != null) {
            permanentBuffEffects.add(chest.getPermanentBuffEffect());
        }
        return permanentBuffEffects;
    }

    ArrayList<Ability> getAbilities(){
        ArrayList<Ability> abilities = new ArrayList<>();
        if (head.getAbility() != null) {
            abilities.add(head.getAbility());
        }
        if (back.getAbility() != null) {
            abilities.add(back.getAbility());
        }
        if (chest.getAbility() != null) {
            abilities.add(chest.getAbility());
        }
        return abilities;
    }

    public Equipment getHead() {
        return head;
    }

    public void setHead(Equipment head) {
        this.head = head;
    }

    public Equipment getBack() {
        return back;
    }

    public void setBack(Equipment back) {
        this.back = back;
    }

    public Equipment getChest() {
        return chest;
    }

    public void setChest(Equipment chest) {
        this.chest = chest;
    }

    @Override
    public void render(Batch batch) {
        if(head != null){
            head.setPosition(player.getTile().getX()*TILE_SIZE, player.getTile().getY()*TILE_SIZE);
            head.render(batch);
        }
        if(back != null){
            back.setPosition(player.getTile().getX()*TILE_SIZE, player.getTile().getY()*TILE_SIZE);
            back.render(batch);
        }
        if(chest != null){
            chest.setPosition(player.getTile().getX()*TILE_SIZE, player.getTile().getY()*TILE_SIZE);
            chest.render(batch);
        }
    }
}
