package com.example.shoppingapp.onlineshopping.Helper;

/**
 * Created by shoppingapp on 10/6/16.
 * for self reference purpose
 */



    import android.annotation.SuppressLint;
    import android.app.Application;
    import android.content.SharedPreferences;

    public class GlobalState extends Application {

        SharedPreferences checkLogin;
        SharedPreferences.Editor checkLoginEditor;//hamle alwazs login or kei garnu paryo bhanney editor through garxau !!


        public static GlobalState singleton;//singleton is the instance of class global state

        public static final String PREFS_IS_LOGGED_IN = "is logged in"; // to check if user is logged in


        public static final String PREFS_CHECK_LOGIN = "check login";

        @SuppressLint("CommitPrefEdits")
        @Override
        public void onCreate() {
            super.onCreate();

            checkLogin = this.getSharedPreferences(PREFS_CHECK_LOGIN, 0);
            checkLoginEditor = checkLogin.edit();//edit le editor banauncha so that kei kura edit n update garna paunxa and directly linked with shareprefence variables
    //login navaye naya banauna lai editor kholxa or else puranai xa i.e already loged in bhanney tei data lai update garxa
            singleton = this;
        }

        /**
         * @return MySIngleton instance
         */
        public GlobalState getInstance() {
            return singleton;
        }

        //For Check Login

        public void setPrefsCheckLogin(String value, int resultCode){//splash screen ma login bhayo bhnney ata true ra 1 pass huncha
            if(resultCode==1){
                checkLoginEditor.putString(PREFS_CHECK_LOGIN,value).commit();//resultCode==1 means ekchoti login bhai sakeko so direct main activity page ma janey
            }else {
                checkLoginEditor.remove(PREFS_CHECK_LOGIN).commit(); //else not login then login page ma  janxa
            }
        }
        public String getPrefsCheckLogin(){
            return checkLogin.getString(PREFS_CHECK_LOGIN,"");//return garxa splash screen ma true if already logged in
        }


    }

