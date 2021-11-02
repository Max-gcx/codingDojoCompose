package de.dojo.weather

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.dojo.weather.data.Weather
import de.dojo.weather.data.WeatherForecast
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeatherScreen(currentWeather: Weather, forecast: List<WeatherForecast>) {
    LazyColumn() {
        item {  Text(
            text = currentWeather.place,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(16.dp)
        )
        }
        item { CurrentWeather(currentWeather = currentWeather) }
        item { Forecast(forecast = forecast, modifier = Modifier.padding(top = 32.dp)) }
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
        shape = RoundedCornerShape(16.dp)
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
                style = MaterialTheme.typography.h1,
                color = Color.White,
                modifier = Modifier.padding(24.dp)
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
                value = "${currentWeather.windSpeed} km/h",
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
            )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(80.dp)
                    .background(color = Color(0xFF61A2F7))
            )
            WeatherTableItem(
                iconId = R.drawable.ic_thermostat,
                title = "FEELS LIKE",
                value = "${currentWeather.feelsLikeTemperature}°",
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
            )
        }
        Divider(color = Color(0xFF61A2F7))
        Row() {
            WeatherTableItem(
                iconId = R.drawable.ic_sun,
                title = "INDEX UV",
                value = currentWeather.indexUV.toString(),
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
            )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(80.dp)
                    .background(color = Color(0xFF61A2F7))
            )
            WeatherTableItem(
                iconId = R.drawable.ic_pressure,
                title = "PRESSURE",
                value = "${currentWeather.pressure} mbar",
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
            )
        }
    }
}

@Composable
fun WeatherTableItem(iconId: Int, title: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(iconId),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 24.dp)
                .size(24.dp)
        )
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = title,
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

@Composable
fun Forecast(forecast: List<WeatherForecast>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Today",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6,
            )
            Row(modifier = Modifier.clickable { }, verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Next 7 Days",
                    style = MaterialTheme.typography.h6,
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Go to next 7 Days"
                )
            }
        }
        LazyRow(
            contentPadding = PaddingValues(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(count = forecast.size) { index ->
                ForecastItem(forecast = forecast[index], isFirst = index == 0)
                if (index != forecast.size - 1) {
                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
fun ForecastItem(forecast: WeatherForecast, isFirst: Boolean, modifier: Modifier = Modifier) {
    Card(
        border = BorderStroke(1.dp, color = Color.LightGray),
        elevation = 0.dp,
        modifier = modifier.width(48.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = SimpleDateFormat(
                    "EE",
                    Locale.getDefault()
                ).format(forecast.date),
                modifier = Modifier.padding(vertical = 8.dp),
                style = MaterialTheme.typography.caption,
            )
            Image(
                painter = painterResource(R.drawable.ic_sunny),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .size(24.dp)
            )
            Text(
                text = forecast.highestTemperature.toString(),
                modifier = Modifier.padding(vertical = 8.dp),
                style = MaterialTheme.typography.caption,
            )
        }
    }
}













