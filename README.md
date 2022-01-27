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
