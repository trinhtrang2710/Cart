package Adapter;

import android.content.Context;
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

import com.example.ecommerceapp.MainActivity;
import com.example.ecommerceapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import Model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private Context context;
    private List<Product> lstProduct;


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
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
        Product product = lstProduct.get(position);
        holder.bind(product);
//        holder.txtvDesc.setText(lstProduct.get(position).getName());
//        holder.txtvPrice.setText((int) lstProduct.get(position).getUnitPrice());
//        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return lstProduct.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardViewP;
        static ImageView imgProduct;
        TextView txtvDesc, txtvPrice;
        ImageButton btnAdd;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewP = itemView.findViewById(R.id.cardViewP);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtvDesc = itemView.findViewById(R.id.txtvDesc);
            txtvPrice = itemView.findViewById(R.id.txtvPrice);
            btnAdd = itemView.findViewById(R.id.btnAdd);
        }

        public void bind(Product product) {
            txtvDesc.setText(product.getName());
            txtvPrice.setText(product.getUnitPrice() + "VND");
//            Toast.makeText(c, ""+ product.getName(), Toast.LENGTH_SHORT).show();
            ImageLoader task = new ImageLoader();
            task.execute(product.getThumbnail());
        }

        public static class ImageLoader extends AsyncTask<String, Void, Bitmap> {
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
