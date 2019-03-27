package com.example.shoppingapp.onlineshopping;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingapp.onlineshopping.Helper.GlobalState;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by shoppingapp on 10/4/16.
 */

public class LoginActivity extends AppCompatActivity {
EditText mUsername,mPassword;
    String username,password;
    Button mLogin;
    GlobalState globalState;
    ProgressDialog progressDialogue;

    int status=0;
    JsonParsing jsonparsing=new JsonParsing();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mUsername=(EditText)  findViewById(R.id.login_activity_et_name);
        mPassword=(EditText)  findViewById(R.id.login_activity_et_password);
        mLogin=(Button) findViewById(R.id.login_activity_et_button);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 username=mUsername.getText().toString();
                  password=mPassword.getText().toString();


                if((username.length()<1)||password.length()<1) {
                    Toast.makeText(LoginActivity.this, "user or password cannot be null", Toast.LENGTH_SHORT).show();
                }else{
                    new CheckLogin().execute();//login start
                }
            }
        });
    finish();
    }

    class CheckLogin extends AsyncTask<String,String,String>{//auta parameter lune,process garney,result diney
        @Override
        protected void onPreExecute() {//loading bhako ghumako dekhauney
            super.onPreExecute();
            progressDialogue=new ProgressDialog(LoginActivity.this);
            progressDialogue.setMessage("Loading....");
            progressDialogue.setCancelable(false);
            progressDialogue.show();
        }

        @Override
        protected String doInBackground(String... params) {//background ma data liney diney rocess k k garney lekhney

            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("username",username);
    hashMap.put("password",password);

            String url="http://sinfoma.com/api/login.php";
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
                Toast.makeText(LoginActivity.this, "No internet Connection", Toast.LENGTH_SHORT).show();
            }
            if(status ==2){

                Toast.makeText(LoginActivity.this, "Login Sucess", Toast.LENGTH_SHORT).show();
                globalState=GlobalState.singleton;
                globalState.setPrefsCheckLogin("true",1);//ekchoti store bhyobhanney alwaz ko lagi yo app kholda yo username ra password save nai bhai rahanxa
                    Intent i=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);

            }
            if(status ==3){
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }


        }
    }

}
