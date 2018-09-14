package com.mindtree.greencard.service.serviceimpl;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.greencard.jprepository.superadminrepository.CategoryRepository;
import com.mindtree.greencard.jprepository.superadminrepository.SubAdminCategoryRepository;
import com.mindtree.greencard.jprepository.superadminrepository.SuperAdminHistoryRepo;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.model.SubAdminCategory;
import com.mindtree.greencard.model.SuperAdminHistory;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.SuperAdminService;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private SubAdminCategoryRepository subAdminCategoryRepo;
	
	@Autowired
	private SuperAdminHistoryRepo SHR;
	

	public String addUser(User user) {
		Optional<User> tempuser = this.userRepo.findUser(user.getMid());
		if (!tempuser.isPresent()) {
			String sha256hex = DigestUtils.sha256Hex(user.getPassword());
			user.setPassword(sha256hex);
			SuperAdminHistory sh = new SuperAdminHistory();
			sh.setMid(user.getMid());
			sh.setType(user.getType());
			sh.setWhatischanged("save");
			sh.setTimelog(LocalDateTime.now(ZoneId.of("Asia/Calcutta")));
			this.SHR.save(sh);
			this.userRepo.save(user);
			return user.getMid();
		} else
			return null;
	}

	public void updateUser(User user) {
		Optional<User> tempuser = this.userRepo.findUser(user.getMid());
		if (tempuser.isPresent()) {
			
			user.setUserId(tempuser.get().getUserId());
			this.userRepo.save(user);
			SuperAdminHistory sh = new SuperAdminHistory();
			sh.setMid(user.getMid());
			String from= this.SHR.getMid(user.getMid());
			String type = from + "to" + user.getType();
			sh.setType(type);
			if(user.getType().matches("SubAdmin"))
			{
				String str=from + "to"+ user.getType()+ " - "+ getMappedCategory(user.getMid());
				sh.setType(str);
			}
			sh.setWhatischanged("edit");
			sh.setTimelog(LocalDateTime.now(ZoneId.of("Asia/Calcutta")));
			this.SHR.save(sh);
			
		}
	  
	}

	public List<User> getUsers() {
		return this.userRepo.findAll();
	}

	public String deleteUser(String mid) {
		Optional<User> user = this.userRepo.findUser(mid);
		if (user.isPresent()) {
			this.userRepo.deleteById(user.get().getUserId());
			SuperAdminHistory sh = new SuperAdminHistory();
			sh.setMid(mid);
			sh.setType(user.get().getType());
			sh.setWhatischanged("deleted");
			sh.setTimelog(LocalDateTime.now(ZoneId.of("Asia/Calcutta")));
			this.SHR.save(sh);
			return user.get().getMid();
		} else
			return null;
	}

	public String addCategory(Category category) {
		this.categoryRepo.save(category);
		SuperAdminHistory sh = new SuperAdminHistory();
		sh.setMid("-");
		sh.setType("category");
		sh.setWhatischanged("added");
		sh.setTimelog(LocalDateTime.now(ZoneId.of("Asia/Calcutta")));
		this.SHR.save(sh);
		return category.getCategoryName();
	}

	public String deleteCategory(String categoryName) {
		Category category = this.categoryRepo.getCategory(categoryName);
		SuperAdminHistory sh = new SuperAdminHistory();
		sh.setMid("-");
		sh.setType("category");
		sh.setWhatischanged("deleted");
		sh.setTimelog(LocalDateTime.now(ZoneId.of("Asia/Calcutta")));
		this.SHR.save(sh);
		this.categoryRepo.deleteById(category.getCategoryId());
		List<SubAdminCategory> list = this.subAdminCategoryRepo.getSubAdminCategories(categoryName);
		for (SubAdminCategory subAdminCategory : list)
		{
		  subAdminCategory.setCategoryName("NOT ASSIGNED");
		  this.subAdminCategoryRepo.save(subAdminCategory);
		}
		return categoryName;
	}

	public List<Category> getCategories() {
		return this.categoryRepo.findAll();
	}

	public void mapSubAdminToCategory(SubAdminCategory subAdminCategory) {
		
		this.subAdminCategoryRepo.save(subAdminCategory);
	}

	@Override
	public void deleteMappedSubAdmin(String mid) {
		this.subAdminCategoryRepo.deleteById(mid);
	}

	@Override
	public String getMappedCategory(String mid) {
		SubAdminCategory subAdminCategory = this.subAdminCategoryRepo.getOne(mid);
		return subAdminCategory.getCategoryName();
	}

	@Override
	public List<SubAdminCategory> getMappedSubAdmins() {
		return this.subAdminCategoryRepo.findAll();
	}

}
