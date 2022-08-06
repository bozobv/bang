package hu.bme.aut.bang.character

import hu.bme.aut.bang.Game
import hu.bme.aut.bang.cards.*
import hu.bme.aut.bang.roles.Role
import kotlin.random.Random

abstract class Character(playerRole:Int) {

    //ismeri a karaktereket tartalmozó Game-t, ezen keresztül éri el az összes többi karaktert
    var game: Game? = null
    //nála lévő lapok:
    val hand = mutableListOf<Cards>()
    //karakter képének elérési útvonala:
    open val pic = hu.bme.aut.bang.R.drawable.normie
    //karakter leírásának útvonala
    open val str = hu.bme.aut.bang.R.string.normie
    //id: sajnos nem tudtam objektumot átrakni egyik activity-ből a másikba, ezért egy csúnya típusellenőrzéssel oldom meg
    open val id = 4
    open val hpmax = 4
    open var hp = hpmax
    var playCounter = 0
    open val playCounterMax = 2
    //past: amit cselekedett a karakter az előző körben
    var past = ""
    var alive = true
    //karakter szerepe(seriff, helyettes, bandita)
    val role = playerRole

    //lövést kapott
    open fun getBang()
    {
        if(alive)
            hp--
        if (hp==0)
        {
            alive = false
            die()
        }
    }

    //kiírja, hogy meghalt a karakter
    fun die() {
        name()
        past+= "  died"
    }

    //dinamit robbant
    open fun getDynamite()
    {

        if(alive)
            this.hp--
        if (hp==0)
        {
            alive = false
            die()
        }
    }

    //a felhasználó játszott ki bangert
    open fun playerPlayBang(i: Int)
    {
        if (playCounter >= playCounterMax)
            return

        hand.removeAt(i)
        playCounter++
    }

    //a gép játszott ki banget, random rálő valakire
    open fun playBang(i: Int)
    {
        if (playCounter >= playCounterMax)
            return
        hand.removeAt(i)

        val target = Random.nextInt(0,5)
        game!!.banging(target)

        if(playCounter == playCounterMax - 1)
            past += " and "
        past += " played Bang "

        playCounter++
    }

    //dinamit kijátszva, kiveszi a kezéből, és meghívja a
    // game dinamit robbanásáért felelős függvényt,
    // és megjegyzi hogy mit csinált

    open fun playDynamite(i: Int)
    {
        if (playCounter >= playCounterMax)
            return

        game?.dynamitePlayed()
        hand.removeAt(i)

        if(playCounter == playCounterMax - 1)
            past += " and "
        past += " exploded a dynamite "
        playCounter++
    }

    //élet lapot játszott ki, eggyel növeli az életét
    open fun playBeer(i: Int)
    {
        if (playCounter >= playCounterMax)
            return

        if(hp < hpmax)
            hp++
        hand.removeAt(i)
        if(playCounter == playCounterMax - 1)
            past += " and "
        past += " drank beer "
        playCounter++
    }

    //megadott mennyiségű random lapot ad a kézbe
    open fun drawCard(amount: Int)
    {

        for (i in 0 until amount)
        {
            if(hand.size >= 6)
                return
            when(Random.nextInt(0,3))
            {
                0 -> hand.add(Bang())
                1 -> hand.add(Beer())
                2 -> hand.add(Dynamite())
            }
        }

    }

    //a játék kezdésénél felhúzott lapok
    open fun getHand()
    {
        drawCard(3)
    }

    //gép által irányított karakter köre
    fun doTurn()
    {
        name()
        playcards()
        playCounter = 0
        //drawNextTurn()
    }

    //kör végén felhúzott lapok
    open fun drawNextTurn() {
        drawCard(1)
    }

    //program által irányított karakter lapokat játszik:
    // random mennyiségű lapot játszik ki, megadott maximumal
    open fun playcards()
    {

        var amount = this.playCounterMax
        if (hand.size < amount)
            amount = hand.size

        amount = Random.nextInt(0 ,amount+1 )
        for(i in 0 until amount)
            when(hand[0].id)
            {
                1 -> playBang(0)
                2 -> playBeer(0)
                3 -> playDynamite(0)
            }
    }

    open fun name()
    {
        past = ""
    }
}

