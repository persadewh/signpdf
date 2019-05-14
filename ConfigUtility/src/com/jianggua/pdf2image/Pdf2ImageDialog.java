/**
 * @Description 
 * @author Ray Wei
 * @Date 2019-5-14
 */
package com.jianggua.pdf2image;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jianggua.util.FileUtil;

/**
 * @ClassName Pdf2ImageDialog
 * @Description 
 * @author Ray Wei
 * @Date 2019-5-14
 */
public class Pdf2ImageDialog extends JDialog {

	static final Logger logger = LogManager.getLogger(Pdf2ImageDialog.class);
	
	private Pdf2ImageDialog mainDlg = null;
	
	public Pdf2ImageDialog(){
		mainDlg = this;
		this.setPreferredSize(new Dimension(700, 500));
		this.setTitle("Location");
		
		label.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getButton() == MouseEvent.BUTTON3){
					Point point = e.getPoint();
					//BufferedImage image = ImageIO.read(new File(strCurrentImageFilePath));
					//logger.info("Image Width: " + point.getX() + ", Height: " + point.getY());
					logger.info("X: " + point.getX() + ", Y: " + point.getY());
					
					double pdfLocationHeight = label.getHeight() - point.getY();
					
//					logger.info("Config : " + point.getX() / scale + "," + pdfLocationHeight / scale);
					double configX =  point.getX() / scale;
					double configY = pdfLocationHeight / scale;
					logger.info("Config : " + configX + "," + configY);
					JOptionPane.showMessageDialog(mainDlg, "X: " + String.valueOf(configX) + ", Y: " + String.valueOf(configY), "Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		JMenuItem menuItemOpen = new JMenuItem("Open");
		menuItemOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openMenuClick(e);
			}
		});
		menu.add(menuItemOpen);
		
		menuItemActionListener = new InnerMenuItemActionListener();
		JMenu menuScale = new JMenu("Scale");
		//JMenuItem menuItemScaleX1 = new JMenuItem("X1");
		menuItemScaleX1.addActionListener(menuItemActionListener);
		//JMenuItem menuItemScaleX2 = new JMenuItem("X2");
		menuItemScaleX2.addActionListener(menuItemActionListener);
		//JMenuItem menuItemScaleX3 = new JMenuItem("X3");
		menuItemScaleX3.addActionListener(menuItemActionListener);
		
		menuScale.add(menuItemScaleX1);
		menuScale.add(menuItemScaleX2);
		menuScale.add(menuItemScaleX3);
		
		menuBar.add(menu);
		menuBar.add(menuScale);
		
		JScrollPane scrollPanel = new JScrollPane();
		
		scrollPanel.setViewportView(label);
		
		scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		
		this.add("Center", scrollPanel);
		
		this.pack();
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	private void openMenuClick(ActionEvent e){
		String strPdfFilePath = FileUtil.chooseFile(this, "location", ".pdf", "Please choose one pdf file");
		if(null != strPdfFilePath){
			logger.info(strPdfFilePath);
			strCurrentPdfFilePath = strPdfFilePath;
			strCurrentImageFilePath = Pdf2Image.pdf2image(strPdfFilePath, 0, Constants.DPI, 1);
			logger.info(strCurrentImageFilePath);
			if(null != strCurrentImageFilePath){
				ImageIcon icon = new ImageIcon(strCurrentImageFilePath);
				label.setIcon(icon);
			}
		}
	}
	
	private JLabel label = new JLabel();
	private String strCurrentPdfFilePath = null;
	private String strCurrentImageFilePath = null;
	private InnerMenuItemActionListener menuItemActionListener = null;
	private int scale = 1;
	
	private JMenuItem menuItemScaleX1 = new JMenuItem("X1");
	private JMenuItem menuItemScaleX2 = new JMenuItem("X2");
	private JMenuItem menuItemScaleX3 = new JMenuItem("X3");
	
	class InnerMenuItemActionListener implements ActionListener{

		/* (non-Javadoc)
		 * actionPerformed
		 * @Description 
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == menuItemScaleX1){
				scale = 1;
			} else if(e.getSource() == menuItemScaleX2){
				scale = 2;
			} else if(e.getSource() == menuItemScaleX3){
				scale = 3;
			}
			if(null != strCurrentPdfFilePath){
				strCurrentImageFilePath = Pdf2Image.pdf2image(strCurrentPdfFilePath, 0, Constants.DPI, scale);
				logger.info(strCurrentImageFilePath);
				if(null != strCurrentImageFilePath){
					ImageIcon icon = new ImageIcon(strCurrentImageFilePath);
					label.setIcon(icon);
				}
			} else{
				//print error
				JOptionPane.showMessageDialog(mainDlg, "PDF file not exist", "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
}
