import java.util.ArrayList;
import java.util.Scanner;



public class Main {
	
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter the number of partitions:");
		int noOfBlocks=scan.nextInt();
		ArrayList<Partition> partitions =new ArrayList<Partition>();
	    ArrayList<Partition> temp =new ArrayList<Partition>(); //temporary array 
		
		for(int i=0;i<noOfBlocks;i++)
		{
			System.out.println("Enter Partition " + (i+1) + "'s name: ");
			scan.nextLine();
			String namee=scan.nextLine();
			
			System.out.println("Enter Partition " + (i+1) + "'s size: ");
			int sizee=scan.nextInt();
		
				Partition p=new Partition(namee,sizee);
				partitions.add(p);
		}
		
		System.out.println();
		System.out.println("Enter the number of procceses");
		int num=scan.nextInt();
		ArrayList<Process> processList= new ArrayList <Process>();
		
		for(int i=0;i<num;i++)
		{
			System.out.println("Enter process " + (i+1) + "'s name: ");
			scan.nextLine();
			String name=scan.nextLine();	
			System.out.println("Enter process " + (i+1) + "'s size: ");
			int size=scan.nextInt();
			Process p=new Process(name,size);
			Process p2=new Process(name,size);
			Process p3=new Process(name,size);
			
			processList.add(p);
	
			
		}
		  Partition.counter=0;
			for(int i=0;i<partitions.size();i++)
			{   
				String name=partitions.get(i).name;
			    double size=partitions.get(i).size;
			    //Partition.counter=0;
			    Partition p=new Partition(name,size);
			   
				temp.add(p);
				
				
			}
		
		ArrayList<Process> unAdded= new ArrayList <Process>();

		String choice="";
		while (!choice.equals("4"))               // Main menu
		{
			System.out.println("* * * * * * * * * * * * * * * * * * ");
		
		System.out.println("Select the policy you want to apply:");
		System.out.println("1-First Fit");
		System.out.println("2-Best Fit");
		System.out.println("3-Worst Fit");
	    System.out.println("4-EXIT");
		System.out.println("* * * * * * * * * * * * * * * * * * ");
		
		System.out.println("Enter your choice: ");
		choice = scan.next();
		
		Policy policy=null;
		switch(choice)

      {
    	  
    	  case"1":
    		  
    		partitions.clear();
    		Partition.counter=0;
       	    for(int i=0;i<temp.size();i++)
   			 {   String name=temp.get(i).name;
   			    double size=temp.get(i).size;
   			    Partition p=new Partition(name,size);
   				partitions.add(p);
   				
   			 }
			policy=new Policy(partitions);
			for(int i=0;i<processList.size();i++)
			{
				if(!policy.firstFit(processList.get(i)))
				{
					unAdded.add((processList.get(i)));
				}
	        }
			
			policy.print();
		    System.out.println();
		    
		  
		   
           System.out.print("Do you want to compact?    1.yes   2.no");
            System.out.print("\nEnter your choice: ");
         	int Choice=scan.nextInt();
         	if(Choice==1)
         	{
				policy.compact();
				
				
			for (int i=0;i<unAdded.size();i++)
		    {
					if(!policy.firstFit(unAdded.get(i)))
					{
						unAdded.add((processList.get(i)));
					}
			}
			policy.print();
			
			
			}
         	
         else
         { 
        	 int sizee=unAdded.size();
 			for(int i=0;i<sizee;i++)
 			{
 				Partition.counter--;
 				sizee--;
 				
 			}
 			unAdded.clear();

         		continue;
         }
     
         	int sizee=unAdded.size();
			for(int i=0;i<sizee;i++)
			{
				Partition.counter--;
				sizee--;
				
			}
			
         	unAdded.clear();
         	partitions.clear();
         
         	
       
         	policy=null;
			break;
		
	
       case"2":
    	   partitions.clear();
    	   Partition.counter=0;
    	   for(int i=0;i<temp.size();i++)
			{   String name=temp.get(i).name;
			    double size=temp.get(i).size;
			    Partition p=new Partition(name,size);
				partitions.add(p);
				
			}
    	
    	   policy=new Policy(partitions);
 
   		
    		
			for(int i=0;i<processList.size();i++)
			{
				if(!policy.bestFit(processList.get(i)))
				{
					unAdded.add((processList.get(i)));
				}
	        }
			policy.print();
		    System.out.println();
            System.out.print("Do you want to compact?    1.yes   2.no");
            System.out.print("\nEnter your choice: ");
         	int Choicee=scan.nextInt();
         	if(Choicee==1)
         	{
				policy.compact();
				
				for (int i=0;i<unAdded.size();i++)
				{
					if(!policy.bestFit(unAdded.get(i)))
					{
						unAdded.add((processList.get(i)));
					}
				}
				policy.print();
			}
         else
         {
        	int Sizee=unAdded.size();
  			for(int i=0;i<Sizee;i++)
  			{
  				Partition.counter--;
  				Sizee--;
  				
  			}
  			unAdded.clear();
           continue;
         }
         	int size=unAdded.size();
			for(int i=0;i<size;i++)
			{
				Partition.counter--;
				size--;
				
			}
  
         	policy=null;
         	unAdded.clear();
         	partitions.clear();

         	
			break;
			
       case"3":
    	   partitions.clear();
    	   Partition.counter=0;
    	   for(int i=0;i<temp.size();i++)
			{   String name=temp.get(i).name;
			    double Size=temp.get(i).size;
			    Partition p=new Partition(name,Size);
				partitions.add(p);
				
			}
    	   
    	   policy=new Policy(partitions);
			for(int i=0;i<processList.size();i++)
			{
				if(!policy.worstFit(processList.get(i)))
				{
					unAdded.add((processList.get(i)));
				}
	        }
			policy.print();
		    System.out.println();
            System.out.print("Do you want to compact?    1.yes   2.no");
            System.out.print("\nEnter your choice: ");
        	int Choiceee=scan.nextInt();
        	if(Choiceee==1)
        	{
				policy.compact();
				
				for (int i=0;i<unAdded.size();i++)
				{
					if(!policy.worstFit(unAdded.get(i)))
					{
						unAdded.add((processList.get(i)));
					}
				}
				policy.print();
			}
        	else
        	{
        		int Sizee=unAdded.size();
      			for(int i=0;i<Sizee;i++)
      			{
      				Partition.counter--;
      				Sizee--;
      				
      			}
      			unAdded.clear();
      			continue;
        	}
        		
       
        	
        	int sizeee=unAdded.size();
			for(int i=0;i<sizeee;i++)
			{
				Partition.counter--;
				sizeee--;
				
			}
        	unAdded.clear();
         	partitions.clear();

        	
			break;
    	   
      
      
      
      
      
      
      }
	}
  }
}
