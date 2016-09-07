package task07;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		//試行回数入力
		int times = in.nextInt();
		for(int i=0; i<times; i++){
			Integer[] coin = dataInput(in);
			euroEfficiency(coin);
		}	
	}
	
	//データ入力
	private static Integer[] dataInput(Scanner in){
		Integer[] inputCoins = new Integer[6];
		for(int i=0; i < inputCoins.length;i++){
			inputCoins[i] = in.nextInt();
		}
		return inputCoins;
	}
	
	//Euro Efficiencyの実行
	private static void euroEfficiency(Integer[] coin){
		//動的計画表の要素数
		int dpNum = 100;
		//動的計画表(お釣りなし)
		Integer[] f = new Integer[dpNum+1];
		//十分に大きな値で配列を埋めておく
		Arrays.fill(f, 100);
		f[0] = 0;

		//コインの動的計画法
		for(int i=0; i < coin.length; i++){
			for(int j=coin[i]; j <= dpNum; j++){
				f[j] = Math.min(f[j], f[j - coin[i]]+1);
			}
		}
		
		//ここまでのコインの枚数最大値を求める
		int max = 0;
		for(int i=0; i < dpNum; i++){
			max = Math.max(max,f[i]);
		}
		
		//お釣りを考慮した配列の準備
		//f[コインの最大値×100セントまでに必要な最大コインの枚数]を準備する
		Integer[] f2 = new Integer[coin[5]*max];
		//十分に大きな値で配列を埋めておく
		Arrays.fill(f2, 100);
		System.arraycopy(f, 0, f2, 0, dpNum);
		f = f2;
		
		//100以降の動的計画法
		for(int i=0; i < coin.length; i++){
			for(int j=100; j < f.length; j++){
				f[j] = Math.min(f[j], f[j - coin[i]]+1);
			}
		}
		
		//お釣りを考慮
		for(int i=1; i <= 100; i++){
			//2枚以下なら考える必要はない
			if(f[i] <= 2)
				continue;
			//3枚以上の時、お釣りを考慮した方が良いことがある
			for(int j=i+1; j < f.length; j++){
				if(f[i] > f[j] + f[j-i])
					f[i] = f[j] + f[j-i];
			}
		}
		
		//コインの必要枚数の平均値と最大値を算出
		//コインのカウンタ
		int coinCounter = 0;
		//最大コインの枚数
		int maxCoin = 0;
		for(int i=1; i <= 100; i++){
			coinCounter = coinCounter + f[i];
			if(maxCoin < f[i])
				maxCoin = f[i];
		}
		
		//出力
		double aveCoin = coinCounter/100.0; 
		BigDecimal bd = new BigDecimal(aveCoin);
		BigDecimal bdCoin = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
		System.out.printf("%.2f %d", bdCoin.doubleValue(),maxCoin);
		System.out.println();
	}
	
}

