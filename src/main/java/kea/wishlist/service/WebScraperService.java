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

        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36")
                    .header("Accept-Language", "en-US,en;q=0.9")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .get();

            String title = document.select("h1").text();
            item.setName(title);

            String description = document.select("span.pb-2").text();
            description = description.substring(0, 255);
            item.setDescription(description);

            String imgUrl = document.select("img.h-auto").attr("src");
            item.setImgUrl("https://www.proshop.dk" + imgUrl);

            String priceText = document.select("span.site-currency-attention").text();
            priceText = priceText.replaceAll("[^\\d,]", "").split(",")[0];
            item.setPrice(Double.parseDouble(priceText));

        } catch (IOException e) {
            item.setDescription("Error: " + e.getMessage());
        }
        return item;
    }
}