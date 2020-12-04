package com.example.appprote;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.appprote.model.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import com.example.appprote.model.Perro;

import static com.example.appprote.MainActivity.databaseReference;


public class CrearActivity extends AppCompatActivity {

    EditText nomPerro, razaPerro, edadPerro, descripcionPerro, moduloPerro, urlPerro;
    String stringNombre, stringRaza, stringEdad, stringDescrip, stringModulo, stringUrl;
    RadioGroup rg;
    String sexoPerro;
    static Perro perrete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        nomPerro = (EditText) findViewById(R.id.inputNombrePerro);
        razaPerro = (EditText)findViewById(R.id.inputRazaPerro);
        edadPerro = (EditText)findViewById(R.id.inputEdadPerro);
        descripcionPerro = findViewById(R.id.inputDescripcionPerro);
        moduloPerro = (EditText) findViewById(R.id.inputModuloPerro);
        urlPerro = (EditText)findViewById(R.id.inputURLPerro);
        moduloPerro.setText(ModuloActivity.getModuloSeleccionado());
        moduloPerro.setEnabled(false);

        rg = (RadioGroup) findViewById(R.id.radioGroup);


    }

    public void guardarPerro(View view) {
        Intent intent = new Intent(CrearActivity.this, ListadoActivity.class);



        // Hay que crear el perro en la DB
        if(validacion()) {
            perrete = new Perro();

            String id = UUID.randomUUID().toString();
            perrete.setId(id);
            perrete.setNombre(stringNombre);
            perrete.setRaza(stringRaza);
            perrete.setEdad(Integer.parseInt(stringEdad));
            perrete.setDescripcion(stringDescrip);
            perrete.setSexo(sexoPerro);
            perrete.setModulo(stringModulo);
            perrete.setUrl(stringUrl);

            databaseReference.child("Perro").child(perrete.getId()).setValue(perrete);

            startActivity(intent);
        }
    }

    private boolean validacion() {
        boolean validacion = true;
        stringNombre = nomPerro.getText().toString();
        stringRaza = razaPerro.getText().toString();
        stringEdad = edadPerro.getText().toString();
        stringDescrip = descripcionPerro.getText().toString();
        stringModulo = moduloPerro.getText().toString();
        stringUrl = urlPerro.getText().toString();
        sexoPerro  = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();

        if(stringNombre.equals("")){
            nomPerro.setError("Requerido");
            validacion = false;

        } else if(stringRaza.equals("")){
            razaPerro.setError("Requerido");
            validacion = false;

        } else if(stringEdad.equals("")){
            edadPerro.setError("Requerido");
            validacion = false;

        } else if(stringDescrip.equals("")){
            descripcionPerro.setError("Requerido");
            validacion = false;

        } else if(stringModulo.equals("")){
            moduloPerro.setError("Requerido");
            validacion = false;

        }
        return validacion;

    }
}