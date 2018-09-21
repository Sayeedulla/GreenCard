package com.mindtree.greencard.service.serviceimpl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.greencard.exception.superadminexceptions.CategoryNameAlreadyExists;
import com.mindtree.greencard.exception.superadminexceptions.CategoryNotFoundException;
import com.mindtree.greencard.exception.superadminexceptions.InvalidCategoryNameException;
import com.mindtree.greencard.exception.superadminexceptions.InvalidEmailFormatException;
import com.mindtree.greencard.exception.superadminexceptions.InvalidMidException;
import com.mindtree.greencard.exception.superadminexceptions.InvalidOperationException;
import com.mindtree.greencard.exception.superadminexceptions.InvalidTypeException;
import com.mindtree.greencard.exception.superadminexceptions.InvalidUserNameException;
import com.mindtree.greencard.exception.superadminexceptions.SuperAdminServiceException;
import com.mindtree.greencard.exception.superadminexceptions.UserNotFoundException;
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
	private SuperAdminHistoryRepo superAdminHistoryRepo;

	private static final String invalidCategoryName = "Invalid Category Name";

	private static final String zone = "Asia/Calcutta";

	private static final String subadmin = "SubAdmin";

	private static final String namePattern = "^[A-Z][a-z]+([ ][A-Z][a-z]+)*$";

	private static final String invalidMid = "Invalid Mid";

	private static final String midPattern = "[Mm][1][0][0-9]{5}";

	public String updateUser(User user) throws SuperAdminServiceException {
		Optional<User> tempuser = this.userRepo.findUser(user.getMid());
		try {
			if (!tempuser.isPresent())
				throw new UserNotFoundException();
		} catch (UserNotFoundException exception) {
			throw new SuperAdminServiceException("User not Found");
		}
		try {
			if (!user.getMid().matches(midPattern)) {
				throw new InvalidMidException();
			}
		} catch (InvalidMidException exception) {
			throw new SuperAdminServiceException(invalidMid);
		}
		try {
			if (!user.getName().matches(namePattern))
				throw new InvalidUserNameException();
		} catch (InvalidUserNameException exception) {
			throw new SuperAdminServiceException("Format of Name is InValid");
		}
		try {
			if (!user.getEmailId()
					.matches("(([A-Za-z][a-z]*[.][A-Za-z][a-z]*[0-9]*)|([Mm][1-9][0-9]{6}))@mindtree.com"))
				throw new InvalidEmailFormatException();
		} catch (InvalidEmailFormatException exception) {
			throw new SuperAdminServiceException("Email Format is InValid");
		}
		try {
			if (!(user.getType().equals("User") || user.getType().equals("Admin") || user.getType().equals(subadmin)))
				throw new InvalidTypeException();
		} catch (InvalidTypeException exception) {
			throw new SuperAdminServiceException("Invalid Type");
		}
		try {
			if (!(tempuser.get().getName().equals(user.getName())
					&& tempuser.get().getEmailId().equals(user.getEmailId())))
				throw new InvalidOperationException();
		} catch (InvalidOperationException exception) {
			throw new SuperAdminServiceException("Name or EmailId does not match");
		}

		user.setUserId(tempuser.get().getUserId());
		SuperAdminHistory sh = new SuperAdminHistory();
		sh.setMid(user.getMid());
		String from = this.userRepo.getType(user.getMid());
		String type = from + "to" + user.getType();
		sh.setType(type);
		if (from.equals(subadmin)) {
			from = from + this.subAdminCategoryRepo.getOne(user.getMid());
		}
		sh.setWhatischanged("edit");
		sh.setTimelog(LocalDateTime.now(ZoneId.of(zone)));
		this.userRepo.save(user);
		if (user.getType().equals(subadmin)) {
			String str = from + "to" + user.getType() + " - "
					+ this.subAdminCategoryRepo.getOne(user.getMid()).getCategoryName();
			sh.setType(str);
		}
		this.superAdminHistoryRepo.save(sh);

		return user.getMid();

	}

	public List<User> getUsers() {

		return this.userRepo.findAll();
	}

	public String addCategory(Category category) throws SuperAdminServiceException {
		try {
			String categoryName = category.getCategoryName();
			if (!categoryName.matches(namePattern))
				throw new InvalidCategoryNameException();
			Category checkCategory = this.categoryRepo.getCategory(categoryName);
			if (checkCategory != null) {
				throw new CategoryNameAlreadyExists();
			}
			this.categoryRepo.save(category);
			SuperAdminHistory sh = new SuperAdminHistory();
			sh.setMid("-");
			String name = "category" + category.getCategoryName();
			sh.setType(name);
			sh.setWhatischanged("added");
			sh.setTimelog(LocalDateTime.now(ZoneId.of(zone)));
			this.superAdminHistoryRepo.save(sh);
			return category.getCategoryName();
		} catch (CategoryNameAlreadyExists exception) {
			throw new SuperAdminServiceException("Category name already exists");
		} catch (InvalidCategoryNameException exception) {
			throw new SuperAdminServiceException(invalidCategoryName);
		}
	}

	public String deleteCategory(String categoryName) throws SuperAdminServiceException {
		Category category;
		try {
			if (categoryName.equals("NOT ASSIGNED"))
				throw new InvalidOperationException();
		} catch (InvalidOperationException exception) {
			throw new SuperAdminServiceException("Default Category ,Operation Not Allowed");
		}
		try {
			if (!categoryName.matches(namePattern))
				throw new InvalidCategoryNameException();
		} catch (InvalidCategoryNameException exception) {
			throw new SuperAdminServiceException(invalidCategoryName);
		}

		try {
			category = this.categoryRepo.getCategory(categoryName);
			if (category == null) {
				throw new CategoryNotFoundException();
			}
		} catch (CategoryNotFoundException exception) {
			throw new SuperAdminServiceException("Category not present");
		}

		SuperAdminHistory sh = new SuperAdminHistory();
		sh.setMid("-");
		sh.setType("category");
		sh.setWhatischanged("deleted");
		sh.setTimelog(LocalDateTime.now(ZoneId.of(zone)));
		this.superAdminHistoryRepo.save(sh);
		this.categoryRepo.deleteById(category.getCategoryId());
		List<SubAdminCategory> list = this.subAdminCategoryRepo.getSubAdminCategories(categoryName);
		for (SubAdminCategory subAdminCategory : list) {
			subAdminCategory.setCategoryName("Not Assigned");
			this.subAdminCategoryRepo.save(subAdminCategory);
		}
		return categoryName;
	}

	public List<Category> getCategories() {
		return this.categoryRepo.findAll();
	}

	public String mapSubAdminToCategory(SubAdminCategory subAdminCategory) throws SuperAdminServiceException {
		try {
			if (!subAdminCategory.getMid().matches(midPattern)) {
				throw new InvalidMidException();
			}
		} catch (InvalidMidException exception) {
			throw new SuperAdminServiceException(invalidMid);
		}
		try {
			User user = this.userRepo.getUserByMid(subAdminCategory.getMid());
			if (user == null)
				throw new UserNotFoundException();
		} catch (UserNotFoundException exception) {
			throw new SuperAdminServiceException("User not Found");
		}
		try {
			if (subAdminCategory.getCategoryName().equals("Not Assigned"))
				throw new InvalidOperationException();
		} catch (InvalidOperationException exception) {
			throw new SuperAdminServiceException("Default Category ,Operation Not Allowed");
		}
		try {
			if (!subAdminCategory.getCategoryName().matches(namePattern))
				throw new InvalidCategoryNameException();
		} catch (InvalidCategoryNameException exception) {
			throw new SuperAdminServiceException(invalidCategoryName);
		}

		try {
			Category category = this.categoryRepo.getCategory(subAdminCategory.getCategoryName());
			if (category == null) {
				throw new CategoryNotFoundException();
			}
		} catch (CategoryNotFoundException exception) {
			throw new SuperAdminServiceException("Category not present");
		}
		this.subAdminCategoryRepo.save(subAdminCategory);
		return "SubAdmin Mapped";
	}

	@Override
	public String deleteMappedSubAdmin(String mid) throws SuperAdminServiceException {
		try {
			if (!mid.matches(midPattern)) {
				throw new InvalidMidException();
			}
		} catch (InvalidMidException exception) {
			throw new SuperAdminServiceException(invalidMid);
		}
		try {
			Optional<SubAdminCategory> subAdminCategory = this.subAdminCategoryRepo.findById(mid);
			if (!subAdminCategory.isPresent())
				throw new UserNotFoundException();
		} catch (UserNotFoundException exception) {
			throw new SuperAdminServiceException("SubAdmin not Found");
		}
		this.subAdminCategoryRepo.deleteById(mid);
		return mid;
	}

	@Override
	public List<SubAdminCategory> getMappedSubAdmins() {
		return this.subAdminCategoryRepo.findAll();
	}

	@Override
	public List<SuperAdminHistory> getSuperAdminHistory() {

		return this.superAdminHistoryRepo.findAll();

	}

}
