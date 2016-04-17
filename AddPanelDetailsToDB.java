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


public class AddPanelDetailsToDB extends JFrame implements ActionListener, MouseListener
{

	//Declare buttons
	private JButton calc, mainMenu, next;
	
	//Declare Labels
	private JLabel pvPanelBrand, BrandExample, VerExample, pvPanelVer, WidthExample, HeightExample, 
	pvPanelWidth, pvPanelHeight, pvPanelOutPutPower, OutputExample, PanelArea, MeterSquareSymbol;
	
	
	//Declare TextFields for user input
	private JTextField panelBrand, panelVer, panelWidth, panelHeight, 
	panelOutput, PvPanelAreaDisplay;
	
	
	
	public AddPanelDetailsToDB()
	{
		super ();
		
		//Set Windows Size and Layout
		setSize(470,300);
		setLayout(new FlowLayout());
		setTitle("Adding New PV Panels Details to DB - Step 3 out of 5");
		
		//Close program on case of red X pressed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		//Register Labels for user input and output boxes
		pvPanelBrand = new JLabel("Enter the PV Panel Brand Here:");
		BrandExample = new JLabel("Eg: LG");
		pvPanelVer = new JLabel("Enter the PV Panel Version Here:");
		VerExample = new JLabel("Eg: SC123AB");
		pvPanelWidth = new JLabel("Enter the PV Panel Width Here:");
		WidthExample = new JLabel("Eg: 1m");
		pvPanelHeight = new JLabel("Enter the PV Panel Height Here:");
		HeightExample = new JLabel("Eg: 1.65m");
		pvPanelOutPutPower = new JLabel("Enter the Panel Output in Watts:");
		OutputExample = new JLabel("Eg: 300W");
		PanelArea = new JLabel("The PV Panel Area is:");
		MeterSquareSymbol = new JLabel("Meter Square");
		
		
		
		//Register Calculate Button
		calc = new JButton("Show Me The PV Module Area and Save!");
		  
		
		//Register Text Boxes for user input
		panelBrand = new JTextField(10);
		panelVer = new JTextField(10);
		panelWidth = new JTextField(10);
		panelHeight = new JTextField(10);
		panelOutput = new JTextField(10);
		PvPanelAreaDisplay = new JTextField(10);
		
		
		//Prevent User to Edit Output field - ERROR Prevention
		PvPanelAreaDisplay.setEditable(false);
		
		
		//Add Labels and Text Fields to the frame
		add(pvPanelBrand);
		add(panelBrand);
		add(BrandExample);
		
		add(pvPanelVer);
		add(panelVer);
		add(VerExample);

		
		add(pvPanelWidth);
		add(panelWidth);
		add(WidthExample);
		
		add(pvPanelHeight);
		add(panelHeight);
		add(HeightExample);
		
		
		add(pvPanelOutPutPower);
		add(panelOutput);
		add(OutputExample);
		
		add(PanelArea);
		add(PvPanelAreaDisplay);
		add(MeterSquareSymbol);

		
		//Adding Return to main menu object
		mainMenu = new JButton("<= Return to Main Menu");
		add(mainMenu);
		
		
		//Add Calc Button
		add(calc);
		
		
		//Adding forward to next menu object
		next = new JButton("Run P.V Array Calculation =>");
		add(next);
		
		
		
		//Assign Action Listener to the objects
		panelBrand.addActionListener(this);
		panelVer.addActionListener(this);
		panelWidth.addActionListener(this);
		panelHeight.addActionListener(this);
		panelOutput.addActionListener(this);
		mainMenu.addActionListener(this);
		calc.addActionListener(this);
		next.addActionListener(this);
		
		
		

		
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
				float widht,lenght, PvPanelArea, PanelPower;
				String BrandInput="", VerInput="", PvPanelWidthInput="", PvPanelHeightInput="", 
						PvPanelOutput="", PvWidth="", PvHeight="", Power;
		
				
				//Taking PV Panel Brand - input
				if (event.getSource() == panelBrand)
				{
					BrandInput=String.format("Panel Brand is: %s", event.getActionCommand());
					JOptionPane.showMessageDialog(this, "Now, Fill PV Panel Version textbox!");
					
					System.out.println(BrandInput);
				}
				
				
				
				//Taking PV Panel Version - input
				else if (event.getSource() == panelVer)
				{
					VerInput=String.format("PV Panel Version is : %s", event.getActionCommand());
					JOptionPane.showMessageDialog(this, "Now, Fill PV Panel Width textbox!");
					
					System.out.println(VerInput);
				}
				
				
				
				//Taking PV Panel Width - input
				else if (event.getSource() == panelWidth)
				{
					PvPanelWidthInput=String.format("Solar PV Panel Width is : %s (meter)", event.getActionCommand());
					JOptionPane.showMessageDialog(this, "Now, Fill PV Panel Height textbox!");
					
					System.out.println(PvPanelWidthInput);
				}
				
				
				//Taking PV Panel Height - input
				else if (event.getSource() == panelHeight)
				{
					PvPanelHeightInput=String.format("Solar PV Panel Height : %s (meter)", event.getActionCommand());
					JOptionPane.showMessageDialog(this, "Now, Press the Show The Area Button!");
					
					System.out.println(PvPanelHeightInput);
				}
				
				
				//Taking PV Panel Power Output - input
				else if (event.getSource() == panelOutput)
				{
					PvPanelOutput=String.format("Solar PV Panel Output is : %s (Watts)", event.getActionCommand());
					JOptionPane.showMessageDialog(this, "Now, Press the Show The Area Button!");
					
					System.out.println(PvPanelOutput);
				}
				
				
				
				//If Show me button pressed, execute the calculations
				else if(event.getSource()==calc)
				  {
					
				  // Get user input and store
				   BrandInput = panelBrand.getText();
				   VerInput = panelVer.getText();
				   PvWidth = panelWidth.getText();
				   PvHeight = panelHeight.getText();
				   Power = panelOutput.getText();
				   
				   
				   //convert string to float for maths calculation
				   widht=Float.parseFloat(PvWidth);
				   lenght=Float.parseFloat(PvHeight);
				   PanelPower=Float.parseFloat(Power);

				   PvPanelArea = (widht * lenght);
				   PvPanelAreaDisplay.setText(String.valueOf(PvPanelArea));
				  
				   
				   System.out.println("The Solar PV area in meter square is: " + PvPanelArea + " Meter Square");
				 
				   
				   
				   
				  //Connecting to Remote Database
					connection();
					
					
					//define credential for the Database
					String host = "jdbc:mysql://localhost/test";
					String username = "root";
					String password = "";
					
						try {
							Connection connect = (Connection) DriverManager.getConnection(host, username, password);
						PreparedStatement statement = (PreparedStatement) 
								connect.prepareStatement("INSERT INTO pvpanels (Brand,Version, Width,Height,OutputPower,PanelArea) VALUES(?,?,?,?,?,?)");
					
						
						//set write statements
						statement.setString(1, BrandInput);
						statement.setString(2, VerInput);
						statement.setString(3, PvWidth);
						statement.setString(4, PvHeight);
						statement.setFloat(5, PanelPower);
						statement.setFloat(6, PvPanelArea);
						
						System.out.println(PanelPower);
						
						//execute query
						statement.executeUpdate();
						
						
						System.out.println("DB Connection established");
						System.out.println("PV Panel Details Saved");
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						//Close the DB connection
						//connection.close();
						
				  }
				
				
				
				
				//"Return to Main Menu" button behavior
				else if (event.getSource() == mainMenu)
				{
					GUI newScreen = new GUI();
					setVisible(false);
					System.out.println("The Program Return to the Main Menu");
				}
				
				
				
				
				//"Next Menu" button behavior
				else if (event.getSource() == next)
				{
					 
					 //Call the next menu (Calc P.V Array)
					 {
						 	//Call Calc Solar PV Array Area
						 	System.out.println("Executing P.V array Calculation");
						 	setVisible(false);
							CalcPvArrayArea newScreen3 = new CalcPvArrayArea();
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
		AddPanelDetailsToDB a=new AddPanelDetailsToDB();
	  a.setVisible(true);
	  a.setLocation(200,200);
	  
	 }
			

	@Override
	public void mouseClicked(MouseEvent event) 
	{
		if (event.getSource() == pvPanelWidth)
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
