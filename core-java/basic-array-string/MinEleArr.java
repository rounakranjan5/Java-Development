import java.util.*;

class MinEleArr 
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size of the Array");
		int n=sc.nextInt();
		
		int arr[]=new int[n];

		int mini=Integer.MAX_VALUE;

		for(int i=0;i<n;i++){
			int ele=sc.nextInt();
			arr[i]=ele;
			mini=Math.min(mini,arr[i]);
		}

		System.out.println("Mini element of the array is "+mini);
		
	}
}
