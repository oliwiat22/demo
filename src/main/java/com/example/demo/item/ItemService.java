package com.example.demo.item;

import com.example.demo.item.dto.*;
import com.example.demo.item.exception.ItemNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemService {

  private ItemRepository itemRepository;
  private ImageRepository imageRepository;

  @Autowired
  public ItemService(ItemRepository itemRepository, ImageRepository imageRepository) {
    this.itemRepository = itemRepository;
    this.imageRepository = imageRepository;
  }

  public ItemDto addItem(NewItem newItem) {
    final Item item = new Item(
        newItem.getName(), newItem.getDescription(), newItem.getCategory(), newItem.getCompany(), newItem.getPrice(), newItem.getImageId()
    );
    return itemRepository.save(item).dto();
  }

  public ItemDto editItem(long id, EditItem editItem) {
    final Item item = getExistingItemById(id);
    item.edit(editItem);
    return itemRepository.save(item).dto();
  }

  public void deleteItem(long id) {
    itemRepository.deleteById(id);
  }

  public ItemDto makeBid(NewBid newBid) {
    final Item item = getExistingItemById(newBid.getId());
    item.bid(newBid.getPrice());
    return itemRepository.save(item).dto();
  }

  public ItemDto getItem(long id) {
    final Item item = getExistingItemById(id);
    return item.dto();
  }

  private Item getExistingItemById(long id) {
    return itemRepository.findById(id)
        .orElseThrow(() -> new ItemNotFound("Can not find item with an ID: " + id));
  }

  public ImageDto getImageById(long imageId) {
    return imageRepository.findById(imageId)
        .orElseThrow(() -> new IllegalStateException("Image not found"))
        .dto();
  }

  public ImageDto storeImage(MultipartFile file) {
    final ItemImage itemImage = ItemImage.fromMultiPartFile(file);
    return imageRepository.save(itemImage)
        .dto();
  }

}