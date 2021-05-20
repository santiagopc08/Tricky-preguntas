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
        guardarPregunta();
        mostrarPuntaje();

    }

    private void mostrarPuntaje() {
        sbPuntaje.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvPuntaje.setText("Puntaje: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void guardarPregunta() {
        btnGuardar.setOnClickListener(v -> {
            if (sbPuntaje.getProgress() != 0) {
                Pregunta pregunta = new Pregunta();
                pregunta.setDificultad(sbPuntaje.getProgress());
                pregunta.setEnunciado(txtEnunciado.getText().toString());
                pregunta.setnOpcCorrecta(rbA.isChecked() ? 0 : rbB.isChecked() ? 1 : 2);
                pregunta.setOpciones(new String[]{txtA.getText().toString(), txtB.getText().toString(), txtC.getText().toString()});
                MenuActivity.preguntas.add(pregunta);
                limpiarCampos();
            } else {
                Toast.makeText(getApplicationContext(), "Debe indicar un puntaje mayor a 0", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void limpiarCampos() {
        txtA.setText("");
        txtB.setText("");
        txtC.setText("");
        txtEnunciado.setText("");
        sbPuntaje.setProgress(0);
    }
}