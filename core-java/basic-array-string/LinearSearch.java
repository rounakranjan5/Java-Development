import java.util.*;
class LinearSearch 
{
	public static void main(String[] args) 
	{
		System.out.println("Enter the element or the key You want to search");
		Scanner sc=new Scanner(System.in);
		int key=sc.nextInt();
		int arr[]={11,23,21,-22,54,22,56};
		boolean keyFound=false;
		
		for(int i=0;i<arr.length;i++){
			if(arr[i]==key){
				keyFound=true;
				break;
			}
		}

		System.out.println( keyFound ?  "Key Found in the array" : "Key Not Found in the array");

	}
}
