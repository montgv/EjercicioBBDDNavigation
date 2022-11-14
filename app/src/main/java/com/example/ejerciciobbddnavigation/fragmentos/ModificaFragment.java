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
import com.example.ejerciciobbddnavigation.databinding.FragmentModificaBinding;


public class ModificaFragment extends Fragment {
    //Creamos el binding que nos sirve para la vinculacion de vista
    private FragmentModificaBinding bindingModifica;

    //Este es el metodo que se llama para que comienze el fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modifica, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(getContext(),
                R.array.spinnerSexo, android.R.layout.simple_spinner_item);
        bindingModifica.spinnnerSexoModificacion.setAdapter(adaptador);

        //Definimos un escuchador mediante el método setOnClickListener que esta asociado al boton
        //con el binding, cuando se pulsa el botón, comento línea a línea
        bindingModifica.btAceptarModificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos una instancia de la subclase SQLiteOpenHelper para poder acceder a la base de datos
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(view.getContext(), "escuela", null, 1);
                //Obtienemos el repositorio de datos en modo de escritura
                SQLiteDatabase baseDatos = admin.getWritableDatabase();

                //Guardamos en cada variable los daots que hemos recuperado de los editText y el
                //spinner con la ayuda del binding
                String dni = bindingModifica.edTxtDniModificacion.getText().toString();
                String nombre = bindingModifica.edTxtNombreModificacion.getText().toString();
                String apellidos = bindingModifica.edTxtApellidosModificacion.getText().toString();
                String sexo = bindingModifica.spinnnerSexoModificacion.toString();

                //Comprobamos que las variables son distintas de vacio
                if (!dni.isEmpty() && !nombre.isEmpty() && !apellidos.isEmpty() && !sexo.isEmpty()) {
                    //Creamos un nuevo mapa de valores, donde los nombres de las columnas son las claves
                    ContentValues registro = new ContentValues();
                    registro.put("dni", dni);
                    registro.put("nombre", nombre);
                    registro.put("apellidos", apellidos);
                    registro.put("sexo", sexo);
                    //Guardamos en una variable de tipo numero si se modificado algun alumno de la base de datos
                    int cantidad = baseDatos.update("alumnos", registro, "dni="+dni, null);
                    //Cerramos la base de datos
                    baseDatos.close();
                    //Comprobamos si cantidad es igual 1 significa que se si ha borrado un alumno,
                    //muestra con toast las diferentes opciones
                    if (cantidad == 1) {
                        Toast.makeText(view.getContext(), "Alumno modificado correctamente.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(view.getContext(), "No existe el alumno.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(view.getContext(), "Debes introducir todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Definimos un escuchador mediante el método setOnClickListener que esta asociado al boton
        //con el binding, cuando se pulsa el botón vuelva al fragment inicial
        bindingModifica.btCancelarModificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.inicioFragment);
            }
        });
    }
}