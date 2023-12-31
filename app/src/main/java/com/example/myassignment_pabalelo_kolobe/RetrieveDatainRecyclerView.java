package com.example.myassignment_pabalelo_kolobe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class RetrieveDatainRecyclerView extends AppCompatActivity {

    FirebaseStorage mStorage;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    RecyclerView recyclerView;
    StudentAdpater studentAdpater;
    List<StudentModel> studentMdlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_datain_recycler_view);



        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Student");
        mStorage = FirebaseStorage.getInstance();
        recyclerView=findViewById(R.id.recyclerview_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentMdlist = new ArrayList<StudentModel>();
        studentAdpater = new StudentAdpater(RetrieveDatainRecyclerView.this, studentMdlist);
        recyclerView.setAdapter(studentAdpater);

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                StudentModel studentModel = dataSnapshot.getValue(StudentModel.class);
                studentMdlist.add(studentModel);
                studentAdpater.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}