package hu.bme.aut.bang.character

import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageButton
import hu.bme.aut.bang.R.*

class Ekler(playerRole: Int) : Character(playerRole) {
    override val str = hu.bme.aut.bang.R.string.ekler
    override val pic= hu.bme.aut.bang.R.drawable.ekler
    override val id = 1

    override fun name() {
        past = "Péter "
    }

    override fun getHand() {
        drawCard(6)
    }

}