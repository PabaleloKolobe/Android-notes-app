package com.example.myassignment_pabalelo_kolobe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText pEmail, pPassword;
    Button ploginBtn;
    TextView pregisterNowBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         pEmail = findViewById(R.id.email);
         pPassword = findViewById(R.id.password);
         ploginBtn = findViewById(R.id.loginBtn);
        pregisterNowBtn = findViewById(R.id.registerNowBtn);
        progressBar = findViewById(R.id.progressBar2);


       ploginBtn.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View view) {
               String email = pEmail.getText().toString().trim();
               String password = pPassword.getText().toString().trim();

               if(TextUtils.isEmpty(email)){
                   pEmail.setError("Email is required");
                   return;
               }


               if(TextUtils.isEmpty(password)){
                   pPassword.setError("Email is required");
                   return;
               }

               progressBar.setVisibility(View.VISIBLE);


               //authnetication

               fAuth = FirebaseAuth.getInstance();

               fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){

                           Toast.makeText(Login.this, "User Logged in", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),Dashboard.class));

                       }else{
                           Toast.makeText(Login.this,"Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                       }

                   }
               });
           }
       });


       pregisterNowBtn.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),Register.class));
           }
       });





    }
}