package com.example.ejerciciobbddnavigation.fragmentos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ejerciciobbddnavigation.R;
import com.example.ejerciciobbddnavigation.basedatos.AdminSQLiteOpenHelper;
import com.example.ejerciciobbddnavigation.databinding.FragmentModificaBinding;


public class ModificaFragment extends Fragment {
    //Creamos el binding que nos sirve para la vinculacion de vista
    private FragmentModificaBinding bindingModifica;
    //Creamos la variable sexo
    public int sexo;

    //Este es el metodo que se llama para que comienze el fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bindingModifica = FragmentModificaBinding.inflate(inflater, container, false);
        return bindingModifica.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Cargamos los datos del Array adapter del spinner
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(getContext(),
                R.array.spinnerSexo, com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        adaptador.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        bindingModifica.spinnnerSexoModificacion.setAdapter(adaptador);

        //Definimos un escuchador que hace que guarde el indice del elemento seleccionado en el spinner
        bindingModifica.spinnnerSexoModificacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sexo = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Definimos un escuchador mediante el método setOnClickListener que esta asociado al boton
        //con el binding, cuando se pulsa el botón, comento línea a línea
        bindingModifica.btAceptarModificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos una instancia de la subclase SQLiteOpenHelper para poder acceder a la base de datos
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getContext(), "escuela", null, 1);
                //Obtienemos el repositorio de datos en modo de escritura
                SQLiteDatabase baseDatos = admin.getWritableDatabase();

                //Guardamos en cada variable los daots que hemos recuperado de los editText y el
                //spinner con la ayuda del binding
                String dni = bindingModifica.edTxtDniModificacion.getText().toString();
                String nombre = bindingModifica.edTxtNombreModificacion.getText().toString();
                String apellidos = bindingModifica.edTxtApellidosModificacion.getText().toString();
                String genero = null;

                //Comprobamos que las variables son distintas de vacio
                if (!dni.isEmpty() && !nombre.isEmpty() && !apellidos.isEmpty()) {
                    if (sexo == 0) {
                        genero = "Hombre";
                    }
                    if (sexo == 1) {
                        genero = "Mujer";
                    }
                    //Creamos un nuevo mapa de valores, donde los nombres de las columnas son las claves
                    ContentValues registro = new ContentValues();
                    registro.put("dni", dni);
                    registro.put("nombre", nombre);
                    registro.put("apellidos", apellidos);
                    registro.put("sexo", genero);
                    //Guardamos en una variable de tipo numero si se modificado algun alumno de la base de datos
                    int cantidad = baseDatos.update("alumnos", registro, "dni="+dni, null);
                    //Cerramos la base de datos
                    baseDatos.close();
                    //Comprobamos si cantidad es igual 1 significa que se si ha borrado un alumno,
                    //muestra con toast las diferentes opciones
                    if (cantidad == 1) {
                        Toast.makeText(getContext(), "Alumno modificado correctamente.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "No existe el alumno.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Debes introducir todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Definimos un escuchador mediante el método setOnClickListener que esta asociado al boton
        //con el binding, cuando se pulsa el botón vuelva al fragment inicial
        bindingModifica.btCancelarModificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_incio);
            }
        });
    }
}