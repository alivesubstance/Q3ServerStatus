package q3

data class Q3ServerStatus(val map: String, val players: List<Player>) {
    override fun toString(): String {
        return "Q3ServerStatus(map='$map', players=$players)"
    }
}