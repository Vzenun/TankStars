package com.tankstars.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.utils.Array;
import com.tankstars.game.TankStars;
import com.tankstars.game.Vidur_DuaContactListner;

import static com.tankstars.game.screen.Constants.PPM;

import java.io.Serializable;


public class PlayScreen implements Screen, InputProcessor, Serializable {

    private Body nalli1;
    private Body nalli2;
    private Texture tz_muzzle;
    private float barrelAngle=0.02f;
    private float barrelAngle2=0.5f;
    boolean movingright=false;
    boolean movingleft=false;
    boolean movingup=false;
    boolean movingdown=false;
    boolean movingright1=false;
    boolean movingleft1=false;
    boolean movingup1=false;
    boolean movingdown1=false;
    boolean fire=false;
    boolean fire2=false;
    //boolean movingdown1=false;
    private TankStars game;
    private Box2DDebugRenderer b2dr;
    private World world;
    private Body Ground;
    private OrthographicCamera camera;
    private int tankheight;
    private int tankwidth;
    private Texture tex;private Texture tex2;private Texture tex3;
    private Body player1,player2,player3,player11,player12,player13;
    private RevoluteJoint rwheel,lwheel;
    private RevoluteJoint n1,n2;
    private RevoluteJoint rwheel1,lwheel1;
    private Sprite sp;
    private Sprite sp2;private Texture tbull;
    RevoluteJointDef ref;
    RevoluteJointDef ref1;
    RevoluteJointDef ref2;
    private Sprite sp3,sp13,sp03;
    private Array<Body> tmpbody=new Array<Body>();

    private Body Bull,Bull2;
    public PlayScreen(TankStars game){

        this.game=game;
        world=new World(new Vector2(0,-2.8f),true);
        b2dr=new Box2DDebugRenderer();
        camera=new OrthographicCamera();
        Body Ground=createGround();
        camera.setToOrtho(false,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        tankheight=20;
        tankwidth=40;
        tz_muzzle=new Texture("t2_nuzzle.png");
        sp3=new Sprite(tz_muzzle);
        //tex=new Texture("theme11.png");
        tex2=new Texture("terrain1.png");
        sp2=new Sprite(tex2);
        sp2.setPosition(-10.552f,-5.1f);
        sp2.setSize(640/1/PPM,200/1/PPM);
        sp2.setOrigin(-400,5000);
        tex3=new Texture("tank2_body.png");
        sp=new Sprite(tex3);
        sp13=new Sprite(new Texture("tank1_body.png"));
        sp03=new Sprite(new Texture("tank1_nuzzle.png"));
        player1=createTank(sp,false,-200,50,true,30,30);
        player2=createwheels(false,-200,50,true,20,20);
        player3=createwheels(false,-190,50,true,20,20);
        player11=createTank(sp13,false,90,50,true,30,30);
        player12=createwheels(false,90,50,true,20,20);
        player13=createwheels(false,90,50,true,20,20);
        nalli1=createnalli(sp3,false,(int)player1.getPosition().x,(int)player1.getPosition().y,false,30,20);
        nalli2=createnalli2(sp03,false,(int)player11.getPosition().x,(int)player11.getPosition().y, false,30,20);
        tbull=new Texture("bullet.png");

        Bull=createbullet(new Sprite(tbull),false,-185,70,false,15,15);
        Bull2=createbullet(new Sprite(tbull),false,74,80,false,15,15);
        this.world.setContactListener(new Vidur_DuaContactListner());
        //Bull.setPosition(player1.getPosition().x,player1.getPosition().y);



        ref = new RevoluteJointDef () ;
        ref.enableMotor=true;
        ref.motorSpeed=1;
        ref.maxMotorTorque=0;
        ref.bodyA=player1;
        ref.bodyB=player2;
        ref.collideConnected=false;
        ref.localAnchorA.set(-9/PPM,-14/PPM);
        lwheel=(RevoluteJoint) world.createJoint(ref);

        ref.bodyB=player3;
        ref.localAnchorA.set(9/PPM,-14/PPM);
        rwheel=(RevoluteJoint) world.createJoint(ref);

        ref.bodyB=nalli1;
        ref.localAnchorA.set(0/PPM,3/PPM);
        n1=(RevoluteJoint)world.createJoint(ref);

        ref1 = new RevoluteJointDef () ;
        ref1.enableMotor=true;
        ref1.motorSpeed=1;
        ref1.maxMotorTorque=0;
        ref1.bodyA=player11;
        ref1.bodyB=player12;
        ref1.collideConnected=false;
        ref1.localAnchorA.set(9/PPM,-15/PPM);
        lwheel1=(RevoluteJoint) world.createJoint(ref1);

        ref1.bodyB=player13;
        ref1.localAnchorA.set(-9/PPM,-15/PPM);
        rwheel1=(RevoluteJoint) world.createJoint(ref1);

        ref1.bodyB=nalli2;
        ref1.localAnchorA.set(-8/PPM,12/PPM);
        n2=(RevoluteJoint)world.createJoint(ref1);

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
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        world.getBodies(tmpbody);
        //game.batch.draw(tex,player1.getPosition().x,player1.getPosition().y);
        //game.batch.draw(tex,player1.getPosition().x*PPM,player1.getPosition().y*PPM);
        for(Body body : tmpbody){
            if(body.getUserData()!=null && body.getUserData() instanceof Sprite){
                Sprite sprite=(Sprite) body.getUserData();
                sprite.setPosition(body.getPosition().x-sprite.getWidth()/2,body.getPosition().y- sprite.getHeight()/2);
                sprite.setRotation(body.getAngle()* MathUtils.radiansToDegrees);
                sprite.draw(game.batch);
            }
        }
        sp2.draw(game.batch);
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
        if(movingup==true)
        {
            if(barrelAngle<3.14) {
                barrelAngle+=0.05f;
                nalli1.setTransform(player1.getPosition().x, player1.getPosition().y, barrelAngle);
            }
        }
        if(movingdown==true)
        {
            if(barrelAngle>0) {
                barrelAngle-=0.05f;
                nalli1.setTransform(player1.getPosition().x,player1.getPosition().y, barrelAngle);
            }
        }
        if(movingup1==true)
        {

            if(barrelAngle2<=3.14) {
                barrelAngle2+=0.05f;
                nalli2.setTransform(player11.getPosition().x, player11.getPosition().y, barrelAngle2);
            }
        }
        if(movingdown1==true)
        {
            if(barrelAngle2>=0) {
                barrelAngle2-=0.05f;
                nalli2.setTransform(player11.getPosition().x,player11.getPosition().y, barrelAngle2);
            }
        }
        if(movingleft==true) {
            player2.setLinearVelocity(-0.46f,player2.getLinearVelocity().y);
            player3.setLinearVelocity(-0.46f,player3.getLinearVelocity().y);
        }
        if(movingleft1==true) {
            player12.setLinearVelocity(-0.46f,player12.getLinearVelocity().y);
            player13.setLinearVelocity(-0.46f,player13.getLinearVelocity().y);
        }
        if(movingright==true)
        {
            player2.setLinearVelocity(0.46f,player2.getLinearVelocity().y);
            player3.setLinearVelocity(0.46f,player3.getLinearVelocity().y);
        }
        if(movingright1==true)
        {
            player12.setLinearVelocity(0.46f,player12.getLinearVelocity().y);
            player13.setLinearVelocity(0.46f,player13.getLinearVelocity().y);
        }
        if(fire==true)
        {
            //Bull=createbullet(new Sprite(tbull),false,-180,80,false,15,15);
            Bull.applyLinearImpulse(new Vector2(0.2f*MathUtils.cos(barrelAngle),0.2f*MathUtils.sin(barrelAngle)),new Vector2(Bull.getPosition().x,Bull.getPosition().y),true);
            //player13.setLinearVelocity(0.46f,player13.getLinearVelocity().y);
        }
        if(fire2==true)
        {
            //Bull2=createbullet(new Sprite(tbull),false,74,80,false,15,15);
            Bull2.applyLinearImpulse(new Vector2(0.2f*MathUtils.cos(barrelAngle2),0.2f*MathUtils.sin(barrelAngle2)),new Vector2(Bull2.getPosition().x,Bull2.getPosition().y),true);
            //player13.setLinearVelocity(0.46f,player13.getLinearVelocity().y);
        }
        if(movingright1!=true && movingright!=true && movingleft!=true && movingleft1!=true){
            player2.setLinearVelocity(0,player2.getLinearVelocity().y);
            player1.setLinearVelocity(0,player1.getLinearVelocity().y);
            player3.setLinearVelocity(0,player3.getLinearVelocity().y);
            player12.setLinearVelocity(0,player12.getLinearVelocity().y);
            player13.setLinearVelocity(0,player13.getLinearVelocity().y);
            player11.setLinearVelocity(0,player11.getLinearVelocity().y);
            //player2.applyForceToCenter(new Vector2(0,2.8f),true);
            //player3.applyForceToCenter(new Vector2(0,2.8f),true);
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
        if(keycode==Input.Keys.A) {
            movingleft1=true;
        }
        if(keycode==Input.Keys.D){
            movingright1=true;
        }
        if(keycode==Input.Keys.W){
            movingup1=true;
        }
        if(keycode==Input.Keys.S){
            movingdown1=true;
        }
        if(keycode==Input.Keys.SPACE){
            fire=true;
        }
        if(keycode==Input.Keys.ENTER){
            fire2=true;
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
        if(keycode==Input.Keys.A) {
            movingleft1=false;
        }
        if(keycode==Input.Keys.D){
            movingright1=false;
        }
        if(keycode==Input.Keys.W){
            movingup1=false;
        }
        if(keycode==Input.Keys.S){
            movingdown1=false;
        }
        if(keycode==Input.Keys.SPACE){
            fire=false;
        }
        if(keycode==Input.Keys.ENTER){
            fire2=false;
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
        chain.createChain(new Vector2[] {new Vector2(-10.57f,0.23f),new Vector2(-8.8f,0.23f),new Vector2(-7.85f,-1.85f),new Vector2(-5.2f,-1.90f),new Vector2(-4.5f,-1.04f),new Vector2(-2.9f,-0.91f),new Vector2(-0.23f,-1.05f),new Vector2(0.7f,-3.02f),new Vector2(3.5f,-3.02f),new Vector2(4.15f,-1.4f),new Vector2(8.1f,-0.95f),new Vector2(8.5f,-1.18f),new Vector2(8.95f,-1.18f),new Vector2(9.24f,-0.35f),new Vector2(9.24f,6.04f),new Vector2(-10.57f,6.04f),new Vector2(-10.57f,0.23f)});

        FixtureDef fix=new FixtureDef();
        fix.shape=chain;
        fix.friction=0.5f;
        fix.restitution=0f;
        body=world.createBody(def);
        body.createFixture(fix);
        return body;
    }

    public Body createTank(Sprite sp,boolean isStatic,int x,int y,boolean isRotatable,int tankwidth,int tankheight){
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
        shape.setAsBox(tankwidth/4/PPM-4/1/PPM,tankheight/6/PPM);

        FixtureDef fix=new FixtureDef();
        fix.shape=shape;
        fix.friction=.05f;
        fix.restitution=.02f;
        fix.density=2.7f;

        tbody=world.createBody(def);

        tbody.createFixture(fix);

        CircleShape shape1=new CircleShape();
        shape1.setRadius(.1f);

        FixtureDef fix1=new FixtureDef();
        fix1.shape=shape1;
        fix1.friction=.25f;
        fix1.restitution=.02f;
        fix1.density=0f;

        tbody.createFixture(fix1);
        sp.setSize(tankwidth/1/PPM+10/1/PPM,tankheight/1/PPM+12/1/PPM);
        sp.setOrigin(sp.getWidth()/2,sp.getHeight()/2);
        tbody.setUserData(sp);

        shape1.dispose();
        shape.dispose();
        return tbody;
    }
    public Body createwheels(boolean isStatic,int x,int y,boolean isRotatable,int tankwidth,int tankheight)
    {
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
        CircleShape shape1=new CircleShape();
        shape1.setRadius(.19f);

        FixtureDef fix1=new FixtureDef();
        fix1.shape=shape1;
        fix1.friction=10.25f;
        fix1.restitution=.02f;
        fix1.density=18f;

        tbody=world.createBody(def);
        tbody.createFixture(fix1);

        shape1.dispose();
        return tbody;
    }
    public Body createnalli(Sprite sp3,boolean isStatic,int x,int y,boolean isRotatable,int tankwidth,int tankheight)
    {
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
        shape.setAsBox(tankwidth/4/PPM,tankheight/6/PPM);

        FixtureDef fix1=new FixtureDef();
        fix1.shape=shape;
        fix1.friction=10.25f;
        fix1.restitution=.02f;
        fix1.density=1f;

        tbody=world.createBody(def);

        sp3.setSize((float)1.5*tankwidth/1/PPM,4*tankheight/1/PPM);
        sp3.setOrigin(sp3.getWidth()/2,sp3.getHeight()/2);
        tbody.setUserData(sp3);

        tbody.createFixture(fix1);

        shape.dispose();
        return tbody;
    }

    public Body createnalli2(Sprite sp3,boolean isStatic,int x,int y,boolean isRotatable,int tankwidth,int tankheight)
    {
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
        shape.setAsBox(tankwidth/4/PPM,tankheight/6/PPM);

        FixtureDef fix1=new FixtureDef();
        fix1.shape=shape;
        fix1.friction=10.25f;
        fix1.restitution=.02f;
        fix1.density=1f;

        tbody=world.createBody(def);

        sp3.setSize((float)1.25f*tankwidth/1/PPM,(float)1.25f*tankheight/1/PPM);
        sp3.setOrigin(sp3.getWidth()/2,sp3.getHeight()/2);
        tbody.setUserData(sp3);

        tbody.createFixture(fix1);

        shape.dispose();
        return tbody;
    }
    public Body createbullet(Sprite sp3,boolean isStatic,int x,int y,boolean isRotatable,int tankwidth,int tankheight)
    {
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
        shape.setAsBox(tankwidth/4/PPM,tankheight/6/PPM);

        FixtureDef fix1=new FixtureDef();
        fix1.shape=shape;
        fix1.friction=10.25f;
        fix1.restitution=.02f;
        fix1.density=4f;

        tbody=world.createBody(def);

        sp3.setSize((float)1.25f*tankwidth/1/PPM,(float)1.25f*tankheight/2/PPM);
        sp3.setOrigin(sp3.getWidth()/2,sp3.getHeight()/2);
        tbody.setUserData(sp3);

        tbody.createFixture(fix1);

        shape.dispose();
        return tbody;
    }
}
