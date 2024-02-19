package rs.ac.uns.ftn.siit.op.csv.task1;

import com.opencsv.bean.CsvBindByPosition;

public class Point implements Comparable<Point>{
	// @CsvBindByName nema mogucnost specificiranja pozicije pri pisanju; moze se dogoditi da kolone ne budu uredjene u redosledu navodjenja polja 
	@CsvBindByPosition(position = 0)
	private double x;
	@CsvBindByPosition(position = 1)
	private double y;
	@CsvBindByPosition(position = 2)
	private double z;
	@CsvBindByPosition(position = 3, required = false)
	private double d;

	public Point() {
	}
	
	public void calculateDistance() {
		d = Math.sqrt(x*x + y*y + z*z);
	}
	

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", z=" + z + ", d=" + d + "]";
	}

	@Override
	public int compareTo(Point point) {
		if (this.d < point.getD()) return -1;
		if (this.d > point.getD()) return 1;
		return 0;
	}

}
