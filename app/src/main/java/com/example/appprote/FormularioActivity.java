package com.example.appprote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.appprote.model.Formulario;
import com.example.appprote.model.Perro;

import java.util.UUID;
import static com.example.appprote.MainActivity.databaseReference;

public class FormularioActivity extends AppCompatActivity {

    EditText nombreForm, correoForm, telefonoForm, descripcionForm;
    String nombre, correo, telefono, descripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        nombreForm = (EditText) findViewById(R.id.editTextTextPersonName);
        correoForm = (EditText)findViewById(R.id.editTextTextPersonName2);
        telefonoForm = (EditText)findViewById(R.id.editTextPhone);
        descripcionForm = findViewById(R.id.editTextTextPersonName3);


    }

    public void enviarFormulario(View view){
        if(validacion()) {
            Intent intent = new Intent(FormularioActivity.this, VerPerroActivity.class);


            Formulario f = new Formulario();
            f.setCorreo(correo);
            f.setDescripcion(descripcion);
            f.setNombre(nombre);
            f.setTelefono(telefono);
            f.setPerro(ListadoActivity.getPerroSeleccionado());
            String id = UUID.randomUUID().toString();
            f.setId(id);


            databaseReference.child("Formulario").child(f.getId()).setValue(f);

            Toast t=Toast.makeText(this,R.string.enviado, Toast.LENGTH_SHORT);
            startActivity(intent);
            t.show();
        }
    }

    private boolean validacion() {
        boolean validacion = true;
        nombre = nombreForm.getText().toString();
        correo = correoForm.getText().toString();
        telefono = telefonoForm.getText().toString();
        descripcion = descripcionForm.getText().toString();


        if(nombre.equals("")){
            nombreForm.setError("Requerido");
            validacion = false;

        } else if(correo.equals("")){
            correoForm.setError("Requerido");
            validacion = false;

        } else if(telefono.equals("")){
            telefonoForm.setError("Requerido");
            validacion = false;

        } else if(descripcion.equals("")){
            descripcionForm.setError("Requerido");
            validacion = false;

        }

        return validacion;

    }
}