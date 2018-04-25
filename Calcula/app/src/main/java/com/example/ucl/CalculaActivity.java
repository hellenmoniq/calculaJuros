package com.example.ucl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by UCL on 24/04/2018.
 */
public class CalculaActivity extends AppCompatActivity {

    private EditText txtnome;
    private EditText txtvalorinicial;
    private EditText txtaplicaomensal;
    private EditText txttempoaplicacao;
    private EditText txttaxa;
    private Button btnCalcular;
    private Button btnLista;
    private TextView txtvalorfinal;

    private double valorinicial;
    private double aplicacaomensal;
    private double tempoaplicacao;
    private double taxa;

    private Helper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcula);

        db = new Helper(this);

        //buscando no formulário os campos e armanezando nas variáveis
        txtnome = (EditText) findViewById(R.id.txtnome);
        txtvalorinicial = (EditText) findViewById(R.id.txtvalorinicial);
        txtaplicaomensal = (EditText) findViewById(R.id.txtaplicacaomensal);
        txttempoaplicacao = (EditText) findViewById(R.id.txttempoaplicacao);
        txttaxa = (EditText) findViewById(R.id.txttaxa);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnLista = (Button) findViewById(R.id.btnlista);
        txtvalorfinal = (TextView) findViewById(R.id.txtvalorfinal);

        //criando o metodo para ao clicar no botão calcular
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //passando de string para double as variáveis
                valorinicial = Double.parseDouble(txtvalorinicial.getText().toString());
                aplicacaomensal = Double.parseDouble(txtaplicaomensal.getText().toString());
                tempoaplicacao = Double.parseDouble(txttempoaplicacao.getText().toString());
                taxa = Double.parseDouble(txttaxa.getText().toString());

                //chamo o metodo calcula rendimento passando os parametros do calculo dos juros composto
                Double rendimento = calcularRendimento(valorinicial, aplicacaomensal, tempoaplicacao, taxa);

                //mostra no textview o resultado
                txtvalorfinal.setText(String.valueOf(rendimento));

                ItemCalcula item = new ItemCalcula();
                item.setName(txtnome.getText().toString());
                item.setValorinit(String.valueOf(valorinicial));
                item.setAlicacaomensal(String.valueOf(aplicacaomensal));
                item.setTempoaplicacao(String.valueOf(tempoaplicacao));
                item.setTaxa(String.valueOf(taxa));
                item.setValorfinal(String.valueOf(rendimento));

                insertInDb(item);
            }
        });

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalculaActivity.this, ListActivity.class));
            }
        });
    }


    // função para calcular o rendimento
    private double calcularRendimento(double valorinicial, double aplicacaomensal, double tempoaplicacao, double taxa) {
        return (valorinicial + (aplicacaomensal * tempoaplicacao) / taxa);
    }

    private void insertInDb(ItemCalcula item) {
        long result = db.insertItem(item);

        if (result != -1) {
            Toast.makeText(this, "Registro Inserido com Sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Houve um problema ao inserir o registro", Toast.LENGTH_SHORT).show();
        }

    }

}
