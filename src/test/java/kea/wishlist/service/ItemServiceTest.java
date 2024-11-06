package kea.wishlist.service;

import kea.wishlist.model.ItemModel;
import kea.wishlist.repo.ItemRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class ItemServiceTest {

    private ItemRepo itemRepo;
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        itemRepo = mock(ItemRepo.class);
        itemService = new ItemService(itemRepo);
    }

    @Test
    public void testGetAllItems() throws SQLException {
        //ARRANGE
        int wishlistId = 1;
        List<ItemModel> expectedItems = new ArrayList<>();
        expectedItems.add(new ItemModel(1, wishlistId, "Item1", "Description1", "link1", 12, "imgUrl1"));
        expectedItems.add(new ItemModel(2, wishlistId, "Item1", "Description1", "link1", 12, "imgUrl1"));

        //ASSERT

        when(itemRepo.findAllItems(wishlistId)).thenReturn(expectedItems);
        List<ItemModel> actualItems = itemService.getAllItems(wishlistId);

        assertEquals(expectedItems.size(), actualItems.size());
        assertEquals(expectedItems.get(0).getName(), actualItems.get(0).getName());
        assertEquals(expectedItems.get(1).getName(), actualItems.get(1).getName());
        verify(itemRepo).findAllItems(wishlistId);
    }

    @Test
    public void testDeleteItemSuccessfully() {
        int id = 1;

        when(itemRepo.deleteItem(id)).thenReturn(true);

        String result = itemService.deleteItem(id);

        assertEquals(id + " was successfully deleted!", result);
        verify(itemRepo).deleteItem(id);
    }

    @Test
    public void testUpdateItem() {
        ItemModel itemModel = new ItemModel(1, 1, "Updated Item", "Updated Description", "link",2, "updatedImgUrl");

        when(itemRepo.updateItem(itemModel, itemModel.getId())).thenReturn(itemModel);

        ItemModel updatedItem = itemService.updateItem(itemModel, itemModel.getId());

        assertEquals(itemModel.getName(), updatedItem.getName());
        verify(itemRepo).updateItem(itemModel, itemModel.getId());
    }

    @Test
    public void testAddItem() throws SQLException {
        ItemModel itemModel = new ItemModel(0, 1, "New Item", "New Description", "link", 22, "newImgUrl");
        when(itemRepo.addItem(itemModel, itemModel.getWishlistId())).thenReturn(itemModel);

        ItemModel addedItem = itemService.addItem(itemModel, itemModel.getWishlistId());

        assertEquals(itemModel.getName(), addedItem.getName());
        verify(itemRepo).addItem(itemModel, itemModel.getWishlistId());
    }
}