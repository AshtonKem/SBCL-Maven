package com.github.ashtonkem.command;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;


import com.github.ashtonkem.configuration.SourceLayout;


public class SBCLCommand implements LispCommand{

	private Collection<String> expressions;
	@SuppressWarnings("unused")
	private SourceLayout layout;
	private String coreName;
	private String mainPackage;
	
	public SBCLCommand()
	{
		expressions = new LinkedList<String>();
	}
	public void addExpression(String exp) {
		expressions.add(exp);
	}

	public void addFile(File f) {
		if (f.exists())
		{
			this.addExpression("(load \"" + f.getAbsolutePath() + "\")");
		}
		
	}

	public void setLayout(SourceLayout s) {
		layout = s;
	}
	
	public String getCommand() {
		String command = "(progn";
		for (String s: expressions)
		{
			command += "\n";
			command += s;
		}
		for (File f : layout.asdFiles()) 
		{
			if (f.exists())
			{
				command += "(pushnew (truename \"" + f.getParent() + "\") asdf::*central-registry*)\n";
			}
		}
		command += "(require :" + mainPackage + ")\n";
		command += "(sb-ext:save-lisp-and-die \"target/" + coreName + "\" :executable t)";
		command += ")";
		return command;
	}
	public void setCoreName(String s) {
		coreName = s;
		
	}
	public void setMainPackage(String s) {
		mainPackage = s;
		
	}

}
