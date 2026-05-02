class ReversalArr 
{
	static void swapEle(int[]arr, int a,int b){
		int temp=arr[a];
		arr[a]=arr[b];
		arr[b]=temp;
	}
	static void reverseArr(int []arr){
		int i=0,j=arr.length-1;

		while(i<j){
			swapEle(arr,i,j);
			i++;
			j--;
		}
	}

	public static void main(String[] args) 
	{
		int arr[]=new int[6];
		arr[0]=1;
		arr[1]=2;
		arr[2]=3;
		arr[3]=4;
		arr[4]=5;
		arr[5]=6;
		
		System.out.println("Before reversal");
		for(int i=0;i<6;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();

		reverseArr(arr);

		System.out.println("After reversal");
		for(int i=0;i<6;i++){
			System.out.print(arr[i]+" ");
		}

	}
}
