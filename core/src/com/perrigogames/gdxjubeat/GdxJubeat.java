package com.perrigogames.gdxjubeat;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.perrigogames.gdxjubeat.assets.A;
import com.perrigogames.gdxjubeat.input.JBKeyboardListener;
import com.perrigogames.gdxjubeat.util.InputLogger;
import com.perrigogames.gdxjubeat.util.Log;

public class GdxJubeat implements ApplicationListener {
	Stage stage;
    JubeatScreen root;
    App app;

    public static final int VIEWPORT_WIDTH = 360;
    public static final int VIEWPORT_HEIGHT = 640;

	@Override
	public void create () {
        app = new App();
        A.initialize(app);
        stage = new Stage(new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
        root = createScreen();
        stage.addActor(root);
        Gdx.input.setInputProcessor(new InputLogger(stage));
        stage.addListener(new JBKeyboardListener(root));
        root.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

    protected JubeatScreen createScreen() {
        return new JubeatScreen();
    }

	@Override
	public void render () {
        Color color = getBackgroundColor();
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
	}

	@Override
	public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }
	
	@Override
	public void dispose () {
        stage.dispose();
        A.destroy();
	}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    protected Color getBackgroundColor() {
        return Color.WHITE;
    }
}
