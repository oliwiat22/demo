package com.example.demo.item;

import com.example.demo.item.dto.EditItem;
import com.example.demo.item.dto.ItemDto;
import com.example.demo.item.dto.NewBid;
import com.example.demo.item.dto.NewItem;
import com.example.demo.item.exception.ItemNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  private ItemRepository itemRepository;

  @Autowired
  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public ItemDto addItem(NewItem newItem) {
    final Item item = new Item(
        newItem.getName(), newItem.getDescription(), newItem.getCategory(), newItem.getCompany(), newItem.getPrice()
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

}
