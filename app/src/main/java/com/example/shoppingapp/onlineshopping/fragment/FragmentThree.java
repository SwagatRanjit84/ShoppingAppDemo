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

public class FragmentThree extends Fragment{
    ListView listView;//list ma herna lai listview
    ProgressDialog progressDialog;//loading ko lagi
    JsonParsing jsonParsing=new JsonParsing();//net bata data lina parsing garnu parxa
    int status=0;
    ArrayList<Products> arrayList=new ArrayList<>();//net bata data array ko form ma ako huncha ani tyo sab array array combine bhara arraylist ma rakhney
    Products products;//each product
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_three,container,false);//view ma fragment 1,2,3 inflate gareko huncha yo chai fragment three ko lagi
        listView=(ListView) view.findViewById(R.id.fragment_3_lv); //yo listview chai 3rd fragment ma dekhauney


        new ShowFeatureList().execute();//talako class execute gareko

        return view;
    }

    class ShowFeatureList extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {//paila kholda
            super.onPreExecute();//super means parent class function harulai denote garxa
            progressDialog=new ProgressDialog(getContext());//get context ..yesma khas aile kun activity ma cha tesma dekhaunu parney and tei lekhnu parney
            //but aile hamle fragment ma kaam gardai xau ..i.e dherai fragment autai view ma huna sakxa so hamle context lekhne fragment ko case ma
            progressDialog.setMessage("Loading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String,String> hashMap=new HashMap<>();//hasmap key ra value ko relation
            hashMap.put("category","recent");//postman bata category is reset
            hashMap.put("limit","100");// and 100 ota samma auna sakxa
            String url="http://sinfoma.com/api/getProduct.php";//url
            JSONObject jsonObject=jsonParsing.performPostCI(url,hashMap);//aba excess garney thau ko url ma hashmap pass gareko
            //jsonObject ma required object aunxa
            if(jsonObject==null){//if connection error or kei le json object ayena bhanney
                status=1;
            }else{
                try{
                    if(jsonObject.getString("status").equals("success")){//if object ma success xa bhanney
                        status=2;
                        JSONArray jsonArray=jsonObject.getJSONArray("data");//data le array lai store gariraxa..do jsonObect jun ako xa tesbata jsonArray matra liney jun chai data ma huncha
                        for(int i=0;i<jsonArray.length();i++){//jsonArray i.e data ma Array huncha and 1 object =1 length  so jaba samma object haru sab aundaina
                            JSONObject productObj=jsonArray.getJSONObject(i);//productObj ma auta i position ko object ayo
                            String id=productObj.getString("id"); //tyo i position object ko id nikalyo
                            String name=productObj.getString("name");//likewise
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
                            products=new Products(id,name,category,description,image,add_by,add_number,price,condition,add_date,end_date,status);//aile ako sab value product ko constructor ma pass gareko for initialization
                            arrayList.add(products);// so auta auta products add gareko arrayList ma
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
            progressDialog.dismiss();//dialog box hatayo
            if(status==1){//toast if no internet
                Toast.makeText(getContext(), "NO connection", Toast.LENGTH_SHORT).show();
            }
            if(status==2){//
//                Toast.makeText(getContext(), "successful list loaded", Toast.LENGTH_SHORT).show();
                ProductAdapter productsAdapter=new ProductAdapter(getContext(),arrayList);//arrayList lai Listview ma lana auta adapter chahinxa so adapter ma kun context(activity /page,,ani kun arrayList dekhauney bhanera pass gareko)
                listView.setAdapter(productsAdapter);//aba adapter ko help le arraylist lai listview ma display garney
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//aba hamle listview ma bhako item lai click garxau so OnItemClickListener
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//auta page ko Listview bhaneko parent  ho
                        Products products=(Products) parent.getItemAtPosition(position);//so parent ko jun position ma click garyo tei thau ko product sab display page ma janxa
                        Intent intent=new Intent(getContext(),ProductDetail.class);//aba yo context bata productDetail ma janey
                        intent.putExtra("product_detail",products);//yo products jun chai mathi nikaleko xa tyo sab productDetail ma laney through product_detail name
                        startActivity(intent);//start this activity
                    }
                });
            }
            if(status==3){
                Toast.makeText(getContext(), "error loading", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
