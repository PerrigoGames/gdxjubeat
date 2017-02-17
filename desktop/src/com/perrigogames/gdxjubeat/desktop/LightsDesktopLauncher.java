package com.perrigogames.gdxjubeat.desktop;

import com.perrigogames.gdxjubeat.GdxJubeat;
import com.perrigogames.gdxjubeat.game.lights.LightsApp;

/**
 * Created by corey on 2/8/17.
 */
public class LightsDesktopLauncher extends DesktopLauncher {

    public static void main (String[] args) {
        new LightsDesktopLauncher().parseArgs(args).start();
    }

    @Override
    protected GdxJubeat createApp() {
        return new LightsApp();
    }
}
