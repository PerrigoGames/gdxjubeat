package com.perrigogames.gdxjubeat.assets;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.perrigogames.gdxjubeat.App;

/**
 * Created by corey on 2/7/17.
 */
public class A {

    public static class Fonts {

        public static void loadFonts() {
            app.assets.load(sfBold, BitmapFont.class, loaderParameter(sfBold, 12));
            app.assets.load(sfRegular, BitmapFont.class, loaderParameter(sfRegular, 12));
            app.assets.load(sfThin, BitmapFont.class, loaderParameter(sfThin, 12));
            app.assets.load(sfUltralight, BitmapFont.class, loaderParameter(sfUltralight, 12));
        }

        public static FreeTypeFontLoaderParameter loaderParameter(String name, int size) {
            FreeTypeFontLoaderParameter param = new FreeTypeFontLoaderParameter();
            param.fontFileName = name;
            param.fontParameters.size = size;
            return param;
        }

        public static final String sfBold = "fonts/SF_bold.ttf";
        public static final String sfRegular = "fonts/SF_regular.ttf";
        public static final String sfThin = "fonts/SF_thin.ttf";
        public static final String sfUltralight = "fonts/SF_ultralight.ttf";
    }

    private static App app;

    public static void initialize (App app) {
        A.app = app;
        FileHandleResolver resolver = new InternalFileHandleResolver();
        app.assets.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        app.assets.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
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

    public static BitmapFont bFont (String path) {
        return app.assets.get(path, BitmapFont.class);
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
