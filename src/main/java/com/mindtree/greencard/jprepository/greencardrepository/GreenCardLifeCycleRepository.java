package com.mindtree.greencard.jprepository.greencardrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.greencard.model.GreenCardLifeCycle;

public interface GreenCardLifeCycleRepository extends JpaRepository<GreenCardLifeCycle, Integer> {

}