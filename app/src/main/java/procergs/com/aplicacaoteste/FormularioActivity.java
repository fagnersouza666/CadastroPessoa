package procergs.com.aplicacaoteste;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import procergs.com.aplicacaoteste.clientws.WebServiceClient;
import procergs.com.aplicacaoteste.dao.PessoaDAO;
import procergs.com.aplicacaoteste.ed.EnderecoED;
import procergs.com.aplicacaoteste.ed.PessoaED;
import procergs.com.aplicacaoteste.helper.FormularioHelper;
import procergs.com.aplicacaoteste.rn.PessoaRN;


public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    private String urlWS = "https://viacep.com.br/ws/90830240/json/";
    private PessoaRN pessoaRN = new PessoaRN();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        PessoaED pessoa = (PessoaED) intent.getSerializableExtra("pessoa");
        if (pessoa !=null) {
            helper.setFormulario(pessoa);
        }

        final EditText fEndereco = (EditText) findViewById(R.id.formEndereco);

        EditText fCEP = (EditText)findViewById(R.id.formCep);
        fCEP.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus){
/*                    EnderecoED endereco =  pessoaRN.getEndereco("https://viacep.com.br/ws/90830240/json/");
                    fEndereco.setText(endereco.getLougradouro());*/

                    chamaWebServiceCEP();
                }

            }
        });
    }

    private void chamaWebServiceCEP(){
        try{
            WebServiceThread ws = new WebServiceThread();

            ws.execute(urlWS);

        }

        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_formulario_ok:

                PessoaED pessoa = helper.getPessoa();

                PessoaDAO pessoaDAO = new PessoaDAO(this);
                if (pessoa.getId() != null)  {
                    pessoaDAO.altera(pessoa);
                } else {
                    pessoaDAO.insere(pessoa);
                }
                pessoaDAO.close();

                Toast.makeText(FormularioActivity.this, "Registro salvo!" , Toast.LENGTH_SHORT).show();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private class WebServiceThread extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            EnderecoED endereco = pessoaRN.getEndereco(s);

            PessoaED pessoa = new PessoaED();
            pessoa.setEndereco(endereco.getLougradouro());
            pessoa.setBairro(endereco.getBairro());
            pessoa.setCidade(endereco.getLocalidade());
            pessoa.setEstado(endereco.getUf());
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
                Log.i("WebServiceThread", "Thread - executa webservice.");

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
}
