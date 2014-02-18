/*******************************************************************************
 * Create By : Jaydip Makadia
 * Date/Time : 20-July-2010/06:16 PM
 * Organization : SPEC-INDIA, Ahmedabad 
 *******************************************************************************/
package com.splus.util;

import java.util.Calendar;

public class Debug {
	private static boolean iDebug = true;
	private static Debug instance = null;
	private static Calendar cal = Calendar.getInstance();

	private Debug() {
		super();
	}

	public static Debug getInstance() {
		if (instance == null) {
			instance = new Debug();
		}
		return instance;
	}

	public static boolean isDebug() {
		return iDebug;
	}

	public static void setDebug(boolean newValue) {
		iDebug = newValue;
	}

	public static void debugLog(String aMessage) {
		if (isDebug()) {
			System.out.print("=>[aPhone:ver:1.0] " + cal.getTime() + " ");
			System.out.println(aMessage);
		}
	}

	public static void errorLog(String aMessage, Throwable t) {
		System.out.println("ERROR: " + aMessage);
		if (t != null) {
			t.printStackTrace();
		}
	}

	public static void errorLog(String aMessage) {
		errorLog(aMessage, null);
	}
}
