package kea.wishlist.service;

import kea.wishlist.model.ItemModel;
import kea.wishlist.model.User;
import kea.wishlist.repo.ItemRepo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ItemService {

    private final ItemRepo itemRepo;

    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public String deleteItem(int id) {
        if (itemRepo.deleteItem(id))
            return id + " was successfully deleted!";
        else {
            return "Could not find " + id;
        }
    }

    public void addItem(ItemModel item, User user) throws SQLException {
        item.setWishlistId(user.getId());
        itemRepo.addItem(item);
    }
}
