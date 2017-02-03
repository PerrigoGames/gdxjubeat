package com.perrigogames.gdxjubeat;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.perrigogames.gdxjubeat.util.InputLogger;

public class GdxJubeat implements ApplicationListener {
	Stage stage;
    JubeatScreen root;

    public static final int VIEWPORT_WIDTH = 360;
    public static final int VIEWPORT_HEIGHT = 640;

	@Override
	public void create () {
        stage = new Stage(new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
        root = new JubeatScreen();
        stage.addActor(root);
        InputLogger debugInput = new InputLogger(stage);
//        debugInput.setLogKeys(true);
        Gdx.input.setInputProcessor(debugInput);
        root.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
	}

	@Override
	public void resize (int width, int height) {}
	
	@Override
	public void dispose () {
        stage.dispose();
	}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
