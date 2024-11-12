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
    private Item item;
    private MockHttpSession mockSession;
    private int userId;
    private int wishlistId;


    @BeforeEach
    void setUp() {
        item = new Item(1,2,"testName","testDesc","testUrl",23,"testImg", true);

        userId = 3;
        wishlistId = item.getWishlistId();
        mockSession = new MockHttpSession();
        mockSession.setAttribute("userId", 3);
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

        mockMvc.perform(post("/item/{wishlistId}/add",item.getWishlistId())
                .session(mockSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/" + userId + "/wishlist/" + wishlistId))
                .andExpect(redirectedUrl("/" + userId + "/wishlist/" + wishlistId));
        verify(itemService, times(1)).addItem(any(Item.class), eq(wishlistId));

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

        when(itemService.updateItem(item, item.getId())).thenReturn(item);
        when(itemService.findItemById(item.getId())).thenReturn(item);

        mockMvc.perform(post("/item/{itemId}/update",item.getId())
                .session(mockSession)
                .flashAttr("item",item))
                .andExpect(view().name("redirect:/" + userId + "/wishlist/" + wishlistId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/" + userId + "/wishlist/" + wishlistId));

        verify(itemService, times(1)).updateItem(any(Item.class), eq(item.getId()));
        verify(itemService, times(1)).findItemById(item.getId());
    }

    @Test
    void deleteItem() throws Exception {
        when(itemService.findItemById(anyInt())).thenReturn(item);
        when(itemService.deleteItem(anyInt())).thenReturn(String.valueOf(true));

        mockMvc.perform(post("/item/{itemId}/delete",item.getId())
                .session(mockSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/" + userId + "/wishlist/" + wishlistId))
                .andExpect(redirectedUrl("/" + userId + "/wishlist/" + wishlistId));

        verify(itemService, times(1)).deleteItem(item.getId());
    }
}