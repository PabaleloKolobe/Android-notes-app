package com.example.myassignment_pabalelo_kolobe;

public class StudentModel {

    String Title;
    String Journal;
    String image;

    public StudentModel(){

    }

    public StudentModel(String title, String journal, String image){

        Title = title;
        Journal = journal;
        this.image = image;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getJournal() {
        return Journal;
    }

    public void setJournal(String journal) {
        Journal = journal;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
