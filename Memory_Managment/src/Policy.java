import java.util.ArrayList;



public class Policy {
	ArrayList<Partition> partitions =new ArrayList<Partition>();
	public Policy(ArrayList<Partition> partitions)
	{
		/*
		Partition p1=new Partition("Partition 0",90);
		Partition p2=new Partition("Partition 1",20);
		Partition p3=new Partition("Partition 2",5);
		Partition p4=new Partition("Partition 3",30);
		Partition p5=new Partition("Partition 4",120);
		Partition p6=new Partition("Partition 5",80);
		partitions.add(p1);
		partitions.add(p2);
		partitions.add(p3);
		partitions.add(p4);
		partitions.add(p5);
		partitions.add(p6);
		
		*/
		this.partitions=partitions;
		
	}


	public boolean firstFit(Process p) //if it returned true->process allocated  -  false->process not allocated
	{
		
		for (int i=0;i<partitions.size();i++) 
		{
			//checking if the partition is available & if the size of partition of index i > size of the current process
			if (partitions.get(i).isAvailable && partitions.get(i).size>=p.size) 
			{
				partitions.get(i).setProcess(p);	
				partitions.get(i).isAvailable=false; //setting the partition to be not available
				double size=partitions.get(i).size -p.size;
				partitions.get(i).size=p.size; //setting the partition size with process size
				createNewPartition(size, i+1); //creating a new partition in position (i+1) with the size remained from the partition of index i
				
				return true;
				
			}
			
		}
		System.out.println(p.name+" Cannot be allocated"); //if there is no partition compatible with the process 
		return false;
	}
	
	public boolean bestFit(Process p)
	{
		double size=100000;
		double diff=100000;
		int position=0;
		double s=0;
		Partition candidatePartition=null; //temporary partition
		
		for (int i=0;i<partitions.size();i++)
		{
			
			if (partitions.get(i).isAvailable)
			{
				if(partitions.get(i).size==p.size)  //if the size of partition is exactly equal to size of process
				{
					partitions.get(i).process=p;
					partitions.get(i).isAvailable=false;
					return true;
				}
				
				if(partitions.get(i).isAvailable&& partitions.get(i).size>p.size)
				{
					diff=partitions.get(i).size-p.size;
				}
				
				if(diff<size) //until we find the best fit for the process
				{
					size=diff;
					candidatePartition=partitions.get(i);
					position=i;
					s=candidatePartition.size-p.size;
			    }
			}
		}
		
		 if(candidatePartition==null) //if there is no partition compatible with the process 
         {
  	   		System.out.println(p.name+" => "+"can not be allocated");
  	   		return false;
         }
      else
  	   {
      	    candidatePartition.size=p.size;
			candidatePartition.process=p;
			candidatePartition.isAvailable=false;
			createNewPartition(s, position+1);
			return true;
  	   }
	}
	
	public boolean worstFit(Process p)
	{
		double size=0;
		double diff=0;
		int position=0;
		double s=0;
		Partition candidatePartition=null; //temporary partition
		
		for (int i=0;i<partitions.size();i++) 
		{
			
			if (partitions.get(i).isAvailable)
			{

				
				if(partitions.get(i).isAvailable&& partitions.get(i).size>p.size)
				{
					diff=partitions.get(i).size-p.size;
					if(diff>size)
					{
						size=diff;
						candidatePartition=partitions.get(i);
						position=i;
						s=candidatePartition.size -p.size;
					}
				}
				
			}
		}
		
           if(candidatePartition==null) //if there is no partition compatible with the process
               {
        	   		System.out.println(p.name+" => "+"can not be allocated");
        	   		return false;
               }
           else
        	   {
	        	    candidatePartition.size=p.size;
					candidatePartition.process=p;
					candidatePartition.isAvailable=false;
					createNewPartition(s, position+1);
					return true;
        	   }
	}
	
	public void createNewPartition(double size,int position) // to create new partition if needed
	{
		
		if(size>0)
		{
			Partition newPartition=new Partition(size);
			partitions.add(position, newPartition);	
		}
		
	}
	public void print() //printing each partition with its info
	{
		System.out.println("--------------");
		for (Partition i : partitions) 
		{
			System.out.println(i);
			
		}
		
	}
	public void compact() //compact all empty partitions to a new partition
	{
		
		double size=0; //to store the size of new partition
		boolean found=false;
		for(int i=0;i<partitions.size();i++) //traversing 
		{
			if(partitions.get(i).isAvailable) //if the partition is available
			{
	
				size+=partitions.get(i).size; //add the size of partition of index i
				partitions.remove(i); 
				found=true;
				i--;
		
			}
			
		}
		if(found)
		{
			createNewPartition(size,partitions.size());
		}
		
		
	}
}


