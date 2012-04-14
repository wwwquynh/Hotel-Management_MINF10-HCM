package datechooser;
import java.util.*;
public class DateBean {
	Calendar calendar = null;

	public DateBean(Date d) {
		calendar = Calendar.getInstance();
		calendar.setTime(d);
	}

	public DateBean() {
		calendar = Calendar.getInstance();
	}

	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	public String getMonth() {
		int m = getMonthInt();
		String[] months = new String[] { "January", "February", "March",
				"April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		if (m > 12)
			return "Unknown to Man";

		return months[m - 1];

	}

	public String getDay() {
		int x = getDayOfWeek();
		String[] days = new String[] { "Sunday", "Monday", "Tuesday",
				"Wednesday", "Thursday", "Friday", "Saturday" };
		if (x > 7)
			return "Unknown to Man";

		return days[x - 1];
	}

	public int getMonthInt() {
		return 1 + calendar.get(Calendar.MONTH);
	}

	public String getDate() {
		return getMonthInt() + "/" + getDayOfMonth() + "/" + getYear();

	}

	public String getTime() {
		return getHour() + ":" + getMinute() + ":" + getSecond();
	}

	public int getDayOfMonth() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public int getDayOfYear() {
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	public int getWeekOfYear() {
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public int getWeekOfMonth() {
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	public int getDayOfWeek() {
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public int getHour() {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public int getMinute() {
		return calendar.get(Calendar.MINUTE);
	}

	public int getSecond() {
		return calendar.get(Calendar.SECOND);
	}

	public java.sql.Date getSQLDate() {
		java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());
		return sqlDate;
	}

	public static void main(String args[]) {
		DateBean jcal = new DateBean(new Date());
		p("Day of Month: " + jcal.getDayOfMonth());
		p("Year: " + jcal.getYear());
		p("Month: " + jcal.getMonth());
		p("Time: " + jcal.getTime());
		p("Date: " + jcal.getDate());
		p("Day: " + jcal.getDay());
		p("DayOfYear: " + jcal.getDayOfYear());
		p("WeekOfYear: " + jcal.getWeekOfYear());
		p("era: " + jcal.getEra());
		p("AMPM: " + jcal.getAMPM());
		p("DST: " + jcal.getDSTOffset());
		p("ZONE Offset: " + jcal.getZoneOffset());
		p("TIMEZONE: " + jcal.getUSTimeZone());
	}

	private static void p(String x) {
		System.out.println(x);
	}

	public int getEra() {
		return calendar.get(Calendar.ERA);
	}

	public String getUSTimeZone() {
		String[] zones = new String[] { "Hawaii", "Alaskan", "Pacific",
				"Mountain", "Central", "Eastern" };

		int i = getZoneOffset();
		if ((i > -11) && (i < -4))
			return zones[10 + getZoneOffset()];
		else
			return "Non-US timezone";
	}

	public int getZoneOffset() {
		return calendar.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000);
	}

	public int getDSTOffset() {
		return calendar.get(Calendar.DST_OFFSET) / (60 * 60 * 1000);
	}

	public int getAMPM() {
		return calendar.get(Calendar.AM_PM);
	}
}