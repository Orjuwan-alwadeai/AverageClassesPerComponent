
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

import org.hawk.measure.impl.HawkQueryConstants;
import org.hawk.measure.impl.NumberOfSubcomponentsInComponent;
import org.junit.Test;
import org.measure.smm.measure.api.IMeasurement;
import org.measure.smm.measure.defaultimpl.measurements.IntegerMeasurement;

public class NumberOfSubcomponentsInComponentTest {
	final static String serverUrl = "http://localhost:8080/thrift/hawk/tuple";
	final static String instanceName = "instance_1";
	final static String componentName = "A";

	@Test
	public void testNumberOfSubcomponentsInComponent() throws Exception {
		System.out.println("\n\n testNumberOfSubcomponentsInComponent *****");
		NumberOfSubcomponentsInComponent measure = new NumberOfSubcomponentsInComponent();

		measure.getProperties().put(HawkQueryConstants.SERVER_URL, serverUrl);
		measure.getProperties().put(HawkQueryConstants.INSTANCE_NAME, instanceName);
		measure.getProperties().put("componentName", componentName);
		List<IMeasurement> measurements = measure.getMeasurement();	

		// checks
//		assertNotNull(measurements);
//		assertNotNull(measurements.get(0));
//		assertTrue(measurements.get(0) instanceof IntegerMeasurement);
		
		System.out.println(measurements.get(0).getLabel());
	}
}
