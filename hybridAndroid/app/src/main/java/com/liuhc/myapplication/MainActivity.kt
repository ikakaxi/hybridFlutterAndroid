package com.liuhc.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.flutter.view.FlutterMain
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

/**
 * 描述:首页
 * 作者:liuhc
 * 创建日期：2019-09-05 on 11:21 AM
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //强烈建议放到Application里初始化,初始化一次即可,放这里只是举个例子
        FlutterMain.startInitialization(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //addContentView方式添加FlutterView
        page1.setOnClickListener {
            startActivity<Page1Activity>()
        }

        //普通Fragment方式添加FlutterView
        page2.setOnClickListener {
            startActivity<Page2Activity>()
        }

        //使用FlutterFragmentActivity
        page3.setOnClickListener {
            startActivity<PageFlutterFragmentActivity>()
        }

        //使用FlutterActivity
        page4.setOnClickListener {
            startActivity<PageFlutterActivity>()
        }

        //addContentView方式添加FlutterView并传递参数
        page1Param.setOnClickListener {
            startActivity<Page1ParamActivity>()
        }

        //解决debug模式下黑屏的另一种方式
        noBlack.setOnClickListener {
            startActivity<DebugNoBlackActivity>()
        }

        //进入Flutter页面演示通过Channel跳转到Activity
        jumpByChannel.setOnClickListener {
            startActivity<JumpActivityFlutterWidget>()
        }

        //进入嵌入了Android平台的View的Flutter页面
        insertAndroidView.setOnClickListener {
            startActivity<InsertAndroidViewFlutterWidget>()
        }
    }

}
