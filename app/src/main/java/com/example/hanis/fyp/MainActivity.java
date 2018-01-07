package com.example.hanis.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    public Button btn_vol;
    public Button btn_org;
    public void init(){
        btn_vol= (Button)findViewById(R.id.btn_vol);
        btn_org= (Button)findViewById(R.id.btn_org);
        btn_vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy=new Intent (MainActivity.this,vol_register.class);
                startActivity(toy);
            }
        });
        btn_org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy2=new Intent (MainActivity.this,org_register.class);
                startActivity(toy2);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

}