package com.example.shoppingapp.onlineshopping;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by shoppingapp on 10/4/16.
 */

public class RegisterActivity extends AppCompatActivity {
    EditText mUsername,mEmail,mPassword,mRPassword,mAddress,mPhone;
    String username,email,password,rpassword,addresss,phone;
    Button mRegister;
    ProgressDialog progressDialogue;

    int status=0;
    JsonParsing jsonparsing=new JsonParsing();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        mUsername=(EditText)  findViewById(R.id.RA_et_name);
        mEmail=(EditText)  findViewById(R.id.RA_et_email);
        mPassword=(EditText)  findViewById(R.id.RA_et_password);
        mRPassword=(EditText)  findViewById(R.id.RA_et_rpassword);
        mAddress=(EditText)  findViewById(R.id.RA_et_address);
        mPhone=(EditText)  findViewById(R.id.RA_et_phone);
        mRegister=(Button) findViewById(R.id.RA_button_register);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=mUsername.getText().toString();
                email=mEmail.getText().toString();
                password=mPassword.getText().toString();
                rpassword=mRPassword.getText().toString();
                addresss=mAddress.getText().toString();
                phone=mPhone.getText().toString();

                if((username.length()<1)||(password.length()<1)||(email.length()<1)||(password.length()<5)||(rpassword.length()<5)||(addresss.length()<1)||(phone.length()<1)) {
                    Toast.makeText(RegisterActivity.this, "cannot be null", Toast.LENGTH_SHORT).show();
                }
                if(password.equals(rpassword)){
                    new CheckRegister().execute();//login start
                }
                else{
                    Toast.makeText(RegisterActivity.this, "password mismatch", Toast.LENGTH_SHORT).show();
                }
            }
        });    }

    class CheckRegister extends AsyncTask<String,String,String>{//auta parameter lune,process garney,result diney
        @Override
        protected void onPreExecute() {//loading bhako ghumako dekhauney
            super.onPreExecute();
            progressDialogue=new ProgressDialog(RegisterActivity.this);
            progressDialogue.setMessage("Loading....");
            progressDialogue.setCancelable(false);
            progressDialogue.show();
        }

        @Override
        protected String doInBackground(String... params) {//background ma data liney diney rocess k k garney lekhney

            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("name",username);
            hashMap.put("email",email);
            hashMap.put("password",password);

            hashMap.put("address",addresss);
            hashMap.put("phone",phone);

            String url="http://sinfoma.com/api/register.php";
            JSONObject jsonObject= jsonparsing.performPostCI(url,hashMap);//hashmap ma name and password and tyo url ma pass garney so return garxa if url bhako thau ma name nd password matches

            if(jsonObject==null){
                status=1;
            }else{
                try{
                    if(jsonObject.getString("status").equals("success")){
                        status=2;
                    }
                    else{
                        status=3;
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {//result ma k dekhauney
            super.onPostExecute(s);
            progressDialogue.dismiss();
            if(status ==1){
                Toast.makeText(RegisterActivity.this, "No internet Connection", Toast.LENGTH_SHORT).show();
            }
            if(status ==2){
                Toast.makeText(RegisterActivity.this, "Register Sucess", Toast.LENGTH_SHORT).show();
            }
            if(status ==3){
                Toast.makeText(RegisterActivity.this, "Register failed", Toast.LENGTH_SHORT).show();
            }


        }
    }

}
