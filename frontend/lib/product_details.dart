import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:shared_preferences/shared_preferences.dart'; // Import SharedPreferences
import 'product_detail_model.dart';

class ProductDetails extends StatelessWidget {
  final int productId;

  ProductDetails({required this.productId});

  Future<ProductDetailsModel> fetchProductDetails() async {
    // Get the JWT token from SharedPreferences
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('jwt_token');

    final response = await http.get(
      Uri.parse('http://localhost:8081/products/$productId'),
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token', // Include the JWT token
      },
    );

    if (response.statusCode == 200) {
      return ProductDetailsModel.fromJson(json.decode(response.body));
    } else if (response.statusCode == 403) {
      throw Exception('Access Denied: You do not have permission to access this resource.');
    } else {
      throw Exception('Failed to load product details');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Product Details'),
      ),
      body: FutureBuilder<ProductDetailsModel>(
        future: fetchProductDetails(),
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            ProductDetailsModel? product = snapshot.data;
            return Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Center(
                    child: Image.network(product!.fullImageUrl, height: 200),
                  ),
                  SizedBox(height: 20),
                  Text(
                    product.name,
                    style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
                  ),
                  SizedBox(height: 10),
                  Text(product.description),
                  SizedBox(height: 10),
                  Text('Size: ${product.size}'),
                  SizedBox(height: 10),
                  Text('Stock Quantity: ${product.stockQty}'),
                  SizedBox(height: 10),
                  Text(
                    'Price: \$${product.price.toStringAsFixed(2)} (MRP: \$${product.mrp.toStringAsFixed(2)})',
                    style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                  ),
                  SizedBox(height: 20),
                  ElevatedButton(
                    onPressed: () {
                      Navigator.pop(context);
                    },
                    child: Text('Back to Products'),
                  ),
                ],
              ),
            );
          } else if (snapshot.hasError) {
            return Center(child: Text("${snapshot.error}"));
          }
          return Center(child: CircularProgressIndicator());
        },
      ),
    );
  }
}
