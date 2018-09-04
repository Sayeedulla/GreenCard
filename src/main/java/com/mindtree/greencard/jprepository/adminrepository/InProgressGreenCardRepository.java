package com.mindtree.greencard.jprepository.adminrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.greencard.model.InProgressGreenCard;


@Repository
public interface InProgressGreenCardRepository extends JpaRepository<InProgressGreenCard, Integer>{

}
