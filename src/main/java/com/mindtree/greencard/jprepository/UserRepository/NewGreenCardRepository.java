package com.mindtree.greencard.jprepository.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.greencard.model.NewGreenCard;

public interface NewGreenCardRepository extends JpaRepository<NewGreenCard, Integer> {

}
