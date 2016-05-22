package pack;

public class Good {
private
	int id;
	int order;
	String prod_name;
	String model;
	double price;
	double rating;
	double discount;
	String company;
	String path;
	int count;
	
public Good()
{
	this.order = 1;
}

public String get_name()
{
	return this.prod_name;
}

public void inc_order()
{
	this.order++;
}

public String get_model()
{
	return this.model;
}

public String get_company()
{
	return this.company;
}

public String get_path()
{
	return this.path;
}

public int get_count()
{
	return this.count;
}

public double get_price()
{
	return this.price;
}

public double get_rating()
{
	return this.rating;
}

public double get_discount()
{
	return this.discount;
}

public int get_id()
{
	return this.id;
}

public int get_order()
{
	return this.order;
}

public String set_name()
{
	return this.prod_name;
}

public void set_model(String model)
{
	 this.model = model;
}

public void set_company(String company)
{
	 this.company = company;
}

public void set_path(String path)
{
	 this.path = path;
}

public void set_count(int count)
{
	 this.count = count;
}

public void set_price(double price)
{
	 this.price = price;
}

public void set_rating(double rating)
{
	 this.rating = rating;
}

public void set_discount(double discount)
{
	 this.discount = discount;
}

public void set_id(int id)
{
	 this.id = id;
}

public void set_order(int order)
{
	 this.order = order;
}
	
	
public Good(int id, String prod_name, String model, double price, double discount, double rating, String company, String path, int count)
{
	this.order = 1;
	this.id = id;
	this.prod_name = prod_name;
	this.model = model;
	this.discount = discount;
	this.rating = rating;
	this.price = price;
	this.company = company;
	this.path = path;
	this.count = count;
}

public void set_all(int id, String prod_name, String model, double price, double discount, double rating, String company, String path, int count)
{
	this.order = 1;
	this.id = id;
	this.prod_name = prod_name;
	this.model = model;
	this.discount = discount;
	this.rating = rating;
	this.price = price;
	this.company = company;
	this.path = path;
	this.count = count;
}


@Override
public boolean equals(Object other) {
    if (!(other instanceof Good)) {
        return false;
    }

    Good that = (Good) other;

    // Custom equality check here.
    return (this.id == that.id);
}
}
