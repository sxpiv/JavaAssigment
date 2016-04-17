package Assigment1;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;


public class CalcPvArrayArea extends JFrame implements ActionListener, MouseListener
{

		
	private JButton calc, mainMenu, next;
	private JLabel roofUsableArea,PvArea, PossiblePvPanels;
	private JTextField outPutUsableArea, outPutPvPanelArea, outPutNoOfPvPanelsCanFit;
	
	
	
	public CalcPvArrayArea()
	{
		super ();
		
		//Set Windows Size and Layout
		setSize(340,300);
		setLayout(new FlowLayout());
		setTitle("Calculate Solar PV Array Area - Step 4 out of 5");
		
		//Close program on case of red X pressed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		//Register Labels for user input and output boxes
		roofUsableArea = new JLabel("The usable Roof Area is:");
		PvArea = new JLabel("One P.V Panel Area is:");
		PossiblePvPanels = new JLabel("The Possible P.V Panel can fit is:");
		
		
		
		//Register Calculate Button
		calc = new JButton("Show Me!");
		  
		
		//Register Text Boxes 
		outPutUsableArea = new JTextField("meter Square", 10);
		outPutPvPanelArea = new JTextField("meter Square", 10);
		outPutNoOfPvPanelsCanFit = new JTextField("meter Square",10);
		
		
		//Prevent User to Edit Output field - ERROR Prevention
		outPutUsableArea.setEditable(false);
		outPutPvPanelArea.setEditable(false);
		outPutNoOfPvPanelsCanFit.setEditable(false);
		
		
		//Add Labels and Text Fields to the frame
		add(roofUsableArea);
		add(outPutUsableArea);
		
		add(PvArea);
		add(outPutPvPanelArea);
		
		
		add(PossiblePvPanels);
		add(outPutNoOfPvPanelsCanFit);

		
		//Adding Return to main menu object
		mainMenu = new JButton("<= Return to Main Menu");
		add(mainMenu);
		
		
		//Add Calc Button
		add(calc);
		
		
		//Adding forward to next menu object
		next = new JButton("Run Commission Test =>");
		add(next);
		
		
		//Assign Action Listener to the objects
		mainMenu.addActionListener(this);
		next.addActionListener(this);
		calc.addActionListener(this);
		
		
		//register the mouse listener to the JFrame
		addMouseListener(this);
		
		
		//Set Frame visibility
		setVisible(true);
		
	}
	
	
	
	
	
	//initialize database connection driver
		public static void connection()
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("DB Driver Loaded successfull");
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	
		
		
		
		
	//Set Action Listeners Behaviors	
	public void actionPerformed(ActionEvent event )
	{
				
				//If Show me button pressed, execute the calculations
				if(event.getSource()==calc)
				{
				   //Connecting to Remote Database
					connection();
					
					
					//define credential for the Database
					String host = "jdbc:mysql://localhost/test";
					String username = "root";
					String password = "";
					String PvPanelAccomodationCalc="";

					//Try to connect to Database
					 try 
					 {
					     
						 Connection connect = (Connection) DriverManager.getConnection(host, username, password);
							PreparedStatement statement = (PreparedStatement) 
									
								
							//Read Solar P.V Panel Details from Database
							connect.prepareStatement("SELECT * FROM  (RoofWidth,RoofLenght,TotalRoofArea, UsableRoofArea, Brand,Version, Width,Height,OutputPower,PanelArea)");
							
					     
					     //display data in table
					     Statement st=connect.createStatement();
					     String sql = "select * from roof, pvpanels";
					     ResultSet rs=st.executeQuery(sql);
					     while(rs.next())
					     {
					    	 //Print Roof Details 
					    	 System.out.println("The Roof Width is: " + rs.getString(1));
					    	 System.out.println("The Roof Lenght is: " + rs.getString(2));
					    	 System.out.println("The Total Roof Area is: " + rs.getString(3));
					    	 System.out.println("The Roof Total Usable Area is: " + rs.getString(4));
					    	 
					    	 //Print Solar PV Panel Details
					    	 System.out.println("PV Panel Brand is: " + rs.getString(5));
					    	 System.out.println("PV Panel Version is: " + rs.getString(6));
					    	 System.out.println("PV Panel Width is: " + rs.getString(7));
					    	 System.out.println("PV Panel Height is: " + rs.getString(8));
					    	 System.out.println("PV Panel OutputPower is: " + rs.getString(9));
					    	 System.out.println("PV Panel Area is: " + rs.getString(10));
					    	 
			
					    	
					    	 //Compare
					    	 PvPanelAccomodationCalc = (rs.getString(3) + rs.getString(10));
								
								
					    	 //Display Results
					    	 System.out.println("\n\n The Usable area of the Roof is: " + rs.getString(4) + " MeterSquare");
					    	 System.out.println("\n Each Solar PV Panel Area is: " + rs.getString(10) + " Meter Square");
					    	 System.out.println("\n The maximum number of PV Panel can be fitted is: " + PvPanelAccomodationCalc  + " Piece");
						     
					    	 
					    	 /* This part is not completed yet*/
						    //Hide current frame
							//setVisible(false);
								
								
					    	//Display Results in the GUI interface
					    	 //roofUsableArea.setText(String.valueOf(rs.getString(4)));
					    	 //outPutPvPanelArea.setText(String.valueOf(rs.getString(10)));
					    	 //outPutNoOfPvPanelsCanFit.setText(String.valueOf(PvPanelAccomodationCalc));
					     
					     }//end while     

					 }//end try
					 
					 
					 
					 
					 	catch (SQLException e) 
					 	{
					 	    System.out.println ("SQL Exception Catched");
					 	}
					 }
				
				
				 // Close Database Connection 
				 //conn.close()
				
				
				
				
				
				//"Return to Main Menu" button behavior
				else if (event.getSource() == mainMenu)
				{
					System.out.println("The Program Return to the Main Menu");
					
					//Hide current frame
					setVisible(false);
					
					//Call Main Menu
					GUI newScreen = new GUI();
					
				}
				
				
				
				
				//Executing P.V Array Commission Test
				else if (event.getSource() == next)
				{
					 
					 //Call the next menu (CommissionTest class)
					 {
						 System.out.println("Comission Test is not available in the Demo Verison!");
						 
						
						 
				//Display Warning Message about the Program Limitation	 
				 JOptionPane.showMessageDialog(this, "This Option is not available in the Demo Version "
									+ "Contact the developer and / or Press any other Button!");
						 
						 /*This part will be available in the Full Version*/
						 
						 //Hide current frame
						//setVisible(false);
						
						//Call Commission Test
						//CommissionTest newScreen4 = new CommissionTest();
					 }
				
			}
				
				//If no object where pressed
				else
				{
					JOptionPane.showMessageDialog(this, "You have not selected a Valid Option, "
							+ "Press a Button to continue!");
				}
				  
	}

	
	public static void main(String args[])
	 {
		CalcPvArrayArea a=new CalcPvArrayArea();
	  a.setVisible(true);
	  a.setLocation(340,300);
	  
	 }
			

	@Override
	public void mouseClicked(MouseEvent event) 
	{
		if (event.getSource() == calc)
		{
			System.out.println("The textbox was clicked");
		}
		else
		{
			//System.out.println("Mouse is Working");
			JOptionPane.showMessageDialog(this, "Press the Show Calculation Button");
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

}
