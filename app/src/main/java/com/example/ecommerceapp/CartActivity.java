package com.example.ecommerceapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventButton();
    }

    private void EventButton() {
        if (MainActivity.lstCart.size() > 0){

        }else{
            //int number =
        }
    }
}
