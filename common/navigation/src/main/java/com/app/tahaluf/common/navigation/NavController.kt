package com.app.tahaluf.common.navigation

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions

fun NavController.navigateToDetail(universityId: String) {
    val context = requireNotNull(this.context) { "Context is not available" }
    val deepLinkUri = context.getString(R.string.deep_link_detail).replace(
        oldValue = "{${context.getString(R.string.argument_university_id)}}",
        newValue = universityId
    ).toUri()

    val request = NavDeepLinkRequest.Builder
        .fromUri(deepLinkUri)
        .build()

    navigate(request, getNavOptionsWithAnimation())
}

private fun getNavOptionsWithAnimation(): NavOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .build()
