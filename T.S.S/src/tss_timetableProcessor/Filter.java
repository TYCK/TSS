package tss_timetableProcessor;

public class Filter
{
	private char filterType;
	private boolean monday,tuesday,wednesday,thursday,friday,saturday = false;
	private int noneBefore, noneAfter, noneBetween1, noneBetween2, limitGap;
	private boolean before,after,between,limit;
	
	public Filter(char filterType)
	{
		this.filterType = filterType;
	}
	
	public void setDays(boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday)
	{
		this.monday = monday;
		this.tuesday= tuesday;
		this.wednesday=wednesday;
		this.thursday = thursday;
		this.friday = friday;
	}
	
	public void setNoneBefore(boolean before, int hr)
	{
		this.before = before;
		this.noneBefore = hr;
	}
	
	public void setNoneAfter(boolean after, int hr)
	{
		this.after = after;
		this.noneAfter = hr;
	}
	
	public void setNoneBetween(boolean between, int hr1, int hr2)
	{
		this.between = between;
		this.noneBetween1 = hr1;
		this.noneBetween2 = hr2;
	}
	
	public void setLimit(boolean limit, int hr)
	{
		this.limit = limit;
		this.limitGap = hr;
	}
	
	public int getBefore()
	{
		if(before)
			return noneBefore;
		else
			return -1;
	}
	
	public int getAfter()
	{
		if(after)
			return noneAfter;
		else
			return -1;
	}
	
	public int[] getBeteween()
	{
		if(between)
		{
			int[] returnInts = {noneBetween1, noneBetween2};
			return returnInts;
		}
		else
			return null;
	}
	
	public int getGapLimit()
	{
		if(limit)
			return limitGap;
		else
			return -1;
	}
	
	public String toString()
	{
		String retString = "Time Filter - ";
		if(before)
			retString +="none before "+noneBefore;
		if(after)
			retString +="none after "+noneAfter;
		if(between)
			retString +="none between "+noneBetween1+" and "+noneBetween2;
		if(limit)
			retString +="less than "+limitGap+"hr intervals";
		retString += " on ";
		if(monday)
			retString +=" MON ";
		if(tuesday)
			retString +=" TUE ";
		if(wednesday)
			retString +=" WED ";
		if(thursday)
			retString +=" THU ";
		if(friday)
			retString +=" FRI ";
		if(saturday)
			retString +=" SAT ";
		return  retString;
	}
	
}
