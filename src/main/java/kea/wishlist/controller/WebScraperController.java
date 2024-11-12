package kea.wishlist.controller;

import kea.wishlist.model.Item;
import kea.wishlist.service.WebScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WebScraperController {
    //TODO add JS instead of controller, do re
    @Autowired
    WebScraperService webScraperService;

    @PostMapping("/scrape/item")
    public String scrape(@RequestParam String url, Model model) {
        try {
            Item item = webScraperService.scrapeitemData(url);
            model.addAttribute("showItemForm", item);
        } catch (Exception e) {
            Item errorItem = new Item();
            errorItem.setDescription("Error occurred: " + e.getMessage());
            model.addAttribute("showItemForm", errorItem);
        }
        return "item/addItem";
    }
}

