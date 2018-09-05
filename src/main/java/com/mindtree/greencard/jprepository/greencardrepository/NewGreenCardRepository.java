package com.mindtree.greencard.jprepository.greencardrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.greencard.model.NewGreenCard;

public interface NewGreenCardRepository extends JpaRepository<NewGreenCard, Integer> {

}
