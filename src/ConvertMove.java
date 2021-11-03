import java.util.Scanner;
public class ConvertMove{
    public static void main(String[]args){
        System.out.println(convertCoordinate("C3"));
    }

    /*
     * Converts a coordinate to a value (to determine board position for methods
     */
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

