	


	static ArrayList<Integer> adjList[];
	
//	To Compare two object :
//		convert them two a Canonical Form -- > Compare the two Canonical Forms
	
//	Our Canonical form for trees is parenthes
//	
//	For example : 
//		1
//	2		3
//	= ( () ()  )
//	
//			1
//		2			3
//	4 		5
//	= ( 	() (  () ()  ) 	)
//	
	

// NOTE !!! : I must start from the ROOT.
// IF there is NO root . apply it on the TREE CENTER.
// IF there is two centers in BOTH then we will need to get 4 canonical Forms (2 for each) & compare.
	public static String treeCanonicalForm (int i , int parent) 
	{
		ArrayList<String> children = new ArrayList<String>();
		
		for(int j : adjList[i])
			if(j != parent)
				children.add(treeCanonicalForm(j, i));
		
		Collections.sort(children);
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for(String x : children)
			sb.append(x);
		sb.append(")");
		
		return sb.toString();
	}