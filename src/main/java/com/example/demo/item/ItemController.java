package com.example.demo.item;

import com.example.demo.item.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/item")
@RestController
class ItemController {

  private ItemService itemService;

  @Autowired
  ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @PostMapping("/")
  ResponseEntity<ItemDto> addItem(@RequestBody NewItem newItem) {
    final ItemDto item = itemService.addItem(newItem);
    return ResponseEntity.ok(item);
  }

  @PutMapping("/{id}/")
  ResponseEntity<ItemDto> editItem(@PathVariable("id") long id, @RequestBody EditItem editItem) {
    final ItemDto item = itemService.editItem(id, editItem);
    return ResponseEntity.ok(item);
  }

  @DeleteMapping("/{id}/")
  ResponseEntity<Void> deleteItem(@PathVariable("id") long id) {
    itemService.deleteItem(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}/")
  ResponseEntity<ItemDto> getItem(@PathVariable("id") long id) {
    return ResponseEntity.ok(itemService.getItem(id));
  }

  @PutMapping("/bid/")
  ResponseEntity<ItemDto> makeBid(@RequestBody NewBid newBid) {
    final ItemDto item = itemService.makeBid(newBid);
    return ResponseEntity.ok(item);
  }

  @CrossOrigin
  @PostMapping("/image/")
  public ResponseEntity<ImageDto> uploadImage(@RequestBody MultipartFile file) {
    return ResponseEntity.ok(itemService.storeImage(file));
  }

  @CrossOrigin
  @GetMapping("/image/{imageId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable long imageId) {
    final ImageDto image = itemService.getImageById(imageId);

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(image.getFileType()))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + image.getFileName() + "")
        .body(new ByteArrayResource(image.getData()));
  }

}