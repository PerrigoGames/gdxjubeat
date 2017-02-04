package com.perrigogames.gdxjubeat.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.perrigogames.gdxjubeat.GdxJubeat;
import com.perrigogames.gdxjubeat.util.Log;

/** Desktop launcher class for gdxjubeat
 *
 * Usage:  java gdxjubeat [-w]
 *
 * -w: run the game in windowed mode */
public class DesktopLauncher {

	public static void main (String[] args) {
		new DesktopLauncher().parseArgs(args).start();
	}

	boolean fullscreen = true;

	DesktopLauncher start() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = GdxJubeat.VIEWPORT_WIDTH;
        config.height = GdxJubeat.VIEWPORT_HEIGHT;
        config.fullscreen = fullscreen;
        new LwjglApplication(new GdxJubeat(), config);
        return this;
    }

    DesktopLauncher parseArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch(args[i]) {
                case "-w":
                case "-W":
                    fullscreen = false;
                    break;
                default:
                    Log.v("Argument \"%s\" is unsupported", args[i]);
                    break;
            }
        }
        return this;
    }
}
