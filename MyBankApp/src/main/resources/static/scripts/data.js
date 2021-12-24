function initWebPageWithData() {
  //const path = '../data/banque_postale.json';
  const path = '../data/societe_generale.json';
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


window.addEventListener("load", (_) => init());
function init() {
  initWebPageWithData();
}