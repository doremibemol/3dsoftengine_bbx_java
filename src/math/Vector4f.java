package math;

public class Vector4f
{
	private final float x;
	private final float y;
	private final float z;
	private final float w;
	
	public Vector4f(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public float Length()
	{
		return (float)Math.sqrt(x * x + y * y + z * z + w * w);
	}
	
	

	public float Max()
	{
		return Math.max(Math.max(this.x, this.y), Math.max(this.z, this.w));
	}

	public float Dot(Vector4f r)
	{
		return this.x * r.GetX() + this.y * r.GetY() + this.z * r.GetZ() + this.w * r.GetW();
	}

	public Vector4f Cross(Vector4f r)
	{
		float x_ = this.y * r.GetZ() - this.z * r.GetY();
		float y_ = this.z * r.GetX() - this.x * r.GetZ();
		float z_ = this.x * r.GetY() - this.y * r.GetX();

		return new Vector4f(x_, y_, z_, 0);
	}

	public Vector4f Normalized()
	{
		float length = Length();

		return new Vector4f(this.x / length, this.y / length, this.z / length, this.w / length);
	}

	public Vector4f Rotate(Vector4f axis, float angle)
	{
		float sinAngle = (float)Math.sin(-angle);
		float cosAngle = (float)Math.cos(-angle);

		return this.Cross(axis.Mul(sinAngle)).Add(           //Rotation X
				(this.Mul(cosAngle)).Add(                     //Rotation Z
						axis.Mul(this.Dot(axis.Mul(1 - cosAngle))))); //Rotation Y
	}

	public Vector4f Lerp(Vector4f dest, float lerpFactor)
	{
		return dest.Sub(this).Mul(lerpFactor).Add(this);
	}

	public Vector4f Add(Vector4f r)
	{
		return new Vector4f(this.x + r.GetX(), this.y + r.GetY(), this.z + r.GetZ(), this.w + r.GetW());
	}

	public Vector4f Add(float r)
	{
		return new Vector4f(this.x + r, this.y + r, this.z + r, this.w + r);
	}

	public Vector4f Sub(Vector4f r)
	{
		return new Vector4f(this.x - r.GetX(), this.y - r.GetY(), this.z - r.GetZ(), this.w - r.GetW());
	}

	public Vector4f Sub(float r)
	{
		return new Vector4f(this.x - r, this.y - r, this.z - r, this.w - r);
	}

	public Vector4f Mul(Vector4f r)
	{
		return new Vector4f(this.x * r.GetX(), this.y * r.GetY(), this.z * r.GetZ(), this.w * r.GetW());
	}

	public Vector4f Mul(float r)
	{
		return new Vector4f(this.x * r, this.y * r, this.z * r, this.w * r);
	}

	public Vector4f Div(Vector4f r)
	{
		return new Vector4f(this.x / r.GetX(), this.y / r.GetY(), this.z / r.GetZ(), this.w / r.GetW());
	}

	public Vector4f Div(float r)
	{
		return new Vector4f(this.x / r, this.y / r, this.z / r, this.w / r);
	}

	public Vector4f Abs()
	{
		return new Vector4f(Math.abs(x), Math.abs(y), Math.abs(z), Math.abs(w));
	}

	public String toString()
	{
		return "(" + x + ", " + y + ", " + z + ", " + w + ")";
	}

	public float GetX()
	{
		return this.x;
	}

	public float GetY()
	{
		return this.y;
	}

	public float GetZ()
	{
		return this.z;
	}

	public float GetW()
	{
		return this.w;
	}

	public boolean equals(Vector4f r)
	{
		return this.x == r.GetX() && this.y == r.GetY() && this.z == r.GetZ() && this.w == r.GetW();
	}
}