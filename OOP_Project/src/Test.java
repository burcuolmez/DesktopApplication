package src;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;


public class Test {
	
	
	static long num;
	static FileWR f = new FileWR();
	static Management m = new Management();
	
	public static void process(JFrame frameTest,String nickname) throws QueueFull, QueueEmpty{
		Processor prc = new Processor(frameTest,nickname);
		prc.main(null);
	}
	
	public static void main(String[] args) throws IOException, QueueFull, QueueEmpty {
		ApiClient api = new ApiClient();
		Management m = new Management();
		m.defaultAdmin();
		f.FileRead();
		mainMenu();
	}

	

	public static void mainMenu() {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
            	f.fileWrite();
                e.getWindow().dispose();
            }
        });
		
		frame.getContentPane().setLayout(null);
		frame.setTitle("EVENTS");

		Button button = new Button("LOG-IN");
		button.setBackground(new Color(219, 112, 147));
		button.setBounds(178, 108, 83, 27);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				
				
				String nickname=JOptionPane.showInputDialog("Nickname: ");
				JPanel panel = new JPanel();
				JLabel label = new JLabel("Enter a password:");
				JPasswordField pass = new JPasswordField(10);
				panel.add(label);
				panel.add(pass);
				String[] options = new String[]{"OK", "Cancel"};
				int option = JOptionPane.showOptionDialog(null, panel, "Input",
				                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				                         null, options, options[0]);
				char[] passw = null;
				if(option == 0) 
				{
				    passw = pass.getPassword();
				}
				
				String password = new String(passw);
				
				
				if (m.Login(nickname, password)) {
					if(m.getActors().get(nickname).getType().equals("user")){
						User u = (User) m.getActors().get(nickname);
						if(!u.isBlockked()) {
							JOptionPane.showMessageDialog(null,"Log-in Successfuly");
								try {
									frame.setVisible(false);
									process(frame,nickname);
								} catch (QueueFull | QueueEmpty e1) {
									e1.printStackTrace();
								}
							
							
						}
						else {
							JOptionPane.showMessageDialog(null,"Your account has been blocked!");
							System.exit(0);
						}
					}
					else {
						frame.setVisible(false);
						JFrame adminPage = new JFrame();
						adminPage.setBounds(100, 100, 450, 300);
						adminPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						adminPage.getContentPane().setLayout(null);
						adminPage.addWindowListener(new WindowAdapter()
				        {
				            public void windowClosing(WindowEvent e)
				            {
				            	f.fileWrite();
				                e.getWindow().dispose();
				            }
				        });
						 ArrayList<JButton> buttons = new ArrayList<JButton>();
						
						JButton btnGetNotification = new JButton("Get Notification");
						Admin a = (Admin) m.getActors().get(nickname);
						btnGetNotification.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e ) {
								adminPage.setVisible(false);
								 JFrame nf = new JFrame();
								    nf.setBounds(100, 100, 450, 300);
								    nf.setLocationRelativeTo(null);
									nf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									nf.setTitle("ADMIN-PAGE");
									nf.addWindowListener(new WindowAdapter()
							        {
							            public void windowClosing(WindowEvent e)
							            {
							            	f.fileWrite();
							                e.getWindow().dispose();
							            }
							        });
									JPanel panel_1 = new JPanel();
									 panel_1.setBounds(81, 37, 289, 184);
									 panel_1.setLayout(new GridLayout(0, 1, 0, 0));
									
								        nf.getContentPane().add( panel_1);
								        final JScrollPane scroll_1 = new JScrollPane(panel_1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
								                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
								
								for (int i=0;i<a.getNotifications().size();i++) {
									buttons.add( new JButton(a.getNotifications().get(i).getNickname()));
									   buttons.get(i).setBackground(Color.MAGENTA);
									   User u =a.getNotifications().get(i);
									   buttons.get(i).addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												nf.setVisible(false);
												JFrame sf = new JFrame();
												sf.setBounds(100, 100, 450, 300);
												sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
												sf.getContentPane().setLayout(null);
												sf.addWindowListener(new WindowAdapter()
										        {
										            public void windowClosing(WindowEvent e)
										            {
										            	f.fileWrite();
										                e.getWindow().dispose();
										            }
										        });
												JButton btn= new JButton("Block User");
												btn.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														a.blockUser(u.getNickname());
														JOptionPane.showMessageDialog(null,u.getNickname()+" has blocked.");
													}
												});
												btn.setBounds(38, 148, 133, 23);
												sf.getContentPane().add(btn);
												
												JButton btn1 = new JButton("Go Back");
												btn1.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
													     sf.setVisible(false);
													     nf.setVisible(true);
													}
												});
												btn1.setBounds(242, 148, 133, 23);
												sf.getContentPane().add(btn1);
												
												JLabel lblNewLabel = new JLabel(u.getNickname());
												lblNewLabel.setBounds(38, 47, 46, 14);
												sf.getContentPane().add(lblNewLabel);
												
												for(Event e :m.getEvents().values()) {
													if(e.getComments().size()!=0) {
														for (Comment c :e.getComments()) {
															if (!c.isDisplayeble() && c.getUserNickname().equals(u.getNickname())) {
																JLabel lblNewLabel_1 = new JLabel(c.getComment());
																lblNewLabel_1.setBounds(38, 86, 311, 14);
																sf.getContentPane().add(lblNewLabel_1);
															}
														
														}
													}
												}
												
												
												sf.setVisible(true);
												
											}
										});
									   panel_1.add(buttons.get(i));
									   
									   
								}
								
								
								JButton btn = new JButton("Go Back");
								btn.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										nf.setVisible(false);
										adminPage.setVisible(true);
									}
								});
								panel_1.add(btn);
								
								nf.getContentPane().add(scroll_1);
								nf.setLocationRelativeTo(null);
								nf.setVisible(true);
							}
						});
						btnGetNotification.setBounds(134, 94, 133, 23);
						adminPage.getContentPane().add(btnGetNotification);
						
						JButton btnShowComments = new JButton("Show Comments");
						btnShowComments.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								adminPage.setVisible(false);
								 ArrayList<JButton> buttons = new ArrayList<JButton>();
								 JFrame fn = new JFrame();
								    fn.setBounds(100, 100, 450, 300);
								    fn.setLocationRelativeTo(null);
									fn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									fn.setTitle("ADMIN-PAGE");
									fn.addWindowListener(new WindowAdapter()
							        {
							            public void windowClosing(WindowEvent e)
							            {
							            	f.fileWrite();
							                e.getWindow().dispose();
							            }
							        });
									JPanel panel_1 = new JPanel();
									 panel_1.setBounds(81, 37, 289, 184);
									 panel_1.setLayout(new GridLayout(0, 1, 0, 0));
									
								        fn.getContentPane().add( panel_1);
								        final JScrollPane scroll_1 = new JScrollPane(panel_1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
								                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
							
								       ArrayList<Comment> com = a.showAllComments();
								        
								        for (int i=0; i<com.size();i++) {
								        	
								        	buttons.add( new JButton(com.get(i).getComment()+"  "+com.get(i).getUserNickname()));
											
											   buttons.get(i).setBackground(Color.MAGENTA);
											   
											   panel_1.add(buttons.get(i));
											   
											   Comment nc = com.get(i);
											   User u = (User) m.getActors().get(com.get(i).getUserNickname());
												buttons.get(i).addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														fn.setVisible(false);
														JFrame comm = new JFrame();
													    comm.setBounds(100, 100, 450, 300);
													    comm.setLocationRelativeTo(null);
														comm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
														comm.setTitle("ADMIN-PAGE");
														comm.setLayout(null);
														comm.addWindowListener(new WindowAdapter()
												        {
												            public void windowClosing(WindowEvent e)
												            {
												            	f.fileWrite();
												                e.getWindow().dispose();
												            }
												        });
														JLabel label = new JLabel(nc.getComment());
														label.setBounds(36, 52, 306, 94);
														comm.getContentPane().add(label);
														
														JButton btn = new JButton("Block User");
														btn.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																a.blockUser(u.getNickname());
																JOptionPane.showMessageDialog(null,u.getNickname()+" has blocked.");
															}
														});
														btn.setBounds(32, 150, 115, 20);
														comm.getContentPane().add(btn);
														
														JButton btn1 = new JButton("Delete Comment ");
														btn1.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																nc.setDisplayeble(false);
																JOptionPane.showMessageDialog(null,"comment has deleted.");
															}
														});
														btn1.setBounds(32, 175, 115, 20);
														comm.getContentPane().add(btn1);
														
														JButton btn2 = new JButton("Go Back");
														btn2.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																comm.setVisible(false);
																fn.setVisible(true);
															}
														});
														btn2.setBounds(32, 200, 115, 20);
														comm.getContentPane().add(btn2);
														
														
														JLabel lblNewLabel_1 = new JLabel("");
														lblNewLabel_1.setIcon(new ImageIcon("output-onlinepngtools.png"));
														lblNewLabel_1.setBounds(0, -46, 313, 162);
														comm.getContentPane().add(lblNewLabel_1);
														
														JLabel lblNewLabel = new JLabel("New label");
														lblNewLabel.setBackground(new Color(255, 105, 180));
														lblNewLabel.setBounds(0, -18, 2457, 5794);
														lblNewLabel.setIcon(new ImageIcon("image.jpg"));
														comm.getContentPane().add(lblNewLabel);
														
														
														comm.setVisible(true);
													}
												});
										}
								       
										JButton btnn = new JButton("Go Back");
										btnn.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												fn.setVisible(false);
												adminPage.setVisible(true);

											}
										});
										panel_1.add(btnn);
										
								        
								      
								        fn.getContentPane().add(scroll_1);
										
								        fn.setVisible(true);
										
										fn.setLocationRelativeTo(null);
									
								
								
								
							}
						});
						btnShowComments.setBounds(134, 148, 133, 23);
						adminPage.getContentPane().add(btnShowComments);
					
						
						
						JButton exit = new JButton("Go Back");
						exit.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								adminPage.setVisible(false);
								frame.setVisible(true);
							}
						});
						exit.setBounds(134, 202, 133, 23);
						adminPage.getContentPane().add(exit);
						
						
						
						JLabel lblNewLabel_1 = new JLabel("");
						lblNewLabel_1.setIcon(new ImageIcon("output-onlinepngtools.png"));
						lblNewLabel_1.setBounds(0, -46, 313, 162);
						adminPage.getContentPane().add(lblNewLabel_1);
						
						JLabel lblNewLabel = new JLabel("New label");
						lblNewLabel.setBackground(new Color(255, 105, 180));
						lblNewLabel.setBounds(0, -18, 2457, 5794);
						lblNewLabel.setIcon(new ImageIcon("image.jpg"));
						adminPage.getContentPane().add(lblNewLabel);
						
						adminPage.setVisible(true);
						
					}


				}
				else {
					JOptionPane.showMessageDialog(null,"Your account name or password is incorrect.");
				}


			}
		});

		frame.getContentPane().add(button);
	
		Button button_1 = new Button("SIGN-UP");
		button_1.setBackground(new Color(219, 112, 147));
		button_1.setBounds(178, 141, 83, 27);

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				String name=JOptionPane.showInputDialog("Name: ");
				String surname=JOptionPane.showInputDialog("Surname: ");
				String nickname=JOptionPane.showInputDialog("Nickname: ");
				

				JPanel panel = new JPanel();
				JLabel label = new JLabel("Enter a password:");
				JPasswordField pass = new JPasswordField(10);
				panel.add(label);
				panel.add(pass);
				String[] options = new String[]{"OK", "Cancel"};
				int option = JOptionPane.showOptionDialog(null, panel, "Input",
				                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				                         null, options, options[0]);
				char[] passw = null;
				if(option == 0) 
				{
				    passw = pass.getPassword();
				}
				
				String password = new String(passw);
				String email=JOptionPane.showInputDialog("E-mail: ");
				String pCityCode = null,  pNumber = null;
				boolean flagP=false;
				do {
					flagP=phoneControl();
					if(flagP) {
						pCityCode=String.valueOf(num).substring(0, 3);
						pNumber=String.valueOf(num).substring(3);
					}
				}while(!flagP);
				if(m.signUP(name, surname, email, pCityCode, pNumber, nickname, password)) {
					
					
						try {
							frame.setVisible(false);
							process(frame,nickname);
						} catch (QueueFull | QueueEmpty e1) {
							e1.printStackTrace();
						}
					
					
				}
				else {
					JOptionPane.showMessageDialog(null,"Nickname has been taken" );
					System.out.println();

				}

			}
		});

		frame.getContentPane().add(button_1);
		
		Button button_2 = new Button("User Service");
		button_2.setBackground(new Color(219, 112, 147));
		button_2.setBounds(178, 174, 83, 27);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"E-mail to deuevents@gmail.com" );
			}
		});
		frame.getContentPane().add(button_2);



		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("output-onlinepngtools.png"));
		lblNewLabel_1.setBounds(0, -46, 313, 162);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblEtkinlikio = new JLabel("etkinlik.io");
		lblEtkinlikio.setBounds(334, 236, 75, 14);
		frame.getContentPane().add(lblEtkinlikio);

		JLabel lblWelcome = new JLabel("WELCOME");
		lblWelcome.setFont(new Font("Yu Gothic Medium", Font.BOLD, 17));
		lblWelcome.setBounds(168, 77, 234, 21);
		frame.getContentPane().add(lblWelcome);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(new Color(255, 105, 180));
		lblNewLabel.setBounds(0, -18, 2457, 5794);
		lblNewLabel.setIcon(new ImageIcon("image.jpg"));
		frame.getContentPane().add(lblNewLabel);

		frame.setVisible(true);
	}

	public static boolean phoneControl() {

		boolean flag=true;
		String str = "";
		str=JOptionPane.showInputDialog("Phone number: 0");
		if (str.length() != 10) {
			JOptionPane.showMessageDialog(null,"phone number is invalid!" );	
			flag=false;
		}
		try {
			num= Long.parseLong(str);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"ERROR! CAST EXCEPTION" );
			flag=false;
		}


		return flag;
	}

}

