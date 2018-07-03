package com.example.dibujo.cursoandroid20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle bundle=getIntent().getExtras();
        et=findViewById(R.id.txtwNombre);
        if (!bundle.getString("idNombre").isEmpty())
        {
            et.setText(bundle.getString("idNombre"));
        }
        else
        {
            et.setText("-");
            Toast.makeText(this,"ERROR",Toast.LENGTH_LONG).show();
        }
    }
}
