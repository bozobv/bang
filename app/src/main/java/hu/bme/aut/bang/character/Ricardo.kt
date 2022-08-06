package hu.bme.aut.bang.character

import kotlin.random.Random

class Ricardo(playerRole: Int) : Character(playerRole) {
    override val str = hu.bme.aut.bang.R.string.ricardo
    override val pic= hu.bme.aut.bang.R.drawable.ricardo
    override val id = 5

    override fun name() {
        past = "Ricardo "
    }

    override fun playDynamite(i: Int) {
        super.playDynamite(i)
        game?.dynamitePlayed()
    }
}