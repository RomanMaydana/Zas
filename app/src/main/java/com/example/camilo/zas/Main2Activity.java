package com.example.camilo.zas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private ImageButton boton;
    private ImageButton boton1;
    private ImageButton boton2;
    private Animation animacion;
    private String clave="idioma";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);
        ImageButton boton = (ImageButton)findViewById(R.id.aleman);
        ImageButton boton1 = (ImageButton)findViewById(R.id.bolivia);
        ImageButton boton2 = (ImageButton)findViewById(R.id.eeuu);

    }

    public void aleman(View view) {
        Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
        intent.putExtra(clave,"Aleman");
        startActivity(intent);
    }
    public void aymara(View view) {
        Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
        intent.putExtra(clave,"Aimara");
        startActivity(intent);
    }
    public void ingles(View view) {
        Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
        intent.putExtra(clave,"Ingles");
        startActivity(intent);
    }
}