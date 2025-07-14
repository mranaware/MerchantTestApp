package com.webplat.MerchantTestApp;

import static org.testng.Assert.assertTrue;

import org.testng.TestNG;

import com.aventstack.extentreports.model.Test;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestNG {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super();
	}

	/**
	 * @return the suite of tests being tested
	 */

	public static Test suite() {
		return new Test();
	}

	/**
	 * Rigorous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}
}
