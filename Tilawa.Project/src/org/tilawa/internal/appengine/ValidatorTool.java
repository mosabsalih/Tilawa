package org.tilawa.internal.appengine;

public final class ValidatorTool {

	/**
	 * @author m.sharif (mosabsalih@gmail.com)
	 */
	public static final String EMAIL_PATTERN = "^[\\w-\\.]{1,}\\@([\\da-zA-Z-]{1,}\\.){1,}[\\da-zA-Z-]{2,3}$";

	public static boolean isEmpty(String val){
		return (val == null || "".equals(val.trim()));
	}

	public static boolean isValidEmailID(String val){
		return val.matches(EMAIL_PATTERN);

	}

	public static boolean isEmpty(Object val){
		if(val instanceof String)
			return isEmpty((String)val);
		return (val == null );
	}
}
