package com.example.demo.item.dto;

import java.math.BigDecimal;

public class NewBid {

  private long id;
  private BigDecimal price;

  public NewBid(long id, BigDecimal price) {
    this.id = id;
    this.price = price;
  }

  public long getId() {
    return id;
  }

  public BigDecimal getPrice() {
    return price;
  }

}
