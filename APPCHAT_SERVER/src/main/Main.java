package main;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import connection.DatabaseConnection;
import service.Service;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextArea txt;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 984, 773);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 950, 726);
		contentPane.add(scrollPane);
		
		txt = new JTextArea();
		txt.setBounds(0, 0, 5, 22);
        txt.setEditable(false);
        txt.setColumns(20);
        txt.setRows(5);
		scrollPane.setViewportView(txt);
		
		init();
	}
	
	public void init() {
        try {
            DatabaseConnection.getInstance().connectToDatabase();
            Service.getInstance(txt).startServer();
        } catch (Exception e) {
        	e.printStackTrace();
            txt.append("Error : " + e + "\n");
        }
	}
	
}
