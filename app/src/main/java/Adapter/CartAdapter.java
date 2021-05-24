package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecommerceapp.CartActivity;
import com.example.ecommerceapp.R;

import java.util.ArrayList;
import java.util.List;


import Model.Product;

public class CartAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Product> cartArrayList ;

    public CartAdapter(Context context,int cart_item, ArrayList<Product> cartArrayList) {
        this.context = context;
        this.layout = layout;
        this.cartArrayList = cartArrayList;
    }

    @Override
    public int getCount() {
        return cartArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder = null;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.item_cart, null);
            viewHolder.txtvName = convertView.findViewById(R.id.txtvName);
            viewHolder.txtvPrice = convertView.findViewById(R.id.txtvPrice);
            viewHolder.txtvNumberPr = convertView.findViewById(R.id.txtvNumberPr);
            viewHolder.txtvTotalOfEachPr = convertView.findViewById(R.id.txtvTotalOfEachPr);
            viewHolder.imgCart = convertView.findViewById(R.id.imgCart);
            viewHolder.btnInc = convertView.findViewById(R.id.btnInc);
            viewHolder.btnDec = convertView.findViewById(R.id.btnDec);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();

        }
        Product cart = cartArrayList.get(position);
        viewHolder.txtvName.setText(cart.getName());
        viewHolder.txtvPrice.setText((int) cart.getUnitPrice());
        viewHolder.txtvNumberPr.setText(cart.getQuantity());
        int total_price = cart.getUnitPrice() * cart.getQuantity();
        viewHolder.txtvTotalOfEachPr.setText(total_price);
        //viewHolder.imgCart.setImageBitmap(cart.getThumbnail());

        return convertView;
    }

    public static  class ViewHolder{
        ImageView imgCart;
        TextView txtvName,  txtvPrice, txtvNumberPr, txtvTotalOfEachPr;
        Button btnInc, btnDec;
    }
}
