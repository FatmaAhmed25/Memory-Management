
public class Partition {

	String name;
	double size;
	Process process;
	boolean isAvailable;
	static int counter=0;
	
	public Partition(String name, double size) {
		super();
		this.name = name;
		this.size = size;
		this.isAvailable=true;
		counter++;
	}
	public Partition(double size) {
		super();
		this.name = "Partition "+counter;
		this.size = size;
		this.isAvailable=true;
		counter++;
	}
	public void setProcess(Process p)
	{
		this.process=p;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean available) {
		this.isAvailable = available;
	}
	public String toString()
	{
		String s=this.name +"("+ this.size+" KB"+")";
		if(this.process==null)
		{
			s+=" => External Fragment";
			
		}
		else s+=" => "+process.name;
		return s;
	}
}
