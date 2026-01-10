package com.example.demo.sample;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupHtmlParser {
    public static void main(String[] args) {

        try {
            // jsoupを使用して当ブログのトップページへアクセス
            Document doc = Jsoup.connect("https://ja.wikipedia.org/wiki/藤井美菜").get();

            Elements elements = doc.select("h2");

            for (Element element : elements) {
                System.out.println(element.text());

            }
            System.out.println(elements.html());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
