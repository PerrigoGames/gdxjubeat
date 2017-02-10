package com.perrigogames.gdxjubeat.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.perrigogames.gdxjubeat.App;

/**
 * Created by corey on 2/7/17.
 */
public class A {

    private static App app;

    public static void initialize (App app) {
        A.app = app;
    }

    public static void destroy () {
        A.app = null;
    }

    public static Drawable d (String path) {
        return new SpriteDrawable(spr(path));
    }

    public static Sprite spr (String path) {
        return new Sprite(tex(path));
    }

    public static Texture tex (String path) {
        return app.assets.get(path, Texture.class);
    }

    public static void load (String path, Class clazz) {
        app.assets.load(path, clazz);
    }

    public static void finishLoading() {
        app.assets.finishLoading();
    }

    public static final String badlogic = "badlogic.png";
    public static final String black = "black.png";
    public static final String white = "white.png";
}
