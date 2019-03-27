package com.example.shoppingapp.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by shoppingapp on 10/5/16.
 */

public class HomeActivity extends AppCompatActivity {
    Button mLoginButton,mRegisterButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        mLoginButton=(Button) findViewById(R.id.HA_login_button);
        mRegisterButton=(Button) findViewById(R.id.HA_register_button);

mLoginButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
        startActivity(intent);
    }
});
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(HomeActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
