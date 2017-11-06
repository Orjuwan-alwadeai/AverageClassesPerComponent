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


public class NumberOfOverrriddenMethodsInAllClasses extends HawkQueryMeasure  {
	
	private String queryString = "var numOfAllOverridenOperations = 0;"
			+ "for(myClass in Class.all) {"
			+ "		numOfAllOverridenOperations = numOfAllOverridenOperations + getNumberOfOverriddenOperations(myClass);"
			+ "	}"
			+ "	return numOfAllOverridenOperations;"
			+ "	/******************************************************************************************************/	"
			+ "operation getNumberOfOverriddenOperations(myClass) {"
		
			+ "var numOfOverridenOperations = 0;"
		+ "var operationsNames : Sequence;	"
		+ "var operations = getAllOperationsOfSubTypesOf(myClass);"
		+ "operationsNames.addAll(operations.Name);"
		
		+ "for(myoperation in myClass.OwnedOperation) {"
			+ "if(operationsNames.includes(myoperation.Name)) {"
				+ "var operationsWithName = operations.select(t|t.Name = myoperation.Name);"

				+ "if(operationsWithName.size > 0) {	"
					+ "var operationsWithNameAndReturn = operationsWithName.select(t|operationsHaveSameReturn(t, myoperation));"		
					+ "var operationsWithNameAndReturnAndArgs = operationsWithNameAndReturn.select(t|operationsHaveSameArguments(t, myoperation));"

					+ "if(operationsWithNameAndReturnAndArgs.size > 0) {				"
						+ "numOfOverridenOperations = numOfOverridenOperations + 1;"
						+ "}"
						+ "}"
						+ "}"
						+ "}"
		+ "return numOfOverridenOperations ;"
		+ "}"

	/******************************************************************************************************/

	+ "operation getAllOperationsOfSubTypesOf(myClass) {"
		+ "var operations : Sequence;"

		+ "var subTypes =  getSubTypes(myClass).flatten();"

		+ "for(subType in subTypes) {"	
			+ "operations.addAll(subType.OwnedOperation);"
			+ "}"
		
		+ "return operations;"
		+ "}"
	/******************************************************************************************************/
	+ "operation getSubTypes(class) {"
	+ "	var subTypes : Sequence;"
	+ "	subTypes.addAll(class.revRefNav_SuperType.revRefNav_Parent.flatten());"
	+ "	for( subType in class.revRefNav_SuperType.revRefNav_Parent.flatten()) {"
	+ "		var list = getSubTypes(subType);"
	+ "		subTypes.addAll(list);"
	+ "	}"
	+ "	return subTypes;"
	+ "}"

	/******************************************************************************************************/
	+ "operation operationsHaveSameReturn(oper1, oper2) {"
	+ "	if((not oper1.Return.isDefined()) and (not oper2.Return.isDefined())) {"
	+ "		return true;" // equal no return
	+ "	} "
	+ "	if(oper1.Return.isDefined() and oper2.Return.isDefined()) {"
	+ "		if(oper1.Return.Type.isDefined() and oper2.Return.Type.isDefined()) {"
	+ "			return (oper1.Return.Type.Name=oper2.Return.Type.Name);"
	+ "		}"
	+ "	}" 
	+ "	return false;"
	+ "}"
	/******************************************************************************************************/
	+ "operation operationsHaveSameArguments(oper1, oper2) {"
	+ "	if((not oper1.IO.isDefined()) and (not oper2.IO.isDefined())) {"
	+ "		return true;" // equal no arguements 
	+ "	} "
	+ "	var AlLEqual = false;"

		+ "if(oper1.IO.isDefined() and oper2.IO.isDefined()) {"
		+ "	if(oper1.IO.flatten().size() = oper2.IO.flatten().size()) {"
				+ "var found = false;"
				+ "for(arg1 in oper1.IO.flatten()) {"
					+ "if(oper2.IO.flatten().exists(t|compareArguments(arg1, t))) {"
						+ "AlLEqual = true;"
						+ "} else {"
						+ "AlLEqual = false;"
						+ "break;"
						+ "}"
						+ "}"
						+ "}"
						+ "}"
		+ "return AlLEqual;"
		+ "} "

	/******************************************************************************************************/
	+ "operation compareArguments(arg1, arg2) {"
		+ "if(arg1.Name = arg2.Name) {"
			+ "if(arg1.Type.isDefined() and arg2.Type.isDefined()) {"
				+ "return arg1.Type.Name = arg2.Type.Name;"
				+ "}"

				+ "}"
		+ "return false;"
		+ "}";
	/******************************************************************************************************/	
			
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
