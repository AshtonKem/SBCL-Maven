# Overview

Common Lisp is a very old and advanced multi-paradigm programming language. It has many fascinating and unique language features, but unfortunately it has a few major short fallings:

1. The specification for the language is showing its age. It leaves many vital features up to implementors.
2. There are multiple implementations that are not cross compatible without adapter libraries.
3. There are no serious build tools available for the majority of the implementations.

We wish to take a look at issue #3. For the time being, due to #1 and #2, we will be creating a build tool for one implementation only at the moment: SBCL.

# Scenarios

## Scenario 1:

Joe Hacker has started a brilliant project that will revolutionize the production of widgets as we understand it. Unfortunately he does not have the time or energy to run the project himself, and he needs employees. After hiring and training employees, they realize that they 
are going to have issues setting machines, sharing code, compiling, and testing. Rather than writing custom build and test scripts, Joe and his employees use the sbcl-plugin to compile and package code in a repeatible manner. This tool allows them to setup a continuous integration server,
thus improving the quality of their code even more.

## Scenario 2:

Bob and Jim are both open source programmers who use SBCL. Unfortunately they both use different conventions and scripts to build and test their code. Thus when they share their code, they waste a lot of time figuring out how the other builds their setup, and chasing down required environment variables and symlinks. 
To stop this from happening, Bob and Jim agree to use the sbcl-plugin to organize their code by convention (rather than configuration), and drop all of their custom build and test scripts. Their code is instantly more shareable and hackable.


# Non Goals

The following cannot be solved at the moment without orders of magnitude more complicated work.

* System independent portable libraries.
  * Would involve hacking LLVM or similar into SBCL.
* Tree shaking of libraries.
  * Common lisp can re-compile itself or new code in run time, which makes it impossible to trim unneeded libraries.
* Default testing framework
  * Might solve this later. It would be nice to have a default unit testing framework to pair with this tool in the future.


# Workflow

A new project would be setup in the following way:

1. pom.xml created with packaging type set to "sbcl"
2. Third party lisp libraries added to 'lib/lisp'
3. Code added in 'src/lisp'
4. Compile with 'mvn compile'
5. Package with 'mvn package'



