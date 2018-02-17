String names [] = {"Sun" , "Mon" , "Tue" , "Wed" , "Thu" , "Fri" , "Sat"};
		Calendar calendar = new GregorianCalendar(2010 , 7 , 9); // 9 / 8 / 2010
		System.out.println(names[calendar.get(Calendar.DAY_OF_WEEK)]);