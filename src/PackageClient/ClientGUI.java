package PackageClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.border.Border;

public class ClientGUI extends JFrame implements ActionListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Varible for GUI
	private JTextArea txtMessage, txtListUser, txtSend;
	private JTextField txthost, txtUser;
	private JButton btnConnect, btnExit, btnLogin, btnSend;

	// Varible use;
	private Client client;

	public ClientGUI(String title) {
		setTitle(title);
		setSize(600, 400);
		addComponent();
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void addComponent() {
		JPanel pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());

		// Jpanel for Message and text send
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		JPanel pnMessage = new JPanel(new BorderLayout());
		txtMessage = new JTextArea(10, 10);
		txtMessage.setEditable(false);
		txtMessage.setVisible(false);
		pnMessage.add(new JScrollPane(txtMessage));
		pnMessage.setBorder(BorderFactory.createTitledBorder("Message Chat"));
		pnCenter.add(pnMessage, BorderLayout.CENTER);
		JPanel pnTextSend = new JPanel(new BorderLayout());
		txtSend = new JTextArea(3, 10);
		txtSend.setLineWrap(isResizable()); // Xuong dong khi het dong
		txtSend.setVisible(false);
		pnTextSend.add(txtSend);
		// btnSend = new JButton("Send");
		// pnTextSend.add(btnSend);
		pnTextSend
				.setBorder(BorderFactory.createTitledBorder("Text to send :"));
		pnCenter.add(pnTextSend, BorderLayout.SOUTH);
		pnCenter.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JPanel pnEast = new JPanel();
		pnEast.setLayout(new BorderLayout());
		// JPanel for Login
		JPanel pnLogin = new JPanel(new GridLayout(3, 1, 1, 1));
		pnLogin.add(new JLabel("Host name:"));
		txthost = new JTextField("localhost", 10);
		txthost.addActionListener(this);
		pnLogin.add(txthost);
		pnLogin.add(new JLabel("User name:"));
		txtUser = new JTextField("Thao Phan", 10);
		txtUser.addActionListener(this);
		pnLogin.add(txtUser);
		btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(this);
		pnLogin.add(btnLogin);
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		pnLogin.add(btnExit);
		pnLogin.setBorder(BorderFactory.createTitledBorder("Login"));

		// JPanel for List use
		JPanel pnList = new JPanel(new BorderLayout());
		txtListUser = new JTextArea();
		txtListUser.setVisible(false);
		pnList.add(new JScrollPane(txtListUser));

		pnList.setBorder(BorderFactory
				.createTitledBorder("Danh sách người trong phòng !"));

		pnEast.add(pnLogin, BorderLayout.NORTH);
		pnEast.add(pnList, BorderLayout.CENTER);

		pnBorder.add(pnCenter, BorderLayout.CENTER);
		pnBorder.add(pnEast, BorderLayout.EAST);
		getContentPane();
		add(pnBorder);

	}

	public static void main(String[] args) {
		ClientGUI clientGui = new ClientGUI("Client");
	}

	public void appendMessage(String str) {
		txtMessage.append(str);
		txtMessage.append("\n");
	}

	public String getMessage() {
		String txt = txtSend.getText();
		txtSend.setText(" ");
		return txt;
	}

	public String getHost() {
		return txthost.getText().trim();
	}

	public String getName() {
		return txtUser.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExit) {
			int ref = JOptionPane.showConfirmDialog(null, "Thoát chương trình",
					"Thoát", JOptionPane.YES_NO_OPTION);
			if (ref == JOptionPane.YES_OPTION) {
				System.out.println("Click exit");
				System.exit(0);
			}
		}
		if (e.getSource() == btnLogin) {
			if (btnLogin.getText() == "Đăng nhập") {
				btnLogin.setText("Đăng xuất");
				String host = getHost();
				String name = getName();
				if (host.length() == 0 || name.length() == 0) {
					// System.out.println("Nhap thieu thong tin");
					JOptionPane.showMessageDialog(this,
							"Đăng nhập thành công !");
				} else {
					txtListUser.setVisible(true);
					txtMessage.setVisible(true);
					txtSend.setVisible(true);
				}
			} else {
				btnLogin.setText("Đăng nhập");
				txtListUser.setVisible(false);
				txtMessage.setVisible(false);
				txtSend.setVisible(false);
			}
		}

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	class StartClient extends Thread {

		@Override
		public void run() {
			client.connect();
		}

	}
}
