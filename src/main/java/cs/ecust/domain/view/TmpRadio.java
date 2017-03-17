package cs.ecust.domain.view;

public class TmpRadio {

	private Integer id;
	private String  name;
	private Integer value;
	private Integer preValue;
	private Integer level;
	private String  longname;
	private Integer button;
	private Integer toplevel;
	private String  style;
	private Integer lowerst;


	public TmpRadio(Integer id, String name, String longname, Integer value,
			Integer preValue, Integer level, Integer button, Integer toplevel,
			String style, Integer lowerst) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.preValue = preValue;
		this.level = level;
		this.longname = longname;
		this.button = button;
		this.toplevel = toplevel;
		this.style = style;
		this.lowerst = lowerst;
	}

	public TmpRadio(Integer id, Integer value, Integer preValue) {
		this.id = id;
		this.value = value;
		this.preValue = preValue;
	}

	
	
	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public Integer getpreValue() {
		return this.preValue;
	}

	public Integer getLevel() {
		return this.level;
	}

	public String getLongname() {
		return this.longname;
	}

	public Integer getButton() {
		return this.button;
	}

	public Integer getToplevel() {
		return this.toplevel;
	}

	public String getStyle() {
		return this.style;
	}

	public Integer getLowerst() {
		return this.lowerst;
	}


}
