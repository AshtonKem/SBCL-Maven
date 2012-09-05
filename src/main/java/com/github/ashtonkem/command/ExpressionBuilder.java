package com.github.ashtonkem.command;

import java.util.ArrayList;
import java.util.Collection;

public class ExpressionBuilder {
	private String function;
	private Collection<String> arguments;
	public ExpressionBuilder(String function) {
		this.function = function;
		arguments = new ArrayList<String>();
	}
	
	public void addKeyArgument (String key, String value) {
		arguments.add(":" + key.toLowerCase());
		arguments.add(value);
	}
	
	public void addSymbol(String lispPackage, String value) {
		if (lispPackage.equals(""))
			arguments.add("'" + value);
		else
			arguments.add("'" + lispPackage + "::" + value);		
	}
	
	public void addArgument(String arg) {
		arguments.add(arg);
	}
	
	public void addFunction(String lispPackage, String name) {
		if (lispPackage.equals(""))
			arguments.add("#'" + name);
		else
			arguments.add("#'" + lispPackage + "::" + name);	
	}
	
	public void addList(String... items) {
		String collector = "(list ";
		for (String item : items)
			collector += item + " ";
		collector += ")";
		arguments.add(collector);
	}
	
	public void addString(String string) {
		arguments.add("\"" + string + "\"");
	}
	
	public String getExpression() {
		String collector = "(";
		collector += function;
		for (String arg : arguments)
			collector += arg + " ";
		collector += ")";
		return collector;
	}
}
