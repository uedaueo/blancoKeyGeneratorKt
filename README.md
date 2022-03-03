# blancoKeyGeneratorKt

このプロジェクトはblancoKeyGeneratorKtの上田オリジナル版です。

blancoKeyGeneratorKt は「キーバリューストアテーブル定義書」というExcel様式を記入するだけで 簡単に バリューオブジェクト とKey文字列を生成するKotlinクラスが作成できるようにするためのツールです。

 1.ごく普通の バリューオブジェクトとキー文字列のためのソースコードを生成します。
 2.生成後のソースコードは それ単独で動作することができます。余計な *.jarファイルなどの実行時ライブラリを一切必要としません。
 3.導入すると、ドキュメントとソースコードが必ず一致するという効果があります。

いまのところblancoKeyGeneratorKtは本家にはありません。

## maven 対応について

### metaファイルからソースコードの生成

* プログラムに必要なコードの生成

```
mvn clean
mvn generate-sources
```

* テスト（テストmetaからのコードの生成）

```
msv generate-test-source
```

maven の特性上、lifecycle に沿ってすべての処理が実行されることに注意。

### jar ファイルの作成

```
mvn package
```

maven の特性上、lifecycle に沿ってすべての処理が実行されることに注意。

### deploy

maven リポジトリは github 上のpublicリポジトリに作成される前提としてます。

```
mvn deploy
```

### 独自mavenリポジトリ

独自mavenリポジトリを作成したい場合は以下の手順で。

* https://github.com/uedaueo/blancofw-maven2 を clone
* github から access_key を取得
* pom.xml のリポジトリURLをclone先に変更

~/.m2/settings.xml に以下のように記述することで、deploy可能となります。（useridとaccess_keyは実在のものをご使用下さい）

```~/.m2/settings.xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>userid</username>
      <password>access_key</password>
    </server>
  </servers>
</settings>
```

## キー文字列に関するルール

キーに使用できる文字列としては、一般的なファイルシステムや S3 上でファイル名として使用できる文字として、ASCII英数に限る事が望ましい。そこで、キー文字列には以下のルールを使用する。

* 文字列はUTF-8コードをbase64エンコードしたもので表現することを原則とする
* ただしASCII英数字しか使用されない事が明確な場合は、キー定義に raw を指定することでエンコードせずに使用できる物とする
* 項目区切りには % 記号をそのまま使用する

これによってバケットタイプがファイルシステム、S3 ともに対応可能となるが、S3 ではキー長がUTF-8で1024バイトまでという制約があるので、使えるキー長がかなり厳しく制約されることに注意が必要である。
