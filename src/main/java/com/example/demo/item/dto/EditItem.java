package com.example.demo.item.dto;

import java.math.BigDecimal;

public class EditItem {

  private String name;
  private String description;
  private String category;
  private String company;
  private BigDecimal price;

  public EditItem(String name, String description, String category, String company, BigDecimal price) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.company = company;
    this.price = price;
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


}
