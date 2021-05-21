package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import Adapter.ProductAdapter;
import Model.Cart;
import Model.Product;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbarHome;
    SearchView searchBar;
    RecyclerView rvHome;
    public List<Product> lstProduct;
    public static ArrayList<Cart> lstCart;
    private String URL_PRODUCT = "https://mpr-cart-api.herokuapp.com/products";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();

//        ProductAdapter productAdapter = new ProductAdapter(this, lstProduct);
//        rvHome.setLayoutManager(new GridLayoutManager(this, 2));
//        rvHome.setAdapter(productAdapter);
        ProductAsyncTask asyncTask = new ProductAsyncTask(this, rvHome, lstProduct);
        asyncTask.execute(URL_PRODUCT);
    }

    private void mapping() {
        toolbarHome = findViewById(R.id.toolBarHome);
        searchBar = findViewById(R.id.searchBar);
        rvHome = findViewById(R.id.rvHome);
        if (lstCart != null){
            lstCart = new ArrayList<>();
        }
    }

}