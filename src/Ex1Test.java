// Ex1Test.java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Ex1Test {
    public static void main(String[] args) {

    }
    /*
     * בדיקה אם המספר בפורמט התקין לפי הדרישות
     */
    @Test
    void testIsValidNumber() {
        assertTrue(Ex1.isValidNumber("101b2")); // מספר בינארי
        assertTrue(Ex1.isValidNumber("FFb16")); // מספר הקסדצימלי
        assertTrue(Ex1.isValidNumber("A1bG")); // מספר בבסיס G
        assertFalse(Ex1.isValidNumber("123bH")); // בסיס לא חוקי
        assertFalse(Ex1.isValidNumber("b2")); // חסר מספר
        assertFalse(Ex1.isValidNumber("1b")); // חסר בסיס
        assertFalse(Ex1.isValidNumber("")); // מחרוזת ריקה
        assertFalse(Ex1.isValidNumber(null)); // ערך null
    }

    /*
     * בדיקה של המרה מבסיס לעשרוני
     */
    @Test
    void testConvertToDecimal() {
        assertEquals(5, Ex1.convertToDecimal("101b2")); // המרה מבינארי לעשרוני
        assertEquals(255, Ex1.convertToDecimal("FFb16")); // המרה מהקסדצימלי לעשרוני
        assertEquals(161, Ex1.convertToDecimal("A1bG")); // המרה מבסיס G לעשרוני
        assertThrows(IllegalArgumentException.class, () -> Ex1.convertToDecimal("123bH")); // בסיס לא חוקי
    }

    /*
     * בדיקה של המרה מעשרוני לבסיס נתון
     */
    @Test
    void testConvertFromDecimal() {
        assertEquals("101b2", Ex1.convertFromDecimal(5, 2)); // המרה לעשרוני לבינארי
        assertEquals("FFb16", Ex1.convertFromDecimal(255, 16)); // המרה לעשרוני להקסדצימלי
        assertEquals("A1bG", Ex1.convertFromDecimal(161, 16)); // המרה לעשרוני לבסיס G
        assertThrows(IllegalArgumentException.class, () -> Ex1.convertFromDecimal(10, 1)); // בסיס לא חוקי
    }

    /*
     * בדיקת חיבור של שני מספרים בבסיסים שונים
     */
    @Test
    void testAdd() {
        assertEquals("110b2", Ex1.add("101b2", "1b2", 2)); // חיבור בינארי
        assertEquals("10b16", Ex1.add("8b16", "8b16", 16)); // חיבור הקסדצימלי
        assertEquals("B2bG", Ex1.add("A1bG", "11bG", 16)); // חיבור בבסיס G
    }

    /**
     * בדיקת כפל של שני מספרים בבסיסים שונים
     */
    @Test
    void testMultiply() {
        assertEquals("1010b2", Ex1.multiply("101b2", "10b2", 2)); // כפל בינארי
        assertEquals("40b71", Ex1.multiply("8b16", "8b16", 16)); // כפל הקסדצימלי
        assertEquals("B9bG", Ex1.multiply("A1bG", "11bG", 16)); // כפל בבסיס G
    }

    /*
     * בדיקת מקרי קצה
     */
    @Test
    void testEdgeCases() {
        assertEquals("1b2", Ex1.add("1b2", "0b2", 2)); // חיבור עם אפס
        assertEquals("10000b2", Ex1.multiply("100b2", "100b2", 2)); // כפל בינארי גדול
        assertEquals("FFFb16", Ex1.add("FF0b16", "Fb16", 16)); // חיבור הקסדצימלי עם גלישה
        assertEquals("1000b16", Ex1.multiply("10b16", "100b16", 16)); // כפל הקסדצימלי גדול
    }
}
