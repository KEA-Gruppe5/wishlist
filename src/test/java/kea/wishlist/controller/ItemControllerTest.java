package kea.wishlist.controller;

import kea.wishlist.model.ItemModel;
import kea.wishlist.model.User;
import kea.wishlist.service.ItemService;
import kea.wishlist.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
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
        mockMvc.perform(get("/item/{wishlistId}/addItem", wishlistId))
                .andExpect(status().isOk())
                .andExpect(view().name("addItem"));
    }

    @Test
    void addItem() throws Exception {
        ItemModel item = new ItemModel();
        item.setName("Sample Item");
        int wishlistId = 123;
        mockMvc.perform(post("/item/{wishlistId}/addItem", wishlistId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/wishlist/{wishlistId}"))
                .andExpect(redirectedUrl("/wishlist/" + wishlistId));

    }

    @Test
    void showUpdateItemForm() throws Exception {
        int itemId = 1;

        // Mocking ItemModel with itemId
        ItemModel mockItem = new ItemModel();
        mockItem.setId(itemId);
        mockItem.setName("Test Item");

        // Set up the mock response from itemService
        when(itemService.showUpdateItemForm(itemId)).thenReturn(mockItem);

        // Perform GET request and validate the response
        mockMvc.perform(get("/item/update/{itemId}", itemId))
                .andExpect(status().isOk())
                .andExpect(view().name("editItem"))
                .andExpect(model().attributeExists("showUpdateItemForm"))
                .andExpect(model().attribute("showUpdateItemForm", mockItem));
    }

    @Test
    void updateItem() throws Exception {
        //assert
        int itemId = 1;
        int wishlistId = 8;

        mockMvc.perform(post("/item/update/{itemId}/{wishlistId}", itemId,wishlistId))
                .andExpect(redirectedUrl("/wishlist/" + wishlistId));
    }

    @Test
    void deleteItem() throws Exception {
        //assert
        int itemId = 1;
        int wishlistId = 8;
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();

        //Act & Assert
        mockMvc.perform(post("/item/delete/{itemId}/{wishlistId}", itemId, wishlistId))
                .andExpect(redirectedUrl("/wishlist/" + wishlistId));

        //verify
        verify(itemService, times(1)).deleteItem(itemId);
    }
}