package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//This code was inspired by and uses some 
//parts of the project of CS132 course of University of Waterloo in 2005

import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import backend.DialogGenerator;
import backend.Game;

/*import monopoly.gui.MonopolyGUI;
import monopoly.model.Monopoly;
*/
public class GUI extends JFrame{
	
	public GUI(final Game model) throws IOException
	   {  super("Monopoly");
	   	  //this.setLayout(new BorderLayout());
	   	  
	      JPanel contents = new GUIHelper(model);
	      /*JScrollPane jsp = new JScrollPane(contents);
	      jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	      jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	      this.add(jsp, BorderLayout.CENTER);*/
	      this.setContentPane(contents);
	      this.setSize(1360, 700);
	      //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	      this.setVisible(true);
	      //this.requestFocus();
	      //this.setFocusable(true);
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      contents.setFocusable(true);
	      contents.getActionMap().put("Save", new AbstractAction() {
              public void actionPerformed(ActionEvent e) {
                  DialogGenerator.SaveGame(model);
              }
          });
	      contents.getActionMap().put("Load", new AbstractAction() {
              public void actionPerformed(ActionEvent e) {
            	  DialogGenerator.LoadGame(model);
              }
          });
	      InputMap inputMap = contents.getInputMap();
	        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK), "Save");
	        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK), "Load");
	      
	   }

}
