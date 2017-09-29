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


import java.nio.file.Paths;
import java.util.Scanner;

import org.hawk.measure.model.HawkQuerySMMMMeasure;
import org.measure.smm.service.MeasurePackager;

public class HawkMeasurePackager {

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println(">> Enter JAR path: ");
		String jarPath = reader.nextLine(); // Scans the next token of the input as an int.
		System.out.println(">> Enter LIB folder path: ");
		String libPath = reader.nextLine(); // Scans the next token of the input as an int.
		System.out.println(">> Enter ZIP file location: ");
		String zipPath = reader.nextLine(); // Scans the next token of the input as an int.

		HawkQuerySMMMMeasure measure = new HawkQuerySMMMMeasure();
		
		try {
			MeasurePackager.packageMeasure(Paths.get(jarPath),
					Paths.get(libPath),
					measure,
					Paths.get(zipPath));
			
			
			System.out.println("Successfully Created " + zipPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
