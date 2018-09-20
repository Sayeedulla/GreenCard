package com.mindtree.ServiceImpl.superAdminServiceImplTestCases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses({
	UpdateUserServiceImplTest.class,
	GetUsersServiceImplTest.class,
	AddCategoryServiceImplTest.class,
	DeleteCategoryServiceImplTest.class,
	GetCategoriesServiceImplTest.class,
	GetMappedSubAdminsServiceImplTest.class,
	GetSuperAdminHistoryServiceImplTest.class,
	DeleteMappedSubAdminServiceImplTest.class,
	MapSubAdminToCategoryServiceImplTest.class
})

public class SuperAdminJunitServiceImplTestSuite {

}
