package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import Adapter.CartAdapter;
import Database.DbHelper;

import Model.Product;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbarHome;
    EditText searchBar;
    RecyclerView rvHome;
    ImageView btnCart;
    public static DbHelper myDB;
    private CartAdapter cartAdapter;
    public List<Product> lstProduct;
    public static ArrayList<Product> lstCart;
    ProductAsyncTask asyncTask;
    private String URL_PRODUCT = "https://mpr-cart-api.herokuapp.com/products";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
      //  searchFilter();

        asyncTask = new ProductAsyncTask(this, rvHome, lstProduct);
        asyncTask.execute(URL_PRODUCT);
    }

    private void mapping() {
        toolbarHome = findViewById(R.id.toolBarHome);
        searchBar = findViewById(R.id.searchBar);
        rvHome = findViewById(R.id.rvHome);
        searchBar = findViewById(R.id.searchBar);
        btnCart = findViewById(R.id.btnCart);
        if (lstCart != null){
            lstCart = new ArrayList<>();
        }
    }




    public void goCart(View view) {
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

//    public void searchFilter() {
//        searchBar.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                filter(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//    }
//    private void filter(String text){
//        ArrayList<Product> filteredList = new ArrayList<>();
//        for (Product item : lstProduct){
//            if(item.getName().toLowerCase().contains(text.toLowerCase())){
//                filteredList.add(item);
//            }
//        }
//        cartAdapter.filterList(filteredList);
//    }

}