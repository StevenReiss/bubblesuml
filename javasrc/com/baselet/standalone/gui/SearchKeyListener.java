package com.baselet.standalone.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.baselet.gui.CurrentGui;

public class SearchKeyListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		boolean meta_f_pressed = e.getKeyCode() == KeyEvent.VK_F && 
            (e.getModifiersEx() & KeyEvent.META_DOWN_MASK) != 0;
		if (meta_f_pressed || e.getKeyChar() == '/') {
			CurrentGui.getInstance().getGui().enableSearch(true);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}
