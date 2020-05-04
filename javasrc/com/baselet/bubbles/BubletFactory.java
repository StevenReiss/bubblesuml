/********************************************************************************/
/*										*/
/*		BubletFactory.java						*/
/*										*/
/*	Main entry point for bubbles UML views					*/
/*										*/
/********************************************************************************/
/*	Copyright 2011 Brown University -- Steven P. Reiss		      */
/*********************************************************************************
 *  Copyright 2011, Brown University, Providence, RI.				 *
 *										 *
 *			  All Rights Reserved					 *
 *										 *
 * This program and the accompanying materials are made available under the	 *
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, *
 * and is available at								 *
 *	http://www.eclipse.org/legal/epl-v10.html				 *
 *										 *
 ********************************************************************************/



package com.baselet.bubbles;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

import com.baselet.control.Main;
import com.baselet.control.config.handler.ConfigHandler;
import com.baselet.control.enums.Program;
import com.baselet.control.enums.RuntimeType;
import com.baselet.control.util.Path;
import com.baselet.control.util.Utils;
import com.baselet.control.util.Utils.BuildInfo;
import com.baselet.diagram.DiagramHandler;
import com.baselet.gui.CurrentGui;

import edu.brown.cs.bubbles.bass.BassFactory;
import edu.brown.cs.bubbles.board.BoardConstants.BoardPluginFilter;
import edu.brown.cs.bubbles.board.BoardLog;
import edu.brown.cs.bubbles.board.BoardMetrics;
import edu.brown.cs.bubbles.board.BoardPluginManager;
import edu.brown.cs.bubbles.board.BoardSetup;
import edu.brown.cs.bubbles.buda.BudaBubble;
import edu.brown.cs.bubbles.buda.BudaBubbleArea;
import edu.brown.cs.bubbles.buda.BudaConstants;
import edu.brown.cs.bubbles.buda.BudaRoot;
import edu.brown.cs.ivy.file.IvyFile;

public class BubletFactory implements BubletConstants, BudaConstants.ButtonListener
{



/********************************************************************************/
/*										*/
/*	Private Storage 							*/
/*										*/
/********************************************************************************/

private File	last_directory;
private Point	create_point;
private BudaRoot buda_root;

private static BubletFactory	the_factory = null;



/********************************************************************************/
/*										*/
/*	Constructors								*/
/*										*/
/********************************************************************************/

private BubletFactory()
{
   last_directory = null;
   create_point = null;
}



static synchronized BubletFactory getFactory()
{
   if (the_factory == null) {
      the_factory = new BubletFactory();
    }
   return the_factory;
}



/********************************************************************************/
/*										*/
/*	Bubbles initialization methods						*/
/*										*/
/********************************************************************************/

public static void setup()
{
   getFactory();

   BuildInfo buildinfo = Utils.readBuildInfo();
   Program.init(buildinfo.version,RuntimeType.BUBBLES_PLUGIN);
   initHomeProgramPath();
   ConfigHandler.loadConfig();

   Main main = Main.getInstance();
   main.init(new BubletGUI(main));

   BudaRoot.registerMenuButton(BUBLET_NEW_BUTTON,the_factory);
   BudaRoot.registerMenuButton(BUBLET_OPEN_BUTTON,the_factory);

   BudaRoot.addBubbleConfigurator("BUBLET",new BubletConfigurator());
}


public static void initialize(BudaRoot br)
{
   BubletFactory bf = getFactory();

   bf.buda_root = br;

   BubletRepository er = new BubletRepository();
   System.err.println("Got repository " + br);
   BassFactory.registerRepository(BudaConstants.SearchType.SEARCH_CODE,er);
   BassFactory.registerRepository(BudaConstants.SearchType.SEARCH_EXPLORER,er);
}




public static void remove()
{
   String dir = Path.homeProgram();
   try {
      IvyFile.remove(dir);
    }
   catch (IOException e) { }
}



/********************************************************************************/
/*										*/
/*	Access methods								*/
/*										*/
/********************************************************************************/

Point getPlacementPoint()
{
   return create_point;
}


BudaBubbleArea getBubbleArea()
{
   return buda_root.getCurrentBubbleArea();
}




/********************************************************************************/
/*										*/
/*	Setup utilities 							*/
/*										*/
/********************************************************************************/

private static void initHomeProgramPath()
{
   File dirf = BoardPluginManager.installResources(BubletFactory.class,
         "bublet",new ResourceFilter());
   if (dirf.exists()) { 
      String dir = dirf.getPath() + File.separator;
      Path.setHomeProgram(dir);
    }
   else {
      String temppath = BoardSetup.getSetup().getLibraryPath("bublet") + "/";
      Path.setHomeProgram(temppath);
    }
}


private static class ResourceFilter implements BoardPluginFilter {

   @Override public boolean accept(String nm) {
      boolean use = false;
      if (nm.startsWith("palettes/")) use = true;
      else if (nm.startsWith("custom_elements/")) use = true;
      else if (!nm.contains("/")) {
         if (nm.endsWith(".html") || nm.endsWith(".png") ||
               nm.endsWith(".properties")) use = true;
       }
      return use;
    }
   
}       // end of inner class ResourceFilter






/********************************************************************************/
/*										*/
/*	Button methods								*/
/*										*/
/********************************************************************************/

@Override public void buttonActivated(BudaBubbleArea bba,String id,Point pt)
{
   create_point = pt;

   try {
      if (id.equals(BUBLET_NEW_BUTTON)) {
	 BoardMetrics.noteCommand("BUBLET_NEW");
	 Main.getInstance().doNew();
       }
      else if (id.equals(BUBLET_OPEN_BUTTON)) {
	JFileChooser chooser = new JFileChooser(last_directory);
	int retval = chooser.showOpenDialog(bba);
	if (retval == JFileChooser.APPROVE_OPTION) {
	   last_directory = chooser.getSelectedFile();
	   BoardMetrics.noteCommand("BUBLET_OPEN");
	   Main.getInstance().doOpen(last_directory.getAbsolutePath());
	 }
       }
      else {
	 BoardLog.logD("BUBLET","Unknown command " + id);
       }
    }
   catch (Throwable t) {
      BoardLog.logE("BUBLET","Problem opening UML diagram",t);
    }
}


static BubletEditor createEditorBubble(File file)
{
   DiagramHandler dh = new DiagramHandler(file);
   Main.getInstance().getDiagrams().add(dh);
   BubletGUI bui = (BubletGUI) CurrentGui.getInstance().getGui();
   BubletEditor be = bui.openDiagram(dh);
   return be;
}




void addBubble(BudaBubble bb)
{
   BudaBubbleArea bba = getBubbleArea();
   bba.addBubble(bb,null,create_point,
	 BudaConstants.PLACEMENT_LOGICAL|BudaConstants.PLACEMENT_NEW|
	 BudaConstants.PLACEMENT_MOVETO|BudaConstants.PLACEMENT_USER);
}



}	// end of class BubletFactory




/* end of BubletFactory.java */

