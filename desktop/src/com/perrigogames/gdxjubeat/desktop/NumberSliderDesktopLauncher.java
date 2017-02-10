package com.perrigogames.gdxjubeat.desktop;

import com.perrigogames.gdxjubeat.GdxJubeat;
import com.perrigogames.gdxjubeat.game.numbersllider.NumberSliderApp;
import com.perrigogames.gdxjubeat.util.Log;

/**
 * Created by corey on 2/8/17.
 */
public class NumberSliderDesktopLauncher extends DesktopLauncher {

    public static void main (String[] args) {
        new NumberSliderDesktopLauncher().parseArgs(args).start();
    }

    @Override
    protected GdxJubeat createApp() {
        return new NumberSliderApp();
    }
}
