package com.example.demo.item.exception;

public class ItemNotFound extends RuntimeException {

  public ItemNotFound(String message) {
    super(message);
  }

}