package task05;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//Alphabetクラス
class Alphabet{
	//xの最大最小
	private int x_max;
	private int x_min;
	//yの最大最小
	private int y_max;
	private int y_min;
	//行の長さ
	public static int rowLength = 0;
	//列の長さ
	public static int columnLength = 0;
	//コンストラクタ
	Alphabet(int x_max,int x_min,int y_max,int y_min){	
		this.x_max = x_max;
		this.x_min = x_min;
		this.y_max = y_max;
		this.y_min = y_min;
	}
	//xの最大値
	public int returnX_Max(){
		return x_max;
	}
	//xの最小値
	public int returnX_Min(){
		return x_min;
	}
	//yの最大値
	public int returnY_Max(){
		return y_max;
	}
	//yの最小値
	public int returnY_Min(){
		return y_min;
	}
}

//Eventクラス
class Event implements Comparable<Event>{
	boolean opening;
	int pos;
	Event(boolean opening, int pos){
		this.opening = opening;
		this.pos = pos;
	}
	
	@Override
	public int compareTo(Event e){
		return this.pos -e.pos;
	}	
}



public class Main {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		//試行回数を格納する
		int testCase = in.nextInt();
		
		//testCaseの回数だけ実験を行う
		for(int i=0; i< testCase; i++){
			//文字の種類
			int k = in.nextInt();
			//それぞれの文字の個数
			int p = in.nextInt();
			//データ入力、Alphabet型のデータを持つリスト
			List<Alphabet> alp = inputData(k,p,in);
			
			//Alphabet Game　スタート
			boolean result = alphabetGame(alp);
			//結果出力
			if(result)
				System.out.println("YES");
			else
				System.out.println("NO");
			//マス目初期化
			Alphabet.rowLength = 0;
			Alphabet.columnLength = 0;
		}		
	}
	
	//データの入力
	public static List<Alphabet> inputData(int k, int p,Scanner in){
		//アルファベットの情報を持つリスト
		List<Alphabet> alp = new ArrayList<Alphabet>();
		for(int i=0; i<k; i++){
			//x,yそれぞれの視点と終点
			int x_max = in.nextInt();
			int y_max = in.nextInt();
			int x_min = x_max;
			int y_min = y_max;
			for(int j=1; j<p; j++){
				int currentX = in.nextInt();
				int currentY = in.nextInt();
				if(x_max < currentX)
					x_max = currentX;
				if(x_min > currentX)
					x_min = currentX;
				if(y_max < currentY)
					y_max = currentY;
				if(y_min > currentY)
					y_min = currentY;
			}
			//アルファベット作成
			Alphabet c = new Alphabet(x_max,x_min,y_max,y_min);
			alp.add(c);
			//最大マスの格納
			if(Alphabet.rowLength < x_max)
				Alphabet.rowLength = x_max;
			if(Alphabet.columnLength < y_max)
				Alphabet.columnLength = y_max;
		}
		return alp;
	}
	
	//Alphabet Gameを行うメソッド
	public static boolean alphabetGame(List<Alphabet> alp){
		//縦線の位置リスト
		List<Integer> vLine = new ArrayList<Integer>();
		//横線の位置リスト
		List<Integer> hLine = new ArrayList<Integer>();
		//縦線処理用
		Event x_events[] = new Event[alp.size()*2];
		//横線処理用
		Event y_events[] = new Event[alp.size()*2];
		
		//縦線を引くための処理
		int k = 0;
		for (int i = 0; i < alp.size(); i++) {
			x_events[k++] = new Event(true, alp.get(i).returnX_Min());
			x_events[k++] = new Event(false, alp.get(i).returnX_Max());
		}
		Arrays.sort(x_events);
		int count = 0;
		int flag = x_events[0].pos;
		for (int  i = 0; i < x_events.length; i++) {
			if(count == 0 && i != 0 && flag != x_events[i].pos)
				vLine.add(x_events[i-1].pos+1);
				
			if(x_events[i].opening) {
				count++;
			}
			else {
				count--;
			}
			flag = x_events[i].pos;
			
		}
		
		//横線を引くための処理
		k = 0;
		for (int i = 0; i < alp.size(); i++) {
			y_events[k++] = new Event(true, alp.get(i).returnY_Min());
			y_events[k++] = new Event(false, alp.get(i).returnY_Max());
		}
		Arrays.sort(y_events);
		count = 0;
		
		flag = y_events[0].pos;
		for (int  i = 0; i < y_events.length; i++) {
			if(count == 0 && i != 0 && flag != y_events[i].pos)
				hLine.add(y_events[i-1].pos+1);
			
			if(y_events[i].opening) {
				count++;
			}
			else {
				count--;
			}
			flag = y_events[i].pos;
		}
		
		//分割の初期値
		int x_min=1;
		int y_min=1;
		int x_max = Alphabet.rowLength;
		if(!vLine.isEmpty())//縦線が存在した場合
			x_max = vLine.get(0)-1;
		int y_max = Alphabet.columnLength;
		if(!hLine.isEmpty())//横線が存在した場合
			y_max = hLine.get(0)-1;
		//文字カウンター
		count = 0;
		//分割されたブロックに異なるアルファベットが入っている場合Noになる
		for(int i=0; i<=vLine.size(); i++){
			for(int j=0; j<=hLine.size(); j++){
				//分割ブロックとそれぞれのアルファベットの長方形が交わるかどうか
				for(int s = 0; s < alp.size(); s++){
					if((x_min <= alp.get(s).returnX_Min() && alp.get(s).returnX_Min() <= x_max) || (x_min <= alp.get(s).returnX_Max() && alp.get(s).returnX_Max() <= x_max)){
						if((y_min <= alp.get(s).returnY_Min() && alp.get(s).returnY_Min() <= y_max) || (y_min <= alp.get(s).returnY_Max() && alp.get(s).returnY_Max() <= y_max)){
							count++;
						}
					}
				}
			//1ブロックに異なる2つのアルファベットが入っていたら
			if(count >= 2)
				return false;
			//カウンター初期化
			count = 0;
			//次のブロック(y座標)
			y_min = y_max+1;
			if(hLine.size() <= j+1)
				y_max = Alphabet.columnLength;
			else
				y_max = hLine.get(j+1)-1;
			}
			//次のブロック(x座標)
			x_min = x_max+1;
			if(vLine.size() <= i+1)
				x_max = Alphabet.rowLength;
			else
				x_max = vLine.get(i+1)-1;	
			//yの初期化
			y_min=1;
			y_max = Alphabet.columnLength;
			if(!hLine.isEmpty())//横線が存在した場合
				y_max = hLine.get(0)-1;
		}
		return true;
	}
}

