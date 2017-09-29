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

package org.hawk.measure.packager;

import java.nio.file.Paths;

import org.hawk.measure.impl.HawkQueryConstants;
import org.hawk.measure.model.HawkQuerySMMMMeasure;
import org.measure.smm.service.MeasurePackager;

public class AverageClassesPerComponentPackager {
	public static final String JAR_RELATIVE_LOCATION = "/target/AverageClassesPerComponent-0.0.1.jar";
	public static final String ZIP_NAME = "/AverageClassesPerComponent.zip";

	public static void main(String[] args) {
		HawkQuerySMMMMeasure measure = new HawkQuerySMMMMeasure("AverageClassesPerComponent", "Average number of classes in a component");
		measure.removeScopeProperty(HawkQueryConstants.QUERY);
		measure.removeScopeProperty(HawkQueryConstants.QUERY_LANGUAGE);

		
		String PROJECTSPACE = System.getProperty("user.dir");
		
		try {
			MeasurePackager.packageMeasure(Paths.get(PROJECTSPACE + JAR_RELATIVE_LOCATION),
					Paths.get(PROJECTSPACE +"/target/lib"),
					measure,
					Paths.get(PROJECTSPACE + ZIP_NAME));
			
			
			System.out.println("Successfully Created " +  PROJECTSPACE + ZIP_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
