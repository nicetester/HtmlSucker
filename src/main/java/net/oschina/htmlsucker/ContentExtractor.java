package net.oschina.htmlsucker;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * 内容提取算法
 */
public class ContentExtractor {

    /**
     * Tags that should be retained in the output. This list should be fairly minimal, and equivalent
     * to the list of tags that callers can be expected to be able to handle.
     */
    private static final Collection<String> TEXT_TAGS = Arrays.asList(
            "p", "b", "i", "u", "strong", "em",
            "a", "pre", "code", "h1", "h2", "h3", "h4",
            "h5", "h6", "blockquote", "img", "hr", "br",
            "ul", "ol", "li", "embed","table"
    );

    public static String dig(Element body) {
        //删除无用节点
        List<Elements> textNodes = findTextNode(body);
        return textNodes.stream().max(Comparator.comparingInt(e -> e.text().length())).get().outerHtml();
    }

    /**
     * 找出所有的内容节点
     * @param element
     * @return
     */
    private static List<Elements> findTextNode(Element element) {
        List<Elements> list = new ArrayList<>();
        Elements elements = new Elements();
        for(Element child : element.children()) {
            String nodeName = child.nodeName().toLowerCase();
            if(TEXT_TAGS.contains(nodeName)) {
                elements.add(child);
            } else {
                list.addAll(findTextNode(child));
            }
        }
        list.add(elements);

        return list;
    }

}
