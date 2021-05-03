import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This is the class that logs the timestamps in a text file
 * Inherits from the Logger Class
 * @author Jake Visniski
 */

public class TextLogger extends Logger{
    @Override
    public void LogTransaction(String message)
    {
        File file = new File( "Log.txt");
        if (!file.exists()) {
            try
            {
                file.createNewFile();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        try
        {
            FileWriter writer = new FileWriter(file, true);
            writer.write(message + "\n");
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
