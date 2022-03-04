import java.awt.Color;

public enum Pieces {
	SMALL_SQUARE_HOLLOW_YELLOW(false, "SQUARE", true, Color.YELLOW),
	SMALL_SQUARE_HOLLOW_BROWN(false, "SQUARE", true, new Color(102,51,0)),
	SMALL_SQUARE_FIELD_YELLOW(false, "SQUARE", false, Color.YELLOW),
	SMALL_SQUARE_FIELD_BROWN(false, "SQUARE", false, new Color(102,51,0)),
	
	SMALL_ROUND_HOLLOW_YELLOW(false, "ROUND", true, Color.YELLOW),
	SMALL_ROUND_HOLLOW_BROWN(false, "ROUND", true, new Color(102,51,0)),
	SMALL_ROUND_FIELD_YELLOW(false, "ROUND", false, Color.YELLOW),
	SMALL_ROUND_FIELD_BROWN(false, "ROUND", false, new Color(102,51,0)),
	
	BIG_SQUARE_HOLLOW_YELLOW(true, "SQUARE", true, Color.YELLOW),
	BIG_SQUARE_HOLLOW_BROWN(true, "SQUARE", true, new Color(102,51,0)),
	BIG_SQUARE_FIELD_YELLOW(true, "SQUARE", false, Color.YELLOW),
	BIG_SQUARE_FIELD_BROWN(true, "SQUARE", false, new Color(102,51,0)),
	
	BIG_ROUND_HOLLOW_YELLOW(true, "ROUND", true, Color.YELLOW),
	BIG_ROUND_HOLLOW_BROWN(true, "ROUND", true, new Color(102,51,0)),
	BIG_ROUND_FIELD_YELLOW(true, "ROUND", true, Color.YELLOW),
	BIG_ROUND_FIELD_BROWN(true, "ROUND", true, new Color(102,51,0));
	
	private boolean big;
	private boolean hollow;
	private Color color;
	private String form;

	Pieces(boolean big, String form, boolean hollow, Color color) {
		this.big = big;
		this.form = form;
		this.hollow = hollow;
		this.color = color;
	}
}
