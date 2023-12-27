package image;

public class RGB {
	private byte a;
	private byte r;
	private byte g;
	private byte b;
	public byte getA() {
		return a;
	}
	public byte getR() {
		return r;
	}
	public byte getG() {
		return g;
	}
	public byte getB() {
		return b;
	}
	public void setA(byte a) {
		this.a = a;
	}
	public void setR(byte r) {
		this.r = r;
	}
	public void setG(byte g) {
		this.g = g;
	}
	public void setB(byte b) {
		this.b = b;
	}

	public RGB(byte a, byte r, byte g, byte b) {
		this.a = a;
		this.r = r;
		this.g = g;
		this.b = b;
	}

}
