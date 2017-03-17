package bs.ecust.service.parser;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import cs.ecust.domain.view.TmpAltrntv;
import cs.ecust.domain.view.TmpAtrbt;
import cs.ecust.domain.view.TmpPrblm;



@Service("problemLoader")
public class ProblemLoader {
	
	public TmpPrblm parseFile2TmpPrblm(String filePath) throws McpParserException {

		File f = new File(filePath);
		MCPDataParser prsr = new MCPDataParser(f);
		TmpPrblm mcpPrblm = prsr.getPrblm();
		List<TmpAtrbt> mcpAtrbts = prsr.getAtrbts();
		List<TmpAltrntv> mcpAltrntvs = prsr.getAltrntvs();
		mcpPrblm.setAtrbts(mcpAtrbts);
		mcpPrblm.setAltrntvs(mcpAltrntvs);
		return mcpPrblm;

	}

}
