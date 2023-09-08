package Programmers.LV3;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 매칭_점수 {
    class Solution {

        HashMap<String, Link> Links;

        public int solution(String word, String[] pages) {
            Links = new HashMap<>();

            Pattern home_url_pattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
            Pattern url_pattern = Pattern.compile("<a href=\"https://(\\S*)\"");
            Pattern word_pattern = Pattern.compile("\\b(?i)" + word + "\\b");
            Matcher url_matcher, word_matcher, home_url_matcher;

            ArrayList<String> ext_urls;
            Link new_Link;
            String body;
            String home_url = "";

            for (int i = 0; i < pages.length; i++) {
                url_matcher = url_pattern.matcher(pages[i]);
                home_url_matcher = home_url_pattern.matcher(pages[i]);

                if (home_url_matcher.find()) {
                    home_url = home_url_matcher.group().split("=")[2].replaceAll("\"", "");
                }

                new_Link = new Link(i, home_url);
                ext_urls = new ArrayList<>();

                while (url_matcher.find()) {
                    ext_urls.add(url_matcher.group().split("\"")[1]);
                }
                new_Link.setExtUrls(ext_urls);


                body = pages[i].split("<body>")[1].split("</body>")[0].replaceAll("[0-9]", " ");
                word_matcher = word_pattern.matcher(body);
                int word_cnt = 0;
                while (word_matcher.find()) word_cnt++;

                new_Link.point = word_cnt;
                new_Link.setLinkPoint();

                Links.put(new_Link.url, new_Link);
            }

            for (Link link: Links.values()) {
                for (String ext_url: link.ext_urls)  {
                    if (Links.containsKey(ext_url)) {
                        Links.get(ext_url).point += link.link_point;
                    }
                }
            }

            double max_point = 0;
            int answer = 0;
            for (Link link : Links.values()) {
                if (link.point == max_point) {
                    if (link.idx < answer) answer = link.idx;
                } else if (link.point > max_point) {
                    answer = link.idx;
                    max_point = link.point;
                }
            }


            return answer;
        }

        class Link {
            String url;
            String[] ext_urls;
            double point, link_point;
            int idx;

            public Link(int idx, String url) {
                this.idx = idx;
                this.url = url;
            }

            public void setExtUrls(ArrayList<String> urls) {
                this.ext_urls = new String[urls.size()];
                for (int i = 0; i < urls.size(); i++) {
                    ext_urls[i] = urls.get(i);
                }
            }

            public void setLinkPoint() {
                this.link_point = this.point / ext_urls.length;
            }
        }
    }
}
