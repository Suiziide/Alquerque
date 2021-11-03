public class isValidCoords {
    /*
    * Determines if coordinates is valid, meaning if it is a 2 character long string consisting of
    * a letter from A-E followed by a number from 1 - 5
    */
    private static boolean isValidCoords(String coords){
        return (coords.matches("[A-E][1-5]")); // Regex for matching
    }
}
