package com.example.baseactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    GestureDetector  detector;
    @SuppressLint({"ClickableViewAccessibility", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         webView=findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.baidu.com/");

        //②
        detector = new GestureDetector(this, onGestureListener);
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
            
        });}
    //②
    private CustomGestureDetectorListener onGestureListener = new CustomGestureDetectorListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();
            judgeSwiperDirection(x,y);
            return true;
        }
    };

    /**
     * 判断左滑右滑
     * @param xAxis X轴滑动距离
     * @param yAxis Y轴滑动距离
     */
    private void judgeSwiperDirection (float xAxis, float yAxis) {

        boolean isYInControlled = Math.abs(yAxis) <= 100;

        if (isYInControlled) {
            if (xAxis > 25) {
                Log.i("TAG", "右滑触发");
                if (null != webView && webView.canGoBack()/*canGoForward()*/) {
                    // webView.goForward();
                    webView.goBack();
                }
            } else if (xAxis < -25) {
                Log.i("TAG", "左滑触发");
                if (null != webView && webView./*canGoBack()*/canGoForward()) {
                    webView.goForward();
                    //webView.goBack();
                } else {
                    Log.i("TAG", "手势返回到主页面");
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    this.finish();
                }
            }
        }
    }

      //①
       /* gestureDetector = new GestureDetector(new MyGestureListener());
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                webView.onTouchEvent(event);
                return true;
            }
        });}*/


  //①
  /*  class MyGestureListener  extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e2.getX() -e1.getX() >100 && Math.abs(e2.getY() -e1.getY()) <100) {
                if(webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }*/
}
