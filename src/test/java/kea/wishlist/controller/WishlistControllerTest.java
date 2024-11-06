package kea.wishlist.controller;
import kea.wishlist.model.ItemModel;
import kea.wishlist.model.WishlistModel;
import kea.wishlist.service.ItemService;
import kea.wishlist.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@WebMvcTest(WishlistController.class)
@ActiveProfiles("test")
class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WishlistService wishlistService;
    @MockBean
    private ItemService itemService;

    @Autowired
    private WishlistController wishlistController;




    @Test
    void testWishlistForm_showForm() throws Exception {
        int userId = 1;
        mockMvc.perform(get("/wishlist/{userId}/create", userId)) // Specify the path and userId
                .andExpect(status().isOk())
                .andExpect(view().name("newWishlist"));
    }

    @Test
    public void testCreateWishlist_ReturnsStatus200() throws Exception {
        int userId = 1;
        WishlistModel wishlist = new WishlistModel(userId, "New Wishlist");


        doNothing().when(wishlistService).addWishlist(any(WishlistModel.class), eq(userId));

        mockMvc.perform(post("/" + userId + "/save")
                        .param("userId", String.valueOf(userId))
                        .param("name", wishlist.getName()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteWishList_ReturnsRedirectionStatus() throws Exception {
        int wishlistId = 1;

        // Mock the service behavior to do nothing on delete
        doNothing().when(wishlistService).deleteWishList(wishlistId);

        // Perform DELETE request and check for a 3xx redirection status without specifying URL
        mockMvc.perform(delete("/wishlist/{id}/delete", wishlistId))
                .andExpect(status().is3xxRedirection()); // Check for any 3xx status

        // Verify the delete method was called with the correct ID
        verify(wishlistService, times(1)).deleteWishList(wishlistId);
    }


    @Test
    void findAllItems() {
    }




    @Test
    void updateForm() {
    }

    @Test
    void update() {
    }

    @Test
    void getWishlistsByUserId() {
    }
}