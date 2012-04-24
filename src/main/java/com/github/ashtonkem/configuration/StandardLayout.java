package com.github.ashtonkem.configuration;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;
import org.apache.maven.project.MavenProject;


/**
 * Represents the standard lisp project layout. Files will be located as such:
 * 
 * <pre>
 *  BASE
 *    +-- doc/overview.edoc
 *    +-- src/lisp (*.lisp)
 *    +-- lib/lisp (*.lisp)
 *    +-- test/lisp (*.lisp)
 *    +-- target (build artifacts)
 *    +-- target/fasl (*.fasl files)
 *    +-- pom.xml
 * </pre>
 * 
 * @author ashtonkemerling
 * 
 */
public class StandardLayout implements SourceLayout {
	private File base;
	
	public StandardLayout(MavenProject project)
	{
		base = project.getBasedir();
	}

	public File base() {
		return base;
	}

	public File src() {
		return new File(base, "src/lisp");
	}

	public File lib() {
		return new File(base, "lib/lisp");
	}

	public File test() {
		return new File(base, "test/lisp");
	}
	
	public File faslDir() {
		return new File(base, "target/fasl");
	}

	@SuppressWarnings("unchecked")
	public Collection<File> asdFiles() {
		Iterator<File> iter = FileUtils.iterateFiles(base, new String[] {"asd"}, true);
		Collection<File> collector = new LinkedList<File>();
		while (iter.hasNext())
			collector.add(iter.next());
		return collector;
	}

}