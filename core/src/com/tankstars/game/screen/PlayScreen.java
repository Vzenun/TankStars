package com.tankstars.game.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tankstars.game.TankStars;
import static com.tankstars.game.screen.Constants.PPM;

import org.w3c.dom.Text;
import org.w3c.dom.css.Rect;

import java.io.Serializable;


public class PlayScreen implements Screen, InputProcessor, Serializable {
    boolean turn=false;//false pe Player1 ki turn hai and true pe Player2 ki.
    boolean movingright=false;
    boolean movingleft=false;
    boolean movingup=false;
    boolean movingdown=false;
    private TankStars game;
    private Box2DDebugRenderer b2dr;
    private World world;
    private Body Ground;
    private OrthographicCamera camera;
    private int tankheight;
    private int tankwidth;
    private Texture tex;private Texture tex2;private Texture tex3;
    private Body player1,player2,nalli1,nalli2;
    public PlayScreen(TankStars game){
        this.game=game;
        world=new World(new Vector2(0,-2.8f),true);
        b2dr=new Box2DDebugRenderer();
        camera=new OrthographicCamera();
        Body Ground=createGround();
        camera.setToOrtho(false,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        tankheight=20;
        tankwidth=40;
        //tex=new Texture("theme11.png");
        tex2=new Texture("terrain1.png");
        //tex3=new Texture("tank2.png");
        player1=createTank(false,-200,50,true,20,20);
        player2=createTank(false,100,50,true,20,20);
        Gdx.input.setInputProcessor(this);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b2dr.render(world, camera.combined.scl(PPM));
        game.batch.begin();
        //game.batch.draw(tex,0,0);
        game.batch.draw(tex2,0,5);
        //game.batch.draw(tex3,player1.getPosition().x*PPM,player1.getPosition().y*PPM);
        //game.batch.draw(tex3,player2.getPosition().x*PPM,player2.getPosition().y*PPM);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,width/2,height/2);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
    public void update(float delta) {
        world.step(1 / 60f, 18, 3);
        inputUpdate(delta);
        cameraUpdate(delta);
    }
    public void inputUpdate(float delta)
    {
        if(movingleft==true){
            player1.setLinearVelocity(-0.4f,player1.getLinearVelocity().y);
        }
        else if(movingright==true)
        {
            player1.setLinearVelocity(0.4f,player1.getLinearVelocity().y);
        }
        else{
            player1.setLinearVelocity(0,player1.getLinearVelocity().y);
        }
    }
    public void cameraUpdate(float delta)
    {
        Vector3 position=camera.position;
        position.x=-21;
        position.y=14;
        camera.position.set(position);
        camera.update();
    }
    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            movingleft = true;
        }
        if (keycode == Input.Keys.RIGHT) {
            movingright = true;
        }
        if (keycode == Input.Keys.UP) {
            movingup = true;
        }
        if (keycode == Input.Keys.DOWN) {
            movingdown = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode==Input.Keys.LEFT) {
            movingleft=false;
        }
        if(keycode==Input.Keys.RIGHT){
            movingright=false;
        }
        if(keycode==Input.Keys.UP){
            movingup=false;
        }
        if(keycode==Input.Keys.DOWN){
            movingdown=false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
    public Body createGround()
    {
        Body body;
        BodyDef def=new BodyDef();
        def.type= BodyDef.BodyType.StaticBody;

        ChainShape chain=new ChainShape();
        chain.createChain(new Vector2[] {new Vector2(-10.57f,0.23f),new Vector2(-8.8f,0.23f),new Vector2(-7.85f,-1.9f),new Vector2(-5.2f,-1.95f),new Vector2(-4.5f,-1.04f),new Vector2(-2.9f,-0.91f),new Vector2(-0.23f,-1.05f),new Vector2(0.7f,-3.02f),new Vector2(3.5f,-3.02f),new Vector2(4.15f,-1.4f),new Vector2(8.1f,-0.95f),new Vector2(8.5f,-1.18f),new Vector2(8.95f,-1.18f),new Vector2(9.24f,-0.35f),new Vector2(9.24f,6.04f),new Vector2(-10.57f,6.04f),new Vector2(-10.57f,0.23f)});

        FixtureDef fix=new FixtureDef();
        fix.shape=chain;
        fix.friction=0.5f;
        fix.restitution=0;
        body=world.createBody(def);
        body.createFixture(fix);
        return body;
    }
    public Body createTank(boolean isStatic,int x,int y,boolean isRotatable,int tankwidth,int tankheight){
        Body tbody;
        BodyDef def=new BodyDef();
        if(!isStatic){
            def.type=BodyDef.BodyType.DynamicBody;
        }
        else{
            def.type=BodyDef.BodyType.StaticBody;
        }
        def.position.set(x/PPM,y/PPM);
        if(!isRotatable){
            def.fixedRotation=true;
        }
        else{
            def.fixedRotation=false;
        }
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(tankwidth/2/PPM,tankheight/2/PPM);

        FixtureDef fix=new FixtureDef();
        fix.shape=shape;
        fix.friction=.05f;
        fix.restitution=.02f;
        fix.density=7f;

        tbody=world.createBody(def);

        tbody.createFixture(fix);
        shape.dispose();
        return tbody;
    }
    public Body createNalli(boolean isStatic,int x,int y,boolean isRotatable,int nalliwidth,int nalliheight)
    {
        Body tbody;
        BodyDef def=new BodyDef();
        if(!isStatic)
        {
            def.type=BodyDef.BodyType.DynamicBody;
        }
        else
        {
            def.type=BodyDef.BodyType.StaticBody;
        }
        def.position.set(x,y);
        if(!isRotatable)
        {
            def.fixedRotation=true;
        }
        else
        {
            def.fixedRotation=false;
        }
        tbody=world.createBody(def);
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(nalliwidth/2,nalliheight/2);
        tbody.createFixture(shape,1.0f);
        shape.dispose();
        return tbody;
    }
}
