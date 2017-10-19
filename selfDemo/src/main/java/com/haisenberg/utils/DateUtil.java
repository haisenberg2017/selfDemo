package com.haisenberg.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
/**
 * 注意，该项目下的long型时间全为10位数字，同mysql中unix_timestamp
 * 
 * 
 * */
public class DateUtil {
	public static final Logger LOG = Logger.getLogger(DateUtil.class);

	// 字符串转date
	public static Date StringtoDate(String str, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			LOG.error("时间格式不正确，请检查时间格式是否正确！");
		}
		return null;
	}

	// date转字符串
	public static String DatetoString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String str = sdf.format(date);
		return str;
	}

	// str转时间long
	public static long StrDatetoLong(String str, String pattern) {
		Date Date = StringtoDate(str, pattern);
		long times = Date.getTime() / 1000;
		return times;
	}

	// long转时间字符串
	public static String LongtoStrDate(long times, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = new Date(times * 1000);
		String str = sdf.format(date);
		return str;
	}
	
		// 格式化date转long
		public static long DatetoLong(Date date, String pattern) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String str = sdf.format(date);
			return StringtoDate(str, pattern).getTime()/1000;
		}
		
		//获取date日期的23:59:59的long时间
		public static long DayLastTimes(Date date, String pattern) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String str = sdf.format(date)+" 23:59:59";
			return StringtoDate(str, pattern).getTime()/1000;
		}
		
		//根据strDate获取上周一的StrDate
		public static String getLastWeekMonday(String StrDate){
			Date date = StringtoDate(StrDate, "yyyy-MM-dd");
			Date a = DateUtils.addDays(date, -1);    
            Calendar cal = Calendar.getInstance();    
            cal.setTime(a);    
            cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周    
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);    
            String datetoString = DatetoString(cal.getTime(), "yyyy-MM-dd");         
             return datetoString;    
		}
		
		//根据strDate获取上周日的StrDate
		public static String getLastWeekSunday(String StrDate){
			Date date = StringtoDate(StrDate, "yyyy-MM-dd");
			Date a = DateUtils.addDays(date, -1);    
            Calendar cal = Calendar.getInstance();    
            cal.setTime(a); 
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);    
            String datetoString = DatetoString(cal.getTime(), "yyyy-MM-dd");         
             return datetoString;    
		}
		
		
		//根据strDate获取所在周的周一的StrDate
		public static String getWeekMonday(String StrDate){        
             return getWeekMondayToSunday(StrDate,1);    
		}
		
		//根据strDate获取所在周的周日的StrDate
		public static String getWeekSunday(String StrDate){
			 return getWeekMondayToSunday(StrDate,7);
		}
		//根据strDate获取所在周的第几天的StrDate
		public static String getWeekMondayToSunday(String StrDate,int num){
			Date date = StringtoDate(StrDate, "yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();    
            cal.setTime(date);
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			if(num>1){
				 cal.add(Calendar.DATE, num-1);
			}
            String datetoString = DatetoString(cal.getTime(), "yyyy-MM-dd");         
             return datetoString;    
		}
		//根据strDate获取所在周的日期list
		public static List<String> getWeekList(String StrDate){
			List<String> weekList = new ArrayList<String>();
			for (int i = 1; i <=7 ; i++) {
				String day = getWeekMondayToSunday(StrDate,i);
				weekList.add(day);    
			}
			return weekList;
		}
		//根据strDate获取上个月的第一天的StrDate
		public static String getLastMonthFirstDay(String StrDate){
			Date date = StringtoDate(StrDate, "yyyy-MM-dd");
			Date a = DateUtils.addDays(date, -1);    
            Calendar cal = Calendar.getInstance();    
            cal.setTime(a);    
            cal.add(Calendar.MONTH, -1);//     
            cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
            String datetoString = DatetoString(cal.getTime(), "yyyy-MM-dd");         
             return datetoString;    
		}
		
		//根据strDate获取上个月的第一天的StrDate
		public static String getLastMonthLastDay(String StrDate){
			Date date = StringtoDate(StrDate, "yyyy-MM-dd");
			Date a = DateUtils.addDays(date, -1);    
            Calendar cal = Calendar.getInstance();    
            cal.setTime(a);     
            cal.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
            String datetoString = DatetoString(cal.getTime(), "yyyy-MM-dd");         
             return datetoString;    
		}
		
		public static void main(String [] args){
		/*	String lastWeekMonday = getLastWeekMonday("2017-05-11");
			String lastWeekSunday = getLastWeekSunday("2017-05-11");
			String lastMonthFirstDay = getLastMonthFirstDay("2017-05-11");
			String lastMonthLastDay = getLastMonthLastDay("2017-05-11");

			System.out.println("lastWeekMonday="+lastWeekMonday);
			System.out.println("lastWeekSunday="+lastWeekSunday);
			System.out.println("lastMonthFirstDay="+lastMonthFirstDay);
			System.out.println("lastMonthLastDay="+lastMonthLastDay);*/
			//String weekMonday = getWeekMonday("2017-05-28");
			//String weekSunday = getWeekSunday("2017-05-28");
			//System.out.println(weekMonday+","+weekSunday);
			 
			
			List<String> weekList = getWeekList("2017-05-27");
			System.out.println(weekList.toString());

		}
}
