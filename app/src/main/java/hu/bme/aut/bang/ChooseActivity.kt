package hu.bme.aut.bang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import hu.bme.aut.bang.character.*
import kotlinx.android.synthetic.main.activity_choose.*
import kotlin.random.Random
import kotlin.collections.fill as fill

class ChooseActivity : AppCompatActivity() {

    companion object
    {
        const val ROLE_TYPE = "ROLE_TYPE"
        
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        //létrehozza a karaktereket, amikből választani lehet
        val theList = randomCharacters()

        //képek és szövegek forrásának beállítása
        btnFirst.setImageResource(theList[0].pic)
        first.setText(theList[0].str)
        btnSecond.setImageResource(theList[1].pic)
        second.setText(theList[1].str)
        btnThird.setImageResource(theList[2].pic)
        third.setText(theList[2].str)
        btnFourth.setImageResource(theList[3].pic)
        fourth.setText(theList[3].str)

        val role = intent.getIntExtra(ROLE_TYPE,-1)

        //amelyikre rányomunk, azt adja tovább a játék activitybe
        //nem sikerült megoldanom, hogy objektumot adjak át,
        // a parcelable osztállyal próbálkoztam, de az mindenhogy hibát dobott ki
        btnFirst.setOnClickListener() {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("created_character", theList[0].id)
            intent.putExtra("ROLE_TYPE", role)
            startActivity(intent)
        }

        btnSecond.setOnClickListener() {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("created_character", theList[1].id)
            intent.putExtra("ROLE_TYPE", role)
            startActivity(intent)
        }

        btnThird.setOnClickListener() {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("created_character", theList[2].id)
            intent.putExtra("ROLE_TYPE", role)
            startActivity(intent)
        }

        btnFourth.setOnClickListener() {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("created_character", theList[3].id)
            intent.putExtra("ROLE_TYPE", role)
            startActivity(intent)
        }


    }

    //létrehoz 4 különböző random karaktert, azokat fogja megjeleníteni
    fun  randomCharacters(): MutableList<Character> {

        val out = mutableListOf<Character>()
        val nums = mutableListOf<Int>(0,1,2,3,4,5,6)

        nums.shuffle()

        for (i in 1..4)
        {
            when(nums[i])
            {
                0 -> out.add(Boris(0))
                1 -> out.add(Ekler(0))
                2 -> out.add(Harambe(0))
                3 -> out.add(Harold(0))
                4 -> out.add(Normie(0))
                5 -> out.add(Ricardo(0))
                6 -> out.add(Tibiatya(0))
            }
        }

        return out
    }


}










