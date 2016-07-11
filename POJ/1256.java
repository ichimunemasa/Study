package task04;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		//試行回数を格納する
		int testCase = in.nextInt();
		//testCaseの回数だけ回す
		for(int i=0; i<testCase; i++){
			//入力文字の格納
			Character[] strStore = inputString(in);
			//配列のソート
			Arrays.sort(strStore,new Comparator<Character>(){
				public int compare(Character a,Character b) {
					Double a_value = ((int)a >=(int)'a'&&(int)a <=(int)'z') ?(double)a-31.5:(double)a;
					Double b_value = ((int)b >=(int)'a'&&(int)b <= (int)'z') ?(double)b-31.5:(double)b;
					return a_value.compareTo(b_value);
				}
			});
			//Anagramの実行
			anagram(0,strStore,new char[strStore.length] , new boolean[strStore.length]);
		}
	}
	
	//入力操作
	public static Character[] inputString(Scanner in){
		String str = in.next();
		//1文字づつ格納する配列の準備
		Character[] strStore = new Character[str.length()];
		//文字の格納
		for(int i=0; i<str.length();i++)
			strStore[i] = str.charAt(i);
		return strStore;
	}
	
	//Anagram実行
	public static void anagram(int n,Character[] c,char[] perm,boolean[] used){	
		//一つ前の文字
		char before = ' ';
		//文字の出力
		if(n == c.length){
			System.out.println(perm);
		}else{
			for(int j=0; j < c.length; j++){
				if(!used[j]){
					//重複を避ける処理
					if (before == c[j]) continue;
					before = c[j];
					used[j] = true;
					perm[n] = c[j];
					anagram(n+1,c,perm,used);
					used[j] = false;
				}
			}
		}
	}
}
