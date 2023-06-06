package com.example.demo.item;

import com.example.demo.item.dto.EditItem;
import com.example.demo.item.dto.ItemDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity(name = "items")
class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private String category;
  private String company;
  private BigDecimal price;
  private Long imageId;

  Item() {
  }

  Item(String name, String description, String category, String company, BigDecimal price) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.company = company;
    this.price = price;
  }

  public Item(String name, String description, String category, String company, BigDecimal price, Long imageId) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.company = company;
    this.price = price;
    this.imageId = imageId;
  }

  Item(Long id, String name, String description, String category, String company, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.category = category;
    this.company = company;
    this.price = price;
  }

  ItemDto dto() {
    return new ItemDto(
        id, name, description, category, company, price, imageId
    );
  }

  void edit(EditItem editItem) {
    this.name = editItem.getName();
    this.description = editItem.getName();
    this.category = editItem.getCategory();
    this.company = editItem.getCompany();
    this.price = editItem.getPrice();
  }

  void bid(BigDecimal price) {
    if (this.price.compareTo(price) < 0) {
      this.price = price;
    }
  }

  Long getId() {
    return id;
  }

  String getName() {
    return name;
  }

  String getDescription() {
    return description;
  }

  String getCategory() {
    return category;
  }

  String getCompany() {
    return company;
  }

  BigDecimal getPrice() {
    return price;
  }

  Long getImageId() {
    return imageId;
  }

}