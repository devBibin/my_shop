package pack;

import java.util.Date;

public class Reports {

private	
	String report;
	Date date;
	String author;
	
public Reports()
{
	
}

public Reports(String report, Date date, String author)
{
	this.report = report;
	this.date = date;
	this.author = author;
}

public int GetTimeDifference(Date date1, Date date2)
{
	return (int)((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
}

public Date getDate()
{
	return this.date;
}

public String getReport()
{
	return this.report;
}

public String getAuthor()
{
	return this.author;
}

public void setDate(Date date)
{
	this.date = date;
}

public void setReport(String report)
{
	this.report = report;
}


public void setAuthor(String author)
{
	this.author = author;
}
	
}
