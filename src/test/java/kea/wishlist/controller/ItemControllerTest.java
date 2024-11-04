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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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


    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        User user = new User();
        user.setId(10); // Set an ID or other required user attributes
        session.setAttribute("userId", user);
    }

    @AfterEach
    void tearDown() {
    }

//    @Test
//    void findAllItems() throws Exception {
//        // Arrange
//        int wishlistIdToLookFor = 1;
//        List<ItemModel> mockItems = new ArrayList<>();
//        mockItems.add(new ItemModel(1,1, "Item 1", "Description 1", "http://link1.com",10.0,  "http://img1.com"));
//        Mockito.when(itemService.getAllItems(wishlistIdToLookFor)).thenReturn(mockItems);
//
//        // Act & Assert
//        mockMvc.perform(get("/item/" + 1))
//                .andExpect(status().isOk())
//                .andExpect(view().name("wishlist"))
//                .andExpect(model().attributeExists("findAllItems"))
//                .andExpect(model().attribute("findAllItems", mockItems));
//    }
//
//    @Test
//    void showItemForm() throws Exception {
//        mockMvc.perform(get("/item/create"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("addItem"));
//    }
//
//    @Test
//    void addItem() throws Exception {
//        ItemModel item = new ItemModel();
//        item.setName("Sample Item");
//        String wishlistId = "123";
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/{wishlistId}/create", wishlistId)
//                        .session(session)
//                        .flashAttr("item", item))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/wishlist"));
//
//        verify(itemService, times(1)).addItem(eq(item), item.getWishlistId());
//    }
//
//    @Test
//    void showUpdateItemForm() {
//    }
//
//    @Test
//    void updateItem() {
//    }
//
//    @Test
//    void deleteItem() throws Exception {
//        //assert
//        List<ItemModel> mockItems = new ArrayList<>();
//        int itemId = 1;
//
//        //act
//        mockMvc.perform(delete("/items/delete/" + itemId))
//                .andExpect(status().isOk());
//    }
}