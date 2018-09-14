package com.mindtree.greencard.jprepository.superadminrepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.greencard.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "select u from User u where u.mid=:mid")
	public Optional<User> findUser(@Param("mid") String mid);

	@Query(value = "select u from User u where u.mid=:mid and u.password=:password")
	public User findUserbymidPassword(@Param("mid") String mid, @Param("password") String password);
	
	@Query(value= "select name from User u where u.mid=?1")
	public String getAssignedName(String mid);
	
	@Query(value= "select mid from User u where u.type='Admin'")
	public String getAdmin();
	
	@Query(value="select type from User u where u.mid=?1")
	public String getType(String string);
	
	
}
