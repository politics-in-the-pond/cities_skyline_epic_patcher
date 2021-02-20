package cities_skylines_epic_patcher;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.TextArea;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class main_ui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public static TextArea textArea = new TextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_ui frame = new main_ui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void log(String line) {
		textArea.append(line + "\n");
	}

	/**
	 * Create the frame.
	 */
	public main_ui() {

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(main_ui.class.getResource("/cities_skylines_epic_patcher/ads.ico")));
		setTitle("Cities_Skylines_epic_tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 482);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBounds(12, 357, 702, 33);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		ButtonGroup g1 = new ButtonGroup();
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Mods");
		panel.add(rdbtnNewRadioButton_2);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Assets");
		panel.add(rdbtnNewRadioButton_1);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Script");
		panel.add(rdbtnNewRadioButton);
		g1.add(rdbtnNewRadioButton);
		g1.add(rdbtnNewRadioButton_1);
		g1.add(rdbtnNewRadioButton_2);

		patcher pc = new patcher();
		parser ps = new parser();

		String directory = System.getProperty("user.home")
				+ "\\AppData\\Local\\Colossal Order\\Cities_Skylines\\Addons\\";
		String mods = "Mods\\";
		String assets = "Assets\\";

		JButton btnNewButton = new JButton("RUN!");
		btnNewButton.setBounds(12, 400, 702, 33);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNewRadioButton_2.isSelected()) { // Mod
					String temp = textField.getText();
					textField.setText("");
					String code = ps.link_parser(temp);
					try {
						pc.do_patch(directory + mods, code);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (rdbtnNewRadioButton_1.isSelected()) { // Asset
					String temp = textField.getText();
					textField.setText("");
					String code = ps.link_parser(temp);
					try {
						pc.do_patch(directory + assets, code);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (rdbtnNewRadioButton.isSelected()) { // Script Mode
					String[] codes = textField.getText().split(","); // even : code //odd : prop
					int length = codes.length;
					for (int i = 0; i <= length / 2; i++) {
						if (codes[i + 1].equals("M")) {
							try {
								pc.do_patch(directory + mods, codes[i]);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if (codes[i + 1].equals("A")) {
							try {
								pc.do_patch(directory + assets, codes[i]);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});

		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setText("enter the steam workshop link or script here.");
		textField.setBounds(12, 301, 702, 57);
		contentPane.add(textField);
		textField.setColumns(10);
		contentPane.add(panel);
		contentPane.add(btnNewButton);
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		textArea.setBounds(12, 10, 702, 285);
		contentPane.add(textArea);

	}
}
