package com.example.myassignment_pabalelo_kolobe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class StudentAdpater extends RecyclerView.Adapter<StudentAdpater.ViewHolder> {

    Context context;
    List<StudentModel> studentModelList;

    public StudentAdpater(Context context, List<StudentModel> studentModelList) {
        this.context = context;
        this.studentModelList = studentModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_row_for_recyler_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StudentModel studentModel = studentModelList.get(position);
        holder.tvjournal.setText("Journal content " + studentModel.getJournal());
        holder.tvtitle.setText("Title" + studentModel.getTitle());

        String imageUri = null;
        imageUri = studentModel.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);

    }

    @Override
    public int getItemCount() {

        return studentModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvtitle,tvjournal;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_recyclerView_id);
            tvtitle = itemView.findViewById(R.id.tvtitle_recyclerview_id);
            tvjournal = itemView.findViewById(R.id.tvjournal_recyclerView_id);

        }
    }
}
