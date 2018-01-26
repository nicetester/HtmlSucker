package net.oschina.htmlsucker;

public class StringUtils {

    private static final String WHITESPACE = "[ \r\t\n]+";

    private StringUtils(){}

    /**
     * remove more than two spaces or newlines
     */
    public static String innerTrim(String str) {
        return str.replaceAll(WHITESPACE, " ").trim();
    }

    public static String urlEncodeSpaceCharacter(String url) {
        return url.isEmpty() ? url : url.trim().replaceAll(WHITESPACE, "%20");
    }

    public static String cleanTitle(String title) {
        StringBuilder res = new StringBuilder();
        int index = title.lastIndexOf("|");
        if (index > 0 && title.length() / 2 < index)
            title = title.substring(0, index + 1);

        int counter = 0;
        String[] strs = title.split("\\|");
        for (String part : strs) {
            if (counter == strs.length - 1 && res.length() > part.length()) {
                continue;
            }
            if (counter > 0) {
                res.append("|");
            }
            res.append(part);
            counter++;
        }
        return innerTrim(res.toString());
    }

}
