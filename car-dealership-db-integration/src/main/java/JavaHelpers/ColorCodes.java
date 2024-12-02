package JavaHelpers;

public class ColorCodes
{
    //Global reset for all text
    public static final String RESET = "\u001B[0m";

    //Normal colors
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String ORANGE = "\u001B[36m";
    public static final String GRAY = "\u001B[37m";
    public static final String CYAN = "\033[38;5;51m";
    public static final String LIGHT_BLUE = "\033[38;5;153m";
    public static final String PINK = "\033[38;5;201m";
    public static final String WHITE = "\033[38;5;255m";

    //Bold colors
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String ORANGE_BOLD = "\033[1;36m";
    public static final String GRAY_BOLD = "\033[1;37m";
    public static final String CYAN_BOLD = "\033[1;38;5;51m";
    public static final String LIGHT_BLUE_BOLD = "\033[1;38;5;153m";
    public static final String PINK_BOLD = "\033[1;38;5;201m";
    public static final String WHITE_BOLD = "\033[1;38;5;255m";

    //Underline text colors
    public static final String BLACK_UNDERLINED = "\033[4;30m";
    public static final String RED_UNDERLINED = "\033[4;31m";
    public static final String GREEN_UNDERLINED = "\033[4;32m";
    public static final String YELLOW_UNDERLINED = "\033[4;33m";
    public static final String BLUE_UNDERLINED = "\033[4;34m";
    public static final String PURPLE_UNDERLINED = "\033[4;35m";
    public static final String ORANGE_UNDERLINED = "\033[4;36m";
    public static final String GRAY_UNDERLINED = "\033[4;37m";
    public static final String CYAN_UNDERLINED = "\033[4;38;5;51m";
    public static final String LIGHT_BLUE_UNDERLINED = "\033[4;38;5;153m";
    public static final String PINK_UNDERLINED = "\033[4;38;5;201m";
    public static final String WHITE_UNDERLINED = "\033[4;38;5;255m";

    //Background colors
    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ORANGE_BACKGROUND = "\u001B[46m";
    public static final String GRAY_BACKGROUND = "\u001B[47m";
    public static final String CYAN_BACKGROUND = "\033[48;5;51m";
    public static final String LIGHT_BLUE_BACKGROUND = "\033[48;5;153m";
    public static final String PINK_BACKGROUND = "\033[48;5;201m";
    public static final String WHITE_BACKGROUND = "\033[48;5;255m";

    //Action message colors
    public static final String SUCCESS = "\033[38;5;46m";
    public static final String FAIL = "\033[38;5;196m";

    //Effects
    public static final String ITALIC = "\033[3m";
    public static final String BLINK_MODE = "\033[5m";

    private ColorCodes(){}
}
