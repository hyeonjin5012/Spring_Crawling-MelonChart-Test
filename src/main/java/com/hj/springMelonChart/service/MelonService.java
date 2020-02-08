package com.hj.springMelonChart.service;

import java.util.ArrayList;
import java.util.List;

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
			Document doc = Jsoup.connect("https://www.melon.com/chart/index.htm").get();
			Elements ranks = doc.select(".wrap .rank");
			Elements titles = doc.select(".wrap_song_info .rank01 a");
			Elements singers_els = doc.select(".wrap_song_info .rank02");
			String singer = null;
			ArrayList<String> singers = new ArrayList<String>();
			
			for (Element el : singers_els) {
				singer = el.select("span a").text();
				System.out.println(singer);
				singers.add(singer);

			}
			Elements photos = doc.select(".image_typeAll img");
			Melon melon = new Melon();

			for (int i = 0; i < ranks.size() - 1; i++) {

				melon.setRanking(Integer.parseInt(ranks.get(i + 1).text()));
				melon.setTitle(titles.get(i).text());
				melon.setSinger(singers.get(i));
				melon.setPhoto(photos.get(i).attr("src"));

				result = melonRepository.save(melon);

			}

		} catch (java.io.IOException e) { 
			e.printStackTrace();
		}
		return result;

	}
}
