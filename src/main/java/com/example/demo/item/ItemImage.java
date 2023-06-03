package com.example.demo.item;

import com.example.demo.item.dto.ImageDto;
import jakarta.persistence.*;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

@Entity
@Table(name = "images")
class ItemImage {

  private static final int NAME_MAX_LENGTH = 255;
  private static final int NAME_MIN_LENGTH = 1;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "file_type")
  private String fileType;

  @Lob
  @Column(name = "data")
  private byte[] data;

  ItemImage() {
  }

  ItemImage(String fileName, String fileType, byte[] data) {
    this.fileName = fileName;
    this.fileType = fileType;
    this.data = data;
  }

  ItemImage(Long id, String fileName, String fileType, byte[] data) {
    this.id = id;
    this.fileName = fileName;
    this.fileType = fileType;
    this.data = data;
  }

  static ItemImage fromMultiPartFile(MultipartFile file)  {
    try {
      return new ItemImage(getCleanFileName(file), file.getContentType(), file.getBytes());
    } catch (IOException e) {
      throw new IllegalStateException("Could not store image" + file.getOriginalFilename());
    }
  }

  ImageDto dto() {
    return new ImageDto(id, fileName, fileType, getImageDownloadUrl(), data);
  }

  private static String getCleanFileName(MultipartFile file) {
    final String filePath = Optional.ofNullable(file.getOriginalFilename())
        .orElseThrow(() -> new IllegalStateException("Invalid file name"));
    final String fileName = StringUtils.cleanPath(filePath);

    return Optional.of(fileName)
        .filter(name -> !name.contains("..") && name.length() <= NAME_MAX_LENGTH && name.length() >= NAME_MIN_LENGTH)
        .orElseThrow(() -> new IllegalStateException("Invalid file name"));
  }

  private String getImageDownloadUrl() {
    return ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/image/")
        .path(String.valueOf(id))
        .toUriString();
  }

  Long getId() {
    return id;
  }

  String getFileName() {
    return fileName;
  }

  String getFileType() {
    return fileType;
  }

  byte[] getData() {
    return data;
  }

}