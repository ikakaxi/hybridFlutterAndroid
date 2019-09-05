import 'dart:io' show Platform;

import 'package:flutter/material.dart';

/// 描述:嵌入AndroidView
/// 作者:liuhc
/// 创建日期：2019-09-05 on 3:20 PM
class InsertAndroidViewPage extends StatefulWidget {
  @override
  _InsertAndroidViewPageState createState() => _InsertAndroidViewPageState();
}

class _InsertAndroidViewPageState extends State<InsertAndroidViewPage> {
  GlobalKey key = GlobalKey();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("我来演示如何嵌入AndroidView"),
        ),
        body: Platform.isAndroid ? AndroidView(key: key, viewType: 'InsertAndroidView') : Text("ios和Android实现原理一样"));
  }
}
