package com.deshpande.camerademo.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deshpande.camerademo.Config;
import com.deshpande.camerademo.R;


import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
public class Delivery_Data extends AppCompatActivity {
    Button done;
    private ProgressDialog loading ;

    EditText name,number,address;
    Bitmap bitmap;
    String image;
    String na,num, add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //Remove notification bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_delivery__data);
        done=(Button)findViewById(R.id.done);
        name=(EditText)findViewById(R.id.name);
        number=(EditText)findViewById(R.id.number);
        address=(EditText)findViewById(R.id.address);
        Intent intent= getIntent();
//        bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARE_PREFRANCE_NAME, Context.MODE_PRIVATE);
        image= sharedPreferences.getString(Config.DIS, null);
//        bitmap=decodeBase64(image);
        done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (name.getText().length()==0) {
                    name.requestFocus();
                    name.setError(Html.fromHtml("<font color='red'>Please Enter Your Name</font>"));
                }else
                if (number.getText().length()==0) {
                    number.requestFocus();
                    number.setError(Html.fromHtml("<font color='red'>Please Enter Your Number</font>"));
                }else
                if (address.getText().length()==0) {
                    address.requestFocus();
                    address.setError(Html.fromHtml("<font color='red'>Please Enter Your Address</font>"));
                }
                else if (v==done)
                {
                Upload_details();
            }
            }
        });
    }

    private void Upload_details()
    {
        na = name.getText().toString();
        num= number.getText().toString();
        add = address.getText().toString();

//        final String ecodedString = ImageBase64.encode(bitmap);
        loading= ProgressDialog.show(this," Uploading_Details","Please Wait...",false,false);
        ////////////////////////////Volley////////////////////////////////////////////////
        StringRequest request = new StringRequest(Request.Method.POST,Config.Upload_Data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                loading.dismiss();

//                Toast.makeText(getApplicationContext(),   response, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Delivery_Data.this, Thank_You.class);
                intent.putExtra("ID",response);
                finish();
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  Log.e("Error",error.printStackTrace());
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Volley Error" + error, Toast.LENGTH_SHORT).show();
//
                ///
                //
                //

            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.Image,image );//ecodedString
                params.put(Config.Name,na);
                params.put(Config.Address,add);
                params.put(Config.Phone,num);

                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
        ///////////////////////////////////////END///////////////////////////
        ////////////////////////ASYCK TASK///////////////////////////////////////

    }
//
//    public static Bitmap decodeBase64(String input)
//    {
//        byte[] decodedByte = Base64.decode(input, 0);
//        return BitmapFactory
//                .decodeByteArray(decodedByte, 0, decodedByte.length);
//    }
private String imageToStrin(Bitmap bitmap)
{
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
    byte[] imgBytes=byteArrayOutputStream.toByteArray();
    return Base64.encodeToString(imgBytes, Base64.DEFAULT);
}
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Delivery_Data.this,MainActivity.class);
        finish();
        startActivity(intent);
    }
}


