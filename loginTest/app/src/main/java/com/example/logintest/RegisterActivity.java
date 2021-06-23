package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id, et_pw, et_name, et_age;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Register");

        et_id = findViewById(R.id.et_regId);
        et_pw = findViewById(R.id.et_regPw);
        et_name = findViewById(R.id.et_regName);
        et_age = findViewById(R.id.et_regAge);

        btn_register = findViewById(R.id.btn_regRegister);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();
                String userPw = et_pw.getText().toString();
                String userName = et_name.getText().toString();
                int userAge = Integer.parseInt(et_age.getText().toString());

                //json object 활용하여 실제 회원가입 요청
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 회원가입 요청 후, 결과값을 json object로 받음
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            //register.php   response["success"]
                            boolean success = jsonObject.getBoolean("success");
                            if(success){ // 회원가입 성공
                                Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                                startActivity(intent);
                                finish();
                            }else{ // 회원가입 실패
                                Toast.makeText(getApplicationContext(), "Register Failed", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }; // responseListener


                // 서버로 Volley를 이용하여 요청
                RegisterRequest registerRequest = new RegisterRequest(userID, userPw, userName, userAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            } // onClick
        }); // setOnClickListener

    }
}