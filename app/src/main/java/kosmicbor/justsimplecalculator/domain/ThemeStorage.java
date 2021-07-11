package kosmicbor.justsimplecalculator.domain;

import android.content.Context;
import android.content.SharedPreferences;

import kosmicbor.justsimplecalculator.R;

public class ThemeStorage {
    private final String SHARED_KEY = "SHARED_KEY";
    private static final String FRAME_KEY = "FRAME_KEY";

    private final SharedPreferences sharedPreferences;

    public ThemeStorage(Context context) {
        sharedPreferences = context.getSharedPreferences("ThemePreferences", Context.MODE_PRIVATE);
    }

    public Theme getCurrentTheme() {
        String key = sharedPreferences.getString(SHARED_KEY, Theme.OPTION_LIGHT.getThemeKey());

        for (Theme theme : Theme.values()) {
            if (theme.getThemeKey().equals(key)) {
                return theme;
            }
        }

        throw new RuntimeException("Key is not correct");

    }

    public void setCurrentTheme(Theme theme, int currentFrameRes) {
        sharedPreferences.edit().putString(SHARED_KEY, theme.getThemeKey()).apply();
        sharedPreferences.edit().putInt(FRAME_KEY, currentFrameRes).apply();
    }


    public int getFrameRes() {
        return sharedPreferences.getInt(FRAME_KEY, R.drawable.frame_for_texts);
    }
}
