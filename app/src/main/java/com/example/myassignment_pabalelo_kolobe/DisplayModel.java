package com.example.myassignment_pabalelo_kolobe;

public class DisplayModel {
    String Title;
    String Journal;
    String Image;

    public DisplayModel(String title, String journal, String image) {
        Title = title;
        Journal = journal;
        Image = image;
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
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
