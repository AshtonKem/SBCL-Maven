package com.github.ashtonkem.mojos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import com.github.ashtonkem.command.LispCommand;
import com.github.ashtonkem.command.StandardCommand;
import com.github.ashtonkem.configuration.SourceLayout;
import com.github.ashtonkem.configuration.StandardLayout;


/**
 * 
 * @author ashtonkemerling
 * @goal compile-lisp
 */
public class Compiler extends AbstractMojo {
	/**
	 * Name of the final .core file produce by SBCL.
	 * 
	 * @parameter expression="${coreName}" default-value="compile.core"
	 */
	private String coreName;

	/**
	 * @parameter default-value="${project}"
	 */
	private MavenProject project;

	public void execute() throws MojoExecutionException, MojoFailureException {

		SourceLayout layout = new StandardLayout(project);
		LispCommand command = new StandardCommand();
		command.setLayout(layout);
		command.addExpression("(sb-ext:disable-debugger)");
		command.addExpression("(error \"Hello\")");
		command.setCoreName(coreName);
		getLog().info("Lisp eval: " + command.getCommand());
		ProcessBuilder builder = new ProcessBuilder("sbcl", "--eval",
				command.getCommand());
		builder.redirectErrorStream(true);
		Process proc;
		try {
			proc = builder.start();
			InputStream is = proc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			int exit = -1;
			try {
				while ((line = br.readLine()) != null) {
					System.out.println(line);
					try {
						exit = proc.exitValue();
						if (exit == 0)
							System.out.println("Exited");

					} catch (IllegalThreadStateException t) {

					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
