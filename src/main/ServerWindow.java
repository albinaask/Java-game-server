package main;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import java.awt.GridLayout;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;

public class ServerWindow{

	private JFrame frmAspaceworksServerApplication;
	private JTextField txtInput;
	private JPasswordField pwdServerSpecificPassword;
	private boolean isOn = false;
	
	private JTextArea log;
	private Server server = new Server(this);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					ServerWindow window = new ServerWindow();
					window.frmAspaceworksServerApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAspaceworksServerApplication = new JFrame();
		frmAspaceworksServerApplication.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.out.println("application closing");
				if(!isOn){
					frmAspaceworksServerApplication.dispose();
					server.stop();
					//closing process
				}else if(isOn&&JOptionPane.showConfirmDialog(null, "Do you realy want to close?\nClosing the window results in a serverstop!", "server closing", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					frmAspaceworksServerApplication.dispose();
					server.stop();
					//closing process
				}
			}
		});
		frmAspaceworksServerApplication.setTitle("a-spaceworks server application");
		frmAspaceworksServerApplication.setBounds(175, 175, 768, 617);
		frmAspaceworksServerApplication.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAspaceworksServerApplication.setMinimumSize(new Dimension(768,617));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{752, 0};
		gridBagLayout.rowHeights = new int[]{20, 488, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frmAspaceworksServerApplication.getContentPane().setLayout(gridBagLayout);
		
		JMenuBar menuBar = new JMenuBar();
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_menuBar.insets = new Insets(0, 0, 5, 0);
		gbc_menuBar.gridx = 0;
		gbc_menuBar.gridy = 0;
		frmAspaceworksServerApplication.getContentPane().add(menuBar, gbc_menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JCheckBoxMenuItem chckbxmntmLogToFile = new JCheckBoxMenuItem("log to file");
		mnFile.add(chckbxmntmLogToFile);
		
		JMenu mnOptions = new JMenu("options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnOptions.add(mntmQuit);
		
		JCheckBoxMenuItem chckbxmntmAllowPlayersTo = new JCheckBoxMenuItem("allow players to connect");
		chckbxmntmAllowPlayersTo.setSelected(true);
		mnOptions.add(chckbxmntmAllowPlayersTo);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 1;
		frmAspaceworksServerApplication.getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("main server control", null, panel_2, null);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{747, 0};
		gbl_panel_2.rowHeights = new int[]{306, 26, 183, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel controlButtonPanel = new JPanel();
		GridBagConstraints gbc_controlButtonPanel = new GridBagConstraints();
		gbc_controlButtonPanel.fill = GridBagConstraints.BOTH;
		gbc_controlButtonPanel.insets = new Insets(0, 0, 5, 0);
		gbc_controlButtonPanel.gridx = 0;
		gbc_controlButtonPanel.gridy = 1;
		panel_2.add(controlButtonPanel, gbc_controlButtonPanel);
		controlButtonPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton startButton = new JButton("start server");
		JButton stopButton = new JButton("stop server");
		stopButton.setEnabled(false);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startButton.setEnabled(false);
				stopButton.setEnabled(false);
				server.start();
				isOn = true;
				stopButton.setEnabled(true);
			}
		});
		controlButtonPanel.add(startButton);
		
		
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButton.setEnabled(false);
				stopButton.setEnabled(false);
				server.stop();
				isOn = false;
				startButton.setEnabled(true);
			}
		});
		controlButtonPanel.add(stopButton);
		
		JButton btnKillServer = new JButton("Kill server");
		btnKillServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		controlButtonPanel.add(btnKillServer);
		
		JPanel progressBarPanel = new JPanel();
		GridBagConstraints gbc_progressBarPanel = new GridBagConstraints();
		gbc_progressBarPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBarPanel.gridx = 0;
		gbc_progressBarPanel.gridy = 2;
		panel_2.add(progressBarPanel, gbc_progressBarPanel);
		GridBagLayout gbl_progressBarPanel = new GridBagLayout();
		gbl_progressBarPanel.columnWidths = new int[]{0, 340, 340, 0, 0};
		gbl_progressBarPanel.rowHeights = new int[]{27, 34, 27, 34, 27, 34, 0};
		gbl_progressBarPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_progressBarPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		progressBarPanel.setLayout(gbl_progressBarPanel);
		
		JLabel lblGeneralProgress = new JLabel("general progress:");
		GridBagConstraints gbc_lblGeneralProgress = new GridBagConstraints();
		gbc_lblGeneralProgress.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblGeneralProgress.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeneralProgress.gridx = 1;
		gbc_lblGeneralProgress.gridy = 0;
		progressBarPanel.add(lblGeneralProgress, gbc_lblGeneralProgress);
		
		JLabel lblStarting = new JLabel("starting");
		GridBagConstraints gbc_lblStarting = new GridBagConstraints();
		gbc_lblStarting.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblStarting.insets = new Insets(0, 0, 5, 5);
		gbc_lblStarting.gridx = 2;
		gbc_lblStarting.gridy = 0;
		progressBarPanel.add(lblStarting, gbc_lblStarting);
		
		JProgressBar progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.BOTH;
		gbc_progressBar.gridwidth = 2;
		gbc_progressBar.insets = new Insets(0, 0, 5, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 1;
		progressBarPanel.add(progressBar, gbc_progressBar);
		progressBar.setValue(50);
		
		JLabel lblSubProgress = new JLabel("sub progress 1:");
		GridBagConstraints gbc_lblSubProgress = new GridBagConstraints();
		gbc_lblSubProgress.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblSubProgress.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubProgress.gridx = 1;
		gbc_lblSubProgress.gridy = 2;
		progressBarPanel.add(lblSubProgress, gbc_lblSubProgress);
		
		JLabel lblSample = new JLabel("sample");
		GridBagConstraints gbc_lblSample = new GridBagConstraints();
		gbc_lblSample.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblSample.insets = new Insets(0, 0, 5, 5);
		gbc_lblSample.gridx = 2;
		gbc_lblSample.gridy = 2;
		progressBarPanel.add(lblSample, gbc_lblSample);
		
		JProgressBar progressBar_1 = new JProgressBar();
		GridBagConstraints gbc_progressBar_1 = new GridBagConstraints();
		gbc_progressBar_1.fill = GridBagConstraints.BOTH;
		gbc_progressBar_1.gridwidth = 2;
		gbc_progressBar_1.insets = new Insets(0, 0, 5, 5);
		gbc_progressBar_1.gridx = 1;
		gbc_progressBar_1.gridy = 3;
		progressBarPanel.add(progressBar_1, gbc_progressBar_1);
		progressBar_1.setValue(50);
		
		JLabel lblSubProgress_1 = new JLabel("sub progress 2:");
		GridBagConstraints gbc_lblSubProgress_1 = new GridBagConstraints();
		gbc_lblSubProgress_1.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblSubProgress_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubProgress_1.gridx = 1;
		gbc_lblSubProgress_1.gridy = 4;
		progressBarPanel.add(lblSubProgress_1, gbc_lblSubProgress_1);
		
		JLabel lblSample_1 = new JLabel("sample");
		GridBagConstraints gbc_lblSample_1 = new GridBagConstraints();
		gbc_lblSample_1.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblSample_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblSample_1.gridx = 2;
		gbc_lblSample_1.gridy = 4;
		progressBarPanel.add(lblSample_1, gbc_lblSample_1);
		
		JProgressBar progressBar_2 = new JProgressBar();
		GridBagConstraints gbc_progressBar_2 = new GridBagConstraints();
		gbc_progressBar_2.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar_2.fill = GridBagConstraints.BOTH;
		gbc_progressBar_2.gridwidth = 2;
		gbc_progressBar_2.gridx = 1;
		gbc_progressBar_2.gridy = 5;
		progressBarPanel.add(progressBar_2, gbc_progressBar_2);
		progressBar_2.setValue(50);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("player control", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{140, 140, 69, 150, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 47, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblSortBy = new JLabel("sort by:");
		GridBagConstraints gbc_lblSortBy = new GridBagConstraints();
		gbc_lblSortBy.insets = new Insets(0, 0, 5, 5);
		gbc_lblSortBy.gridx = 2;
		gbc_lblSortBy.gridy = 0;
		panel.add(lblSortBy, gbc_lblSortBy);
		
		ButtonGroup group = new ButtonGroup();
		
		JList<Player> list = new JList<Player>();
		list.setModel(new AbstractListModel<Player>() {
			private static final long serialVersionUID = 1L;
			public int getSize() {
				return Player.getConnectedPlayerNames().size();
			}
			public Player getElementAt(int index) {
				return Player.getPlayers().get(index);
			}
		});
		
		JRadioButton rdbtnA = new JRadioButton("player name");
		rdbtnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		rdbtnA.setSelected(true);
		group.add(rdbtnA);
		GridBagConstraints gbc_rdbtnA = new GridBagConstraints();
		gbc_rdbtnA.anchor = GridBagConstraints.WEST;
		gbc_rdbtnA.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnA.gridx = 3;
		gbc_rdbtnA.gridy = 0;
		panel.add(rdbtnA, gbc_rdbtnA);
		
		JRadioButton rdbtnTotalConnectTime = new JRadioButton("total connect time");
		group.add(rdbtnTotalConnectTime);
		GridBagConstraints gbc_rdbtnTotalConnectTime = new GridBagConstraints();
		gbc_rdbtnTotalConnectTime.anchor = GridBagConstraints.WEST;
		gbc_rdbtnTotalConnectTime.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnTotalConnectTime.gridx = 3;
		gbc_rdbtnTotalConnectTime.gridy = 1;
		panel.add(rdbtnTotalConnectTime, gbc_rdbtnTotalConnectTime);
		
		JLabel lblPlayerOptions = new JLabel("player options: ");
		lblPlayerOptions.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPlayerOptions = new GridBagConstraints();
		gbc_lblPlayerOptions.anchor = GridBagConstraints.WEST;
		gbc_lblPlayerOptions.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerOptions.gridx = 0;
		gbc_lblPlayerOptions.gridy = 2;
		panel.add(lblPlayerOptions, gbc_lblPlayerOptions);
		
		JLabel lblPlayers = new JLabel("players:");
		GridBagConstraints gbc_lblPlayers = new GridBagConstraints();
		gbc_lblPlayers.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayers.gridx = 2;
		gbc_lblPlayers.gridy = 2;
		panel.add(lblPlayers, gbc_lblPlayers);
		
		JRadioButton rdbtnSessionConnectTime = new JRadioButton("session connect time");
		group.add(rdbtnSessionConnectTime);
		GridBagConstraints gbc_rdbtnSessionConnectTime = new GridBagConstraints();
		gbc_rdbtnSessionConnectTime.anchor = GridBagConstraints.WEST;
		gbc_rdbtnSessionConnectTime.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnSessionConnectTime.gridx = 3;
		gbc_rdbtnSessionConnectTime.gridy = 2;
		panel.add(rdbtnSessionConnectTime, gbc_rdbtnSessionConnectTime);
		
		JComboBox<ActionsAvailableToPerformOnPlayers> comboBox = new JComboBox<ActionsAvailableToPerformOnPlayers>();
		comboBox.setModel(new DefaultComboBoxModel<ActionsAvailableToPerformOnPlayers>(ActionsAvailableToPerformOnPlayers.values()));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 3;
		panel.add(comboBox, gbc_comboBox);
		
		
		list.setSelectedIndex(0);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 3;
		gbc_list.gridwidth = 2;
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 2;
		gbc_list.gridy = 3;
		panel.add(list, gbc_list);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 4;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{228, 0};
		gbl_panel_3.rowHeights = new int[]{58, 58, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JButton btnPerformAction = new JButton("perform action");
		btnPerformAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				list.getSelectedValue().performCommandAction((ActionsAvailableToPerformOnPlayers)comboBox.getSelectedItem());
			}
		});
		GridBagConstraints gbc_btnPerformAction = new GridBagConstraints();
		gbc_btnPerformAction.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnPerformAction.insets = new Insets(0, 0, 5, 0);
		gbc_btnPerformAction.gridx = 0;
		gbc_btnPerformAction.gridy = 0;
		panel_3.add(btnPerformAction, gbc_btnPerformAction);
		
		JLabel lblMaxPlayers = new JLabel("Max players:");
		lblMaxPlayers.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_lblMaxPlayers = new GridBagConstraints();
		gbc_lblMaxPlayers.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblMaxPlayers.gridx = 0;
		gbc_lblMaxPlayers.gridy = 1;
		panel_3.add(lblMaxPlayers, gbc_lblMaxPlayers);
		
		JSlider playerSlider = new JSlider();
		playerSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblMaxPlayers.setText("Max allowed players: " + playerSlider.getValue());
			}
		});
		playerSlider.setSnapToTicks(true);
		playerSlider.setPaintTicks(true);
		playerSlider.setPaintLabels(true);
		playerSlider.setMinorTickSpacing(1);
		playerSlider.setMinimum(1);
		playerSlider.setMaximum(1000);
		GridBagConstraints gbc_playerSlider = new GridBagConstraints();
		gbc_playerSlider.gridwidth = 2;
		gbc_playerSlider.fill = GridBagConstraints.BOTH;
		gbc_playerSlider.insets = new Insets(0, 0, 0, 5);
		gbc_playerSlider.gridx = 0;
		gbc_playerSlider.gridy = 5;
		panel.add(playerSlider, gbc_playerSlider);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("server settings", null, scrollPane_1, null);
		
		JPanel panel_1 = new JPanel();
		scrollPane_1.setViewportView(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{237, 237, 148, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblGeneralSettins = new JLabel("General settings:");
		GridBagConstraints gbc_lblGeneralSettins = new GridBagConstraints();
		gbc_lblGeneralSettins.anchor = GridBagConstraints.WEST;
		gbc_lblGeneralSettins.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeneralSettins.gridx = 0;
		gbc_lblGeneralSettins.gridy = 0;
		panel_1.add(lblGeneralSettins, gbc_lblGeneralSettins);
		
		pwdServerSpecificPassword = new JPasswordField();
		pwdServerSpecificPassword.setToolTipText("");
		pwdServerSpecificPassword.setText("aaaa");
		GridBagConstraints gbc_pwdServerSpecificPassword = new GridBagConstraints();
		gbc_pwdServerSpecificPassword.insets = new Insets(0, 0, 5, 5);
		
		char defaultPassWordChar = pwdServerSpecificPassword.getEchoChar();
		
		gbc_pwdServerSpecificPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdServerSpecificPassword.gridx = 1;
		gbc_pwdServerSpecificPassword.gridy = 0;
		panel_1.add(pwdServerSpecificPassword, gbc_pwdServerSpecificPassword);
		
		JCheckBox chckbxShowPassword = new JCheckBox("show password");
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxShowPassword.isSelected()){
					pwdServerSpecificPassword.setEchoChar((char) 0);
				}else{
					pwdServerSpecificPassword.setEchoChar(defaultPassWordChar);
				}
			}
		});
		GridBagConstraints gbc_chckbxShowPassword = new GridBagConstraints();
		gbc_chckbxShowPassword.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxShowPassword.gridx = 2;
		gbc_chckbxShowPassword.gridy = 0;
		panel_1.add(chckbxShowPassword, gbc_chckbxShowPassword);
		
		JCheckBox chckbxOption_1 = new JCheckBox("option_0");
		GridBagConstraints gbc_chckbxOption_1 = new GridBagConstraints();
		gbc_chckbxOption_1.anchor = GridBagConstraints.WEST;
		gbc_chckbxOption_1.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxOption_1.gridx = 0;
		gbc_chckbxOption_1.gridy = 1;
		panel_1.add(chckbxOption_1, gbc_chckbxOption_1);
		
		JCheckBox chckbxOption = new JCheckBox("require server password");
		chckbxOption.setToolTipText("server specific password, force everyone that want to log in to the server to type in a server specific password");
		GridBagConstraints gbc_chckbxOption = new GridBagConstraints();
		gbc_chckbxOption.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxOption.anchor = GridBagConstraints.WEST;
		gbc_chckbxOption.gridx = 1;
		gbc_chckbxOption.gridy = 1;
		panel_1.add(chckbxOption, gbc_chckbxOption);
		
		JButton btnCopyPasswordTo = new JButton("copy password to clipboard");
		btnCopyPasswordTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(new String(pwdServerSpecificPassword.getPassword())), new StringSelection("A-Spaceworks server apssword selection"));
			}
		});
		GridBagConstraints gbc_btnCopyPasswordTo = new GridBagConstraints();
		gbc_btnCopyPasswordTo.insets = new Insets(0, 0, 5, 0);
		gbc_btnCopyPasswordTo.gridx = 2;
		gbc_btnCopyPasswordTo.gridy = 1;
		panel_1.add(btnCopyPasswordTo, gbc_btnCopyPasswordTo);
		
		JSplitPane splitPane = new JSplitPane();
		tabbedPane.addTab("Log", null, splitPane, null);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(1.0);
		splitPane.setContinuousLayout(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		splitPane.setLeftComponent(scrollPane);
		
		log = new JTextArea();
		log.setEditable(false);
		log.setText("The server log\n");
		scrollPane.setColumnHeaderView(log);
		
		txtInput = new JTextField();
		txtInput.setForeground(Color.GRAY);
		txtInput.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtInput.getText().toLowerCase().equals("input")){
					txtInput.setText("");
					txtInput.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtInput.getText().equals("")){
					txtInput.setForeground(Color.GRAY);
					txtInput.setText("input");
				}
			}
		});
		txtInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getExtendedKeyCode()==KeyEvent.VK_ENTER){
					String message = txtInput.getText();
					txtInput.setText("");
					server.inputRecived(message);
				}
			}
		});
		splitPane.setRightComponent(txtInput);
		txtInput.setText("input");
		txtInput.setColumns(10);
	}
	
	void writeToLog(String message){
		log.append(message + "\n");
	}
}
