package com.example.demo.item.dto;

public class ImageDto {

  private Long id;
  private String fileName;
  private String fileType;
  private String fileDownloadUrl;
  private byte[] data;

  public ImageDto(Long id, String fileName, String fileType, String fileDownloadUrl, byte[] data) {
    this.id = id;
    this.fileName = fileName;
    this.fileType = fileType;
    this.fileDownloadUrl = fileDownloadUrl;
    this.data = data;
  }

  public Long getId() {
    return id;
  }

  public String getFileName() {
    return fileName;
  }

  public String getFileType() {
    return fileType;
  }

  public byte[] getData() {
    return data;
  }

  public String getFileDownloadUrl() {
    return fileDownloadUrl;
  }

}