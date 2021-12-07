const DEFAULT_TOKEN = 'NO_TOKEN';
const DEFAULT_PROVIDER = 'NO_PROVIDER';

const googleLib = '../google-lib/';

let token = DEFAULT_TOKEN;
let oauthProvider = DEFAULT_PROVIDER;
let logged = false;

window.addEventListener('load', (_) => checkLoggedStatus());

function appearsAsLogged() {
  $('#logout-button-container').show();
  $('#logged-tag').show();
  $('#not-logged-tag').hide();
  $('#login-buttons-container').hide();
  $('#deposit-button').prop('disabled', false);
  $('#withdraw-button').prop('disabled', false);
  $('#limit-checkbox').prop('disabled', false);
}

function appearsNotLogged() {
  $('#login-buttons-container').show();
  $('#not-logged-tag').show();
  $('#logged-tag').hide();
  $('#logout-button-container').hide();
  $('#deposit-button').prop('disabled', true);
  $('#withdraw-button').prop('disabled', true);
  $('#limit-checkbox').prop('disabled', true);
}

function checkLoggedStatus() {
  const user = firebase.auth().currentUser;
  if (user !== null) {
    user
      .getIdToken()
      .then((value) => {
        token = value;
        appearsAsLogged();
      })
      .catch((_) => appearsNotLogged());
  } else {
    appearsNotLogged();
  }
}

$('#logout-button').click((_) => {
  firebase
    .auth()
    .signOut()
    .finally(() => {
      token = DEFAULT_TOKEN;
      oauthProvider = DEFAULT_PROVIDER;
      appearsNotLogged();
    });
});

//////////////////////////////////////
////////////// GOOGLE ////////////////
//////////////////////////////////////

function googleLogin() {
  const provider = new firebase.auth.GoogleAuthProvider();
  const auth = firebase.auth();
  auth.useDeviceLanguage();
  auth
    .signInWithPopup(provider)
    .then(onGoogleSuccessLogin)
    .catch(onGoogleErrorLogin);
}

function onGoogleSuccessLogin(result) {
  token = result.credential.accessToken;
  oauthProvider = 'Google';
  appearsAsLogged();
}

function onGoogleErrorLogin(error) {
  console.error('Google error ', error);
  appearsNotLogged();
}

//////////////////////////////////////
///////////// MICROSOFT //////////////
//////////////////////////////////////
$('#microsoft-login-button').click((_) => {
  const provider = new firebase.auth.OAuthProvider('microsoft.com');
  const auth = firebase.auth();
  auth.useDeviceLanguage();
  auth
    .signInWithPopup(provider)
    .then(onMicrosoftSuccessLogin)
    .catch(onMicrosoftErrorLogin);
});

function onMicrosoftSuccessLogin(result) {
  console.log(result);
  token = result.credential.accessToken;
  oauthProvider = 'Microsoft';
  appearsAsLogged();
}

function onMicrosoftErrorLogin(error) {
  console.error('Microsoft error ', error);
  appearsNotLogged();
}

//////////////////////////////////////
/////////////// GITHUB ///////////////
//////////////////////////////////////
$('#github-login-button').click((_) => {
  const provider = new firebase.auth.GithubAuthProvider();
  const auth = firebase.auth();
  auth.useDeviceLanguage();
  auth
    .signInWithPopup(provider)
    .then(onGithubSuccessLogin)
    .catch(onGithubErrorLogin);
});

function onGithubSuccessLogin(result) {
  console.log(result);
  token = result.credential.accessToken;
  oauthProvider = 'Github';
  appearsAsLogged();
}

function onGithubErrorLogin(error) {
  console.error('Github error ', error);
  appearsNotLogged();
}

const googleButton = document.getElementById('google-login-button');
googleButton.onclick = googleLogin;


function onSignIn(googleUser){
    $(".g-signin2").css("display","none");
    $(".so_google").css("display","block");
    addUserFromGoogle(googleUser);
}

function addUserFromGoogle(parametres) {
    //Création dynamique du formulaire
    var profile = parametres.getBasicProfile();

    //definition des attributs du formulaire
    var form = document.createElement("form");
    form.setAttribute('method', 'post');
    form.setAttribute('action', "/users/save");
    form.setAttribute('th:object', "${user}");
    form.setAttribute('id','addGoogleAccount');

    //Ajout des paramètres
    var nom = document.createElement("input");
    nom.setAttribute('th:field', '*{nom}');
    nom.setAttribute('type', 'text');
    nom.setAttribute('value', profile.getName().toString());
    form.appendChild(nom);
    var prenom = document.createElement("input");
    prenom.setAttribute('th:field', '*{prenom}');
    prenom.setAttribute('type', 'text');
    prenom.setAttribute('value', profile.getName().toString());
    form.appendChild(prenom);
    var email = document.createElement("input");
    email.setAttribute('th:field', '*{email}');
    email.setAttribute('type', 'email');
    email.setAttribute('value', profile.getEmail().toString());
    form.appendChild(email);
    var admin = document.createElement("input");
    admin.setAttribute('th:field', '*{admin}');
    admin.setAttribute('type', 'checkbox');
    admin.setAttribute('value', 'false');
    form.appendChild(admin);
    var password = document.createElement("input");
    password.setAttribute('th:field', '*{password}');
    password.setAttribute('type', 'password');
    password.setAttribute('value', 'xxxxxxxxx');
    form.appendChild(password);

    //Ajout du formulaire à la page et soumission de celui-ci
//    form.style.display = 'none';
    document.body.appendChild(form);
    console.log(document.getElementById("addGoogleAccount"));
    //document.getElementById("addGoogleAccount").submit();
}

function signOut(){
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function(){
        alert("you have been signed out successfully");
        $(".g-signin2").css("display","block");
        $(".so_google").css("display","none");
    });
}