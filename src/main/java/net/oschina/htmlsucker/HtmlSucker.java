package net.oschina.htmlsucker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

/**
 *  HtmlSucker 的入口
 *  @author Winter Lau (javayou@gmail.com)
 */
public class HtmlSucker {

    public static void main(String[] args) throws IOException {
        String url = "https://www.oschina.net/news/92798/micro-match-1-0-1-released";
        String html = "<html><head><title>Test</title></head><body><div class='article'><h1>开源中国社区</h1><div class='content'><img src='http://www.oschina.net/logo.gif'/><ul><li>Hello</li></ul><p>Many languages support default arguments for methods and constructors out of the box, i.e. Scala:</p><p>The sum method can be invoked as follows:</p><p>This is very handy, but Java doesn't support it. There are a few different ways to accomplish something similar, however all of them have some drawback.</p><div class='copyright'>oschina</div></div></div></body></html>";
        System.out.println(parse(url, 20000));
    }

    /**
     * 根据 URL 来解析文章信息
     * @param url
     * @return
     */
    public final static Article parse(String url, int timeMillis) throws IOException {
        return parse(Jsoup.parse(new URL(url), timeMillis));
    }

    /**
     * 根据 html 内容来解析文章信息
     * @param html
     * @return
     */
    public final static Article parse(String html) {
        return parse(Jsoup.parse(html));
    }

    private static Article parse(Document doc) {
        Article art = new Article();
        art.setTitle(MetadataExtractor.title(doc));
        art.setDescription(MetadataExtractor.description(doc));
        art.setKeywords(MetadataExtractor.keywords(doc));
        art.setAuthor(MetadataExtractor.author(doc));
        art.setDate(MetadataExtractor.date(doc));
        art.setImage(MetadataExtractor.image(doc));

        //开始解析内容
        art.setContent(ContentExtractor.dig(doc.body()));

        return art;
    }

}
