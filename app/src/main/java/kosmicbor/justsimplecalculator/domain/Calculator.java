package kosmicbor.justsimplecalculator.domain;

public interface Calculator {
    /**
     * This method perform arithmetic operations
     * @param argOne Double value appears as the first argument of arithmetic operations
     * @param argTwo Double value appears as the second argument of arithmetic operations
     * @param operation instance of Enum class contains arithmetic operations types
     * @return string result value of arithmetic operation
     */
    String calculateResult(Double argOne, Double argTwo, Operations operation);

    /**
     * This method perform percent calculation
     * @param value number which from percent takes (first argument of operation)
     * @param percent percent value which takes from value
     * @param operation instance of Enum class contains arithmetic operations types
     * @return double result value of percent operation
     */
    double calculatePercent(double value, double percent, Operations operation);
}
