import java.util.*;
class StringReversalAndPalindromeCheck 
{
	static String revStr(String st){
		String reversedStr="";
		for(int i=st.length()-1;i>=0;i--){
			reversedStr+=st.charAt(i);
		}
		return reversedStr;
	}

	static void swap(String st,int i,int j){
		char c=st.charAt(i);
		st.charAt(i)=st.charAt(j);
		st.charAt(j)=c;
	}

	static void revInplStr(String st){
		int i=0,j=st.length()-1;
		while(i<j){
			swap(st,i,j);
			i++;
			j--;
		}
	}

	public static void main(String[] args) 
	{
		System.out.println("Enter the String");
		Scanner sc=new Scanner(System.in);

		String st=new String();
		st=sc.nextLine();
		
		String reversedString=revStr(st);

		System.out.println(st.equals(reversedString) ? "Input String is Palindrome" : "Input String is Not Palindrome");
		
		System.out.println("---Inplace reversal---");
		
		System.out.println("Before : "+st);
		
		revInplStr(st);

		System.out.println("After : "+st);

	
	}
}
