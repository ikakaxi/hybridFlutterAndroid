package com.liuhc.myapplication

import android.os.Bundle
import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MethodChannel
import io.flutter.view.FlutterMain
import org.jetbrains.anko.startActivity

/**
 * 描述:这个页面包含FlutterView,然后点击FlutterView中的按钮跳转到另一个Activity,
 * 这种方式其实只是调用Channel通信而已
 *
 * 作者:liuhc
 * 创建日期：2019-09-04 on 23:30
 */
class JumpActivityFlutterWidget : FlutterActivity() {

    private lateinit var methodChannel: MethodChannel

    override fun onCreate(savedInstanceState: Bundle?) {
        //强烈建议放到Application里初始化,初始化一次即可,放这里只是举个例子
        FlutterMain.startInitialization(this)
        //intent的参数设置必须在super.onCreate之前,因为super.onCreate里会取这些参数
        intent.action = "android.intent.action.RUN"
        intent.putExtra("route", "page4")
        super.onCreate(savedInstanceState)
        initMethodChannel()
        //调用super.onCreate(savedInstanceState)之后flutterView才有值,
        //所以如果需要注册插件,则应该放到super.onCreate(savedInstanceState)代码之后才可以
        flutterView.enableTransparentBackground()
    }

    //Flutter跳转Activity的方式1,使用Channel
    private fun initMethodChannel() {
        methodChannel = MethodChannel(
            this.registrarFor("pluginKeyMainActivity").messenger(),
            "MainActivityMethodChannel"
        )
        methodChannel.setMethodCallHandler { methodCall, result ->
            if (methodCall.method == "jumpTestActivity") {
                startActivity<TestActivity>()
            }
        }
    }
}