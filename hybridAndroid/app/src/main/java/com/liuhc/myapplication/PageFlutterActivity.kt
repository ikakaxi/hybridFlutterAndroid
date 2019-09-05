package com.liuhc.myapplication

import android.os.Bundle
import android.util.Log
import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MethodChannel
import io.flutter.view.FlutterMain
import org.json.JSONObject

/**
 * 描述:
 * 作者:liuhc
 * 创建日期：2019-09-04 on 23:30
 */
class PageFlutterActivity : FlutterActivity() {

    private lateinit var methodChannel: MethodChannel

    override fun onCreate(savedInstanceState: Bundle?) {
        //强烈建议放到Application里初始化,初始化一次即可,放这里只是举个例子
        FlutterMain.startInitialization(this)
        //intent的参数设置必须在super.onCreate之前,因为super.onCreate里会取这些参数
        intent.action = "android.intent.action.RUN"
        val channelName = "channelName_PageFlutterActivity"
        val androidMethod = "methodName_PageFlutterActivity"
        val jsonObject = JSONObject()
        jsonObject.put("path", "InvokeMethodPage")
        jsonObject.put("title", "PageFlutterActivity")
        jsonObject.put("channelName", channelName)
        jsonObject.put("androidMethod", androidMethod)
        intent.putExtra("route", jsonObject.toString())
        super.onCreate(savedInstanceState)
        //调用super.onCreate(savedInstanceState)之后flutterView才有值,
        //所以如果需要注册插件,则应该放到super.onCreate(savedInstanceState)代码之后才可以
        flutterView.enableTransparentBackground()
        //如果不需要平台交互的话,只需要上面的代码并在最后加上super.onCreate就可以了
        //这里this.registrarFor方法实际调用的是FlutterFragmentActivity里的delegate的registrarFor方法,
        //而delegate的registrarFor方法实际调用的是FlutterActivityDelegate里的flutterView.getPluginRegistry().registrarFor方法,
        //而FlutterActivityDelegate里的flutterView在调用了这里的super.onCreate(savedInstanceState)才有值,
        //所以如果需要注册插件,则应该放到super.onCreate(savedInstanceState)代码之后才可以
        val key = "PageFlutterActivity"
        if (this.hasPlugin(key)) return
        val registrar = this.registrarFor(key)
        methodChannel = MethodChannel(
            registrar.messenger(),
            channelName
        )
        methodChannel.setMethodCallHandler { methodCall, result ->
            if (methodCall.method == androidMethod) {
                Log.e("Android", "接收到了Flutter传递的参数:${methodCall.arguments}")
                result.success("$androidMethod ok")
                Log.e("Android", "主动调用Flutter的methodInvokeMethodPageState方法")
                methodChannel.invokeMethod("methodInvokeMethodPageState", "Android发送给Flutter的参数")
            }
        }
    }

}