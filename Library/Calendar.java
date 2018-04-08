String names [] = {"Sun" , "Mon" , "Tue" , "Wed" , "Thu" , "Fri" , "Sat"};
		Calendar calendar = new GregorianCalendar(2010 , 7 , 9); // 9 / 8 / 2010
		System.out.println(names[calendar.get(Calendar.DAY_OF_WEEK)]);

String yourDateString = "02/28/2012 15:00:00";
SimpleDateFormat yourDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
Date yourDate = yourDateFormat.parse(yourDateString);
String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(yourDate);
