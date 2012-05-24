package com.github.ashtonkem.command;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

import com.github.ashtonkem.configuration.SourceLayout;

public class SBCLCommand implements LispCommand {

	private Collection<String> expressions;
	private SourceLayout layout;
	private String coreName;
	private String mainPackage;
	private boolean packageResult;

	public SBCLCommand() {
		expressions = new LinkedList<String>();
	}

	public void addExpression(String exp) {
		expressions.add(exp);
	}

	public void addFile(File f) {
		if (f.exists()) {
			this.addExpression("(load \"" + f.getAbsolutePath() + "\")");
		}

	}

	public void setLayout(SourceLayout s) {
		layout = s;
	}

	public String getCommand() {
		String command = "(progn";
		for (String s : expressions) {
			command += "\n";
			command += s;
		}
		for (File f : layout.asdFiles()) {
			if (f.exists()) {
				command += "(pushnew (truename \"" + f.getParent()
						+ "\") asdf::*central-registry*)\n";
			}
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

}
