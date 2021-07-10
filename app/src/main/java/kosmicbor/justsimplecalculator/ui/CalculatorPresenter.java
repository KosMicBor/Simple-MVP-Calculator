package kosmicbor.justsimplecalculator.ui;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import kosmicbor.justsimplecalculator.domain.Calculator;
import kosmicbor.justsimplecalculator.domain.Operations;

public class CalculatorPresenter implements Parcelable {

    private final int ONE_VALUE = 1;

    private Calculator calculator;
    private CalculatorView view;
    private ArrayList<String> resultList = new ArrayList<>();
    private String result = "0.0";
    private double arg1 = 0d;
    private double arg2 = 0d;
    private Operations operation = Operations.ADD;

    public CalculatorPresenter(Calculator calculator, CalculatorView view) {
        this.calculator = calculator;
        this.view = view;
    }

    protected CalculatorPresenter(Parcel in) {
        result = in.readString();
        resultList = in.createStringArrayList();
        arg1 = in.readDouble();
        arg2 = in.readDouble();
    }

    public static final Creator<CalculatorPresenter> CREATOR = new Creator<CalculatorPresenter>() {
        @Override
        public CalculatorPresenter createFromParcel(Parcel in) {
            return new CalculatorPresenter(in);
        }

        @Override
        public CalculatorPresenter[] newArray(int size) {
            return new CalculatorPresenter[size];
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addNumberToList(String text) {
        resultList.add(text);
        view.drawResult(getStringOfArray(resultList));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getStringOfArray(ArrayList<String> resultList) {
        StringBuilder builder = new StringBuilder();
        resultList.forEach(builder::append);
        return builder.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void resetButtonClicked() {
        resultList.clear();
        view.drawResult("0.0");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void positiveNegativeButtonClicked() {
        ArrayList<String> oldResultList = new ArrayList<>(resultList);

        if (!resultList.isEmpty()) {

            if (!resultList.get(0).equals("-")) {
                resultList.clear();
                resultList.add("-");
                resultList.addAll(oldResultList);
                view.drawResult(getStringOfArray(resultList));
            } else {
                resultList.remove(0);
                view.drawResult(getStringOfArray(resultList));
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addButtonClicked() {
        operation = Operations.ADD;
        arg1Setter();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void subtractButtonClicked() {
        operation = Operations.SUB;
        arg1Setter();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void divideButtonClicked() {
        operation = Operations.DIVIDE;
        arg1Setter();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void multiplyButtonClicked() {
        operation = Operations.MULTIPLY;
        arg1Setter();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void equalsButtonClicked() {

        if (!resultList.isEmpty()) {
            arg2 = Double.parseDouble(getStringOfArray(resultList));
            result = calculator.calculateResult(arg1, arg2, operation);
            view.drawResult(result);

        } else {
            view.drawResult("0.0");
        }
        resultList.clear();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void percentButtonClicked() {
        if (!resultList.isEmpty()) {
            result = String.valueOf(calculator.calculatePercent(arg1,
                    Double.parseDouble(getStringOfArray(resultList)), operation));
            view.drawResult(result);
            resultList.clear();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void dotButtonClicked() {

        if (!resultList.contains(".")) {
            if (resultList.isEmpty()) {
                resultList.add("0.0");
            }
            resultList.add(".");
            view.drawResult(getStringOfArray(resultList));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getCurrentValueForDraw() {

        if (!resultList.isEmpty()) {
            return getStringOfArray(resultList);
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void arg1Setter() {

        if (!resultList.isEmpty()) {
            arg1 = Double.parseDouble(getStringOfArray(resultList));
            resultList.clear();
        } else {
            arg1 = Double.parseDouble(result);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ONE_VALUE);
        dest.writeStringList(resultList);
        dest.writeDouble(arg1);
        dest.writeDouble(arg2);
        dest.writeString(result);
        dest.writeParcelable((Parcelable) calculator, flags);
    }

    public void setView(CalculatorView view) {
        this.view = view;
    }
}
