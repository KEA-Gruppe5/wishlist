package kea.wishlist.service;

import kea.wishlist.model.Item;
import kea.wishlist.repository.ItemRepository;
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
public class ItemServiceTest {

    private ItemRepository itemRepo;
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        itemRepo = mock(ItemRepository.class);
        itemService = new ItemService(itemRepo);
    }

    @Test
    public void testGetAllItems() throws SQLException {
        //ARRANGE
        int wishlistId = 1;
        List<Item> expectedItems = new ArrayList<>();
        expectedItems.add(new Item(1, wishlistId, "Item1", "Description1", "link1", 12, "imgUrl1",true));
        expectedItems.add(new Item(2, wishlistId, "Item1", "Description1", "link1", 12, "imgUrl1",false));

        //ASSERT

        when(itemRepo.findAllItems(wishlistId)).thenReturn(expectedItems);
        List<Item> actualItems = itemService.getAllItems(wishlistId);

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
        Item itemModel = new Item(1, 1, "Updated Item", "Updated Description", "link",2, "updatedImgUrl",true);

        when(itemRepo.updateItem(itemModel, itemModel.getId())).thenReturn(itemModel);

        Item updatedItem = itemService.updateItem(itemModel, itemModel.getId());

        assertEquals(itemModel.getName(), updatedItem.getName());
        verify(itemRepo).updateItem(itemModel, itemModel.getId());
    }

    @Test
    public void testAddItem() throws SQLException {
        Item itemModel = new Item(0, 1, "New Item", "New Description", "link", 22, "newImgUrl",true);
        when(itemRepo.addItem(itemModel, itemModel.getWishlistId())).thenReturn(itemModel);

        Item addedItem = itemService.addItem(itemModel, itemModel.getWishlistId());

        assertEquals(itemModel.getName(), addedItem.getName());
        verify(itemRepo).addItem(itemModel, itemModel.getWishlistId());
    }
}