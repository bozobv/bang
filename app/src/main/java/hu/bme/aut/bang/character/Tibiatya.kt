package hu.bme.aut.bang.character

import hu.bme.aut.bang.cards.Bang
import hu.bme.aut.bang.cards.Beer
import hu.bme.aut.bang.cards.Dynamite
import kotlin.random.Random

class Tibiatya(playerRole: Int) : Character(playerRole) {
    override val str = hu.bme.aut.bang.R.string.tibiatya
    override val pic= hu.bme.aut.bang.R.drawable.tibiatya
    override val id = 6

    override fun name()
    {
        past = "Tibor "
    }

    override fun getHand()
    {
        for (i in 0 until 3)
        {
             hand.add(Beer())
        }
    }

}