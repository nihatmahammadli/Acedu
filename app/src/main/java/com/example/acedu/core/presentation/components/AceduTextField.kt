package com.example.acedu.core.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acedu.core.presentation.theme.*

@Composable
fun AceduTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    leadingIcon: ImageVector? = null,
    isPassword: Boolean = false,
    singleLine: Boolean = true,
    error: String? = null,
    labelAction: @Composable (() -> Unit)? = null
) {
    Column(modifier = modifier) {
        if (label.isNotEmpty() || labelAction != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, start = 2.dp, end = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (label.isNotEmpty()) {
                    Text(
                        text = label,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (error != null) ErrorRed else TextBody.copy(alpha = 0.8f)
                    )
                }
                if (labelAction != null) {
                    labelAction()
                }
            }
        }
        
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { 
                Text(
                    text = placeholder, 
                    color = TextWhite.copy(alpha = 0.15f),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                ) 
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            leadingIcon = leadingIcon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = if (error != null) ErrorRed.copy(alpha = 0.6f) else TextBody.copy(alpha = 0.6f),
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = TextWhite,
                unfocusedTextColor = TextWhite,
                focusedContainerColor = SurfaceContainer.copy(alpha = 0.6f),
                unfocusedContainerColor = SurfaceContainer.copy(alpha = 0.6f),
                focusedBorderColor = if (error != null) ErrorRed else PrimaryBlue.copy(alpha = 0.3f),
                unfocusedBorderColor = if (error != null) ErrorRed.copy(alpha = 0.3f) else Color.Transparent,
                cursorColor = PrimaryBlue,
                errorBorderColor = ErrorRed,
                focusedPlaceholderColor = TextWhite.copy(alpha = 0.15f),
                unfocusedPlaceholderColor = TextWhite.copy(alpha = 0.15f)
            ),
            singleLine = singleLine,
            isError = error != null
        )
        
        if (error != null) {
            Text(
                text = error,
                color = ErrorRed,
                fontSize = 11.sp,
                modifier = Modifier.padding(top = 6.dp, start = 4.dp)
            )
        }
    }
}
