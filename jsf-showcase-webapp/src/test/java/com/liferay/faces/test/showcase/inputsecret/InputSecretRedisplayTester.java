/**
 * Copyright (c) 2000-2016 Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.faces.test.showcase.inputsecret;

import org.junit.Test;

import org.openqa.selenium.WebElement;

import com.liferay.faces.test.showcase.Browser;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputSecretRedisplayTester extends InputSecretTester {

	@Test
	public void runInputSecretRedisplayTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.navigateToURL(inputSecretURL + "redisplay");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButtonXpath);

		// Test that the value submits successfully and the alloy:inputSecret component is intentionally
		// not re-rendered in the DOM.
		WebElement input = browser.getElement(inputSecretXpath);
		String text = "Hello World!";
		input.sendKeys(text);
		browser.performAndWaitForAjaxRerender(browser.createClickAction(submitButtonXpath), modelValueXpath);
		browser.assertElementTextVisible(modelValueXpath, text);

		String redisplayMessage = "//td[contains(text(),'was intentionally not re-rendered')]";
		browser.assertElementVisible(redisplayMessage);

		// Test that the value submits successfully and the entire form (including the alloy:inputSecret component)
		// is re-rendered in the DOM.
		input = browser.getElement(inputSecretXpathRight);
		input.sendKeys(text);
		browser.clickAndWaitForAjaxRerender(submitButtonXpathRight);
		browser.assertElementTextVisible(modelValueXpathRight, text);

		String redisplayMessageRight = "//td[contains(text(),'entire form')]";
		browser.assertElementVisible(redisplayMessageRight);
	}
}