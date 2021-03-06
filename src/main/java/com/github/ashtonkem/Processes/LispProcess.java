package com.github.ashtonkem.Processes;

import java.io.BufferedWriter;
import java.io.IOException;
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
	protected BufferedWriter writer;
	protected boolean silent = false;

	protected ArrayList<LispCommand> commands = new ArrayList<LispCommand>();

	public LispProcess() {

	}

	public void start() {
		ProcessBuilder builder = new ProcessBuilder(this.getExecutableName());
		try {
			builder.redirectErrorStream(true);
			process = builder.start();
			writer = new BufferedWriter(new OutputStreamWriter(
					process.getOutputStream()));
			OutputListener listener = new OutputListener(process, silent);
			new Thread(listener).start();
			try {
				for (LispCommand c : commands) {
					for (String exp : c) {
						if (this.running())
						{
							writer.write(exp);
							writer.write('\n');
							writer.flush();
						}
					}
					if (c.finalCommand())
						break;
				}
				this.stop();

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
	
	public void silenceOutput() {
		silent = true;
	}

	protected abstract String getExecutableName();

}
