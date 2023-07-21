package com.example.myassignment_pabalelo_kolobe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText pfullname , plastname , pphone, pemail, ppassword, pconpassword;
    TextView ploginNowBtn;
    Button pregisterBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;


   // DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        pfullname = findViewById(R.id.fullname);
        plastname = findViewById(R.id.lastname);
        pphone = findViewById(R.id.phone);
        pemail = findViewById(R.id.email);
        ppassword = findViewById(R.id.password);
        pconpassword = findViewById(R.id.conpassword);
        ploginNowBtn = findViewById(R.id.loginBtn);
        pconpassword = findViewById(R.id.conpassword);

        pregisterBtn = findViewById(R.id.registerBtn);
        //oginNowBtn = findViewById(R.id.loginNow);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);


        pregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting the data from the editTexts
                String fullname = pfullname.getText().toString().trim();
                String lastname = plastname.getText().toString().trim();
                String phone = pphone.getText().toString().trim();
                String email = pemail.getText().toString().trim();
                String password = ppassword.getText().toString().trim();
                String conpassword = pconpassword.getText().toString().trim();

                if(fullname.isEmpty()|| lastname.isEmpty()||phone.isEmpty()||email.isEmpty()||password.isEmpty()||conpassword.isEmpty()){
                    Toast.makeText(Register.this,"All fields are required",Toast.LENGTH_SHORT).show();
                    return;

                }
                else if(!password.equals(conpassword)){
                    Toast.makeText(Register.this,"Password need to match",Toast.LENGTH_SHORT).show();
                }

                else if(ppassword.length() < 6){
                    ppassword.setError("Password must be more than 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(Register.this, "Your account has been created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Login.class));
                        }else {

                            Toast.makeText(Register.this,"Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }


        });

        ploginNowBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });




    }

}