package io.twotle.uimakercompose.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import io.twotle.uimakercompose.model.Caffeine
import io.twotle.uimakercompose.view.ui.theme.Citrus


@Composable
fun mainTopBar(username: String = "현주", curCaf: Int = 0, maxCaf: Int = 0) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Citrus)
    ) {
        Column(
            modifier= Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(text = username+"님, 안녕하세요?", fontSize = 23.sp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("카페인 섭취량")
                Text(curCaf.toString()+"/"+maxCaf.toString()+"mg", fontSize = 30.sp, fontWeight = FontWeight.W600)
            }
        }
    }
}

@Composable
fun mainCaffeineList(caffeineList: List<Caffeine>) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(20.dp)
    ) {
        Text("오늘 마신 카페인 리스트")
        CaffineList(caffeineList)
    }
}

@Composable
fun addingFloatingActionBtn(onClick: () -> Unit) {
    Box(
        modifier= Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd // FAB를 오른쪽 아래로 정렬
    ) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier
                .offset(y = (-16).dp) // FAB를 약간 위로 이동하여 더 정확한 정렬을 수행합니다.
                .padding(16.dp), // FAB와 주변 요소 간의 여백 설정
            content = {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        )
    }
}