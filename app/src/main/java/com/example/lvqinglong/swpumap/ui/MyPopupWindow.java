package com.example.lvqinglong.swpumap.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lvqinglong.swpumap.R;

/**
 * Created by chebole on 2018/5/22.
 */

public class MyPopupWindow extends PopupWindow {

    private View conentView;
    private Activity context;

    public MyPopupWindow (final Activity context) {
        super(context);
        this.context = context;
        this.initPopupWindow();

    }

    private void initPopupWindow() {
        //使用view来引入布局
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popupwindow_showlocations, null);
        //获取popupwindow的高度与宽度
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w / 2 + 50);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        //布局控件初始化与监听设置
        EditText addressed = (EditText) conentView
                .findViewById(R.id.addresset);
        RadioGroup radioGroup = (RadioGroup) conentView
                .findViewById(R.id.popradio);
        Button  btn_cancel = (Button) conentView.findViewById(R.id.btn_cancel);
        Button  btn_ok =  (Button) conentView.findViewById(R.id.btn_ok);

        
        btn_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 显示popupWindow的方式设置，当然可以有别的方式。
     *一会会列出其他方法
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
        } else {
            this.dismiss();
        }
    }
}
