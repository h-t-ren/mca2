package bs.ecust.service.parser;

import java.util.List;


import cs.ecust.domain.view.TmpAltrntv;
import cs.ecust.domain.view.TmpAtrbt;
import cs.ecust.domain.view.TmpPrblm;

public interface McpDataParsable {
	TmpPrblm getPrblm() throws McpParserException;

	List<TmpAtrbt> getAtrbts() throws McpParserException;

	List<TmpAltrntv> getAltrntvs() throws McpParserException;
}
