package com.example.shoppingapp.onlineshopping;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingapp.onlineshopping.Helper.DBHelper;
import com.example.shoppingapp.onlineshopping.Pojo.FeedbackObject;

/**
 * Created by shoppingapp on 10/7/16.
 */

public class FeedbackFormActivity extends AppCompatActivity{
    EditText mName,mMessage,mPhone,mEmail;
    Button mSend;
    String name,email,phone,message;
    DBHelper dbHelper;
    FeedbackObject feedback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_inquiry);

        dbHelper=new DBHelper(this);
        int count=dbHelper.getMyFeedbackCount();
        Toast.makeText(this, "Total "+ String.valueOf(count), Toast.LENGTH_SHORT).show();


        mName=(EditText) findViewById(R.id.aci_et_name);
        mEmail=(EditText) findViewById(R.id.aci_et_email);
        mPhone=(EditText) findViewById(R.id.aci_et_phone);
        mMessage=(EditText) findViewById(R.id.aci_et_message);
        mSend=(Button) findViewById(R.id.aci_btn_send);


        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=mEmail.getText().toString();
                email=mEmail.getText().toString();
                phone=mPhone.getText().toString();
                message=mMessage.getText().toString();


                feedback=new FeedbackObject(name,email,phone,message);
                dbHelper.addFeeback(feedback);
                Toast.makeText(FeedbackFormActivity.this, "Feedback Insert", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
