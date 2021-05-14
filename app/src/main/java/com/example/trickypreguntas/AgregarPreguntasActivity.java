package com.example.trickypreguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AgregarPreguntasActivity extends AppCompatActivity {

    RadioButton rbA, rbB, rbC;
    EditText txtA, txtB, txtC;
    TextInputEditText txtEnunciado;
    Button btnGuardar;
    SeekBar sbPuntaje;
    TextView tvPuntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_preguntas);

        rbA = findViewById(R.id.rbANuevo);
        rbB = findViewById(R.id.rbBNuevo);
        rbC = findViewById(R.id.rbCNuevo);
        txtA = findViewById(R.id.txtOpcA);
        txtB = findViewById(R.id.txtOpcB);
        txtC = findViewById(R.id.txtOpcC);
        txtEnunciado = findViewById(R.id.txtEnunciado);
        btnGuardar = findViewById(R.id.btnGuardar);
        sbPuntaje = findViewById(R.id.sbPuntaje);
        tvPuntaje = findViewById(R.id.tvPuntaje);

    }
}