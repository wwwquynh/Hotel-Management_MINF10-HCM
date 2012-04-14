package datechooser;
/*
* 02/02/2002 - 20:54:54
*
* LocaleEditor.java - a locale editor for JavaBeans
* Copyright (C) 2002 Kai Toedter
* kai@toedter.com
* www.toedter.com
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
*/



import java.util.*;

/**
 * Property editor for locales.
 *
 * @version 1.1 02/04/02
 * @author  Kai Toedter
 */
public class LocaleEditor extends java.beans.PropertyEditorSupport {
    /**
     * Default LocaleEditor constructor.
     */
    public LocaleEditor() {
        locale = Locale.getDefault();
        locales = Calendar.getInstance().getAvailableLocales();
        length = locales.length;
        localeStrings = new String[length];
    }

    /**
     * Returns the locale Strings.
     */
    public String[] getTags() {
        for (int i = 0; i < length; i++)
            localeStrings[i] = locales[i].getDisplayName();
        return localeStrings;
    }

    /**
     * Sets the locale Strings as text and invokes setValue( locale ).
     */
    public void setAsText(String text) throws IllegalArgumentException {
        for (int i = 0; i < length; i++)
            if (text.equals(locales[i].getDisplayName())) {
                locale = locales[i];
                setValue(locale);
                break;
            }
    }

    /**
     * Returnss the locale String as text.
     */
    public String getAsText() {
        return locale.getDisplayName();
    }

    private Locale[] locales;
    private String[] localeStrings;
    private Locale locale;
    private int length;
}


