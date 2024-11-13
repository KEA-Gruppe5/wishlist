package kea.wishlist.controller;

import kea.wishlist.model.Item;
import kea.wishlist.service.WebScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebScraperController {

    @Autowired
    private WebScraperService webScraperService;

    @PostMapping("/scrape")
    @ResponseBody
    public Item scrapeItem(@RequestParam("url") String url) {
        try {
            return webScraperService.scrapeitemData(url);
        } catch (Exception e) {
            return new Item();
        }
    }
}