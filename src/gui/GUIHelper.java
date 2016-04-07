package gui;

//This code was inspired by and uses some 
//parts of the project of CS132 course of University of Waterloo in 2005


import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import backend.Game;
import backend.intSq;



/*import monopoly.gui.MonopolyThread;
import monopoly.model.Monopoly;
*/
public class GUIHelper extends JPanel implements intSq{
	
	static final Color[] PLAYER_COLORS = new Color[]{
            Color.blue, Color.red, Color.green, Color.yellow,
            Color.darkGray, Color.orange, Color.cyan
         };
	
	JList console = new JList<Object>();
	Game model;
	JViewport jv1 = new JViewport();


	public GUIHelper(Game model) throws IOException{
		super();

	      //model.loadGame(this.getOptionalInputFile());
		  this.model=model;
	      this.layoutView(model);
	      this.model.addView(this);
	      

	      /* The HumanPlayer class waits for the human to manipulate the
	      user interface. Therefore it must run in a separate thread so it
	      doesn't freeze the UI at the same time. */
	      Thread mThread = new Thread(new GUIThread(model));
	      mThread.start();
	   }
	
	private void layoutView(Game model) throws IOException{
		
		Box top = Box.createVerticalBox();

		JPanel propertyPanel = this.makePropertiesPanel(model);
		propertyPanel.setMinimumSize(propertyPanel.getMaximumSize());
		JPanel playerPanel = this.makePlayerPanel(model);
		JScrollPane console = this.makeConsole(model);

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); 

		top.add(playerPanel);
		top.add(console);

		//this.add(top);
		this.add(propertyPanel);
		this.add(top);
		
	   }
	
	 private JPanel makePropertiesPanel(Game model) throws IOException
	   {  JPanel p = new JPanel();
	      int numProperties = 56;
	      int propPerSide = (numProperties + 3) / 4 + 1;
	      p.setLayout(new GUIPerimeter(propPerSide, propPerSide,
	                         propPerSide - 1, propPerSide - 1));

	      for(int i=0; i<116; i++)
	      {  GUISquare sq = new GUISquare(model.getSquare(i), model);
	         p.add(sq);
	      }
	      //BufferedImage myPicture = ImageIO.read(new File("grup1.jpg"));
	      //JLabel picLabel = new JLabel(new ImageIcon(myPicture));
	      //p.add(picLabel,GUIPerimeter.CENTER);
	      return p;
	   }
	 
	 private JPanel makePlayerPanel(Game model)
	   {  JPanel panel = new JPanel();
	      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	      int numPlayers = model.getNumberOfPlayers();
	      for(int i=0; i<numPlayers; i++)
	      {  GUIPlayer pv = new GUIPlayer(model.getSpecificPlayer(i), model);
	         panel.add(pv);
	      }
	      return panel;
	   }
	 
	 private JScrollPane makeConsole(Game model){
		  /*JPanel panel = new JPanel();
	      GUIConsole gc = new GUIConsole(model);
	      panel.setSize(100, 100);
			//propScroller1.setMinimumSize(new Dimension(230,150));
			//propScroller1.setMaximumSize(new Dimension(230,150));
			panel.setPreferredSize(new Dimension(100,100));
	      panel.add(gc);
	      
	      return panel;*/
		 
		 
			console.setEnabled(true);
			console.setListData(model.getConsole());
			console.setVisible(true);
			console.setVisibleRowCount(10);
			JScrollPane propScroller1 = new JScrollPane(console);
			propScroller1.setSize(200, 150);
			//propScroller1.setMinimumSize(new Dimension(230,150));
			//propScroller1.setMaximumSize(new Dimension(230,150));
			propScroller1.setPreferredSize(new Dimension(200,150));
			propScroller1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			propScroller1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			
			jv1.setView(new JLabel("Console           Pool:"+model.getTheTable().getPool().getAmount()));
			propScroller1.setColumnHeader(jv1);

			return propScroller1;

		}

	@Override
	public void updateView() {
		console.setListData(model.getConsole());
		jv1.setView(new JLabel("Console           Pool:"+model.getTheTable().getPool().getAmount()));
	}
}
