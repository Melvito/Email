package com.example.email;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainPrincipal extends AppCompatActivity {
    EditText direccion,Asunto,texto;
    Button enviar,limpiar,salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);
    direccion = findViewById(R.id.EdtDirecto);
    Asunto = findViewById(R.id.EdtAsunto);
    texto = findViewById(R.id.EdtContenido);
    enviar = findViewById(R.id.BtnEnviar);
    limpiar = findViewById(R.id.Btnlimpiar);
    salir = findViewById(R.id.BtnSalir);
    enviar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           if(direccion.getText().toString().isEmpty()){
               MessageDialog();
           }
           else{
               if (!direccion.getText().toString().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(direccion.getText().toString()).matches()){
                   generaremail();
               }
               else{
                 MessageDialog2();
               }
           }
        }
    });
    salir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    });
    limpiar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            direccion.setText("");
            Asunto.setText("");
            texto.setText("");
        }
    });
    }
    public void generaremail(){
        Intent inten = new Intent(Intent.ACTION_SENDTO);
        inten.setData(Uri.parse("mailto:"));
        inten.putExtra(Intent.EXTRA_EMAIL,new String[]{direccion.getText().toString()});
        inten.putExtra(Intent.EXTRA_SUBJECT, Asunto.getText().toString());
        inten.putExtra(Intent.EXTRA_TEXT,texto.getText().toString());
        startActivity(Intent.createChooser(inten, "Seleccione una Aplicacion"));
        direccion.setText("");
        Asunto.setText("");
        texto.setText("");
    }

    public void MessageDialog(){
        new AlertDialog.Builder(MainPrincipal.this)
                .setTitle("Error")
                .setMessage("No has ingresado los datos!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }
    public void MessageDialog2(){
        new AlertDialog.Builder(MainPrincipal.this)
                .setTitle("Error")
                .setMessage("No has ingresado un correo Electronico valido!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }
}