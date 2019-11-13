package jp.ac.asojuku.mysize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // test kuga   aaa
    //
    // aaa
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    //再表示のたびに呼ばれるライフサイクルイベントのコールバックメソッド
    override fun onResume() {
        super.onResume()
        //入力値を端末内に保存
        //共有プリファレンスのインスタンスを取得
        val pref = PreferenceManager.getDefaultSharedPreferences(this);

        //共有プリファレンスのインスタンスから4つの保存値を取りだす
        val editNeck = pref.getString("NECK","");       //首回りの値
        val editSleeve = pref.getString("SLEEVE","");   //裄丈の値
        val editWaist = pref.getString("WAIST","");     //腰回りの値
        val editInseam = pref.getString("INSEAM","");   //股下の値

        //取得した保存値で各入力欄の表示を上書きする
        neck.setText(editNeck);
        sleeve.setText(editSleeve);
        waist.setText(editWaist);
        inseam.setText(editInseam);

        //保存ボタンが押された時の処理
        savebutton.setOnClickListener{
            onSaveTapped()
        }

        //身長アイコンボタンの画面遷移
        heightButtoon.setOnClickListener {
            //クリックされたときに呼び出す処理
            //intentを定義
            val intent = Intent(this,HeightActivity::class.java)
            this.startActivity(intent);

        }
    }

    //保存ボタンが押されたらOSがコールバックする処理
    private fun onSaveTapped(){
        //画面表示の値を共有プリファレンスに保存する
        //共有プリファレンスのインスタンスを取得
        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        //共有プリファレンスの編集（editメソッド）
        pref.edit{//ラムダ式でインスタンスに対して引き継ぎ処理を実行
            this.putString("NECK",neck.text.toString())//首回りの入力値を共有プリファレンスに保存
            this.putString("SLEEVE",neck.text.toString())//裄丈の入力値を共有プリファレンスに保存
            this.putString("WAIST",neck.text.toString())//腰回りの入力値を共有プリファレンスに保存
            this.putString("INSEAM",neck.text.toString())//股下の入力値を共有プリファレンスに保存
        }
    }



}

