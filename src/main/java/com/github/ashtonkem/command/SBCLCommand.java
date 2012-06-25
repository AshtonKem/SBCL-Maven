package com.github.ashtonkem.command;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.github.ashtonkem.configuration.SourceLayout;

public class SBCLCommand implements LispCommand {

	private Collection<String> expressions;
	private String coreName;
	private String mainPackage;
	private boolean packageResult;

	public SBCLCommand() {
		expressions = new LinkedList<String>();
		this.addExpression("(require :asdf)");
	}

	public void addExpression(String exp) {
		expressions.add(exp);
	}

	public void addPackage(File f) {
		if (f.exists()) {
			this.addExpression("(pushnew (truename \"" + f.getParent() + "\") asdf::*central-registry*)");
		}

	}
	
	public void addPackage(String s)
	{
		if (s.charAt(0) == ':')
			this.addExpression("(load " + s + ")");
		else
			this.addExpression("(load :" + s + ")");
		
	}

	public void setLayout(SourceLayout s) {
		for (File f : s.asdFiles())
			this.addPackage(f);
		
	}

	public String getCommand() {
		String command = "(progn";
		for (String s : expressions) {
			command += "\n";
			command += s;
		}
		command += "(list :output-translations :disable-cache :ignore-inherited-configuration (list *home* (merge-pathnames \"target/fasl/**/*.*\" *home*))";
		command += "(require :" + mainPackage + ")\n";
		if (packageResult) {
			command += "(sb-ext:save-lisp-and-die \"target/" + coreName
					+ "\" :executable t)";
		}
		command += ")";
		return command;
	}

	public void setCoreName(String s) {
		coreName = s;

	}

	public void setMainPackage(String s) {
		mainPackage = s;

	}

	public void packageResult(boolean t) {
		packageResult = t;

	}

	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return expressions.iterator();
	}



}
