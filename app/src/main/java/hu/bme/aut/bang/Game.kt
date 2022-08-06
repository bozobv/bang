package hu.bme.aut.bang

import hu.bme.aut.bang.character.*
import kotlin.random.Random

class Game(playerIn: Character, private val inPlayerRole: Int) {

    val player = playerIn
    val listOfAll = randomCharacters() + player
    var end = 0
    var playerRole= inPlayerRole

    //azon random karakterek létrehozása, akik közül majd választani lehet a chooseActivityben
    fun  randomCharacters(): MutableList<Character> {

        val out = mutableListOf<Character>()
        val nums = mutableListOf<Int>(0,1,2,3,4,5,6)
        var roles = mutableListOf<Int>(1,2,3,3)


        if (inPlayerRole == 1)
             roles = mutableListOf<Int>(2,3,3,3)
        if (inPlayerRole == 2)
             roles = mutableListOf<Int>(1,3,3,3)
        if (inPlayerRole == 3)
             roles = mutableListOf<Int>(1,2,3,3)

        nums.shuffle()

        for (i in 0..3)
        {
            when(nums[i])
            {
                0 -> out.add(Boris(roles[i]))
                1 -> out.add(Ekler(roles[i]))
                2 -> out.add(Harambe(roles[i]))
                3 -> out.add(Harold(roles[i]))
                4 -> out.add(Normie(roles[i]))
                5 -> out.add(Ricardo(roles[i]))
                6 -> out.add(Tibiatya(roles[i]))
            }
        }

        return out
    }

    fun getHand()
    {
        for(element in listOfAll)
            element.getHand()
    }

    fun setGameToCharacters()
    {
        listOfAll.forEach{
            it.game = this
        }
    }

    fun dynamitePlayed() {
        for (i in listOfAll)
        {
            i.getDynamite()
        }
        endGame()
    }

    fun endTurn() {

        for(i in 0 until 4) {
            if(listOfAll[i].alive)
                listOfAll[i].doTurn()
        }
        listOfAll.forEach {
            if (it.alive)
                it.drawNextTurn()
        }
        listOfAll[4].playCounter = 0

        if (listOfAll[4].alive == false) {
            end = 1
        }
        endGame()
    }

    fun banging(target: Int) {
        if (listOfAll[4].playCounter <= listOfAll[4].playCounterMax)
            listOfAll[target].getBang()
        endGame()
    }

    //a felhasználó szerepe alapján megvizsgálja, hogy vége van-e a játéknak
    //mindegyik esetben végigmegy az összes karaktere, megnézi, hogy kik haltak meg, és
    // azok alapján dönti el a játék végét
    fun endGame() {
        if (inPlayerRole == 1) {
            var n = 3
            for (i in 0 until 4)
            {
                if (listOfAll[i].alive == false && listOfAll[i].role == 3)
                    n--
                if (listOfAll[i].alive == false && listOfAll[i].role == 1)
                    end = 1
            }
            if (n == 0)
                end = 2
        }
        if (inPlayerRole == 2) {

            var n = 3
            for (i in 0 until 4) {
                if (listOfAll[i].alive == false && listOfAll[i].role == 1) {
                    end = 1
                    return
                }
                if (listOfAll[i].alive == false && listOfAll[i].role == 3)
                    n--
            }
            if (n == 0)
                end = 2
        }
        if (inPlayerRole == 3) {
            for (i in 0 until 4) {
                if (listOfAll[i].alive == false && listOfAll[i].role == 1)
                    end = 2
            }
        }
    }
}