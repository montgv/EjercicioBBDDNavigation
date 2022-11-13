package com.example.ejerciciobbddnavigation.fragmentos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ejerciciobbddnavigation.R;
import com.example.ejerciciobbddnavigation.basedatos.AdminSQLiteOpenHelper;
import com.example.ejerciciobbddnavigation.databinding.FragmentMatriculaAlumnoBinding;

public class MatriculaAlumnoFragment extends Fragment {
    private FragmentMatriculaAlumnoBinding bindingMatricula;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matricula_alumno, container, false);
    }

    public void MatriculaAlumno(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "escuela", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();

        String dni = bindingMatricula.edTxtDniMatricula.getText().toString();
        String nombre = bindingMatricula.edTxtNombreMatricula.getText().toString();
        String apellidos = bindingMatricula.edTxtApellidosMatricula.getText().toString();
        //Falta sexo

        if (!dni.isEmpty() && !nombre.isEmpty() && !apellidos.isEmpty()) {
            ContentValues registro = new ContentValues();

            registro.put("dni", dni);
            registro.put("nombre", nombre);
            registro.put("apellidos", apellidos);

            baseDatos.insert("alumnos", null, registro);

            baseDatos.close();

            bindingMatricula.edTxtDniMatricula.setText("");
            bindingMatricula.edTxtNombreMatricula.setText("");
            bindingMatricula.edTxtApellidosMatricula.setText("");

            Toast.makeText(this, "Alumno matriculado correctamente.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debes introducir todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}