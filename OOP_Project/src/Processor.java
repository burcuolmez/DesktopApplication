package src;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.lang.model.element.Element;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Processor {
	static JFrame frameTest;
	static Management m = new Management();
	static String nickname;
	static int num;
	static long num2;
	static FileWR f = new FileWR();
	public Processor(JFrame frameTest,String nickname) {
		this.nickname=nickname;
		this.frameTest=frameTest;
	}
	
	public static void main(String[] args) throws QueueFull, QueueEmpty {
		showWindow(nickname);
	}
	
	public static void profileSettings(JFrame ftest, String nickname) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("EVENTS");
		frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
            	f.fileWrite();
                e.getWindow().dispose();
            }
        });
		JLabel lblname = new JLabel("Name: "+ m.getActors().get(nickname).getName());
		lblname.setBounds(69, 66, 146, 23);
		lblname.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		frame.getContentPane().add(lblname);
		
		JButton btnNewButton = new JButton("Change Name");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=JOptionPane.showInputDialog("New name: ");
				m.getActors().get(nickname).setName(name);
				lblname.setText("Name: "+ m.getActors().get(nickname).getName());
			}
		});
		btnNewButton.setBounds(362, 66, 145, 23);
		btnNewButton.setBackground(new Color(219, 112, 147));
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblsurname = new JLabel("Surname: "+ m.getActors().get(nickname).getSurname());
		lblsurname.setBounds(69, 100, 146, 23);
		lblsurname.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		frame.getContentPane().add(lblsurname);
		
		JButton btnChangeSurname = new JButton("Change Surname");
		btnChangeSurname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String surname=JOptionPane.showInputDialog("New surname: ");
				m.getActors().get(nickname).setSurname(surname);
				lblsurname.setText("Surname: "+ m.getActors().get(nickname).getSurname());
			}
		});
		btnChangeSurname.setBounds(362, 100, 145, 23);
		btnChangeSurname.setBackground(new Color(219, 112, 147));
		frame.getContentPane().add(btnChangeSurname);
		
		JLabel lblemail = new JLabel("E-mail: "+ m.getActors().get(nickname).getEmail());
		lblemail.setBounds(69, 133, 200, 23);
		lblemail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		frame.getContentPane().add(lblemail);
		
		JButton btnChangeMail = new JButton("Change E-mail");
		btnChangeMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email=JOptionPane.showInputDialog("New e-mail: ");
				m.getActors().get(nickname).setEmail(email);
				lblemail.setText("E-mail: "+ m.getActors().get(nickname).getEmail());
			}
		});
		btnChangeMail.setBounds(362, 133, 145, 23);
		btnChangeMail.setBackground(new Color(219, 112, 147));
		frame.getContentPane().add(btnChangeMail);
		
		JLabel lblphone = new JLabel("Phone number: "+ m.getActors().get(nickname).getPhone().toString());
		lblphone.setBounds(69, 167, 250, 23);
		lblphone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		frame.getContentPane().add(lblphone);
		
		JButton btnChangePhone = new JButton("Change Phone");
		btnChangePhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pCityCode = null,  pNumber = null;
				boolean flagP=false;
				do {
					flagP=PhoneControl();
					if(flagP) {
						pCityCode=String.valueOf(num2).substring(0, 3);
						pNumber=String.valueOf(num2).substring(3);
					}
				}while(!flagP);
				
				Phone p = new Phone(pCityCode,pNumber);
				m.getActors().get(nickname).setPhone(p);
				lblphone.setText("Phone number: "+ m.getActors().get(nickname).getPhone().toString());
			}
		});
		btnChangePhone.setBounds(362, 167, 145, 23);
		btnChangePhone.setBackground(new Color(219, 112, 147));
		frame.getContentPane().add(btnChangePhone);
		
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ftest.setVisible(true);
				
				
				
			}
		});
		btnExit.setBounds(362, 199, 145, 23);
		btnExit.setBackground(new Color(219, 112, 147));
		frame.getContentPane().add(btnExit);
	

		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("output-onlinepngtools.png"));
		lblNewLabel_1.setBounds(0, -46, 313, 162);
		frame.getContentPane().add(lblNewLabel_1);
		
	
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(new Color(255, 105, 180));
		lblNewLabel.setBounds(0, -18, 2457, 5794);
		lblNewLabel.setIcon(new ImageIcon("image.jpg"));
		frame.getContentPane().add(lblNewLabel);
		frame.setVisible(true);
	}
	
	public static void printEvent(JFrame frame,Set<Event> a,String nickname) throws QueueFull, QueueEmpty, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    ArrayList<JButton> buttons = new ArrayList<JButton>();
	
		    JFrame events = new JFrame();
		    events.setBounds(100, 100, 450, 300);
		    events.setLocationRelativeTo(null);
			events.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			events.setTitle("EVENTS");
			events.addWindowListener(new WindowAdapter()
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
			
		        events.getContentPane().add( panel_1);
		        final JScrollPane scroll_1 = new JScrollPane(panel_1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		        
			
			Object[] o = a.toArray();
			for ( int i = 0; i <o.length ; i++ )
			{
			
			   buttons.add( new JButton(((Event) o[i]).getEventName() +" "+((Event) o[i]).getPlace().getAddress().getCity() ));
			
			   buttons.get(i).setBackground(Color.MAGENTA);
			   
			   panel_1.add(buttons.get(i));
			   Event e = (Event) o[i];
			   User u = (User) m.getActors().get(nickname);
			   buttons.get(i).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						  events.setVisible(false);
						   JFrame event = new JFrame();
						    event.setBounds(100, 100, 817, 900);
						    event.setLocationRelativeTo(null);
							event.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							event.setResizable(false);
							event.setTitle("EVENTS");
							event.addWindowListener(new WindowAdapter()
					        {
					            public void windowClosing(WindowEvent e)
					            {
					            	f.fileWrite();
					                e.getWindow().dispose();
					            }
					        });
							JPanel pan = new JPanel();
							pan.setOpaque(false);
							event.add(pan);
							JScrollPane scrollBar = new JScrollPane(pan,
						            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
							  scrollBar.setOpaque(false);
						      scrollBar.getViewport().setOpaque(false);
						      scrollBar.setBounds(0, 0, 800, 800);
						      event.getContentPane().add(scrollBar);
							
						      scrollBar.setViewportView(pan);
						      
							JLabel lblNewLabel_1 = new JLabel("");
							lblNewLabel_1.setIcon(new ImageIcon("output-onlinepngtools.png"));
							
							
							JLabel label = new JLabel("            "+e.getEventName());
							label.setSize(label.getPreferredSize());
							label.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
							
						
								JLabel label1 = new JLabel("      URL: "+e.getUrl());
								label1.setSize(label1.getPreferredSize());
								label1.setFont(new Font("Tahoma", Font.PLAIN, 14));
							
							
							
							
						
							
							String text =e.getContent();
							JTextPane jTextPane =new JTextPane ();
							jTextPane.setContentType("text/html");
							jTextPane.setText("<pre>"+"   "+text+"</pre>");
							jTextPane.setSize(jTextPane.getPreferredSize());
							jTextPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
							jTextPane.setEditable(false);
							jTextPane.setOpaque(false);
						
						
						
						
							
							
							JLabel label3 = new JLabel("      Rate: "+e.getRate()+"  "+e.getRateCount()+" people has rated");
							label3.setSize(label3.getPreferredSize());
							label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
						
						
							JLabel label4 = new JLabel("      Location: "+e.getPlace().toString());
							label4.setSize(label4.getPreferredSize());
							label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
							
							JLabel label5 = new JLabel("      Date: "+e.getStartDate().toString()+"/"+e.getEndDate().toString());
							label5.setSize(label5.getPreferredSize());
							label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
							

							JLabel label6 = new JLabel("      Format: "+e.getFormat()+" Category: "+e.getCategory());
							label6.setSize(label6.getPreferredSize());
							label6.setFont(new Font("Tahoma", Font.PLAIN, 14));
							
							
								JLabel label7 = new JLabel("      Phone Number: "+e.getPhone().toString());
							
								label7.setSize(label7.getPreferredSize());
								label7.setFont(new Font("Tahoma", Font.PLAIN, 14));
					
							
							
							
							ArrayList<String> tags = e.getTags();
							String tag=" ";
							for (String s : tags) {
								tag+="#"+s+" ";
							}
							JLabel label8 = new JLabel("      Tags: "+tag);
						
							label8.setSize(label8.getPreferredSize());
							label8.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
							String sc = "";
							for (Comment c : e.getComments()) {
								sc+=c.toString()+"    ";
							}
							JLabel label9 = new JLabel("      Comments: "+sc);
						
							label9.setSize(label9.getPreferredSize());
							label9.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
							
							GroupLayout gl_panel = new GroupLayout(pan);
							gl_panel.setHorizontalGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNewLabel_1)
											.addComponent(label)
											.addComponent(label1)
											.addComponent(jTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(label3)
											.addComponent(label4)
											.addComponent(label5)
											.addComponent(label6)
											.addComponent(label7)
											.addComponent(label8)
											.addComponent(label9))
										.addContainerGap(328, Short.MAX_VALUE))
							);
							
							gl_panel.setVerticalGroup(
									gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblNewLabel_1)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(label)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(label1)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(jTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)											
											.addComponent(label3)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(label4)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(label5)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(label6)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(label7)
											.addPreferredGap(ComponentPlacement.UNRELATED)
										    .addComponent(label8)
										    .addPreferredGap(ComponentPlacement.UNRELATED)
										    .addComponent(label9)
										    .addPreferredGap(ComponentPlacement.UNRELATED))
								);
								
								
								pan.setLayout(gl_panel);
							JButton btn = new JButton("Add Calendar");
							btn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									
									try {
										u.addCalendar(e);
										JOptionPane.showMessageDialog(null," Event has been added to your calendar successfuly");
									} catch (QueueFull | QueueEmpty e) {
										e.printStackTrace();
									}
								}
							});
							btn.setBounds(50, 810, 115, 23);
							btn.setBackground(new Color(219, 112, 147));
							event.add(btn);
							
							JButton btn1 = new JButton("Comment");
							btn1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									String str=JOptionPane.showInputDialog("Comment: ");
									Comment c = new Comment(str,nickname,e.getEventID());
									e.addComment(c);
									JOptionPane.showMessageDialog(null,"Comment will be checked. Thank you ");
								}
							});
							btn1.setBounds(185, 810, 115, 23);
							btn1.setBackground(new Color(219, 112, 147));
							event.add(btn1);
							
							JButton btn2 = new JButton("Rate");
							btn2.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
								
									while (!rateCheck()) {
										
									}
									e.updateRate(num);
									JOptionPane.showMessageDialog(null,"Rate has been added. Thank you ");
								}
							});
							btn2.setBounds(320, 810, 115, 23);
							btn2.setBackground(new Color(219, 112, 147));
							event.add(btn2);
							
							
							JButton btn3 = new JButton("Exit");
							btn3.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
								event.setVisible(false);
								events.setVisible(true);	
								}
							});
							btn3.setBounds(455, 810, 115, 23);
							btn3.setBackground(new Color(219, 112, 147));
							event.add(btn3);
							
							
							event.add(scrollBar);
							
							 JLabel lblImg = new JLabel("img");
						        lblImg.setBounds(0, 0, 434, 261);
						        lblImg.setIcon(new ImageIcon("image.jpg"));
						        event.getContentPane().add(lblImg);
							
							
							
							
							event.setVisible(true);
							
						
					}

					
				});
			   
			}
			JButton bye = new JButton("GO BACK");
			panel_1.add(bye);
			bye.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						events.setVisible(false);
						frame.setVisible(true);
					}
				});
		
			events.getContentPane().add(scroll_1);
			
			 
			events.setVisible(true);
			events.setLocationRelativeTo(null);
		
	}
	public static void showWindow(String nickname) throws QueueFull, QueueEmpty{
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 715, 480);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("EVENTS");
		frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
            	f.fileWrite();
                e.getWindow().dispose();
            }
        });
		
		
	         
		JButton button = new JButton("Profile Settings");
		button.setBackground(new Color(255, 240, 245));
		button.setOpaque(false);
		button.setIcon(new ImageIcon("psicon.png"));
		button.setBounds(68, 86, 162, 43);
		button.setFont(new Font("Tahoma", Font.BOLD, 12));

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				frame.setVisible(false);
				profileSettings(frame,nickname);
			}
		});
		button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(255, 240, 245));
                button.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	button.setOpaque(false);
            }
        });
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Notifications");
		button_1.setBackground(new Color(255, 240, 245));
		button_1.setOpaque(false);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setIcon(new ImageIcon("noticon.png"));
		button_1.setBounds(68, 140, 162, 43);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				User u = (User) m.getActors().get(nickname);
				for(Object i : u.getCalender().values()) {
					if(i!=null) {
						String s =m.getEvents().get(i).countDown();
						JOptionPane.showMessageDialog(null, s);
					}

				}
				if(u.getInterest()!=null) {
					
					try {
						printEvent(frame, u.getInterest(),nickname);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | QueueFull
							| QueueEmpty | UnsupportedLookAndFeelException e1) {
						
						e1.printStackTrace();
					}
					
				}
				
				
			}
		});
		button_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button_1.setBackground(new Color(255, 240, 245));
                button_1.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	button_1.setOpaque(false);
            }
        });
		frame.getContentPane().add(button_1);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(new Color(255, 240, 245));
		btnSearch.setOpaque(false);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				JFrame jf= new JFrame();
				jf.setBounds(100, 100, 450, 300);
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf.getContentPane().setLayout(null);
				jf.addWindowListener(new WindowAdapter()
		        {
		            public void windowClosing(WindowEvent e)
		            {
		            	f.fileWrite();
		                e.getWindow().dispose();
		            }
		        });
				JLabel labell = new JLabel("You don't have to fill all areas.");
				labell.setBounds(82, 60, 180, 14);
				jf.getContentPane().add(labell);
				
				JLabel label = new JLabel("Name: ");
				label.setBounds(82, 95, 92, 14);
				jf.getContentPane().add(label);
				
				JTextField textField = new JTextField();
				textField.setBounds(204, 95, 157, 20);
				jf.getContentPane().add(textField);
				textField.setColumns(10);
				
				JLabel lblNewLabel = new JLabel("City: ");
				lblNewLabel.setBounds(82, 129, 46, 14);
				jf.getContentPane().add(lblNewLabel);
				
				JTextField textField_1 = new JTextField();
				textField_1.setBounds(204, 126, 157, 20);
				jf.getContentPane().add(textField_1);
				textField_1.setColumns(10);
				
				JLabel lblNewLabel_1 = new JLabel("Tag: ");
				lblNewLabel_1.setBounds(82, 160, 46, 14);
				jf.getContentPane().add(lblNewLabel_1);
				
				JTextField textField_2 = new JTextField();
				textField_2.setBounds(204, 157, 157, 20);
				jf.getContentPane().add(textField_2);
				textField_2.setColumns(10);
				
				
				JButton btnSearch1 = new JButton("SEARCH");
				btnSearch1.setBackground(new Color(219, 112, 147));
				btnSearch1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						String name=textField.getText();
						
						if (name.equalsIgnoreCase(""))
							name=null;
						
						String city=textField_1.getText();
						if (city.equalsIgnoreCase(""))
							city=null;
						

						String tag=textField_2.getText();
						if (tag.equalsIgnoreCase(""))
							tag=null;
						Set<Event> set = m.search(name, city, tag);
						if(set!=null) {
							try {
								jf.setVisible(false);
								printEvent(jf,set,nickname);
							} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
									| QueueFull | QueueEmpty | UnsupportedLookAndFeelException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else 
							JOptionPane.showMessageDialog(null, "Nothing found");
					}
				});
				btnSearch1.setBounds(103, 207, 89, 23);
				jf.getContentPane().add(btnSearch1);
				
				JButton btnExit = new JButton("EXIT");
				btnExit.setBackground(new Color(219, 112, 147));
				btnExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						jf.setVisible(false);
						frame.setVisible(true);
					}
				});
				btnExit.setBounds(223, 207, 89, 23);
				jf.getContentPane().add(btnExit);
				
				
				JLabel lblNewLabel1 = new JLabel("");
				lblNewLabel1.setIcon(new ImageIcon("output-onlinepngtools.png"));
				lblNewLabel1.setBounds(0, -46, 313, 162);
				jf.getContentPane().add(lblNewLabel1);
				
			
				
				JLabel lblNewLabel2 = new JLabel("New label");
				lblNewLabel2.setBackground(new Color(255, 105, 180));
				lblNewLabel2.setBounds(0, -18, 2457, 5794);
				lblNewLabel2.setIcon(new ImageIcon("image.jpg"));
				jf.getContentPane().add(lblNewLabel2);
				jf.setVisible(true);
			}
		});
		btnSearch.setIcon(new ImageIcon("searchicon.png"));
		btnSearch.setBounds(68, 194, 163, 43);
		btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnSearch.setBackground(new Color(255, 240, 245));
            	btnSearch.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnSearch.setOpaque(false);
            }
        });
		frame.getContentPane().add(btnSearch);
		
		JButton btnAllEvents = new JButton("All Events");
		btnAllEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Set<Event> set1 = new HashSet<Event>();
				for (Event e1 : m.getEvents().values()) { 
					set1.add(e1);
				}
				try {
					printEvent(frame,set1, nickname);
				} catch (QueueFull | QueueEmpty e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (InstantiationException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IllegalAccessException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (UnsupportedLookAndFeelException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
			}
		});
		btnAllEvents.setBackground(new Color(255, 240, 245));
		btnAllEvents.setOpaque(false);
		btnAllEvents.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAllEvents.setIcon(new ImageIcon("alleventsicon.png"));
		btnAllEvents.setBounds(68, 248, 162, 43);
		btnAllEvents.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnAllEvents.setBackground(new Color(255, 240, 245));
            	btnAllEvents.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnAllEvents.setOpaque(false);
            }
        });
		frame.getContentPane().add(btnAllEvents);
		
		
		JButton btnLogout = new JButton("Log-out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frameTest.setVisible(true);
			}
		});
		btnLogout.setBackground(new Color(255, 240, 245));
		btnLogout.setOpaque(false);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogout.setIcon(new ImageIcon("logouticon.png"));
		btnLogout.setBounds(68, 356, 162, 43);
		btnLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnLogout.setBackground(new Color(255, 240, 245));
            	btnLogout.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnLogout.setOpaque(false);
            }
        });
		frame.getContentPane().add(btnLogout);
		
		JButton btnShowCalendar = new JButton("Show Calendar");
		btnShowCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    frame.setVisible(false);
				User u = (User) m.getActors().get(nickname);
				Set<Event> set1=new HashSet();
				for (Object o :u.getCalender().values()) {
					if(o!=null)
						set1.add(m.getEvents().get(o));
				}
			
				
				try {
					printEvent(frame,set1,nickname);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | QueueFull
						| QueueEmpty | UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnShowCalendar.setBackground(new Color(255, 240, 245));
		btnShowCalendar.setOpaque(false);
		btnShowCalendar.setIcon(new ImageIcon("showcalicon.png"));
		btnShowCalendar.setBounds(68, 302, 162, 43);
		btnShowCalendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnShowCalendar.setBackground(new Color(255, 240, 245));
            	btnShowCalendar.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnShowCalendar.setOpaque(false);
            }
        });
		frame.getContentPane().add(btnShowCalendar);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("output-onlinepngtools.png"));
		lblNewLabel_1.setBounds(0, -46, 313, 162);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Be your own hero!\r\n\r\n");
        lblNewLabel.setForeground(new Color(102, 0, 51));
        lblNewLabel.setFont(new Font("Ink Free", Font.BOLD | Font.ITALIC, 23));
        lblNewLabel.setBounds(348, 127, 226, 63);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("Plan yourself");
        lblNewLabel_2.setForeground(new Color(102, 0, 51));
        lblNewLabel_2.setFont(new Font("Ink Free", Font.BOLD | Font.ITALIC, 24));
        lblNewLabel_2.setBounds(378, 267, 154, 43);
        frame.getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon("styleicon.png"));
        lblNewLabel_3.setBounds(263, 39, 415, 376);
        frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabele = new JLabel("New label");
		lblNewLabele.setBackground(new Color(255, 105, 180));
		lblNewLabele.setBounds(0, -18, 2457, 5794);
		lblNewLabele.setIcon(new ImageIcon("image.jpg"));
		frame.getContentPane().add(lblNewLabele);
		
		
		frame.setVisible(true);
		}
	public static boolean rateCheck() {
		num=0;
		boolean flag = true;
		try {
			String str=JOptionPane.showInputDialog("Rate (out of 10) : ");
			num = Integer.parseInt(str);
			if(num<0 || num>10) {
				flag=false;
				JOptionPane.showMessageDialog(null,"num is invalid!" );
			}

		} 
		catch (NumberFormatException n) {
			JOptionPane.showMessageDialog(null,"Entered value is not an integer!" );
			flag=false;
		}
		return flag;
	}
	public static boolean PhoneControl() {

		boolean flag=true;
		String str = "";
		str=JOptionPane.showInputDialog("Phone number: 0");
		if (str.length() != 10) {
			JOptionPane.showMessageDialog(null,"phone number is invalid!" );	
			flag=false;
		}
		try {
			num2= Long.parseLong(str);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"ERROR! CAST EXCEPTION" );
			flag=false;
		}


		return flag;
	}
}
