package com.mindtree.greencard.jprepository.savegreencardrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.greencard.model.GreenCardLifeCycle;

public interface SaveGreenCardRepository extends JpaRepository<GreenCardLifeCycle,Integer>{

}
