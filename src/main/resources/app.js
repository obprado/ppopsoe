$(document).ready(function(){
        loadQuote(1, showQuoute);
    }
);

function showQuoute(response){
    response = $.parseJSON(response);
    $("#sentence").text(response.sentence);

    $("#PP").click(function(){
        if (response.party === "PP") {
            showSuccess("PP", response.explanation);
        } else {
            showFail("PSOE", "PP", response.explanation);
        }
    } );

    $("#PSOE").click(function(){
        if (response.party === "PSOE") {
            showSuccess("PSOE", response.explanation);
        } else {
            showFail("PP", "PSOE", response.explanation);
        }
    } );
}

function showFail(correctParty, selectedParty, explanation){
    $("#explanation")
        .text("Te has confundido. No te preocupes, el PP y el PSOE son tan similares que a todos nos pasa de vez en cuando. Has selectionado " + selectedParty + " cuando en realidad esta frase pertenece al " + correctParty + ".         " +  explanation);
}

function showSuccess(selectedParty, explanation){
    $("#explanation").text("Efectivamente, esta frase pertenece al " + selectedParty + "    .         " +  explanation);
}

function loadQuote(number, response){
    $.get("quotes/" + number, response);
}