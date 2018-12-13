package com.example.sitting.myapplicationmixx;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.example.sitting.myapplicationmixx.API.*;
import com.example.sitting.myapplicationmixx.Entity.ArrayEntity;
import com.example.sitting.myapplicationmixx.Entity.Auth;
import com.example.sitting.myapplicationmixx.Entity.Store;
import com.example.sitting.myapplicationmixx.Entity.User;


public class MainActivity extends AppCompatActivity {

    private EditText loginaccount;
    private EditText loginpassword;
    private Button btnLogin;
    private Button btnSignup;
    String URL= "https://lineup.susautw.nctu.me /test_android2/index.php";

    JSONParser jsonParser=new JSONParser();

    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        loginaccount = (EditText) findViewById(R.id.loginaccount);
        loginpassword = (EditText) findViewById(R.id.loginpassword);
        btnSignup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                UserConnect.setAuthData(loginaccount.getText().toString(),loginaccount.getText().toString());

                UserConnect.getUser(new AsyncComplete() {
                    @Override
                    public void success(AsyncResult completeObject) {
                        Intent intent = new Intent();
                        intent.putExtra("login",true);
                        intent.setClass(MainActivity.this, Main3Activity.class);
                        startActivity(intent);
                        MainActivity.this.finish();
                    }

                    @Override
                    public void failed(AsyncResult completeObject) {
                        Log.e("API TEST login","failed");
                    }
                });




//                Auth.getToken(loginaccount.getText().toString(),loginpassword.getText().toString(), new AsyncComplete(){
//
//                    @Override
//                    public void success(AsyncResult completeObject) {
//
//                    }
//
//                    @Override
//                    public void failed(AsyncResult completeObject) {
//                        Log.e("API TEST2","failed");
//                    }
//                });


                //                check();
            }
        });

    }


    private void cleanEditText() {
        loginaccount.setText("");
        loginpassword.setText("");

    }

//    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {
//
//        @Override
//
//        protected void onPreExecute() {
//
//            super.onPreExecute();
//
//        }
//
//        @Override
//
//        protected JSONObject doInBackground(String... args) {
//
//
//            String email = args[2];
//            String password = args[1];
//            String name = args[0];
//
//            ArrayList params = new ArrayList();
//            params.add(new BasicNameValuePair("username", name));
//            params.add(new BasicNameValuePair("password", password));
//            if (email.length() > 0)
//                params.add(new BasicNameValuePair("email", email));
//
//            try{
//                return jsonParser.makeHttpRequest(URL, "POST", params);
//            }catch (JSONException e){
//                try {
//                    return new JSONObject("{\"status\":-1}");
//                }catch (Exception ej){}
//            }
//
//            return null;
//        }
//
//        protected void onPostExecute(JSONObject result) {
//
//             dismiss the dialog once product deleted
//            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
//
//            try {
//                if (result != null) {
//                    int success = result.getInt("success");
//                    if (success == 1) {
//                        Intent intent = new Intent();
//                        intent.setClass(MainActivity.this, Main3Activity.class);
//                        startActivity(intent);
//                        MainActivity.this.finish();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
//                    }
//                }
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//   }
}
