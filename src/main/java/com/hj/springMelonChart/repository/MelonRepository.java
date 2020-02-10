package com.hj.springMelonChart.repository;

import java.util.List;

import com.hj.springMelonChart.model.Melon;

public interface MelonRepository {

	List<Melon> findAll(int num);

	int save(Melon melon);

}
