package com.tankstars.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class Vidur_DuaContactListner implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Body fa=contact.getFixtureA().getBody();
        Body fb=contact.getFixtureB().getBody();

        if(fa==null || fb==null){return;}
        if(fa.getUserData()==null || fb.getUserData()==null){return;}

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
