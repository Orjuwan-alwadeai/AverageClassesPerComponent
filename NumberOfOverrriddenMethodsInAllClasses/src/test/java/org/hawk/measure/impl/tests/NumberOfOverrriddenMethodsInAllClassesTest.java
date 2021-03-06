
/*******************************************************************************
 * Copyright (c) 2017 Aston University
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Orjuwan Al-Wadeai - Hawk Query SMMM Measure Implementation
 ******************************************************************************/
package org.hawk.measure.impl.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.hawk.measure.impl.NumberOfOverrriddenMethodsInAllClasses;
import org.hawk.measure.impl.HawkQueryConstants;
import org.junit.Test;
import org.measure.smm.measure.api.IMeasurement;
import org.measure.smm.measure.defaultimpl.measurements.IntegerMeasurement;


public class NumberOfOverrriddenMethodsInAllClassesTest {
	final static String serverUrl = "http://localhost:8080/thrift/hawk/tuple";
	final static String instanceName = "instance_1";

	@Test
	public void testAverageNumberOfClassesInAComponent() throws Exception {
		System.out.println("\n\n NumberOfOverrriddenMethodsInAllClassesTest *****");
		NumberOfOverrriddenMethodsInAllClasses measure = new NumberOfOverrriddenMethodsInAllClasses();

		measure.getProperties().put(HawkQueryConstants.SERVER_URL, serverUrl);
		measure.getProperties().put(HawkQueryConstants.INSTANCE_NAME, instanceName);
		measure.getProperties().put(HawkQueryConstants.DEFAULT_NAMESPACES, "modelio://Modeliosoft.Standard/2.0.00");
		
		List<IMeasurement> measurements = measure.getMeasurement();	

		// checks
		assertNotNull(measurements);
		assertNotNull(measurements.get(0));
		assertTrue(measurements.get(0) instanceof IntegerMeasurement);
		
		System.out.println(measurements.get(0).getLabel());
	}
}
