package procergs.com.aplicacaoteste.helper;

import android.widget.EditText;

import procergs.com.aplicacaoteste.ed.PessoaED;
import procergs.com.aplicacaoteste.FormularioActivity;
import procergs.com.aplicacaoteste.R;

/**
 * Created by fagnersouza on 14/11/16.
 */

public class FormularioHelper {

    EditText campoNome;
    EditText campoEndereco;
    EditText campoCep;
    EditText campoBairro;
    EditText campoCidade;
    EditText campoEstado;

    private PessoaED pessoa;

    public FormularioHelper(FormularioActivity activity)  {
        campoNome = (EditText) activity.findViewById(R.id.formNome);
        campoEndereco = (EditText) activity.findViewById(R.id.formEndereco);
        campoCep = (EditText) activity.findViewById(R.id.formCep);
        campoBairro = (EditText) activity.findViewById(R.id.formBairro);
        campoCidade = (EditText) activity.findViewById(R.id.formCidade);
        campoEstado = (EditText) activity.findViewById(R.id.formEstado);

        pessoa = new PessoaED();
    }

    public PessoaED getPessoa() {

        pessoa.setNome(campoNome.getText().toString());
        pessoa.setEndereco(campoEndereco.getText().toString());
        pessoa.setCep(campoCep.getText().toString());
        pessoa.setBairro(campoBairro.getText().toString());
        pessoa.setCidade(campoCidade.getText().toString());
        pessoa.setEstado(campoEstado.getText().toString());

        return pessoa;

    }

    public void setFormulario(PessoaED pessoa) {

        campoNome.setText(pessoa.getNome());
        campoEndereco.setText(pessoa.getEndereco());
        campoCep.setText(pessoa.getCep());
        campoBairro.setText(pessoa.getBairro());
        campoCidade.setText(pessoa.getCidade());
        campoEstado.setText(pessoa.getEstado());

        this.pessoa = pessoa;
    }
}
