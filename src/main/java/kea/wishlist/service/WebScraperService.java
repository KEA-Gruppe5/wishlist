package kea.wishlist.service;

import kea.wishlist.model.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WebScraperService {

    // fix
    public Item scrapeitemData(String url)  {
        Item item = new Item();

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }

        try {
            Document document = Jsoup.connect(url).get();

            String title = document.select("h1").text();
            item.setName(title);

            String description = document.select("span.pb-2").text();
            item.setDescription(description);

            String imgUrl = document.select("img.h-auto").attr("src");
            item.setImgUrl(imgUrl);

            String priceText = document.select("span.site-currency-attention").text();

            if (priceText.isEmpty()) {
                item.setPrice(0.0);
            } else {
                priceText = priceText.replaceAll("[^\\d,]", "").split(",")[0];

                try {
                    item.setPrice(Double.parseDouble(priceText));
                } catch (NumberFormatException e) {
                    item.setPrice(0.0);
                }
            }

        } catch (IOException e) {
            item.setDescription("Error: " + e.getMessage());
        }

        return item;
    }
}