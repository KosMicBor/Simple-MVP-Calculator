package kosmicbor.justsimplecalculator.ui;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;

import kosmicbor.justsimplecalculator.R;
import kosmicbor.justsimplecalculator.domain.CalculatorImpl;
import kosmicbor.justsimplecalculator.domain.ThemeStorage;

public class MainCalculatorActivity extends AppCompatActivity implements CalculatorView {

    private static final String PRESENTER_KEY = "PRESENTER_KEY";
    private static final int INTENT_KEY = 99;
    private CalculatorPresenter presenter;
    private final ArrayList<Integer> buttonsIdList = new ArrayList<>(Arrays.asList(R.id.btn_zero, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_dot,
            R.id.btn_equals, R.id.btn_add, R.id.btn_subtract, R.id.btn_divide, R.id.btn_multiply,
            R.id.btn_reset, R.id.btn_positive_negative, R.id.btn_percent));

    private TextView resultFrame;
    private ThemeStorage storage;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PRESENTER_KEY, presenter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = new ThemeStorage(this);
        setTheme(storage.getCurrentTheme().getThemeRes());

        setContentView(R.layout.activity_main);
        resultFrame = findViewById(R.id.result_field);
        if (savedInstanceState == null) {
            presenter = new CalculatorPresenter(new CalculatorImpl(), this);
        } else {
            presenter = savedInstanceState.getParcelable("PRESENTER_KEY");
            drawResult(presenter.getCurrentValueForDraw());
            presenter.setView(this);
        }

        resultFrame.setBackgroundResource(storage.getFrameRes());

        for (int elem : buttonsIdList) {
            Button currentButton = findViewById(elem);
            currentButton.setOnClickListener(v -> {

                if (elem == R.id.btn_positive_negative) {
                    presenter.positiveNegativeButtonClicked();
                } else if (elem == R.id.btn_reset) {
                    presenter.resetButtonClicked();
                } else if (elem == R.id.btn_add) {
                    presenter.addButtonClicked();
                } else if (elem == R.id.btn_subtract) {
                    presenter.subtractButtonClicked();
                } else if (elem == R.id.btn_divide) {
                    presenter.divideButtonClicked();
                } else if (elem == R.id.btn_multiply) {
                    presenter.multiplyButtonClicked();
                } else if (elem == R.id.btn_equals) {
                    presenter.equalsButtonClicked();
                } else if (elem == R.id.btn_percent) {
                    presenter.percentButtonClicked();
                } else if (elem == R.id.btn_dot) {
                    presenter.dotButtonClicked();
                } else {
                    String currentNumber = String.valueOf(buttonsIdList.indexOf(elem));
                    presenter.addNumberToList(currentNumber);
                }
            });
        }
    }

    @Override
    public void drawResult(String result) {
        resultFrame.setText(result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.themes_menu_item) {
            Intent intent = new Intent(this, ThemesSettingsActivity.class);
            startActivityForResult(intent, INTENT_KEY);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INTENT_KEY) {
            if (resultCode == RESULT_OK) {
                recreate();
            }
        }

    }
}