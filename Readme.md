## HtmlSucker ##

HtmlSucker 是一个用来从一个网页中提取文章信息的小工具包，例如从网页中提取文章标题、作者、发布时间、
封面图以及文章正文内容。

示例代码：

```
public static void main(String[] args) throws IOException {
    String url = "https://www.oschina.net/news/92798/micro-match-1-0-1-released";
    System.out.println(HtmlSucker.parse(url, 20000));
}
```

本工具包采用 WTFPL 许可，爱怎么用怎么用！