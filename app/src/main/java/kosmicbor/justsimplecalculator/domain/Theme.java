package kosmicbor.justsimplecalculator.domain;

import kosmicbor.justsimplecalculator.R;

public enum Theme {

    OPTION_LIGHT("OPTION_LIGHT", R.style.Theme_JustSimpleCalculator),
    OPTION_DARK("OPTION_DARK", R.style.Theme_JustSimpleCalculatorDark),
    OPTION_ADVANCED("OPTION_ADVANCED", R.style.Theme_JustSimpleCalculatorAdvanced);

    private String themeKey;
    private int themeRes;

    Theme(String themeKey, int themeRes) {
        this.themeKey = themeKey;
        this.themeRes = themeRes;
    }

    public String getThemeKey() {
        return themeKey;
    }

    public int getThemeRes() {
        return themeRes;
    }
}
