package bs.ecust.service.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import cs.ecust.domain.view.TmpAltrntv;
import cs.ecust.domain.view.TmpAtrbt;
import cs.ecust.domain.view.TmpPrblm;

public class MCPDataParser implements McpDataParsable, MCAConfig {
	String[][] atrbtsFields = MCPatrbtFields;
	String[][] altrntvFields = MCPaltrntvsFields;
	String[][] vlsFields;
	String[][] prblmFields = MCPprblmFields;

	List<TmpAltrntv> altrntvs = null;
	List<TmpAtrbt> atrbts = null;
	TmpPrblm prblm = null;
	File f;
	CsvMcpFlReader fileReader;
	boolean parsed = false;

	List<String> atrbtNames;
	List<String> altrntvNames;

	public MCPDataParser(File file) {
		fileReader = new CsvMcpFlReader(file);
	}

	/*
	 * private void parse() throws McpParserException { try {
	 * fileReader.parse(); parsed = true; } catch (MCPFileReaderException e) {
	 * throw new McpParserException(e.getMessage(), e); } }
	 */
	public TmpPrblm getPrblm() throws McpParserException {
		try {
			if (parsed == false)
				fileReader.parse();
			prblm = getPrblm(fileReader.getPrblmPrsd(), fileReader.prblmPos);
			return prblm;
		} catch (MCPFileReaderException e) {
			throw new McpParserException(e.getMessage(), e);
		}

	};

	/*
	 * public TmpPrblm getProblem(String[] prblmVls) throws McpParserException{
	 * ArrayList<String[]> temp=new ArrayList<String[]>(); temp.add(prblmVls);
	 * return getPrblm(temp,0); }
	 */
	/*
	 * public List<TmpAtrbt> getAtrbts(TmpPrblm prblm)throws McpParserException{
	 * try{ if(parsed==false) fileReader.parse(); return
	 * getAtrbts(fileReader.getAtrbtsPrsd(), fileReader.atrbtsPos,prblm); }
	 * catch(MCPFileReaderException e){ throw new
	 * McpParserException(e.getMessage(),e); } }
	 */

	public List<TmpAltrntv> getAltrntvs(List<TmpAtrbt> atrbts)
			throws McpParserException {
		try {
			if (parsed == false)
				fileReader.parse();
			List<TmpAltrntv> alts = getAltrntvs(fileReader.getAltrntvsPrsd(),
					fileReader.altrntvsPos, atrbts, prblm);
			List<String[]> values = getValues(fileReader.getValuesPrsd(),
					fileReader.vlsPos, alts.size(), atrbts.size());
			updateAlternatives(alts, values);
			return alts;
		} catch (MCPFileReaderException e) {
			throw new McpParserException(e.getMessage(), e);
		}
	}

	public List<TmpAtrbt> getAtrbts() throws McpParserException {
		try {
			if (parsed == false)
				fileReader.parse();
			if (prblm == null)
				getPrblm();
			atrbts = getAtrbts(fileReader.getAtrbtsPrsd(),
					fileReader.atrbtsPos, prblm);
			return atrbts;
		} catch (MCPFileReaderException e) {
			throw new McpParserException(e.getMessage(), e);
		}
	}

	public List<TmpAltrntv> getAltrntvs() throws McpParserException {
		try {
			if (parsed == false)
				fileReader.parse();
			if (prblm == null)
				getPrblm();
			if (atrbts == null)
				getAtrbts(fileReader.getAtrbtsPrsd(), fileReader.atrbtsPos,
						prblm);
			altrntvs = getAltrntvs(fileReader.getAltrntvsPrsd(),
					fileReader.altrntvsPos, atrbts, prblm);
			List<String[]> values = getValues(fileReader.getValuesPrsd(),
					fileReader.vlsPos, altrntvs.size(), atrbts.size());
			updateAlternatives(altrntvs, values);
			return altrntvs;
		} catch (MCPFileReaderException e) {
			throw new McpParserException(e.getMessage(), e);
		}
	}

	private void updateAlternatives(List<TmpAltrntv> alts, List<String[]> values) {

		for (int i = 0; i < values.size(); i++) {
			updateAltrntvWithVls(values.get(i), alts.get(i));
		}
	}

	private void updateAltrntvWithVls(String[] vls, TmpAltrntv altrntv) {
		for (String vlue : vls) {
			vlue = ((vlue == null) || vlue.length() == 0) ? "0" : vlue;
			Double dvalue = Double.parseDouble(vlue);
			altrntv.addValue(dvalue);
		}
	}

	private TmpPrblm getPrblm(List<String[]> prblmVlsPrsd, int beginPos)
			throws McpParserException {
		String errors = "";
		List<TmpPrblm> prblms = new ArrayList<TmpPrblm>();
		for (int i = 0; i < prblmVlsPrsd.size(); i++) {
			String[] prblmVls = prblmVlsPrsd.get(i); // MS todo -adavanced
			if (prblmVls == null) { // comment or empty line
				continue;
			}
		//	if (this.prblmFields.length != prblmVls.length)
			if (prblmVls.length!=3&&prblmVls.length!=4)
			{
				errors += "line "+beginPos + i+": "+this.prblmFields.length+ " (instead of 3 or 4 expected) fields defined.";	
				//	String.format(InvalidFieldsNum,beginPos + i,prblmVls.length,this.prblmFields.length);
				throw new McpParserException(errors);
			}
			for (int j = 0; j < prblmVls.length; j++) {
			//	errors += Tools.chckField(prblmFields[j][0], beginPos,
			//			prblmVls[j], Integer.valueOf(prblmFields[j][1]),
			//			Boolean.valueOf(prblmFields[j][2]));
				// to do checking the field type in db
				if (errors.length() == 0)
					prblms.add(definePrblm(prblmVls));
			}
		}
		if (errors.length() != 0)
			throw new McpParserException(errors);
		return prblms.get(0);

	}

	private List<TmpAtrbt> getAtrbts(List<String[]> atrbtsVlsPrsd,
			int beginPos, TmpPrblm prblm) throws McpParserException {
		String errors = "";
		List<TmpAtrbt> atrbts = new ArrayList<TmpAtrbt>();
		atrbtNames = new ArrayList<String>();

		for (int i = 0; i < atrbtsVlsPrsd.size(); i++) {
			String[] atrbtVls = atrbtsVlsPrsd.get(i);
			if (atrbtVls == null) { // comment or empty line
				continue;
			}
			if (atrbtsFields.length != atrbtVls.length)
				errors += String.format(MCPerFrmtInvalidFieldsNum,
						"attributes", beginPos + i);
			else {
				for (int j = 0; j < atrbtVls.length; j++) {
					errors += Tools.chckField(this.atrbtsFields[j][0], beginPos
							+ i, atrbtVls[j], Integer
							.valueOf(this.atrbtsFields[j][1]), Boolean
							.valueOf(this.atrbtsFields[j][2]));
				}
				if (atrbtNames.contains(atrbtVls[0])) {
					errors += String.format(MCPDuplicateAttribute,
							beginPos + i, atrbtVls[0]);
				}
				atrbtNames.add(atrbtVls[0]);
				if (errors.length() == 0)
					atrbts.add(defineAtrbt(atrbtVls, prblm));
			}
		}
		if (errors.length() != 0)
			throw new McpParserException(errors);
		return atrbts;
	}

	private List<TmpAltrntv> getAltrntvs(List<String[]> altrntvsVlsPrsd,
			int beginPos, List<TmpAtrbt> atrbts, TmpPrblm prblm)
			throws McpParserException {
		String errors = "";

		List<TmpAltrntv> altrntvs = new ArrayList<TmpAltrntv>();
		altrntvNames = new ArrayList<String>();
		for (int i = 0; i < altrntvsVlsPrsd.size(); i++) {
			String[] altrntvVls = altrntvsVlsPrsd.get(i);
			if (altrntvVls == null) { // comment or empty line
				continue;
			}
			if (altrntvFields.length != altrntvVls.length)
				errors += String.format(MCPerFrmtInvalidFieldsNum,
						"alternatives", beginPos + i);
			else {
				for (int j = 0; j < altrntvVls.length; j++) {
					errors += Tools.chckField(this.altrntvFields[j][0],
							beginPos + i, altrntvVls[j], Integer
									.valueOf(this.altrntvFields[j][1]), Boolean
									.valueOf(this.altrntvFields[j][2]));
				}
				if (altrntvNames.contains(altrntvVls[0])) {
					errors += String.format(MCPDuplicateAlternaive, beginPos
							+ i, altrntvVls[0]);
				}
				altrntvNames.add(altrntvVls[0]);
				if (errors.length() == 0)
					altrntvs.add(defineAltrntv(altrntvVls));
			}
		}
		if (errors.length() != 0)
			throw new McpParserException(errors);
		return altrntvs;
	}

	private List<String[]> getValues(List<String[]> vls, int beginPos,
			int altrntvNum, int atrbtNum) throws McpParserException {
		String errors = "";
		int altrntvsNmInVlsSctn = 0;
		List<String[]> altAtrbtVls = new ArrayList<String[]>();
		for (String[] strings : vls) {
			if (strings != null)
				altrntvsNmInVlsSctn++;
		}
		if (altrntvsNmInVlsSctn != altrntvNum)
			errors += String.format(MCPerFrmtInsfficientLinesNum, "values",
					altrntvNum, altrntvsNmInVlsSctn);
		for (int i = 0; i < vls.size(); i++) {
			String[] vlrow = vls.get(i);
			if (vlrow == null)
				continue;
			if (vlrow.length != atrbtNum) {
				errors += String.format(MCPerFrmtInvalidSectionNum, "values",
						beginPos + i, atrbtNum, vlrow.length);
			}
			for (int j = 0; j < vlrow.length; j++) {
				errors += checkValueField(vlrow[j], beginPos + i, j + 1,
						Integer.valueOf(MCPaltrntvsAtrbtVlField[0]));
			}
			altAtrbtVls.add(vlrow);
		}
		if (errors.length() != 0)
			throw new McpParserException(errors);
		return altAtrbtVls;
	}

	private String checkValueField(String value, int line, int pos, int maxChars) {
		String errors = "";
		if (value == null || value.trim().length() == 0) {
			value = null; /* by MS not to keep empty strings in the database */
			return errors;
		}
		if (value.length() > maxChars) {
			errors += String.format(MCPerVlFrmtTooLong, "values", line, pos,
					maxChars);
		}
		try {
			Double.parseDouble(value);

		} catch (NumberFormatException e) {
			errors += String.format(MCPerVlFrmtInvalid, "values", line, pos);
		}
		return errors;
	}

	private TmpPrblm definePrblm(String[] prblmVlsPrsd) {
		String lngnm = ((prblmVlsPrsd[1] == null) || prblmVlsPrsd[1].length() == 0) ? prblmVlsPrsd[0]
				: prblmVlsPrsd[1]; // lng name
		// System.out.println("problem name:" + prblmVlsPrsd[0]);
		TmpPrblm tmpPrblm =new TmpPrblm(prblmVlsPrsd[0], lngnm, prblmVlsPrsd[2]);
		if(prblmVlsPrsd.length==4)
		{
		if(prblmVlsPrsd[3]!=null&&!prblmVlsPrsd[3].isEmpty())
		{
			tmpPrblm.setTemplateString(prblmVlsPrsd[3]);
			System.out.println("template string: "+prblmVlsPrsd[3]);
		}
		}
		return tmpPrblm;
	}

	private TmpAtrbt defineAtrbt(String[] atrbtVls, TmpPrblm prblm) {

		String shrtnm = atrbtVls[0];// short name,
		String lngnm = ((atrbtVls[1] == null) || atrbtVls[1].length() == 0) ? atrbtVls[0]
				: atrbtVls[1]; // lng name
		String unit = atrbtVls[2]; // unit
		String dscrptn = atrbtVls[3]; // description

		TmpAtrbt atrbt = new TmpAtrbt(shrtnm, lngnm, dscrptn, unit);
		return atrbt;
	}

	private TmpAltrntv defineAltrntv(String[] altrntvVls) {

		String shrtnm = altrntvVls[0];// short name,
		String lngnm = ((altrntvVls[1] == null) || altrntvVls[1].length() == 0) ? altrntvVls[0]
				: altrntvVls[1]; // lng name
		String dscrptn = altrntvVls[2]; // description

		return new TmpAltrntv(shrtnm, lngnm, dscrptn);
	}
}
