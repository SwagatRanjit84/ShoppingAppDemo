package com.example.shoppingapp.onlineshopping.fragment;


import android.app.ProgressDialog;
import android.app.job.JobInfo;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shoppingapp.onlineshopping.Adapter.ProductAdapter;
import com.example.shoppingapp.onlineshopping.JsonParsing;
import com.example.shoppingapp.onlineshopping.Pojo.Products;
import com.example.shoppingapp.onlineshopping.ProductDetail;
import com.example.shoppingapp.onlineshopping.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by shoppingapp on 10/4/16.
 */

public class FragmentOne extends Fragment{
    ListView listView;
    ProgressDialog progressDialog;
    JsonParsing jsonParsing=new JsonParsing();
    int status=0;
    ArrayList<Products> arrayList=new ArrayList<>();
    Products products;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_one,container,false);//>>>.............
        listView=(ListView) view.findViewById(R.id.fragment_1_lv);

        new ShowFeatureList().execute();

        return view;
    }

    class ShowFeatureList extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(getContext());
            progressDialog.setMessage("Loading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("category","feature");
            hashMap.put("limit","100");
            String url="http://sinfoma.com/api/getProduct.php";
            JSONObject jsonObject=jsonParsing.performPostCI(url,hashMap);
            if(jsonObject==null){
                status=1;
            }else{
                try{
                    if(jsonObject.getString("status").equals("success")){
                        status=2;
                        JSONArray jsonArray=jsonObject.getJSONArray("data");//.................
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject productObj=jsonArray.getJSONObject(i);
                            String id=productObj.getString("id");
                            String name=productObj.getString("name");
                            String category=productObj.getString("category");
                            String description=productObj.getString("description");
                            String image=productObj.getString("image");
                            String add_by=productObj.getString("add_by");
                            String add_number=productObj.getString("add_number");
                            String price=productObj.getString("price");
                            String condition=productObj.getString("condition");
                            String add_date=productObj.getString("add_date");
                            String end_date=productObj.getString("end_date");
                            String status=productObj.getString("status");
    products=new Products(id,name,category,description,image,add_by,add_number,price,condition,add_date,end_date,status);
                 arrayList.add(products);
                        }
                    }else{
                        status=3;
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        progressDialog.dismiss();
            if(status==1){
                Toast.makeText(getContext(), "NO connection", Toast.LENGTH_SHORT).show();
            }
            if(status==2){
//                Toast.makeText(getContext(), "successful list loaded", Toast.LENGTH_SHORT).show();
                ProductAdapter productsAdapter=new ProductAdapter(getContext(),arrayList);
                listView.setAdapter(productsAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Products products=(Products) parent.getItemAtPosition(position);//jun position ma click garyo tei thau ko product sab display page ma janxa
                        Intent intent=new Intent(getContext(),ProductDetail.class);
                        intent.putExtra("product_detail",products);
                        startActivity(intent);
                    }
                });
            }
            if(status==3){
                Toast.makeText(getContext(), "error loading", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
