package com.example.demo.item.dto;

import java.math.BigDecimal;

public class ItemDto {

  private Long id;
  private String name;
  private String description;
  private String category;
  private String company;
  private BigDecimal price;
  private Long imageId;

  public ItemDto(Long id, String name, String description, String category, String company, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.category = category;
    this.company = company;
    this.price = price;
  }

  public ItemDto(Long id, String name, String description, String category, String company, BigDecimal price, Long imageId) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.category = category;
    this.company = company;
    this.price = price;
    this.imageId = imageId;
  }

  public Long getId() {
    return id;
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