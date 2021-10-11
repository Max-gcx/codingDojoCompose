package de.dojo.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.dojo.weather.data.Weather
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeatherScreen(currentWeather: Weather) {
    Column() {
        Text(
            text = currentWeather.place,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(16.dp)
        )
        CurrentWeather(currentWeather = currentWeather)

    }
}

@Composable
fun CurrentWeather(currentWeather: Weather) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        backgroundColor = Color(0xFF3F7CF5),
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_cloudy),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .size(64.dp)
            )
            Text(
                text = currentWeather.weatherType.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = SimpleDateFormat(
                    "EE, dd MMM",
                    Locale.getDefault()
                ).format(currentWeather.date),
                style = MaterialTheme.typography.caption,
                color = Color.White
            )
            Text(
                text = "${currentWeather.temperature}°",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h2,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            WeatherDateTable(currentWeather = currentWeather)
        }
    }
}

@Composable
fun WeatherDateTable(currentWeather: Weather) {
    Column {
        Divider(color = Color(0xFF61A2F7))
        Row() {
            WeatherTableItem(
                iconId = R.drawable.ic_wind,
                title = "WIND",
                value = "${currentWeather.windSpeed}km/h"
            )
        }
    }
}

@Composable
fun WeatherTableItem(iconId: Int, title: String, value: String) {
    Row(modifier = Modifier.height(64.dp)) {
        Image(
            painter = painterResource(iconId),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 8.dp)
                .size(24.dp)
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.caption,
                color = Color.White
            )
            Text(
                text = value,
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.caption,
                color = Color.White
            )
        }
    }
}