package pack;

public class Stores {
	private
	String subway;
	String adress;
	int count;
	
	public Stores(String sub, String adress, int count)
	{
		this.adress = adress;
		this.count = count;
		this.subway = sub;
	}
	
	public String get_adress()
	{
		return this.adress;
	}

	public String get_subway()
	{
		return this.subway;
	}

	public int get_count()
	{
		return this.count;
	}

}
