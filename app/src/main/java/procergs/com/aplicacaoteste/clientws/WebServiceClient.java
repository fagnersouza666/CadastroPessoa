package procergs.com.aplicacaoteste.clientws;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


/**
 * Created by fagnersouza on 26/11/16.
 */

public class WebServiceClient {

    private String urlWS;

    public WebServiceClient(String urlWS){
        this.urlWS = urlWS;
    }

    //POST
    public String post(String json) throws IOException {

        URL url = new URL(urlWS);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        connection.setRequestProperty("Content-type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        connection.setDoOutput(true);


        PrintStream printStream = new PrintStream(connection.getOutputStream());
        printStream.println(json);

        connection.connect();

        String jsonDeResposta = new Scanner(connection.getInputStream()).next();

        return jsonDeResposta;
    }

    //GET
    public String get() {
        String jsonDeResposta = "";

        try {

            URL url = new URL(urlWS);
            Log.i("get", urlWS);
  //          HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            URLConnection urlConnection = url.openConnection();
            InputStream in = urlConnection.getInputStream();
  //          copyInputStreamToOutputStream(in, System.out);
   //         connection.setRequestMethod("GET");

   //         connection.setRequestProperty("Accept", "application/json");

      //      connection.connect();

            jsonDeResposta = new Scanner(in).next();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return jsonDeResposta;
    }

}