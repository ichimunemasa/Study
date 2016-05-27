package task02;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		//最初の文字判定(数字判定)
		int times = in.nextInt();
		
		for(int i=0; i<times; i++){
			//入力値を格納(現在電気がついているかどうか)
			int[][] input = dataInput(in);
			//Extended Lights Out　開始
			System.out.println("PUZZLE #" + (i+1));
			extendedLights(input);
		}
	}
	
	//データの入力
	private static int[][] dataInput(Scanner in){
		int[][] input = new int[5][6];
		for(int i=0;i<5;i++){
			for(int j=0;j<6;j++){
				input[i][j] = in.nextInt();
			}
		}	
		return input;
	}
	
	//Extended Lights Out の 開始
	private static void extendedLights(int[][] input){
		
		for(int i=1; i<=64; i++){
			//押すか押さないかを表す配列
			int[][] light = new int[5][6];
			//1行目の配列を格納
			for(int j=0; j<6; j++){
				if((i & (1<<j)) != 0)
					light[0][j] = 1;
				else
					light[0][j] = 0;
			}
			//全てのスイッチをオフにできたら
			if(explore(light,input))
				break;
			
		}
	}
	
	//探索を行うメソッド
	private static boolean explore(int[][] light,int init[][]){
		int[][] input = new int[5][6];
		//ディープコピー
		for(int i=0; i<init.length;i++) input[i] = init[i].clone();

		//2行目から5行目まで
		for(int i=0; i<5; i++){
			//1列目から6列目まで
			for(int j=0; j<6; j++){
				//上のマスの電気がついていたら、スイッチを押す
				
				//１行目処理
				if(i==0){
					if(light[i][j] == 1){
						if(j==0){
							input[i][j] = input[i][j]^1;
							input[i+1][j] = input[i+1][j]^1;
							input[i][j+1] = input[i][j+1]^1;
						}
						else if(j==5){
							input[i][j] = input[i][j]^1;
							input[i+1][j] = input[i+1][j]^1;
							input[i][j-1] = input[i][j-1]^1;
						}else{
							input[i][j] = input[i][j]^1;
							input[i+1][j] = input[i+1][j]^1;
							input[i][j+1] = input[i][j+1]^1;
							input[i][j-1] = input[i][j-1]^1;
						}
					}
					continue;
				}
				//2行目以降
				if(input[i-1][j] == 1){
					light[i][j] = 1;
					//左端
					if(j==0 && i!=4){
						input[i][j] = input[i][j]^1;
						input[i-1][j] = input[i-1][j]^1;
						input[i][j+1] = input[i][j+1]^1;
						input[i+1][j] = input[i+1][j]^1;
					}
					//右端
					else if(j==5 && i!=4){
						input[i][j] = input[i][j]^1;
						input[i-1][j] = input[i-1][j]^1;
						input[i][j-1] = input[i][j-1]^1;
						input[i+1][j] = input[i+1][j]^1;
					}
					//左下角
					else if(j==0 && i==4){
						input[i][j] = input[i][j]^1;
						input[i-1][j] = input[i-1][j]^1;
						input[i][j+1] = input[i][j+1]^1;
					}
					//右下角
					else if(j==5 && i==4){
						input[i][j] = input[i][j]^1;
						input[i-1][j] = input[i-1][j]^1;
						input[i][j-1] = input[i][j-1]^1;
					}
					//一番下
					else if((j!=0 && i==4) || (j!=5 && i==4)){
						input[i][j] = input[i][j]^1;
						input[i-1][j] = input[i-1][j]^1;
						input[i][j-1] = input[i][j-1]^1;
						input[i][j+1] = input[i][j+1]^1;
					}
					else{//その他のマス
						input[i][j] = input[i][j]^1;
						input[i+1][j] = input[i+1][j]^1;
						input[i-1][j] = input[i-1][j]^1;
						input[i][j-1] = input[i][j-1]^1;
						input[i][j+1] = input[i][j+1]^1;
						
					}
				}else{//上のマスが0だった時(電気がついていなかった時)
					light[i][j] = 0;
				}
			}
		}
		
		//チェック
		for(int i=0; i<5;i++){
			for(int j=0;j<6;j++){
				if(input[i][j] == 1)
					return false;
			}
		}
		
		//出力
		for(int i=0; i<5;i++){
			for(int j=0;j<6;j++){
				System.out.print(light[i][j] + " ");
			}
			System.out.println();
		}
		return true;
	}
	
}
