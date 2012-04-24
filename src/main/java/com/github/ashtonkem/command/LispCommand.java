package com.github.ashtonkem.command;

import java.io.File;

import com.github.ashtonkem.configuration.SourceLayout;


public interface LispCommand {
	
	void addExpression(String exp);
	
	void addFile(File f);
	
	void setLayout(SourceLayout layout);
	
	void setCoreName(String s);
	
	void setMainPackage(String s);
	
	String getCommand();
}
