package task03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

//壁のクラス
class Wall{
	private int start;
	private int end;
	public static int rowLength = 0;
	//コンストラクタ
	Wall(int start,int end){
		this.start = start;
		this.end = end;
	}
	//壁の始点を返す
	public int getStart(){
		return start;
	}
	//壁の終点を返す
	public int getEnd(){
		return end;
	}
}

public class Main {
	public static void main(String[] args){
		//入力
		Scanner in = new Scanner(System.in);
		//試行回数を格納する
		int testCase = in.nextInt();
		
		for(int i=0; i<testCase; i++){
			//壁の数
			int k = in.nextInt();
			//マジシャンが壁を通り抜けられる個数
			int n = in.nextInt();
			List<Wall> walls = dataInput(k,in);
			//Pass-Muraille実行
			passMuraille(walls,n);
			//最終行初期化
			Wall.rowLength = 0;		
			
		}
	}
		
	//データ入力のメソッド
	private static List<Wall> dataInput(int k,Scanner in){
		List<Wall> walls = new ArrayList<Wall>();
		//壁をListに格納
		for(int i=0; i<k; i++){
			//始点
			int start = in.nextInt();
			in.nextInt();//y座標飛ばし
			//終点
			int end = in.nextInt();
			//始点と終点の入れ替え
			if(start > end){
				int t = start;
				start = end;
				end = t;
			}
			in.nextInt();//y座標飛ばし
			walls.add(new Wall(start,end));
			//最終列の格納
			if(Wall.rowLength < end)
				Wall.rowLength = end;
		}
		return walls;
	}
	
	//Pass-Murailleの実行
	private static void passMuraille(List<Wall> walls,int n){
		//取り除いた壁の数のカウント
		int count = 0;
		//最終行の情報
		int rowLength = Wall.rowLength;
		//i行目に存在する壁のList
		List<Wall> iWalls = new ArrayList<Wall>();

		//0列目の壁の有無情報を格納する
		for(int i=0; i<walls.size(); i++){
			//0列目に壁が存在したら、iWallsに格納
			if(walls.get(i).getStart() == 0)
				iWalls.add(walls.get(i));
		}
		
		//列の数だけfor文を回す
		for(int i=0; i<=rowLength; i++){	
			//0列目でなかった時
			if(i > 0){
				//列i-1で終わる壁をlistから除く
				for(int j=0; j<iWalls.size(); j++){
					if(iWalls.get(j).getEnd() == i-1){
						iWalls.remove(j);
						j--;
					}
				}
				//列iで始まる壁をlistに追加する
				for(int k=0; k<walls.size(); k++){
					if(walls.get(k).getStart() == i){
						iWalls.add(walls.get(k));
					}
				}
			}
					
			//listの長さがnを超える時、n - listの長さの個数の壁を終わりの列の番号が大きい順にlistから削除する
			if(iWalls.size() > n){
				//iWallsのソート最終列でソート
				Collections.sort(iWalls,new Comparator<Wall>(){
					public int compare(Wall w1,Wall w2){
						return w2.getEnd()-w1.getEnd();
					}
				});
				//壁の削除
				for(int t=0; t<iWalls.size()-n; t++){
					walls.remove(iWalls.get(t));
					iWalls.remove(t);
					t--;
					//削除した壁の数をインクリメントする
					count++;
				}
			}		
		}
		System.out.println(count);
	}
}
