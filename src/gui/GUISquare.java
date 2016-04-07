package gui;

//This code was inspired by and uses some 
//parts of the project of CS132 course of University of Waterloo in 2005

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import backend.DialogGenerator;
import backend.Game;
import backend.Player;
import backend.intSq;
import boardrelated.RailroadTrackSquare;
import boardrelated.Square;
import boardrelated.cabSquare;
import boardrelated.deedSquare;

/*import becker.util.IView;
import monopoly.gui.MonopolyGUI;
import monopoly.model.Player;
import monopoly.model.Square;
*/
public class GUISquare extends JPanel implements intSq
{
	   private Square model;
	   private JTextArea tArea = new JTextArea(2,20);
	   private static final Font defFont = new Font("SansSerif", Font.BOLD, 10);
	   private static final int TOKEN_DIA = 10;

	   /** Construct a new view.
	   @param aSquare the model for this view */
	   public GUISquare(Square aSquare, Game gameModel){
	      super();
	      this.model = aSquare;

	      this.layoutView();
	      this.model.addView(this);
	      
	     
	      
	   }

	   /** Layout all the components in this view. */
	   private void layoutView()
	   {  this.setLayout(new GridLayout());
	      //this.setPreferredSize(new Dimension(60,60));
	      this.tArea.setFont(this.defFont);
	      this.tArea.setOpaque(false);
	      this.tArea.setEditable(false);
	      this.tArea.setBackground(Color.BLUE);
	      this.tArea.setText(this.model.getName());
	      this.add(tArea);
	   }

	   /** Update the information shown by this view.*/
	   public void updateView()
	   {
	      this.repaint();
	   }

	   /** Paint the view. */
	   public void paintComponent(Graphics g)
	   {
		   super.paintComponent(g);
		   g.setColor(this.model.getColor());
		   g.fillRect(0,0,this.getWidth(),20);
		   g.drawRect(0, 0, this.getWidth(), this.getHeight());
		   g.drawLine(0, this.getHeight()-1, this.getWidth()-1, this.getHeight()-1);
		   g.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight()-1);
		   if(this.model instanceof deedSquare){
			   g.setColor(Color.BLACK);
			   g.setFont(new Font("SansSerif", Font.BOLD, 9));
			   g.drawString("HS: " + ((deedSquare)this.model).getVisNumOfHouses() + " HT: " 
					   + ((deedSquare)this.model).getNumberOfHotels() + " S: "
					   + ((deedSquare)this.model).getNumberOfSkyscrapers(), 0, this.getHeight()-1);
		   }
		   if(this.model instanceof cabSquare){
			   g.setColor(Color.BLACK);
			   g.setFont(new Font("SansSerif", Font.BOLD, 9));
			   g.drawString("Stand: " + ((cabSquare)this.model).getNumOfStand(),
					   0, this.getHeight()-1);
		   }
		   if(this.model instanceof RailroadTrackSquare){
			   g.setColor(Color.BLACK);
			   g.setFont(new Font("SansSerif", Font.BOLD, 9));
			   g.drawString("Depot: " + ((RailroadTrackSquare)this.model).getNumOfDepot(),
					   0, this.getHeight()-1);
		   }
		   Player[] playersHere = this.model.getPlayerList();
		   for(int i=0; i<playersHere.length; i++)
		   {  
			  if(playersHere[i]!=null){ 
			  int pID = playersHere[i].getPlayerNo();
		      g.setColor(GUIHelper.PLAYER_COLORS[pID]);
		      g.fillOval(i*TOKEN_DIA, 20, TOKEN_DIA, TOKEN_DIA);
			  }
	      } 
	   }
	   
	}
