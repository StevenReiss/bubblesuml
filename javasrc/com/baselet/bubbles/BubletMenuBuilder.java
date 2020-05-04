/********************************************************************************/
/*                                                                              */
/*              BubletMenuBuilder.java                                          */
/*                                                                              */
/*      Menu builder for bubbles                                                */
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

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;

import com.baselet.control.constants.MenuConstants;
import com.baselet.diagram.CustomPreviewHandler;
import com.baselet.diagram.DiagramHandler;
import com.baselet.diagram.PaletteHandler;
import com.baselet.element.interfaces.GridElement;
import com.baselet.gui.menu.MenuFactorySwing;
import com.baselet.standalone.gui.MenuBuilder;


class BubletMenuBuilder extends MenuBuilder implements BubletConstants
{


/********************************************************************************/
/*                                                                              */
/*      Private Storage                                                         */
/*                                                                              */
/********************************************************************************/

private MenuFactorySwing menu_factory;
private JMenu edit_menu;
private JMenuItem edit_undo;
private JMenuItem edit_redo;
private JMenuItem edit_delete;
private JMenuItem edit_select_all;
private JMenuItem edit_group;
private JMenuItem edit_ungroup;
private JMenuItem edit_cut;
private JMenuItem edit_copy;
private JMenuItem edit_paste;
private JMenuItem custom_new;
private JMenu custom_new_from_template;
private JMenuItem custom_edit;
private JToggleButton mail_button;



/********************************************************************************/
/*                                                                              */
/*      Constructors                                                            */
/*                                                                              */
/********************************************************************************/

BubletMenuBuilder()
{
   menu_factory = MenuFactorySwing.getInstance();
}




/********************************************************************************/
/*                                                                              */
/*      Create menu                                                             */
/*                                                                              */
/********************************************************************************/

JPopupMenu createPopupMenu(BubletEditor be,MouseEvent me)
{
   JPopupMenu m = be.getContextMenu(me);
   if (m == null) m = new JPopupMenu();
   else m.addSeparator();
   
   
   JMenu filemenu = new JMenu(MenuConstants.FILE);
   // filemenu.setMnemonic(KeyEvent.VK_F);
   filemenu.add(menu_factory.createGenerate());
   filemenu.add(menu_factory.createGenerateOptions());
   filemenu.addSeparator();
   filemenu.add(menu_factory.createSave());
   filemenu.add(menu_factory.createSaveAs());
   filemenu.add(menu_factory.createExport());
   filemenu.add(menu_factory.createExportAs());
   filemenu.add(menu_factory.createMailTo());
   filemenu.addSeparator();
   filemenu.add(menu_factory.createPrint());
   m.add(filemenu);
   
   edit_menu = new JMenu(MenuConstants.EDIT);
   // edit_menu.setMnemonic(KeyEvent.VK_E);
   edit_menu.add(edit_undo = menu_factory.createUndo());
   edit_menu.add(edit_redo = menu_factory.createRedo());
   edit_menu.add(edit_delete = menu_factory.createDelete());
   edit_menu.addSeparator();
   edit_menu.add(edit_select_all = menu_factory.createSelectAll());
   edit_menu.add(edit_group = menu_factory.createGroup());
   edit_menu.add(edit_ungroup = menu_factory.createUngroup());
   edit_menu.addSeparator();
   edit_menu.add(edit_copy = menu_factory.createCopy());
   edit_menu.add(edit_cut = menu_factory.createCut());
   edit_menu.add(edit_paste = menu_factory.createPaste());
   m.add(edit_menu);
   
   // add search button here -- get input in text field
   //    add new SearchListener() on that text field (KeyListener)
   // 
   
   edit_delete.setEnabled(false);
   edit_group.setEnabled(false);
   edit_cut.setEnabled(false);
   edit_paste.setEnabled(false);
   edit_ungroup.setEnabled(false);
  
   JMenu helpmenu = new JMenu(MenuConstants.HELP);
   // helpmenu.setMnemonic(KeyEvent.VK_H);
   helpmenu.add(menu_factory.createOnlineHelp());
   helpmenu.add(menu_factory.createOnlineSampleDiagrams());
   helpmenu.add(menu_factory.createVideoTutorials());
   helpmenu.addSeparator();
   helpmenu.add(menu_factory.createProgramHomepage());
   helpmenu.add(menu_factory.createRateProgram());
   helpmenu.addSeparator();
   helpmenu.add(menu_factory.createAboutProgram());
   m.add(helpmenu);
   
   m.addSeparator();
   m.add(be.getFloatBubbleAction());
   
   return m;
}



public void elementsSelected(Collection<GridElement> selectedElements) 
{
   if (selectedElements.isEmpty()) {
      edit_delete.setEnabled(false);
      edit_group.setEnabled(false);
      edit_cut.setEnabled(false);
      // menu_edit_copy must remain enabled even if no entity is selected to allow the export of the full diagram to the system clipboard.
    }
   else {
      edit_delete.setEnabled(true);
      edit_cut.setEnabled(true);
      
      boolean allElementsInGroup = true;
      for (GridElement e : selectedElements) {
         if (e.getGroup() == null) {
            allElementsInGroup = false;
          }
       }
      edit_ungroup.setEnabled(allElementsInGroup);
      edit_group.setEnabled(!allElementsInGroup && selectedElements.size() > 1);
    }
}



public void enablePasteMenuEntry() 
{
   edit_paste.setEnabled(true);
}



public void setNewCustomElementMenuItemsEnabled(boolean enable) 
{
   custom_new.setEnabled(enable);
   custom_new_from_template.setEnabled(enable);
}



public void setEditCustomElementMenuItemEnabled(boolean enabled) 
{
   if (custom_edit != null) {
      custom_edit.setEnabled(enabled);
    }
}

public void setCustomElementEditMenuEnabled(boolean enabled) 
{
   edit_group.setEnabled(enabled);
   edit_ungroup.setEnabled(enabled);
   edit_delete.setEnabled(enabled);
   edit_cut.setEnabled(enabled);
   edit_paste.setEnabled(enabled);
   edit_copy.setEnabled(enabled);
   edit_select_all.setEnabled(enabled);
}



public void updateGrayedOutMenuItems(DiagramHandler handler) 
{
   // These menuitems only get changed if this is not the palette or custompreview
   if (!(handler instanceof PaletteHandler) && !(handler instanceof CustomPreviewHandler)) {
      menu_factory.updateDiagramDependendComponents();
      
      if (handler == null || handler.getDrawPanel().getGridElements().isEmpty()) {
         mail_button.setEnabled(false);
       }
      else {
         mail_button.setEnabled(true);
       }
    }
   
   // The menu_edit menuitems always work with the actual selected diagram (diagram, palette or custompreview), therefore we change it everytime
   if (handler == null || handler.getDrawPanel().getGridElements().isEmpty()) {
      edit_copy.setEnabled(false);
      edit_select_all.setEnabled(false);
    }
   else if (handler instanceof CustomPreviewHandler) {
      setCustomElementEditMenuEnabled(false);
    }
   else {
      edit_menu.setEnabled(true); // must be set to enabled explicitely because it could be deactivated from CustomPreview
      setCustomElementEditMenuEnabled(true);
    }
   
   if (handler == null || !handler.getController().isUndoable()) {
      edit_undo.setEnabled(false);
    }
   else {
      edit_undo.setEnabled(true);
    }
   if (handler == null || !handler.getController().isRedoable()) {
      edit_redo.setEnabled(false);
    }
   else {
      edit_redo.setEnabled(true);
    }
}



}       // end of class BubletMenuBuilder




/* end of BubletMenuBuilder.java */

