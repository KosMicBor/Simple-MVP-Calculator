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

    /**
     * get current theme from sharedPreferences
     * @return
     */
    public Theme getCurrentTheme() {
        String key = sharedPreferences.getString(SHARED_KEY, Theme.OPTION_LIGHT.getThemeKey());

        for (Theme theme : Theme.values()) {
            if (theme.getThemeKey().equals(key)) {
                return theme;
            }
        }

        throw new RuntimeException("Key is not correct");

    }

    /**
     * This method sets current theme. It puts value to sharedPreferences
     * @param theme Enum's Theme class instance
     * @param currentFrameRes resource id for result TextView's background frame
     */
    public void setCurrentTheme(Theme theme, int currentFrameRes) {
        sharedPreferences.edit().putString(SHARED_KEY, theme.getThemeKey()).apply();
        sharedPreferences.edit().putInt(FRAME_KEY, currentFrameRes).apply();
    }

    public int getFrameRes() {
        return sharedPreferences.getInt(FRAME_KEY, R.drawable.frame_for_texts);
    }
}
