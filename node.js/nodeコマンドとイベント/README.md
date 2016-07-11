# nodeコマンドとイベント
## Node.jsの対話環境を利用
* 対話型評価環境(REPL : Read Eval Print Loop)がある。
### REPLを起動するnodeコマンド
* REPLの起動

``` JavaScript
$ node
```
* JavaScriptのコードを入力してEnterキーを押すと、コードが実行され戻り値が表示

``` JavaScript
> foo = "bar";
'bar'
```
* 関数の呼び出しを含む任意の式を実行できる

``` JavaScript
> Math.random()
0.657785597955808
> foo = 'abc'
'abc'
> bar = '012'
'012'
> foo + bar
'abc012'
```

* functionキーワードを使って、関数を定義
``` JavaScript
> function add(a,b){
...  return a+b;
...}
undefined
> add(1,2)
3
> add(foo,bar);
'abc012'
```

* コンソールにテキストや数値を表示するにはNode.jsの組み込み関数console.log関数を使用
``` JavaScript
> console.log('Hello, World');
Hello, World
undefined
> console.log(Math.PI)
3.141592653589793
undefined
```

### REPL環境の終了
  + Ctrl + "C"を2回入力

## Node.jsでのWebアプリケーション実行モデル

* シンプルなWebアプリケーションとして動作するコード
``` JavaScript
// httpモジュールを読み込み
var http = require('http');

//http.Serverクラスのインスタンスを作成する
var server = http.createServer();

//requestイベントハンドラを定義
server.on('requrst',function(request,response){
  //コンソールにリクエストされたURLを出力
  comsole.log(request.url)
  //200(OK)レスポンスと「Console-Type」：test/plain」ヘッダーを送信
  response.writeHead(200,{'Content-Type':　'text/plain'});
  //「hello」という文字列とともにリクエストURLを送信する
  response.end('hello' + request.url)
});

//8080番ポートで待受を開始
server.listen(8080,'localhost');
```

* Webサーバーを終了する
``` JavaScript
> server.close()
```

## さまざまな機能が実装された「モジュール」を使う
### モジュールを読み込む関数-require関数
  + モジュールを読み込んで利用
  + moduleName引数には、ロードしたいモジュールめいの名前を文字列で指定
  + require関数は指定されたモジュールを読み込み、そのモジュールに含まれるメソッドやクラスといったコンテンツを返す
  + require関数を実行したプログラムからは、このオブジェクトを通じてモジュール内の関数やクラスなどにアクセスできる

``` JavaScript
> require(moduleName)
```

* 例えばhttpモジュールを利用するには、、、
``` JavaScript
> var http = require('http');
```

* 読み込んだモジュール内の関数を呼び出すには、.演算子を使用
* たとえば、httpモジュールに含まれるcreateServer関数を実行するには、、、
* createServer関数はHTTPサーバークラスのインスタンスを作成する関数で、戻り値としてhttp.Server型のオブジェクトを返す

``` JavaScript
> var server = http.createServer();
```

### Node.jsのコアモジュール
   + Node.jsに付属しているモジュールをコアモジュールと呼ぶ
   + Node.js 0.8系で用意されているモジュール


|モジュール名|機能|安定度(Stability)|
|:--:|:--:|:--:|
|assert|アサーション|5|
|buffer|バイト列の格納および操作|3|
|child_process|子プロセスを使った負荷分散|3|
|cluster|コンソールへのメッセージ出力|1|
|console|コンソールへのメッセージ出力|4|
|crypto|暗号化ハッシュ|2|
|dgram|UDPを扱うソケット関連の処理|3|
|dns|DNS関連の処理|3|
|domain|複数のI/O処理間の連携|1|
|events|イベント処理を実装するための基底クラス|4|
|fs|ファイルおよびファイルシステムの操作|3|
|http|HTTPサーバー/クライアント|3|
|https|HTTPSサーバー/クライアント|3|
|net|ソケットの操作|3|
|os|OSに関連する情報の取得|4|
|path|パス文字列の処理|3|
|punycode|Punycode文字列のエンコード/デコード|2|
|quesystring|HTTPで使われるクエリ文字列の処理|3|
|readline|標準入出力を使用した対話的インターフェイス|2|
|repl|REPL|-|
|stream|ストリーム入出力処理が定義された基底クラス|2|
|string_decoder|バイナリ列から文字列デコード|3|
|tls|OpenSSLを使ったTLS/SSL通信|3|
|tty|TTY(キャラクター端末)の情報取得|2|
|url|URLの文字列のパースやフォーマット|3|
|util|各種ユーティリティ関数|5|
|vm|JavaScriptの実行エンジン(仮想マシン)|2|
|zlib|zlibを使ったデータの圧縮/伸長|3|

* コアモジュールの詳細はNode.jsのドキュメントを参照
  + [Node.jsドキュメント](http://nodejs.org/api/)
* 安定度(1~5)はそのモジュールがどれくらい安定しているかという完成度を示すもの
  + 安定度が低いからといって使い物にならないというわけではない

* Node.jsでグローバルに定義されているクラスや関数、オブジェクト

|クラス/関数/オブジェクト名|説明/処理内容|
|:--:|:--:|
|Bufferクラス|バイト列を扱うクラス|
|setTimeout関数|指定時間後に指定した関数を実行する|
|clearTimeout関数|setTimeout関数で設定した関数の実行を取り消す|
|setInterval関数|指定した間隔で設定した関数を実行する|
|clearInterval関数|setInterval関数で設定した関数の実行を取り消す|
|require関数|モジュールをロードする|
|moduleオブジェクト|モジュール情報にアクセスするためのオブジェクト|
|processオブジェクト|プロセス情報にアクセスするためのオブジェクト|

* モジュールの自作やサードパーティが公開しているモジュールの利用も可能

### 非同期処理とコールバック関数-synchronusとasynchronous
* 同期的な関数
  + 関数が呼びだされた時、処理の実行が完了するまでは関数の呼び出し元には戻らない関数
  + 例) console.log
* 非同期的な関数
  + 関数が呼びだされた時、関数内で実行すべき処理が完了する前に呼び出し元に戻る関数
  + 例) server.listen
* Node.jsプログラムでは非同期的な関数が多用される
* 非同期的な関数では関数の処理が完了する前に戻り値を返すので、関数の戻り値で処理の結果を受け取ることができない。
* そのため非同期的な関数やメソッドを持つクラスには「コールバック」や「イベント」といった処理の結果を通知するための仕組みが用意されている。
* コールバック
  + コールバック関数と呼ばれる別の関数を指定しておき、処理の完了時にその結果をコールバック関数の引数として与えて実行する仕組み
  + 例)ファイルからその内容を読みだす処理
    + Node.jsにはファイルに対する操作をまとめたfsモジュールがある
    + ファイルからその内容を読み出すにはfsモジュールに含まれるreadFile関数が利用できる。

    ``` JavaScript
    > fs.readFile(filename,[encoding],[callback])
    ```

    + 第3引数にコールバック関数を指定する。

  + 実際の挙動を確かめ
  ``` JavaScript
  > var fs = require('fs');
  undefined
  > function test01(){
  ... fs.readFile('example.txt','utf8',function(err,data){
  ....   console.log('read!');
  ....   console.log('data');  
  ... });
  ... console.log('foo');
  ... console.log('bar');
  ... for(var i=0; i<10000;i++){
  ....     console.log('.');
  .... }
  ...}
  undefined
  test01()
  ```
  + readFile関数の実行後console.('foo')やconsole.log('bar')、forループ内の処理が完了してからコールバック関数内に記述されている内容が実行される
  + このような挙動になるのはNode.jsが基本的にシングルスレッドでプログラムを実行しているからである。

### Node.jsのイベントループ-EventEmitterクラス
* イベントという仕組みについての解説
* http.Serverクラス
  + 「待ち受けを行う」という処理に対して「クライアントからリクエストを受信した」や「接続エラーが発生した」、「特別に処理すべきリクエストを受信した」といった様々なイベントが発生する
* それぞれのイベントごとに異なる処理が必要となる。そこで「イベントハンドラー」と呼ばれるコールバック関数を登録することで、それぞれのイベントに対応する処理を実行できるようになっている
* Node.jsのイベントはeventモジュールで定義されているEventEmitterクラスを使って実装されている。イベントを扱うクラスは全て子のクラスの派生となっており、イベントハンドラーの登録といったイベント関連の処理はEventEmitterクラスのメソッドを呼び出すことで実行する。
* それぞれのevent引数にはイベント名を示す文字列を、listener引数にはイベントハンドラーとして使用する関数オブジェクトを指定。

|メソッド名および引数|説明|
|:--:|:--:|
|addListener(event,listener)|指定したイベントにイベントハンドラーを登録|
|on(event,listener)|指定したイベントにイベントハンドラーを登録(addListenerの別名)|
|once(event,listener)|指定したイベントに一度だけ実行されるイベントハンドラーを登録|
|removeListener(event,listener)|指定したイベントハンドラーを削除|
|removeAllListeners([event])|指定したイベントハンドラーをえうべて削除する|
|setMaxListeners(n)|登録できるイベントハンドラー数の上限を変更する。n引数には整数で上限数を指定する|
|listeners(event)|指定したイベントに登録されているイベントハンドラーを取得する|
|emit(event,[arg1],[arg2],[...])|指定したイベントを発生させる|

### イベントハンドラーを登録する-emitter.onメソッド
* イベントに対してイベントハンドラーを登録するにはonメソッドあるいはaddListenerメソッドを使用する
``` JavaScript
emitter.on(event,lister)
emitter.addListener(event,listener)
```

* リクエスト受信時のイベントハンドラーの定義
  + ここではイベントハンドラーに無名関数を作成して与えている
  + イベントハンドラーに与えられる引数はイベントに応じて異なるが、requestイベントの場合はリクエスト情報が格納されたrequestオブジェクトとそれに対するレスポンスを返すために使われるresponseオブジェクトが与えられる
  + 無名関数内でこれらを利用し、リクエストURLの表示やHTTPレスポンスの作成といった処理を実行している
``` JavaScript
//requestイベントハンドラーを定義
server.on('request',function(request,response)){
  //コンソールにリクエストされたURLを入力
  console.log(request.url);
  //200(OK)レスポンスと「Context-Type: text/plain」ヘッダーを送信
  response.writeHead(200,{'Content-Type': 'text/plain'});
  //「hello　」という文字列とともにリクエストURLを送信する
  response.end('hello ' + request.url);
});
```

* 1つのイベントに複数のイベントハンドラーを登録する
  + 複数のイベントハンドラーが登録されている場合、イベントの発生時には登録順にイベントハンドラーが呼び出される
  + 下の場合、requestイベントの発生時「request received.」という文字列がコンソールに表示され、続いてクライアントにレスポンスを送信し、最後に「request sent.」という文字がコンソールに表示される。
  + デフォルトではイベントハンドラー数は10個までに制限されている。

``` JavaScript
server.on('request',function(request,response){
  console.log('request received.');
});
server.on('request',function(request,response)){
  console.log(request.url);
  response.writeHead(200,{'Content-Type', 'text/plain'});
  response.end('hello ' + request.url)
});

server.on('request', function(request, response)){
  console.log('response sent.');
});
```

* イベントハンドラーの上限はsetMaxListenersメソッドで変更可能
  + n引数は上限として設定するイベントハンドラー数
``` JavaScript
emitter.setMaxListeners(n)
```

* event引数にはイベントハンドラーを取得したいイベント名を指定する。
* listenersメソッドはイベントハンドラーとして登録されている関数オブジェクトを格納した配列を戻り値として返す。
``` JavaScript
emitter.listeners(event)
```

 * onceメソッドはonメソッドと同様、イベントハンドラーを登録するメソッドであるが、onceメソッドで登録されたイベントハンドラーは実行完了後に削除される点が異なる
 * onceメソッドで登録されたイベントハンドラーは1回だけ実行され、それ以降同じイベントが発生しても実行されない
 ``` JavaScript
emitter.once(event,listener)
 ```

 ### イベントハンドラーを削除する-emitter.removeListenerメソッド
 * 登録したイベントハンドラーを削除するには、removeListenerメソッドを使用
 ``` JavaScript
emitter.removeListener(event,listener)
 ```
 * 全イベントを削除するにはremoveAllListenerメソッドを使用
  + eventが省略された場合はすべてのイベントのイベントハンドラーが削除
 ``` JavaScript
emitter.removeAllListener([event])
 ```

 ### イベントを発生させる-emitter.emitメソッド
 * イベントを発生させるにはemitメソッドを使用
 * 通常、このメソッドをユーザが明示的に呼ぶことはほとんどないが、イベント関連のクラスを実装する場合はこのメソッドを使ってイベントを発生させることになる
* event引数には発生させるイベント名を指定し、第2引数以降にはイベントハンドラーに与える引数を指定する。
 ``` JavaScript
emitter.emit(event,[arg1],[arg2],[...])
 ```
* EventEmitterオブジェクト自体にも「newListener」というイベントが定義されている
* これはオブジェクトにあらたなイベントハンドラーが登録された時に発生するイベントで、イベントハンドラーの引数にはイベント名およびイベントハンドラーとして登録された関数オブジェクトが与えられる。

## Node.jsスクリプトを作成、実行する
