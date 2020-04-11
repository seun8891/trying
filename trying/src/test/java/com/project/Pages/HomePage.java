package com.project.Pages;

import org.openqa.selenium.WebElement;

import com.project.Helper.BaseClass;

public class HomePage extends BaseClass 
{
	private WebElement email;
	
	public void andenteremail() throws Exception
	{
		email= getElementById("email");
		email.sendKeys("seunakinbode@yahoo.com");
	}

}
