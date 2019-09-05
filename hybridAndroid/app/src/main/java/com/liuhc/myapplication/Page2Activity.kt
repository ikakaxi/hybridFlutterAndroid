package com.liuhc.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.flutter.facade.Flutter

/**
 * 描述:普通Fragment方式添加FlutterView
 * 作者:liuhc
 * 创建日期：2019-09-05 on 11:21 AM
 */
class Page2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)
        //Activity嵌入Flutter View的方式2
        val tx = supportFragmentManager.beginTransaction()
        //下面2种方式都可以,只不过方式2代码里调用了flutterView.enableTransparentBackground(),所以在debug模式下不会闪烁黑屏
        //方式1
        tx.replace(R.id.frameLayout, Flutter.createFragment("page2"))
        //方式2
        tx.replace(R.id.frameLayout, Page2Fragment())
        tx.commit()
    }

    class Page2Fragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val flutterView = Flutter.createView(activity!!, lifecycle, "page2")
            //这句话的作用是在debug模式下去除启动Flutter页面的时候的黑屏
            flutterView.enableTransparentBackground()
            return flutterView
        }
    }
}
