package com.github.ashtonkem.Processes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.github.ashtonkem.command.LispCommand;

/***
 * Abstract class designed to cover all of the behavior for a Lisp Process. It
 * will contain a reference to the external Process, and accept a series of
 * LispCommand objects to execute. It will also queue up all output internally.
 * 
 * @author ashtonkemerling
 * 
 */
public abstract class LispProcess {
	protected Process process;

	protected ArrayList<LispCommand> commands = new ArrayList<LispCommand>();

	public LispProcess() {

	}

	public void start() {
		ProcessBuilder builder = new ProcessBuilder(this.getExecutableName());
		try {
			builder.redirectErrorStream(true);
			process = builder.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					process.getOutputStream()));
			String line;
			try {
				for (LispCommand c : commands) {
					for (String exp : c) {
						if (this.running())
						{
							bw.write(exp);
							bw.write('\n');
							bw.flush();
						}
					}
				}
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public abstract void stop();

	public void addCommand(LispCommand c) {
		commands.add(c);
	}

	public boolean running() {
		try {
			process.exitValue();
			return false;
		} catch (IllegalThreadStateException e) {
			return true;
		}
	}

	protected abstract String getExecutableName();

}
