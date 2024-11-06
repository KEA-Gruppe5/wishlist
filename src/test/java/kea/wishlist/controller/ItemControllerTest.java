package kea.wishlist.controller;

import kea.wishlist.model.Item;
import kea.wishlist.service.ItemService;
import kea.wishlist.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
class ItemControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ItemService itemService;
    private MockHttpSession session;
    @Autowired
    private ItemController itemController;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void showItemForm() throws Exception {
        int wishlistId = 1;
        mockMvc.perform(get("/item/{wishlistId}/add", wishlistId))
                .andExpect(status().isOk())
                .andExpect(view().name("item/addItem"));
    }

    @Test
    void addItem() throws Exception {
        Item item = new Item();
        item.setName("Sample Item");
        int wishlistId = 123;
        mockMvc.perform(post("/item/{wishlistId}/add", wishlistId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/wishlist/{wishlistId}"))
                .andExpect(redirectedUrl("/wishlist/" + wishlistId));

    }

    @Test
    void showUpdateItemForm() throws Exception {
        int itemId = 1;

        // Mocking Item with itemId
        Item mockItem = new Item();
        mockItem.setId(itemId);
        mockItem.setName("Test Item");

        // Set up the mock response from itemService
        when(itemService.findItemById(itemId)).thenReturn(mockItem);

        // Perform GET request and validate the response
        mockMvc.perform(get("/item/{itemId}/update", itemId))
                .andExpect(status().isOk())
                .andExpect(view().name("item/editItem"))
                .andExpect(model().attributeExists("item"))
                .andExpect(model().attribute("item", mockItem));
    }


    @Test
    void updateItem() throws Exception {
        //assert
        int itemId = 1;
        int wishlistId = 8;

        mockMvc.perform(post("/item/{itemId}/update", itemId))
                .andExpect(redirectedUrl("/wishlist/" + wishlistId));
    }

    @Test
    void deleteItem() throws Exception {
        //assert
        int itemId = 1;
        int wishlistId = 8;
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();

        //Act & Assert
        mockMvc.perform(post("/item/{itemId}/delete", itemId))
                .andExpect(redirectedUrl("/wishlist/" + wishlistId));

        //verify
        verify(itemService, times(1)).deleteItem(itemId);
    }
}