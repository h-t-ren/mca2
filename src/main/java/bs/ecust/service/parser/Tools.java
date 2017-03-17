package bs.ecust.service.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;




public class Tools implements MCAConfig {

	public static String chckField(String field, int line, String value,
			int maxChars, boolean required) {
		String errors = "";
		if (value.trim().length() == 0) {
			value = null; /* by MS not to keep empty strings in the database */
			if (required)
				errors += String.format(MCPerFrmtempty, line, field);
			return errors;
		}

		if (value.length() > maxChars) {
			errors += String.format(MCPerFrmtTooLong, line, field, maxChars);
		}
		return errors;
	}

	public static String chckField(String field, int line, String value,
			int maxChars, boolean required, String accptdVls) {
		String errors = "";
		if (value.trim().length() == 0) {
			value = null; /* by MS not to keep empty strings in the database */
			if (required)
				errors += String.format(MCPerFrmtempty, line, field);
			return errors;
		}

		if (value.length() > maxChars) {
			errors += String.format(MCPerFrmtTooLong, line, field, maxChars);
		}
		if ((accptdVls.compareTo("all") != 0) && !accptdVls.contains(value)) {
			errors += String.format(MCPerFrmtFldUnxpctdVl, line, field,
					accptdVls);
		}
		return errors;
	}

	public static String chckField(String field, String value, int maxChars,
			boolean required) {
		String errors = "";

		if (value == null || value.trim().length() == 0) {
			value = null; /* by MS not to keep empty strings in the database */
			if (required)
				errors += String.format(MCPerFrmFldempty, field);
			return errors;
		}

		if (value.length() > maxChars) {
			errors += String.format(MCPerFrmFldTooLong, field, maxChars);
		}
		return errors;
	}

	
}
