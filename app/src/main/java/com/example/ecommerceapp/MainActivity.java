package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

//import android.widget.SearchView;
import android.widget.EditText;
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
    public List<Product> lstProduct;
    public static ArrayList<Cart> lstCart;
    ProductAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();

        asyncTask = new ProductAsyncTask(this, rvHome, lstProduct);
        asyncTask.execute();
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