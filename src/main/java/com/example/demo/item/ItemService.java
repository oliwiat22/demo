package com.example.demo.item;

import com.example.demo.item.dto.EditItem;
import com.example.demo.item.dto.ItemDto;
import com.example.demo.item.dto.NewBid;
import com.example.demo.item.dto.NewItem;
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

  public ItemDto addItem(NewItem newItem, MultipartFile file) {
    ItemImage image = null;
    if (file != null) {
      image = imageRepository.save(ItemImage.fromMultiPartFile(file));
    }
    final Item item = image != null && image.getId() != null ?
        new Item(newItem.getName(), newItem.getDescription(), newItem.getCategory(), newItem.getCompany(), newItem.getPrice(), image.getId())
        : new Item(newItem.getName(), newItem.getDescription(), newItem.getCategory(), newItem.getCompany(), newItem.getPrice());
    final Item saved = itemRepository.save(item);

    return image != null ?
        saved.dto(image)
        : saved.dto();
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
    final Long imageId = item.getImageId();
    if (imageId != null) {
      final ItemImage image = imageRepository.findById(imageId)
          .orElseThrow(() -> new IllegalStateException("Image not found"));
      return item.dto(image);
    } else {
      return item.dto();
    }
  }

  private Item getExistingItemById(long id) {
    return itemRepository.findById(id)
        .orElseThrow(() -> new ItemNotFound("Can not find item with an ID: " + id));
  }

}