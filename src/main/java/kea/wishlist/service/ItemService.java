package kea.wishlist.service;

import kea.wishlist.model.Item;
import kea.wishlist.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems(int wishlistId) throws SQLException {
        return itemRepository.findAllItems(wishlistId);
    }

    public String deleteItem(int id) {
        if (itemRepository.deleteItem(id))
            return id + " was successfully deleted!";
        else {
            return "Could not find " + id;
        }
    }

    public Item updateItem(Item item, int id){
        return itemRepository.updateItem(item,id);
    }

    public Item findItemById(int id){
        return itemRepository.findItemById(id);
    }

    public Item addItem(Item item, int wishlistId) throws SQLException {
        itemRepository.addItem(item, wishlistId);
        return item;
    }
}
