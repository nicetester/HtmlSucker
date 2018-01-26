package net.oschina.htmlsucker;

import java.util.Collection;
import java.util.Date;

/**
 * 文章对象
 *  @author Winter Lau (javayou@gmail.com)
 */
public class Article {

    @Override
    public String toString() {
        return String.format("title:%s\nkeywords:%s\ndescription:%s\nauthor:%s\ndate:%s\nimage:%s\ncontent:\n%s",
                title, String.join(",", keywords), description, author, date, image, content);
    }

    private String title;
    private Collection<String> keywords;
    private String description;
    private String content;
    private String author;
    private Date date;
    private String image;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Collection<String> keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
