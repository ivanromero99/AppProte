package com.example.appprote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appprote.model.Formulario;
import com.example.appprote.model.Perro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import static com.example.appprote.MainActivity.databaseReference;


public class FormularioVerActivity extends AppCompatActivity {

    Formulario formularioSeleccionado;
    List<Formulario> listaFormularios;
    TextView nombrePerro;
    EditText nombreForm, correoForm, telefonoForm, descripcionForm;
    int indice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_ver);

        nombreForm = (EditText) findViewById(R.id.editTextTextPersonName);
        correoForm = (EditText)findViewById(R.id.editTextTextPersonName2);
        telefonoForm = (EditText)findViewById(R.id.editTextPhone);
        descripcionForm = findViewById(R.id.editTextTextPersonName3);

        formularioSeleccionado = ListadoFormularioActivity.getFormularioSeleccionado();

        rellenar();

    }

    private void rellenar() {

        nombrePerro = (TextView)findViewById(R.id.textView33);
        nombrePerro.setText(formularioSeleccionado.getPerro().getNombre());
        nombreForm.setText(formularioSeleccionado.getNombre());
        correoForm.setText(formularioSeleccionado.getCorreo());
        telefonoForm.setText(formularioSeleccionado.getTelefono());
        descripcionForm.setText(formularioSeleccionado.getDescripcion());

        nombreForm.setEnabled(false);
        correoForm.setEnabled(false);
        telefonoForm.setEnabled(false);
        descripcionForm.setEnabled(false);

    }


    public void volver(View view) {
        Intent intent = new Intent(FormularioVerActivity.this, ListadoFormularioActivity.class);
        startActivity(intent);
    }

}