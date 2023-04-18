package com.ahmadrenhoran.pregnantz.ui.feature.hospital


object MapStyle {

    val json = """
        [  {    "featureType": "poi",    "elementType": "geometry.fill",    "stylers": [      {        "color": "#00ff80"      },      {        "visibility": "on"      }    ]
          },
          {
            "featureType": "poi",
            "elementType": "labels.text",
            "stylers": [
              {
                "visibility": "off"
              }
            ]
          },
          {
            "featureType": "poi",
            "elementType": "labels.icon",
            "stylers": [
              {
                "visibility": "on"
              }
            ]
          },
          {
            "featureType": "poi.medical",
            "elementType": "geometry.fill",
            "stylers": [
              {
                "color": "#ff0000"
              }
            ]
          },
          {
            "featureType": "poi.medical",
            "elementType": "labels.text",
            "stylers": [
              {
                "visibility": "on"
              }
            ]
          },
          {
            "featureType": "poi.medical",
            "elementType": "labels.icon",
            "stylers": [
              {
                "visibility": "off"
              }
            ]
          }
        ]


    """.trimIndent()
}