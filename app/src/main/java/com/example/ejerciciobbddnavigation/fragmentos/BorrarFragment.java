package com.example.ejerciciobbddnavigation.fragmentos;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ejerciciobbddnavigation.R;
import com.example.ejerciciobbddnavigation.basedatos.AdminSQLiteOpenHelper;
import com.example.ejerciciobbddnavigation.databinding.FragmentBorrarBinding;

public class BorrarFragment extends Fragment {
    //Creamos el binding que nos sirve para la vinculacion de vista
    private FragmentBorrarBinding bindingBorrar;

    //Este es el metodo que se llama para que comienze el fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_borrar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Definimos un escuchador mediante el método setOnClickListener que esta asociado al boton
        //con el binding, cuando se pulsa el botón, comento línea a línea
        bindingBorrar.btAceptarBorrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos una instancia de la subclase SQLiteOpenHelper para poder acceder a la base de datos
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(view.getContext(), "escuela", null, 1);
                //Obtienemos el repositorio de datos en modo de escritura
                SQLiteDatabase baseDatos = admin.getWritableDatabase();

                //Guardamos en una variable el dni que lo hemos recuperado del editText con la ayuda
                //del binding
                String dni = bindingBorrar.edTxtDniBorrado.getText().toString();

                //Comprobamos que la variable es distinta de vacio
                if (!dni.isEmpty()) {
                    //Guardamos en una variable de tipo numero si se borrado algun alumno de la base de datos
                    int cantidad = baseDatos.delete("alumnos", "dni="+dni, null);
                    //Cerramos la base de datos
                    baseDatos.close();
                    //Comprobamos si cantidad es igual 1 significa que se si ha borrado un alumno,
                    //muestra con toast las diferentes opciones
                    if (cantidad == 1) {
                        Toast.makeText(view.getContext(), "Alumno eliminado corectamente.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(view.getContext(), "No existe el alumno.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(view.getContext(), "Debes introducir todos los campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Definimos un escuchador mediante el método setOnClickListener que esta asociado al boton
        //con el binding, cuando se pulsa el botón vuelva al fragment inicial
        bindingBorrar.btCancelarBorrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.inicioFragment);
            }
        });
    }
}