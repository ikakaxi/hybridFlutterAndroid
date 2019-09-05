package com.liuhc.myapplication

import android.content.Context
import android.os.Bundle
import android.view.View
import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory
import io.flutter.view.FlutterMain

/**
 * 描述:这个页面给Flutter提供AndroidView
 *
 * 作者:liuhc
 * 创建日期：2019-09-04 on 23:30
 */
class InsertAndroidViewFlutterWidget : FlutterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //强烈建议放到Application里初始化,初始化一次即可,放这里只是举个例子
        FlutterMain.startInitialization(this)
        //intent的参数设置必须在super.onCreate之前,因为super.onCreate里会取这些参数
        intent.action = "android.intent.action.RUN"
        intent.putExtra("route", "page5")
        super.onCreate(savedInstanceState)
        //调用super.onCreate(savedInstanceState)之后flutterView才有值,
        //所以如果需要注册插件,则应该放到super.onCreate(savedInstanceState)代码之后才可以
        flutterView.enableTransparentBackground()
        val key = "InsertAndroidViewFlutterWidget"
        if (this.hasPlugin(key)) return
        val registrar = registrarFor(key)
        //核心:通过registerViewFactory方法注册提供View的工厂
        registrar.platformViewRegistry().registerViewFactory(
            "InsertAndroidView",
            ProvideAndroidView(this, StandardMessageCodec())
        )
    }

    class ProvideAndroidView(context: Context, createArgsCodec: MessageCodec<Any>) :
        PlatformViewFactory(createArgsCodec) {
        private val insertView: View = View.inflate(context, R.layout.view_insert_to_flutter, null)

        override fun create(context: Context, i: Int, data: Any?): PlatformView {
            return object : PlatformView {
                override fun getView(): View = insertView

                override fun dispose() {

                }

            }
        }

    }

}