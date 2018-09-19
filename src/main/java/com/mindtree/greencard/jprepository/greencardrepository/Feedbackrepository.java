package com.mindtree.greencard.jprepository.greencardrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.greencard.model.FeedBack;

public interface Feedbackrepository extends JpaRepository<FeedBack, Integer> {

}
