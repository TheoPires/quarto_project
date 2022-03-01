import java.awt.Color;

public enum Pieces {
	SMALL_SQUARE_HOLLOW_YELLOW(),
	SMALL_SQUARE_HOLLOW_BROWN(),
	SMALL_SQUARE_FIELD_YELLOW(),
	SMALL_SQUARE_FIELD_BROWN(),
	
	SMALL_ROUND_HOLLOW_YELLOW(),
	SMALL_ROUND_HOLLOW_BROWN(),
	SMALL_ROUND_FIELD_YELLOW(),
	SMALL_ROUND_FIELD_BROWN(),
	
	BIG_SQUARE_HOLLOW_YELLOW(),
	BIG_SQUARE_HOLLOW_BROWN(),
	BIG_SQUARE_FIELD_YELLOW(),
	BIG_SQUARE_FIELD_BROWN(),
	
	BIG_ROUND_HOLLOW_YELLOW(),
	BIG_ROUND_HOLLOW_BROWN(),
	BIG_ROUND_FIELD_YELLOW(),
	BIG_ROUND_FIELD_BROWN();
	   private boolean big;
	   private boolean hollow;
	   private Color color;
	   private String form;

	   // Constructor of Enum internal use only
	   // Modifier of constructor is private
	   // If you do not declare private, java alway understand is private.    
	   private Pieces() {

	   } 

}
