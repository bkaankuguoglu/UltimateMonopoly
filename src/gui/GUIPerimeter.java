package gui;

//This code was inspired by and uses some 
//parts of the project of CS132 course of University of Waterloo in 2005

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;

import javax.swing.JFrame;

public class GUIPerimeter extends Object implements LayoutManager2
{
	private int rows;
	private int cols;
	private int originX;
	private int originY;
	private Component centerComp = null;
	private static int stat=0;

	/** To put a component in the center of the layout:
	     <tt>this.add(component, PerimeterLayout.CENTER);</tt> */
	public static final Object CENTER = new Object();

	/** To put a component on the perimeter of the layout:
	     <tt>this.add(component, PerimeterLayout.PERIMETER);</tt> */
	public static final Object PERIMETER = new Object();

	/** Lay the components out in a clockwise directions starting at the
	   upper left corner.
	   @param numRows the number of rows of components
	   @param numCols the number of columns of components */
	public GUIPerimeter(int numRows, int numCols)
	{  this(numRows, numCols, 0, 0);
	}

	/** Lay the components out in a clockwise direction starting at the
	   given origin.
	   @param numRows the number of rows of components
	   @param numCols the number of columns of components
	   @param xOrigin the x coordinate of the cell where layout should begin
	   @param yOrigin the y coordinate of the cell where layout should begin */
	public GUIPerimeter(int numRows, int numCols, int xOrigin, int yOrigin)
	{  super();
	this.rows = numRows;
	this.cols = numCols;
	this.originX = xOrigin;
	this.originY = yOrigin;
	}

	/** Adds the specified component with the specified name to the layout.
	   @param name the component name
	   @param comp the component to be added*/
	public void addLayoutComponent(String name, Component comp)
	{
	}

	/** Removes the specified component from the layout.
	   @param comp the component to be removed*/
	public void removeLayoutComponent(Component comp)
	{
	}

	/** Calculates the preferred size dimensions for the specified
	   panel given the components in the specified parent container.
	   @param parent the component to be laid out
	   @return the preferred size as a Dimension object
	   @see #minimumLayoutSize*/
	public Dimension preferredLayoutSize(Container target)
	{
		synchronized (target.getTreeLock())
		{
			Dimension dim = new Dimension(0, 0);
			//int nmembers = target.getComponentCount();
			int nmembers = 56;
			boolean firstVisibleComponent = true;

			for (int i = 0 ; i < nmembers ; i++)
			{  Component m = target.getComponent(i);
			if (m.isVisible() && m != this.centerComp)
			{
				Dimension d = m.getPreferredSize();
				
				dim.height = Math.max(dim.height, d.height);
				dim.width = Math.max(dim.width, d.width);
			}
			}
			
			Insets insets = target.getInsets();
			dim.width = insets.left + insets.right + dim.width*this.cols;
			dim.height = insets.top + insets.bottom + dim.height*this.rows;

			return dim;
			//return new Dimension(1100,1000);
		}
	}

	/** Calculates the minimum size dimensions for the specified
	   panel given the components in the specified parent container.
	   @param parent the component to be laid out
	   @return the minimum size as a Dimension object
	   @see #preferredLayoutSize*/
	public Dimension minimumLayoutSize(Container target)
	{
		synchronized (target.getTreeLock())
		{
			Dimension dim = new Dimension(0, 0);
			//int nmembers = target.getComponentCount();
			int nmembers = 56;
			boolean firstVisibleComponent = true;

			for (int i = 0 ; i < nmembers ; i++)
			{  Component m = target.getComponent(i);
			if (m.isVisible() && m != this.centerComp)
			{
				Dimension d = m.getMinimumSize();
				dim.height = Math.max(dim.height, d.height);
				dim.width = Math.max(dim.width, d.width);
			}
			}
			Insets insets = target.getInsets();
			dim.width = insets.left + insets.right + dim.width*this.cols;
			dim.height = insets.top + insets.bottom + dim.height*this.rows;
			return dim;
			//return new Dimension(1100,1000);
		}
	}

	/** Lays out the container in the specified panel.
	   @param parent the component which needs to be laid out*/
	public void layoutContainer(Container target)
	{
		synchronized (target.getTreeLock())
		{
			Insets insets = target.getInsets();
			int cellWidth = (target.getWidth() - (insets.left + insets.right))/this.cols;
			int cellHeight = (target.getHeight() - (insets.left + insets.right))/this.rows;

			int nmembers = target.getComponentCount();

			int x = this.originX;
			int y = this.originY;
			for(int i=0; i<nmembers; i++)
			{  Component m = target.getComponent(i);

			if (m == this.centerComp)
			{  // position the center component
				int width = this.cols - 2;
				int height = this.rows - 2;
				m.setLocation(cellWidth, cellHeight);
				m.setSize(cellWidth * width, cellHeight * height);
			} else
			{  // position components on the perimeter
				m.setSize(cellWidth, cellHeight);
				if(stat<56){//First track
					if(stat==35){
						m.setSize(cellWidth,3*cellHeight);
					}
					else if(stat==7){
						m.setSize(cellWidth,3*cellHeight);
					}
					else{
						m.setSize(cellWidth, cellHeight);
					}
					if(stat==7){
						m.setLocation(x*cellWidth, (y-2)*cellHeight);
					}
					else{
						m.setLocation(x*cellWidth, y*cellHeight);				
					}
					if (y == 0 && x < this.cols)
					{  // proceed across the top
						x++;
						stat++;
						//ConsoleGenerator.write2Console(stat);
						if (x == this.cols)
						{  x--;
						y++;
						}
					} else if (y > 0 && y < this.rows && x == this.cols - 1)
					{  // proceed down right edge
						y++;
						stat++;
						//ConsoleGenerator.write2Console(stat);
						if (y == this.rows)
						{  y--;
						x--;
						}
					}  else if (y > 0 && x >= 0 && x < this.cols - 1)
					{  // proceed back across the bottom
						x--;
						stat++;
						//ConsoleGenerator.write2Console(stat);
						if (x < 0)
						{  x = 0;
						y--;
						}
					} else
					{  y--;
					stat++;
					// ConsoleGenerator.write2Console(stat);
					}
				}

				else if(stat>=56 && stat<94){//Second Track
					if(stat==56){
						x=this.originX-2;
						y=this.originY-2;
					}
					if(stat==80){
						x++;
					}else if(stat==61){
						x--;
					}
					if(stat==70){
						m.setSize(3*cellWidth, cellHeight);
					}
					else if(stat==89){
						m.setSize(3*cellWidth, cellHeight);
					}
					else{
						m.setSize(cellWidth, cellHeight);
					}
					if(stat==89){
						m.setLocation((x-2)*cellWidth, y*cellHeight);
					}
					else{
						m.setLocation(x*cellWidth, y*cellHeight);				
					}
					if (y == 2 && x < this.cols-2)
					{  // proceed across the top
						x++;
						stat++;
						//ConsoleGenerator.write2Console(stat);
						if (x == this.cols-2)
						{  x--;
						y++;
						}
					} else if (y > 2 && y < this.rows-2 && x == this.cols - 3)
					{  // proceed down right edge
						y++;
						stat++;
						//ConsoleGenerator.write2Console(stat);
						if (y == this.rows-2)
						{  y--;
						x--;
						}
					}  else if (y > 2 && x >= 2 && x < this.cols - 3)
					{  // proceed back across the bottom
						x--;
						stat++;
						//ConsoleGenerator.write2Console(stat);
						if (x < 2)
						{  x = 2;
						y--;
						}
					} else
					{  y--;
					stat++;
					// ConsoleGenerator.write2Console(stat);
					}
				}
				else{//Third track
					if(stat==94){
						x=this.originX-4;
						y=this.originY-4;
					}
					if(stat==103){
						y--;
					}
					if(stat==114){
						y++;
					}
					m.setLocation(x*cellWidth, y*cellHeight);				
					if (y == 4 && x < this.cols-4)
					{  // proceed across the top
						x++;
						stat++;
						//ConsoleGenerator.write2Console(stat);
						if (x == this.cols-4)
						{  x--;
						y++;
						}
					} else if (y > 4 && y < this.rows-4 && x == this.cols - 5)
					{  // proceed down right edge
						y++;
						stat++;
						//ConsoleGenerator.write2Console(stat);
						if (y == this.rows-4)
						{  y--;
						x--;
						}
					}  else if (y > 4 && x >= 4 && x < this.cols - 5)
					{  // proceed back across the bottom
						x--;
						stat++;
						//ConsoleGenerator.write2Console(stat);
						if (x < 4)
						{  x = 4;
						y--;
						}
					} else
					{  y--;
					stat++;
					// ConsoleGenerator.write2Console(stat);
					}
				}
			}
			}

		}
	}

	public void addLayoutComponent(Component comp, Object constraints)
	{  if (constraints == this.CENTER)
	{  this.centerComp = comp;
	}
	}

	public float getLayoutAlignmentX(Container target)
	{  return 0.5F;
	}

	public float getLayoutAlignmentY(Container target)
	{  return 0.5F;
	}

	public void invalidateLayout(Container target)
	{
	}

	public Dimension maximumLayoutSize(Container target)
	{  
		/*synchronized (target.getTreeLock())
		{
			Dimension dim = new Dimension(0, 0);
			//int nmembers = target.getComponentCount();
			int nmembers = 56;
			boolean firstVisibleComponent = true;

			for (int i = 0 ; i < nmembers ; i++)
			{  Component m = target.getComponent(i);
			if (m.isVisible() && m != this.centerComp)
			{
				Dimension d = m.getPreferredSize();
				System.out.println(d.getHeight());
				dim.height = Math.max(dim.height, d.height);
				dim.width = Math.max(dim.width, d.width);
			}
			}
			Insets insets = target.getInsets();
			dim.width = insets.left + insets.right + dim.width*this.cols;
			dim.height = insets.top + insets.bottom + dim.height*this.rows;

			
			return dim;
		}
			
		//return new Dimension(1080,800);    // bogus!

			//return dim;
		//System.out.println("3232");
*/		return new Dimension(1080,670);    // bogus!

	}
	
}
