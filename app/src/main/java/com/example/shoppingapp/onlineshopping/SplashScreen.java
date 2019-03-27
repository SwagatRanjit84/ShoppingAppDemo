package com.example.shoppingapp.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.onlineshopping.Helper.GlobalState;
/**
 * Created by shoppingapp on 10/6/16
 */

public class SplashScreen extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);//splash_screen layout lai link gareko

        final ImageView mLoader=(ImageView) findViewById(R.id.splash_screen_image_view);//yo auta imageView ho
        Glide.with(SplashScreen.this).load(R.drawable.loader_2).into(mLoader);//splash ma image aundaina atikai so we have to use glide to load loader_2 in mLoader
        new Handler().postDelayed(new Runnable() {//pailai queue ma xa bhanney false // else return true if first time successful bhayo bhanney
            // Returns true if the Runnable was successfully placed in to the
//            *         message queue.  Returns false on failure, usually because the
//            *         looper processing the message queue is exiting.  Note that a
//            *         result of true does not mean the Runnable will be processed --
//                    *         if the looper is quit before the delivery time of the message
//            *         occurs then the message will be dropped.
            @Override
            public void run() {
                GlobalState globalState=GlobalState.singleton;//globalstate foe session check
                String checkin=globalState.getPrefsCheckLogin();//pailai login bhaye bhaye true auney or else anyother
                if(checkin.equals("true")){//if true ayo bhanney direct Mainactivity kholney
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));//aile splashscreen paxi mainactivity

                }
                else{//else if previously login chaina bhanney loginActivity ma
                    startActivity(new Intent(SplashScreen.this,LoginActivity.class));

                }




                finish();//finish feri na aos bhannako lagi yo splash page ...ie.splash page open hunda ekchoti matra aunu paro not paxi
            }
        },3000);// 3000ms=3s samma splash screen ma basiranxa and then bhitra pasxa
        {

        }
    }
}
