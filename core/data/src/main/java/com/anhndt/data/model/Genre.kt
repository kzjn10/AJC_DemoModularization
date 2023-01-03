package com.anhndt.data.model

import com.anhndt.model.Genre
import com.anhndt.network.model.NetworkGenre


fun NetworkGenre.asData() = Genre(
    id = id,
    name = name
)

