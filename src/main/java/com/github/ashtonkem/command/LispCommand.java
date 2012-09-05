package com.github.ashtonkem.command;

import java.io.File;

import com.github.ashtonkem.configuration.SourceLayout;


public abstract class LispCommand implements Iterable<String> {
	
	protected boolean finalize;
	protected SourceLayout layout;
	
	public LispCommand(boolean finalCommand, SourceLayout layout)
	{
		this.finalize = finalCommand;
		this.layout = layout;
	}
	
	public boolean finalCommand() 
	{
		return finalize;
	}
	
	public abstract void addExpression(String exp);
	
	public abstract void addPackage(File f);
	
	public abstract void setCoreName(String s);
	
	public abstract void setMainPackage(String s);
	
	public abstract void silenceWarnings();
	
	public abstract void includeFasls();
	
	public abstract void includeSource();
}
