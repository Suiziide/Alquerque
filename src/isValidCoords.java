public class isValidCoords {
    /*
    * Determines if coordinates is valid, meaning if it is a 2 character long string consisting of
    * a letter from A-E followed by a number from 1 - 5
    */
    public static void main(String[] args) {
        String test = "E5";
        if (isValidCoords(test))
            System.out.println(convertCoordinate(test));
    }
    private static boolean isValidCoords(String coords){
        return (coords.matches("[A-E][1-5]")); // Regex for matching
    }
    private static int convertCoordinate(String coord){
        int position = 0;
        switch(coord.charAt(0)){
            case 'A':
                position = (1+(5*((Integer.parseInt(coord.substring(1))-1))));
                break;
            case 'B':
                position = (2+(5*((Integer.parseInt(coord.substring(1))-1))));
                break;
            case 'C':
                position = (3+(5*((Integer.parseInt(coord.substring(1))-1))));
                break;
            case 'D':
                position = (4+(5*((Integer.parseInt(coord.substring(1))-1))));
                break;
            case 'E':
                position = (5+(5*((Integer.parseInt(coord.substring(1))-1))));
                break;
            default:
                return 0;
        }
        return position;
    }
}
