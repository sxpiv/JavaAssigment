package Assigment1;


//GUI Interface - Import JFrame from Java API
import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

		
public class GUI extends JFrame implements ActionListener, MouseListener
{
	
	//Declare variables for Graphical panel
			private JPanel panel;
			private JFrame frame;
			private JButton menu1;
			private JButton menu2;
			private JButton menu3;
			private JButton menu4;
			private JButton menu5;
			private JButton help;
			
			
	//use external listener file
	myListener handler = new myListener();
	
			
	/** 
	 * 
	 * Lunch Application
	 */


	//Create a Constructor
	public GUI()
	{
		frame = new JFrame("Solar P.V Calculator Software");
		frame.setVisible(true);
		frame.setSize(1000,200);
		
		//Close program on case of red X pressed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		panel = new JPanel();
		panel.setBackground(Color.GREEN);
		
		
		//Instantiate - Creating an object
		menu1 = new JButton("Run Full Site Survey");
		menu2 = new JButton("Calc Available Roof Area");
		menu3 = new JButton("Add Panel Details to DB");
		menu4 = new JButton("Calc Solar P.V Array Area");
		menu5 = new JButton("Display Full Report");
		help = new JButton("Help");
		
		//Add Buttons to JPanel
		panel.add(menu1);
		panel.add(menu2);
		panel.add(menu3);
		panel.add(menu4);
		panel.add(menu5);
		panel.add(help);
		
		//Add JPanel to the frame and set Menu on the first Line
		frame.add(panel, BorderLayout.BEFORE_FIRST_LINE);

		
		
		//Register the button objects with a listener - Register the object
		menu1.addActionListener(this);
		menu2.addActionListener(this);
		menu3.addActionListener(this);
		menu4.addActionListener(this);
		menu5.addActionListener(this);
		help.addActionListener(this);
	
		
		//register mouse Listener
		addMouseListener(this);
 
	}//end main()
	
	
	//Implements Action Listeners internally
	public void actionPerformed(ActionEvent event)
	{
		
		//If "Full Site Survey" Selected, run the full program in a sequenced order
			if (event.getSource() == menu1)
			{
				JOptionPane.showMessageDialog(this, "This Function is Not available in DEMO version,"
						+ "Select menu between 2 to 4");

			}
			
			
			//If "Calculate Roof Area" is pressed, Execute CalcRoofArea.java
			else if (event.getSource() == menu2)
			{
				System.out.println("The CalcRoof Area is clicked");
				
				//Hide current frame
				frame.setVisible(false);
				
				//Call CalcRoofArea
				CalcRoofArea newScreen1 = new CalcRoofArea();
				
			}
			
			//Add new Panel Details to DB - Class
			//When "Calculate P.V Array" pressed, Execute CalcPVArray class
			else if (event.getSource() == menu3)
			{
				System.out.println("The button3 is clicked");
				
				//Hide current frame
				frame.setVisible(false);
				
				//Call CalcRoofArea
				AddPanelDetailsToDB newScreen3 = new AddPanelDetailsToDB();
				
			}
			
			
			//When "Calculate P.V Array" pressed, Execute CalcPVArray class
			else if (event.getSource() == menu4)
			{
				System.out.println("The button4 is clicked");
				
				//Hide current frame
				frame.setVisible(false);
				
				
				//Call Calc Solar PV Array Area
				CalcPvArrayArea newScreen3 = new CalcPvArrayArea();
			}
			
			
			//Print Full Report - report class
			else if (event.getSource() == menu5)
			{
				JOptionPane.showMessageDialog(this, "This Function is Not available in DEMO version");
			}
			//
			else if (event.getSource() == help)
			{
				JOptionPane.showMessageDialog(this, "Instructions: \n"
						+ "Click on any menu to start to use the program.\n \n"
						+ "To exit from the program gracefully:"
						+ "\nPress the OK button or the Red X on the right Corner for close this window."
						+ "\n\n"
						+ "Menus Functions:\n"
						+ "1) Run Full Site Survey: Will run the Full program in sequenced Order\n"
						+ "2) Calc Available Roof Area: Allows you to calculate the Absolute and Usable Roof Area in meter squares\n"
						+ "3) Add PV Panels details: Allows you to set the Solar PV panels lenght, and width for roof area comparison\n"
						+ "4) Calc P.V Array Area: Allows you to calculate how many P.V panels could fit in your examinated roof in different layouts\n"
						//+ "5) Commission Test: Will return you the expected results when you Test on your Solar P.V Array\n"
						+ "5) Display Full Report: When you run the full program, It will allows you to Display your results in a PDF format");
			}

	}

	@Override
	public void mouseClicked(MouseEvent event) 
	{
		if (event.getSource() == menu1)
		{
			System.out.println("The menu1 was clicked");
		}
		else
		{
			//System.out.println("Mouse is Working");
			JOptionPane.showMessageDialog(this, "Mouse is Working");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}//End Main
	
	
	
	