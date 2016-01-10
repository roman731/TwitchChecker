import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;

public class Checker extends TimerTask  {

    private static String inputString = null;
    public static Timer timer;

    public static void timerFunction()
    {
        timer = new Timer(false);
        timer.schedule(new Checker(), 0, 60000);
    }
    public static void stopTimer()
    {
        timer.cancel();
    }

    public void run()
    {
        Platform.runLater(() -> {
            boolean errorCaught = false;

            //the string received from the Twitch API
            String API_String = null;

            try
            {
                //go to the API site and read it into API_String
                URL myURL = new URL("https://api.twitch.tv/kraken/streams/" + getInputString());
                URLConnection yc = myURL.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

                API_String = in.readLine();
            }
            catch(FileNotFoundException x)
            {
                errorCaught = true;
            }
            catch(IOException x)
            {
                errorCaught = true;
            }
            catch(Exception x)
            {
                errorCaught = true;
            }
            finally
            {
                if(errorCaught == false)
                {
                    if (API_String == null) {
                   //     System.out.println("OFFLINE NULL");
                        Main.updateStatus(false);
                    } else if (!(API_String.contains("\"stream\":null") || API_String.contains("\"error\""))) {
                        //System.out.println("STREAM IS ONLINE");
                        Main.updateStatus(true);
                    } else {
                        //System.out.println("STREAM IS OFFLINE");
                        Main.updateStatus(false);
                    }
                }
                else{
                    Main.updateStatus(false);
                    //System.out.println("Non existent stream");
                }
            }
        });
    }
    //getters and setters
    public static void setInputString(String x)
    {
        inputString = x;
    }
    public static String getInputString()
    {
        return inputString;
    }
}
