package com.liuhc.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.FrameLayout
import io.flutter.facade.Flutter

/**
 * 描述:addContentView方式添加FlutterView
 * 作者:liuhc
 * 创建日期：2019-09-05 on 11:21 AM
 */
class Page1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //下面这个setContentView添加不添加的都没关系
        //如果添加了,在debug模式下FlutterView显示有点慢所以会先显示activity_page1再显示FlutterView
        //而release模式下速度会很快,看不到activity_page1,会直接显示FlutterView
        setContentView(R.layout.activity_page1)
        //Activity嵌入FlutterView的方式1
        val flutterView = Flutter.createView(
            this,
            lifecycle,
            "page1"
        )
        //这句话的作用是在debug模式下去除启动Flutter页面的时候的黑屏
        flutterView.enableTransparentBackground()
        val flutterLayoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        flutterLayoutParams.leftMargin = 0
        //将下面的值设置为100就可以在FlutterView加载完以后仍然看到TextView
        flutterLayoutParams.topMargin = 0
        //因为flutterLayoutParams是占满全屏,所以调用addContentView会将flutterView放到R.layout.activity_page1上层
        //效果看起来就是flutterView替换了R.layout.activity_page1
        addContentView(flutterView, flutterLayoutParams)
        //但是如果上面直接使用setContentView就看不到过渡效果了,也就是看不到activity_page1也就看不到TextView显示的文字了
    }
}
