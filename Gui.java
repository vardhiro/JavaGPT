import java.io.*;
import javax.swing.*;
import java.awt.*;
class Gui {
	static String user = "";
	static String response = "";
	public static String getResponse(String q) throws Exception {
	    String command = " python request.py \""+q+"\" ", res="";
	    Process child = Runtime.getRuntime().exec(command);

	    InputStream in = child.getInputStream();
	    int c;
	    while ((c = in.read()) != -1) {
	      res += (char)c;
	    }
	    in.close();
	    return res;
    }
    public static void main(String args[]) {

        //Creating the Frame
        JFrame frame = new JFrame("JavaGPT: Your ChatGPT client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(600, 600);

        //Creating the MenuBar and adding components
        /*
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);
		*/
		
        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Your Question: ");
        JTextField tf = new JTextField(32); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        JTextArea textArea = new JTextArea(1,1);
         textArea.setLineWrap(true);
        textArea.setEditable(false);
		JScrollPane scroll = new JScrollPane (textArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.add(scroll);

		Font font = new Font("Calibri", Font.BOLD, 18);
        textArea.setFont(font);
		// Function of send
		send.addActionListener(e ->
        {
            user = tf.getText();
            textArea.append("\nYou: "+user);
            try{
            	response = getResponse(user);
            	textArea.append("\nGPT: "+response.trim());
            }catch(Exception exception){
            	System.exit(-1);
            }
			tf.setText("");
        });

        reset.addActionListener(e -> 
        {
        	textArea.setText("");
        });
        

        //Adding Components to the frame.
        frame.getContentPane().add(java.awt.BorderLayout.SOUTH, panel);
        frame.getContentPane().add(java.awt.BorderLayout.CENTER, textArea);
        frame.setVisible(true);
    }
}
