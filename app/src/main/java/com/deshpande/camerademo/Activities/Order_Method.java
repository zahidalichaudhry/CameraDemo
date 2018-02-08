package com.deshpande.camerademo.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
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

public class Order_Method extends AppCompatActivity {
    ImageView home,pick;
    private ProgressDialog loading;
    Bitmap bitmap;
    String image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__method);
        home=(ImageView)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Order_Method.this,Delivery_Data.class);
//                intent.putExtra("BitmapImage", bitmap);
                startActivity(intent);
            }
        });
        pick=(ImageView)findViewById(R.id.pic);
//        Intent intent=getIntent();
//        bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARE_PREFRANCE_NAME, Context.MODE_PRIVATE);
        image = sharedPreferences.getString(Config.DIS, null);
        bitmap= decodeBase64(image);

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadPeronly();
//                Intent intent = new Intent(Order_Method.this,Adress.class);
//                startActivity(intent);
            }
        });
    }

    private void UploadPeronly()
    {
//        final String ecodedString = ImageBase64.encode(bitmap);

            loading = ProgressDialog.show(this,"Uploading Prescription...","Please Wait...",false,false);
            ////////////////////////////////VoLLEY//////////////////////////////////
            StringRequest request = new StringRequest(Request.Method.POST,Config.Upload_piconly, new Response.Listener<String>() {
                @Override
                public void onResponse(String response)
                {
                    loading.dismiss();
                    Intent intent = new Intent(Order_Method.this, Adress.class);
                    intent.putExtra("ID",response);
                    startActivity(intent);
//                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //  Log.e("Error",error.printStackTrace());
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Volley Error" + error, Toast.LENGTH_SHORT).show();

                }
            }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(Config.Image,imageToStrin(bitmap));
                    return params;
                }
            };
            request.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
            //////////////////////////////////END/////////////////////////////
        /////////////////////////ASYNCK TASK//////////////////
        //Now will create a StringRequest almost the same as we did before

//        class UpdateEmployee extends AsyncTask<Void, Void, String>
//        {
//            ProgressDialog loading;
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(Order_Method.this,"Uploading Prescription...","Please Wait...",false,false);
//            }
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//                Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            protected String doInBackground(Void... params) {
//                HashMap<String, String> hashMap = new HashMap<>();
//                hashMap.put(Config.Image,imageToStrin(bitmap));
//
//                RequestHandler rh = new RequestHandler();
//                String s = rh.sendPostRequest(Config.Upload_piconly, hashMap);
//                return s;
//            }
//        }
//        UpdateEmployee ue = new UpdateEmployee();
//        ue.execute();
////////////////////////////////////////////END....////////////////////////////////////
    }
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
//
    private String imageToStrin(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
}
