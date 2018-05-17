package com.example.lvqinglong.swpumap.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lvqinglong.swpumap.R;
import com.example.lvqinglong.swpumap.ui.ClearEditText;

import org.json.JSONException;



/**
 * Created by Amence on 2018/4/28.
 */

public class LoginActivity extends Activity {

    private Button mLoginBut;

    private ClearEditText mPhone;

    private ClearEditText mPwd;

    private TextView mRegisterText;

    private TextView mForgetPwd;

    private TextView mJumpTxt;

    private ImageView mBackImg;

    private String pwdStr;

    private String name;

    boolean isSuccess = false;



    private boolean isCheck = false;
    String userJson;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            isCheck = extras.getBoolean("isCheck");
        }

//        if(login.equals("1")){
//            startActivity(new Intent(this,FlashActivity.class));
//            finish();
//        }
        initLayout();
        doListener();
    }


    private final Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };


    private void doListener() {


        if (isCheck) {
            mLoginBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(LoginActivity.this, "您已经登陆", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mLoginBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String phone = mPhone.getText().toString().trim();
                    if (TextUtils.isEmpty(phone)) {
                        Toast.makeText(LoginActivity.this, "请输入邮箱", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String pwd = mPwd.getText().toString().trim();
                    if (TextUtils.isEmpty(pwd)) {
                        Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        getStatusFromNet();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }


        mForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPwdActivity.class));
            }
        });


        mRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });


        mJumpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();

            }
        });

        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void initLayout() {
        mLoginBut = (Button) findViewById(R.id.btn_login);
        mPhone = (ClearEditText) findViewById(R.id.etxt_phone);
        mPwd = (ClearEditText) findViewById(R.id.etxt_pwd);
        mRegisterText = (TextView) findViewById(R.id.txt_toReg);
        mForgetPwd = (TextView) findViewById(R.id.register_text);
        mJumpTxt = (TextView) findViewById(R.id.login_jump);
        mBackImg = (ImageView) findViewById(R.id.login_back_img);

        if (isCheck) {
            /*mPhone.setText(Util.getString(this, "phoneStr"));
            mPwd.setText(Util.getString(this, "pwdStr"));*/
            mJumpTxt.setVisibility(View.GONE);
            mBackImg.setVisibility(View.VISIBLE);
            mRegisterText.setVisibility(View.GONE);
            mForgetPwd.setVisibility(View.GONE);
        } else {
            mJumpTxt.setVisibility(View.VISIBLE);
            mLoginBut.setClickable(true);
            mBackImg.setVisibility(View.GONE);
        }

    }

    public void getStatusFromNet() throws JSONException {

        name = mPhone.getText().toString();
        pwdStr = mPwd.getText().toString();


/*        Retrofit retrofit = RetrofitUtil.retrofitRequest();

        UserService userService = retrofit.create(UserService.class);
        //创建请求体
        User user = new User(name, pwdStr);

        String lngAndLat = Utils.getLngAndLat(this);
        String[] lngAndLatArry;
        if(lngAndLat!=null){
            lngAndLatArry = lngAndLat.split(",");
            user.setLongitude(Double.parseDouble(lngAndLatArry[0]));
            user.setLatitude(Double.parseDouble(lngAndLatArry[1]));
        }

        Call<BaseResponse> call = userService.login(user);

        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Response<BaseResponse> response, Retrofit retrofit) {

                BaseResponse baseResponse = response.body();

                if (baseResponse.getMeta().isResult()) {
                    userJson = JSON.toJSONString(baseResponse.getData());
                    Util.saveString(LoginActivity.this, Constant.SP_USER_KEY, userJson);
                    User vo = JSON.parseObject(userJson, User.class);

                    if(vo.getUserType()==1){
                        startService(new Intent(LoginActivity.this, DriverServer.class));
                    }else{
                        startService(new Intent(LoginActivity.this, PassengerServer.class));
                    }

                    startService(new Intent(LoginActivity.this, LocalServer.class));
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    LoginActivity.this.finish();
                } else {
                    String message = baseResponse.getMeta().getMsg();
                    Log.v("Amence","login error "+message);

                }
            }

            @Override
            public void onFailure(Throwable t) {
                String message = t.getMessage();

            }

        });*/


    }
}
