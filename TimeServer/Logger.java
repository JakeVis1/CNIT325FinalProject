/**
 * This is the class that simply prints timestamps to teh console
 * This class can be implemented, but is mostly used so multiple types of loggers could be added to override
 * the LogTransaction method. This enables polymorphic behavior in the OOServer class.
 * @author Jake Visniski
 */
public class Logger {

    public void LogTransaction(String message)
    {
        System.out.println(message);
    }

}
