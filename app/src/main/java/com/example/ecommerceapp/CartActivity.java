package com.example.ecommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import Adapter.CartAdapter;
import Database.ProductManager;
import Model.Cart;
import Model.Product;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnBack;
    ListView lvCart;
    CartAdapter adapter;
    List<Product> lstCart = new ArrayList<>();
    TextView txtvPrice;
    ProductManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        mapping();
        btnBack.setOnClickListener(this);
        adapter = new CartAdapter(CartActivity.this, R.layout.item_cart, (ArrayList<Product>) lstCart);
        adapter.notifyDataSetChanged();
        getData();
    }

    private void getData() {
        lstCart = manager.getAllData();
    }

    private void mapping() {
        lvCart = findViewById(R.id.lvCart);
        txtvPrice = findViewById(R.id.txtvPrice);
        btnBack = findViewById(R.id.btnBack);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                Intent i = new Intent(CartActivity.this, MainActivity.class);
                startActivity(i);
                break;
            default:
        }


    }
}
