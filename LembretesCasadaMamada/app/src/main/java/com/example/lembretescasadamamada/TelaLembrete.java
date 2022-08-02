package com.example.lembretescasadamamada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lembretescasadamamada.data.DataBaseHandler;
import com.example.lembretescasadamamada.model.UsuarioLembrete;

public class TelaLembrete extends AppCompatActivity {

    private TextView nomeUsuario;
    private TextView lembretesSalvos;
    private Button botaoSalvar;
    private DataBaseHandler db;
    private String nomeChave;
    private EditText lembreteParaSalvar;
    private CheckBox dropCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lembrete);

        this.setNomeUsuario(findViewById(R.id.nomeUsuario));
        this.setLembreteParaSalvar(findViewById(R.id.lembreteInput));
        this.setLembretesSalvos(findViewById(R.id.listaLembretes));
        this.setBotaoSalvar(findViewById(R.id.botaoSalvar));
        this.setDropCheck(findViewById(R.id.dropCheck));

        this.db = new DataBaseHandler(TelaLembrete.this);
        this.setNomeChave(this.getIntent().getStringExtra("nomeUsuario"));
        this.getNomeUsuario().setText(this.getNomeChave());
        this.atualizaCaixaTexto();
    }

    public CheckBox getDropCheck() {
        return dropCheck;
    }

    public void setDropCheck(CheckBox dropCheck) {
        this.dropCheck = dropCheck;
    }

    public EditText getLembreteParaSalvar() {
        return lembreteParaSalvar;
    }

    public void setLembreteParaSalvar(EditText lembreteParaSalvar) {
        this.lembreteParaSalvar = lembreteParaSalvar;
    }

    public TextView getLembretesSalvos() {
        return lembretesSalvos;
    }

    public void setLembretesSalvos(TextView lembretesSalvos) {
        this.lembretesSalvos = lembretesSalvos;
    }

    public String getNomeChave() {
        return nomeChave;
    }

    public void setNomeChave(String nomeChave) {
        this.nomeChave = nomeChave;
    }

    public Button getBotaoSalvar() {
        return botaoSalvar;
    }

    public void setBotaoSalvar(Button botaoSalvar) {
        this.botaoSalvar = botaoSalvar;
    }

    public TextView getNomeUsuario() {
        return this.nomeUsuario;
    }

    public void setNomeUsuario(TextView nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }


    public void salvarLembrete(View v) {

        if (this.getDropCheck().isChecked()) {
            db.dropDB();
            db.criarTabela();
            this.finish(); //volta para a tela anterior!
        } else {
            UsuarioLembrete lembrete = new UsuarioLembrete();
            lembrete.setNomeCompleto(this.getNomeChave());
            lembrete.setLembrete(this.getLembreteParaSalvar().getText().toString() + "\n");
            db.addLembrete(lembrete);
            this.atualizaCaixaTexto();
        }

    }

    private void atualizaCaixaTexto() {
        String resposta = db.getLembretes(this.getNomeChave());
        Log.i("Plaintext", "here" + resposta);
        this.getLembretesSalvos().setText(resposta);
    }
}