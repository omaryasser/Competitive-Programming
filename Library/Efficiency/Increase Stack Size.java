public static void main(String[] args) 
{
	new Thread(null, new Runnable() { 
			public void run() 
			{
				try 
				{
					sol();		//your actual main method
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
	}, "2",1<<26).start();		//stack size = 1<<26
}