package rs.ac.uns.ftn.siit.op.json.task1;

public class Magazine {
	private String Title;
	private String Month;
	private int Year;

	public Magazine() {
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	@Override
	public String toString() {
		return "Magazine [Title=" + Title + ", Month=" + Month + ", Year=" + Year + "]";
	}

}
