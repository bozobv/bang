package hu.bme.aut.bang.character

class Harold(playerRole: Int) : Character(playerRole) {
    override val str = hu.bme.aut.bang.R.string.harold
    override val pic= hu.bme.aut.bang.R.drawable.harold
    override val id = 3

    override fun name() {
        past = "Harold "
    }

    override fun drawNextTurn()
    {
        drawCard(2)
    }
}