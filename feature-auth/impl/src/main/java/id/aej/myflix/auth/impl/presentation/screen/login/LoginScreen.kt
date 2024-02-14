package id.aej.myflix.auth.impl.presentation.screen.login

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import id.aej.myflix.auth.impl.R
import id.aej.myflix.auth.impl.presentation.BasicUiState
import id.aej.myflix.design_system.domain.model.PartialClickableTextItems
import id.aej.myflix.design_system.domain.model.PartialClickableTextType
import id.aej.myflix.design_system.presentation.components.FlixButton
import id.aej.myflix.design_system.presentation.components.FlixIconButton
import id.aej.myflix.design_system.presentation.components.FlixPartialClickableText
import id.aej.myflix.design_system.presentation.components.FlixTextField
import id.aej.myflix.design_system.presentation.theme.Gray
import id.aej.myflix.design_system.presentation.theme.Gray15

/**
 * Created by dinopriyano on 27/11/23.
 */

@OptIn(ExperimentalMaterial3Api::class) @Composable fun LoginScreen(
  viewModel: LoginViewModel,
  onToRegister: () -> Unit,
  onLoginSuccess: () -> Unit
) {
  val email by viewModel.emailInput
  val password by viewModel.passwordInput
  var isShouldShowPassword by remember {
    mutableStateOf(false)
  }
  val sheetState = rememberModalBottomSheetState()
  var showBottomSheet by remember {
    mutableStateOf(false)
  }
  val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error))
  val progress by animateLottieCompositionAsState(composition)
  var errorMessage by remember {
    mutableStateOf("")
  }

  val uiState by viewModel.uiState.collectAsState(BasicUiState.Idle)

  LaunchedEffect(uiState) {
    when(val result = uiState) {
      is BasicUiState.Success -> {
        onLoginSuccess()
      }
      is BasicUiState.Error -> {
        Log.e("Login", "Error: ${result.message}")
        errorMessage = result.message
        showBottomSheet = true
      }
      else -> Unit
    }
  }
  
  Column(modifier = Modifier
    .fillMaxSize()
    .verticalScroll(rememberScrollState())
  ) {
    Icon(
      modifier = Modifier.padding(start = 24.dp, top = 70.dp),
      painter = painterResource(id = id.aej.myflix.design_system.R.drawable.ic_myflix),
      contentDescription = null,
      tint = Color.Unspecified
    )
    FlixTextField(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
        .padding(top = 36.dp),
      input = email,
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
      visualTransformation = VisualTransformation.None,
      label = R.string.email_address_txt,
      placeholder = R.string.email_address_placeholder,
      onValueChange = viewModel::onEmailInput
    )
    FlixTextField(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
        .padding(top = 20.dp),
      input = password,
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      visualTransformation = if (isShouldShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
      trailingIcon = {
        IconButton(
          onClick = {
            isShouldShowPassword = !isShouldShowPassword
          }
        ) {
          Icon(
            imageVector = if (isShouldShowPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
            contentDescription = null,
            tint = Gray
          )
        }
      },
      label = R.string.password_txt,
      placeholder = R.string.password_placeholder,
      onValueChange = viewModel::onPasswordInput
    )
    Text(
      modifier = Modifier
        .align(Alignment.End)
        .padding(top = 36.dp, end = 24.dp)
        .clickable { // TODO: handle forgot password click
        },
      text = stringResource(id = R.string.forgot_password_txt),
      style = MaterialTheme.typography.labelLarge.copy(
        fontWeight = FontWeight.SemiBold
      ),
      color = MaterialTheme.colorScheme.primary
    )
    FlixButton(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
        .padding(top = 36.dp),
      enabled = (email.error.isNullOrEmpty() && password.error.isNullOrEmpty() && email.value.isNotEmpty() && password.value.isNotEmpty()),
      isLoading = (uiState is BasicUiState.Loading),
      buttonText = R.string.sign_in_txt
    ) {
      viewModel.login()
    }
    Text(
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(top = 24.dp),
      text = stringResource(id = R.string.or_txt),
      style = MaterialTheme.typography.labelMedium,
      color = Gray
    )
    Row(
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(top = 24.dp),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      FlixIconButton(
        modifier = Modifier,
        contentColor = Gray,
        buttonColor = Gray15,
        icon = id.aej.myflix.design_system.R.drawable.ic_google
      ) {

      }
      FlixIconButton(
        modifier = Modifier,
        contentColor = Gray,
        buttonColor = Gray15,
        icon = id.aej.myflix.design_system.R.drawable.ic_facebook
      ) {

      }
      FlixIconButton(
        modifier = Modifier,
        contentColor = Gray,
        buttonColor = Gray15,
        icon = id.aej.myflix.design_system.R.drawable.ic_twitter
      ) {

      }
    }
    Spacer(modifier = Modifier.weight(1f))
    FlixPartialClickableText(
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(top = 24.dp, bottom = 32.dp),
      textStyle = MaterialTheme.typography.bodyLarge,
      items = listOf(
        PartialClickableTextItems(
          text = stringResource(id = R.string.dont_have_an_account_txt),
          color = Gray,
          type = PartialClickableTextType.NORMAL
        ),
        PartialClickableTextItems(
          text = stringResource(id = R.string.sign_up_txt),
          color = MaterialTheme.colorScheme.primary,
          type = PartialClickableTextType.CLICKABLE,
          onClick = {
            onToRegister.invoke()
          }
        )
      )
    )

    if (showBottomSheet) {
      ModalBottomSheet(
        containerColor = MaterialTheme.colorScheme.onBackground,
        onDismissRequest = {
          showBottomSheet = false
        },
        sheetState = sheetState
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 32.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Top
        ) {
          LottieAnimation(
            modifier = Modifier.height(230.dp),
            contentScale = ContentScale.FillHeight,
            composition = composition,
            progress = { progress },
          )
          Text(
            text = errorMessage,
            style = MaterialTheme.typography.bodyMedium
          )
        }
      }
    }
  }
}