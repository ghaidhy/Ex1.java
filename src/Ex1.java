public class Ex1 { //214756645
    public static void main(String[] args) {

    }
    /*
     * בדיקה אם המחרוזת היא מספר בפורמט התקין
     * @param s המחרוזת לבדיקה
     * @return נכון אם המחרוזת תקינה, אחרת שקר
     */
    public static boolean isValidNumber(String s) {
        if (s == null || s.isEmpty()) return false;
        String[] parts = s.split("b");
        if (parts.length != 2) return false;

        String number = parts[0];
        String baseStr = parts[1];

        // המרת הבסיס לעשרוני
        int base;
        try {
            base = baseStr.matches("[A-G]") ? 10 + (baseStr.charAt(0) - 'A') : Integer.parseInt(baseStr);
        } catch (NumberFormatException e) {
            return false;
        }

        if (base < 2 || base > 16) return false;

        // בדיקת המספר הנתון
        for (char c : number.toCharArray()) {
            if (Character.isDigit(c)) {
                if (Character.getNumericValue(c) >= base) return false;
            } else if (Character.isLetter(c)) {
                if (base <= 10 || c < 'A' || c >= 'A' + (base - 10)) return false;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * המרת מספר בפורמט מסוים לעשרוני
     * @param s הפלט הוא בפורמט מספר
     * @return הערך העשרוני של המספר
     */
    public static int convertToDecimal(String s) {
        if (!isValidNumber(s)) throw new IllegalArgumentException("Invalid number format");

        String[] parts = s.split("b");
        String number = parts[0];
        int base = parts[1].matches("[A-G]") ? 10 + (parts[1].charAt(0) - 'A') : Integer.parseInt(parts[1]);

        int decimalValue = 0;
        for (char c : number.toCharArray()) {
            decimalValue = decimalValue * base + Character.getNumericValue(c);
        }
        return decimalValue;
    }

    /*
     * המרת מספר עשרוני לצורת הבסיס הרצויה
     * @param number הערך העשרוני
     * @param base הבסיס הנדרש
     * @return הפלט הוא בצורת בסיס
     */
    public static String convertFromDecimal(int number, int base) {
        if (base < 2 || base > 16) throw new IllegalArgumentException("Base must be between 2 and 16");

        StringBuilder result = new StringBuilder();
        while (number > 0) {
            int remainder = number % base;
            if (remainder < 10) {
                result.append((char) ('0' + remainder));
            } else {
                result.append((char) ('A' + (remainder - 10)));
            }
            number /= base;
        }
        return result.reverse().toString() + "b" + (base > 10 ? (char) ('A' + (base - 10)) : base);
    }

    /*
     הוסף שני מספרים בפורמט מסוים והחזר את התוצאה בפורמט הבסיס הנדרש
     * @param num1 המספר הראשון
     * @param num2 המספר השני
     * @param base הבסיס הנדרש
     * @return תוצאת ההוספה
     */
    public static String add(String num1, String num2, int base) {
        int decimal1 = convertToDecimal(num1);
        int decimal2 = convertToDecimal(num2);
        int sum = decimal1 + decimal2;
        return convertFromDecimal(sum, base);
    }

    /*
     * הכפל שני מספרים בנוסחה ספציפית והחזר את התוצאה בצורת הבסיס הנדרשת
     * @param num1 המספר הראשון
     * @param num2 המספר השני
     * @param base הבסיס הנדרש
     * @return תוצאת הכפל
     */
    public static String multiply(String num1, String num2, int base) {
        int decimal1 = convertToDecimal(num1);
        int decimal2 = convertToDecimal(num2);
        int product = decimal1 * decimal2;
        return convertFromDecimal(product, base);
    }
}
