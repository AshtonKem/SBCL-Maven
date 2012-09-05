package com.github.ashtonkem.Processes;

import java.io.IOException;


public class SBCLProcess extends LispProcess {

	@Override
	public void stop() {
		try {
			writer.write("(quit)\n");
			writer.flush();
			process.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected String getExecutableName() {
		return "sbcl";
	}

}
