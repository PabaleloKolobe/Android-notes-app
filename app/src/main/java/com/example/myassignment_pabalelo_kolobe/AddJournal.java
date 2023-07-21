package com.example.myassignment_pabalelo_kolobe;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myassignment_pabalelo_kolobe.databinding.ActivityAddJournalBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.sql.Ref;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddJournal extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityAddJournalBinding binding;
    FirebaseFirestore fStore;
    EditText noteContent, noteTitle;
    ProgressBar progressBar;
    FloatingActionButton fab;
    FirebaseStorage mStorage;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    //int Image_Request_Code = 7;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    //public Uri imageUri;
    private static final int Gallery_Code = 1;
    Uri imageUrl = null;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddJournalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        fStore = FirebaseFirestore.getInstance();
        noteContent = findViewById(R.id.addNoteContent);
        noteTitle = findViewById(R.id.addNoteTitle);
        progressBar = findViewById(R.id.progressBar);


        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Student");
        mStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);


        FloatingActionButton map = findViewById(R.id.map);
        binding.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddJournal.this,Map.class);
                startActivity(intent);
            }
        });


        FloatingActionButton attach = findViewById(R.id.attach);
        binding.attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, Gallery_Code);

            }


        });



        FloatingActionButton fab = findViewById(R.id.fab);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String tt = noteTitle.getText().toString().trim();
                 String jn = noteContent.getText().toString().trim();

                 if(!(tt.isEmpty()&& jn.isEmpty()&& imageUrl!=null)){

                     progressDialog.setTitle("Uploading Journal");
                     progressDialog.show();

                     StorageReference filepath = mStorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                     filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                         @Override
                         public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                             Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Uri> task) {
                                     String t = task.getResult().toString();

                                     DatabaseReference newPost = mRef.push();
                                     newPost.child("title").setValue(tt);
                                     newPost.child("Journal").setValue(jn);
                                     newPost.child("image").setValue(task.getResult().toString());
                                     progressDialog.dismiss();

                                     Intent intent = new Intent(AddJournal.this, RetrieveDatainRecyclerView.class);
                                     startActivity(intent);


                                 }
                             });
                         }
                     });

                 }
            }
        });



    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== Gallery_Code && resultCode==RESULT_OK){

            imageUrl=data.getData();
            binding.attach.setImageURI(imageUrl);
        }




    }

    }



