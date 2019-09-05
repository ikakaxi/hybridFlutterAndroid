import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class InvokeMethodPage extends StatefulWidget {
  final String _title;
  final String _channelName;
  final String _androidMethod;

  InvokeMethodPage({
    @required String title,
    @required String channelName,
    @required String androidMethod,
  })  : this._title = title,
        this._channelName = channelName,
        this._androidMethod = androidMethod;

  @override
  _InvokeMethodPageState createState() => _InvokeMethodPageState();
}

class _InvokeMethodPageState extends State<InvokeMethodPage> {
  MethodChannel _methodChannel;

  @override
  void initState() {
    _methodChannel = MethodChannel(widget._channelName);
    _methodChannel.setMethodCallHandler((methodCall) {
      Future future = Future(() {
        if (methodCall.method == "methodInvokeMethodPageState") {
          print("接收到了Android的主动调用,参数为:${methodCall.arguments}");
        }
      });
      return future;
    });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget._title),
      ),
      body: RaisedButton(
        onPressed: () async {
          print("主动调用Android的方法${widget._androidMethod}");
          var result = await _methodChannel.invokeMethod(
            widget._androidMethod,
            "我是来自Flutter的参数",
          );
          print("接收到了调用Android方法的返回值:$result");
        },
        child: Text("我是Flutter控件,点击发送参数到Android"),
      ),
    );
  }
}
