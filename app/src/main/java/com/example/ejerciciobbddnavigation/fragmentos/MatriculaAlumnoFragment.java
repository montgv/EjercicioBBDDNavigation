package com.example.ejerciciobbddnavigation.fragmentos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ejerciciobbddnavigation.R;
import com.example.ejerciciobbddnavigation.basedatos.AdminSQLiteOpenHelper;
import com.example.ejerciciobbddnavigation.databinding.FragmentMatriculaAlumnoBinding;

public class MatriculaAlumnoFragment extends Fragment {
    //Creamos el binding que nos sirve para la vinculacion de vista
    private FragmentMatriculaAlumnoBinding bindingMatricula;

    //Este es el metodo que se llama para que comienze el fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matricula_alumno, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(getContext(),
                R.array.spinnerSexo, android.R.layout.simple_spinner_item);
        bindingMatricula.spinnnerSexoMatricula.setAdapter(adaptador);

        //Definimos un escuchador mediante el método setOnClickListener que esta asociado al boton
        //con el binding, cuando se pulsa el botón, comento línea a línea
        bindingMatricula.btAceptarMatricula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos una instancia de la subclase SQLiteOpenHelper para poder acceder a la base de datos
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(view.getContext(), "escuela", null, 1);
                //Obtienemos el repositorio de datos en modo de escritura
                SQLiteDatabase baseDatos = admin.getWritableDatabase();

                //Guardamos en cada variable los daots que hemos recuperado de los editText y el
                //spinner con la ayuda del binding
                String dni = bindingMatricula.edTxtDniMatricula.getText().toString();
                String nombre = bindingMatricula.edTxtNombreMatricula.getText().toString();
                String apellidos = bindingMatricula.edTxtApellidosMatricula.getText().toString();
                String sexo = bindingMatricula.spinnnerSexoMatricula.toString();

                //Comprobamos que las variables son distintas de vacio
                if (!dni.isEmpty() && !nombre.isEmpty() && !apellidos.isEmpty() && !sexo.isEmpty()) {
                    //Creamos un nuevo mapa de valores, donde los nombres de las columnas son las claves
                    ContentValues registro = new ContentValues();
                    registro.put("dni", dni);
                    registro.put("nombre", nombre);
                    registro.put("apellidos", apellidos);
                    registro.put("sexo", sexo);
                    //Insertamos la nueva fila en la base de daatos
                    baseDatos.insert("alumnos", null, registro);
                    //Cerramos la base de datos
                    baseDatos.close();
                    //Cambiamos el texto de los editText para que no salga nada
                    bindingMatricula.edTxtDniMatricula.setText("");
                    bindingMatricula.edTxtNombreMatricula.setText("");
                    bindingMatricula.edTxtApellidosMatricula.setText("");
                    //Mostramos con el toast las diferentes opciones que se realizan
                    Toast.makeText(view.getContext(), "Alumno matriculado correctamente.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "Debes introducir todos los campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Definimos un escuchador mediante el método setOnClickListener que esta asociado al boton
        //con el binding, cuando se pulsa el botón vuelva al fragment inicial
        bindingMatricula.btCancelarMatricula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.inicioFragment);
            }
        });
    }
}