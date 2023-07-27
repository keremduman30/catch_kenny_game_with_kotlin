package com.example.catchkennygame

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.catchkennygame.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding;
    var score=0;
    var imageArray=ArrayList<ImageView>();
    var runnable=Runnable{};
    var handler=Handler(Looper.getMainLooper());



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root;
        setContentView(view)
        imageArray.add(binding.imageView);
        imageArray.add(binding.imageView2);
        imageArray.add(binding.imageView3);
        imageArray.add(binding.imageView4);
        imageArray.add(binding.imageView5);
        imageArray.add(binding.imageView6);
        imageArray.add(binding.imageView7);
        imageArray.add(binding.imageView8);
        imageArray.add(binding.imageView9);
        hideImages();
        //count down  timer
        object :CountDownTimer(15000,1000){
            override fun onTick(millisUntilFinished: Long) {//her saniyede bir ne olsun
                binding.txtTime.text="Time : ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                binding.txtTime.text="Game Over"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility=View.INVISIBLE;
                }
                //alert dialog
                val alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over . Score : $score");
                alert.setMessage("Restart The Game ? ")
                alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->
                    //restart uygulamayı bastan baslatmak kolay yol
                    val intentFromMain =intent;
                    finish()//guncel sayfa kapansın
                    startActivity(intentFromMain)//kendimize intent yapıp yeniden baslttık


                })
                alert.setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_SHORT).show()

                })
                alert.show()

            }

        }.start()



    }

    fun hideImages(){
      runnable=  object :Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility=View.INVISIBLE;
                }
                val random=Random();
                val randomIndex=random.nextInt(9)//o ile 8 arasonda sayı doner
                imageArray[randomIndex].visibility=View.VISIBLE;
                handler.postDelayed(runnable,500);//yarım saniye olunca tıklamak zor olur ve oyun guzellesir


            }

        }
        handler.post(runnable);
    }


    fun increaseScore(view: View){
        score+=1;
        binding.txtScore.text="Score : $score"

    }




}

