package com.example.jgx.androidtrain_inwinter;


import android.os.Bundle;

public class MainActivity  {

    private int contentView;


    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(new Myview(this));
    }


    public void setContentView(int contentView) {
        this.contentView = contentView;
    }
}
