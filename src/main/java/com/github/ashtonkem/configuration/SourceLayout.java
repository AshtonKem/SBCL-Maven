package com.github.ashtonkem.configuration;

import java.io.File;
import java.util.Collection;


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
	
	/**
	 * 
	 * @return Directory where .fasl files will be stored.
	 */
	public File faslDir();
	
	/**
	 * 
	 * @return A collection of .asd files.
	 */
	public Collection<File> asdFiles();
	
	
	/**
	 * 
	 * @return A follection of .fasl files.
	 */
	public Collection<File> faslFiles();

}