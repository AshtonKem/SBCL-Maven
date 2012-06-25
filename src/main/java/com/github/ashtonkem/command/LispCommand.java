package com.github.ashtonkem.command;

import java.io.File;
import java.util.Iterator;

import com.github.ashtonkem.configuration.SourceLayout;


public interface LispCommand extends Iterable<String> {
	
	void addExpression(String exp);
	
	void addPackage(File f);
	
	void addPackage(String s);
	
	void setLayout(SourceLayout layout);
	
	void setCoreName(String s);
	
	void setMainPackage(String s);
}
