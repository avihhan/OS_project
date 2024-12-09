package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WebCrawlerApp {
    public static void main(String[] args) {
        // Create a Scanner object to take input from the terminal
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the search item
        System.out.print("Enter the search item: ");
        String searchItem = scanner.nextLine();  // Read the search item

        int maxDepth = 1;  // Set the maximum depth for crawling

        // Initialize the WebCrawler with maxDepth
        WebCrawler crawler = new WebCrawler(maxDepth);

        // List to store matching URLs
        List<String> ebayLinks = new ArrayList<>();

        // Crawl the eBay website with the input search item
        String url = "https://www.ebay.com/sch/i.html?_from=R40&_trksid=p4432023.m570.l1313&_nkw=" + searchItem + "&_sacat=0";

        // Crawl the website and store matching links
        crawler.crawl(url, 0, maxDepth, ebayLinks);

        // Print the stored links
        System.out.println("\nFound eBay item links:");
        for (String link : ebayLinks) {
            System.out.println(link);
        }

        // Shut down the crawler
        crawler.shutdown();

        //pricing
        EbaySearchDemo ebay_items = new EbaySearchDemo();
        
        System.out.println("Ebay_Item List: " + "\n");
        ebay_items.get_items();

        // Close the scanner
        scanner.close();
    }
}

class WebCrawler {
    private int maxDepth;

    public WebCrawler(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    // Crawl method with List to store URLs
    public void crawl(String url, int currentDepth, int maxDepth, List<String> ebayLinks) {
        // Base condition to stop crawling when max depth is reached
        if (currentDepth > maxDepth) return;

        // Simulating the crawling and link extraction process (to be implemented)
        // System.out.println("Crawling: " + url);

        // Extract links from the page
        List<String> links = extractLinksFromPage(url);

        // Check each link and store it if it matches the condition
        for (String link : links) {
            if (link.startsWith("https://www.ebay.com/itm/")) {
                ebayLinks.add(link);
            }
        }

        // Simulate crawling deeper by calling crawl() on the extracted links (next depth)
        for (String link : links) {
            crawl(link, currentDepth + 1, maxDepth, ebayLinks);
        }
    }

    // Method to extract links from a page (real implementation with Jsoup)
    private List<String> extractLinksFromPage(String url) {
        List<String> links = new ArrayList<>();

        try {
            // Connect to the page and fetch the document
            Document document = Jsoup.connect(url).get();

            // Select all the anchor elements with href attribute
            Elements anchorTags = document.select("a[href]");

            // Loop through each anchor tag to extract the links
            for (Element anchor : anchorTags) {
                String link = anchor.absUrl("href");  // Get the absolute URL
                links.add(link);  // Add the link to the list
            }

        } catch (IOException e) {
            System.out.println("Error extracting links from: " + url);
        }

        return links;
    }

    // Shutdown method (if needed for cleanup)
    public void shutdown() {
        System.out.println("Crawler shutdown.");
    }
}
