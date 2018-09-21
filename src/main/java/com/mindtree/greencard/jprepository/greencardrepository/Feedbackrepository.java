package com.mindtree.greencard.jprepository.greencardrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.greencard.entity.FeedBack;

public interface Feedbackrepository extends JpaRepository<FeedBack, Integer> {

}
