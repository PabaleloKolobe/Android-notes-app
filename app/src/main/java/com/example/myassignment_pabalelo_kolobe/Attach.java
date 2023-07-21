package com.example.myassignment_pabalelo_kolobe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.myassignment_pabalelo_kolobe.databinding.ActivityAddJournalBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

public class Attach extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityAddJournalBinding binding;
    FirebaseFirestore fStore;
    EditText addNoteContent, noteTitle;
    ProgressBar progressBar;
    FloatingActionButton attach;
    int Image_Request_Code = 7;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddJournalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);



        FloatingActionButton attach = findViewById(R.id.attach);
        binding.attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),Image_Request_Code);

            }


        });


    }




}
