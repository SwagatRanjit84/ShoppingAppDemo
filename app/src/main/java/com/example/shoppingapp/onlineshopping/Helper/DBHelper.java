package com.example.shoppingapp.onlineshopping.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shoppingapp.onlineshopping.FeedbackFormActivity;
import com.example.shoppingapp.onlineshopping.Pojo.FeedbackObject;

/**
 * Created by shoppingapp on 10/7/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="feed";
    public static final int DATABASE_VERSION=1;

    public static  final String FEEDBACK_TABLE="feedback";
    public static  final String FEED_KEY_ID="id";
    public static  final String FEED_KEY_NAME="name";
    public static  final String FEED_KEY_EMAIL="email";
    public static  final String FEED_KEY_PHONE="phone";
    public static  final String FEED_KEY_MESSAGE="message";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+FEEDBACK_TABLE+"("+
                FEED_KEY_ID+" INTEGER PRIMARY KEY,"+
                FEED_KEY_NAME+" TEXT,"+
                FEED_KEY_EMAIL+" TEXT,"+
                FEED_KEY_PHONE+" TEXT,"+
                FEED_KEY_MESSAGE+" TEXT)";
        db.execSQL(query);





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addFeeback(FeedbackObject feedback){
        SQLiteDatabase db=this.getWritableDatabase();//to make database writable

        ContentValues values=new ContentValues();
        values.put(FEED_KEY_NAME,feedback.getName());
        values.put(FEED_KEY_EMAIL,feedback.getEmail());
        values.put(FEED_KEY_PHONE,feedback.getPhone());
        values.put(FEED_KEY_MESSAGE,feedback.getMessage());

        db.insert(FEEDBACK_TABLE,null,values);
        db.close();


    }
public int getMyFeedbackCount(){
    String countQuery="SELECT * FROM "+FEEDBACK_TABLE;
    SQLiteDatabase db=this.getReadableDatabase();
    Cursor cursor=db.rawQuery(countQuery,null);
    return cursor.getCount();



}

}
