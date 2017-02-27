// Elizabeth Koshelev
// COSI 12B, Spring 2015 
// Programming Assignment #2, 2/13/16
// This program returns the date, day of the week, number of days until the birthday, and days old of an individual. 
import java.io.*; 
import java.util.*; 
public class Birthday{
	public static void main(String[] args) {
	getData(); //This calls another method ot do everything needed for the problem.
	}
	
	public static void getData(){ //This allows the user to put in a date, and then calls a method to print all the data.
		int checkvalid = 0;
		while (checkvalid == 0){ //This checks for an invalid date.
			Scanner console= new Scanner(System.in);	
			System.out.println("Write the month, day, and year were you born:");	
			checkvalid=1;
			int month = console.nextInt();
			int day = console.nextInt();
			int year = console.nextInt();
			if (day > 31  || month > 12 || year < 1850){
				System.out.println("Oops! That date is invalid, try again.");
				checkvalid=0;
			} else{
				printBirthday(month,day,year); 
			}
		}
	}
	
	public static void printBirthday(int givemonth,int giveday, int giveyear){//This method prints the birthday information.
		int totaldays = totalMonthDays(giveday,givemonth,giveyear) + findYearsDays(1601, giveyear); //This finds the total days from a Monday in 1601.
		System.out.print("You were born " + giveyear + "/" + givemonth + "/" + giveday + ", which was a ");
			findday(totaldays); //This calls the method that determines the day of the week.}
			System.out.println();
				int counter=0;
				TeacherDate birthday = new TeacherDate(giveyear, givemonth, giveday);
				TeacherDate today = new TeacherDate(); //The information for the current day is called from TeacherDate.
				int daytoday = today.getDay();
				int monthtoday = today.getMonth();
				int yeartoday = today.getYear();
				int totaldays1; //This declares the integer before the if statement.
		if(((monthtoday == givemonth) && (daytoday <= giveday ))|| (monthtoday < givemonth)){ 
			totaldays1 = Math.abs(totalMonthDays(giveday,givemonth, yeartoday) - totalMonthDays(daytoday,monthtoday, yeartoday)) ; //This determines the case in which the current day is before the birthday.
		}else{
			totaldays1= 365-Math.abs(totalMonthDays(giveday,givemonth, yeartoday)-totalMonthDays(daytoday,monthtoday, yeartoday)); //This determines the case in which the current day is after the birthday. Leap years mess with this algorithm.
		}
		int daysold;
		System.out.println("It will be your birthday in " + totaldays1 + " days.");	
		if(((monthtoday == givemonth) && (daytoday <= giveday ))|| (monthtoday < givemonth)){ //If it is before the birthday, then...
			daysold= Math.abs(findYearsDays(giveyear,yeartoday) + totalMonthDays(daytoday,monthtoday, yeartoday) - totalMonthDays(giveday,givemonth,giveyear)); //Here is the algorithm for the total days old.
		}else{
			daysold= Math.abs(findYearsDays(giveyear,yeartoday) - totalMonthDays(daytoday,monthtoday, yeartoday) + totalMonthDays(giveday,givemonth,giveyear)); //Here is the algorithm for if the birthday is before the current day.
		}
			System.out.println("You are " + daysold + " days old."); //I later realized that I could have pushed the date of the current date to find days until the birthday using the TeacherClass.
	}
	
	public static void findday(int c2) { //This method determines the day of the week by taking the total days mod 7.
	int dayofweek= c2%7;
		if (dayofweek==0) {
			System.out.print("Monday.");
		} else if (dayofweek==1) {
			System.out.print("Tuesday.");
		} else if (dayofweek==2) {
			System.out.print("Wednesday.");
		} else if (dayofweek==3) {
			System.out.print("Thursday.");
		} else if (dayofweek==4){
			System.out.print("Friday.");
		} else if (dayofweek==5) {
			System.out.print("Saturday.");
		} else {
			System.out.print("Sunday.");
		}
	}
	
	public static int findMonthDays(int x1, int y1){ //This method declares the number of days in each month, checking for a leapyear.
		if (x1==1 || x1==3 || x1==5 || x1==7 || x1==8 || x1==10 || x1==12){
			return 31; }else if (x1==4 || x1==6 || x1==9 || x1 == 11){
			return 30;
		} else {
			if (checkLeapYear(y1) == true ){
				return 29;
			}
			else {
				return 28;
			}
		}
	}
	
	public static boolean checkLeapYear(int x2){ //This method checks if it is a leap year.
		if (x2%400==0){
			return true;
		}
		else if (x2%100==0){
			return false;
		}
		else if (x2%4==0){
			return true;
		}
		else {
			return false;
		}
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
}
