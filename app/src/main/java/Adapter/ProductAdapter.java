package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.R;

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
        holder.imgProduct.setImageResource(Integer.parseInt(lstProduct.get(position).getThumbnail()));
        holder.txtvDesc.setText(lstProduct.get(position).getName());
        holder.txtvPrice.setText((int) lstProduct.get(position).getUnitPrice());
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return lstProduct.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardViewP;
        ImageView imgProduct;
        TextView txtvDesc, txtvPrice;
        ImageButton btnAdd;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewP = itemView.findViewById(R.id.cardViewP);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtvDesc = itemView.findViewById(R.id.txtvDesc);
            txtvPrice = itemView.findViewById(R.id.txtvPrice);
        }
    }
}
