public class isValidCoords {
    /*
    * Determines if coordinates is valid, meaning if it is a 2 character long string consisting of
    * a letter from A-E followed by a number from 1 - 5
    */
    private static boolean isValidCoords(String coords){
        return (coords.length() == 2 && coords.charAt(0) >= 65 && coords.charAt(0) <= 69 &&
                coords.charAt(1) >= 49 && coords.charAt(1) <= 53); // ASCII (65 = A, 69 = E, 49 = 1, 53 = 5)
    }
}
