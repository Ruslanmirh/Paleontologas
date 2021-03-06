package com.zigis.paleontologas.periods.repositories

import android.content.Context
import androidx.annotation.WorkerThread
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.data.BaseRepository
import com.zigis.paleontologas.periods.data.daos.PeriodDao
import com.zigis.paleontologas.periods.data.entities.Period

class PeriodRepository(
    private val context: Context,
    private val periodDao: PeriodDao
) : BaseRepository<Period>(periodDao) {

    @WorkerThread
    override suspend fun initialize() {
        val titles = context.resources.getStringArray(R.array.period_titles)
        val timeScales = context.resources.getStringArray(R.array.period_time_scales)
        val shortDescriptions = context.resources.getStringArray(R.array.period_short_descriptions)
        val environments = context.resources.getStringArray(R.array.period_environments)
        val descriptions = context.resources.getStringArray(R.array.period_descriptions)
        val additionalTitles = context.resources.getStringArray(R.array.period_additional_titles)
        val additionalDescriptions = context.resources.getStringArray(R.array.period_additionals)
        val lifeForms = context.resources.getStringArray(R.array.period_life_forms)
        val artworkAuthors = context.resources.getStringArray(R.array.period_photo_authors)
        val thumbnails = context.resources.getStringArray(R.array.period_thumbs)
        val images = context.resources.getStringArray(R.array.period_images)
        val maps = context.resources.getStringArray(R.array.period_maps)

        val list = mutableListOf<Period>()
        titles.forEachIndexed { index, title ->
            list.add(
                Period(
                    id = index + 1,
                    title = title,
                    additionalTitle = additionalTitles[index],
                    shortDescription = shortDescriptions[index],
                    description = descriptions[index],
                    additionalDescription = additionalDescriptions[index],
                    timeScale = timeScales[index],
                    environmentDescription = environments[index],
                    lifeFormDescription = lifeForms[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    artworkAuthor = artworkAuthors[index],
                    map = maps[index]
                )
            )
        }
        deleteAll()
        periodDao.insertAll(list)
    }
}