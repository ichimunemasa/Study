package task06;
//branch_a

import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		//試行回数分まわる。
		String input = in.next();
		while(!input.equals("0")){
			System.out.println(increasingSeaquence(input));
			input = in.next();
		}
	}
	
	//Increasing Sequencesの実行
	public static StringBuilder increasingSeaquence(String input){
		//入力された桁数
		int strNum = input.length();
		
		//すべて0だった時の処理
		int zeroCounter = 0;
		for(int i=0; i < strNum; i++){
			if(!input.substring(i,i+1).equals("0"))
				break;
			else
				zeroCounter++;
		}
		if(zeroCounter == strNum)
			return new StringBuilder(input);
		
		//左側から動的計画法
		String[] f = new String[strNum+1];
		f[0] = "0";
		for(int i=1; i<=strNum; i++){
			for(int j = i-1; j >= 0; j--){
				String last_str = input.substring(j,i);
				if(compareString(f[j],last_str) < 0 || compareString(f[j],"0") == 0){
					f[i] = last_str;
					break;
				}
			}
		}
		
		//右側から動的計画法
		String f2[] = new String[strNum];
		int limit = strNum - f[strNum].length();
		f2[limit] = f[strNum];
		for(int i = limit - 1; i >= 0; i--){
			for(int j = limit; j > i; j--){
				String pre_str = input.substring(i,j);
				if(compareString(pre_str,f2[j]) < 0 || (compareString(f2[j],"0") == 0 && compareString(pre_str,f2[limit]) < 0)){
					f2[i] = pre_str;
					break;
				}
			}
		}
		
		
		//動的計画法の表をもとにカンマをプロットする
		StringBuilder sb_ = new StringBuilder();
		for(int i=0; i < f2.length;){
			if(compareString(f2[i],"0") == 0){
				sb_.append(f2[i]);
				i = i + f2[i].length();
			}else if(i == limit){
				sb_.append(f2[i]);
				break;
			}else{
				sb_.append(f2[i]+",");
				i = i + f2[i].length();
			}
		}
		
		return sb_;
	}
	
	//数字を表すものではあるが、2つの文字列の大きさの比較
	public static int compareString(String s1, String s2){
		
		StringBuilder sb1 = new StringBuilder(s1);
		StringBuilder sb2 = new StringBuilder(s2);
		
		//0が続く場合の処理
		int zeroCounter = 0;
		for(int i=0; i < s1.length(); i++){
			if(sb1.substring(i, i+1).equals("0")){
				zeroCounter++;
			}else
				break;
		}
		sb1.delete(0, zeroCounter);
		zeroCounter = 0;
		for(int i=0; i < s2.length(); i++){
			if(sb2.substring(i, i+1).equals("0")){
				zeroCounter++;
			}
			else
				break;
		}
		sb2.delete(0, zeroCounter);
		
		//大きさ比較
		if(sb1.length() > sb2.length())
			return 1;
		else if(sb1.length() < sb2.length()){
			return -1;
		}else{
			//値の大きさの比較を行う
			for(int i=0; i < sb1.length(); i++){
				if(Integer.parseInt(sb1.substring(i,i+1)) > Integer.parseInt(sb2.substring(i,i+1)))
					return 1;
				else if(Integer.parseInt(sb1.substring(i,i+1)) < Integer.parseInt(sb2.substring(i,i+1))){
					return -1;
				}
			}
			return 0;
		}
	}
}
