package hu.bme.aut.bang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import hu.bme.aut.bang.character.*
import kotlinx.android.synthetic.main.all_enemies.*
import kotlinx.android.synthetic.main.enemy_cell.view.*
import kotlinx.android.synthetic.main.middle.*
import kotlinx.android.synthetic.main.yourself.*

class GameActivity : AppCompatActivity() {

    var G: Game? = null
    //felhasználó kezében lévő lapok gombjai
    var handBtns = mutableListOf<ImageButton>()
    //ellenfelek gombjai(amin van  képük)
    var enemyBtns = mutableListOf<ImageButton>()
    //azok a textek ahova ki vannak írva az ellefelek tevékenységei az előző körből
    var enemyTexts = mutableListOf<TextView>()
    var roleImgs = mutableListOf<ImageView>()

    var playerRole = 0
    var banging = false
    var counter = 0
    var ended = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        playerRole = intent.getIntExtra("ROLE_TYPE",-1)
        //listák feltöltése
        handBtns = mutableListOf<ImageButton>(btnCard1,btnCard2,btnCard3,btnCard4,btnCard5,btnCard6)
        enemyBtns = mutableListOf<ImageButton>(frmCellOne.btnEnemy, frmCellTwo.btnEnemy, frmCellThree.btnEnemy,frmCellFour.btnEnemy)
        enemyTexts = mutableListOf<TextView>(text1,text2,text3,text4)
        roleImgs = mutableListOf<ImageView>(frmCellOne.imageEnemyRole, frmCellTwo.imageEnemyRole, frmCellThree.imageEnemyRole,frmCellFour.imageEnemyRole)

        val created = intent.getIntExtra("created_character",0)

        G = Game(createPlayer(created), playerRole)
        G!!.getHand()
        G!!.setGameToCharacters()

        imgYou.setImageResource(G!!.player.pic)
        frmCellOne.btnEnemy.setImageResource(G!!.listOfAll[0].pic)
        frmCellTwo.btnEnemy.setImageResource(G!!.listOfAll[1].pic)
        frmCellThree.btnEnemy.setImageResource(G!!.listOfAll[2].pic)
        frmCellFour.btnEnemy.setImageResource(G!!.listOfAll[3].pic)

        //szerep képek beállítása
        for (i in 0 until 4)
        {
            if(G!!.listOfAll[i].role == 1)
                roleImgs[i].setImageResource(hu.bme.aut.bang.R.drawable.seriff)
            if(G!!.listOfAll[i].role == 2)
                roleImgs[i].setImageResource(hu.bme.aut.bang.R.drawable.vice)
            if(G!!.listOfAll[i].role == 3)
                roleImgs[i].setImageResource(hu.bme.aut.bang.R.drawable.bandit)

        }

        bind()


    }

    //minden lapkijátszás és kör vége után frissíti a képernyőt és megvizsgálja a végét
    fun bind()
    {
        //ha vége a játéknak, kinullázza a listenereket
        if (ended == true)
        {
            enemyBtns.forEach{
                it.setOnClickListener(null)
            }
            handBtns.forEach{
                it.setOnClickListener(null)
            }
            return
        }

        //életpontok és kezekben lévő lapszámok beállítása
        editTxtYourHp.text = G!!.player.hp.toString()
        frmCellOne.editTextHp.setText(G!!.listOfAll[0].hp.toString())
        frmCellTwo.editTextHp.setText(G!!.listOfAll[1].hp.toString())
        frmCellThree.editTextHp.setText(G!!.listOfAll[2].hp.toString())
        frmCellFour.editTextHp.setText(G!!.listOfAll[3].hp.toString())

        frmCellOne.editTextCards.setText(G!!.listOfAll[0].hand.size.toString())
        frmCellTwo.editTextCards.setText(G!!.listOfAll[1].hand.size.toString())
        frmCellThree.editTextCards.setText(G!!.listOfAll[2].hand.size.toString())
        frmCellFour.editTextCards.setText(G!!.listOfAll[3].hand.size.toString())

        for (i in 0 until 4)
        {
            enemyTexts[i].setText(G!!.listOfAll[i].past)
        }

        //ha valaki bang-et használt, akkor engedélyezi a többi karakter képnek a megnyomását
        if (banging)
            for (i in 0 until 4)
            {
                enemyBtns[i].setOnClickListener()
                {
                    if (banging && counter <=2)
                    {
                        G!!.banging(i)
                        banging = false
                        bind()
                    }
                }
            }

        //end turn gomb
        btnSkip.setOnClickListener()
        {
            G!!.endTurn()
            banging = false
            enemyBtns.forEach{
                it.setOnClickListener(null)
            }
            counter = 0
            bind()
        }

        for (i in 0..5) {
            handBtns[i].setImageResource(android.R.color.transparent)
            handBtns[i].setOnClickListener(null)
        }

        for (i in 0 until G!!.listOfAll[4].hand.size)
        {
            handBtns[i].setImageResource(G!!.listOfAll[4].hand[i].pic)
            when(G!!.listOfAll[4].hand[i].id)
            {
                2 -> beerListener(i)
                1 -> bangListener(i)
                3 -> dynamiteListener(i)
            }

        }
        end()
    }



     fun bangListener(i: Int)
     {
         handBtns[i].setOnClickListener()
         {
             banging = true
             G!!.listOfAll[4].playerPlayBang(i)
             counter++
             bind()
         }
     }

    fun dynamiteListener(i: Int)
     {
        handBtns[i].setOnClickListener()
        {
            counter++
            G!!.listOfAll[4].playDynamite(i)
            bind()
        }
    }

    fun beerListener(i:Int)
    {
        handBtns[i].setOnClickListener()
        {
            counter++
            G!!.listOfAll[4].playBeer(i)
            bind()
        }
    }

    //játék végét vizsgáló függvény
    fun end()
    {
        if(G!!.end == 1)
            lose()
        if(G!!.end == 2)
            win()
    }

    fun win()
    {

        ended = true
        Toast.makeText(applicationContext,"YOU WON",Toast.LENGTH_SHORT).show()
    }

    fun lose()
    {
        ended = true
        Toast.makeText(applicationContext,"YOU LOST",Toast.LENGTH_SHORT).show()
    }

    fun createPlayer (num:Int): Character {

        when(num)
        {
            0 -> return Boris(playerRole)
            1 -> return Ekler(playerRole)
            2 -> return Harambe(playerRole)
            3 -> return Harold(playerRole)
            4 -> return Normie(playerRole)
            5 -> return Ricardo(playerRole)
            6 -> return Tibiatya(playerRole)
        }

        return Normie(playerRole)
    }
}