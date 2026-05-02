import java.util.*;
class  MaxElementArr
{
	public static void main(String[] args) 
	{
		System.out.println("Enter the size of your array");
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int maxi=Integer.MIN_VALUE;

		int arr[]=new int[n];

		System.out.println("Enter the elements of the array");

		for(int i=0;i<n;i++){
			int ele=sc.nextInt();
			arr[i]=ele;
			maxi=Math.max(maxi,arr[i]);
		}

		System.out.println("The maximum element of the above input array is : "+maxi);
		
		int arrFixed[]={21,32,12,-500,43,76,33};


		for(int i=0;i<7;i++){
			maxi=Math.max(maxi,arrFixed[i]);
		}

		System.out.println("The maximum element of the fixed array is :"+maxi);

	}
}
