package org.example;

// Error when b == 0
class DivisionByZeroException extends Exception {
    public DivisionByZeroException() {
        super("Division by zero is impossible.");
    }
}

// Error when number >= (1e308)
class ValueOutOfRangeException extends Exception {
    public ValueOutOfRangeException() {
        super("Number exceeds maximum allowed value (1e308)");
    }
}

// Main calculator
public class SafeCalculator {
    // counts
    private static int operationCount = 0;

    // getCounts
    public static int getOperationCount() {
        return operationCount;
    }

    // '+'
    public double add(double a, double b) throws ValueOutOfRangeException{
        validateInput(a, b);
        operationCount += 1;
        double result = a + b;
        return result;
    }

    // '-'
    public double subtract(double a, double b) throws ValueOutOfRangeException{
        validateInput(a, b);
        operationCount += 1;
        double result = a - b;
        return result;
    }

    // '*'
    public double multiply(double a, double b) throws ValueOutOfRangeException{
        validateInput(a, b);
        operationCount += 1;
        double result = a * b;
        return result;
    }

    // '/'
    public double divide(double a, double b) throws DivisionByZeroException, ValueOutOfRangeException {
        if (b == 0) {
           throw new DivisionByZeroException();
        }
        validateInput(a, b);
        operationCount += 1;
        double result = a / b;
        return result;
    }

    // Checking number
    private void validateInput(double a, double b) throws ValueOutOfRangeException{
        if (a > 1e308 || b > 1e308 || a < -1e308 || b < -1e308) {
            throw new ValueOutOfRangeException();
        }
    }

    // start
    public static void main(String[] args){
        SafeCalculator calc = new SafeCalculator();

        try {
            System.out.println(calc.add(2, 5));         // 1
            System.out.println(calc.multiply(3, 6));    // 2
            System.out.println(calc.subtract(4,6));     // 3
            System.out.println(calc.divide(10, 3));     // 4
            System.out.println(SafeCalculator.getOperationCount());
        }
        catch (DivisionByZeroException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (ValueOutOfRangeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
