/*
 *  Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.jaggery.integration.tests.hostObjects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.Test;
import org.wso2.carbon.integration.framework.ClientConnectionUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test cases for Collection Object
 */
public class CollectionHostObjectTestCase {

	private static final Log log = LogFactory
			.getLog(CollectionHostObjectTestCase.class);

	@Test(groups = { "jaggery" }, description = "Test Collection object")
	public void collectionHostObjectExsit() {
		log.info("*****Inside Collection host object test in jaggery Test*****");
		ClientConnectionUtil.waitForPort(9763);

		String finalOutput = null;

		try {
			URL jaggeryURL = new URL(
					"http://localhost:9763/testapp/collection.jag");
			URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					jaggeryServerConnection.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				finalOutput = inputLine;
			}

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			assertNotNull(finalOutput, "Result cannot be null");
		}

	}

	@Test(groups = { "jaggery" }, description = "Test collection members")
	public void collectionMembers() {
		ClientConnectionUtil.waitForPort(9763);

		String finalOutput = null;

		try {
			URL jaggeryURL = new URL(
					"http://localhost:9763/testapp/collection.jag?action=members");
			URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					jaggeryServerConnection.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				finalOutput = inputLine;
			}

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			assertEquals(finalOutput,"3");
		}

	}

	@Test(groups = { "jaggery" }, description = "Test collection operations")
	public void collectionReadOperations() throws JSONException {
		ClientConnectionUtil.waitForPort(9763);

		String finalOutput = null;

		try {
			URL jaggeryURL = new URL(
					"http://localhost:9763/testapp/collection.jag");
			URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					jaggeryServerConnection.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				finalOutput = inputLine;
			}

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			JSONAssert.assertEquals("[\"/wso2products/AM.xml\", \"/wso2products/GS.xml\","
                    + " \"/wso2products/jaggery.xml\"]", finalOutput, true);
		}

	}


}
