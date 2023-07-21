package com.example.myassignment_pabalelo_kolobe;

public class Journals {


    private String title;
    private String journal;


    public Journals(){


    }

    public Journals(String title, String journal){
        this.title = title;
        this.journal = journal;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String content) {
        this.journal = journal;
    }
}
