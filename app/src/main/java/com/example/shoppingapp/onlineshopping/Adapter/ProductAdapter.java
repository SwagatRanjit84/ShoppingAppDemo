package com.example.shoppingapp.onlineshopping.Adapter;

/**
 * Created by shoppingapp on 10/5/16.
 */

   import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.onlineshopping.Pojo.Products;
import com.example.shoppingapp.onlineshopping.R;

import java.util.ArrayList;
    /**
     * Created by Sushil on 7/7/2016.
     */
    public class  ProductAdapter extends ArrayAdapter<Products> {
        TextView mName,mPrice;
ImageView mImage;
        Products product;
        Context context;

        public ProductAdapter(Context context, ArrayList<Products> products) {
            super(context, 0, products);
            this.product = product;//k kaam  ????
            this.context = context;
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        @Override
        public Products getItem(int position) {
            return super.getItem(position);
        }

        @Override
        public int getPosition(Products item) {
            return super.getPosition(item);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Products product= getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {//using this for the first time else we are reusing stuff
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.aspv, parent, false);
            }
            // Lookup view for data population
          mName=(TextView) convertView.findViewById(R.id.adapter_single_product_tv_name);
          mPrice=(TextView) convertView.findViewById(R.id.adapter_single_product_tv_price);
            mImage=(ImageView) convertView.findViewById(R.id.adapter_single_product_tv_img);
            mName.setText(product.getName());
            mPrice.setText("Rs."+product.getPrice()+"/-");
            String image_url="http://sinfoma.com/api/images/"+product.getId()+"."+product.getImage();
            Glide.with(context).load(image_url).into(mImage);//glide chalako  adapter le garda context ayo  image url bhaneko agi ako url ani mImage bhaneok ata dekhauney

            return convertView;
        }
    }


