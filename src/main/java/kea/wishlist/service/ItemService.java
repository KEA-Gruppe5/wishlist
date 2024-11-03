package kea.wishlist.service;

import kea.wishlist.model.ItemModel;
import kea.wishlist.model.User;
import kea.wishlist.repo.ItemRepo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepo itemRepo;

    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public List<ItemModel> getAllItems() throws SQLException {
        return itemRepo.findAllItems();
    }

    public String deleteItem(int id) {
        if (itemRepo.deleteItem(id))
            return id + " was successfully deleted!";
        else {
            return "Could not find " + id;
        }
    }

    public ItemModel updateItem(ItemModel itemModel,int id){
        return itemRepo.updateItem(itemModel,id);
    }

    public ItemModel showUpdateItemForm(int wishlistId){
        return itemRepo.showUpdateItemForm(wishlistId);
    }

    public void addItem(ItemModel item, User user) throws SQLException {
        item.setWishlistId(user.getId());
        itemRepo.addItem(item);
    }
}
