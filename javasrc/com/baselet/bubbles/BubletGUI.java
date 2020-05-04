/********************************************************************************/
/*                                                                              */
/*              BubletGUI.java                                                  */
/*                                                                              */
/*      description of class                                                    */
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

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import com.baselet.control.CanCloseProgram;
import com.baselet.control.config.Config;
import com.baselet.diagram.DiagramHandler;
import com.baselet.diagram.DrawPanel;
import com.baselet.element.old.custom.CustomElementHandler;
import com.baselet.gui.BaseGUI;
import com.baselet.gui.pane.OwnSyntaxPane;

import edu.brown.cs.bubbles.board.BoardLog;
import edu.brown.cs.bubbles.buda.BudaBubble;
import edu.brown.cs.bubbles.buda.BudaRoot;
import edu.brown.cs.bubbles.buda.BudaConstants.BubbleViewCallback;
import edu.brown.cs.bubbles.buda.BudaConstants.BudaWorkingSet;



class BubletGUI extends BaseGUI implements BubletConstants
{


/********************************************************************************/
/*                                                                              */
/*      Private Storage                                                         */
/*                                                                              */
/********************************************************************************/

private Map<DiagramHandler,BubletEditor> diagram_set;
private BubletEditor cur_editor;
 



/********************************************************************************/
/*                                                                              */
/*      Constructors                                                            */
/*                                                                              */
/********************************************************************************/

BubletGUI(CanCloseProgram m)
{
   super(m);
   diagram_set = new HashMap<>();
   cur_editor = null;
   
   BudaRoot.addBubbleViewCallback(new ViewCallback());
}



/********************************************************************************/
/*                                                                              */
/*      Update methods                                                          */
/*                                                                              */
/********************************************************************************/

@Override public void updateDiagramName(DiagramHandler diagram,String name)
{
   BubletEditor ed = diagram_set.get(diagram);
   if (ed != null) ed.diagramNameChanged(name);
}



@Override public void setDiagramChanged(DiagramHandler diagram,boolean changed)
{
   BubletEditor editor = diagram_set.get(diagram);
   if (editor != null) editor.noteDirtyChanged(changed);
}


@Override public void setCustomElementChanged(CustomElementHandler arg0,boolean arg1)
{
   // nothing to do
}


/********************************************************************************/
/*                                                                              */
/*      Window managmeent                                                       *//*                                                                              */
/********************************************************************************/

@Override protected void init()
{ }


@Override public void closeWindow()
{
   if (cur_editor != null) {
      DiagramHandler dh = cur_editor.getDiagramHandler();
      if (dh.askSaveIfDirty()) {
         // do nothing
       }
    }
}


@Override public void showPalette(String p)
{
   super.showPalette(p);
   
   if (cur_editor != null) {
      BoardLog.logD("BUBLET","Show pallette " + p);
      SwingUtilities.invokeLater(new ChangePalette(cur_editor,p));
      // cur_editor.showPalette(p);
    }
   else {
      BoardLog.logE("BUBLET","Show palette without editor");
    }
}


private static class ChangePalette implements Runnable {
   
   private BubletEditor for_editor;
   private String palette_name;
   
   ChangePalette(BubletEditor be,String nm) {
      for_editor = be;
      palette_name = nm;
    }
   
   @Override public void run() {
      for_editor.showPalette(palette_name);
    }
   
}       // end of inner class ChangePalette


@Override public String getSelectedPalette()
{
   if (cur_editor != null) {
      return cur_editor.getSelectedPaletteName();
    }
   
   return null;
}


@Override public void close(DiagramHandler dh)
{
   diagram_set.remove(dh);
}


@Override public void open(DiagramHandler diagram)
{
   cur_editor = new BubletEditor(diagram);
}


BubletEditor openDiagram(DiagramHandler dh)
{
   return new BubletEditor(dh);
}


/********************************************************************************/
/*                                                                              */
/*      Abstract Method Implementations                                         */
/*                                                                              */
/********************************************************************************/

@Override public void requestFocus()
{
  // do nothing
}






@Override public OwnSyntaxPane getPropertyPane()
{
   if (cur_editor != null) return cur_editor.getPropertyPane();
   return null;
}






@Override public Frame getMainFrame()
{
   if (cur_editor == null) return null;
   for (Component c = cur_editor; c != null; c = c.getParent()) {
      if (c instanceof Frame) return (Frame) c;
    }
   return null;
}







@Override public int getMailSplitPosition()
{
   return Config.getInstance().getMail_split_position();
}






@Override public void setValueOfZoomDisplay(int i)
{
   // do nothing for now
}



@Override public void setCursor(Cursor cursor)
{
   if (cur_editor != null) cur_editor.setCursor(cursor);
}






@Override public void setCustomPanelEnabled(boolean arg0)
{
   // do nothing
}






@Override public void diagramSelected(DiagramHandler arg0)
{
   // default would make menus visible -- we only have popups, so ignore
}






@Override public boolean saveWindowSizeInConfig()
{
   return false;
}






@Override public CustomElementHandler getCurrentCustomHandler()
{
   if (cur_editor != null) return cur_editor.getCustomElementHandler();
   
   return null;
}






@Override public void setCustomElementSelected(boolean sel)
{
   // nothing needs to be done here?
}






@Override public void jumpTo(DiagramHandler arg0)
{
   // not called
}






@Override public boolean hasExtendedContextMenu()
{
   return false;
}









@Override public void enablePasteMenuEntry()
{
   // enable paste in the pop up menu?
}






@Override public DrawPanel getCurrentDiagram()
{
   if (cur_editor != null) return cur_editor.getDiagram();

   return null;
}









@Override public void setMailPanelEnabled(boolean fg)
{
   if (cur_editor != null) {
      cur_editor.setMailPanelEnabled(fg);
    }
}







@Override public boolean isMailPanelVisible()
{
   if (cur_editor != null) {
      return cur_editor.isMailPanelVisible();
    }

   return false;
}






@Override public int getMainSplitPosition()
{
   // what should this do

   return 0;
}






@Override public int getRightSplitPosition()
{
   // what should this do

   return 0;
}






@Override public void focusPropertyPane()
{
   if (cur_editor != null) {
      cur_editor.focusPropertyPane();
    }
}



@Override public void afterSaving()
{ }


/********************************************************************************/
/*                                                                              */
/*      View callback to track current UML bubble                               */
/*                                                                              */
/********************************************************************************/

private class ViewCallback implements BubbleViewCallback {

   @Override public void doneConfiguration()                    { }
   @Override public void bubbleAdded(BudaBubble bb)             { }
   @Override public void bubbleRemoved(BudaBubble bb)           { }
   @Override public boolean bubbleActionDone(BudaBubble bb)     { return false; }
   @Override public void workingSetAdded(BudaWorkingSet ws)     { }
   @Override public void workingSetRemoved(BudaWorkingSet ws)   { }
   @Override public void copyFromTo(BudaBubble f,BudaBubble t)  { }
   
   @Override public void focusChanged(BudaBubble bb,boolean set) {
      if (bb instanceof BubletEditor) {
         BubletEditor be = (BubletEditor) bb;
         if (set) cur_editor = be;
         // else if (cur_editor == be) cur_editor = null;
         BoardLog.logD("BUBLET","Editor focus " + set + " " +  be.getDiagramHandler());
       } 
    }
      
}       // end of inner class ViewCallback


}       // end of class BubletGUI




/* end of BubletGUI.java */

