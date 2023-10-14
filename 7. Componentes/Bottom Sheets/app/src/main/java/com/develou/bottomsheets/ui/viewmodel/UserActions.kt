package com.develou.bottomsheets.ui.viewmodel

data class MainEvents(
    val onShowModalSheetClick: (Boolean) -> Unit,
    val setSwipe: (Boolean) -> Unit,
    val setPartiallyExpandedHeight: (String) -> Unit,
    val setExpandedHeight: (String) -> Unit,
    val onLastActionChange: (String) -> Unit
) {
    companion object {
        val NoActions = MainEvents({}, {}, {}, {}, {})
    }
}

data class ModalEvents(
    val onMenuDismiss: () -> Unit,
    val onShareClick: () -> Unit,
    val onGetLinkClick: () -> Unit,
    val onDeleteClick: () -> Unit
) {
    companion object {
        val NoActions = ModalEvents({}, {}, {}, {})
    }
}