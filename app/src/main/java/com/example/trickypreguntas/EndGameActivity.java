package com.example.trickypreguntas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGameActivity extends AppCompatActivity {

    TextView tvInfoWin;
    Button btnJugarDNuevo;
    ConstraintLayout fondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        tvInfoWin = findViewById(R.id.tvInfoWin);
        btnJugarDNuevo = findViewById(R.id.btnJugarDNuevo);
        fondo = findViewById(R.id.victoriaBackground);

        //Obtiene el número del jugador que ganó para poner el respectivo color de fondo a la pantalla
        fondo.setBackgroundColor(
                getIntent().getIntExtra("jugador", 0) == 1
                ? getResources().getColor(R.color.jugador1)
                : getResources().getColor(R.color.jugador2));

        //Obtiene el String "infoVictoria" para mostrarlo
        tvInfoWin.setText(getIntent().getStringExtra("infoVictoria"));

        btnJugarDNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    //Método que tiene lugar cuando presionan atrás en la aplicación
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
