package task01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		while(true){
			//最初の文字判定
			String init = in.next();
			//”#”終了判定
			if(init.equals("#")){
				break;
			}
			//カードの山の生成
			List<List<String>> pile = new ArrayList<List<String>>();
			//データの格納
			dataInput(in,pile,init);
			//"Accordian" 開始
			accordian(pile);
		}
	}
	
	//データ格納用メソッド
	private static List<List<String>> dataInput(Scanner in,List<List<String>> pile,String init){
		//最初のカードだけ格納
		List<String> cards = new ArrayList<String>();
		pile.add(cards);
		pile.get(0).add(init);
		
		//最初の文字だけ読み込み
		for(int i=1; i < 52; i++){
			cards = new ArrayList<String>();
			pile.add(cards);
			pile.get(i).add(in.next());
		}
		//データ格納後のリストを返す
		return pile;
	}
	
	//"Accordian"を行うメソッド
	private static void accordian(List<List<String>> pile){
		//探索箇所を追っていく変数
		int currentNumber = 1;
		
		while(true){
			//左3つ隣のカードに重ねる処理
			if(currentNumber-3 >= 0 && ((pile.get(currentNumber).get(pile.get(currentNumber).size()-1).charAt(0) == pile.get(currentNumber-3).get(pile.get(currentNumber-3).size()-1).charAt(0) || pile.get(currentNumber).get(pile.get(currentNumber).size()-1).charAt(1) == pile.get(currentNumber-3).get(pile.get(currentNumber-3).size()-1).charAt(1)))){
				//要素の追加
				pile.get(currentNumber-3).add(pile.get(currentNumber).get(pile.get(currentNumber).size()-1));
				//要素の削除
				pile.get(currentNumber).remove(pile.get(currentNumber).get(pile.get(currentNumber).size()-1));
				//空になったカードの山の削除
				if(pile.get(currentNumber).isEmpty())
					pile.remove(currentNumber);
				
				currentNumber -= 3;
				continue;
			}
			//左1つ隣のカードに重ねる処理
			if(currentNumber-1 >= 0 && ((pile.get(currentNumber).get(pile.get(currentNumber).size()-1).charAt(0) == pile.get(currentNumber-1).get(pile.get(currentNumber-1).size()-1).charAt(0) || pile.get(currentNumber).get(pile.get(currentNumber).size()-1).charAt(1) == pile.get(currentNumber-1).get(pile.get(currentNumber-1).size()-1).charAt(1)))){
				//要素の追加
				pile.get(currentNumber-1).add(pile.get(currentNumber).get(pile.get(currentNumber).size()-1));
				//要素の削除
				pile.get(currentNumber).remove(pile.get(currentNumber).get(pile.get(currentNumber).size()-1));
				//空になったカードの山の削除
				if(pile.get(currentNumber).isEmpty())
					pile.remove(currentNumber);
				
				currentNumber -= 1;
				continue;
			}
			//探索箇所移動
			currentNumber++;
			//探索終了処理
			if(currentNumber == pile.size())
				break;
		}
		
		//出力
		System.out.print(pile.size() + " piles remaining:");
		for(int i=0; i < pile.size(); i++){
				System.out.print(" "+pile.get(i).size());
			}
		System.out.println();
		
	}
	
}
