package com.example.camilo.zas;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    private Button boton;
    private Button boton1;
    private Animation animacion;
    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Button boton = (Button)findViewById(R.id.traducir);
        Button boton1 = (Button)findViewById(R.id.acerca);
        inicializar();
    }

    public void traducir(View view) {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }
    public void acerca(View view) {
        Toast.makeText(this, "Desarrollado por Camilo", Toast.LENGTH_SHORT).show();
    }
    private void inicializar(){
        if(cantidadRegistros() == 0){
             String[] texto =  leerArchivo();
            BaseHelper baseHelper= new BaseHelper(this,"mibase",null,1);
            SQLiteDatabase sqLiteDatabase = baseHelper.getWritableDatabase();
            sqLiteDatabase.beginTransaction();

            for (int i = 0 ; i < texto.length ; i++){
                String[] linea = texto[i].split(";");
                ContentValues contentValues = new ContentValues();
                contentValues.put("Español",linea[0]);
                contentValues.put("Aimara",linea[1]);
                contentValues.put("Ingles",linea[2]);
                contentValues.put("Aleman",linea[3]);
                sqLiteDatabase.insert("Palabras",null,contentValues);
            }
            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
        }
    }
    private long cantidadRegistros(){
        BaseHelper baseHelper= new BaseHelper(this,"mibase",null,1);
        SQLiteDatabase sqLiteDatabase = baseHelper.getReadableDatabase();
        long cantidad = DatabaseUtils.queryNumEntries(sqLiteDatabase,"Palabras");
        sqLiteDatabase.close();
        return cantidad;
    }
     private String[] leerArchivo(){
         InputStream inputStream = getResources().openRawResource(R.raw.idiomas);
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         try {
            int i = inputStream.read();
            while (i != -1){
                byteArrayOutputStream.write(i);
                i=inputStream.read();
            }
            inputStream.close();
         }catch (IOException e){
             e.printStackTrace();
         }
         return byteArrayOutputStream.toString().split("\n");
     }
}
