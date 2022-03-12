package com.ismo.brevets.ihm.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class DoubleDocument extends PlainDocument {

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

		try {
			String t = getText(0, getLength()) + str;
			Double.parseDouble(t);
			super.insertString(offs, str, a);
		} catch (Exception e) {
		}

	}

}
