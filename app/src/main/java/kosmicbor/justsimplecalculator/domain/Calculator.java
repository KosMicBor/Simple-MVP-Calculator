package kosmicbor.justsimplecalculator.domain;

public interface Calculator {
    String calculateResult(Double argOne, Double argTwo, Operations operation);
    double calculatePercent(double value, double percent, Operations operation);
}
