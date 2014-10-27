package com.jackzilla.scrumpoker;

import com.jackzilla.scrumpoker.util.SystemUiHider;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class ShowCardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String displayString = "";
        if (getIntent().hasExtra("display_string")) {
            displayString = getIntent().getStringExtra("display_string");
        }

        setContentView(R.layout.activity_show_card);

        final TextView contentView = (TextView) findViewById(R.id.fullscreen_content);
        Character c = new Character('\u2615');
        if (TextUtils.equals(displayString, c.toString())) {
            Log.d(ShowCardActivity.class.getSimpleName(), displayString);
            contentView.setTextSize(70);
        }
        else {
            contentView.setTextSize(200);
        }
        contentView.setText(displayString);
    }
}
