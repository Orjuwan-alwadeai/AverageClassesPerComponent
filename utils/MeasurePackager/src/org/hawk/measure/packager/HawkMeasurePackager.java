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
import java.util.Scanner;

import org.hawk.measure.model.HawkQuerySMMMMeasure;
import org.measure.smm.service.MeasurePackager;

public class HawkMeasurePackager {

	public static void main(String[] args) {
		
		Boolean doContinue = true; 

		while(doContinue) {
			Scanner reader = new Scanner(System.in);  // Reading from System.in

			
			System.out.println(">> Enter Measure Name: ");
			String name = reader.nextLine(); // Scans the next token of the input as an int.
			System.out.println(">> Enter Measaure Version: ");
			String version = reader.nextLine(); 
			System.out.println(">> Enter target path: ");
			String targetPath = reader.nextLine(); 
			System.out.println(">> Enter Scope Properties to add to default (comma seperated): ");
			String scopeProperties = reader.nextLine(); 
			System.out.println(">> Enter Scope Properties to remove from default (comma seperated): ");
			String toRemoveScopeProperties = reader.nextLine(); 
			
			
			HawkQuerySMMMMeasure measure = new HawkQuerySMMMMeasure();
	
			measure.setName(name);
			
			if(scopeProperties != null && !scopeProperties.isEmpty()){
				String[] list = scopeProperties.split(",");
				for(int i = 0; i < list.length; i++) {
					String prop = list[i].trim();
					measure.addScopeProperty(prop, "", "");
				}
			}
			
			if(toRemoveScopeProperties != null && !toRemoveScopeProperties.isEmpty()){
				String[] list = toRemoveScopeProperties.split(",");
				for(int i = 0; i < list.length; i++) {
					String prop = list[i].trim();
					measure.removeScopeProperty(prop);
				}
			}
			Paths.get(targetPath, name+"-"+version+".jar");
			Paths.get(targetPath, "lib");
			try {
				MeasurePackager.packageMeasure(Paths.get(targetPath, name+"-"+version+".jar"),
						Paths.get(targetPath, "lib"),
						measure,
						Paths.get(targetPath, name+"-"+version+".zip"));
				
				
				System.out.println("Successfully Created '" + name + "-" +version + ".zip'" + "\n at "+ targetPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			System.out.println(">> Press Any key to Continue, type 'quit' to exit: ");
			String input = reader.next();
			if(input.equalsIgnoreCase("quit")) {
				doContinue = false;
				System.out.println(">> Program is quiting ");

			}
			
			
		}
	}
}
