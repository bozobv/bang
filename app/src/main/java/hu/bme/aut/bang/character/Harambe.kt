package hu.bme.aut.bang.character

class Harambe(playerRole: Int) : Character(playerRole) {
    override val str = hu.bme.aut.bang.R.string.harambe
    override val pic= hu.bme.aut.bang.R.drawable.harambe
    override val id = 2
    override val hpmax = 6
    override var hp = hpmax

    override fun name() {
        past = "Harambe "
    }
}