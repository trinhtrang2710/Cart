package Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.CartActivity;
import com.example.ecommerceapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import Database.ProductManager;
import Model.Product;

public class  CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    List<Product> cartLines;
    private CartActivity cartActivity;

    public CartAdapter(List<Product> cartLines, CartActivity cartActivity){
        this.cartLines = cartLines;
        this.cartActivity = cartActivity;
    }


    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Activity to display
        Context context = parent.getContext();

        //XML to java Object
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_cart, parent, false);


        return new CartHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        Product product = cartLines.get(position);

        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return cartLines.size();
    }


    public class CartHolder extends RecyclerView.ViewHolder {
        ImageView imgCart;
        TextView txtvName,  txtvPrice, txtvNumberPr, txtvTotalOfEachPr;
        ImageButton btnInc, btnDec;
        Context context;

        public CartHolder(@NonNull View itemView, Context context) {
            super(itemView);
            txtvName  = itemView.findViewById(R.id.txtvName );
            txtvPrice = itemView.findViewById(R.id.txtvPrice);
            txtvNumberPr = itemView.findViewById(R.id.txtvNumberPr);
            txtvTotalOfEachPr = itemView.findViewById(R.id.txtvTotalOfEachPr);
            btnInc = itemView.findViewById(R.id.btnInc);
            btnDec = itemView.findViewById(R.id.btnDec);
            imgCart = itemView.findViewById(R.id.imgCart);
        }

        public void bind(Product product){
            txtvName.setText(product.getName());
            txtvNumberPr.setText(product.getQuantity()+"");
            txtvPrice.setText(product.getUnitPrice()+" VND");
            txtvTotalOfEachPr.setText(product.totalPrice() + "\nVND");

            btnInc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductManager productManager = ProductManager.getInstance(context);

                    boolean isUpdated = false;
                    product.increaseQuantity();
                    isUpdated = productManager.updateQuantity(product);


                    if( isUpdated){
                        Toast.makeText(context, "+1", Toast.LENGTH_SHORT).show();
                        //cartActivity.t.setText(productManager.getTotalPrice()+" VND");
                    }else{
                        Toast.makeText(context, "Add fail", Toast.LENGTH_SHORT).show();
                    }
                    notifyDataSetChanged();
                }

            });

            btnDec.setOnClickListener(new View.OnClickListener() {
                ProductManager productManager = ProductManager.getInstance(context);

                @Override
                public void onClick(View v) {
//                    if(product.getQuantity() ==  1){
//                        new AlertDialog.Builder(context)
//                                .setTitle("Delete product")
//                                .setMessage("Do you want to remove this product from cart?")
//                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//
//
//                                        boolean isDeleted = productManager.deleteProduct(product.getId());
//                                        if(isDeleted){
//                                            cartLines.remove(product);
//                                            cartActivity.totalPrice.setText(productManager.getTotalPrice()+" VND");
//                                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
//                                        }
//                                        notifyDataSetChanged();
//                                    }
//                                })
//                                .setNegativeButton("No", null)
//                                .show();
//
//
//                    }else{
//                        product.decreseQuantity();
//                        productManager.updateProduct(product);
//                        cartActivity.totalPrice.setText(productManager.getTotalPrice()+" VND");
//                        notifyDataSetChanged();
//                    }


                }
            });

            ThumbnailLoader task = new ThumbnailLoader();
            task.execute(product.getThumbnail());

        }

        public class ThumbnailLoader extends AsyncTask<String, Void, Bitmap> {
            URL image_url;
            HttpURLConnection urlConnection;


            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    image_url = new URL(strings[0]);
                    urlConnection = (HttpURLConnection) image_url.openConnection();
                    urlConnection.connect();

                    InputStream is = urlConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    return bitmap;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                imgCart.setImageBitmap(bitmap);
            }
        }

    }


}