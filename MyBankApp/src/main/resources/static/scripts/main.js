function getUser(){
    $.ajax({
        type: "GET",
        url: "/getAccount",
        dataType: "JSON",
        success: function(data){
            document.getElementById("accountId").innerHTML = data.idUser;
            document.getElementById("accountBalance").innerHTML = data.value;
            if(data.admin){
                $("#admin_manage").css("display","block");
            }
        },
    });
}

window.onload = function(){
    getUser();
};


function withdraw(){
    $.ajax({
        type: "POST", // A Voir pour changer en get afin de modifier la disponibilités du comptes.
        url: "/subMoney",
        success: function(){
            getUser();
        },
    });
}



function deposit(){
    $.ajax({
        type: "POST", // A Voir pour changer en get afin de modifier la disponibilités du comptes.
        url: "/addMoney",
        success: function(){
            getUser();
        },
    });
}