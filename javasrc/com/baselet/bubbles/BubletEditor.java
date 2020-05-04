/********************************************************************************/
/*                                                                              */
/*              BubletEditor.java                                               */
/*                                                                              */
/*      Diagram editor bubble                                                   */
/*                                                                              */
/********************************************************************************/
/*      Copyright 2011 Brown University -- Steven P. Reiss                    */
/*********************************************************************************
 *  Copyright 2011, Brown University, Providence, RI.                            *
 *                                                                               *
 *                        All Rights Reserved                                    *
 *                                                                               *
 * This program and the accompanying materials are made available under the      *
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, *
 * and is available at                                                           *
 *      http://www.eclipse.org/legal/epl-v10.html                                *
 *                                                                               *
 ********************************************************************************/



package com.baselet.bubbles;


import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Point;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import com.baselet.diagram.DiagramHandler;
import com.baselet.diagram.DrawPanel;
import com.baselet.diagram.SelectorOld;
import com.baselet.element.interfaces.GridElement;
import com.baselet.element.old.custom.CustomElementHandler;
import com.baselet.gui.CurrentGui;
import com.baselet.gui.pane.OwnSyntaxPane;

import edu.brown.cs.bubbles.board.BoardColors;
import edu.brown.cs.bubbles.board.BoardProperties;
import edu.brown.cs.bubbles.buda.BudaBubble;
import edu.brown.cs.bubbles.buda.BudaXmlWriter;
import edu.brown.cs.bubbles.buda.BudaConstants.BudaBubbleOutputer;

class BubletEditor extends BudaBubble implements BubletConstants, BudaBubbleOutputer
{ 


/********************************************************************************/
/*                                                                              */
/*      Private Storage                                                         */
/*                                                                              */
/********************************************************************************/

private DiagramHandler  diagram_handler;
private BubletGUIBuilder gui_components;
private BubletMenuBuilder menu_builder;

private static final long serialVersionUID = 1;




/********************************************************************************/
/*                                                                              */
/*      Constructors                                                            */
/*                                                                              */
/********************************************************************************/

BubletEditor(DiagramHandler dh)
{
   diagram_handler = dh;
   gui_components = new BubletGUIBuilder(dh); 
   menu_builder = new BubletMenuBuilder();
   
   JComponent mf = gui_components.getMainFrame();
   BoardProperties bp = BoardProperties.getProperties("Bublet");
   int w = bp.getInt("Bublet.editor.width",800);
   int h = bp.getInt("Bublet.editor.height",400);
   Dimension dim = new Dimension(w,h);
   mf.setSize(dim);
   mf.setPreferredSize(dim);
   setSize(dim);
   setPreferredSize(dim);
   setContentPane(gui_components.getMainFrame());
   addFocusComponent(gui_components.getPropertyTextPane().getTextComponent()); 
   
   BubletFactory bf = BubletFactory.getFactory();
   bf.addBubble(this);
   
   if (BoardColors.isInverted()) {
      // ColorOwn.invertColors();
    }
   
   setTitle();
}


@Override protected void localDispose()
{ }



/********************************************************************************/
/*                                                                              */
/*      Access methods                                                          */
/*                                                                              */
/********************************************************************************/

void noteDirtyChanged(boolean changed)
{ 
   setTitle();
}

OwnSyntaxPane getPropertyPane()
{ 
   return gui_components.getPropertyTextPane();
}


CustomElementHandler getCustomElementHandler()
{
   return gui_components.getCustomHandler();
}


void diagramNameChanged(String name)
{
   setTitle();
}



private void setTitle()
{
   String nm = diagram_handler.getName();
   if (nm.equals("new")) {
      String fnm = diagram_handler.getFullPathName();
      if (fnm == null || fnm.equals("")) nm = null;
    }
   if (nm == null) nm = "<< UNNAMED >>";
   if (diagram_handler.isChanged()) {
      nm += " *";
    }
   gui_components.setTitle(nm);
}

DrawPanel getDiagram()
{
   if (diagram_handler != null) {
      return diagram_handler.getDrawPanel();
    }
   
   return null;
}


DiagramHandler getDiagramHandler() 
{
   return diagram_handler;
}



String getSelectedPaletteName()
{
   return gui_components.getPaletteList().getSelectedItem().toString();
}



void setMailPanelEnabled(boolean fg)
{
   gui_components.setMailPanelEnabled(fg);
}


boolean isMailPanelVisible()
{
   return gui_components.getMailPanel().isVisible();
}

void focusPropertyPane()
{
   gui_components.getPropertyTextPane().getTextComponent().requestFocus();
}



void showPalette(String p)
{
   gui_components.setPaletteActive(p);
}

/********************************************************************************/
/*                                                                              */
/*      Callback methods                                                        */
/*                                                                              */
/********************************************************************************/

@Override public void handleSaveRequest()
{ 
   if (diagram_handler.isChanged() && diagram_handler.getFullPathName().length() > 0) {
      diagram_handler.doSave();
    }
   setTitle();
}


/********************************************************************************/
/*                                                                              */
/*      Menuing requests                                                        */
/*                                                                              */
/********************************************************************************/

@Override public void handlePopupMenu(MouseEvent e)
{
   JPopupMenu m = menu_builder.createPopupMenu(this,e);
   
   Point p0 = e.getLocationOnScreen();
   SwingUtilities.convertPointFromScreen(p0,this);
   
   m.show(this,p0.x,p0.y);
}


/********************************************************************************/
/*                                                                              */
/*      Configuration methods                                                   */
/*                                                                              */
/********************************************************************************/

@Override public String getConfigurator()               { return "BUBLET"; }


@Override public void outputXml(BudaXmlWriter xw)
{
   BubletConfigurator.outputXmlForEditor(xw,this);
}




/********************************************************************************/
/*                                                                              */
/*      Menuing methods                                                         */
/*                                                                              */
/********************************************************************************/

JPopupMenu getContextMenu(MouseEvent me)
{
   Point p = new Point(me.getXOnScreen(),me.getYOnScreen());
   SwingUtilities.convertPointFromScreen(p,this);
   Component c = SwingUtilities.getDeepestComponentAt(this,p.x,p.y);
   GridElement ge = diagram_handler.getDrawPanel().getElementToComponent(c);
   if (ge == null) return null;
   
   SelectorOld sold = diagram_handler.getDrawPanel().getSelector();
   if (sold != null) {
      if (sold.getSelectedElements().contains(ge)) {
         sold.selectOnly(ge);
       }
      sold.setDominantEntity(ge);
    }
   
   JPopupMenu pm = CurrentGui.getInstance().getGui().getContextMenu(ge);
   
   return pm;
}




}       // end of class BubletEditor




/* end of BubletEditor.java */

