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

运行结果：

```
title:撮合平台 1.0.1 发布 暴露远程调用接口 - 开源中国社区
keywords:开源中国,micro-match,撮合平台 1.0.1 发布 暴露远程调用接口
description:撮合平台 1.0.1 发布 暴露远程调用接口并优化撮合条数算法 1，挂买单接口 2，挂卖单接口 3，撮合接口 4，撮合结果查询接口 详见：https://my.oschina.net/jeffreyning/blog/1612810...
author:
date:null
image:https://www.oschina.net/img/logo_s2.png
content:
<p style="margin:0 0 10px 0;"><a data-traceid="news_detail_above_text_link_1" data-tracepid="news_detail_above_text_link" style="color:#A00;font-weight:bold;" href="http://click.aliyun.com/m/18500/" target="_blank">阿里云高性能云服务器，2折起！ &gt;&gt;&gt; &gt;&gt;&gt;</a>&nbsp;&nbsp;<img src="https://my.oschina.net/img/hot3.png" align="" style="max-height: 32px; max-width: 32px;"></p>
<p>撮合平台 1.0.1 发布 暴露远程调用接口并优化撮合条数算法</p>
<p>1，挂买单接口</p>
<p>2，挂卖单接口</p>
<p>3，撮合接口</p>
<p>4，撮合结果查询接口</p>
<p>详见：<a data-cke-saved-href="https://my.oschina.net/jeffreyning/blog/1612810" href="https://my.oschina.net/jeffreyning/blog/1612810" target="_blank">https://my.oschina.net/jeffreyning/blog/1612810</a></p>

```

本工具包采用 WTFPL 许可，爱怎么用怎么用！