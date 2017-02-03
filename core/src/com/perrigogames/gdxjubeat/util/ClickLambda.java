package com.perrigogames.gdxjubeat.util;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by corey on 2/3/17.
 */
public class ClickLambda extends ClickListener {

    public L.Listener lambda;


    public ClickLambda(L.Listener lambda) {
        this.lambda = lambda;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        if (lambda != null) lambda.invoke();
    }
}
