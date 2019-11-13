package jp.ac.asojuku.mysize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceManager
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Spinner
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_height.*

class HeightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)
    }

    //再表示のライフサイクルのコールバック
    override fun onResume() {
        super.onResume()

        //スピナーの処理
        //スピナーにアイテムを選んだ時の動きを持たクラスの匿名クラスのインスタンスを代入
        Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{//アイテムを選んだ時の動きを持った継承クラスを定義して匿名インスタンスにする
            override fun onItemSelected(parent:AdapterView<*>?/*選択が発生したフォーム*/,view:View?/*選択された要素*/,position:Int/*選択肢の番号*/,id:Long/*選択されたアイテムのID*/) {
                //選択肢を取得するためのスピナーインスタンスを取得
                val spinner = parent as? Spinner;
                //スピナーの選択肢を取得
                val item = spinner?.selectedItem as? String
                //取得した値を身長の値のテキストビューに上書き
                item?.let{
                    if(it.isNotEmpty()){
                        //身長の値が空文字でなければ身長のテキスト(height)に代入php
                        height.text = it

                        //シークバーの現在値を取得した値で上書き
                        seekBar.progress = it.toInt()

                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //何もしない
            }
        }

        //シークバーの処理
        //共有プリファレンスから身長の値を読み込む->シークバーに値を表示するため
        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        //身長の値の変数を保存
        val heightVal = pref.getInt("HEIGHT",170)

        //heightラベルも値を取得した値で上書き
        height.text = heightVal.toString()

        //シークバーの現在値(progress)も取得値で上書き
        seekBar.progress = heightVal

        //シークバーの値が変更されたらコールバックされるメソッドを持つ匿名クラスを引き渡す
        seekBar.setOnSeekBarChangeListener(
            object:SeekBar.OnSeekBarChangeListener{
                //1st over ride method
                override fun onProgressChanged(seekBar: SeekBar?,   //値が変化したインスタンス
                                               progress: Int,       //シークバーの現在値
                                               formUser: Boolean) { //ユーザが操作したか
                    //ユーザーの指定値でheightラベルの表示を変える
                    height.text = progress.toString()

                }

                //2nd over ride method
                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    //Non Activity
                }

                //3rd over ride method
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    //Non Activity
                }
            }
        )

        //ラジオボタンの処理
        //ラジオグループに選択されたときに反応するコールバックメソッドを待機するリスナーを設定
        radioGroup.setOnCheckedChangeListener{
            //二つの引数（ラジオボタングループ、選択されたラジオボタンのID）を受け取って実行する
            radioGroup, checkedid ->
            //heightラベルを選ばれたラジオボタンの値で上書き
            height.text = findViewById<RadioButton>(checkedid).text
            seekBar.progress = (findViewById<RadioButton>(checkedid).text).toString().toInt()
        }
    }

    //画面が閉じられるときに呼ばれるライフサイクルコールバックメソッドをover ride
    override fun onPause() {
        super.onPause()
        //身長の現在値を共有プリファレンスンに保存する処理
        //共有プリファレンスのインスタンスを取得
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit{
            //heightラベルの表示値を取得してStringに変換した後、Intに変換して保存する
            this.putInt("HEIGHT",height.text.toString().toInt())
        }
    }
}
