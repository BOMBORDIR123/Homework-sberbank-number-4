package URL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;

public class URLReader {

    public static void readContent(String url) {
        try {
            URL urlObj = new URL(url);

            BufferedReader in = new BufferedReader(new InputStreamReader(urlObj.openStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL format. Please try again.");
        } catch (IOException e) {
            System.out.println("Error accessing the URL. Please check the URL or your internet connection.");
        }
    }
}

