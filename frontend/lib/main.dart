// lib/main.dart
import 'package:flutter/material.dart';
import 'login_page.dart';

void main() {
  runApp(ProductApp());
}

class ProductApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Web JWT Example',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: LoginPage(),
    );
  }
}

