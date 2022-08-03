package com.example.lembretescasadamamada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lembretescasadamamada.data.DataBaseHandler;

public class MainActivity extends AppCompatActivity {

    private Button botaoEntrar;
    private EditText caixaNome;
    private EditText caixaSenha;
    private DataBaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setBotaoEntrar(findViewById(R.id.botaoEntrar));
        this.setCaixaNome(findViewById(R.id.caixaNome));
        this.setCaixaSenha(findViewById(R.id.caixaSenha));
        this.db = new DataBaseHandler(MainActivity.this);
    }

    public Button getBotaoEntrar() {
        return botaoEntrar;
    }

    public void setBotaoEntrar(Button botaoEntrar) {
        this.botaoEntrar = botaoEntrar;
    }

    public EditText getCaixaNome() {
        return caixaNome;
    }

    public void setCaixaNome(EditText caixaNome) {
        this.caixaNome = caixaNome;
    }

    public EditText getCaixaSenha() {
        return caixaSenha;
    }

    public void setCaixaSenha(EditText caixaSenha) {
        this.caixaSenha = caixaSenha;
    }

    public void entrarApp(View view){
        if(!this.passouSenha(view)){

        }
        Intent nextActivity = new Intent(MainActivity.this, TelaLembrete.class);
        nextActivity.putExtra("nomeUsuario", getCaixaNome().getText().toString());
        startActivity(nextActivity);
    }

    private boolean passouSenha(View view) {
        return true;
    }
}