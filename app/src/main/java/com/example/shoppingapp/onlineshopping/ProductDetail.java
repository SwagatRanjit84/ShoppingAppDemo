package com.example.shoppingapp.onlineshopping;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.onlineshopping.Pojo.Products;

/**
 * Created by shoppingapp on 10/6/16.
 */

public class ProductDetail extends AppCompatActivity{
TextView mname,mcategory,mprice,mcondition,maddDate,mendDate,maddBy,mnumber,mdescription;
   Button mbuttonCall,mbuttonMessage;//declare gareko jati pani xa product detail ma dekhaunu parney
    Products products;//auta product

ImageView mImage;// image ko lagi
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
    mname=(TextView) findViewById(R.id.product_detail_image_name);
    mcategory=(TextView) findViewById(R.id.product_detail_image_category);
    mprice=(TextView) findViewById(R.id.product_detail_image_price);
    mcondition=(TextView) findViewById(R.id.product_detail_image_condition);
    maddDate=(TextView) findViewById(R.id.product_detail_image_addDate);
    mendDate=(TextView) findViewById(R.id.product_detail_image_endDate);
    maddBy=(TextView) findViewById(R.id.product_detail_image_addBy);
    mnumber=(TextView) findViewById(R.id.product_detail_image_number);
    mdescription=(TextView) findViewById(R.id.product_detail_image_description);
        mImage=(ImageView) findViewById(R.id.product_detail_image_view);
        mbuttonCall=(Button) findViewById(R.id.product_detail_buttonCall);
        mbuttonMessage=(Button) findViewById(R.id.product_detail_buttonMessage);

        products=(Products) getIntent().getSerializableExtra("product_detail");//sab lai character la lirako huncha coz ki string ma pass huncha ki tah character ma not in object so serializable le object lai charater ma change garxa
        String image_url="http://sinfoma.com/api/images/"+products.getId()+"."+products.getImage();//image ko url deko http://sinfoma.com/api/images/1.jpg
        Glide.with(ProductDetail.this).load(image_url).into(mImage);//url lai direct product page ma fig ko rup ma dekhuna sakinaa so url have to be changed into pic by the use of glide
// and displayed in the location of mImage aba baki ko ta sab text ma represent garna sakihalxa
        mname.setText(products.getName());
        mcategory.setText(products.getCategory());
        mprice.setText(products.getPrice());
        mcondition.setText(products.getCondition());
        maddDate.setText(products.getAdd_date());
        mendDate.setText(products.getEnd_date());
        maddBy.setText(products.getAdd_by());
        mnumber.setText(products.getAdd_number());
        mdescription.setText(products.getDescription());

        mbuttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber="tel:9841687734";//+products.getAdd_number();//tel:9779841687734
                Intent callIntent=new Intent(Intent.ACTION_CALL);//intent through aba button thichepaxi call huncha
                callIntent.setData(Uri.parse(phoneNumber));//call garna url ko form ma pass garney mathiko string jasti
              try {
                  startActivity(callIntent);
              }catch(Exception e){
                  e.printStackTrace();
              }
        }});
        //Message
        mbuttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.product_detail);

//                String singleNumbers="9779860302444";//products.getAdd_number();..product owner ko number taneko
//                SmsManager sms= SmsManager.getDefault();//sms pathauna lai default functionalities haru k k chahincha tyo initialize gareko sms variable ma
//                sms.sendTextMessage(singleNumbers,null,"Demo Message",null,null);//text send garnalai singlenumbers ma mathi bata ako number
//                Toast.makeText(ProductDetail.this, "click on button message", Toast.LENGTH_SHORT).show();//toast if button of message is clicked
                String phoneNumber="smsto:9841687734";
                Uri uri=Uri.parse(phoneNumber);
                Intent intent=new Intent(Intent.ACTION_SENDTO,uri);
                intent.putExtra("sms_body","hy hello");
                startActivity(intent);
            }
        });

    }
}
