import java.util.*;
class BinarySearch 
{
	static void swap(int []arr,int a,int b){
		int c=arr[a];
		arr[a]=arr[b];
		arr[b]=c;
	}
	static void selectionSort(int []arr){
		for(int i=0;i<arr.length;i++){
			int minInd=i;
			for(int j=i+1;j<arr.length;j++){
				if(arr[j]<arr[minInd]){
					minInd=j;
				}
			}
			swap(arr,i,minInd);
		}
	}
	public static void main(String[] args) 
	{
		System.out.println("Passes args are : ");

		for(int i=0;i<args.length;i++){
			System.out.println(args[i]);
		}
		
		System.out.println("Enter the Key or the element You want to search");
		Scanner sc=new Scanner(System.in);
		int key=sc.nextInt();
		boolean keyFound=false;

		int arr[]={22,32,-31,45,31,23,78,0,5};
		selectionSort(arr);
		
		int low=0,high=arr.length-1;

		while(low<=high){
			int mid=low+(high-low)/2;

			if(arr[mid]==key){
				keyFound=true;
				break;
			}
			else if(arr[mid]<key){
				low=mid+1;
			}
			else high=mid-1;
		}

		System.out.println( keyFound ? "Key Found" : "Key Not Found");

	}
}
