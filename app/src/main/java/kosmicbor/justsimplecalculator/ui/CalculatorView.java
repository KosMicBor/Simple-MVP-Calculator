package kosmicbor.justsimplecalculator.ui;

import android.os.Build;

import androidx.annotation.RequiresApi;

public interface CalculatorView {

    @RequiresApi(api = Build.VERSION_CODES.N)
    void drawResult(String result);
}
