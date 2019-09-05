import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class JumpActivityPage extends StatefulWidget {
  @override
  _JumpActivityPageState createState() => _JumpActivityPageState();
}

class _JumpActivityPageState extends State<JumpActivityPage> {

  MethodChannel _methodChannel;

  @override
  void initState() {
    _methodChannel = MethodChannel("MainActivityMethodChannel");
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("我是Flutter页面"),
      ),
      body: RaisedButton(
        child: Text("点我通过发送Channel消息通知Android层跳转页面"),
        onPressed: (){
          _methodChannel.invokeMethod("jumpTestActivity");
        },
      ),
    );
  }
}
