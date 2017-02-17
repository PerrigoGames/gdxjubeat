package com.perrigogames.gdxjubeat.game.numbersllider;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.perrigogames.gdxjubeat.assets.A;

/**
 * Created by corey on 2/10/17.
 */
public class TextNumberCell extends Table {

    private Label label;
    private Integer value;

    public TextNumberCell() {
        setBackground(A.d(A.black));
        label = new Label("", new Label.LabelStyle(A.bFont(A.Fonts.sfBold), Color.WHITE));
        label.setAlignment(Align.center);
        add(label).expand().fillX();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
        label.setText(value == null ? "" : String.valueOf(value));
    }
}
