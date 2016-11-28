package procergs.com.aplicacaoteste.thread;

import android.os.AsyncTask;

import procergs.com.aplicacaoteste.clientws.WebServiceClient;
import procergs.com.aplicacaoteste.rn.PessoaRN;

/**
 * Created by fagnersouza on 26/11/16.
 */

public class WebServiceThread extends AsyncTask<String, Void, String> {

    public WebServiceThread() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected String doInBackground(String... urls) {
// params comes from the execute() call: params[0] is the url.
        try {
            WebServiceClient wsClient = new WebServiceClient(urls[0]);
            String json = wsClient.get();

            return json;
        }
        catch (Exception e) {
            e.printStackTrace();

            return "Unable to retrieve web page. URL may be invalid.";
        }
    }


}
