async function ButtonPress() {
  var loginInput = document.getElementById("login");
  var passwordInput = document.getElementById("password");
  console.log("Script loaded successfully."); // This should log when the script is loaded, not inside this function.
  var loginPattern = /^[A-Za-z0-9]+$/;

  if (!loginPattern.test(loginInput.value)) {
    alert("Ошибка ввода логина. Введите латинские буквы и цифры.");
    return;
  }
  if (!loginPattern.test(passwordInput.value)) {
    alert("Ошибка ввода пароля. Введите латинские буквы и цифры.");
    return;
  }

  var userData = {
    login: loginInput.value,
    password: passwordInput.value
  };

  fetch('http://localhost:8080/auth/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(userData)
  })
  .then(response => {
    if (response.ok) {
      return response.json();
    } else {
      return response.json().then(error => {
        throw new Error(error.message || 'Invalid credentials');
      });
    }
  })
  .then(data => {
    localStorage.setItem('jwtToken', data.token);

  })
  .catch((error) => {
    console.error('Ошибка авторизации:', error);
  });
}
