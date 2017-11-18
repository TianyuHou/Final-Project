package com.Tianyu.dealerListDisplay;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.SystemColor;

public class Display extends JFrame {

	private JPanel contentPane;
	private JPanel panelTop;
	private JPanel leftPanel;
	private JScrollPane scrollPane;

	private JFrame frame;

	private static Point origin = new Point();
	private JTextField textField;

	private ArrayList<Vehicle> list;
	private JTable table;

	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;

	private JCheckBox chckbxYear;
	private JCheckBox chckbxWebId;
	private JCheckBox chckbxId;
	private JCheckBox chckbxPrice;
	private JCheckBox chckbxType;
	private JCheckBox chckbxMake;
	private JCheckBox chckbxModel;
	private JCheckBox chckbxCategory;

	private JLabel lblSort;
	private JLabel lblSearch;
	private JLabel labelBG;
	
	private JButton close;
	private JButton min;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display frame = new Display();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Display() {
		String f = Display.class.getResource("asset/test.txt").getPath();
		File file = new File(f);
		list = Service.readAndGetVehicles(file);
		contentPane = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 1300, 800);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		
		registerPanel();
		
		registerAEDBtn();
		
		registerSortCheckBox();
		
		registerTable();
		
		registerSearch();
		
		setCloseAndMin();

		setDrag();
	}
	
	//close
	private void exit() {
		System.exit(0);
	}
	
	//minimize
	private void minimize() {
		frame.setExtendedState(ICONIFIED);
	}
	
	//Panel
	private void registerPanel() {
		//Left Panel
		leftPanel = new JPanel();
		leftPanel.setBounds(0, 0, 350, 800);
		leftPanel.setLayout(null);
		contentPane.add(leftPanel);
	
		labelBG = new JLabel("");
		labelBG.setDisplayedMnemonic('0');
		labelBG.setIcon(new ImageIcon(Display.class.getResource("/com/Tianyu/dealerListDisplay/asset/1.jpg")));
		labelBG.setBounds(0, 0, 350, 800);
		leftPanel.add(labelBG);
		
		//Top Panel
		panelTop = new JPanel();
		panelTop.setBackground(new Color(255, 255, 255));
		panelTop.setBounds(350, 0, 950, 220);
		panelTop.setLayout(null);
		contentPane.add(panelTop);
	}

	//Search
	private void registerSearch() {
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				warn();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			
			public void warn() {
				ArrayList<Vehicle> filter = new ArrayList<>();
				String str = textField.getText();
				str = str.toUpperCase();
				for (Vehicle item : list) {
					String pattern = item.id+item.category.toString()+item.webId+item.make+item.year+item.price+item.type+item.trim+item.model;
					if (pattern.toUpperCase().contains(str)) {
						filter.add(item);
					}
				}
				fillTable(filter);
				
			}
		});
		textField.setFont(new Font("Segoe UI Light", Font.PLAIN, 23));
		textField.setBounds(168, 167, 275, 40);
		panelTop.add(textField);
		textField.setColumns(10);

		lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblSearch.setBounds(70, 168, 175, 30);
		panelTop.add(lblSearch);
		
		
	}
	
	// Sort Function
	private void registerSortCheckBox() {
		lblSort = new JLabel("SortBy");
		lblSort.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblSort.setBounds(70, 35, 105, 29);
		panelTop.add(lblSort);
	
		chckbxWebId = new JCheckBox("WebId");
		chckbxWebId.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Vehicle> tmp = new ArrayList<>(list);
				if (chckbxWebId.isSelected()) {
					Service.sortByWebId(tmp);
					fillTable(tmp);
				} else {
					fillTable(tmp);
				}
	
			}
		});
		chckbxWebId.setFont(new Font("Segoe UI Historic", Font.PLAIN, 23));
		chckbxWebId.setBackground(new Color(255, 255, 255));
		chckbxWebId.setBounds(167, 35, 149, 29);
		panelTop.add(chckbxWebId);
	
		chckbxYear = new JCheckBox("Year");
		chckbxYear.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Vehicle> tmp = new ArrayList<>(list);
				if (chckbxYear.isSelected()) {
					Service.sortByYear(tmp);
					fillTable(tmp);
				} else {
					fillTable(tmp);
				}
	
			}
		});
		chckbxYear.setFont(new Font("Segoe UI Historic", Font.PLAIN, 23));
		chckbxYear.setBackground(new Color(255, 255, 255));
		chckbxYear.setBounds(343, 35, 149, 29);
		panelTop.add(chckbxYear);
	
		chckbxId = new JCheckBox("Id");
		chckbxId.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Vehicle> tmp = new ArrayList<>(list);
				if (chckbxId.isSelected()) {
					Service.sortById(tmp);
					fillTable(tmp);
				} else {
					fillTable(tmp);
				}
	
			}
		});
		chckbxId.setFont(new Font("Segoe UI Historic", Font.PLAIN, 23));
		chckbxId.setBackground(new Color(255, 255, 255));
		chckbxId.setBounds(522, 35, 149, 29);
		panelTop.add(chckbxId);
	
		chckbxPrice = new JCheckBox("Price");
		chckbxPrice.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Vehicle> tmp = new ArrayList<>(list);
				if (chckbxPrice.isSelected()) {
					Service.sortByPrice(tmp);
					fillTable(tmp);
				} else {
					fillTable(tmp);
				}
	
			}
		});
		chckbxPrice.setFont(new Font("Segoe UI Historic", Font.PLAIN, 23));
		chckbxPrice.setBackground(new Color(255, 255, 255));
		chckbxPrice.setBounds(706, 35, 97, 29);
		panelTop.add(chckbxPrice);
	
		chckbxMake = new JCheckBox("Make");
		chckbxMake.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Vehicle> tmp = new ArrayList<>(list);
				if (chckbxMake.isSelected()) {
					Service.sortByMake(tmp);
					fillTable(tmp);
				} else {
					fillTable(tmp);
				}
	
			}
		});
		chckbxMake.setFont(new Font("Segoe UI Historic", Font.PLAIN, 23));
		chckbxMake.setBackground(new Color(255, 255, 255));
		chckbxMake.setBounds(706, 90, 105, 29);
		panelTop.add(chckbxMake);
	
		chckbxCategory = new JCheckBox("Category");
		chckbxCategory.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Vehicle> tmp = new ArrayList<>(list);
				if (chckbxCategory.isSelected()) {
					Service.sortByCategory(tmp);
					fillTable(tmp);
				} else {
					fillTable(tmp);
				}
	
			}
		});
		chckbxCategory.setFont(new Font("Segoe UI Historic", Font.PLAIN, 23));
		chckbxCategory.setBackground(Color.WHITE);
		chckbxCategory.setBounds(167, 90, 149, 29);
		panelTop.add(chckbxCategory);
	
		chckbxModel = new JCheckBox("Model");
		chckbxModel.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Vehicle> tmp = new ArrayList<>(list);
				if (chckbxModel.isSelected()) {
					Service.sortByModel(tmp);
					fillTable(tmp);
				} else {
					fillTable(tmp);
				}
	
			}
		});
		chckbxModel.setFont(new Font("Segoe UI Historic", Font.PLAIN, 23));
		chckbxModel.setBackground(Color.WHITE);
		chckbxModel.setBounds(343, 90, 149, 29);
		panelTop.add(chckbxModel);
	
		chckbxType = new JCheckBox("Type");
		chckbxType.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Vehicle> tmp = new ArrayList<>(list);
				if (chckbxType.isSelected()) {
					Service.sortByType(tmp);
					fillTable(tmp);
				} else {
					fillTable(tmp);
				}
	
			}
		});
		chckbxType.setFont(new Font("Segoe UI Historic", Font.PLAIN, 23));
		chckbxType.setBackground(Color.WHITE);
		chckbxType.setBounds(522, 90, 149, 29);
		panelTop.add(chckbxType);
	}
	
	// ADD Table
	private void registerTable() {
		String[] headers = { "Id", "WebId", "Category", "Year", "Make", "Model", "Trim", "Type", "Price", "Photo" };
		Object[][] cellData = null;

		DefaultTableModel model = new DefaultTableModel(cellData, headers) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);
		fillTable(list);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(350, 220, 950, 580);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(table);
	}

	// ADD DELETE EDIT BTN
	private void registerAEDBtn() {
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Segoe UI Historic", Font.PLAIN, 25));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(SystemColor.textHighlight);
		btnAdd.setBounds(545, 165, 110, 40);
		panelTop.add(btnAdd);

		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Segoe UI Historic", Font.PLAIN, 25));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(SystemColor.textHighlight);
		btnDelete.setBounds(805, 165, 110, 40);
		panelTop.add(btnDelete);

		btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Segoe UI Historic", Font.PLAIN, 25));
		btnEdit.setForeground(new Color(255, 255, 255));
		btnEdit.setBackground(SystemColor.textHighlight);
		btnEdit.setBounds(675, 165, 110, 40);
		panelTop.add(btnEdit);
	}

	private void setCloseAndMin() {
		close = new JButton("");
		close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				exit();
			}
		});
		close.setBorderPainted(false);
		close.setBackground(new Color(255, 255, 255));
		close.setIcon(new ImageIcon(Display.class.getResource("/com/Tianyu/dealerListDisplay/asset/close.png")));
		close.setBounds(905, 12, 32, 38);
		panelTop.add(close);
		
		min = new JButton("");
		min.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				minimize();
			}
		});
		min.setIcon(new ImageIcon(Display.class.getResource("/com/Tianyu/dealerListDisplay/asset/min.png")));
		min.setBorderPainted(false);
		min.setBackground(Color.WHITE);
		min.setBounds(860, 12, 32, 38);
		panelTop.add(min);
	}

	// setDrag
	private void setDrag() {
		frame = (JFrame) contentPane.getParent().getParent().getParent();

		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});

		frame.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = frame.getLocation();
				frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}
		});
	}

	private void fillTable(ArrayList<Vehicle> list) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);

		// String id;
		// String webId;
		// Category category;
		// String year;
		// String make;
		// String model;
		// String trim;
		// String type;
		// double price;
		// URL photo;

		for (Vehicle vehicle : list) {
			String[] arr = new String[10];
			arr[0] = vehicle.id;
			arr[1] = vehicle.webId;
			arr[2] = vehicle.category.toString();
			arr[3] = vehicle.year;
			arr[4] = vehicle.make;
			arr[5] = vehicle.model;
			arr[6] = vehicle.trim;
			arr[7] = vehicle.type;
			arr[8] = String.valueOf(vehicle.price);
			arr[9] = vehicle.photo.toString();

			tableModel.addRow(arr);
		}

		table.invalidate();
	}
}
