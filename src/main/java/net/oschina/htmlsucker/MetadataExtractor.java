package net.oschina.htmlsucker;

import org.jsoup.nodes.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 网页的元数据抽取
 */
public class MetadataExtractor {

    public static void main(String[] args) throws ParseException {
        String sdate = "2018-01-24T18:53:26+00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        System.out.println(sdf.parse(sdate));
    }

    static String author(Document doc) {
        try {
            return new HeuristicString(null)
                    .or(StringUtils.innerTrim(doc.select("meta[property=article:author]").text()))
                    .or(StringUtils.innerTrim(doc.select("meta[name=article:author_name]").text()))
                    .or(StringUtils.innerTrim(doc.select("meta[itemprop=author]").attr("content")))
                    .or(StringUtils.innerTrim(doc.select("meta[name=blogger_name]").attr("content")))
                    .or(StringUtils.innerTrim(doc.select("meta[name=author]").attr("content")))
                    .or(StringUtils.innerTrim(doc.select("meta[property=article:author]").attr("content")))
                    .toString();
        } catch (HeuristicString.CandidateFound candidateFound) {
            return StringUtils.cleanTitle(candidateFound.candidate);
        }
    }

    private static String[] DATE_FORMATS = {"yyyy-MM-dd'T'hh:mm:ss","EEE MMM dd HH:mm:ss yyyy", "yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss", "yyyy-MM-dd"};

    static Date date(Document doc) {
        String sdate;
        try {
            sdate = new HeuristicString(null)
                    .or(doc.select("meta[property=article:published_time]").attr("content"))
                    .or(doc.select("meta[name=date]").attr("content"))
                    .or(doc.select("meta[name=DisplayDate]").attr("content"))
                    .or(doc.select("meta[itemprop=datePublished]").attr("datetime"))
                    .or(doc.select("meta[itemprop=datePublished]").attr("content"))
                    .or(doc.select("meta[name=published_at]").attr("content"))
                    .or(doc.select("meta[property=article:modified_time]").attr("content"))
                    .or(doc.select("time[itemprop=datePublished]").attr("content"))
                    .or(doc.select("meta[name=dcterms.created]").attr("content"))
                    .or(doc.select("head meta[name=article:author_name]").attr("content"))
                    .or(doc.select("meta[name=pdate]").attr("content"))
                    .or(doc.select("meta[property=og:updated_time]").attr("content"))
                    .or(doc.select("meta[name=timestamp]").attr("content"))
                    .or(doc.select("meta[property=article:published]").attr("content"))
                    .or(doc.select("meta[property=bt:pubDate]").attr("content"))
                    .toString();
        } catch (HeuristicString.CandidateFound candidateFound) {
            sdate = candidateFound.candidate;
        }

        if(sdate != null) {
            for (String fmt : DATE_FORMATS) {
                try {
                    return new SimpleDateFormat(fmt, Locale.ENGLISH).parse(sdate);
                } catch (ParseException e) {
                }
            }
        }
        return null;
    }

    static String image(Document doc) {
        try {
            return new HeuristicString(null)
                    .or(StringUtils.urlEncodeSpaceCharacter(doc.select("meta[name=twitter:image]").attr("content")))
                    .or(StringUtils.urlEncodeSpaceCharacter(doc.select("meta[property=og:image]").attr("content")))
                    .or(StringUtils.urlEncodeSpaceCharacter(doc.select("link[rel=image_src]").attr("href")))
                    .or(StringUtils.urlEncodeSpaceCharacter(doc.select("meta[name=thumbnail]").attr("content")))
                    .or(StringUtils.urlEncodeSpaceCharacter(doc.select("meta[itemprop=image]").attr("content")))
                    .toString();
        } catch (HeuristicString.CandidateFound candidateFound) {
            return candidateFound.candidate;
        }
    }

    static String title(Document doc) {
        try {
            return StringUtils.cleanTitle(new HeuristicString(doc.title())
                    .or(StringUtils.innerTrim(doc.select("head title").text()))
                    .or(StringUtils.innerTrim(doc.select("meta[name=title]").attr("content")))
                    .or(StringUtils.innerTrim(doc.select("meta[property=og:title]").attr("content")))
                    .or(StringUtils.innerTrim(doc.select("meta[name=twitter:title]").attr("content")))
                    .toString());
        } catch (HeuristicString.CandidateFound candidateFound) {
            return StringUtils.cleanTitle(candidateFound.candidate);
        }
    }

    static String description(Document doc) {
        try {
            return new HeuristicString(null)
                    .or(StringUtils.innerTrim(doc.select("meta[name=description]").attr("content")))
                    .or(StringUtils.innerTrim(doc.select("meta[property=og:description]").attr("content")))
                    .or(StringUtils.innerTrim(doc.select("meta[name=twitter:description]").attr("content")))
                    .toString();
        } catch (HeuristicString.CandidateFound candidateFound) {
            return candidateFound.candidate;
        }
    }

    static Collection<String> keywords(Document doc) {
        String content = StringUtils.innerTrim(doc.select("meta[name=keywords]").attr("content"));
        if (content.startsWith("[") && content.endsWith("]"))
            content = content.substring(1, content.length() - 1);

        String[] split = content.split("\\s*,\\s*");
        if (split.length > 1 || (split.length > 0 && !"".equals(split[0])))
            return Arrays.asList(split);

        return Collections.emptyList();
    }

}
