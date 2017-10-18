package se.snrn.combatcreatures.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.items.Item;
import se.snrn.combatcreatures.map.trainstops.TileMap;
import se.snrn.combatcreatures.userinterface.inventory.Inventory;

public class InventoryInputState implements InputState {

    private Inventory inventory;
    private TileMap tileMap;
    private Player player;

    public InventoryInputState(Player player, TileMap tileMap) {
        this.player = player;
        inventory = player.getInventory();
        this.tileMap = tileMap;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public void enter() {
        System.out.println(player.getInventory().getInventoryString());
        inventory.setOpen(true);
    }

    @Override
    public void exit() {

    }

    @Override
    public InputState handleInput(int input) {

        switch (input) {
            case Input.Keys.W: {
                inventory.moveCursorDown();
                break;
            }
            case Input.Keys.S: {
                inventory.moveCursorUp();
                break;
            }

            case Input.Keys.ENTER: {
                System.out.println(inventory.getItemAtCursor());
                inventory.setOpen(false);
                return new DefaultInputState(player, tileMap);

            }
            case Input.Keys.O: {
                Item item = inventory.getItemAtCursor();
                inventory.removeItemAtCursor();
                if(item != null) {
                    player.getTile().addItem(item);
                }
                inventory.setOpen(false);
                return new DefaultInputState(player, tileMap);

            }
        }

        return null;
    }
}
