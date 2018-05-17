package com.example.lvqinglong.swpumap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lvqinglong.swpumap.R;
import com.example.lvqinglong.swpumap.entity.User;
import com.example.lvqinglong.swpumap.ui.ClearEditText;


public class ForgetPwdActivity extends AppCompatActivity {
    private ImageView mBackImg;

    private ClearEditText mPhoneTxt;

    private ClearEditText mPwdTxt;

    private ClearEditText mPwdAgainTxt;

    private ClearEditText mName;

    private Button mLoginBut;

    private User    cacheUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);

        initLayout();
        doListener();
    }



    private void initLayout() {
        mBackImg = (ImageView) findViewById(R.id.forget_back_img);
        mPhoneTxt = (ClearEditText) findViewById(R.id.forget_etxt_phone);
        mName = (ClearEditText) findViewById(R.id.forget_etxt_name);
        mPwdTxt = (ClearEditText) findViewById(R.id.forget_etxt_pwd);
        mPwdAgainTxt= (ClearEditText) findViewById(R.id.forget_etxt_pwd_again);
        mLoginBut = (Button) findViewById(R.id.forget_btn_login);
        cacheUser  = getUserCache();
    }

    private User getUserCache() { //todo 获取用户

        return null;
    }

    private void doListener() {

        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mLoginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mPhoneTxt.getText().toString();

                String name = mName.getText().toString();

                String pwdStr = mPwdTxt.getText().toString();

                String pwdStrAgain = mPwdAgainTxt.getText().toString();


                if(pwdStr.trim().equals(pwdStrAgain.trim())){
        /*            Retrofit retrofit = RetrofitUtil.retrofitRequest();

                    UserService userService = retrofit.create(UserService.class);
                    //创建请求体
                    User user = new User(name, pwdStr,email);
                    Call<BaseResponse> call = userService.returnBack(user);

                    call.enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Response<BaseResponse> response, Retrofit retrofit) {

                            BaseResponse baseResponse = response.body();

                            if(baseResponse.getMeta().isResult()){
                                startActivity(new Intent(ForgetPwdActivity.this, LoginActivity.class));
                                ForgetPwdActivity.this.finish();
                            }else{
                                String message =  baseResponse.getMeta().getMsg();

                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            String message = t.getMessage();

                        }

                    });
*/
                }else {
                    Toast.makeText(ForgetPwdActivity.this,"两次密码不一致", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
