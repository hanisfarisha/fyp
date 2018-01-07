package com.example.hanis.fyp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class org_register extends AppCompatActivity implements  View.OnClickListener{
    private Button buttonRegister;
    private EditText editOrgName;
    private EditText editOrgNo;
    private EditText editOrgEmail;
    private EditText editOrgPassword;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_register);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            //profile act here
            finish();
            startActivity(new Intent(getApplicationContext(),org_profile.class));
        }
        progressDialog=new ProgressDialog(this);
        buttonRegister=(Button) findViewById(R.id.buttonRegister);
        editOrgName=(EditText) findViewById(R.id.editOrgName);
        editOrgNo=(EditText) findViewById(R.id.editOrgNo);
        editOrgEmail=(EditText) findViewById(R.id.editOrgEmail);
        editOrgPassword=(EditText) findViewById(R.id.editOrgPassword);
        textViewSignin=(TextView) findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }
    private void registerOrg() {
        String orgName = editOrgName.getText().toString().trim();
        String orgNo = editOrgNo.getText().toString().trim();
        String orgEmail = editOrgEmail.getText().toString().trim();
        String orgPassword = editOrgPassword.getText().toString().trim();
        if (TextUtils.isEmpty(orgName)) {
            //orgname is empty
            Toast.makeText(this, "Please enter orgnization name", Toast.LENGTH_SHORT).show();
            //stop func exec further
            return;
        }
        if (TextUtils.isEmpty(orgNo)) {
            //orgname is empty
            Toast.makeText(this, "Please enter orgnization no", Toast.LENGTH_SHORT).show();
            //stop func exec further
            return;
        }
        if (TextUtils.isEmpty(orgEmail)) {
            //orgname is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            //stop func exec further
            return;
        }
        if (TextUtils.isEmpty(orgPassword)) {
            //orgname is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            //stop func exec further
            return;
        }
        //if validation are ok
        //will first show a prgressbar
        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(orgEmail, orgPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), org_profile.class));

                        } else {
                            Toast.makeText(org_register.this, "Could not register! Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void  onClick(View view){
        if(view==buttonRegister){
            registerOrg();
    }
    if(view==textViewSignin){
            //will open login act
        startActivity(new Intent(this,OrgLogin.class));
    }
    }
}
