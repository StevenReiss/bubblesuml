/********************************************************************************/
/*										*/
/*		BubletRepository.java						*/
/*										*/
/*	Track all uml files in all projects					*/
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

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.w3c.dom.Element;

import edu.brown.cs.bubbles.bass.BassName;
import edu.brown.cs.bubbles.bass.BassNameBase;
import edu.brown.cs.bubbles.bass.BassConstants.BassRepository;
import edu.brown.cs.bubbles.bass.BassConstants.BassUpdatableRepository;
import edu.brown.cs.bubbles.board.BoardFileSystemView;
import edu.brown.cs.bubbles.board.BoardThreadPool;
import edu.brown.cs.bubbles.buda.BudaBubble;
import edu.brown.cs.bubbles.bump.BumpClient;
import edu.brown.cs.ivy.xml.IvyXml;

class BubletRepository implements BassUpdatableRepository, BubletConstants
{


/********************************************************************************/
/*										*/
/*	Private Storage 							*/
/*										*/
/********************************************************************************/

private List<UmlFileName> uml_files;
private FileSystemView	  file_system;

private static final String	UML_SUFFIX = ".uxf";



/********************************************************************************/
/*										*/
/*	Constructors								*/
/*										*/
/********************************************************************************/

BubletRepository()
{
   file_system = BoardFileSystemView.getFileSystemView();
   uml_files = null;
   loadUmlFiles();
}



/********************************************************************************/
/*										*/
/*	Data loading methods							*/
/*										*/
/********************************************************************************/

private void loadUmlFiles()
{
   uml_files = new ArrayList<>();
   UmlFileLoader fl = new UmlFileLoader();
   BoardThreadPool.start(fl);
}


private void loadUmlFilesFrom(File dir,String pfx)
{
   if (dir.exists() && dir.canRead() && dir.isDirectory()) {
      String nm = dir.getName();
      if (pfx == null) pfx = nm;
      else pfx += "." + nm;
      for (File f : dir.listFiles(new UmlFileFilter())) {
	  loadUmlFilesFrom(f,pfx);
       }
    }
   else if (dir.exists() && dir.canRead() && dir.getName().endsWith(UML_SUFFIX)) {
      addFile(dir,pfx);
    }
}



private void addFile(File f,String pfx)
{
   String nm = f.getName();
   if (nm.endsWith(UML_SUFFIX)) {
      int idx = nm.lastIndexOf(".");
      nm = nm.substring(0,idx);
    }
   if (pfx != null && !pfx.endsWith(":")) nm = pfx + "." + nm;
   else if (pfx != null) nm = pfx + nm;
   uml_files.add(new UmlFileName(f,nm));
   System.err.println("BUBLET: Found uml file: " + f + " " + nm);
}


private static class UmlFileFilter implements FileFilter {

   @Override public boolean accept(File f) {
      if (f.isDirectory()) return true;
      if (f.getName().endsWith(UML_SUFFIX)) return true;
      return false;
    }
}


private class UmlFileLoader implements Runnable {

   UmlFileLoader() {
    }

   @Override public void run() {
      BumpClient bc = BumpClient.getBump();
      Element projs = bc.getAllProjects();
      for (Element pelt : IvyXml.children(projs,"PROJECT")) {
	 String pnm = IvyXml.getAttrString(pelt,"NAME");
	 Element pdata = bc.getProjectData(pnm,false,true,false,false,false);
	 Element cpdata = IvyXml.getChild(pdata,"CLASSPATH");
	 String pfx = pnm + ":";
	 for (Element pathd : IvyXml.children(cpdata,"PATH")) {
	    String typ = IvyXml.getAttrString(pathd,"TYPE");
	    if (typ.equals("SOURCE")) {
	       String dir = IvyXml.getTextElement(pathd,"SOURCE");
	       File fdir = file_system.createFileObject(dir);
	       File [] dirs = fdir.listFiles(new UmlFileFilter());
	       if (dirs != null) {
		  for (File f : dirs) {
		     loadUmlFilesFrom(f,pfx);
		   }
		}
	     }
	  }
       }
    }

}	// end of inner class UmlFileLoader




/********************************************************************************/
/*										*/
/*	Bass repository methods 						*/
/*										*/
/********************************************************************************/

@Override public Iterable<BassName> getAllNames()
{
   ArrayList<BassName> rslt = new ArrayList<>();
   rslt.addAll(uml_files);
   return rslt;
}


@Override public boolean isEmpty()
{
   return uml_files.isEmpty();
}



@Override public boolean includesRepository(BassRepository br)
{
   if (br == this) return true;
   return false;
}


@Override public void reloadRepository()
{ }



/********************************************************************************/
/*										*/
/*	UmlFileName -- holder for BassName for uml file 			*/
/*										*/
/********************************************************************************/

private static class UmlFileName extends BassNameBase {

   private String key_name;
   private String project_name;
   private String path_name;
   private File file_name;

   UmlFileName(File f,String p) {
      file_name = f;
      key_name = p;
      int idx = p.indexOf(":");
      project_name = p.substring(0,idx);
      String pnm = p.substring(idx+1);
      idx = pnm.lastIndexOf(".");
      if (pnm.endsWith(UML_SUFFIX)) {
	 pnm = pnm.substring(0,idx);
	 idx = pnm.lastIndexOf(".");
       }
      if (idx < 0) pnm = "UML DIAGRAMS." + pnm;
      else pnm = pnm.substring(0,idx) +  ".UML DIAGRAMS" + pnm.substring(idx);

      name_type = BassNameType.UML;
    }

   @Override public String getProject() 		{ return project_name; }
   @Override public String getSymbolName()		{ return path_name; }
   @Override public String getParameters()		{ return null; }
   @Override public String getKey()			{ return key_name; }

   @Override public BudaBubble createBubble() {
      return BubletFactory.createEditorBubble(file_name);
    }

}	// end of inner class UmlFileName





}	// end of class BubletRepository




/* end of BubletRepository.java */

