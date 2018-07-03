package com.example.dibujo.cursoandroid20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private View btn,btntres;
    private EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btnEnviar);
        btntres=findViewById(R.id.btnAcciones);
        btn.setOnClickListener(new enviar());
        btntres.setOnClickListener(new acciones());
    }
    private class  enviar implements OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            et=findViewById(R.id.txtNombre);
            intent.putExtra("idNombre",et.getText().toString());
            startActivity(intent);
        }
    }
    private class  acciones implements OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MainActivity.this,ThirdActivity.class);
            startActivity(intent);
        }
    }
}
