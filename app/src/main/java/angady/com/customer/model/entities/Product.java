package angady.com.customer.model.entities;
public class Product {
    private String created_date;

    private String created_by;

    private String product_id;

    private String modified_date;

    private String status;

    private String product_name;

    private String shopkeeper_id;

    private String category_id;

    private String quantity_per_unit;

    private String orderQty;

    private String sku;

    private String product_description;

    private String mini_quantity_order;

    private String unit_price;

    private String special_price;

    private String size;

    private String discount;

    private String modified_by;

    private String unit_weight;

    private String product_image;

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getModified_date() {
        return modified_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getShopkeeper_id() {
        return shopkeeper_id;
    }

    public void setShopkeeper_id(String shopkeeper_id) {
        this.shopkeeper_id = shopkeeper_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getQuantity_per_unit() {
        return quantity_per_unit;
    }

    public void setQuantity_per_unit(String quantity_per_unit) {
        this.quantity_per_unit = quantity_per_unit;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getMini_quantity_order() {
        return mini_quantity_order;
    }

    public void setMini_quantity_order(String mini_quantity_order) {
        this.mini_quantity_order = mini_quantity_order;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getSpecial_price() {
        return special_price;
    }

    public void setSpecial_price(String special_price) {
        this.special_price = special_price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public String getUnit_weight() {
        return unit_weight;
    }

    public void setUnit_weight(String unit_weight) {
        this.unit_weight = unit_weight;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }
    public String getQuantity() {
        return orderQty;
    }
    public void setQuantity(String quantity) {
        this.orderQty = quantity;
    }

    @Override
    public String toString() {
        return "ClassPojo [created_date = " + created_date + ", created_by = " + created_by + ", product_id = " + product_id + ", modified_date = " + modified_date + ", status = " + status + ", product_name = " + product_name + ", shopkeeper_id = " + shopkeeper_id + ", category_id = " + category_id + ", quantity_per_unit = " + quantity_per_unit + ", sku = " + sku + ", product_description = " + product_description + ", mini_quantity_order = " + mini_quantity_order + ", unit_price = " + unit_price + ", special_price = " + special_price + ", size = " + size + ", discount = " + discount + ", modified_by = " + modified_by + ", unit_weight = " + unit_weight + ", product_image = " + product_image + "]";
    }
///*
// * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
// * Unauthorized copying of this file, via any medium is strictly prohibited
// * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
// * along with the project.
// *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
// */
//
///**
// *
// */
//package angady.com.customer.model.entities;
//
//public class Product {
//
//    private String detailProduct_id;
//
//    private String productSku;
//
//    private String productQuantity;
//
//    private String productPrice;
//
//    /**
//     * The item short desc.
//     */
//    private String description = "";
//
//    /**
//     * The item detail.
//     */
//    private String longDescription = "";
//
//    /**
//     * The mrp.
//     */
//    private String mrp;
//
//    /**
//     * The discount.
//     */
//    private String discount;
//
//    /**
//     * The sell mrp.
//     */
//    private String salePrice;
//
//    /**
//     * The quantity.
//     */
//    private String orderQty;
//
//    /**
//     * The image url.
//     */
//    private String imageUrl = "";
//
//    /**
//     * The item name.
//     */
//    private String productName = "";
//
//    private String productId = "";
//
//    /**
//     * @param itemName
//     * @param itemShortDesc
//     * @param MRP
//     */
//    public Product(String itemName, String itemShortDesc,
//                   String MRP, String salePrice) {
//        this.productName = itemName;
//        this.description = itemShortDesc;
//        this.mrp = MRP;
//        this.salePrice = salePrice;
//    }
//
//    public String getProductId() {
//        return productId;
//    }
//
//    public void setProductId(String productId) {
//        this.productId = productId;
//    }
//
//    public String getItemShortDesc() {
//        return description;
//    }
//
//    public void setItemShortDesc(String itemShortDesc) {
//        this.description = itemShortDesc;
//    }
//
//    public String getItemDetail() {
//        return longDescription;
//    }
//
//    public void setItemDetail(String itemDetail) {
//        this.longDescription = itemDetail;
//    }
//
//    public String getMRP() {
//        return this.mrp;
//    }
//
//    public void setMRP(String MRP) {
//        this.mrp = MRP;
//    }
//
//    public String getDiscount() {
//        return discount + "%";
//    }
//
//    public void setDiscount(String discount) {
//        this.discount = discount;
//    }
//
//    public String getDiscountNumeric() {
//        return discount;
//    }
//
//    public String getSellMRP() {
//        return salePrice;
//    }
//
//    public void setSellMRP(String sellMRP) {
//        this.salePrice = sellMRP;
//    }
//
//    public String getQuantity() {
//        return orderQty;
//    }
//
//    public void setQuantity(String quantity) {
//        this.orderQty = quantity;
//    }
//
//    public String getImageURL() {
//        return imageUrl;
//    }
//
//    public void setImageURL(String imageURL) {
//        this.imageUrl = imageURL;
//    }
//
//    public String getDetailProduct_id ()
//    {
//        return detailProduct_id;
//    }
//
//    public void setDetailProduct_id (String detailProduct_id)
//    {
//        this.detailProduct_id = detailProduct_id;
//    }
//
//    public String getProductSku ()
//    {
//        return productSku;
//    }
//
//    public void setProductSku (String productSku)
//    {
//        this.productSku = productSku;
//    }
//
//    public String getProductQuantity ()
//    {
//        return productQuantity;
//    }
//
//    public void setProductQuantity (String productQuantity)
//    {
//        this.productQuantity = productQuantity;
//    }
//
//    public String getProductPrice ()
//    {
//        return productPrice;
//    }
//
//    public void setProductPrice (String productPrice)
//    {
//        this.productPrice = productPrice;
//    }
//
//    public String getProductName ()
//    {
//        return productName;
//    }
//
//    public void setProductName (String productName)
//    {
//        this.productName = productName;
//    }
//
//    @Override
//    public String toString()
//    {
//        return "ClassPojo [detailProduct_id = "+detailProduct_id+", productSku = "+productSku+", productQuantity = "+productQuantity+", productPrice = "+productPrice+", productName = "+productName+"]";
//    }
//
//}
}