// Elizabeth Koshelev
// COSI 12B, Spring 2015 
// Programming Assignment #1, 1/29/16
// This program mimics the TeacherDate class and can check if a day is a leap year, give the date, format any date, and check if two dates are equal.
public class Date{
	
	
	 int year;
	 int month;
	 int day;
	 
	 
	 public Date(int x, int y, int z){ //This creates the Date object.
		 year = x;
		 month = y;
		 day=z;
	 }
	 
	 public Date(){ //This creates the current date using methods below.
		 this.year=getYearToday();
		 this.month=getMonthToday();
		 this.day=getDayToday();
	 }
	 
	 public int getYear(){ //This returns the year.
		 return year;
	 }
	 
	 public int getDay(){ //This returns the day.
		 return day;
	 }
	 
	 public int getMonth(){ //This returns the month.
		 return month;
	 }
	 
	public int getYearToday(){ //This finds the current year by looping the days in the years since 1970 until it is greater than the days since the Epoch.
		int days = TeacherDate.getDaysSinceEpoch();
		int daycount=days;
		int i = 1970;
		while(days/daycount != 0){
		daycount = findYearsDays(1968,i);
		i++;
		}
		return i;
	}
	
	public int getMonthToday(){ //This follows the same idea as the method above, for the month.
		int year = getYear();
		int daysminusyears = TeacherDate.getDaysSinceEpoch()-findYearsDays(1970, year);
		int daycount= daysminusyears;
		int i;
		for (i=0; i<=daysminusyears/daycount; i++){
			daycount= findMonthDays(i,year);
		}
		return i;
	}
	
	public int getDayToday(){ //This follows the same idea as the method above, but for the day.
		int day = TeacherDate.getDaysSinceEpoch()-findYearsDays(1970, getYear())-findMonthDays(getMonth(), getYear())-1;
		return day;
	}

	public String getDayofWeek(){ //This finds the total days since 1601, and uses methods below to find the day of the week.
		int totaldays = totalMonthDays(day,month,year) + findYearsDays(1601, year);
		return findday(totaldays);
		
	}
		
	public static String findday(int c2) { //This method determines the day of the week by taking the total days mod 7.
	int dayofweek= c2%7;
		if (dayofweek==0) {
			return "Monday.";
		} else if (dayofweek==1) {
			return "Tuesday.";
		} else if (dayofweek==2) {
			return "Wednesday.";
		} else if (dayofweek==3) {
			return "Thursday.";
		} else if (dayofweek==4){
			return "Friday.";
		} else if (dayofweek==5) {
			return "Saturday.";
		} else {
			return "Sunday.";
	}
	}
	
	public boolean isLeapYear(){ //This calls the method checkLeapYear to determine if the given year is a leap year.
		return checkLeapYear(year);
	}
	
	public void nextDay(){ //This adds a day, taking into account all cases.
		if (month==12 && day==31){ //Last day of December.
			year=year+1;
			month=1;
			day=1;
		} else if (day==findMonthDays(month,year)){ //Last day of a month.
			day=1;
			month=month+1;
		} else{
			day= day+1;
		}
	}

	 public boolean equals(Object o){ //This method determines if two objects are equal.
		 if (o instanceof Date){
			Date other = (Date) o;
		return other.day==day && other.month==month&&other.year==year;
		 } else{
			 return false;
		 }			
	} 
	
	public String toString(){ //This converts a date to a string.
		return year + "/" + month + "/" + day;
	}

	public static int findYearsDays(int r3, int r4){ //This method determines the total number of years up to the date from a given year, including leap years.
		int counter = 0;
	for(int i = r3; i < r4; i++){
		if (checkLeapYear(i)==true){
			counter+=366;
			} else {
			counter+=365;
		}
	}
	return counter;
	}
	
	public static int totalMonthDays(int r1, int r2, int r3){ //This method determines the total number of days in the months up to the date given from Jan 1st.
		int counter = 0;
		for(int i = 1; i < r2; i++){
			counter+=findMonthDays(i, r3);
			}
			int totaldays= r1+ counter-1;
			return totaldays;
			}
			
	public static int findMonthDays(int x1, int y1){ //This method declares the number of days in each month, checking for a leapyear.
		if (x1==1 || x1==3 || x1==5 || x1==7 || x1==8 || x1==10 || x1==12){
			return 31; } else if (x1==4 || x1==6 || x1==9 || x1 == 11){
			return 30;
		} else {
			if (checkLeapYear(y1) == true ){
				return 29;
			} else {
				return 28;
			}
		}
	}
	
	public static boolean checkLeapYear(int x2){ //This method checks if it is a leap year by checking all cases.
		if (x2%400==0){
			return true;
		} else if (x2%100==0){
			return false;
		} else if (x2%4==0){
			return true;
		} else {
			return false;
		}
	}
}