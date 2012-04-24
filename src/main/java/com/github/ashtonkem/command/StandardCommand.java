package com.github.ashtonkem.command;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;


import com.github.ashtonkem.configuration.SourceLayout;


public class StandardCommand implements LispCommand{

	private Collection<String> expressions;
	@SuppressWarnings("unused")
	private SourceLayout layout;
	
	public StandardCommand()
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
		command += ")";
		return command;
	}
	public void setCoreName(String s) {
		// TODO Auto-generated method stub
		
	}

}
