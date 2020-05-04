package com.baselet.control.enums;

/**
 * PROGRAM, PLATTFORM AND JAVA SPECIFIC SETTINGS
 **/
public class Program {

	private static Program instance;

	public static Program getInstance() {
		if (!isInitialized()) {
			throw new RuntimeException("Program must be initialized before using it");
		}
		return instance;
	}

	public static void init(String version, RuntimeType runtimeType) {
		instance = new Program(version, runtimeType);
	}

	public static boolean isInitialized() {
		return instance != null;
	}

	private final RuntimeType runtimeType;
	private final String configName;
	private final String programName;
	private final String extension;
	private final String website;
	private final String version;

	private Program(String v, RuntimeType rt) {
		this.version = v;
		this.runtimeType = rt;
		programName = "UMLet";
		extension = "uxf";
		website = "http://www." + getProgramName().toLowerCase() + ".com";
        
		if (runtimeType == RuntimeType.ECLIPSE_PLUGIN) {
			configName = getProgramName().toLowerCase() + "plugin.cfg";
		}
                else if (runtimeType == RuntimeType.BUBBLES_PLUGIN) {
                   configName = getProgramName().toLowerCase() + "bubbles.cfg";
                 }
		else /* STANDALONE and BATCH */ {
			configName = getProgramName().toLowerCase() + ".cfg";
		}
        
	}

	public RuntimeType getRuntimeType() {
		return runtimeType;
	}

	public String getConfigName() {
		return configName;
	}

	public String getProgramName() {
		return programName;
	}

	public String getExtension() {
		return extension;
	}

	public String getWebsite() {
		return website;
	}

	public String getVersion() {
		return version;
	}

}