package delegation.property.lazy

class BlogPost(
    val firstName: String,
    val surname: String,
    val post: String
) {
    val authorName: String
        get() = "$firstName $surname"

    val postWords by lazy { post.length }

    val isLongRead by lazy { postWords > 1000 }

    val summary by lazy { generateSummary() }

    private fun generateSummary() = List(10000) { "$it" }.joinToString(",")
}
