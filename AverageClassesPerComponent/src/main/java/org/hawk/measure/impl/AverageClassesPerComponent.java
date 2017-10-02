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
package org.hawk.measure.impl;


import java.util.List;
import java.util.Scanner;
import org.measure.smm.measure.api.IMeasurement;


public class AverageClassesPerComponent extends HawkQueryMeasure  {
	/*	private String queryString_orig =	"var components = Component.all;"
			+ "if(components.size() = 0) return 0;"
			+ "var num  = 0;"
			+ "for(cmp in components) "
			+ "{"
			+ "num = num + getAllClassesInComponent(cmp);"
			+ "}"
			+ "num = num/components.size();"
			+ "return num;" 
			+ "operation getAllClassesInComponent(cmp) {\n"
			+ "var num : Integer;\n"
			+ "if(cmp.hasProperty('hawkChildren')) {\n"
			+ "for(child in c.hawkChildren) {\n"
			+ "if(child.isTypeOf(Class)) {\n"
			+ "num = num + 1;\n"
			+ "}  if(child.hasProperty('hawkChildren')) {\n"
			+ "if(child.hawkChildren.size() > 0) {\n"
			+ "num = num + getAllClassesInComponent(child);\n"
			+ "}\n"
			+ "}\n"
			+ "}\n"
			+ "}\n"
			+ "return num;\n"
			+ "}\n";
	*/
	// query size reduced to be accepted by MEASURE platform
	// longer string was causing org.hibernate.exception.DataException
	private String queryString = "var cs = Component.all;"
//			+ "if(cs.size() = 0) return 0;"
			+ "var n  = 0;"
			+ "for(c in cs) "
			+ "{"
			+ "n = n + f(c);"
			+ "}"
			+"if(n=0) return 0;"
			+ "n = n/cs.size();"
			+ "return n;"
			+ "operation f(c) {\n"
			+ "var n : Integer;\n"
			+ "if(c.hasProperty('hawkChildren')) {\n"
			+ "for(ch in c.hawkChildren) {\n"
			+ "if(ch.isTypeOf(Class)) {\n"
			+ "n = n + 1;\n"
			+ "} if(ch.hasProperty('hawkChildren')) {\n"
			//+ "if(ch.hawkChildren.size() > 0) {\n"
			+ "n = n + f(ch);\n"
			//+ "}\n"
			+ "}\n"
			+ "}\n"
			+ "}\n"
			+ "return n;\n"
			+ "}\n";
	
	public List<IMeasurement> getMeasurement() throws Exception {
		setQuery();
		return super.getMeasurement();
	}
	
	private void setQuery() {
		StringBuffer buffer = new StringBuffer();
		Scanner sc = new Scanner(queryString);
		while(sc.hasNextLine()) {
			buffer.append(sc.nextLine());
			buffer.append(System.getProperty("line.separator"));
		}
		
		this.getProperties().put(HawkQueryConstants.QUERY, buffer.toString());
		this.getProperties().put(HawkQueryConstants.QUERY_LANGUAGE, "org.hawk.epsilon.emc.EOLQueryEngine");
	}
}
