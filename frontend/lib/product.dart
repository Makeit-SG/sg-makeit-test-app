
class Product {
  int id;
  String name;
  int size;
  String sku;
  String thumbImageUrl;
  int mrp;
  int price;
  int stockQty;

  Product({
    required this.id,
    required this.name,
    required this.size,
    required this.sku,
    required this.thumbImageUrl,
    required this.mrp,
    required this.price,
    required this.stockQty,
  });

  factory Product.fromJson(Map<String, dynamic> json) => Product(
    id: json["id"],
    name: json["name"],
    size: json["size"],
    sku: json["sku"],
    thumbImageUrl: json["thumbImageUrl"],
    mrp: json["mrp"],
    price: json["price"],
    stockQty: json["stockQty"],
  );

  Map<String, dynamic> toJson() => {
    "id": id,
    "name": name,
    "size": size,
    "sku": sku,
    "thumbImageUrl": thumbImageUrl,
    "mrp": mrp,
    "price": price,
    "stockQty": stockQty,
  };
}