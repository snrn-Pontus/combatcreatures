package se.snrn.combatcreatures;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import se.snrn.combatcreatures.entities.enemies.CreatureFactory;
import se.snrn.combatcreatures.entities.enemies.CreatureManager;
import se.snrn.combatcreatures.entities.Stats;
import se.snrn.combatcreatures.entities.enemies.EnemySpawner;
import se.snrn.combatcreatures.entities.player.Experience;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.input.InputHandler;
import se.snrn.combatcreatures.input.InputStateMachine;
import se.snrn.combatcreatures.items.consumable.ConsumableFactory;
import se.snrn.combatcreatures.map.trainstops.StopType;
import se.snrn.combatcreatures.map.trainstops.TrainStopFactory;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;
import se.snrn.combatcreatures.userinterface.GameLog;
import se.snrn.combatcreatures.userinterface.Ui;
import se.snrn.combatcreatures.visualeffects.VisualEffectManager;
import se.snrn.combatcreatures.worldmap.WorldMap;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class MissionScreen implements Screen {


    private static final int WORLD_WIDTH = 1280;
    private static final int WORLD_HEIGHT = 720;
    public static boolean debug = false;
    public static Ui ui;
    private final CombatCreatures cc;
    private Train train;
    private InputStateMachine inputStateMachine;
    private Batch batch;
    private InputHandler inputHandler;
    private OrthographicCamera orthographicCamera;
    private Viewport viewport;
    private Player player;
    private CreatureManager creatureManager;
    public static TurnManager turnManager;
    private Batch uiBatch;
    private ConsumableFactory consumableFactory;
    private ShapeRenderer shapeRenderer;
    public static VisualEffectManager visualEffectManager;
    public static TrainStopMap trainStopMap;
    public WorldMap worldMap;


    public MissionScreen(Batch batch, SpriteBatch uiBatch, CombatCreatures combatCreatures) {
        cc = combatCreatures;
        new GameLog();
        orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, orthographicCamera);
        viewport.apply();
        new ResourceManager();
        this.batch = batch;
        this.uiBatch = uiBatch;
        new CreatureFactory();
        consumableFactory = new ConsumableFactory();
        trainStopMap = TrainStopFactory.getTrainStop(StopType.SWITCH, true);
        //trainStopMap = TrainStopFactory.getDungeonMap();

        creatureManager = new CreatureManager();
        player = new Player(trainStopMap.getStartTile(), trainStopMap, new Stats(1, 1, 1, 1, 1, 1));
        creatureManager.setPlayer(player);
        turnManager = new TurnManager(creatureManager);
        inputStateMachine = new InputStateMachine(player, trainStopMap);
        inputHandler = new InputHandler(inputStateMachine, player);

        visualEffectManager = new VisualEffectManager();

        Gdx.input.setInputProcessor(inputHandler);


        ui = new Ui(player, trainStopMap);


        shapeRenderer = new ShapeRenderer();


        new Experience();

        train = new Train();
        System.out.println(trainStopMap.getSpawns());

        EnemySpawner.spawnEnemies(creatureManager, trainStopMap, 200);

    }

    public static Ui getUi() {
        return ui;
    }

    @Override
    public void show() {
        orthographicCamera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        orthographicCamera.update();
        viewport.apply();
    }

    private void update(float delta) {
        orthographicCamera.position.set(player.getTile().getX() * TILE_SIZE, player.getTile().getY() * TILE_SIZE, 0);

        if (!player.isAlive()) {
            cc.setScreen(cc.highScoreScreen);
        }
        orthographicCamera.update();

        player.update(delta);
        visualEffectManager.update(delta);
        creatureManager.update(delta);

        inputStateMachine.update(delta);
        ui.update(delta);
        Gdx.graphics.setTitle("FPS: " + Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.setProjectionMatrix(orthographicCamera.combined);
        batch.begin();
        trainStopMap.render(batch);

        creatureManager.render(batch);
        player.render(batch);
        inputStateMachine.render(batch);
        visualEffectManager.render(batch);
//        for (Tile tile : line
//                ) {
//            ResourceManager.player.setPosition(tile.getX() * TILE_SIZE, tile.getY() * TILE_SIZE);
//            ResourceManager.player.draw(batch);
//        }
//        train.render(batch);
        batch.end();
        uiBatch.begin();
        ui.render(uiBatch);
        uiBatch.end();


        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);


        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.setScreenSize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
