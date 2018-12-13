package com.example.sitting.myapplicationmixx;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sitting.myapplicationmixx.API.AsyncComplete;
import com.example.sitting.myapplicationmixx.API.AsyncResult;
import com.example.sitting.myapplicationmixx.API.UserConnect;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;




public class Main2Activity extends AppCompatActivity  {

    public TextView result;
    public TextView reGender;
    public ListView listData;
    public EditText editName;
    public EditText editCode;
    private EditText editCheckcode;
    public EditText editTel;
    public EditText editEmail;
    public EditText editAccount;
    private Button btnAdd;
    String URL= "https://lineup.susautw.nctu.me/test_android2/index.php";

    JSONParser jsonParser=new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViews();

        //開啟資料庫

//       showInList();    //show listview


        radioGroup = (RadioGroup) this.findViewById(R.id.radio_group);
        textView = (TextView) this.findViewById(R.id.select_result);
        textView.setText(getSelectedInfo());
        btnAdd=(Button)findViewById(R.id.btnAdd);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                textView.setText(getSelectedInfo(checkedId));

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.btnAdd:
                        int nameLength = editName.getText().toString().length();
                        if (nameLength < 2 || nameLength > 5) {

                            Toast.makeText(Main2Activity.this, "用戶名需介於2~5個字", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        int accountLength = editAccount.getText().toString().length();
                        if (accountLength < 2 || accountLength > 10) {
                            Toast.makeText(Main2Activity.this, "帳號需介於2~10個字", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        int codeLength = editCode.getText().toString().length();
                        if (codeLength < 2 || codeLength > 10) {
                            Toast.makeText(Main2Activity.this, "密碼需介於2~10個字", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        String codeA = (editCode.getText().toString());
                        String codeB = (editCheckcode.getText().toString());
                        if (codeA.equals(codeB)) {
                            editCheckcode.setText("");
                        } else {
                            Toast.makeText(Main2Activity.this, "與密碼不符", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        int telLength = editTel.getText().toString().length();
                        if (telLength != 10) {
                            Toast.makeText(Main2Activity.this, "手機格式錯誤", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        String email = (editEmail.getText().toString());
                        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                        if (!email.matches(emailRegex)) {
                            Toast.makeText(Main2Activity.this, "email不符合格式", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        break;
//限制條件
                    default:
                        cleanEditText();
                        break;
                }
                AttemptLogin attemptLogin = new AttemptLogin();
                attemptLogin.execute(editAccount.getText().toString(), editCode.getText().toString(), editEmail.getText().toString(), editTel.getText().toString(), textView.getText().toString(), editName.getText().toString());


            }
        });
    }


    private RadioGroup radioGroup = null;
    private TextView textView = null;

    private String getSelectedInfo() {
        int checkedId = radioGroup.getCheckedRadioButtonId();
        return getSelectedInfo(checkedId);
    }

    private String getSelectedInfo(int checkedId) {
        String selected;

        switch (checkedId) {
            case R.id.radio_male:
                selected = "male";
                break;
            case R.id.radio_female:
                selected = "female";
                break;
            default:
                selected = "male";
                break;

        }

        return selected;
    }


    private void findViews() {

        editName = (EditText) findViewById(R.id.editName);
        reGender = (TextView) this.findViewById(R.id.select_result);
        editAccount = (EditText) findViewById(R.id.editAccount);
        editCode = (EditText) findViewById(R.id.editCode);
        editCheckcode = (EditText) findViewById(R.id.editCheckcode);
        editTel = (EditText) this.findViewById(R.id.editTel);
        editEmail = (EditText) findViewById(R.id.editEmail);
//        editId = (EditText) findViewById(R.id.editID);
        btnAdd = (Button) findViewById(R.id.btnAdd);
//        result=(TextView)findViewById(R.id.txtResult);
//        listData=(ListView)findViewById(R.id.listData);

    }



    private void cleanEditText() {
        editName.setText("");
        reGender.setText("");
        editAccount.setText("");
        editCode.setText("");
        editTel.setText("");
        editEmail.setText("");
    }

    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }
        protected JSONObject doInBackground(String... args) {



            String email = args[2];
            String password = args[1];
            String username= args[0];
            String tel=args[3];
            String selected=args[4];
            String name=args[5];
            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));

            if(email.length()>0)
                params.add(new BasicNameValuePair("email",email));
            params.add(new BasicNameValuePair("tel",tel));
            params.add(new BasicNameValuePair("selected",selected));
            params.add(new BasicNameValuePair("name",name));
            System.out.println(params);
            try{
                return jsonParser.makeHttpRequest(URL, "POST", params);
            }catch (JSONException e){
                try {
                    return new JSONObject("{\"status\":-1}");
                }catch (Exception ej){}
            }

            return null;

        }

        @Override
        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    int success = result.getInt("success");
                    if(success == 1){
                        UserConnect.setAuthData(editAccount.getText().toString(),editCode.getText().toString());

                        UserConnect.getUser(new AsyncComplete() {
                            @Override
                            public void success(AsyncResult completeObject) {
                                Intent intent = new Intent();
                                intent.setClass(Main2Activity.this, Main3Activity.class);
                                startActivity(intent);
                                Main2Activity.this.finish();
                            }

                            @Override
                            public void failed(AsyncResult completeObject) {
                                Log.e("API TEST login","failed");
                            }
                        });

                    }else{
                        Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}