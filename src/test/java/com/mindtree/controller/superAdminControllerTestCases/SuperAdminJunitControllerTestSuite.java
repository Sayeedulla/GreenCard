package com.mindtree.controller.superAdminControllerTestCases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   UpdateUserTest.class,
   AddCategoryTest.class,
   DeleteCategoryTest.class,
   MapSubAdminToCategoryTest.class,
   DeleteMappedSubAdminTest.class,
   GetUsersTest.class,
   GetMappedSubAdminTest.class,
   GetCategoriesTest.class,
   GetSuperAdminHistoryTest.class
})

public class SuperAdminJunitControllerTestSuite {   
	
}  