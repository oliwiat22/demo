package com.example.demo.item;

import com.example.demo.item.dto.EditItem;
import com.example.demo.item.dto.ItemDto;
import com.example.demo.item.dto.NewBid;
import com.example.demo.item.dto.NewItem;
import org.springframework.beans.factory.annotation.Autowired;
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
  ResponseEntity<ItemDto> addItem(@RequestBody NewItem newItem, @RequestParam("file") MultipartFile file) {
    final ItemDto item = itemService.addItem(newItem, file);
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

}