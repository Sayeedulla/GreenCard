package com.mindtree.greencard.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.greencard.jprepository.superadminrepository.CategoryRepository;
import com.mindtree.greencard.jprepository.superadminrepository.SubAdminCategoryRepository;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.model.SubAdminCategory;
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
    
	public void updateUser(User user) {
		this.userRepo.save(user);
	}

	public List<User> getUsers() {
		return this.userRepo.findAll();
	}

	public Optional<User> getUser(String mid) {
		User user = this.userRepo.findUser(mid);
		return this.userRepo.findById(user.getUserId());
	}

	public String deleteUser(String mid) {
		User user = this.userRepo.findUser(mid);
		this.userRepo.deleteById(user.getUserId());
		if(user.getType().equals("SubAdmin"))
			this.subAdminCategoryRepo.deleteById(user.getMid());
		return user.getMid();

	}

	public String addCategory(Category category) {
		this.categoryRepo.save(category);
		return category.getCategory_Name();
	}

	public String deleteCategory(String Category_name) {
		Category category = this.categoryRepo.getCategory(Category_name);
		this.categoryRepo.deleteById(category.getCategory_Id());
		List<SubAdminCategory> list=this.subAdminCategoryRepo.getSubAdminCategories(Category_name);
		for(SubAdminCategory subAdminCategory:list)
			this.subAdminCategoryRepo.deleteById(subAdminCategory.getMid());
		return Category_name;
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
		Optional<SubAdminCategory> subAdminCategory= this.subAdminCategoryRepo.findById(mid);
		SubAdminCategory sub=subAdminCategory.get();
		return sub.getCategory_Name();
	}

}
