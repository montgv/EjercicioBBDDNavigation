package com.example.ejerciciobbddnavigation.fragmentos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.ejerciciobbddnavigation.R;
import com.example.ejerciciobbddnavigation.basedatos.AdminSQLiteOpenHelper;
import com.example.ejerciciobbddnavigation.databinding.FragmentListarBinding;
import com.example.ejerciciobbddnavigation.objetos.Alumno;
import com.example.ejerciciobbddnavigation.objetos.MiAdaptador;
import com.example.ejerciciobbddnavigation.objetos.Sexo;

import java.util.ArrayList;
import java.util.List;

public class ListarFragment extends Fragment {
    //Creamos el binding que nos sirve para la vinculacion de vista
    private FragmentListarBinding bindingListar;
    //Creamos una lista de la clase Alumno
    List<Alumno> listaAlumnos;

    //Este es el metodo que se llama para que comienze el fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bindingListar = FragmentListarBinding.inflate(inflater, container,false);
        return bindingListar.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Creamos una instancia de la subclase SQLiteOpenHelper para poder acceder a la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getContext(), "escuela", null, 1);
        //Obtienemos el repositorio de datos en modo de escritura
        SQLiteDatabase baseDatos = admin.getReadableDatabase();

        //Creamos un cursor para recorrer toda la base de datos y creamos un arrayList donde se van
        //a guardar los diferentes alumnos
        Cursor cursor = baseDatos.rawQuery("select * from alumnos", null);
        String dni, nombre, apellidos, sexo;
        listaAlumnos = new ArrayList<>();
        //Esta parte es la manera que tiene el cursor para recorrer la base de datos
        if (cursor.moveToFirst()) {
            do {
                dni = cursor.getString(0);
                nombre = cursor.getString(1);
                apellidos = cursor.getString(2);
                sexo = cursor.getString(3);
                if (sexo.equals("Hombre")) {
                    listaAlumnos.add(new Alumno(dni, nombre, apellidos, Sexo.HOMBRE));
                } else {
                    listaAlumnos.add(new Alumno(dni, nombre, apellidos, Sexo.MUJER));
                }
            } while (cursor.moveToNext());
        }

        //Estamos creando un adaptador que lo que hace es devolver una vista para cada objeto
        //de una coleccion de objetos de datos, es decir, para los diferentes objetos que tiene
        //el arrayList, y despues, asociamos al binding a la listView para que use ese adaptador
        MiAdaptador miAdaptador = new MiAdaptador(getContext(), R.layout.alumnos_item, listaAlumnos);
        bindingListar.listViewListar.setAdapter(miAdaptador);

        //Definimos un escuchador para cuando pulsemos en un item del listView haga lo que se le indica
        bindingListar.listViewListar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(view.getContext(), "Alumno con DNI: " + listaAlumnos.get(i).getDni(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}