package procergs.com.aplicacaoteste.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import procergs.com.aplicacaoteste.ed.PessoaED;

/**
 * Created by fagnersouza on 15/11/16.
 */

public class PessoaDAO extends SQLiteOpenHelper {

    private static final String TABELA = "pessoas";
    private static final int VERSAO = 1;

    public PessoaDAO(Context context) {
        super(context, PessoaDAO.TABELA, null, PessoaDAO.VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + PessoaDAO.TABELA +
                " (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, cep TEXT, endereco TEXT, bairro TEXT, cidade TEXT, estado TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + PessoaDAO.TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(PessoaED pessoa) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosPessoa(pessoa);

        db.insert(PessoaDAO.TABELA, null, dados);

    }

    public List<PessoaED> getPessoas() {
        String sql = "SELECT * FROM " + PessoaDAO.TABELA;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<PessoaED> pessoas = new ArrayList<PessoaED>();
        while (c.moveToNext()) {
            PessoaED pessoa = new PessoaED();
            pessoa.setId(c.getLong(c.getColumnIndex("id")));
            pessoa.setNome(c.getString(c.getColumnIndex("nome")));
            pessoa.setEndereco(c.getString(c.getColumnIndex("endereco")));
            pessoa.setBairro(c.getString(c.getColumnIndex("bairro")));
            pessoa.setCidade(c.getString(c.getColumnIndex("cidade")));
            pessoa.setEstado(c.getString(c.getColumnIndex("estado")));

            pessoas.add(pessoa);

        }
        c.close();

        return pessoas;
    }

    public void deleta(PessoaED pessoa) {
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {pessoa.getId().toString()};
        db.delete(PessoaDAO.TABELA, "id = ?", params);
    }

    public void altera(PessoaED pessoa) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosPessoa(pessoa);

        String[] params ={pessoa.getId().toString()};
        db.update(PessoaDAO.TABELA, dados, "id = ?", params);
    }

    @NonNull
    private ContentValues pegaDadosPessoa(PessoaED pessoa) {
        ContentValues dados = new ContentValues();
        dados.put("nome", pessoa.getNome());
        dados.put("cep", pessoa.getCep());
        dados.put("endereco", pessoa.getEndereco());
        dados.put("bairro", pessoa.getBairro());
        dados.put("cidade", pessoa.getCidade());
        dados.put("estado", pessoa.getEstado());

        return dados;
    }
}
