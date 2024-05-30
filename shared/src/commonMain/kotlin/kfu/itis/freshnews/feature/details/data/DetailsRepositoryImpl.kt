package kfu.itis.freshnews.feature.details.data

import kfu.itis.freshnews.feature.details.data.datasource.remote.RemoteDetailsDataSource
import kfu.itis.freshnews.feature.details.data.mapper.toArticleDetails
import kfu.itis.freshnews.feature.details.domain.DetailsRepository
import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails

internal class DetailsRepositoryImpl(
    private val remoteDetailsDataSource: RemoteDetailsDataSource,
) : DetailsRepository {

    override suspend fun getArticleDetails(title: String): ArticleDetails {
        val articleDetails = remoteDetailsDataSource
            .getArticleDetails(title)
            .articleDetails

        return if (articleDetails.isEmpty())  {
            MOCK_ARTICLE_DETAILS
        } else {
            articleDetails
                .first()
                .toArticleDetails()
        }
    }

    private companion object {

        val MOCK_ARTICLE_DETAILS = ArticleDetails(
            author = "Alison Wood",
            content = "A happy cat is a playful cat — but many owners can certainly attest to " +
                    "buying toys that our beloved felines simply don’t show a bit of interest in. " +
                    "When that happens, our buckets of dust-collecting toys gets larger, and we’re " +
                    "left wondering why we spent so much money for something with such little use. " +
                    "To help you avoid these frustrating situations, we’ve put together a list of " +
                    "the five best cat toys most recommended by experts, plus a few honorable mentions. " +
                    "As cat parents, we recognize each cat has its own unique personality, and " +
                    "research backs this up. The University of Helsinki reports there are seven " +
                    "distinct personality and behavioral traits displayed by cats, covering areas " +
                    "such as activity, sociability, and aggression. With that in mind, is it any " +
                    "wonder that our feline friends enjoy such a variety of cat toys? While some " +
                    "love to chase, others focus on kicking and biting. Of course, there are those " +
                    "that are only in it for the catnip. " +
                    "And guess what: Playtime is beneficial for your cat, helping improve bonding " +
                    "and sociability, and leading to improved physical health. It’s vital to ensure " +
                    "they always have a toy at hand — or should that be to paw? There is an enormous " +
                    "variety of toys available for cats, but when it came time to see what the " +
                    "experts recommend, there were several toys that were featured repeatedly. " +
                    "Our consensus list of the top five cat toys is comprised of the products " +
                    "found most frequently across 15 experts’ reviews. Read on to find out more " +
                    "about the most popular selections.",
            description = "The List: Consensus Top 5 Cat Toys, According To Feline Fanatics",
            publishedAt = "2024-05-14T17:00:00Z",
            source = "StudyFinds",
            title = "Top 5 Cat Toys Most Recommended By Expert Reviewers",
            url = "https://studyfinds.org/best-cat-toys/",
            urlToImage = "https://studyfinds.org/wp-content/uploads/2023/01/Cat-playing-with-fishing-toy-604x385.jpg",
        )
    }
}
