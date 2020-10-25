package com.example.practicasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Actusuario extends AppCompatActivity {

    EditText emailu, nombreu, passwordu;
    RadioButton adminu, usuu;
    Button agregaru, buscaru, actualizaru,eliminaru, listaru;
    BaseDatos osqlite = new BaseDatos(getApplicationContext(),"BDBIBLIOTECA",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actusuario);
        emailu = findViewById(R.id.etemailu);
        nombreu = findViewById(R.id.etnombreu);
        passwordu = findViewById(R.id.etpasswordu);
        adminu = findViewById(R.id.rbadminu);
        usuu = findViewById(R.id.rbusuu);
        agregaru = findViewById(R.id.btnagregaru);
        buscaru = findViewById(R.id.btnbuscaru);
        actualizaru = findViewById(R.id.btnactualizaru);
        eliminaru = findViewById(R.id.btneliminaru);
        listaru = findViewById(R.id.btnlistaru);

        agregaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (!emailu.getText().toString().isEmpty()&&!nombreu.getText().toString().isEmpty()&&!passwordu.getText().toString().isEmpty()&&(adminu.isChecked()||usuu.isChecked())){
                    SQLiteDatabase db = osqlite.getReadableDatabase();
                    String query = "SELECT email FROM usuario WHERE = '" + emailu.getText().toString()+"'";
                    Cursor cusuario = db.rawQuery(query,null);
                    if (cusuario.moveToFirst()){
                        Toast.makeText(getApplicationContext(), "El Email ya está en uso", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        SQLiteDatabase db1 = osqlite.getWritableDatabase();
                        try {
                            ContentValues contusuario = new ContentValues();
                            contusuario.put("email", emailu.getText().toString().trim());
                            contusuario.put("nombre", nombreu.getText().toString().trim());
                            contusuario.put("clave", emailu.getText().toString().trim());
                            if (adminu.isChecked()){
                                contusuario.put("rol","1");
                            }
                            else{
                                contusuario.put("rol","2");
                            }
                            db1.insert("usuario",null,contusuario);
                            db1.close();
                            Toast.makeText(getApplicationContext(), "Usuario agregado correctamente", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
                }
            }
                
        });

        listaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListadoUsuarios.class));
            }
        });
    }
}