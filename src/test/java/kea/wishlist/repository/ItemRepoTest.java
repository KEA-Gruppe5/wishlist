package kea.wishlist.repository;

import kea.wishlist.model.Item;
import kea.wishlist.repository.ItemRepository;
import kea.wishlist.util.ConnectionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class ItemRepoTest {



    @Autowired
    private ItemRepository itemRepo;

    private Item item;

    private static final Logger logger = LoggerFactory.getLogger(ItemRepoTest.class);

    @BeforeEach
    void setup() throws SQLException {
        item = new Item(1, 1, "updatedName", "updatedDesc", "link", 2.0, "updatedImgUrl", true);
        itemRepo.addItem(item, item.getWishlistId());
    }

    @Test
    void findAllItems() throws SQLException {
        List<Item> findItem = itemRepo.findAllItems(item.getWishlistId());
        assertNotNull(findItem);
        assertFalse(findItem.isEmpty());
        assertEquals("Noise Cancelling Headphones", findItem.get(0).getName());
        assertEquals(1, findItem.get(0).getWishlistId());
    }

    @Test
    void addItem() throws SQLException {
        Item newItem = new Item(2, 1, "newItem", "newDesc", "newLink", 3.0, "newImgUrl", false);
        Item addedItem = itemRepo.addItem(newItem, newItem.getWishlistId());
        assertNotNull(addedItem);
        assertEquals("newItem", addedItem.getName());
        logger.info("Test add item: {}", addedItem);
    }

    @Test
    void updateItem() throws SQLException {
        item.setName("testName");
        item.setDescription("testDesc");
        Item updatedItem = itemRepo.updateItem(item, item.getId());

        assertNotNull(updatedItem);
        assertEquals("testName", updatedItem.getName());
        assertEquals("testDesc", updatedItem.getDescription());
    }

    @Test
    void findItemById() throws SQLException {
        Item foundItem = itemRepo.findItemById(item.getId());
        assertNotNull(foundItem);
        assertEquals(item.getId(), foundItem.getId());
    }

    @Test
    void reserveGift() throws SQLException {
        itemRepo.reserveGift(item.getId());
        Item reservedItem = itemRepo.findItemById(item.getId());
        assertFalse(reservedItem.isReserveGift());
    }
}