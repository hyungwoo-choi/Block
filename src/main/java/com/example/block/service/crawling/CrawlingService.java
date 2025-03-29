package com.example.block.service.crawling;

import com.example.block.domain.Contest;
import com.example.block.repository.ContestRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CrawlingService {

    private final ContestRepository contestRepository;
    private static final List<String> urls = Arrays.asList(
//            it
            "https://www.contestkorea.com/sub/list.php?displayrow=12&int_gbn=1&Txt_sGn=1&Txt_key=all&Txt_word=&Txt_bcode=030510001&Txt_code1=&Txt_aarea=&Txt_area=&Txt_sortkey=a.int_sort&Txt_sortword=desc&Txt_host=&Txt_award=&Txt_award2=&Txt_code3=&Txt_tipyn=&Txt_comment=&Txt_resultyn=&Txt_actcode=&page=5",
//            경시 학문 논문
            "https://www.contestkorea.com/sub/list.php?displayrow=12&int_gbn=1&Txt_sGn=1&Txt_key=all&Txt_word=&Txt_bcode=030310001&Txt_code1=&Txt_aarea=&Txt_area=&Txt_sortkey=a.int_sort&Txt_sortword=desc&Txt_host=&Txt_award=&Txt_award2=&Txt_code3=&Txt_tipyn=&Txt_comment=&Txt_resultyn=&Txt_actcode=&page=5",
//            산업 사회 건축 관광 창업
            "https://www.contestkorea.com/sub/list.php?displayrow=12&int_gbn=1&Txt_sGn=1&Txt_key=all&Txt_word=&Txt_bcode=031510001&Txt_code1=&Txt_aarea=&Txt_area=&Txt_sortkey=a.int_sort&Txt_sortword=desc&Txt_host=&Txt_award=&Txt_award2=&Txt_code3=&Txt_tipyn=&Txt_comment=&Txt_resultyn=&Txt_actcode=&page=5"
    );

    public List<String> getContestPage(String url) throws IOException {
        List<String> newContestPages = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();

        try {
            Elements pages = doc.select("#frm > div > div.pagination > ul > li > a");
            for (Element page : pages) {
                String href = page.attr("href");
                newContestPages.add("https://www.contestkorea.com/sub/list.php" + href);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        newContestPages.remove(4); // 이 부분은 필요에 따라 수정하세요.
        System.out.println(newContestPages);
        return newContestPages;
    }

    public List<String> getContestURL() throws IOException {
        List<String> newContestURLs = new ArrayList<>();
        for (String url : urls) {
            List<String> newContestPages = getContestPage(url);
            for (String newContestPage : newContestPages) {
                Document doc = Jsoup.connect(newContestPage).get();
                Elements contests = doc.select("#frm > div > div.list_style_2 > ul > li > div.title > a");
                for (Element contest : contests) {
                    String href = contest.attr("href");
                    newContestURLs.add("https://www.contestkorea.com/sub/" + href);
                }
            }
        }
        System.out.println(newContestURLs);
        return newContestURLs;
    }

//        @PostConstruct
    public void saveContest() throws IOException {
        List<String> newContestURLs = getContestURL();
        for (String newContestURL : newContestURLs) {
            Document doc = Jsoup.connect(newContestURL).get();
            Elements contestTitle = doc.select("#wrap > div.container.list_wrap > div.left_cont > div.view_cont_area > div.view_top_area.clfx > h1");
            Elements startDate = doc.select("#wrap > div.container.list_wrap > div.left_cont > div.view_cont_area > div.view_top_area.clfx > div.clfx > div.txt_area > table > tbody > tr:nth-child(4) > td");
            Elements endDate = doc.select("#wrap > div.container.list_wrap > div.left_cont > div.view_cont_area > div.view_top_area.clfx > div.clfx > div.txt_area > table > tbody > tr:nth-child(4) > td");
            Elements applyUrl = doc.select("#wrap > div.container.list_wrap > div.left_cont > div.view_cont_area > div.view_top_area.clfx > div.clfx > div.txt_area > table > tbody > tr > td > a");
            Elements host = doc.select("#wrap > div.container.list_wrap > div.left_cont > div.view_cont_area > div.view_top_area.clfx > div.clfx > div.txt_area > table > tbody > tr:nth-child(1) > td");
            Elements image = doc.select("#wrap > div.container.list_wrap > div.left_cont > div.view_cont_area > div.view_top_area.clfx > div.clfx > div.img_area > div > img");
            Elements hashtag = doc.select("#wrap > div.container.list_wrap > div.left_cont > div.view_cont_area > div.view_top_area.clfx > div.clfx > div.txt_area > table > tbody > tr:nth-child(2) > td");

            String iHref = image.attr("src");
            String href = applyUrl.attr("href");

            System.out.println(contestTitle.text());
            System.out.println(host.text());
            System.out.println(startDate.text().substring(0, 10));
            System.out.println(endDate.text().substring(13, 23));
            System.out.println(hashtag.text());
            System.out.println(href);
            System.out.println("------------------");

            if (!contestRepository.existsByTitle(contestTitle.text())) {
                Contest contest = Contest.builder()
                        .startDate(startDate.text().substring(0, 10))
                        .endDate(endDate.text().substring(13, 23))
                        .applyUrl(href)
                        .title(contestTitle.text())
                        .host(host.text())
                        .hashTag(hashtag.text())
                        .imageUrl("https://www.contestkorea.com/" + iHref)
                        .build();
//                if(hashtag.text() == ){
//                }
//                contest.setContestCategory();

                contestRepository.save(contest);
            }
        }
    }
}
