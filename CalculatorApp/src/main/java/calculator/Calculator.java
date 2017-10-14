package calculator;
/**
 * This class preforms calculator functions for Calculator application.
 * Calculator.java
 */

/**
 * This class accepts the inputs from CacluatorUI and process the data and updates the text for the calculator display.
 * @author Kevin Smith
 * @version 1.0
 */
public class Calculator
{
    private static Double previousNumber = 0.0;
    private static Double currentNumber = 0.0;
    private static int numericPlaceValue = 0;
    private static String currentOperand = "";

    /**
     * This method takes string input from the GUI and processes the input according to type of input data.
     * @param input String - Button inputs from CalculatorUI GUI
     */
    public static void addInput(String input)
    {
        switch(input)
        {
            case "0" : addDouble(0.0);
                break;
            case "1" : addDouble(1.0);
                break;
            case "2" : addDouble(2.0);
                break;
            case "3" : addDouble(3.0);
                break;
            case "4" : addDouble(4.0);
                break;
            case "5" : addDouble(5.0);
                break;
            case "6" : addDouble(6.0);
                break;
            case "7" : addDouble(7.0);
                break;
            case "8" : addDouble(8.0);
                break;
            case "9" : addDouble(9.0);
                break;
            case "+" : addOperand(input);
                break;
            case "-" : addOperand(input);
                break;
            case "*" : addOperand(input);
                break;
            case "/" : addOperand(input);
                break;
            case "sin" : addOperand(input);
                break;
            case "cos" : addOperand(input);
                break;
            case "tan" : addOperand(input);
                break;
            case "Enter" : preformCalculation();
                break;
            case "C" : clearCalculator();
                break;
            default: System.out.println("default switch occurred");
                break;
        }
    }

    /**
     * This method returns the current value of the calculator display value.
     * @return String - of current display text value.
     */
    public static String getDisplayLabel()
    {
        return new Double(currentNumber).toString();
    }

    private static void addDouble(Double keyPressNumber)
    {
        if(numericPlaceValue > 0)
        {
            currentNumber = currentNumber * 10 + keyPressNumber;
        }
        else
        {
            currentNumber = keyPressNumber;
        }
        numericPlaceValue++;
    }

    private static void addOperand(String keyPressOperand)
    {
        currentOperand = keyPressOperand;

        if(currentOperand.equals("sin") || currentOperand.equals("cos") || currentOperand.equals("tan"))
        {
            preformCalculation();
        }
        else if(previousNumber == 0.0)
        {
            previousNumber = currentNumber;
            currentNumber = 0.0;
            numericPlaceValue = 0;
        }
        else
        {
            preformCalculation();
        }
    }

    private static void preformCalculation()
    {
        Double answer;
        if(!currentOperand.equals(""))
        {
            if(previousNumber != 0.0)
            {
                switch (currentOperand)
                {
                    case "+" :
                        answer = previousNumber + currentNumber;
                        currentNumber = answer;
                        previousNumber = 0.0;
                        numericPlaceValue = 0;
                        break;
                    case "-" :
                        answer = previousNumber - currentNumber;
                        currentNumber = answer;
                        previousNumber = 0.0;
                        numericPlaceValue = 0;
                        break;
                    case "*" :
                        answer = previousNumber * currentNumber;
                        currentNumber = answer;
                        previousNumber = 0.0;
                        numericPlaceValue = 0;
                        break;
                    case "/" :
                        answer = previousNumber / currentNumber;
                        currentNumber = answer;
                        previousNumber = 0.0;
                        numericPlaceValue = 0;
                        break;
                    default :
                        System.out.println("preform calculation default switch condition occurred");
                        break;
                }
            }
            else
            {
                switch (currentOperand)
                {
                    case "sin" :
                        answer = Math.sin(Math.toRadians(currentNumber));
                        currentNumber = answer;
                        break;
                    case "cos" :
                        answer = Math.cos(Math.toRadians(currentNumber));
                        currentNumber = answer;
                        break;
                    case "tan" :
                        answer = Math.tan(Math.toRadians(currentNumber));
                        currentNumber = answer;
                        break;
                    default :
                        System.out.println("preform calculation default switch condition occurred");
                        break;
                }
            }
            //clear operand after calculation
            currentOperand = "";
        }
        else
        {
            System.out.println("there were not operands");
        }
    }

    private static void clearCalculator()
    {
        previousNumber = 0.0;
        currentNumber = 0.0;
        currentOperand = "";
        numericPlaceValue = 0;
    }
}
