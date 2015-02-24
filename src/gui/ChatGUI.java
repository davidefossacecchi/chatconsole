package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import chatthreads.InboxListener;
import chatthreads.ReceiveThread;
import chatthreads.SendThread;

public class ChatGUI extends JFrame implements ActionListener, InboxListener{
	
	private Container content;
	private JTextField msg;
	private JTextArea conversation;
	private ReceiveThread inbox;
	private SendThread outbox;
	private String user; 
	
	public ChatGUI(String user, ReceiveThread inbox, SendThread outbox){
		super(user + " - ChatConsole");
		this.inbox = inbox;
		this.outbox = outbox;
		this.user = user;
		this.inbox.addInboxListener(this);
		try{
			UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e){
			
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = getContentPane();
		content.setLayout(new GridBagLayout());
		content.setBackground(Color.CYAN);
		conversation = new JTextArea(30,20);
	    conversation.setEditable(false);
	    conversation.setLineWrap( true );
        conversation.setWrapStyleWord( true );
	    conversation.setAlignmentY(BOTTOM_ALIGNMENT);
	    JScrollPane scroll = new JScrollPane(conversation);
		msg = new JTextField(25);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(3,3,0,3);
		content.add(scroll, c);
		
		c.ipadx = 3;
		c.ipady = 3;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(3,3,3,3);
		content.add(msg, c);
		msg.addActionListener(this);
		setVisible(true);
		setResizable(false);
		pack();
	}
	
	public void actionPerformed(ActionEvent e){
		outbox.sendMessage(user + ": "+ msg.getText());
		conversation.append(user + ": " + msg .getText()+"\n");
		this.msg.setText("");
	}
	
	public void messageReceived(String msg){
		conversation.append(msg+"\n");
	}

}
