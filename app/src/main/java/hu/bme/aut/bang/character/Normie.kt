package hu.bme.aut.bang.character

class Normie(playerRole: Int) : Character(playerRole) {
    override val str = hu.bme.aut.bang.R.string.normie
    override val pic= hu.bme.aut.bang.R.drawable.normie
    override val id = 4

    override fun name() {
        past = "Normie "
    }
}