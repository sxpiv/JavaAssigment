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
import java.sql.SQLException;


import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;


public class CalcRoofArea extends JFrame implements ActionListener, MouseListener
{

		
	private JButton calc, mainMenu, next;
	private JLabel roofWidth, roofLenght, roofTotalArea, roofUsableArea, MeterSquareSymbol;
	private JTextField input1, input2, outputTotal, outputUsable;
	
	
	
	public CalcRoofArea()
	{
		super ();
		
		//Set Windows Size and Layout
		setSize(360,300);
		setLayout(new FlowLayout());
		setTitle("Roof Area Calculation - Step 2 out of 5");
		
		//Close program on case of red X pressed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		//Register Labels for user input and output boxes
		roofWidth = new JLabel("Enter Roof Width Here:");
		roofLenght = new JLabel("Enter Roof Height Here:");
		roofTotalArea = new JLabel("Roof Total Area is:");
		roofUsableArea = new JLabel("Roof Usable Area is:");
		MeterSquareSymbol = new JLabel("m");
		
		
		
		//Register Calculate Button
		calc = new JButton("Show Me and Save!");
		  
		
		//Register Text Boxes for user input
		input1 = new JTextField(10);
		input2 = new JTextField(10);
		outputTotal = new JTextField("meter Square", 10);
		outputUsable = new JTextField("meter Square",10);
		
		
		//Prevent User to Edit Output field - ERROR Prevention
		outputTotal.setEditable(false);
		outputUsable.setEditable(false);
		
		
		//Add Labels and Text Fields to the frame
		add(roofWidth);
		add(input1);
		add(MeterSquareSymbol);
		
		add(roofLenght);
		add(input2);
		add(MeterSquareSymbol);
		
		add(roofTotalArea);
		add(outputTotal);
		
		add(roofUsableArea);
		add(outputUsable);

		
		//Adding Return to main menu object
		mainMenu = new JButton("<= Return to Main Menu");
		add(mainMenu);
		
		
		//Add Calc Button
		add(calc);
		
		
		//Adding forward to next menu object
		next = new JButton("ADD P.V Modules Details =>");
		add(next);
		
		
		//Assign Action Listener to the objects
		input1.addActionListener(this);
		input2.addActionListener(this);
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
		
		
				//set local variables for user input
				float widht,lenght,c;
				double d;
				String string1="";
				String string2="";
				
				String RoofWidth=null, RoofLenght = null, TotalRoofArea=null, UsableRoofArea=null;

				
				
				
				//Taking RoofWidth input
				if (event.getSource() == input1)
				{
					string1=String.format("RoofWidth is : %s (meter)", event.getActionCommand());
					JOptionPane.showMessageDialog(this, "Now, Fill Roof Lenght as well!");
					
				}
				
				
				
				
				//Taking RoofLenght input
				else if (event.getSource() == input2)
				{
					string2=String.format("RoofLenght is : %s (meter)", event.getActionCommand());
					JOptionPane.showMessageDialog(this, "Now, Press the Show Me Button!");
				}
				
				
				
				//If Show me button pressed, execute the calculations
				else if(event.getSource()==calc)
				  {
				   RoofWidth = input1.getText();
				   RoofLenght = input2.getText();
				   
				   
				   widht=Float.parseFloat(RoofWidth);
				   lenght=Float.parseFloat(RoofLenght);
				   c = (widht * lenght);
				   d = (widht - 0.5) + (lenght - 0.5);
				   System.out.println("The Roof area in meter square is: " + c + " Meter Square");
				   outputTotal.setText(String.valueOf(c) + " MeterSq");
				   outputUsable.setText(String.valueOf(d) + " MeterSq");
				 
				 
				   
				   
				  //Connecting to Remote Database
					connection();
					
					
					//define credential for the Database
					String host = "jdbc:mysql://localhost/test";
					String username = "root";
					String password = "";
					
						try {
							Connection connect = (Connection) DriverManager.getConnection(host, username, password);
						PreparedStatement statement = (PreparedStatement) connect.prepareStatement("INSERT INTO roof (RoofWidth,RoofLenght, TotalRoofArea,UsableRoofArea) VALUES(?,?,?,?)");
					
						
						//set write statements
						statement.setString(1, RoofWidth);
						statement.setString(2, RoofLenght);
						statement.setFloat(3, c);
						statement.setDouble(4, d);
						
						//execute query
						statement.executeUpdate();
						
						
						System.out.println("DB Connection established");
						System.out.println("Roof Area Details Saved");
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						//Close the DB connection
						//conn.close();
						
				  }
				
				
				
				
				//"Return to Main Menu" button behavior
				else if (event.getSource() == mainMenu)
				{
					System.out.println("The Program Return to the Main Menu");
					
					//Hide current frame
					setVisible(false);
					
					//Call Main Menu
					GUI newScreen = new GUI();
					
				}
				
				
				
				
				//Add PV Panels details to Database
				else if (event.getSource() == next)
				{
					 
					 //Call the next menu (Upload / Defined Solar P.V Modules Details)
					 {
						 System.out.println("Executing P.V Panel Details upload");
						 
						//Hide current frame
						setVisible(false);
						
						//Call Add PV Panel Details	
						AddPanelDetailsToDB newScreen3 = new AddPanelDetailsToDB();
					 }
				
			}
				
				//If no object where pressed
				else
				{
					JOptionPane.showMessageDialog(this, "You have not selected an Option, "
							+ "Enter values in the Field and / or Press a Button!");
				}
				  
	}

	
	public static void main(String args[])
	 {
		CalcRoofArea a=new CalcRoofArea();
	  a.setVisible(true);
	  a.setLocation(200,200);
	  
	 }
			

	@Override
	public void mouseClicked(MouseEvent event) 
	{
		if (event.getSource() == roofWidth)
		{
			System.out.println("The textbox was clicked");
		}
		else
		{
			//System.out.println("Mouse is Working");
			JOptionPane.showMessageDialog(this, "Enter details in TextFields or Press any Button!");
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
