package com.example.camilo.zas;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private Button button;
    private String idioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main3);
        editText = (EditText)findViewById(R.id.edText);
        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.btTraducir);
        Bundle bundle = this.getIntent().getExtras();
        idioma = bundle.getString("idioma");
        Toast.makeText(this, idioma, Toast.LENGTH_SHORT).show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().isEmpty()){
                    return;
                }
                String palabra = editText.getText().toString();
                try {
                    BaseHelper baseHelper = new BaseHelper(getApplicationContext(), "mibase", null, 1);
                    SQLiteDatabase sqLiteDatabase = baseHelper.getReadableDatabase();
                    Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+idioma+" FROM Palabras WHERE Español = '" + palabra + "' COLLATE LOCALIZED " , null);
                    cursor.moveToFirst();
                    textView.setText(cursor.getString(0).toString());
                    sqLiteDatabase.close();
                }catch (Exception e){
                    Toast.makeText(Main3Activity.this,"La palabra no existe" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
