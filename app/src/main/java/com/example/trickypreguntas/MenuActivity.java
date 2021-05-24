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
        mostrarCuadroNombres();
        agregarPreguntas();
        jugar();
        cancelar();
    }

    private void jugar() {
        btnContinuar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            cNombres.setVisibility(View.GONE);
            nombre1 = txtNombreJugador1.getText().toString();
            nombre2 = txtNombreJugador2.getText().toString();
            limpiarNombres();
        });
    }

    private void cancelar() {
        btnCancelar.setOnClickListener(v -> {
            cNombres.setVisibility(View.GONE);
            limpiarNombres();
        });
    }

    private void limpiarNombres() {
        txtNombreJugador1.setText("");
        txtNombreJugador2.setText("");
    }

    private void agregarPreguntas() {
        btnAgregarPreguntas.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AgregarPreguntasActivity.class));
        });
    }

    private void mostrarCuadroNombres() {
        btnJugar.setOnClickListener(v -> cNombres.setVisibility(View.VISIBLE));
    }
}