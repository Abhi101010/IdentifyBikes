package com.openkart.utils;


import com.opencart.base.Base;

public class Utils extends Base {

	public static final long PAGE_LOAD_TIMEOUT = 20;
	public static final long IMPLICITLY_WAIT = 20;
	
	//******************************************Return Title****************************************
	public static String getTite() {
		return driver.getTitle();
	}
	
	//******************************************Return URL****************************************
	public static String getUrl() {
		return driver.getCurrentUrl();
	}
	
	//******************************************Navigate Back****************************************
	public static void navigateBack() {
		driver.navigate().back();
	}
}
