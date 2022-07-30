package com.example.meunucuconta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button botaoIncrementa;
    private TextView displayValor;
    private int acumulador;
    private EditText valorIncremento;
    /*
        @TODO
            - mudar o nome dessa variavel ai de baixo
     */
    private int valorDeIncremento; //o quanto vai incrementar a cada toque do botao

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setBotaoIncrementa(findViewById(R.id.botaoIncrementa));
        this.setValorIncremento(findViewById(R.id.valorIncremento));

        this.setAcumulador(10000);
        this.setDisplayValor(findViewById(R.id.displayValorAcumulado));

        this.setValorDeIncremento(1000);

        Log.i("Plaintext", "Setei o display: " +
                this.getDisplayValor().getText().toString());
    }

    public Button getBotaoIncrementa() {
        return botaoIncrementa;
    }

    public void setBotaoIncrementa(Button botaoIncrementa) {
        this.botaoIncrementa = botaoIncrementa;
    }

    public TextView getDisplayValor() {
        return displayValor;
    }

    public void setDisplayValor(TextView displayValor) {
        this.displayValor = displayValor;
        this.displayValor.setText(String.valueOf(this.getAcumulador()));
    }

    public int getAcumulador() {
        return acumulador;
    }

    public void setAcumulador(int acumulador) {
        this.acumulador = acumulador;
    }

    public EditText getValorIncremento() {
        return valorIncremento;
    }

    public void setValorIncremento(EditText valorIncremento) {
        this.valorIncremento = valorIncremento;
    }

    public int getValorDeIncremento() {
        return valorDeIncremento;
    }

    public void setValorDeIncremento(int valorDeIncremento) {
        this.valorDeIncremento = valorDeIncremento;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void incrementaValor(View view){

        if(isNumeric(this.getValorIncremento().getText().toString())){
            int inc = Integer.parseInt(
                    this.getValorIncremento().getText().toString()
            );
            this.setValorDeIncremento(inc);
        }

        int novoV = this.getAcumulador() + this.valorDeIncremento;
        this.setAcumulador(novoV);
        this.setDisplayValor(this.getDisplayValor());
    }
}