package kosmicbor.justsimplecalculator.domain;

import android.content.Context;
import android.content.SharedPreferences;

public class ThemeStorage {
    private final String SHARED_KEY = "SHARED_KEY";

    private final Context context;
    private SharedPreferences sharedPreferences;

    public ThemeStorage(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("ThemePreferences", Context.MODE_PRIVATE);
    }

    public Theme getCurrentTheme() {
        String key = sharedPreferences.getString(SHARED_KEY, Theme.OPTION_LIGHT.getThemeKey());

        for (Theme theme: Theme.values()) {
            if (theme.getThemeKey().equals(key)){
                return theme;
            }
        }

        throw new RuntimeException ("Key is not correct");

    }

    public void setCurrentTheme(Theme theme) {
        sharedPreferences.edit().putString(SHARED_KEY, theme.getThemeKey()).apply();
    }
}
