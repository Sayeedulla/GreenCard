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
import com.mindtree.greencard.entity.Category;
import com.mindtree.greencard.entity.SubAdminCategory;
import com.mindtree.greencard.entity.SuperAdminHistory;
import com.mindtree.greencard.entity.User;
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

	private static final String INVALID_CATEGORY_NAME = "Invalid Category Name";
	private static final String ZONE = "Asia/Calcutta";
	private static final String SUBADMIN = "SubAdmin";
	private static final String ADMIN = "Admin";
	private static final String USER = "User";
	private static final String NAME_PATTERN = "^[A-Z][a-z]+([ ][A-Z][a-z]+)*$";
	private static final String MID_PATTERN = "[Mm][1][0][0-9]{5}";
	private static final String EMAIL_PATTERN = "(([A-Za-z][a-z]*[.][A-Za-z][a-z]*[0-9]*)|([Mm][1-9][0-9]{6}))@mindtree.com";
	private static final String INVALID_MID = "Invalid Mid";
	private static final String INVALID_USER = "User not Found";
	private static final String INVALID_NAME = "Format of Name is Invalid";
	private static final String INVALID_EMAIL = "Email Format is Invalid";
	private static final String INVALID_TYPE = "Invalid Type";
	private static final String INVALID_NAME_EMAIL = "Name or EmailId does not match";

	public String updateUser(User user) throws SuperAdminServiceException {
		Optional<User> tempuser = this.userRepo.findUser(user.getMid());
		try {
			if (!tempuser.isPresent()) {
				throw new UserNotFoundException(INVALID_USER);
			}

			else if (!user.getMid().matches(MID_PATTERN)) {
				throw new InvalidMidException(INVALID_MID);
			}

			else if (!user.getName().matches(NAME_PATTERN)) {
				throw new InvalidUserNameException(INVALID_NAME);
			}

			else if (!user.getEmailId().matches(EMAIL_PATTERN)) {
				throw new InvalidEmailFormatException(INVALID_EMAIL);
			}

			else if (!(user.getType().equals(USER) || user.getType().equals(ADMIN)
					|| user.getType().equals(SUBADMIN))) {
				throw new InvalidTypeException(INVALID_TYPE);
			}

			else if (!(tempuser.get().getName().equals(user.getName())
					&& tempuser.get().getEmailId().equals(user.getEmailId()))) {
				throw new InvalidOperationException(INVALID_NAME_EMAIL);
			}

		} catch (UserNotFoundException | InvalidMidException | InvalidUserNameException | InvalidEmailFormatException | InvalidTypeException |InvalidOperationException exception) {
			throw new SuperAdminServiceException(exception.getMessage());
		} 	
		user.setUserId(tempuser.get().getUserId());
		SuperAdminHistory sh = new SuperAdminHistory();
		sh.setMid(user.getMid());
		String from = this.userRepo.getType(user.getMid());
		String type = from + "to" + user.getType();
		sh.setType(type);
		if (from.equals(SUBADMIN)) {
			from = from + this.subAdminCategoryRepo.getOne(user.getMid());
		}
		sh.setWhatischanged("edit");
		sh.setTimelog(LocalDateTime.now(ZoneId.of(ZONE)));
		this.userRepo.save(user);
		if (user.getType().equals(SUBADMIN)) {
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
			if (!categoryName.matches(NAME_PATTERN))
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
			sh.setTimelog(LocalDateTime.now(ZoneId.of(ZONE)));
			this.superAdminHistoryRepo.save(sh);
			return category.getCategoryName();
		} catch (CategoryNameAlreadyExists exception) {
			throw new SuperAdminServiceException("Category name already exists");
		} catch (InvalidCategoryNameException exception) {
			throw new SuperAdminServiceException(INVALID_CATEGORY_NAME);
		}
	}

	public String deleteCategory(String categoryName) throws SuperAdminServiceException {
		Category category;
		try {
			if (categoryName.equals("NOT ASSIGNED"))
				throw new InvalidOperationException();

			else if (!categoryName.matches(NAME_PATTERN))
				throw new InvalidCategoryNameException();

			category = this.categoryRepo.getCategory(categoryName);
			if (category == null) {
				throw new CategoryNotFoundException();
			}

		} catch (InvalidOperationException exception) {
			throw new SuperAdminServiceException("Default Category ,Operation Not Allowed");
		} catch (InvalidCategoryNameException exception) {
			throw new SuperAdminServiceException(INVALID_CATEGORY_NAME);
		} catch (CategoryNotFoundException exception) {
			throw new SuperAdminServiceException("Category not present");
		}

		SuperAdminHistory sh = new SuperAdminHistory();
		sh.setMid("-");
		sh.setType("category");
		sh.setWhatischanged("deleted");
		sh.setTimelog(LocalDateTime.now(ZoneId.of(ZONE)));
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
			if (!subAdminCategory.getMid().matches(MID_PATTERN))
				throw new InvalidMidException();

			User user = this.userRepo.getUserByMid(subAdminCategory.getMid());
			if (user == null)
				throw new UserNotFoundException();

			else if (subAdminCategory.getCategoryName().equals("Not Assigned"))
				throw new InvalidOperationException();

			else if (!subAdminCategory.getCategoryName().matches(NAME_PATTERN))
				throw new InvalidCategoryNameException();

			Category category = this.categoryRepo.getCategory(subAdminCategory.getCategoryName());
			if (category == null)
				throw new CategoryNotFoundException();

		} catch (InvalidMidException exception) {
			throw new SuperAdminServiceException(INVALID_MID);
		} catch (UserNotFoundException exception) {
			throw new SuperAdminServiceException(INVALID_USER);
		} catch (InvalidOperationException exception) {
			throw new SuperAdminServiceException("Default Category ,Operation Not Allowed");
		} catch (InvalidCategoryNameException exception) {
			throw new SuperAdminServiceException(INVALID_CATEGORY_NAME);
		} catch (CategoryNotFoundException exception) {
			throw new SuperAdminServiceException("Category not present");
		}
		this.subAdminCategoryRepo.save(subAdminCategory);
		return "SubAdmin Mapped";
	}

	@Override
	public String deleteMappedSubAdmin(String mid) throws SuperAdminServiceException {
		try {
			if (!mid.matches(MID_PATTERN))
				throw new InvalidMidException();

			Optional<SubAdminCategory> subAdminCategory = this.subAdminCategoryRepo.findById(mid);
			if (!subAdminCategory.isPresent())
				throw new UserNotFoundException();

		} catch (InvalidMidException exception) {
			throw new SuperAdminServiceException(INVALID_MID);
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
