package procergs.com.aplicacaoteste.rn;

import android.util.Log;

import com.google.gson.Gson;

import procergs.com.aplicacaoteste.clientws.WebServiceClient;
import procergs.com.aplicacaoteste.ed.EnderecoED;


/**
 * Created by fagnersouza on 26/11/16.
 */

public class PessoaRN {

    public void PessoaRN(){

    }

    //Retorna um EnderecoED populado pelo json recebido
    public EnderecoED getEndereco(String jsonEndereco){
        EnderecoED enderecoRetorno = null;

        try{
            Log.i("PessoaRN-", jsonEndereco);

            Gson gson = new Gson();
            enderecoRetorno = gson.fromJson(jsonEndereco, EnderecoED.class); // converte de json para EnderecoED

        }

        catch (Exception e){
            e.printStackTrace();
        }

        return enderecoRetorno;
    }


}
