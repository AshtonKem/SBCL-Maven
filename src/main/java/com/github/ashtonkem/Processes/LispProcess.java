package com.github.ashtonkem.Processes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.github.ashtonkem.command.LispCommand;


/***
 * Abstract class designed to cover all of the behavior for a Lisp Process. 
 * It will contain a reference to the external Process, and accept a series of LispCommand objects
 * to execute. It will also queue up all output internally.
 * @author ashtonkemerling
 *
 */
public abstract class LispProcess {
	protected Process process;
	
	protected ArrayList<LispCommand> commands = new ArrayList<LispCommand>();
	public LispProcess() {
		
	}
	
	public  void start()
	{
		ProcessBuilder builder = new ProcessBuilder(this.getExecutableName());
		try {
			process = builder.start();
			InputStream is = process.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			int exit = -1;
			try {
				while ((line = br.readLine()) != null) {
					System.out.println(line);
					try {
						exit = process.exitValue();
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
	public abstract String getOutput();
	public abstract void stop();
	public abstract void addCommand(LispCommand c);
	public boolean running()
	{
		try {
			process.exitValue();
			return false;
		} catch (IllegalThreadStateException e)
		{
			return true;
		}
	}
	protected abstract String getExecutableName();


}
