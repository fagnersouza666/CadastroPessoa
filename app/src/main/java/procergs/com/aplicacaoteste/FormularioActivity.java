package procergs.com.aplicacaoteste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import procergs.com.aplicacaoteste.ED.PessoaED;
import procergs.com.aplicacaoteste.dao.PessoaDAO;
import procergs.com.aplicacaoteste.helper.FormularioHelper;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

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
}
