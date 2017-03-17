package bs.ecust.service.parser;

public class MCPFileReaderException extends Exception {
	public MCPFileReaderException() {
		super();
	}

	public MCPFileReaderException(String msg) {
		super(msg);
	}

	public MCPFileReaderException(Throwable thr) {
		super(thr);
	}

	public MCPFileReaderException(String msg, Throwable thr) {
		super(msg, thr);
	}
}
