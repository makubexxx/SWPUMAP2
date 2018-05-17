package com.example.lvqinglong.swpumap.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lvqinglong.swpumap.R;
import com.example.lvqinglong.swpumap.ui.ClearEditText;
import com.example.lvqinglong.swpumap.ui.MyCheckBox;


public class RegisterActivity extends Activity {

    private ImageView mBackImg;

    private ClearEditText mPhoneTxt;

    private ClearEditText mPwdTxt;

    private ClearEditText mEmail;

    private Button mLoginBut;

    private MyCheckBox myCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initLayout();
        doListener();


    }


    private void initLayout() {
        mBackImg = (ImageView) findViewById(R.id.register_back_img);
        mPhoneTxt = (ClearEditText) findViewById(R.id.register_edittxt_phone);
        mPwdTxt = (ClearEditText) findViewById(R.id.register_edittxt_pwd);
        mEmail = (ClearEditText) findViewById(R.id.register_edittxt_email);
        mLoginBut = (Button) findViewById(R.id.register_btn_login);
        myCheckBox = (MyCheckBox) findViewById(R.id.myCheckBox);

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
                String name = mPhoneTxt.getText().toString();
                String pwdStr = mPwdTxt.getText().toString();
                String email = mEmail.getText().toString();
                boolean checked = myCheckBox.isChecked();


            }
        });

    }
}
