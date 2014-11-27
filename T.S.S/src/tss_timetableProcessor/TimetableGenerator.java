package tss_timetableProcessor;

import java.util.ArrayList;
import java.util.Scanner;

import tss_core.Course;

public class TimetableGenerator
{
	public static ArrayList<Timetable> generateTimetables(ArrayList<Course> courses,
			ArrayList<Filter> filters)
	{
		return null;
	}

	public static ArrayList<Timetable> generateAllPossibleTimetables(ArrayList<Course> courses)
	{
		System.out.println("here");
		ArrayList<Timetable> returnList = new ArrayList<Timetable>();
		ArrayList<ArrayList<Course>> coursePool = new ArrayList<ArrayList<Course>>();
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < courses.size(); ++i)
		{
			Course c = courses.get(i);
			String toCheck = c.getCode() + String.valueOf(c.getType().charAt(0));
			if (!temp.contains(toCheck))
				temp.add(toCheck);
		}
		for (int i = 0; i < temp.size(); ++i)
			coursePool.add(new ArrayList<Course>());
		System.out.println("here2");
		for (int i = 0; i < courses.size(); ++i)
		{
			Course c = courses.get(i);
			String toCheck = c.getCode() + String.valueOf(c.getType().charAt(0));
			coursePool.get(temp.indexOf(toCheck)).add(c);
		}
		ArrayList<Integer> eachPoolSize = new ArrayList<Integer>();
		for(int i = 0; i < coursePool.size(); ++ i)
			eachPoolSize.add(coursePool.get(i).size());
		
		ArrayList<String> allPossiblePermutes = getPermutes(coursePool.size(),eachPoolSize);
		for(int i = 0; i < allPossiblePermutes.size(); ++i)
			System.out.println(allPossiblePermutes.get(i));
		System.out.println("here3");
		for(int i = 0; i < allPossiblePermutes.size(); ++i)
		{
			
			Scanner scan = new Scanner(allPossiblePermutes.get(i));
			ArrayList<TimeSlot> slots = new ArrayList<TimeSlot>();
			for(int j = 0; j < coursePool.size(); ++ j)
			{
				slots.addAll(coursePool.get(j).get(scan.nextInt()).getTimeslot());
			}
			scan.close();
			int beginTime = slots.get(0).getBeginTime();
			int endTime = slots.get(0).getEndTime();
			for(int j = 1; j < slots.size(); ++j)
			{
				if(beginTime > slots.get(j).getEndTime())
					beginTime = slots.get(j).getEndTime();
				if(endTime < slots.get(j).getEndTime())
					endTime = slots.get(j).getEndTime();
			}
			Timetable t = new Timetable(8, endTime);
//			System.out.println(beginTime+" "+ endTime);
			for(int j = 0; j < slots.size(); ++ j)
			{
				t.addTimeSlot(slots.get(j), true);
			}
			returnList.add(t);
		}
		return returnList;
		
	}

	public static ArrayList<String> getPermutes(int amtPools, ArrayList<Integer> eachPoolSize)
	{
		ArrayList<String> returnList = new ArrayList<String>();
		if (eachPoolSize.size() >= 1)
		{
			for (int a = 0; a < eachPoolSize.get(0); ++a)
			{
				if (eachPoolSize.size() >= 2)
				{
					for (int b = 0; b < eachPoolSize.get(1); ++b)
					{
						if (eachPoolSize.size() >= 3)
						{
							for (int c = 0; c < eachPoolSize.get(2); ++c)
							{
								if (eachPoolSize.size() >= 4)
								{
									for (int d = 0; d < eachPoolSize.get(3); ++d)
									{
										if (eachPoolSize.size() >= 5)
										{
											for (int e = 0; e < eachPoolSize.get(4); ++e)
											{
												if (eachPoolSize.size() >= 6)
													for (int f = 0; f < eachPoolSize.get(5); ++f)
													{
														returnList.add(a + " " + b + " " + c + " "
																+ d + " " + e + " " + f);
													}
												else
													returnList.add(a + " " + b + " " + c + " " + d
															+ " " + e);
											}
										} else
											returnList.add(a + " " + b + " " + c + " " + d);
									}
								} else
									returnList.add(a + " " + b + " " + c);
							}
						} else
							returnList.add(a + " " + b);
					}
				} else
					returnList.add(String.valueOf(a));
			}
		}
		return returnList;
	}

}
