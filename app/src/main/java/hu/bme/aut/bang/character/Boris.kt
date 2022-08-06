package hu.bme.aut.bang.character

import android.os.Parcelable

class Boris(playerRole: Int) : Character(playerRole) {
    override val id = 0
    override val str = hu.bme.aut.bang.R.string.boris
    override val pic= hu.bme.aut.bang.R.drawable.boris

    override fun name() {
        past = "Boris "
    }

    override fun playBeer(i: Int) {
        super.playBeer(i)
        if(hp < hpmax)
            hp++
    }
}