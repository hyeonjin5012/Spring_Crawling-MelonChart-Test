package com.hj.springMelonChart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hj.springMelonChart.model.Melon;
import com.hj.springMelonChart.repository.MelonRepository;

@Service
public class MelonService {

	@Autowired
	private MelonRepository melonRepository;

	@Transactional
	public List<Melon> melonChart(int num) {

		return melonRepository.findAll(num);

	}

	@Transactional
	public int melonSave() {
		int result = 0;
		try {
			Document docMelon = Jsoup.connect("https://www.melon.com/chart/index.htm").get();
			Elements ranks = docMelon.select(".wrap .rank");
			Elements titles = docMelon.select(".wrap_song_info .rank01 a");
			Elements singers_els = docMelon.select(".wrap_song_info .rank02");
			String singer = null;
			ArrayList<String> singers = new ArrayList<String>();

			for (Element el : singers_els) {
				singer = el.select("span a").text();
//				System.out.println(singer);
				singers.add(singer);

			}
			Elements photos = docMelon.select(".image_typeAll img");
			Melon melon = new Melon();

			for (int i = 0; i < ranks.size() - 1; i++) {

				melon.setRanking(Integer.parseInt(ranks.get(i + 1).text()));
				melon.setTitle(titles.get(i).text());
				melon.setSinger(singers.get(i));
				melon.setPhoto(photos.get(i).attr("src"));
				String title = titles.get(i).text();
				title = title.replace(" ", "+");
				title = title.replace("(", "%28");
				title = title.replace(")", "%29");
				title = title.replace(",", "%2C");

				Document docYoutube = Jsoup.connect("https://www.youtube.com/results?search_query=" + title + "-MV")
						.get();
				Elements musicVideoLink_el = docYoutube.select("h3.yt-lockup-title a");
				String musicVideoLink = musicVideoLink_el.attr("href");
//				Elements a = docYoutube.select("#thumbnail");
//				String s = a.attr("href");
//				System.out.println(s);
				String musicLink = musicVideoLink.substring(musicVideoLink.indexOf("=") + 1, musicVideoLink.length());
				if (musicLink.equals("")) {
					musicLink = "널너러어ㅓ러";
					String singer2 = singers.get(i);
					singer2 = singer2.replace(" ", "+");
					singer2 = singer2.replace("(", "%28");
					singer2 = singer2.replace(")", "%29");
					singer2 = singer2.replace(",", "%2C");
					Document docYoutube2 = Jsoup
							.connect("https://www.youtube.com/results?search_query=" + title + "+" + singer2).get();
					Elements musicVideoLink_el2 = docYoutube.select("h3.yt-lockup-title a");
					String musicVideoLink2 = musicVideoLink_el2.attr("href");
					String musicLink2 = musicVideoLink2.substring(musicVideoLink.indexOf("=") + 1,
							musicVideoLink2.length());
					System.out.println(musicVideoLink2);
					System.out.println("1:" + ranks.get(i + 1).text() + " : " + title + " : " + musicLink2);

				} else {
					System.out.println(musicVideoLink);
					System.out.println("2:" + ranks.get(i + 1).text() + " : " + title + " : " + musicLink);
				}

			}

		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return result;

	}

	@Transactional
	public int melonMusic(String title) {
		int result = 0;

		try {
			Document doc = Jsoup.connect("https://www.youtube.com/results?search_query=" + title + "-MV").get();
//			Document doc = Jsoup.connect("https://www.youtube.com/results").data("search_query", "아무노래-MV").post();
			Elements musicVideoLink_el = doc.select("h3.yt-lockup-title a");
//			Elements musicLink=doc.select("#video-title");

			String musicVideoLink = musicVideoLink_el.attr("href");

			StringTokenizer str = new StringTokenizer(musicVideoLink, "=");
			str.nextToken();
			String musicLink = str.nextToken();
			System.out.println(musicLink);

		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return result;

	}
}
