package kea.wishlist.controller;
import kea.wishlist.model.Wishlist;
import kea.wishlist.service.ItemService;
import kea.wishlist.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    void testWishlistForm_showForm() throws Exception {
        int userId = 1;
        mockMvc.perform(get("/{userId}/wishlist/create", userId)) // Specify the path and userId
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist/newWishlist"));
    }

    @Test
    public void testCreateWishlist_ReturnsStatus200() throws Exception {
        int userId = 1;
        Wishlist wishlist = new Wishlist(userId, "New Wishlist");

        mockMvc.perform(post("/{userId}/wishlist/create", userId)
                        .param("userId", String.valueOf(userId))
                        .param("name", wishlist.getName()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"+ userId + "/wishlist"));
    }

    @Test
    void deleteWishList_ReturnsRedirectionStatus() throws Exception {
        int userId = 1;
        int wishlistId = 1;
        doNothing().when(wishlistService).deleteWishlist(wishlistId);

        mockMvc.perform(delete("/{userId}/wishlist/{wishlistId}/delete", userId, wishlistId))
                .andExpect(status().is3xxRedirection());

        verify(wishlistService, times(1)).deleteWishlist(wishlistId);
    }


    @Test
    void updateForm() throws Exception {
        int userId = 1;
        int wishlistId = 1;
        Wishlist wishlist = new Wishlist(userId, "Wishlist to Update");
        when(wishlistService.findWishlistById(wishlistId)).thenReturn(wishlist);

        mockMvc.perform(get("/{userId}/wishlist/{wishlistId}/edit", userId, wishlistId))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist/editWishlist"))
                .andExpect(model().attributeExists("wishlist"))
                .andExpect(model().attribute("wishlist", wishlist));

        verify(wishlistService, times(1)).findWishlistById(wishlistId);
    }

    @Test
    void update() {
    }

    @Test
    void edit(){

    }


    @Test
    void getWishlistsByUserId() {
    }

    @Test
    void findAllItems() {
    }





}