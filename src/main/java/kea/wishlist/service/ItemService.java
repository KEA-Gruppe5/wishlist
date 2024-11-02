package kea.wishlist.service;

import kea.wishlist.repo.ItemRepo;
import org.springframework.stereotype.Service;

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
}
