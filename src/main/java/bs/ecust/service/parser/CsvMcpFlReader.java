package bs.ecust.service.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author msalwa structure of the file: #problem# short name;long
 *         name;description #attributes# short name;long name;description;unit
 *         #alternatives# short name;long name;description;url;attributes
 *         values(the order must be the same as order of introduced attributes)
 */
// todo MS decide if we wanna have all errors from all file sections parsing
// given at the one processing.
// then we've got to use errors Class variable
public class CsvMcpFlReader implements MCAConfig {

	BufferedReader r;
	File f;
	String errors = "";
	LinkedList<String> content;
	private List<String> prblmCntntPrt;
	private List<String> atrbtsCntntPrt;
	private List<String> altrntvsCntntPrt;
	private List<String> vlsCntntPrt;
	private List<String> prblmInstCntntPrt;
	private List<String> prblmCriteriaCntntPrt;
	private List<String[]> criteriaPrsd;
	private List<String[]> instncPrsd;
	private List<String[]> altrntvsPrsd;
	private List<String[]> atrbtsPrsd;
	private List<String[]> vlsPrsd;
	private List<String[]> prblmPrsd;
	int prblmPos = -1;
	int atrbtsPos = -1;
	int altrntvsPos = -1;
	int instncPos = -1;
	int criteriaPos = -1;
	int vlsPos = -1;
	private boolean isItcriteriaFile;

	public CsvMcpFlReader(File file) {
		// TODO Auto-generated constructor stub
		f = file;
	}

	public void parse() throws MCPFileReaderException {
		readFileIn();
		prblmPrsd = parseSection("problem", prblmPos, prblmCntntPrt);
		atrbtsPrsd = parseSection("attributes", atrbtsPos, atrbtsCntntPrt);
		altrntvsPrsd = parseSection("alternatives", altrntvsPos,
				altrntvsCntntPrt);
		vlsPrsd = parseSection("values", vlsPos, altrntvsCntntPrt);
	}

	public void parseCriteria() throws MCPFileReaderException {
		isItcriteriaFile = true;
		readFileIn();
		instncPrsd = parseSection("instance", instncPos, prblmInstCntntPrt);
		criteriaPrsd = parseSection("criteria", atrbtsPos, atrbtsCntntPrt);

	}

	public List<String[]> getAltrntvsPrsd() {
		return altrntvsPrsd;
	}

	public List<String[]> getValuesPrsd() {
		return vlsPrsd;
	}

	public List<String[]> getAtrbtsPrsd() {
		return atrbtsPrsd;
	}

	public List<String[]> getPrblmPrsd() {
		return prblmPrsd;
	}

	public int getCriteriaPos() {
		return criteriaPos;
	}

	public int getInstncPos() {
		return instncPos;
	}

	public List<String[]> getCriteriaPrsd() {
		return criteriaPrsd;
	}

	public List<String[]> getInstncPrsd() {
		return instncPrsd;
	}

	/**
	 * 
	 * @param sectionName
	 *            gets
	 *            "problem","attributes","alternatives","instance,"criteria"
	 * @param beginLine
	 *            -line at which section starts
	 * @param sectionContent
	 *            Function checks if there is the same number of fields in each
	 *            row (field can be empty however) and returns list of arrays
	 *            keeping values form fields,from all lines in the selected
	 *            section.
	 * @return -list of values from the fields, for each line which belongs to
	 *         the section separately.
	 * @throws MCPFileReaderException
	 */
	private List<String[]> parseSection(String sectionName, int beginLine,
			List<String> sectionContent) throws MCPFileReaderException {
		String errors = "";
		List<String> content = (sectionName.contains("problem")) ? prblmCntntPrt
				: (sectionName.contains("attributes")) ? atrbtsCntntPrt
						: (sectionName.contains("alternatives")) ? altrntvsCntntPrt
								: (sectionName.contains("values")) ? vlsCntntPrt
										: (sectionName.contains("instance")) ? prblmInstCntntPrt
												: (sectionName
														.contains("criteria")) ? prblmCriteriaCntntPrt
														: null;

		List<String[]> lst = new ArrayList<String[]>();
		String[] fldVls = null;
		// int fieldCount = 0;
		int i = 0;
		for (String line : content) {
			if (line.length() == 0) { // if comment or empty line
				lst.add(null);
				i++;
				continue;
			}
			fldVls = line.split(";");
			for (int j = 0; j < fldVls.length; j++) {
				fldVls[j] = fldVls[j].trim();
			}
			lst.add(fldVls);
			/*
			 * if(i==0) fieldCount=fldVls.length; lst.add(fldVls);
			 * if(fldVls.length!=fieldCount)
			 * errors+=String.format(MCPerFrmtInvalidFieldsNum
			 * ,sectionName,beginLine+i);
			 */
			i++;
		}
		if (errors.length() == 0)
			return lst;
		else
			throw new MCPFileReaderException(errors);
	}

	private void readFileIn() throws MCPFileReaderException {
		try {
			content = new LinkedList<String>();
			prblmCntntPrt = new LinkedList<String>();
			atrbtsCntntPrt = new LinkedList<String>();
			altrntvsCntntPrt = new LinkedList<String>();
			vlsCntntPrt = new LinkedList<String>();
			prblmInstCntntPrt = new LinkedList<String>();
			prblmCriteriaCntntPrt = new LinkedList<String>();
			// modifoed by renh
			try {
				r = new BufferedReader(new InputStreamReader(
						new FileInputStream(f), "UTF8"));

			} catch (UnsupportedEncodingException e) {
				//e.printStackTrace();
				throw new MCPFileReaderException(e.getMessage(), e);
			} catch (IOException e) {
				throw new MCPFileReaderException(e.getMessage(), e);
			}

			// r=new BufferedReader(new FileReader(f));
			String line = "";

			int i = 1;
			while ((line = r.readLine()) != null) {
				// check if comment or empty line
				if ((line.trim()).startsWith("#")
						|| (line.trim()).length() == 0) {
					line = "";
					content.add(line);
					i++;
					continue;
				}
				if (line.contains(MCPprblmTag)) {
					prblmPos = i + 1;// next line after the tag

				}
				if (line.contains(MCPatrbtsTag)) {
					atrbtsPos = i + 1;

				}
				if (line.contains(MCPaltrntvsTag)) {
					altrntvsPos = i + 1;

				}
				if (line.contains(MCPaltrntvsVlsTag)) {
					vlsPos = i + 1;

				}
				if (line.contains(MCPprblmInstTag)) {
					instncPos = i + 1;

				}
				if (line.contains(MCPInstCriteriaTag)) {
					criteriaPos = i + 1;

				}

				line = line.replaceAll(";$", "; ");
				content.add(line);
				i++;
			}
			// to do -the order of tags//
			// to do -detect empty parts (nothing under tags unitl the next tag
			// uccur)
			if (i == 0)
				throw new MCPFileReaderException(MCPerFrmtFileEmpty);
			if (isItcriteriaFile) {
				if (criteriaPos == -1 || instncPos == -1)
					throw new MCPFileReaderException(MCPerFrmtFileWrongFormat);

				// couse line number start from 1 and position in list starts
				// from 0;
				for (String row : content.subList(instncPos - 1,
						criteriaPos - 2)) {
					prblmInstCntntPrt.add(row);
				}
				for (String row : content.subList(criteriaPos - 1, content
						.size())) {
					prblmCriteriaCntntPrt.add(row);
				}
			} else {
				if (prblmPos == -1 || atrbtsPos == -1 || altrntvsPos == -1
						|| vlsPos == -1
						|| (instncPos == -1 && criteriaPos != -1)
						|| (instncPos == 1 && criteriaPos == -1))
					throw new MCPFileReaderException(MCPerFrmtFileWrongFormat);

				if (i == 0)
					throw new MCPFileReaderException(MCPerFrmtFileEmpty);

				for (String row : content.subList(prblmPos - 1,
						getNextSectionPos("problem"))) { // couse line number
					// start from 1 and
					// position in list
					// starts from 0;
					// to do -size of the prblm part -should be only one line//
					prblmCntntPrt.add(row);
				}
				for (String row : content.subList(atrbtsPos - 1,
						getNextSectionPos("attributes"))) {
					atrbtsCntntPrt.add(row);
				}
				for (String row : content.subList(altrntvsPos - 1,
						getNextSectionPos("alternatives"))) {
					altrntvsCntntPrt.add(row);
				}

				for (String row : content.subList(vlsPos - 1,
						getNextSectionPos("values"))) {
					vlsCntntPrt.add(row);
				}
			}
		} catch (IOException e) {
			throw new MCPFileReaderException(e.getMessage(), e);
		}
	}

	private int getNextSectionPos(String sectionnm) {

		if (sectionnm.compareTo("problem") == 0) {
			return atrbtsPos - 2;
		}
		if (sectionnm.compareTo("attributes") == 0) {
			return altrntvsPos - 2;
		}
		if (sectionnm.compareTo("alternatives") == 0) {
			return vlsPos - 2;

		}
		if (sectionnm.compareTo("values") == 0) {
			if (instncPos != -1)
				return instncPos - 2;
			else
				return content.size();
		}
		if (sectionnm.compareTo("instance") == 0) {
			return criteriaPos - 2;
		}
		if (sectionnm.compareTo("criteria") == 0) {
			return content.size();
		}
		return -1;
	}
}
