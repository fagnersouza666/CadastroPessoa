package procergs.com.aplicacaoteste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import procergs.com.aplicacaoteste.ED.PessoaED;
import procergs.com.aplicacaoteste.dao.PessoaDAO;

public class PrincipalActivity extends AppCompatActivity {

    ListView listaPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        listaPessoas = (ListView) findViewById(R.id.lista_pessoas);

        listaPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View item, int position, long id) {
                PessoaED pessoa = (PessoaED)  listaPessoas.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(PrincipalActivity.this, FormularioActivity.class);
                intentVaiProFormulario.putExtra("pessoa", pessoa);
                startActivity(intentVaiProFormulario);
            }
        });

        Button bNovoPessoa = (Button) findViewById(R.id.novaPessoa);
        bNovoPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiproFormulario = new Intent(PrincipalActivity.this, FormularioActivity.class);
                startActivity(intentVaiproFormulario);
            }
        });

        registerForContextMenu(listaPessoas);

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        PessoaDAO pessoaDAO = new PessoaDAO(this);
        List<PessoaED> pessoas = pessoaDAO.getPessoas();
        pessoaDAO.close();

        ArrayAdapter<PessoaED> adapter = new ArrayAdapter<PessoaED>(this, android.R.layout.simple_list_item_1, pessoas);

        listaPessoas.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuItem remover =  menu.add("Remover");

        MenuItem menuItem = remover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                PessoaED pessoa = (PessoaED) listaPessoas.getItemAtPosition(info.position);

                PessoaDAO dao = new PessoaDAO(PrincipalActivity.this);
                dao.deleta(pessoa);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }
}
