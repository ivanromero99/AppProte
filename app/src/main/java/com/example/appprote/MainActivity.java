package com.example.appprote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.appprote.model.Perro;
import com.example.appprote.model.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    public static FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference;
    private static Usuario usuarioLogueado;
    EditText inputUsuario, inputPassword;
    String stringUsuario, stringPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarFirebase();
    }

    public void avanzar(View view) {
        Intent intent = new Intent(MainActivity.this, ModuloActivity.class);
        setUsuarioLogueado(null);
        startActivity(intent);
    }

    public void iniciar(View view) {
        Intent intent = new Intent(MainActivity.this, ModuloActivity.class);
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Comprobar el login
                inputUsuario = (EditText) findViewById(R.id.inputUsuario);
                inputPassword = (EditText) findViewById(R.id.inputPassword);
                stringUsuario = inputUsuario.getText().toString();
                stringPassword = inputPassword.getText().toString();

                if (validacion()) {
                    boolean login = false;
                    for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                        Usuario u = objSnaptshot.getValue(Usuario.class);
                        if (u.getUsuario().equals(stringUsuario) && u.getPassword().equals(stringPassword)) {
                            login = true;
                            usuarioLogueado = u;
                        }
                    }
                    if (login) {
                        startActivity(intent);
                    } else{
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Error al iniciar sesión", Toast.LENGTH_SHORT);

                        toast1.show();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private boolean validacion() {
        boolean validacion = true;
        stringUsuario = inputUsuario.getText().toString();
        stringPassword = inputPassword.getText().toString();

        if(stringUsuario.equals("")){
            inputUsuario.setError("Requerido");
            validacion = false;

        } else if(stringPassword.equals("")){
            inputPassword.setError("Requerido");
            validacion = false;

        }
        return validacion;

    }

    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public static void setUsuarioLogueado(Usuario usuarioLogueado) {
        MainActivity.usuarioLogueado = usuarioLogueado;
    }
}