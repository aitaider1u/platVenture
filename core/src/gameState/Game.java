package gameState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import controleur.ContactListenerPlayer;
import controleur.GestionaireMouvement;
import gameElement.Element;
import gameElement.element.Gem1;
import gameElement.element.Gem2;
import outils.FabriqueSprite;
import outils.GestionaireTextureAnimation;
import outils.GestionaireBodyToDelete;
import gameElement.Player;
import gameElement.element.Brique;
import gameElement.element.Eau;
import gameElement.element.Sortie;
import gameElement.element.Gem;
import outils.Constants;
import outils.FabriqueBriqueBody;
import outils.GestionaireDeSons;
import outils.GestionaireDeText;
import outils.LireFichier;






public class Game extends GameState {
    private Box2DDebugRenderer debugRenderer2D; //affichage de debug
    private World world;
    private Player player;
    private int time ,timeTansition;
    private int heightMap,weidthMap;
    private GestionaireBodyToDelete gestionaieBodyToDelete = new GestionaireBodyToDelete();
    private GestionaireDeText gestionaireDeText;
    private int level=1;
    private Timer.Task tache;
    private Timer.Task timeTransition;
    private boolean finPartie = false ,transitionLancer=false;
    private static Texture backgroundTexture;
    private OrthographicCamera cameraBack =new OrthographicCamera();
    private Array<Element> elements = new Array<>();
    private Texture getBackgroundTexture = new Texture(Gdx.files.internal("images/Back.png"));
    private GestionaireMouvement gestionaireMouvement;
    private float widthBackground;
    private ContactListenerPlayer c;


    public Game(GameStateManager gsm) {
        super(gsm);
        world = new World(new Vector2(0, -10f), false);
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        debugRenderer2D = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        GestionaireTextureAnimation.getInstance().initialiserLesAnimation();
        this.initWorld("levels/level_00"+this.level+".txt");
        tache = new Timer.Task() {
            @Override
            public void run() {
                if(time>0)
                    time--;
                else
                    player.setDead(true);
            }
        };
        Timer.schedule(tache,1F,1F);
        float f1 = Gdx.graphics.getWidth();
        float f2 = Gdx.graphics.getHeight();
        float f = Constants.UNITY_world*Constants.NbUnityCameraWidth*(f2/f1);
        camera.setToOrtho(false,Constants.UNITY_world*Constants.NbUnityCameraWidth,f);
        camera.setToOrtho(false,
                Constants.UNITY_world*Constants.NbUnityCameraWidth,
                Constants.UNITY_world*Constants.NbUnityCameraWidth);
        this.gestionaireDeText = new GestionaireDeText();
        backgroundTexture = new Texture(Gdx.files.internal("images/Back.png"));
    }


    @Override
    public void render() {

        if(!finPartie) {
            if (!player.afiniLeLevel() && !player.isDead())
                updatePlayer();
            Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            cameraBack.setToOrtho(false,Constants.UNITY_world*Constants.NbUnityCameraWidth,this.weidthMap*((float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth()));
            debugRenderer2D.render(world, camera.combined);
            batch.setProjectionMatrix(this.camera.combined);
            batch.begin();
            batch.draw(backgroundTexture,0,0,this.weidthMap*Constants.UNITY_world,this.heightMap*Constants.UNITY_world);
            player.drawPlayer(batch);
            for(Element s : elements){
                if(s.getBody().isAwake()) {
                    s.draw(batch);
                }
            }
            batch.end();
            this.updateText();
        }
        if(this.player.afiniLeLevel()){
            this.trisitionAuProchainLevel();
        }
        if(this.player.isDead()){
            this.trisitionRefaireLevel();
        }
    }


    public void passerAuNiveauSuivant(){
        this.level++;
        if(this.level>3)
            this.level = 1;
        int saveScore = this.player.getScore();
        this.clearLevel();
        this.initWorld("levels/level_00"+this.level+".txt");
        this.player.setScore(saveScore);
        lancerUntimer(time);
        Timer.schedule(tache,1F,1F);

    }


    public void rejouer(){
        this.level=1;
        this.clearLevel();
        this.initWorld("levels/level_00"+this.level+".txt");
        lancerUntimer(time);
        Timer.schedule(tache,1F,1F);
    }



    @Override
    public void dispose(){
        batch.dispose();
        debugRenderer2D.dispose();
        gestionaireDeText.dispose();
        backgroundTexture.dispose();
        GestionaireTextureAnimation.getInstance().dispose();
        GestionaireDeSons.getInstance().disposeSounds();
    }

    public void update()
    {
        world.step(1/60f,6,2);
        this.cameraUpdate();
        this.bodyToDelete();
    }

    public void updatePlayer()
    {

        player.getBody().setLinearVelocity(this.player.getSpeedX(), player.getBody().getLinearVelocity().y);

        if(this.player.estPasserParLaSortie()){
            if(this.player.getBody().getPosition().x > this.weidthMap){
                this.player.setAfiniLeLevel(true);
            }
        }else
        {
           if(this.player.getBody().getPosition().x > this.weidthMap ||this.player.getBody().getPosition().x < 0){
                this.player.setDead(true);
                GestionaireDeSons.getInstance().playSoundLoose();
            }
        }
    }

    private  void  initWorld(String fileName) {
        FileHandle file =  Gdx.files.internal(fileName);
        world = new World(new Vector2(0, -10f), false);
        String str = file.readString();
        String[][] tab = LireFichier.extraireMap(str);
        int l = LireFichier.extraireLargeur(str);
        int h = LireFichier.extraireHauter(str);
        this.time = LireFichier.extraireTemp(str);
        this.weidthMap = l;
        this.heightMap = h;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                System.out.println(i+" "+j);
                if(tab[h-1-i][j].equals("1")){
                    Gem jaune = new Gem1(FabriqueBriqueBody.getInstance().createGemBody(world,Constants.UNITY_world/2+ Constants.UNITY_world *(j),Constants.UNITY_world/2+Constants.UNITY_world*(i),1), FabriqueSprite.getInstance().createSpriteGem(Constants.UNITY_world/2+ Constants.UNITY_world *(j),Constants.UNITY_world/2+Constants.UNITY_world*(i),tab[h-1-i][j]), 1);
                    elements.add(jaune);
                }else if(tab[h-1-i][j].equals("2")) {
                    //Gem jaune = new Gem(FabriqueBriqueBody.getInstance().createGemBody(world,Constants.UNITY_world/2+ Constants.UNITY_world *(j),Constants.UNITY_world/2+Constants.UNITY_world*(i),2),2);
                    Gem jaune = new Gem2(FabriqueBriqueBody.getInstance().createGemBody(world,Constants.UNITY_world/2+ Constants.UNITY_world *(j),Constants.UNITY_world/2+Constants.UNITY_world*(i),2), FabriqueSprite.getInstance().createSpriteGem(Constants.UNITY_world/2+ Constants.UNITY_world *(j),Constants.UNITY_world/2+Constants.UNITY_world*(i),tab[h-1-i][j]), 2);
                    elements.add(jaune);
                } else if(tab[h-1-i][j].equals("K")){  //pour platefome K
                    Brique plateFormeK = new Brique( FabriqueBriqueBody.getInstance().createPlateformeKBody( world,Constants.UNITY_world/2+ Constants.UNITY_world *(j),-Constants.UNITY_world/8+Constants.UNITY_world/2+Constants.UNITY_world*(i)),
                            FabriqueSprite.getInstance().createSpritePlatefrome(Constants.UNITY_world/2+ Constants.UNITY_world *(j),-Constants.UNITY_world/8+Constants.UNITY_world/2+Constants.UNITY_world*(i),tab[h-1-i][j]));
                    elements.add(plateFormeK);
                }else if(tab[h-1-i][j].equals("J")){   //pour platefome J
                    Brique plateFormeJ = new Brique(FabriqueBriqueBody.getInstance().createPlateformeJBody(world,Constants.UNITY_world/2+ Constants.UNITY_world *(j),-Constants.UNITY_world/8+Constants.UNITY_world/2+Constants.UNITY_world*(i)),
                            FabriqueSprite.getInstance().createSpritePlatefrome(Constants.UNITY_world/2+ Constants.UNITY_world *(j),-Constants.UNITY_world/8+Constants.UNITY_world/2+Constants.UNITY_world*(i),tab[h-1-i][j]));
                    elements.add(plateFormeJ);
                }else if(tab[h-1-i][j].equals("L")){    //pour platforme L
                    Brique plateFormeJ = new Brique(FabriqueBriqueBody.getInstance().createPlateformeLBody(world,Constants.UNITY_world/2+ Constants.UNITY_world *(j) ,-Constants.UNITY_world/8+Constants.UNITY_world/2+Constants.UNITY_world*(i)),
                            FabriqueSprite.getInstance().createSpritePlatefrome(Constants.UNITY_world/2+ Constants.UNITY_world *(j),-Constants.UNITY_world/8+Constants.UNITY_world/2+Constants.UNITY_world*(i),tab[h-1-i][j]));
                    elements.add(plateFormeJ);
                }else if(tab[h-1-i][j].equals("P")){ // pour player
                    this.player = new Player(world,Constants.UNITY_world/2+ Constants.UNITY_world *(j),Constants.UNITY_world/2+Constants.UNITY_world*(i));
                }else if(tab[h-1-i][j].equals("W")){ // pour water
                    Eau eau = new Eau(FabriqueBriqueBody.getInstance().createWaterBody(world,Constants.UNITY_world/2+ Constants.UNITY_world *(j),-Constants.UNITY_world/8+Constants.UNITY_world/2+Constants.UNITY_world*(i)),
                            FabriqueSprite.getInstance().createSpriteWater(Constants.UNITY_world/2+ Constants.UNITY_world *(j),-Constants.UNITY_world/8+Constants.UNITY_world/2+Constants.UNITY_world*(i)));
                    elements.add(eau);
                }else if(tab[h-1-i][j].equals("Z")){ // pour water
                    Sortie sortie = new Sortie(FabriqueBriqueBody.getInstance().createSortieBody(world,Constants.UNITY_world/2+ Constants.UNITY_world *(j),Constants.UNITY_world/2+Constants.UNITY_world*(i))
                                                ,FabriqueSprite.getInstance().createSpriteSortie(Constants.UNITY_world/2+ Constants.UNITY_world *(j),Constants.UNITY_world/2+Constants.UNITY_world*(i)));
                    elements.add(sortie);
                }else if(!tab[h-1-i][j].equals("V")){ // pour briques simple
                    Brique brique = new Brique(FabriqueBriqueBody.getInstance().createBriqueBody(world,Constants.UNITY_world/2+ Constants.UNITY_world *(j),Constants.UNITY_world/2+Constants.UNITY_world*(i)),
                            FabriqueSprite.getInstance().createSpriteBrique(Constants.UNITY_world/2+ Constants.UNITY_world *(j),Constants.UNITY_world/2+Constants.UNITY_world*(i),tab[h-1-i][j]));
                    elements.add(brique);
                }
            }
        }

        c = new ContactListenerPlayer(this.player,this,gestionaieBodyToDelete);
        world.setContactListener(c);

        this.gestionaireMouvement = new GestionaireMouvement(this.player);
        Gdx.input.setInputProcessor(gestionaireMouvement);
        widthBackground = this.getWeidthMap();
        if(widthBackground<Constants.NbUnityCameraWidth)
            widthBackground = Constants.NbUnityCameraWidth;
    }

    public void cameraUpdate(){
        float x,y;
        float f1 = Gdx.graphics.getWidth();
        float f2 = Gdx.graphics.getHeight();
        float f = Constants.UNITY_world*Constants.NbUnityCameraWidth*(f2/f1);
        float fmm = Constants.UNITY_world*Constants.NbUnityCameraWidth*((float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth());

        camera.setToOrtho(false,Constants.UNITY_world*Constants.NbUnityCameraWidth,f);
        x = player.getBody().getPosition().x;

        if(player.getBody().getPosition().x < Constants.NbUnityCameraWidth/2)
            x = Constants.NbUnityCameraWidth/2;

        else if (player.getBody().getPosition().x > (float)this.weidthMap-Constants.NbUnityCameraWidth/2)
            x = this.weidthMap-Constants.NbUnityCameraWidth/2;

        y = player.getBody().getPosition().y;


        if(player.getBody().getPosition().y < f/2)
            y =f/2;
        else if (player.getBody().getPosition().y>(float)this.heightMap-f/2)
            y = this.heightMap-f/2;


        camera.position.set(x,y,0);
        camera.update();
    }



    public void bodyToDelete(){
        for(Body body : gestionaieBodyToDelete) {
            world.destroyBody(body);
            body.setAwake(false);
        }
        gestionaieBodyToDelete.clearList();
    }

    public void updateText(){
        gestionaireDeText.updateCamera();
        gestionaireDeText.getBatchText().setProjectionMatrix(gestionaireDeText.getCameraText().combined);
        gestionaireDeText.getBatchText().begin();
        gestionaireDeText.updateFontTime(this.time);
        gestionaireDeText.updateFontScore(this.player.getScore());
        if(this.player.afiniLeLevel()){
            gestionaireDeText.updateFontState("Bravo !");
            tache.cancel(); // kill thread of the timer
        }else if(this.player.isDead())
        {
            gestionaireDeText.updateFontState("Dommage :/");
            tache.cancel(); // kill thread of the timer
        }
        gestionaireDeText.getBatchText().end();
    }

    public  void clearLevel(){
        Array<Body> bodies = new Array<Body>();
        this.world.getBodies(bodies);
        for(Body bod: bodies){
            this.world.destroyBody(bod);
        }
        this.clearSprite();
        this.gestionaieBodyToDelete.clearList();
    }

    public void lancerUntimer(int i ){
        this.time = i;
        this.tache = new Timer.Task() {
            @Override
            public void run() {
                if(time>0)
                    time--;
                else{
                    player.setDead(true);
                    this.cancel();
                }
            }
        };
    }

    private void trisitionAuProchainLevel(){
        if(!transitionLancer){
            transitionLancer = true;
            timeTansition = 2;
            timeTransition = new Timer.Task() {
                @Override
                public void run() {
                    if(timeTansition>0){
                        timeTansition--;
                    }
                    else{
                        finPartie = false;
                        timeTransition.cancel();
                        transitionLancer = false;
                        passerAuNiveauSuivant();

                    }
                }
            };
            Timer.schedule(timeTransition,1F,1F);
        }
    }

    private void trisitionRefaireLevel(){
        if(!transitionLancer){
            transitionLancer = true;
            timeTansition = 2;
            timeTransition = new Timer.Task() {
                @Override
                public void run() {
                    if(timeTansition>0){
                        timeTansition--;
                    }
                    else{
                        finPartie = false;
                        timeTransition.cancel();
                        transitionLancer = false;
                        rejouer();
                    }
                }
            };
            Timer.schedule(timeTransition,1F,1F);
        }
    }



    public int getWeidthMap() {
        return weidthMap;
    }

    private void clearSprite(){
        this.elements.clear();
    }


}

