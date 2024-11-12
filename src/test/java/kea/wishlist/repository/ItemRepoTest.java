package kea.wishlist.repo;

import kea.wishlist.model.ItemModel;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class ItemRepoTest {


    private ItemModel item;

    @Autowired
    private ItemRepo itemRepo;

    private static final Logger logger = LoggerFactory.getLogger(ItemRepoTest.class);


    @BeforeEach
    void setup() throws SQLException {
        item = new ItemModel(1, 1, "updatedName", "updatedDesc", "link",2, "updatedImgUrl");
    }

    @Test
    void findAllItems() throws SQLException {
        List<ItemModel> findItem = itemRepo.findAllItems(item.getId());
        assertNotNull(findItem);
        assertEquals("updatedName", item.getName());
        assertEquals(1, item.getId());
    }

    @Test
    void addItem() throws SQLException {
        ItemModel addItem = itemRepo.addItem(item, item.getId());
        assertNotNull(addItem);
        assertEquals("updatedName", item.getName());
        assertEquals(1, addItem.getId());
        logger.info("Test add item:", addItem);
    }

    @Test
    void updateItem() throws SQLException {
        //ARRANGE
        itemRepo.addItem(item, item.getId());

        //ACT
        item.setName("testName");
        item.setDescription("testDesc");
        ItemModel updatedItem = itemRepo.updateItem(item,item.getId());

        //ASSERT
        assertNotNull(updatedItem);
        assertEquals("testName", updatedItem.getName());
        assertEquals("testDesc", updatedItem.getDescription());
    }

    @Test
    void showUpdateItemForm() throws SQLException {
        itemRepo.addItem(item, item.getId());

        ItemModel updated = itemRepo.showUpdateItemForm(3);
        //ASSERT
        assertNotNull(updated);
        assertEquals(item,updated);

    }

    @Test
    void deleteItem() throws SQLException{
        itemRepo.addItem(item, item.getId());

        boolean deleted = itemRepo.deleteItem(item.getId());
        assertTrue(deleted);
    }

    @Test
    void findItemById() {
    }
}