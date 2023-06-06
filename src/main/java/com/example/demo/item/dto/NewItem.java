package com.example.demo.item.dto;

import java.math.BigDecimal;

public class NewItem {

  private String name;
  private String description;
  private String category;
  private String company;
  private BigDecimal price;
  private Long imageId;

  public NewItem(String name, String description, String category, String company, BigDecimal price) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.company = company;
    this.price = price;
  }

  public NewItem(String name, String description, String category, String company, BigDecimal price, Long imageId) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.company = company;
    this.price = price;
    this.imageId = imageId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getCategory() {
    return category;
  }

  public String getCompany() {
    return company;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Long getImageId() {
    return imageId;
  }

}