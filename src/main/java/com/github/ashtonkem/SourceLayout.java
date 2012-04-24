package com.github.ashtonkem;

import java.io.File;


/**
 * Interface for any source file layout.
 * @author Ashton Kemerling <ashtonkemerling@gmail.com>
 *
 */


public interface SourceLayout {
    
	/**
	 * 
	 * @return The base directory for this project.
	 */
	public File base();
	
	/**
	 * 
	 * @return The source directory for this project.
	 */
	public File src();
	
	/**
	 * 
	 * @return The library directory for this project.
	 */
	public File lib();
	
	
	/**
	 * 
	 * @return The file where tests are located.
	 */
	public File test();
	


}