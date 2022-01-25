Clicked = false;

function ClickLogin(){
    Clicked = true;
}

function onSignIn(googleUser){
    if(!Clicked) return;
    //Création dynamique du formulaire
    var profile = googleUser.getBasicProfile();

    //definition des attributs du formulaire
    var form = document.createElement("form");
    form.setAttribute('method', 'post');
    form.setAttribute('action', "/users/save/google");
    form.setAttribute('th:object', "${user}");
    form.setAttribute('id','addGoogleAccount');

    //Ajout des paramètres
    var nom = document.createElement("input");
    nom.setAttribute('th:field', '*{nom}');
    nom.setAttribute('name','nom');
    nom.setAttribute('type', 'text');
    nom.setAttribute('value', profile.getGivenName().toString());
    form.appendChild(nom);
    var prenom = document.createElement("input");
    prenom.setAttribute('th:field', '*{prenom}');
    prenom.setAttribute('name','prenom');
    prenom.setAttribute('type', 'text');
    prenom.setAttribute('value', profile.getFamilyName().toString());
    form.appendChild(prenom);
    var email = document.createElement("input");
    email.setAttribute('th:field', '*{email}');
    email.setAttribute('name','email');
    email.setAttribute('type', 'email');
    email.setAttribute('value', profile.getEmail().toString());
    form.appendChild(email);
    var admin = document.createElement("input");
    admin.setAttribute('th:field', '*{admin}');
    admin.setAttribute('name','admin');
    admin.setAttribute('type', 'checkbox');
    admin.setAttribute('value', 'false');
    form.appendChild(admin);
    var password = document.createElement("input");
    password.setAttribute('th:field', '*{password}');
    password.setAttribute('name','password');
    password.setAttribute('type', 'password');
    password.setAttribute('value', 'xxxxxxxxx');
    form.appendChild(password);

    //Ajout du formulaire à la page et soumission de celui-ci
    form.style.display = 'none';
    document.body.appendChild(form);
    sendGoogleInfo();
}

function sendGoogleInfo(){
    $.ajax({
        type: "POST",
        url: "/users/save/google",
        data: $("#addGoogleAccount").serialize(),
        dataType: "JSON",
    });

    var redirect = document.createElement("a");
    redirect.setAttribute('href','/index');
    redirect.setAttribute('id','goHome');
    document.body.appendChild(redirect);
    document.getElementById('goHome').click();
}

function logout(){

    $.ajax({
        type: "DELETE",
        url: "/users/disconnect"
    });

    var redirect = document.createElement("a");
    redirect.setAttribute('href','/login');
    redirect.setAttribute('id','goLogin');
    document.body.appendChild(redirect);
    document.getElementById('goLogin').click();
}