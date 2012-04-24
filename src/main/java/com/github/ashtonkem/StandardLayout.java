package com.github.ashtonkem;

import java.io.File;
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

}
