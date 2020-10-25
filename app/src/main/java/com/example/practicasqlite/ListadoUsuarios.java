package com.example.practicasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListadoUsuarios extends AppCompatActivity {

    ListView listausuarios;
    ArrayList<String> arrayusuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_usuarios);
        listausuarios = findViewById(R.id.lvusuarios);
        CargarUsuarios();
    }

    private void CargarUsuarios() {
        arrayusuarios = ListadoUsuarios();
        ArrayAdapter<String> adapterusuarios = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1, arrayusuarios);
        listausuarios.setAdapter(adapterusuarios);
    }

    private ArrayList<String> ListadoUsuarios (){
        ArrayList <String> datos = new ArrayList<String>();
        BaseDatos osql = new BaseDatos(getApplicationContext(),"BDBIBLIOTECA", null, 1);
        SQLiteDatabase db = osql.getReadableDatabase();
        String query = ("Select email, nombre, rol From Usuario");
        Cursor cusuario = db.rawQuery(query,null);
        if (cusuario.moveToFirst()){
            do {
                String tipou = "Invitado";
                if (cusuario.getString(2).equals("1"));{
                    tipou = "Administrador";
                }
                String fila = cusuario.getString(1) + " " + cusuario.getString(0) + " " + tipou;
                datos.add(fila);
            }while (cusuario.moveToNext());
        }
        db.close();
        return datos;
    }
}