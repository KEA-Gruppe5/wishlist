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
        String apiKey = "0XE64RG6REXQBIO9AQX15FVCOY3PVMYL973CTJYQJPD5TNESAAYOQVP3J4WX7JR34OCA3KO3R4SIRPST";
        String scrapingBeeUrl = "https://app.scrapingbee.com/api/v1/?api_key=" + apiKey + "&url=" + url + "&render_js=true&json=true";;

        Item item = new Item();

        try {
            Document document = Jsoup.connect(url)
                    .header("Accept-Language", "en-US,en;q=0.9")
                    .header("Referer", "https://www.proshop.dk/")
                    .userAgent("Mozilla/5.0 ...")
                    .timeout(10000)
                    .ignoreContentType(true)
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