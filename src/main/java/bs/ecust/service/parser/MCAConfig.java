package bs.ecust.service.parser;


public interface MCAConfig {

	public static int MCAShrtnmSize = 15;
	public static int MCALngnmSize = 90;
	public static int MCADscrptnSize = 2000;
	public static int MCAMemoSize = 500;
	public static int MCAUnitSize = 10;
	public static int MCAAtrbtVlSize = 10;
	public static String MCPerFrmtTooLong = "line %d: field %s can not be longer than %d signs<br>";
	public static String MCPerFrmtempty = "line %d: field %s can not be empty<br>";
	public static String MCPerFrmtFileWrongFormat = "Incorrect structure of data in the file";
	public static String MCPerFrmtFileEmpty = "File is empty";
	public static String MCPerFrmtFldUnxpctdVl = "line %d: field %s can only have following values: %s";

	public static String MCPerFrmtInvalidFieldsNum = "Section %s, line %d: invalid number of fields in line<br>";
	public static String InvalidFieldsNum = "line %d: %d (instead of %d expected) fields defined.";
	public static String MCPerFrmtInvalidSectionNum = "Section %s, line %d:  %d attributes values expected %d defined<br>";
	public static String MCPerFrmtInsfficientLinesNum = "Section %s,values of %d alternatives expected, %d defined<br>";
	// end
	public static String MCPUnknownParent = "line %d: There is no such parent criterion: %s.<br>";
	public static String MCPDuplicateCriterion = "line %d: duplicate criterion: %s.<br>";
	public static String MCPDuplicateAttribute = "line %d: duplicate attribute: %s.<br>";
	public static String MCPDuplicateAlternaive = "line %d: duplicate alternaive: %s.<br>";

	public static String MCPerUnknownAttrbt = "line %d:There is no such attribute as %s for selected problem<br>";
	public static String MCPerInvldPrnt = "line %d:Criterion parent can not be the base level criterion";
	public static String MCPerInvldCrtrnLvl = "line %d:Criterion can not be the higher level criterion if it is not pointed by some other criteria as parent<br>";
	public static String MCPerFrmFldTooLong = "field %s can not be longer than %d signs<br>";
	public static String MCPerFrmFldempty = "field %s can not be empty<br>";
	public static String MCPerVlFrmtInvalid = "Section %s:line %d:field position %d: value must be a number<br>";
	public static String MCPerVlFrmtTooLong = "Section %s:line %d:field position %d: value can not be longer than %d signs <br>";
	public static String MCPerAtrbtLost = "Line d%: criterion \" s%\" uses undifined attribute \" s% \" <br>";
	public static String MCPprblmTag = "<problem>";
	public static String MCPatrbtsTag = "<attributes>";
	public static String MCPaltrntvsTag = "<alternatives>";
	public static String MCPaltrntvsVlsTag = "<values>";
	public static String MCPprblmInstTag = "<instance>";
	public static String MCPInstCriteriaTag = "<criteria>";
	public static String[][] MCPprblmFields = { { "short name", "16", "true" },
			{ "long name", "128", "false" }, { "description", "2000", "no" } };
	public static String[][] MCPatrbtFields = { { "short name", "16", "true" },
			{ "long name", "128", "false" }, { "unit", "16", "true" },
			{ "description", "2000", "no" } };
	public static String[][] MCPaltrntvsFields = {
			{ "short name", "16", "true" }, { "long name", "128", "false" },
			{ "description", "2000", "no" } };
	public static String[] MCPaltrntvsAtrbtVlField = { "15", "true" };
	public static String[][] MCPinstncFields = {
			{ "short name", "16", "true" }, { "long name", "128", "false" } };
	public static String[][] MCPInstCriteriaFields = {
			{ "short name", "16", "true", "all" },
			{ "long name", "128", "false", "all" },
			{ "attribute name", "16", "no", "all" },
			{ "parent", "16", "no", "all" },
			{ "type", "6", "false", "max min target" },
			{ "target value", "16", "false", "all" } };
	public static int MAXNUMVERTICAL = 10;

	public final static String logName = "MCAA";

}
