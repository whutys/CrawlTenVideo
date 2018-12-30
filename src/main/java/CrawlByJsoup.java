
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CrawlByJsoup {

    public static String getTitle(String url) {
        if (url.replaceFirst("https?://", "").startsWith("m.")) {
            url = url.replaceFirst("m.", "");
        }
        try {
            Document document = Jsoup.connect(url).get();
            String title = document.title();
            return title;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void test() {
        String url = null;
        url = "http://v.qq.com/x/cover/nphyo88qm4m6i6s.html?vid=v0028cwf3bv";
//        try {
//            url = "https://v.qq.com/x/search/?q=" + URLEncoder.encode("闪电侠第四季", "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        System.out.println(CrawlByJsoup.getTitle(url));
    }

    public static Map<String, String> crawlTencentVideo(String url) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        try {
            Document document = Jsoup.connect(url).get();
//            String title = document.title();
//            map.put("title",title);
            Element element = document.selectFirst(".result_episode_list");
            if (element==null)return null;
            Elements div_a = element.select("div a");
            for (Element a :
                    div_a) {

                String href = a.attr("href");
                String text = a.text();
                map.put(text, href);
            }

            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
