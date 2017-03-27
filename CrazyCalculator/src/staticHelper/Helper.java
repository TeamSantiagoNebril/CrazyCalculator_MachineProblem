package staticHelper;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class Helper
{
	
	public static Font createFromExternalFont(String fontDirectory, float fontSize)
	{
		File font_file = new File(fontDirectory);
		Font font = null;
		try {
			//fontKeys = Font.createFont(Font.TRUETYPE_FONT, fontFileKey).deriveFont(18f);
			font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(fontSize);
		} catch (FontFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return font;
	}
}
