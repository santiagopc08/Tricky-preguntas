package com.example.trickypreguntas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int nFilas = 5, nColumnas = 5, turno = 1;
    Boton[][] botones = new Boton[nFilas][nColumnas];
    LinearLayout buttonContainer;
    RadioButton rbJugador1, rbJugador2;
    TextView tvPregRestantes;
    int nPreguntas;
    ConstraintLayout cPregunta;
    TextView tvEnunciado;
    RadioGroup rgOpciones;
    RadioButton rbOpcA, rbOpcB, rbOpcC;
    Button btnResponder;
    Random random = new Random();
    int i = 0, j = 0;
    Pregunta preguntaEnCurso;
    int[] puntajes = {0, 0};
    String separator = "½";
    RelativeLayout ventanaCorrecta, ventanaIncorrecta;
    String infoVictoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonContainer = findViewById(R.id.buttonContainer);
        rbJugador1 = findViewById(R.id.rbJugador1);
        rbJugador2 = findViewById(R.id.rbJugador2);
        rbJugador1.setText(MenuActivity.nombre1);
        rbJugador2.setText(MenuActivity.nombre2);
        tvPregRestantes = findViewById(R.id.tvPregRestantes);
        cPregunta = findViewById(R.id.cPregunta);
        tvEnunciado = findViewById(R.id.tvEnunciado);
        rgOpciones = findViewById(R.id.rgOpciones);
        rbOpcA = findViewById(R.id.rbOpcA);
        rbOpcB = findViewById(R.id.rbOpcB);
        rbOpcC = findViewById(R.id.rbOpcC);
        btnResponder = findViewById(R.id.btnResponder);
        ventanaCorrecta = findViewById(R.id.cNombres);
        ventanaIncorrecta = findViewById(R.id.incorrect);
        guardarPreguntas();
        inicializarBotones();
        llenarPreguntas();
        nPreguntas = MenuActivity.preguntas.size();
        responder();
    }

    //Guarda las preguntas en el archivo de texto plano "preguntas.txt"
    private void guardarPreguntas() {
        String preguntasString =
                "1. ¿Quién inventó el primer gusano?½a. Freddie Mercury½b. Robert Tappan Morris½c. Einstein½1½4½" +
                        "2. ¿En qué año se fundó google?½a. 1998½b. 1980½c. 2003½0½1½" +
                        "3. ¿En qué año se lanzó el IBM PC?½a. 1992½b. 1969½c. 1981½2½2½" +
                        "4. ¿En qué año se creó JAVA?½a. 1991½b. 2015½c. 1987½0½2½" +
                        "5. ¿En qué año se inventó ENIAC (Electronic Numerical Integrator And Computer)?½a. 1498½b. 1943½c. 1999½1½4½" +
                        "6. ¿Para qué se creó ENIAC (Electronic Numerical Integrator And Computer?½a. Con el propósito de resolver los problemas de balística del ejército de Reino Unido½b. Con el propósito de resolver los problemas de balística del ejército de Alemania½c. Con el propósito de resolver los problemas de balística del ejército de Estados Unidos½2½3½" +
                        "7. ¿En qué año se fabricó la Z1½a. 1936½b. 1836½c. 1903½0½4½" +
                        "8. ¿Quién se inventó la Z1?½a. James P. Schneider½b. Carl Smith½c. Konrad Zuse½2½3½" +
                        "9. ¿En qué año se fundó Apple?½a. 1980½b. 1976½c. 2001½1½2½" +
                        "10. ¿Quién creó Apple?½a. Steve Jobs½b. Steve Jobs y Steve Wozniak½c. Steve Jobs y Bill Gate½0½1½" +
                        "11.¿Quiénes son los fundadores de Microsoft?½a. Bill Gates y Paul Allen½b. Bill Gates y Steve Jobs½c. Bill Gates y Steve Ballmer½0½1½" +
                        "12. ¿En qué año se fundó Microsoft?½a. 1980½b. 1975½c. 1973½1½3½" +
                        "13. ¿En qué año Alemania adopto enigma para uso militar?½a. 1926½b. 1930½c.  1924½0½4½" +
                        "14. ¿Para qué se creó la máquina enigma?½a. Encriptar mensajes½b. Desencriptar mensajes½c. Encriptar y desencriptar mensajes½2½3½" +
                        "15. ¿Quién patento la máquina enigma?½a. Arthur Scherbius½b. Scherbius & Ritter½c. Ritter½1½4½" +
                        "16. ¿En qué año se lanzó el primer iPhone?½a. 2004½b. 2007½c. 2011½1½1½" +
                        "17. ¿Cuál se considera como el primer teléfono móvil inteligente?½a. Nokia 8000½b. BlackBerry 850½c. IBM Simon½2½4½" +
                        "18. ¿En qué año se lanzó el primer Smartphone?½a. 1992½b. 2004½c. 2006½0½4½" +
                        "19. ¿Cuál es considerado como el primer ordenador pórtatil?½a. Epson HX-20½b. Macintosh½c. IBM computer½0½2½" +
                        "20. ¿Qué significa Microsoft?½a. Es una palabra inventada½b. Es el acrónimo de microcomputer y software½c. Es el acrónimo de microtecnology y software½1½2½" +
                        "21. ¿En qué año fue creado el código ASCII?½a. 1963½b. 1957½c. 1974½0½3½" +
                        "22. ¿Por qué se creó el código ASCII?½a. Para poder agregar más caracteres.½b. No se sabe.½c. Como evolución de los códigos de la telegrafía.½2½3½" +
                        "23. ¿En qué año fue el primer microprocesador?½a. 1984½b. 1964½c. 1971½2½4½" +
                        "24. ¿Qué nombre se le dio al primer microprocesador?½a. 4004½b. T-304½c. IBM 1001½0½4½" +
                        "25. ¿En qué año fue lanzado el primer Macintosh?½a. 1990½b. 1984½c. 1987½1½2½";
        try {
            OutputStreamWriter writer = new OutputStreamWriter(openFileOutput("preguntas.txt", MODE_PRIVATE));
            writer.write(preguntasString);
            writer.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //Obtiene las preguntas del archivo de texto plano "preguntas.txt"
    //Con el método split obtiene los elementos de la pregunta que se encuentran separados por el "separator"
    //Guarda las preguntas en la lista "preguntas"
    private void llenarPreguntas() {
        String preguntasString = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("preguntas.txt")));
            preguntasString = reader.readLine();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        String[] elementosPreguntas = preguntasString.split(separator);
        for (int i = -1; i < elementosPreguntas.length - 1; i += 6) {
            Pregunta pregunta = new Pregunta();
            pregunta.setEnunciado(elementosPreguntas[i + 1]);
            pregunta.setOpciones(new String[]{elementosPreguntas[i + 2], elementosPreguntas[i + 3], elementosPreguntas[i + 4]});
            pregunta.setnOpcCorrecta(Integer.parseInt(elementosPreguntas[i + 5]));
            pregunta.setDificultad(Integer.parseInt(elementosPreguntas[i + 6]));
            MenuActivity.preguntas.add(pregunta);
        }
    }

    //Guarda los objetos de la clase "Boton" en una matriz "botones"
    //Se guardan en una matriz para facilitar el acceso a ellos sin necesidad de un id establecido en el diseño ("activity_main.xml")
    private void inicializarBotones() {
        for (int i = 0; i < nFilas; i++) {
            LinearLayout filaBotones = (LinearLayout) buttonContainer.getChildAt(i);
            for (int j = 0; j < nColumnas; j++) {
                botones[i][j] = new Boton();
                botones[i][j].setBoton((Button) filaBotones.getChildAt(j));
                botones[i][j].getBoton().setId((i * 10) + j);
            }
        }
    }

    //Carga los elementos de una pregunta obtenida de manera aleatoria en la vista que muestra los elementos de una pregunta
    public void mostrarPregunta(View view) {
        if (nPreguntas > 0) {
            buttonContainer.setVisibility(View.GONE);
            cPregunta.setVisibility(View.VISIBLE);
            preguntaEnCurso = MenuActivity.preguntas.get(obtenerIndice(random.nextInt(MenuActivity.preguntas.size())));
            tvEnunciado.setText(preguntaEnCurso.getEnunciado());
            rbOpcA.setText(preguntaEnCurso.getOpciones()[0]);
            rbOpcB.setText(preguntaEnCurso.getOpciones()[1]);
            rbOpcC.setText(preguntaEnCurso.getOpciones()[2]);
            int id = view.getId();
            i = id / 10;
            j = id % 10;
        }
    }

    //Método que se ejecuta si "nPreguntas" = 0 y que llama el método "mostrarFinDeJuego()"
    private void victoriaPorPuntos() {
        if (puntajes[0] > puntajes[1]) {
            infoVictoria = MenuActivity.nombre1 + " ganó con " + puntajes[0] + " puntos!";
        } else {
            infoVictoria = MenuActivity.nombre2 + " ganó con " + puntajes[1] + " puntos!";
        }
        mostrarFinDeJuego();
    }

    //Función recursiva que obtiene un índice aleatorio de una pregunta que no haya sido respondida
    private int obtenerIndice(int n) {
        return MenuActivity.preguntas.get(n).respondida ? obtenerIndice(random.nextInt(MenuActivity.preguntas.size())) : n;
    }

    //Muestra el resultado de la respuesta a una pregunta y cambia de turno
    private void responder() {
        btnResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (respuestaCorrecta()) {
                    ventanaCorrecta.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ventanaCorrecta.setVisibility(View.GONE);
                        }
                    }, 1000);
                } else {
                    ventanaIncorrecta.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ventanaIncorrecta.setVisibility(View.GONE);
                        }
                    }, 1000);
                }
                cambiarTurno();
                buttonContainer.setVisibility(View.VISIBLE);
                cPregunta.setVisibility(View.GONE);
                rbOpcA.setChecked(true);
                if (nPreguntas == 0) {
                    victoriaPorPuntos();
                }
            }
        });
    }

    //Si la respuesta es correcta, inhabilita el botón que se tocó y lo pinta del color del jugador que lo tocó
    // indica que la pregunta fue respondida suma el puntaje al respectivo jugador y
    // resta 1 al total de preguntas restante y lo muestra, llama el método "verificarVictoria()"
    private boolean respuestaCorrecta() {
        if (rbOpcA.isChecked() && preguntaEnCurso.getnOpcCorrecta() == 0 ||
                rbOpcB.isChecked() && preguntaEnCurso.getnOpcCorrecta() == 1 ||
                rbOpcC.isChecked() && preguntaEnCurso.getnOpcCorrecta() == 2) {
            botones[i][j].getBoton().setClickable(false);
            botones[i][j].setRespondidoPor(turno);
            MenuActivity.preguntas.get(MenuActivity.preguntas.indexOf(preguntaEnCurso)).setRespondida(true);
            verificarVictoria();
            nPreguntas--;
            tvPregRestantes.setText("Quedan " + nPreguntas + " preguntas");
            puntajes[turno - 1] += preguntaEnCurso.getDificultad();
            if (turno == 1) {
                botones[i][j].getBoton().setBackgroundTintList(getResources().getColorStateList(R.color.jugador1_color_list));
            } else {
                botones[i][j].getBoton().setBackgroundTintList(getResources().getColorStateList(R.color.jugador2_color_list));
            }
            return true;
        }
        return false;
    }

    //Pinta el color del jugador que tiene el turno actual y suma o resta 1 a "turno" para saber de quién es el turno que viene
    private void cambiarTurno() {
        if (turno == 1) {
            turno++;
            rbJugador1.setChecked(false);
            rbJugador2.setChecked(true);
            rbJugador1.setTextColor(Color.WHITE);
            rbJugador2.setTextColor(getResources().getColor(R.color.jugador2));
        } else {
            turno--;
            rbJugador1.setChecked(true);
            rbJugador2.setChecked(false);
            rbJugador1.setTextColor(getResources().getColor(R.color.jugador1));
            rbJugador2.setTextColor(Color.WHITE);
        }
    }

    //Llama los métodos individuales de cada uno de los tipos de victoria posibles (línea o diagonal)
    private void verificarVictoria() {
        if (verificarDiagonalttb() || verificarDiagonalbtt() || verificarColumnas() || verificarFilas()) {
            mostrarFinDeJuego();
        }

    }

    //Muestra la pantalla de fin de juego
    private void mostrarFinDeJuego() {
        Intent i = new Intent(this, EndGameActivity.class);
        i.putExtra("infoVictoria", infoVictoria);
        i.putExtra("jugador", turno);
        startActivity(i);
        finish();
    }

    private boolean verificarColumnas() {
        for (int i = 0; i < nColumnas; i++) {
            int jugador = botones[0][i].getRespondidoPor();
            int nBuenas = 1;
            Log.i("TAG", "verificarColumnas: " + jugador);
            for (int j = 1; j < nFilas; j++) {
                if (botones[j][i].getRespondidoPor() == jugador && jugador != 0) {
                    nBuenas++;
                } else {
                    break;
                }
            }
            if (nBuenas == nFilas) {
                if (jugador == 1) {
                    infoVictoria = MenuActivity.nombre1;
                } else {
                    infoVictoria = MenuActivity.nombre2;
                }
                infoVictoria += " ganó al formar una línea vertical";
                return true;
            }
        }
        return false;
    }

    private boolean verificarFilas() {
        for (int i = 0; i < nFilas; i++) {
            int jugador = botones[i][0].getRespondidoPor();
            int nBuenas = 1;
            Log.i("TAG", "verificarFilas: " + jugador);
            for (int j = 1; j < nColumnas; j++) {
                if (botones[i][j].getRespondidoPor() == jugador && jugador != 0) {
                    nBuenas++;
                } else {
                    break;
                }
            }
            if (nBuenas == nColumnas) {
                if (jugador == 1) {
                    infoVictoria = MenuActivity.nombre1;
                } else {
                    infoVictoria = MenuActivity.nombre2;
                }
                infoVictoria += " ganó al formar una línea horizontal";
                return true;
            }
        }
        return false;
    }

    private boolean verificarDiagonalttb() {
        int jugador = botones[0][0].getRespondidoPor();
        int nBuenas = 1;
        int n = 1;
        while (n < nFilas) {
            if (botones[n][n].getRespondidoPor() == jugador && jugador != 0) {
                nBuenas++;
            } else {
                return false;
            }
            n++;
        }
        if (jugador == 1) {
            infoVictoria = MenuActivity.nombre1;
        } else {
            infoVictoria = MenuActivity.nombre2;
        }
        infoVictoria += " ganó al formar una diagonal";
        return nBuenas == nFilas;
    }

    private boolean verificarDiagonalbtt() {
        int jugador = botones[0][4].getRespondidoPor();
        int nBuenas = 1;
        int col = 3, fil = 1;
        while (fil < nFilas) {
            if (botones[fil][col].getRespondidoPor() == jugador && jugador != 0) {
                nBuenas++;
            } else {
                return false;
            }
            fil++;
            col--;
        }
        if (jugador == 1) {
            infoVictoria = MenuActivity.nombre1;
        } else {
            infoVictoria = MenuActivity.nombre2;
        }
        infoVictoria += " ganó al formar una diagonal";
        return nBuenas == nFilas;
    }
}
