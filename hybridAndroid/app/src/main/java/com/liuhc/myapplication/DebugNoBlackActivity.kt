package com.liuhc.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import io.flutter.facade.Flutter
import org.jetbrains.anko.find

/**
 * 描述:在debug模式下,直接跳转到使用FlutterView的Activity的时候会有黑屏,
 * 如果想处理这种情况可以直接调用flutterView.enableTransparentBackground(),参考Page1Activity
 * 这里提供另一种解决方式
 *
 * 但是没什么必要,因为release模式下是没有这种问题的
 *
 * 这里要注意隐藏显示window.decorView是不可以的,必须隐藏显示R.layout.xxx里将要包含FlutterView的ViewGroup
 *
 * 作者:liuhc
 * 创建日期：2019-09-03
 */
class DebugNoBlackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //先调用setContentView设置一个layout
        setContentView(R.layout.activity_debug_no_black)
        //Activity嵌入FlutterView的方式1
        val flutterView = Flutter.createView(
            this,
            lifecycle,
            "page1"
        )
        val flutterLayoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        flutterLayoutParams.leftMargin = 0
        flutterLayoutParams.topMargin = 0
        //1.取出setContentView里将要添加FlutterView的ViewGroup
        val flutterViewViewGroup = find<ViewGroup>(R.id.flutterViewViewGroup)
        val root = find<ViewGroup>(R.id.root)
        val tipTextView = find<View>(R.id.tipTextView)
        //2.添加FlutterView
        flutterViewViewGroup.addView(flutterView, flutterLayoutParams)
        //3.在xml或者调用代码隐藏包含FlutterView的ViewGroup,因为我在xml隐藏了所以下面的方法就不需要调用了
        //flutterViewViewGroup.visibility = View.INVISIBLE
        //4.当FlutterView绘制了第一帧以后再显示,就解决了debug模式下的黑屏问题
        flutterView.addFirstFrameListener {
            //删除过渡TextView
            root.removeView(tipTextView)
            flutterViewViewGroup.visibility = View.VISIBLE
        }
    }
}
