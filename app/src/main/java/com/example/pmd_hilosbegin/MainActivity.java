package com.example.pmd_hilosbegin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botonSinHilos;
    Button botonConHilos;
    ProgressBar barraProgreso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonSinHilos = findViewById(R.id.buttonSinHilos);
        botonConHilos = findViewById(R.id.buttonConHilos);
        barraProgreso = findViewById(R.id.progressBar);

        botonSinHilos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barraProgreso.setMax(100);
                barraProgreso.setProgress(0);

                for (int i = 0; i < 10; i++) {
                    tareaLarga();
                    barraProgreso.incrementProgressBy(10);
                }

                Toast.makeText(getApplicationContext(), "Tarea Finalizada", Toast.LENGTH_SHORT).show();
            }
        });

        botonConHilos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barraProgreso.setMax(100);
                new Thread(new Runnable() {
                    public void run() {
                        barraProgreso.post(new Runnable() {
                            public void run() {
                                barraProgreso.incrementProgressBy(0);
                            }
                        });

                        for (int i = 0; i < 10; i++) {
                           tareaLarga();
                           barraProgreso.post(new Runnable() {
                               public void run() {
                                   barraProgreso.incrementProgressBy(10);
                               }
                           });

                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Tarea Finalizada", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                }).start();


            }
            /*@Override
            public void onClick(View v) {
                barraProgreso.setMax(100);
                barraProgreso.setProgress(0);

                for (int i = 0; i < 10; i++) {
                    tareaLarga();
                    barraProgreso.incrementProgressBy(10);
                }
                Toast.makeText(getApplicationContext(),"Tarea Finalizada",Toast.LENGTH_SHORT).show();
            }*/


        });

    }

    public void tareaLarga() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}