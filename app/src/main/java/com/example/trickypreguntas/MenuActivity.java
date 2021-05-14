package com.example.trickypreguntas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    Button btnAgregarPreguntas, btnJugar, btnCancelar, btnContinuar;
    TextInputEditText txtNombreJugador1, txtNombreJugador2;
    ConstraintLayout cNombres;
    public static String nombre1, nombre2;
    public static List<Pregunta> preguntas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnAgregarPreguntas = findViewById(R.id.btnAgregarPreguntas);
        btnJugar = findViewById(R.id.btnJugar);
        txtNombreJugador1 = findViewById(R.id.txtNombreJugador1);
        txtNombreJugador2 = findViewById(R.id.txtNombreJugador2);
        btnCancelar = findViewById(R.id.btnCancelarNombre);
        btnContinuar = findViewById(R.id.btnContinuarNombre);
        cNombres = findViewById(R.id.cNombres);
    }
}