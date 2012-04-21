package datechooser;
import java.awt.Image;
import java.beans.PropertyEditorManager;
import java.beans.SimpleBeanInfo;
import java.util.Locale;

/**
 * A generic bean info class use by all bean infos of the JCalendar bean suite.
 * 
 * @author Kai Toedter
 * @version $LastChangedRevision: 85 $
 * @version $LastChangedDate: 2006-04-28 13:50:52 +0200 (Fr, 28 Apr 2006) $
 */
public class GenericBeanInfo extends SimpleBeanInfo {
	/** 16x16 color icon. */
	protected Image iconColor16;

	/** 32x32 color icon. */
	protected Image iconColor32;

	/** 16x16 mono icon. */
	protected Image iconMono16;

	/** 32x32 mono icon. */
	protected Image iconMono32;

	/**
	 * Constructs a new BeanInfo.
	 */
	public GenericBeanInfo(String bean, boolean registerLocaleEditor) {
		try {
			iconColor16 = loadImage("images/" + bean + "Color16.gif");
			iconColor32 = loadImage("images/" + bean + "Color32.gif");
			iconMono16 = loadImage("images/" + bean + "Mono16.gif");
			iconMono32 = loadImage("images/" + bean + "Mono32.gif");
		} catch (RuntimeException e) {
			System.out.println("GenericBeanInfo.GenericBeanInfo(): " + e);
		}
		
		if(registerLocaleEditor) {
			PropertyEditorManager.registerEditor(Locale.class,LocaleEditor.class);
		}
	}

	/**
	 * This method returns an image object that can be used to represent the
	 * bean in toolboxes, toolbars, etc.
	 * 
	 * @param iconKind
	 *            the kind of requested icon
	 * 
	 * @return the icon image
	 */
	public Image getIcon(int iconKind) {
		switch (iconKind) {
			case ICON_COLOR_16x16 :
				return iconColor16;

			case ICON_COLOR_32x32 :
				return iconColor32;

			case ICON_MONO_16x16 :
				return iconMono16;

			case ICON_MONO_32x32 :
				return iconMono32;
		}

		return null;
	}
}