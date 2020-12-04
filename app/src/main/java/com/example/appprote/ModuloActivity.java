package com.example.appprote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ModuloActivity extends AppCompatActivity {

    private static String moduloSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo);
    }

    public void avanzarA(View view) {
        setModuloSeleccionado("A");
        Intent intent = new Intent(ModuloActivity.this, ListadoActivity.class);
        startActivity(intent);
    }
    public void avanzarB(View view) {
        setModuloSeleccionado("B");
        Intent intent = new Intent(ModuloActivity.this, ListadoActivity.class);
        startActivity(intent);
    }
    public void avanzarC(View view) {
        setModuloSeleccionado("C");
        Intent intent = new Intent(ModuloActivity.this, ListadoActivity.class);
        startActivity(intent);
    }
    public void avanzarT(View view) {
        setModuloSeleccionado("T");
        Intent intent = new Intent(ModuloActivity.this, ListadoActivity.class);
        startActivity(intent);
    }
    public void avanzarCachorros(View view) {
        setModuloSeleccionado("Cachorros");
        Intent intent = new Intent(ModuloActivity.this, ListadoActivity.class);
        startActivity(intent);
    }
    public void avanzarGeri(View view) {
        setModuloSeleccionado("Geriatrico");
        Intent intent = new Intent(ModuloActivity.this, ListadoActivity.class);
        startActivity(intent);
    }

    public static String getModuloSeleccionado() {
        return moduloSeleccionado;
    }

    public static void setModuloSeleccionado(String moduloSeleccionado) {
        ModuloActivity.moduloSeleccionado = moduloSeleccionado;
    }
    public void cerrarSesion(View view) {
        Intent intent = new Intent(ModuloActivity.this, MainActivity.class);
        MainActivity.setUsuarioLogueado(null);
        startActivity(intent);
    }

    public void irInfo(View view) {
        Intent intent = new Intent(ModuloActivity.this, InfoActivity.class);
        startActivity(intent);
    }
}