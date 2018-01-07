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

public class VolLogin extends AppCompatActivity implements View.OnClickListener{

    private Button btn_login;
    private EditText editTextmatrixNo;
    private EditText editTextPassword;
    private TextView textViewSignUp;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vol_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),VolSearch.class));
        }

        editTextmatrixNo = (EditText) findViewById(R.id.editTextmatrixNo);
        editTextPassword = (EditText)  findViewById(R.id.editTextPassword);
        textViewSignUp = (TextView)  findViewById(R.id.textViewSignUp);

        progressDialog = new ProgressDialog(this);

        btn_login.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);

    }

    private void userLogin(){
        String matrixNo= editTextmatrixNo.getText().toString().trim();
        String password= editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(matrixNo)){
            //matrixNo is empty
            Toast.makeText(this,"Please enter matrix No", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please enter password", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        //if email and ps not empty
        //displaying a progress dialog

        progressDialog.setMessage("Login User...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(matrixNo,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(),VolSearch.class));
                        }

                    }
                });
    }
    @Override
    public void onClick(View view) {
        if(view==btn_login){
            userLogin();
        }
        if(view==textViewSignUp){
            finish();
            startActivity(new Intent(this,vol_register.class));
        }
    }
}
