package com.example.ecommerceapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Adapter.ProductAdapter;
import Model.Product;

public class ProductAsyncTask extends AsyncTask<String, Void, String> {
    ProductAdapter adapter;
    Context context;
    RecyclerView recyclerView;
    List<Product> list;

    public ProductAsyncTask(Context context, RecyclerView recyclerView, List<Product> list) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.list = list;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            urlConnection.disconnect();
            InputStream inputStream = urlConnection.getInputStream();
            String line;
            Scanner sc = new Scanner(inputStream);
            StringBuilder result = new StringBuilder();
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s == null) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i ++){
                JSONObject jsonProduct = jsonArray.getJSONObject(i);

                int productId = jsonProduct.getInt("id");
                String productName = jsonProduct.getString("name");
                String productThumbnail = jsonProduct.getString("thumbnail");
                double productPrice = jsonProduct.getDouble("unitPrice");
                Product product = new Product(productId, productThumbnail, productName,productPrice);

                list.add(product);

            }
        } catch (JSONException e){
            e.printStackTrace();
        }

        ProductAdapter productAdapter = new ProductAdapter(context, list);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setAdapter(productAdapter);
    }
}