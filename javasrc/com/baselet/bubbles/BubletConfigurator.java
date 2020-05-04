/********************************************************************************/
/*                                                                              */
/*              BubletConfigurator.java                                         */
/*                                                                              */
/*      Configurator to save and restore uml bubbles                            */
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

import java.io.File;

import org.w3c.dom.Element;

import com.baselet.diagram.DiagramHandler;

import edu.brown.cs.bubbles.buda.BudaBubble;
import edu.brown.cs.bubbles.buda.BudaBubbleArea;
import edu.brown.cs.bubbles.buda.BudaXmlWriter;
import edu.brown.cs.bubbles.buda.BudaConstants.BubbleConfigurator;
import edu.brown.cs.ivy.xml.IvyXml;

class BubletConfigurator implements BubbleConfigurator, BubletConstants
{



/********************************************************************************/
/*                                                                              */
/*      Bubble creation methods                                                         */
/*                                                                              */
/********************************************************************************/

@Override public BudaBubble createBubble(BudaBubbleArea bba,Element xml)
{
   String filename = getFilePath(xml);
   if (filename == null) return null;
   
   File f = new File(filename);
   if (!f.exists() || !f.canRead()) return null;
   
   BubletEditor be = BubletFactory.createEditorBubble(f);
   return be;
}


@Override public boolean matchBubble(BudaBubble bb,Element xml)
{
   if (bb instanceof BubletEditor) {
      BubletEditor be = (BubletEditor) bb;
      DiagramHandler dh = be.getDiagramHandler();
      String pn = dh.getFullPathName();
      if (pn.equals(getFilePath(xml))) return true;
    }
   
   return false;
}


@Override public void outputXml(BudaXmlWriter xw,boolean history)       { }
@Override public void loadXml(BudaBubbleArea bba,Element root)          { }   



/********************************************************************************/
/*                                                                              */
/*      Output metohds                                                          */
/*                                                                              */
/********************************************************************************/

static void outputXmlForEditor(BudaXmlWriter xw,BubletEditor be)
{
   xw.field("TYPE","BUBLET");
   xw.field("FILENAME",be.getDiagramHandler().getFullPathName());
}



/********************************************************************************/
/*                                                                              */
/*      Helper methods                                                          */
/*                                                                              */
/********************************************************************************/

private String getFilePath(Element xml)
{
   Element cnt = IvyXml.getChild(xml,"CONTENT");
   
   return IvyXml.getAttrString(cnt,"FILENAME");
}




}       // end of class BubletConfigurator




/* end of BubletConfigurator.java */

