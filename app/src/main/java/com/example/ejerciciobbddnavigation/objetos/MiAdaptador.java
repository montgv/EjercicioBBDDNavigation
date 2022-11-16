package com.example.ejerciciobbddnavigation.objetos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ejerciciobbddnavigation.R;

import java.util.List;

//Declaramos la clase MiAdaptador que extiende de ArrayAdapter
public class MiAdaptador extends ArrayAdapter {
    //Declaramos los diferentes atributos que tiene esta clase
    private Context ctx;
    private int layoutTemplate;
    private List<Alumno> listaAlumnos;

    //Declaramos un constructor personas con los diferentes atributos
    public MiAdaptador(Context context, int resource, List objects) {
        super(context, resource, objects);

        this.ctx = context;
        this.layoutTemplate = resource;
        this.listaAlumnos = objects;
    }

    @NonNull
    @Override
    //Implementamos el metodo getView que obtiene una vista que muestra los datos de la posicion
    //actual del conjunto de datos
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(layoutTemplate, parent, false);

        //Obtiene la persona de la posicion actual
        Alumno alumnoActual = listaAlumnos.get(position);

        //Enlazamos los determinados componentes del adaptador con la vinculacion de datos
        ImageView imageViewPersona = v.findViewById(R.id.iVItemAlumno);
        TextView textViewDni = v.findViewById(R.id.tvDni);
        TextView textViewNombre = v.findViewById(R.id.tvNombre);
        TextView textViewApellidos = v.findViewById(R.id.tvApellidos);

        //Cambiamos el texto de los diferentes componentes por los obtenidos de la persona actual
        textViewDni.setText(alumnoActual.getDni());
        textViewNombre.setText(alumnoActual.getNombre());
        textViewApellidos.setText(alumnoActual.getApellidos());

        //Si el dato de la persona actual es igual al introducido la cambia la imagen por una concreta
        //sino le cambia la imagen por otra
        if (alumnoActual.getSexo() == Sexo.HOMBRE) {
            imageViewPersona.setImageResource(R.drawable.boy_avatar_icon_148454);
        } else {
            imageViewPersona.setImageResource(R.drawable.girl_avatar_icon_148461);
        }

        //Devuelve la vista
        return v;
    }
}
