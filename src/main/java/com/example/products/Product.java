package com.example.products;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Product {
  private int id;
  private String name;
  private int price;
  private String manufacturer;
  private Date manufacturingDate;

  public Product(int id, String name, int price, String manufacturer, Date manufacturingDate) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.manufacturer = manufacturer;
    this.manufacturingDate = manufacturingDate;
  }
}
