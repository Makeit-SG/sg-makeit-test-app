class ProductDetailsModel {
    int id;
    String name;
    int size;
    String sku;
    String description;
    String thumbImageUrl;
    String fullImageUrl;
    int mrp;
    int price;
    int stockQty;
    DateTime createdAt;
    dynamic updatedAt;

    ProductDetailsModel({
        required this.id,
        required this.name,
        required this.size,
        required this.sku,
        required this.description,
        required this.thumbImageUrl,
        required this.fullImageUrl,
        required this.mrp,
        required this.price,
        required this.stockQty,
        required this.createdAt,
        required this.updatedAt,
    });

    factory ProductDetailsModel.fromJson(Map<String, dynamic> json) => ProductDetailsModel(
        id: json["id"],
        name: json["name"],
        size: json["size"],
        sku: json["sku"],
        description: json["description"],
        thumbImageUrl: json["thumbImageUrl"],
        fullImageUrl: json["fullImageUrl"],
        mrp: json["mrp"],
        price: json["price"],
        stockQty: json["stockQty"],
        createdAt: DateTime.parse(json["createdAt"]),
        updatedAt: json["updatedAt"],
    );

    Map<String, dynamic> toJson() => {
        "id": id,
        "name": name,
        "size": size,
        "sku": sku,
        "description": description,
        "thumbImageUrl": thumbImageUrl,
        "fullImageUrl": fullImageUrl,
        "mrp": mrp,
        "price": price,
        "stockQty": stockQty,
        "createdAt": createdAt.toIso8601String(),
        "updatedAt": updatedAt,
    };
}