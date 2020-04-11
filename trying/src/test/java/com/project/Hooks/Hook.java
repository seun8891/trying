package com.project.Hooks;

import com.project.Helper.BaseClass;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook extends BaseClass 
{
	@Before
	public void setup() throws Exception
	{
		launchBrowser("Chrome");
	}
	
	@After
	public void teardown() throws Exception
	{
		closeBrowser();
	}

}
