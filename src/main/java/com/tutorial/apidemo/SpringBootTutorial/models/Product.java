package com.tutorial.apidemo.SpringBootTutorial.models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name="tblProduct")
public class Product {
    //this is "primary key"
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //auto increament
//    you can also use "Sequence"
    private Long productID;
    //validate = contraint
    @Column(nullable = false, unique = true, length = 300)
    private String productName;
    private int productYear;
    private Double price;
    private String url;

    public Product() {
    }
    //calculated field = transient
    @Transient
    private int age; //age is calculated from "year"
    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - productYear;
    }
    public Product(Long productID, String productName, int productYear, Double price, String url) {
        this.productID = productID;
        this.productName = productName;
        this.productYear = productYear;
        this.price = price;
        this.url = url;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductYear() {
        return productYear;
    }

    public void setProductYear(int productYear) {
        this.productYear = productYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productYear=" + productYear +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productYear == product.productYear && age == product.age && Objects.equals(productID, product.productID) && Objects.equals(productName, product.productName) && Objects.equals(price, product.price) && Objects.equals(url, product.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, productName, productYear, price, url, age);
    }
}
