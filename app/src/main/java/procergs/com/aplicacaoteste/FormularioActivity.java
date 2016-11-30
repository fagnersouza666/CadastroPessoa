package procergs.com.aplicacaoteste;

import android.Manifest;
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
import procergs.com.aplicacaoteste.permissao.PermissaoUtil;
import procergs.com.aplicacaoteste.rn.PessoaRN;


//Formulário de cadastro de pessoa
public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    private PessoaRN pessoaRN = new PessoaRN();
    private EditText campoCep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


        //Solicita permissões de internet para consultar o webservice no CEP
        String[] permissoes = new String[]{
                Manifest.permission.INTERNET
        };

        //Se a permissão for aceita
        if (PermissaoUtil.validade(this, 0, permissoes)){

            campoCep = (EditText) findViewById(R.id.formCep);

            //Helper para a partir de um PessoaED preencher os campos da tela
            helper = new FormularioHelper(this);

            Intent intent = getIntent();
            PessoaED pessoa = (PessoaED) intent.getSerializableExtra("pessoa");

            //Se foi enviado os dados de um registro popula-lo na tela
            if (pessoa !=null) {
                helper.setFormulario(pessoa);
            }

            //Ao sair do campo CEP chama o webservice para popular os campos de endereço
            EditText fCEP = (EditText)findViewById(R.id.formCep);
            fCEP.setOnFocusChangeListener(new View.OnFocusChangeListener(){
                @Override
                public void onFocusChange(View v, boolean hasFocus){
                    if (!hasFocus){
                        chamaWebServiceCEP();
                    }
                }
            });

        }

        //Se a permissão for negada
        else{
            Toast.makeText(FormularioActivity.this, "É preciso permitir acesso da aplicação a internet para buscar dados do seu CEP." , Toast.LENGTH_LONG).show();
        }
    }

    //Monta url do serviço e envia para uma nova thread
    private void chamaWebServiceCEP(){
        try{
            String cep = campoCep.getText().toString().replaceAll("-","");

            String wsText = "https://viacep.com.br/ws/" + cep + "/json/";

            WebServiceThread ws = new WebServiceThread();

            ws.execute(wsText); //Chama nova thread

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

    //Botão no menu para salvar os dados do formulário
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


    //Cria nova thread chama o serviço e popula os campos ao receber os dados
    private class WebServiceThread extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //Popula campos do formulário
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            EnderecoED endereco = pessoaRN.getEndereco(s);

            PessoaED pessoa = new PessoaED();
            pessoa.setEndereco(endereco.getRua());
            pessoa.setBairro(endereco.getBairro());
            pessoa.setCidade(endereco.getCidade());
            pessoa.setEstado(endereco.getEstado());

            helper.setFormularioCEP(pessoa);
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

        //Roda em background
        @Override
        protected String doInBackground(String... urls) {
            try {
                Log.i("WebServiceThread", "Thread - executa webservice.");

                WebServiceClient wsClient = new WebServiceClient(urls[0]);
                String json = wsClient.get();

                return json;
            }
            catch (Exception e) {
                e.printStackTrace();

                return "Problema ao chamar o webservice.";
            }
        }

    }
}
