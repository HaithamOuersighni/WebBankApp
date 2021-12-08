function initWebPageWithData() {
  const path = '../data/banque_postale.json';
  fetch(path)
    .then((response) => response.json())
    .then((data) =>
      populateWebpageWithData(
        data.name,
        data.description,
        data.phoneNumber,
        data.mail
      )
    );
}

function populateWebpageWithData(
  bankName,
  bankDescription,
  bankPhone,
  bankMail
) {
  $('#bankNameContainer').html(bankName);
  $('#bankDescriptionContainer').html(bankDescription);
  $('#phoneNumberContainer').html(bankPhone);
  $('#mailContainer').html(bankMail);
}

function getUser(){
    $.ajax({
        type: "GET",
        url: "/getAccount",
        dataType: "JSON",
        success: function(data){
            console.log(data);
        },
    });
}

window.addEventListener("load", (_) => init());
function init() {
  initWebPageWithData();
//  getUser();
}


function withdraw(){
    console.log("test");
    $.ajax({
        type: "POST",
        url: "/subMoney",
    });
//    getUser();
}



function deposit(){
    $.ajax({
        type: "POST",
        url: "/addMoney",
    });
//    getUser();
}