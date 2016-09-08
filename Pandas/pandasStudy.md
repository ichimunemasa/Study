# Pandasの勉強まとめ
* Pandasの使い方をツラツラと書いていく
* Pandasはpythonでデータ解析などをするのに便利なLibrary
* numpyとpandasはimportしておく
```python
import numpy as np
import pandas as pd
```
## Series
* 軸にラベルを付けた1次元の配列
```python
pd.Series([1,2,3])
#実行結果
# 0    1  
# 1    2
# 2    3
# dtype: int64

#軸のラベルを指定する
s = pd.Series([1,2,3],index=['a','b','c'])
print s
#実行結果
# a    1
# b    2
# c    3
# dtype: int64

#ラベルを表示
print s.index
#実行結果
# Index([u'a', u'b', u'c'], dtype='object')

#最大値
print s.max()
#実行結果
# 3

#最小値
print s.min()
#実行結果
# 1

#中央値
print s.median()
#実行結果
# 2.0

#分散
print s.var()
#実行結果
# 1.0

#合計
print s.sum()
#実行結果
# 6

#modの計算
print s.mod(2)
#実行結果
# a   1
# b   0
# c   1
# dtype: int64

#特定の関数を各値にする
print s.apply(lambda x: x*3)
#実行結果
# a   3
# b   6
# c   9
# dtype: int64

#最大値のindexを取得
print s.argmax()
# 実行結果
# 'c'

#最小値のindexを取得
s.argmin()
#実行結果
# 'a'

#listに変更
print s.tolist()
#実行結果
# [1, 2, 3]

#dictionaryに変更
print s.to_dict()
#実行結果
# {'a': 1, 'c': 3, 'b': 2}

#jsonに変更
print s.to_json()
#実行結果
# '{"a":1,"b":2,"c":3}'


```

## DataFrame
* DataFrameの作成(Matrix)

### DataFrameの作成
```python
df = pd.DataFrame([[1,2,3],[2,5,8],[3,6,9]],
				  index=['i1','i2','i3'],
				  columns=list("abc"))
print df
#実行結果
#	a b c
#i1 1 2 3
#i2 2 5 8
#i3 3 6 9

```

### 値の操作
* 行に関する操作は,".ix"を用いる

```python
#指定した行の取り出し。index名かindex番号で与えられる
print df.ix['i1']
#実行結果
# a   1
# b   2
# c   3
# Name: i1, dtype: int64

print df.ix[1]
# a   2
# b   5
# c   8
# Name: i2, dtype: int64

#2つ目のparameterに列を渡すことで、行を取得できる
print df.ix['i1','a']
# 1

# : は全指定
print df.ix[:,'a']
#実行結果
# i1   1
# i2   2
# i3   3
# Name: a, dtype: int64

# 複数の指定も配列で可能
print df.ix[[1,2],['b','c']]
#実行結果
#	b c
#i2 5 8
#i3 6 9

print df.ix[[1,2][1,2]]
#実行結果
#   b c 
#i2 5 8
#i3 6 9

```
* 列に関する操作は[column名]で表す

```python

print df['a']
#実行結果
# i1   1
# i2   2
# i3   3
# Name: a, dtype: int64

#arrayとして取得
print df['a'].values
#実行結果
# array([1, 2, 3])

#index名を指定することで値として取得できる
print df['a']['i3']
#実行結果
# 3
```
* DataFrameをtableとみなして、位置指定から値を明示的に取る方法
```python
print df.iloc[0,0]
#実行結果
# 1

print df.iloc[2]
#実行結果
# a   3
# b   6
# c   9
# Name: i3, dtype: int64

```

### 条件を満たす要素に値を代入
* 例） 行列において1より大きい値を全部1にしたい時

```python
print a = pd.DataFrame(np.random.randint(3,size=(5,3)))
#実行結果
#    0  1  2
# 0  1  0  0
# 1  2  2  0
# 2  1  0  2
# 3  2  1  1
# 4  0  0  2

#条件を満たすものだけを抽出したDataFrame
print a[a>1]
#実行結果
#     0   1   2
# 0 NaN NaN NaN
# 1   2   2 NaN
# 2 NaN NaN   2
# 3   2 NaN NaN
# 4 NaN NaN   2

#条件を満たすものに-1を代入
a[a>1] = -1
print a
#実行結果
#    0  1  2
# 0  1  0  0
# 1 -1 -1  0
# 2  1  0 -1
# 3 -1  1  1
# 4  0  0 -1


```

### Nanの処理
* Nanの削除
* doropnaで削除できる
```python
#Nanを含む行を削除
df['a']['i2']=np.nan
print df.dropna()
#実行結果
#     a  b  c
# i1  1  2  3
# i3  3  6  9

#Nanを含む列を削除
# df.dropna(axis=1)


```

* Nanを埋める
* fillnaを使う。fillnaで指定した値を埋めることができる
* 直前の値で補完(pad/fill)、直後の値で補完(bfill)

```python
df = pd.DataFrame([[1,2,3],[2,5,8],[3,6,9]],
               index=['i1','i2','i3'],
			   columns=list("abc"))
df[df>3]
#-1を埋める
df.fillna(-1)
#直前の値を補完
df.fillna(method='pad')
#直後の値を補完
df.fillna(method='bfill')

```
