import 'dart:convert';
import 'dart:ui';

import 'package:flutter/material.dart';

import 'insert_android_view_page.dart';
import 'invoke_method_page.dart';
import 'jump_activity_page.dart';
import 'my_home_page.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      //方式1
      home: _getWidgetByRoute(window.defaultRouteName),
      //方式2 如果route相同,优先匹配routes而不是home
      routes: <String, WidgetBuilder>{
        "page1": (context) => MyHomePage(
              title: "匹配到了page1",
              message: "通过routes变量",
            ),
        "page2": (context) => MyHomePage(
              title: "匹配到了page2",
              message: "通过routes变量",
            ),
        "page3": (context) => MyHomePage(
              title: "匹配到了page3",
              message: "通过routes变量",
            ),
        "page4": (context) => JumpActivityPage(),
        "page5": (context) => InsertAndroidViewPage(),
      },
      //当通过routes和home的返回值都为null的话,才会从onUnknownRoute寻找
      onUnknownRoute: (RouteSettings settings) {
        return new PageRouteBuilder(pageBuilder: (BuildContext context, _, __) {
          //这里为返回的Widget
          return MyHomePage(
            title: "没有匹配到",
            message: "通过onUnknownRoute变量",
          );
        });
      },
    );
  }
}

//如果要接收平台层发送的参数,除了使用Channel以外(这种方式不是正常的方式,强烈不推荐),就只能通过window.defaultRouteName了,
//因为routes的route只能提前定义好,无法动态判断
Widget _getWidgetByRoute(String jsonStr) {
  print("json=$jsonStr");
  String _route;
  Map<String, dynamic> jsonMap;
  try {
    jsonMap = json.decode(jsonStr);
    _route = jsonMap["path"];
  } catch (e) {
    print(e);
    _route = jsonStr;
  }
  switch (_route) {
    //接收到了匹配的规则，跳转到flutter指定页面
    case 'page1':
      return MyHomePage(
        title: "匹配到了page1",
        message: "通过home变量",
      );
    case 'page1Param':
      return MyHomePage(
        title: "匹配到了page1Param",
        message: jsonMap["param"],
      );
    case "InvokeMethodPage":
      return InvokeMethodPage(
        title: jsonMap["title"],
        channelName: jsonMap["channelName"],
        androidMethod: jsonMap["androidMethod"],
      );
    default:
      return MyHomePage(
        title: "没有匹配到",
        message: "通过home变量",
      );
  }
}
