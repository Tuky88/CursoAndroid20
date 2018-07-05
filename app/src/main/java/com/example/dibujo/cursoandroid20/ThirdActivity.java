package com.example.dibujo.cursoandroid20;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText phone, web;
    private ImageButton txtphone, txtweb, txtcam;
    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        phone = findViewById(R.id.txtCall);
        web = findViewById(R.id.txtWeb);
        txtphone = findViewById(R.id.imgBtnCall);
        txtweb = findViewById(R.id.imgBtnWeb);
        txtcam = findViewById(R.id.imgBtnCamera);
        txtphone.setOnClickListener(new call());
        txtweb.setOnClickListener(new buscar());

    }
    private class buscar implements View.OnClickListener
    {

        @Override
        public void onClick(View view) {
            if(!web.getText().toString().isEmpty())
            {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://"+web.getText().toString()));
                startActivity(i);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permiso = permissions[0];
                int result = grantResults[0];
                if (permiso.equals(Manifest.permission.CALL_PHONE)) {
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        Intent iCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone.getText().toString()));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(iCall);
                    }
                } else {
                    Toast.makeText(ThirdActivity.this, "SIN PERMISO--", Toast.LENGTH_LONG).show();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private class call implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (!phone.getText().toString().isEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    newerVersions();
                } else {
                    olderVersions(phone.getText().toString());
                }

            } else {
                Toast.makeText(ThirdActivity.this, "ERROR DE NUMERO", Toast.LENGTH_LONG).show();
            }

        }

        private void olderVersions(String numero) {
            Intent iCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numero));

            if (checarPermiso(Manifest.permission.CALL_PHONE))
                startActivity(iCall);
            else
                Toast.makeText(ThirdActivity.this,"SIN PERMISO",Toast.LENGTH_LONG).show();
        }
        @RequiresApi(api = Build.VERSION_CODES.M)
        private void newerVersions()
        {
            if(!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE))
            {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},PHONE_CALL_CODE);
            }
            else
            {
                Toast.makeText(ThirdActivity.this,"ACTIVE LOS PERMISOS DE LLAMADA",Toast.LENGTH_LONG).show();
                Intent i=new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setData(Uri.parse("package"+getPackageName()));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(i);

            }
        }
    }
    private boolean checarPermiso(String permiso){
        int res=this.checkCallingOrSelfPermission(permiso);
        return res == PackageManager.PERMISSION_GRANTED;
    }
}
