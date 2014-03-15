package poo;

public class CalculatorImpl implements Calculator {
    @Override
    public int add(int arg1, int arg2) {
        return arg1 + arg2;
    }

    @Override
    public int subtract(int arg1, int arg2) {
        return arg1 - arg2;
    }
}
