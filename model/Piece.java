package model;

import java.awt.Color;

public enum Piece {
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
	BIG_ROUND_FIELD_YELLOW(true, "ROUND", false, Color.YELLOW),
	BIG_ROUND_FIELD_BROWN(true, "ROUND", false, new Color(102,51,0));


	private boolean big;
	private boolean hollow;
	private Color color;
	private String form;

	Piece(boolean big, String form, boolean hollow, Color color) {
		this.big = big;
		this.form = form;
		this.hollow = hollow;
		this.color = color;
	}

	public boolean isBig() {
		return big;
	}

	public boolean isHollow() {
		return hollow;
	}

	public Color getColor() {
		return color;
	}

	public String getForm() {
		return form;
	}

	public String getNameImage(){
		String result = "";
		result+=(isBig())?"big_":"sml_";
		result+=(getForm().equals("SQUARE"))?"sqr_":"cir_";
		result+=(isHollow())?"hlw_":"fld_";
		result+=(getColor().equals(Color.YELLOW))?"ylw":"bwn";
		return result;
	}

	public boolean haveOneCaracteristicsInCommun(Piece piece){
		return false;
	}
}
