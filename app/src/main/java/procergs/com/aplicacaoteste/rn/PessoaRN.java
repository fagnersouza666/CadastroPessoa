package procergs.com.aplicacaoteste.rn;

import android.util.Log;

import com.google.gson.Gson;

import procergs.com.aplicacaoteste.clientws.WebServiceClient;
import procergs.com.aplicacaoteste.ed.EnderecoED;
import procergs.com.aplicacaoteste.thread.WebServiceThread;

/**
 * Created by fagnersouza on 26/11/16.
 */

public class PessoaRN {

    public void PessoaRN(){

    }

    public EnderecoED getEndereco(String jsonEndereco){
        EnderecoED enderecoRetorno = null;

        try{
            Gson gson = new Gson();
            enderecoRetorno = gson.fromJson(jsonEndereco, EnderecoED.class);
        }

        catch (Exception e){
            e.printStackTrace();
        }

        return enderecoRetorno;
    }


}
