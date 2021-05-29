package com.example.ecommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapter.CartAdapter;
import Database.ProductManager;

import Model.Product;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnBack;
    RecyclerView rvCart;
    CartAdapter adapter;
    List<Product> lstCart;
    TextView txtvPrice, txtTotalPrice;
    ProductManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        mapping();
        manager = ProductManager.getInstance(this);
        lstCart =manager.getAllData();
//        for (int i = 0; i < lstCart.size(); i++){
//            Product p = lstCart.get(i);
//            String name = p.getName();
//            int price = p.getUnitPrice();
//            Toast.makeText(this, "" + name + price, Toast.LENGTH_SHORT).show();
//        }

        int sumP = 0;
        for (int i=0; i<lstCart.size(); i++){
            Product pd = lstCart.get(i);
            double price = pd.totalPrice();
            sumP += price;
        }
        String s = String.valueOf(sumP);
        txtTotalPrice.setText(s + " VND");

        btnBack.setOnClickListener(this);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(lstCart, this);
        rvCart.setAdapter(adapter);

    }




    private void mapping() {
        rvCart = findViewById(R.id.rvCart);
        txtvPrice = findViewById(R.id.txtvPrice);
        btnBack = findViewById(R.id.btnBack);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);

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
