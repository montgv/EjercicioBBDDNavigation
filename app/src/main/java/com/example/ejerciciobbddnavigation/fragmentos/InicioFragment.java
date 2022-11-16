package com.example.ejerciciobbddnavigation.fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ejerciciobbddnavigation.R;
import com.example.ejerciciobbddnavigation.databinding.FragmentInicioBinding;


public class InicioFragment extends Fragment {

    FragmentInicioBinding bindingInicio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bindingInicio = FragmentInicioBinding.inflate(inflater, container, false);
        return bindingInicio.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}