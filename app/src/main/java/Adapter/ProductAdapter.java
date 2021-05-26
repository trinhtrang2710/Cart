package Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.CartActivity;
import com.example.ecommerceapp.MainActivity;
import com.example.ecommerceapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import Database.DbHelper;
import Database.DbSchema;
import Database.ProductManager;
import Model.Product;

import static com.example.ecommerceapp.MainActivity.myDB;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private Context context;
    private List<Product> lstProduct;
    ProductManager manager;

    public ProductAdapter(Context context, List<Product> lstProduct) {
        this.context = context;
        this.lstProduct = lstProduct;
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_product, parent, false);
        return new MyViewHolder(view, context) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
        Product product = lstProduct.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return lstProduct.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardViewP;
        ImageView imgProduct;
        TextView txtvDesc, txtvPrice;
        ImageButton btnAdd;
        private Context context;


        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            cardViewP = itemView.findViewById(R.id.cardViewP);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtvDesc = itemView.findViewById(R.id.txtvDesc);
            txtvPrice = itemView.findViewById(R.id.txtvPrice);
            btnAdd = itemView.findViewById(R.id.btnAdd);

            this.context = context;
        }

        public void bind(Product product) {
            txtvDesc.setText(product.getName());
            txtvPrice.setText(product.getUnitPrice() + " VND");
            ImageLoader task = new ImageLoader();
            task.execute(product.getThumbnail());
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager = ProductManager.getInstance(context);
                    
                    manager.addProduct(product);
                    Toast.makeText(context , "Add product ok" , Toast.LENGTH_LONG).show();
                }
            });
        }

        public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
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
                imgProduct.setImageBitmap(bitmap);
            }
        }
    }
}
