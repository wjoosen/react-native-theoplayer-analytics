package com.theoplayer.engage.adapter

import com.google.android.engage.video.datamodel.TvSeasonEntity
import com.theoplayer.engage.adapter.ParseUtils.getArray
import com.theoplayer.engage.adapter.ParseUtils.getInt
import com.theoplayer.engage.adapter.ParseUtils.getLong
import com.theoplayer.engage.adapter.ParseUtils.getString
import com.theoplayer.engage.adapter.Constants.PROP_AVAILABILITY
import com.theoplayer.engage.adapter.Constants.PROP_AVAILABILITY_WINDOWS
import com.theoplayer.engage.adapter.Constants.PROP_CONTENT_RATINGS
import com.theoplayer.engage.adapter.Constants.PROP_EPISODE_COUNT
import com.theoplayer.engage.adapter.Constants.PROP_SEASON_DISPLAY_NUMBER
import com.theoplayer.engage.adapter.Constants.PROP_FIRST_EPISODE_AIR_DATE
import com.theoplayer.engage.adapter.Constants.PROP_GENRES
import com.theoplayer.engage.adapter.Constants.PROP_ID
import com.theoplayer.engage.adapter.Constants.PROP_INFOPAGE_URI
import com.theoplayer.engage.adapter.Constants.PROP_LAST_ENGAGEMENT_TIME
import com.theoplayer.engage.adapter.Constants.PROP_LAST_EPISODE_AIR_DATE
import com.theoplayer.engage.adapter.Constants.PROP_LAST_PLAYBACK_POS
import com.theoplayer.engage.adapter.Constants.PROP_NAME
import com.theoplayer.engage.adapter.Constants.PROP_PLAYBACK_URI
import com.theoplayer.engage.adapter.Constants.PROP_POSTERS
import com.theoplayer.engage.adapter.Constants.PROP_PRICE
import com.theoplayer.engage.adapter.Constants.PROP_SEASON_NUMBER
import com.theoplayer.engage.adapter.Constants.PROP_WATCHNEXT_TYPE
import com.theoplayer.engage.adapter.EntityAdapter.convertImages
import com.theoplayer.engage.adapter.ParseUtils.getObject
import com.theoplayer.engage.adapter.ParseUtils.getUri
import org.json.JSONObject

object TvSeasonAdapter {
  /**
   * Create a TvSeasonEntity from bridge data.
   */
  fun convert(season: JSONObject): TvSeasonEntity {
    val builder = TvSeasonEntity.Builder()
    getString(season, PROP_NAME)?.let {
      builder.setName(it)
    }
    getString(season, PROP_ID)?.let {
      builder.setEntityId(it)
    }
    getArray(season, PROP_POSTERS)?.let { posterList ->
      builder.addPosterImages(convertImages(posterList))
    }
    getUri(season, PROP_PLAYBACK_URI)?.let {
      builder.setPlayBackUri(it)
    }
    getUri(season, PROP_INFOPAGE_URI)?.let {
      builder.setInfoPageUri(it)
    }
    getInt(season, PROP_SEASON_NUMBER)?.let {
      builder.setSeasonNumber(it)
    }
    getString(season, PROP_SEASON_DISPLAY_NUMBER)?.let {
      builder.setSeasonDisplayNumber(it)
    }
    getLong(season, PROP_FIRST_EPISODE_AIR_DATE)?.let {
      builder.setFirstEpisodeAirDateEpochMillis(it)
    }
    getLong(season, PROP_LAST_EPISODE_AIR_DATE)?.let {
      builder.setLatestEpisodeAirDateEpochMillis(it)
    }
    getInt(season, PROP_AVAILABILITY)?.let {
      builder.setAvailability(it)
    }
    getObject(season, PROP_PRICE)?.let {
      builder.setPrice(EntityAdapter.convertPrice(it))
    }
    getInt(season, PROP_EPISODE_COUNT)?.let {
      builder.setEpisodeCount(it)
    }
    getArray(season, PROP_GENRES)?.let {
      for (i in 0 until it.length()) {
        builder.addGenre(it.getString(i))
      }
    }
    getArray(season, PROP_CONTENT_RATINGS)?.let {
      builder.addContentRatings(EntityAdapter.convertRatingSystems(it))
    }
    getInt(season, PROP_WATCHNEXT_TYPE)?.let {
      builder.setWatchNextType(it)
    }
    getLong(season, PROP_LAST_ENGAGEMENT_TIME)?.let {
      builder.setLastEngagementTimeMillis(it)
    }
    getLong(season, PROP_LAST_PLAYBACK_POS)?.let {
      builder.setLastPlayBackPositionTimeMillis(it)
    }
    getArray(season, PROP_AVAILABILITY_WINDOWS)?.let {
      builder.addAllAvailabilityTimeWindows(EntityAdapter.convertDisplayTimeWindows(it))
    }
    return builder.build()
  }
}

