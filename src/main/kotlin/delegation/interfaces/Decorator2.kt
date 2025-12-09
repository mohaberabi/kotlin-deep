package delegation.interfaces

import kotlin.random.Random


interface AdFilter {
    fun showToPerson(id: String): Boolean
    fun showOnPage(page: Int): Boolean
    fun showOnArticle(id: String): Boolean
}

object ShowAds : AdFilter {
    override fun showToPerson(id: String): Boolean = true
    override fun showOnPage(page: Int): Boolean = true
    override fun showOnArticle(id: String): Boolean = true
}

class ShowToPerson(
    private val prevFilter: AdFilter = ShowAds
) : AdFilter {
    override fun showOnPage(page: Int): Boolean = prevFilter.showOnPage(page)
    override fun showToPerson(id: String): Boolean {
        val show = Random.nextBoolean()
        return show && prevFilter.showToPerson(id)
    }

    override fun showOnArticle(id: String): Boolean {
        val show = Random.nextBoolean()
        return show && prevFilter.showOnArticle(id)
    }
}