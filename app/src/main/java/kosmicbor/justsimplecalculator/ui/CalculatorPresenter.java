package kosmicbor.justsimplecalculator.ui;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import kosmicbor.justsimplecalculator.domain.Calculator;
import kosmicbor.justsimplecalculator.domain.Operations;

public class CalculatorPresenter implements Parcelable {

    private static final int ONE_VALUE = 1;

    private Calculator calculator;
    private CalculatorView view;
    private ArrayList<String> listOfNumbers = new ArrayList<>();
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
        listOfNumbers = in.createStringArrayList();
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

    /**
     * The method gets Number from button pushes and draw it in main frame
     * @param text string value from buttons pushes
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void drawNumberInFrame(String text) {
        listOfNumbers.add(text);
        view.drawResult(getStringOfArray(listOfNumbers));
    }

    /**
     * The method takes string value from resultList
     * @param listOfNumbers ArrayList of string values from buttons pushes
     * @return string value of Arraylist listOfNumbers
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getStringOfArray(ArrayList<String> listOfNumbers) {
        StringBuilder builder = new StringBuilder();
        listOfNumbers.forEach(builder::append);
        return builder.toString();
    }

    /**
     * The method clears main TextView
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void resetButtonClicked() {
        listOfNumbers.clear();
        view.drawResult("0.0");
    }

    /**
     * The method makes number positive or negative
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void positiveNegativeButtonClicked() {
        ArrayList<String> oldResultList = new ArrayList<>(listOfNumbers);

        if (!listOfNumbers.isEmpty()) {

            if (!listOfNumbers.get(0).equals("-")) {
                listOfNumbers.clear();
                listOfNumbers.add("-");
                listOfNumbers.addAll(oldResultList);
            } else {
                listOfNumbers.remove(0);
            }
            view.drawResult(getStringOfArray(listOfNumbers));
        }
    }

    /**
     * The method sets add as arithmetic operation and sets first argument
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addButtonClicked() {
        operation = Operations.ADD;
        arg1Setter();
    }

    /**
     * The method sets subtract as arithmetic operation and sets first argument
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void subtractButtonClicked() {
        operation = Operations.SUB;
        arg1Setter();
    }

    /**
     * The method sets divide as arithmetic operation and sets first argument
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void divideButtonClicked() {
        operation = Operations.DIVIDE;
        arg1Setter();
    }

    /**
     * The method sets multiply as arithmetic operation and sets first argument
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void multiplyButtonClicked() {
        operation = Operations.MULTIPLY;
        arg1Setter();
    }

    /**
     * The method sets second argument from listOfNumbers
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void equalsButtonClicked() {

        if (!listOfNumbers.isEmpty()) {
            arg2 = Double.parseDouble(getStringOfArray(listOfNumbers));
            result = calculator.calculateResult(arg1, arg2, operation);
            view.drawResult(result);

        } else {
            view.drawResult("0.0");
        }
        listOfNumbers.clear();
    }

    /**
     * calculate percent from value
     */
    @SuppressLint("DefaultLocale")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void percentButtonClicked() {
        if (!listOfNumbers.isEmpty()) {
            result = String.format("%.2f", calculator.calculatePercent(arg1,
                    Double.parseDouble(getStringOfArray(listOfNumbers)), operation));
            view.drawResult(result);
            listOfNumbers.clear();
        }

    }

    /**
     * The method processes dot button click
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void dotButtonClicked() {

        if (!listOfNumbers.contains(".")) {
            if (listOfNumbers.isEmpty()) {
                listOfNumbers.add("0.0");
            }
            listOfNumbers.add(".");
            view.drawResult(getStringOfArray(listOfNumbers));
        }
    }

    /**
     * Gets current number for draw from listOfNumbers
     * @return string value of listOfNumbers
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getCurrentValueForDraw() {

        if (!listOfNumbers.isEmpty()) {
            return getStringOfArray(listOfNumbers);
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void arg1Setter() {

        if (!listOfNumbers.isEmpty()) {
            arg1 = Double.parseDouble(getStringOfArray(listOfNumbers));
            listOfNumbers.clear();
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
        dest.writeStringList(listOfNumbers);
        dest.writeDouble(arg1);
        dest.writeDouble(arg2);
        dest.writeString(result);
        dest.writeParcelable((Parcelable) calculator, flags);
    }

    public void setView(CalculatorView view) {
        this.view = view;
    }
}
