package com.perrigogames.gdxjubeat.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.perrigogames.gdxjubeat.GdxJubeat;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GdxJubeat.VIEWPORT_WIDTH;
		config.height = GdxJubeat.VIEWPORT_HEIGHT;
		new LwjglApplication(new GdxJubeat(), config);
	}
}
