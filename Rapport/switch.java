switch (Character.toUpperCase(color.charAt(0))) {
                            case 'B':
                                System.out.println("\nYou have chosen to play black.\n" +
                                        "The CPU will therefore play white");
                                System.out.print("Please enter the name of the player: ");
                                blackName = reader.nextLine().trim();
                                System.out.println();
                                isWhiteCPU = true;
                                break;
                            case 'W':
                                System.out.println("\nYou have chosen to play white.\n" +
                                        "The CPU will therefore play black");
                                System.out.print("Please enter the name of the player: ");
                                whiteName = reader.nextLine().trim();
                                isBlackCPU = true;
                                break;
                            default:
                                System.out.println("'" + color + "'" + " is not a valid color " +
                                        "option, please try again.\n");
                        }