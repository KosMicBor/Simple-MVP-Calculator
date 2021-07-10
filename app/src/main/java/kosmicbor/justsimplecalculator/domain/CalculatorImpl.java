package kosmicbor.justsimplecalculator.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class CalculatorImpl implements Calculator, Parcelable {

    private Double result = 0d;

    public CalculatorImpl(Parcel in) {
        if (in.readByte() == 0) {
            result = null;
        } else {
            result = in.readDouble();
        }
    }

    public static final Creator<CalculatorImpl> CREATOR = new Creator<CalculatorImpl>() {
        @Override
        public CalculatorImpl createFromParcel(Parcel in) {
            return new CalculatorImpl(in);
        }

        @Override
        public CalculatorImpl[] newArray(int size) {
            return new CalculatorImpl[size];
        }
    };

    public CalculatorImpl() {

    }

    @Override
    public String calculateResult(Double argOne, Double argTwo, Operations operation) {
        if (operation.equals(Operations.ADD)) {
            result = argOne + (argTwo);
            return result.toString();
        } else if (operation.equals(Operations.SUB)) {
            result = argOne - (argTwo);
            return result.toString();
        } else if (operation.equals(Operations.DIVIDE)) {
            result = argOne / argTwo;
            return result.toString();
        } else if (operation.equals(Operations.MULTIPLY)) {
            result = argOne * argTwo;
            return result.toString();
        } else {
            return "0.0";
        }
    }

    @Override
    public double calculatePercent(double value, double percent, Operations operation) {

        if (operation == Operations.ADD) {

            if (value > 0) {
                return value + ((value / 100) * percent);
            } else {
                return value - ((value / 100) * percent);
            }

        } else if (operation == Operations.SUB) {

            if (value > 0) {
                return value - ((value / 100) * percent);
            } else {
                return value + ((value / 100) * percent);
            }

        } else if (operation == Operations.DIVIDE) {

            if (value > 0) {
                return value / ((value / 100) * percent);
            } else {
                return value / ((-value / 100) * percent);
            }

        } else if (operation == Operations.MULTIPLY) {

            if (value > 0) {
                return value * ((value / 100) * percent);
            } else {
                return value * ((-value / 100) * percent);
            }

        } else {
            return 0d;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (result == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(result);
        }
    }
}
