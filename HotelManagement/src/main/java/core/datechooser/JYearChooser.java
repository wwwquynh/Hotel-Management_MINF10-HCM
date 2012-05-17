/*
 *  JYearChooser.java  - A bean for choosing a year
 *  Copyright (C) 2004 Kai Toedter
 *  kai@toedter.com
 *  www.toedter.com
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package core.datechooser;
import java.util.*;
import javax.swing.*;

/**
 * JYearChooser is a bean for choosing a year.
 *
 * @version 1.1 02/04/02
 * @author  Kai Toedter
 */
public class JYearChooser extends JSpinField {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Default JCalendar constructor.
     */
    public JYearChooser() {
        Calendar calendar = Calendar.getInstance();
        dayChooser = null;
        setMinimum(calendar.getMinimum(Calendar.YEAR));
        setMaximum(calendar.getMaximum(Calendar.YEAR));
        setValue(calendar.get(Calendar.YEAR));
    }

    protected void setValue(int newValue, boolean updateTextField,
            boolean updateScrollbar) {
        int oldYear = year;
        year = newValue;
        super.setValue(newValue, updateTextField, updateScrollbar);
        if (dayChooser != null)
            dayChooser.setYear(newValue);
        firePropertyChange("year", oldYear, year);
    }

    /**
     * Sets the year.
     * This is a bound property.
     *
     * @see #getYear
     * @param y the new year
     */
    public void setYear(int y) {
        super.setValue(y);
    }

    /**
     * Returns the year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Convenience method set a day chooser.
     *
     * @param dayChooser the day chooser
     */
    public void setDayChooser(JDayChooser dayChooser) {
        this.dayChooser = dayChooser;
    }

    /**
     * Creates a JFrame with a JYearChooser inside and can be used for testing.
     */
    static public void main(String[] s) {
        JFrame frame = new JFrame("JYearChooser");
        frame.getContentPane().add(new JYearChooser());
        frame.pack();
        frame.setVisible(true);
    }

    private JDayChooser dayChooser;
    private int year;
}

