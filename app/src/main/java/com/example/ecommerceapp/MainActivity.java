package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

//import android.widget.SearchView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import Adapter.ProductAdapter;
import Model.Cart;
import Model.Product;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbarHome;
    EditText searchBar;
    RecyclerView rvHome;
    ImageView btnCart;
    public List<Product> lstProduct;
    public static ArrayList<Cart> lstCart;
    ProductAsyncTask asyncTask;
    private String URL_PRODUCT = "https://mpr-cart-api.herokuapp.com/products";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();

        asyncTask = new ProductAsyncTask(this, rvHome, lstProduct);
        asyncTask.execute(URL_PRODUCT);
    }

    private void mapping() {
        toolbarHome = findViewById(R.id.toolBarHome);
        searchBar = findViewById(R.id.searchBar);
        rvHome = findViewById(R.id.rvHome);
        btnCart = findViewById(R.id.btnAdd);
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

}