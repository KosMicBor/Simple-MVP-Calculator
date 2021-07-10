package kosmicbor.justsimplecalculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;

import kosmicbor.justsimplecalculator.R;
import kosmicbor.justsimplecalculator.domain.Theme;
import kosmicbor.justsimplecalculator.domain.ThemeStorage;

public class ThemesSettingsActivity extends AppCompatActivity {

    private ThemeStorage storage;
    private static final String THEME_KEY = "THEME_KEY";

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
                storage.setCurrentTheme(Theme.OPTION_LIGHT);
            } else if (checkedId == R.id.dark_theme_radio_btn) {
                storage.setCurrentTheme(Theme.OPTION_DARK);
            } else if (checkedId == R.id.advanced_theme_radio_btn) {
                storage.setCurrentTheme(Theme.OPTION_ADVANCED);
            }
        });

        buttonConfirm.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}