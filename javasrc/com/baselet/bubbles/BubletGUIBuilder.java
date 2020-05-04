/********************************************************************************/
/*                                                                              */
/*              BubletGUIBuilder.java                                           */
/*                                                                              */
/*      GUI builder implementation for bubbles                                  */
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

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;

import com.baselet.control.Main;
import com.baselet.control.config.Config;
import com.baselet.control.constants.Constants;
import com.baselet.diagram.DiagramHandler;
import com.baselet.diagram.PaletteHandler;
import com.baselet.gui.BaseGUIBuilder;
import com.baselet.gui.filedrop.FileDrop;
import com.baselet.gui.filedrop.FileDropListener;
import com.baselet.gui.listener.DividerListener;
import com.baselet.gui.listener.GUIListener;
import com.baselet.standalone.gui.SearchKeyListener;
import com.baselet.standalone.gui.ZoomListener;

import edu.brown.cs.ivy.swing.SwingGridPanel;

class BubletGUIBuilder extends BaseGUIBuilder implements BubletConstants
{


/********************************************************************************/
/*                                                                              */
/*      Private Storage                                                         */
/*                                                                              */
/********************************************************************************/

private JPanel        content_place_holder; 
private JComboBox<String> zoom_combo_box;
private ZoomListener zoom_listener;
private JToggleButton mail_button;
private JComponent    main_window;
private JLabel        title_label;
private CardLayout      palette_panel_layout;


/********************************************************************************/
/*                                                                              */
/*      Constructors                                                            */
/*                                                                              */
/********************************************************************************/

BubletGUIBuilder(DiagramHandler dh)  
{
   content_place_holder = new JPanel(new BorderLayout());
   
   setup(dh);
}



/********************************************************************************/
/*                                                                              */
/*      Access methods                                                          */
/*                                                                              */
/********************************************************************************/

JComponent getMainFrame()
{
   return main_window;
}


void setTitle(String ttl)
{
   title_label.setText(ttl);
}



/********************************************************************************/
/*                                                                              */
/*      Setup methods                                                           */
/*                                                                              */
/********************************************************************************/

public Panel initBubletGui()
{
   Panel emb = new Panel();
   emb.setLayout(new BorderLayout());
   emb.add(initBase(content_place_holder,Config.getInstance().getMain_split_position()));
   emb.addKeyListener(new GUIListener());
   
   return emb;
}



private void setup(DiagramHandler dh)
{
   main_window = new JPanel();
   main_window.setLayout(new GridLayout(1, 1)); 
   main_window.addKeyListener(new GUIListener());
   main_window.addKeyListener(new SearchKeyListener());
   
   JComponent dp = dh.getDrawPanel().getScrollPane(); 
   SwingGridPanel dpanel = new SwingGridPanel();
   new FileDrop(dpanel, new FileDropListener()); // enable drag&drop from desktop into diagrampanel
   title_label = new JLabel("NAME GOES HERE");
   dpanel.addGBComponent(title_label,0,0,0,1,1,0);
   dpanel.addGBComponent(new JSeparator(),0,1,0,1,1,0);
   dpanel.addGBComponent(dp,0,2,0,1,1,1);
   
   int mainDividerLoc = Math.min(main_window.getSize().width - Constants.MIN_MAIN_SPLITPANEL_SIZE, Config.getInstance().getMain_split_position());
   JSplitPane baseSplitPane = initBase(dpanel, mainDividerLoc);
   main_window.add(baseSplitPane);
   
   main_window.setBounds(Config.getInstance().getProgram_location().x, Config.getInstance().getProgram_location().y, Config.getInstance().getProgram_size().width, Config.getInstance().getProgram_size().height);
   main_window.setVisible(true);
}

 








public JPanel createZoomPanel() 
{
   createZoomComboBox();
   
   JPanel zoompanel = new JPanel();
   zoompanel.setOpaque(false);
   zoompanel.setLayout(new BoxLayout(zoompanel, BoxLayout.X_AXIS));
   zoompanel.add(new JLabel("Zoom:   "));
   zoompanel.add(zoom_combo_box);
   zoompanel.add(Box.createRigidArea(new Dimension(20, 0)));
   return zoompanel;
}


private void createZoomComboBox() 
{
   zoom_combo_box = new JComboBox<>();
   zoom_combo_box.setPreferredSize(new Dimension(80, 24));
   zoom_combo_box.setMinimumSize(zoom_combo_box.getPreferredSize());
   zoom_combo_box.setMaximumSize(zoom_combo_box.getPreferredSize());
   zoom_listener = new ZoomListener();
   zoom_combo_box.addActionListener(zoom_listener);
   zoom_combo_box.addMouseWheelListener(zoom_listener);
   zoom_combo_box.setToolTipText("Use ?? or mouse wheel to zoom");
   
   String[] zoomValues = Constants.zoomValueList.toArray(new String[Constants.zoomValueList.size()]);
   zoom_combo_box.setModel(new DefaultComboBoxModel<String>(zoomValues));
   zoom_combo_box.setSelectedIndex(9);
}



public JToggleButton createMailButton()
{
   mail_button = new JToggleButton("Mail diagram");
   mail_button.addActionListener(new ActionListener() {
      @Override
         public void actionPerformed(ActionEvent e) {
         setMailPanelEnabled(!getMailPanel().isVisible());
       }
    });
   return mail_button;
}




/********************************************************************************/
/*                                                                              */
/*      Implementation methods                                                  */
/*                                                                              */
/********************************************************************************/

void setContent(JScrollPane sp)
{
   content_place_holder.removeAll();
   content_place_holder.add(sp);
}


@Override public JPanel newPalettePanel()
{
   palette_panel_layout = new CardLayout();
   JPanel palettepanel = new JPanel(palette_panel_layout);
   palettepanel.addComponentListener(new DividerListener()); // Adding the DividerListener which refreshes Scrollbars here is enough for all dividers
   for (PaletteHandler palette : Main.getInstance().getPalettes().values()) {
      File f = new File(palette.getFullPathName());
      PaletteHandler ph = new PaletteHandler(f);
      palettepanel.add(ph.getDrawPanel().getScrollPane(), ph.getName());
    }
   return palettepanel;
}


@Override public void setPaletteActive(String name)
{
   palette_panel_layout.show(getPalettePanel(),name);
}




}       // end of class BubletGUIBuilder




/* end of BubletGUIBuilder.java */

