package com.example.lvqinglong.swpumap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.lvqinglong.swpumap.R;


/**
 * Created by Amence on 2018/4/28.
 */

public class FlashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);


        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(FlashActivity.this, LoginActivity.class);
                startActivity(intent);
                FlashActivity.this.finish();

            }
        }, 2000);


        handlerTread();

    }

    /**
     * 做一些耗时操作
     */
    private void handlerTread() {


    }


}
