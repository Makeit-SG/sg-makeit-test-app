// lib/add_product.dart
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:makeit_frontend/product_list.dart';
import 'package:shared_preferences/shared_preferences.dart';

class AddProductPage extends StatefulWidget {
  @override
  _AddProductPageState createState() => _AddProductPageState();
}

class _AddProductPageState extends State<AddProductPage> {
  final TextEditingController _skuController = TextEditingController();
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _descriptionController = TextEditingController();
  final TextEditingController _priceController = TextEditingController();
  final TextEditingController _sizeController = TextEditingController();
  final TextEditingController _stockQuantityController =
      TextEditingController();
  final TextEditingController _thumbnailImageUrlController =
      TextEditingController();
  final TextEditingController _productImageUrlController =
      TextEditingController();
  final TextEditingController _mrpController = TextEditingController();

  Future<void> _addProduct() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('jwt_token');

    final response = await http.post(
      Uri.parse('http://localhost:8081/products'),
      headers: {
        'Authorization': 'Bearer $token',
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(<String, dynamic>{
        'sku': _skuController.text,
        'name': _nameController.text,
        'description': _descriptionController.text,
        'price': double.parse(_priceController.text),
        'size': _sizeController.text,
        'stockQty': int.parse(_stockQuantityController.text),
        'thumbImageUrl': _thumbnailImageUrlController.text,
        'fullImageUrl': _productImageUrlController.text,
        'mrp': double.parse(_mrpController.text),
      }),
    );

    if (response.statusCode == 200) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Product Added')),
      );
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => ProductList()),
      );
    } else {
      Navigator.pop(context);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Add Product'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _skuController,
              decoration: InputDecoration(labelText: 'SKU'),
            ),
            TextField(
              controller: _nameController,
              decoration: InputDecoration(labelText: 'Name'),
            ),
            TextField(
              controller: _descriptionController,
              decoration: InputDecoration(labelText: 'Description'),
            ),
            TextField(
              controller: _priceController,
              decoration: InputDecoration(labelText: 'Price'),
              keyboardType: TextInputType.number,
            ),
            TextField(
              controller: _sizeController,
              decoration: InputDecoration(labelText: 'Size'),
            ),
            TextField(
              controller: _stockQuantityController,
              decoration: InputDecoration(labelText: 'Stock Quantity'),
              keyboardType: TextInputType.number,
            ),
            TextField(
              controller: _thumbnailImageUrlController,
              decoration: InputDecoration(labelText: 'Thumbnail Image URL'),
            ),
            TextField(
              controller: _productImageUrlController,
              decoration: InputDecoration(labelText: 'Product Image URL'),
            ),
            TextField(
              controller: _mrpController,
              decoration: InputDecoration(labelText: 'MRP'),
              keyboardType: TextInputType.number,
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: _addProduct,
              child: Text('Add Product'),
            ),
          ],
        ),
      ),
    );
  }
}
