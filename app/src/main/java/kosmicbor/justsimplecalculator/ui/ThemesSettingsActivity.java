package kosmicbor.justsimplecalculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;

import kosmicbor.justsimplecalculator.R;
import kosmicbor.justsimplecalculator.domain.Theme;
import kosmicbor.justsimplecalculator.domain.ThemeStorage;

public class ThemesSettingsActivity extends AppCompatActivity {

    private static final String THEME_KEY = "THEME_KEY";

    private ThemeStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = new ThemeStorage(this);
        setTheme(storage.getCurrentTheme().getThemeRes());

        setContentView(R.layout.activity_settings_layout);

        MaterialButton buttonConfirm = findViewById(R.id.confirm_button);
        RadioGroup themeSelector = findViewById(R.id.theme_radio_group);

        themeSelector.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.light_theme_radio_btn) {
                storage.setCurrentTheme(Theme.OPTION_LIGHT, R.drawable.frame_for_texts);
            } else if (checkedId == R.id.dark_theme_radio_btn) {
                storage.setCurrentTheme(Theme.OPTION_DARK, R.drawable.frame_for_texts);
            } else if (checkedId == R.id.advanced_theme_radio_btn) {
                storage.setCurrentTheme(Theme.OPTION_ADVANCED, R.drawable.frame_for_texts_advanced);
            }
        });

        buttonConfirm.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}