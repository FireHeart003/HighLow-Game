package Game;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class Window {
	/*
	 * A class to easily use JOptionPanes to display messages
	 */
	
	private static JFrame j;
	
	//Constructor`
	public Window() {
		j = new JFrame();
	}
	
	//Displays a JOptionPane with a message, and another message at the top of the JOptionPane in the bar that's at the top
	public static void msg(String msg, String msg1) {
		JOptionPane.showMessageDialog(null, msg, msg1, JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Displays a JOptionPane with a message
	public static void msg3(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	//Returns the string of the user's input
	public static String in(JFrame fram, String msg) {
		return JOptionPane.showInputDialog(fram, msg);
	}
	 
	//Displays a message and options for user to choose from
	public static int option(String[] options, String msg) {
		return JOptionPane.showOptionDialog(
				j, 
				msg, // my message
                "Click a button", // dialog box title
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                options, // possible options
                options[0]); // default option
		
	}
	
	//Displays a message, options for user to choose from, and an Image to go along with it
	public static int option1(String[] options, String msg, Icon i) {
        return JOptionPane.showOptionDialog(
                j, 
                msg, // my message
                null, // dialog box title
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE, 
                i, 
                options, // possible options
                options[0]); // default option
    }
	
	//Prints a message and skips a line
	public static void println(String msg) {
		System.out.println(msg);
	}
	
	//Prints a message	
	public static void print(String msg) {
		System.out.println(msg);
	}
	
	//Returns the string of the user's input
	public static String in(Object msg) {
		return JOptionPane.showInputDialog(msg);
	}
	
	//Displays a message with an icon
	public static void msg2(String msg, Icon i){
        JOptionPane.showMessageDialog(
                j, 
                msg, 
                null, 
                JOptionPane.DEFAULT_OPTION, 
                i); 
    }
}

