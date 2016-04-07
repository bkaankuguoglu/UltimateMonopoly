package gui;

import java.awt.Color;
import java.awt.Dimension;

//This code was inspired by and uses some 
//parts of the project of CS132 course of University of Waterloo in 2005

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;

import backend.Auction;
import backend.ConsoleGenerator;
import backend.DialogGenerator;
import backend.Game;
import backend.Player;
import backend.intSq;
import boardrelated.Stock;
import boardrelated.buyableSquare;



/*import becker.util.IView;
import monopoly.gui.MonopolyGUI;
import monopoly.gui.PlayerView;
import monopoly.gui.PlayerView.BuyHouseListener;
import monopoly.gui.PlayerView.BuyPropertyListener;
import monopoly.gui.PlayerView.FinishedListener;
import monopoly.model.ComputerPlayer;
import monopoly.model.HumanPlayer;
import monopoly.model.Player;
 */
public class GUIPlayer extends JPanel implements intSq
{
	private JLabel balance = new JLabel("$0");
	private JLabel netWorth = new JLabel("$0");
	private JButton buyProperty = new JButton("Buy");
	private JButton finished = new JButton("Finished");
	private JButton rollDice = new JButton("Roll Dice");
	private JButton useCard = new JButton("Use Card");
	private JButton sellProperty = new JButton("Sell");
	private JButton build = new JButton("Build");
	private JButton deeds = new JButton("Owned Deeds");
	private JButton mortgaged = new JButton("Mortgaged");
	private JButton cards = new JButton("Cards");
	//private JButton dismortgage = new JButton("Dismortgage");
	//private JList ownedProp = new JList();
	//private JList mortgageProp = new JList();
	//private JList ownedCards = new JList();
	private static final Font buttonFont = new Font("SansSerif", Font.BOLD, 8);
	private Player model;
	private Game gameModel;

	/** Construct a new player view
	   @param aPlayer the model for this view */
	public GUIPlayer(Player aPlayer, Game gameModel)
	{  super();
	this.model = aPlayer;
	this.gameModel=gameModel;
	this.layoutView();
	this.registerListeners();
	this.model.addView(this);
	}

	/** Update the information shown by this view.*/
	public void updateView()
	{
		this.balance.setText("Balance: $" + this.model.getBalance());
		this.netWorth.setText("Net Worth: $" + this.model.getReceivableWorth());
		//this.ownedProp.setListData(this.model.getPropertyNames());
		//this.ownedCards.setListData(this.model.getCardNames());
		//this.ownedCards.setListData(this.model.getMortgageNames());

		Player hp = this.model;
		// enable or disable controls
		this.buyProperty.setEnabled(hp.isTakingTurn() && hp.canBuyCurrentSquare());
		this.finished.setEnabled(hp.isTakingTurn());
		this.rollDice.setEnabled(hp.isCanRoll());
		this.useCard.setEnabled(hp.getCardList().size()!=0);
		this.sellProperty.setEnabled(hp.isTakingTurn());
		this.build.setEnabled(hp.isTakingTurn());
		this.deeds.setEnabled(true);
		this.mortgaged.setEnabled(true);
		this.cards.setEnabled(true);
		this.buyProperty.setFocusable(false);
		this.finished.setFocusable(false);
		this.rollDice.setFocusable(false);
		this.useCard.setFocusable(false);
		this.sellProperty.setFocusable(false);
		this.build.setFocusable(false);
		this.deeds.setFocusable(false);
		this.mortgaged.setFocusable(false);
		this.cards.setFocusable(false);
		//this.dismortgage.setEnabled(hp.isTakingTurn());
		//this.ownedProp.setEnabled(true);
		//this.ownedCards.setEnabled(true);
		//this.mortgageProp.setEnabled(true);
	}


	/** Layout all the components in this view. */
	private void layoutView()
	{
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder(this.model.getPlayerName()));

		//this.setMinimumSize(new Dimension(100,150));

		this.balance.setFont(GUIPlayer.buttonFont);
		this.netWorth.setFont(GUIPlayer.buttonFont);
		this.buyProperty.setFont(GUIPlayer.buttonFont);
		this.finished.setFont(GUIPlayer.buttonFont);
		this.rollDice.setFont(GUIPlayer.buttonFont);
		this.useCard.setFont(GUIPlayer.buttonFont);
		this.sellProperty.setFont(GUIPlayer.buttonFont);
		this.build.setFont(GUIPlayer.buttonFont);
		this.deeds.setFont(GUIPlayer.buttonFont);
		this.mortgaged.setFont(GUIPlayer.buttonFont);
		this.cards.setFont(GUIPlayer.buttonFont);
		//this.dismortgage.setFont(GUIPlayer.buttonFont);
		//this.ownedProp.setFont(GUIPlayer.buttonFont);
		//this.ownedCards.setFont(GUIPlayer.buttonFont);
		//this.mortgageProp.setFont(GUIPlayer.buttonFont);


		/*this.ownedProp.setVisible(true);
	      this.ownedProp.setVisibleRowCount(1);
	      JScrollPane propScroller1 = new JScrollPane(this.ownedProp);
	      propScroller1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	      propScroller1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	      JViewport jv1 = new JViewport();
	      jv1.setView(new JLabel("Deeds"));
	      propScroller1.setColumnHeader(jv1);	      


	      this.ownedCards.setVisible(true);
	      this.ownedCards.setVisibleRowCount(1);
	      JScrollPane propScroller2 = new JScrollPane(this.ownedCards);
	      propScroller2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	      propScroller2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	      JViewport jv2 = new JViewport();
	      jv2.setView(new JLabel("Cards"));
	      propScroller2.setColumnHeader(jv2);

	      this.mortgageProp.setVisible(true);
	      this.mortgageProp.setVisibleRowCount(1);
	      JScrollPane propScroller3 = new JScrollPane(this.mortgageProp);
	      propScroller3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	      propScroller3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	      JViewport jv3 = new JViewport();
	      jv3.setView(new JLabel("Mortgaged Deeds"));
	      propScroller3.setColumnHeader(jv3);*/

		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0.25;
		gc.weighty = 0.0;
		//this.add(new Token(this.model.getID()), gc);
		gc.fill = gc.BOTH;
		this.add(this.balance, gc);
		gc.gridx++;
		gc.gridwidth=3;
		this.add(this.netWorth, gc);
		gc.gridwidth=1;
		gc.gridx=0;
		gc.gridy++;
		this.add(this.rollDice, gc);
		gc.gridx++;
		gc.weighty = 0.0;
		this.add(this.finished, gc);
		gc.gridx++;
		gc.weighty = 0.0;
		this.add(this.buyProperty, gc);
		gc.gridx=0;
		gc.gridy++;
		gc.weighty = 0.0;
		this.add(this.sellProperty, gc);
		gc.gridx++;
		this.add(this.build, gc);
		gc.gridx++;
		this.add(this.useCard, gc);
		gc.gridy++;
		gc.gridx=0;
		this.add(this.deeds, gc);
		gc.gridx++;
		this.add(this.mortgaged, gc);
		gc.gridx++;
		this.add(this.cards, gc);
		gc.gridx++;
		/*this.add(propScroller1, gc);
	      gc.gridy++;
	      gc.weighty = 0.75;
	      this.add(propScroller3, gc);
	      gc.gridy++;
	      gc.weighty = 0.75;
	      this.add(propScroller2, gc);*/
	}

	private void registerListeners()
	{  this.buyProperty.addActionListener(new BuyPropertyListener());
	this.finished.addActionListener(new FinishedListener());
	this.rollDice.addActionListener(new RollDiceListener());
	this.useCard.addActionListener(new UseCardListener());
	this.sellProperty.addActionListener(new SellPropertyListener());
	this.build.addActionListener(new BuildListener());
	this.deeds.addActionListener(new DeedsListener());
	this.mortgaged.addActionListener(new MortgagedListener());
	//this.dismortgage.addActionListener(new DismortgageListener());
	this.cards.addActionListener(new CardsListener());
	}

	/** Paint the view. */
	public void paintComponent(Graphics g)
	{  // fill in the background
		super.paintComponent(g);
		g.setColor(this.getBackground());
		g.fillRect(0,0,this.getWidth(), this.getHeight());

		// paint the player's token
		g.setColor(GUIHelper.PLAYER_COLORS[this.model.getPlayerNo()]);
		Insets insets = this.getInsets();
		g.fillOval(this.getWidth() - insets.right - 20, insets.top - 10, 20, 20);

		// paint the other components
		this.paintComponents(g);
	}

	/** The listener for the Buy Property button. */
	private class BuyPropertyListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt){

			model.getLocation().setAuctioned(true);
			model.buySquare(model.getbuyableLocation());

		}	      
	}

	/** The listener for the Finished button. */
	private class FinishedListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt){

			if(model.getLocation() instanceof buyableSquare){
				if(model.getLocation().isAuctioned()){
					model.getLocation().setAuctioned(false);
				}else{
					Auction.putAuction(model.getbuyableLocation(), gameModel);
				}
			}
			gameModel.finishTurn();
		}
	}

	private class RollDiceListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt){

			gameModel.throwDiceAndPerform();

		}
	}

	private class UseCardListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt){

			DialogGenerator.UseCardDialog(model, gameModel);;

		}
	}

	private class SellPropertyListener implements ActionListener{
		public void actionPerformed(ActionEvent evt){
			DialogGenerator.sellDialog(model, gameModel);

		}
	}

	private class BuildListener implements ActionListener{
		public void actionPerformed(ActionEvent evt){
			DialogGenerator.buildDialog(model, gameModel);
		}
	}

	private class DeedsListener implements ActionListener{
		public void actionPerformed(ActionEvent evt){
			String [] hop= {"dsada", "asdada", "adasd"}; 
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, model.getPropertyNames());
			ArrayList<Stock> stocks1 = model.getAllStocks();
			String[] stocks = new String[stocks1.size()];
			for(int o=0; o<stocks1.size();o++){
				stocks[o]=stocks1.get(o).getName();
			}
			JOptionPane.showMessageDialog(frame, stocks);
		}
	}

	private class MortgagedListener implements ActionListener{
		public void actionPerformed(ActionEvent evt){
			String [] hop= {"dsada", "asdada", "adasd","","","","",""}; 
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, model.getMortgageNames());
			ArrayList<Stock> stocks1 = model.getMortgagedStocks();
			String[] stocks = new String[stocks1.size()];
			for(int o=0; o<stocks1.size();o++){
				stocks[o]=stocks1.get(o).getName();
			}
			JOptionPane.showMessageDialog(frame, stocks);
		}
	}

	private class CardsListener implements ActionListener{
		public void actionPerformed(ActionEvent evt){
			String [] hop= {"dsada", "asdada", "adasd"}; 
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, model.getCardNames());
		}
	}

}

