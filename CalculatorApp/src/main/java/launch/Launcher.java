package launch;
/**
 * This Class launches the Calculator application.
 */
import javafx.application.Application;
import ui.CalculatorUI;

/**
 * This is the Calculator application launcher class.
 * @author Kevin Smith
 * @version 1.0
 */
public class Launcher
{
    /**
     * This is the Java main method the entry point for this application.
     * @param args Accepts strings as arguments.
     */
    public static void main(String[] args)
    {
        Application.launch(CalculatorUI.class, args);
    }
}
