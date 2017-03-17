package bs.ecust.service.parser;

public class McpParserException extends Exception {
	McpParserException() {
		super();
	}

	public McpParserException(String msg) {
		super(msg);
	}

	public McpParserException(Throwable thr) {
		super(thr);
	}

	public McpParserException(String msg, Throwable thr) {
		super(msg, thr);
	}
}
