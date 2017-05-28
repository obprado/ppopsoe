$(document).ready(function(){
        loadQuote(1, showQuoute);
    }
);

function showQuoute(response){
    response = $.parseJSON(response);
    $("#sentence").text(response.sentence);

    $("#PP").prop('disabled', false);
    $("#PP").click(function(){
        if (response.party === "PP") {
            showSuccess("PP", response.explanation, response.id);
        } else {
            showFail("PSOE", "PP", response.explanation, response.id);
        }
    } );

    $("#PSOE").prop('disabled', false);
    $("#PSOE").click(function(){
        if (response.party === "PSOE") {
            showSuccess("PSOE", response.explanation, response.id);
        } else {
            showFail("PP", "PSOE", response.explanation, response.id);
        }
    } );
}

function showFail(correctParty, selectedParty, explanation, currentQuoteId){
    $("#explanation")
        .text("Te has confundido. No te preocupes, el PP y el PSOE son tan similares que a todos nos pasa de vez en cuando. Has selectionado " + selectedParty + " cuando en realidad esta frase pertenece al " + correctParty + ".         " +  explanation);
    showNextButton(currentQuoteId);
    $("#PP").prop('disabled', true);
    $("#PSOE").prop('disabled', true);
}

function showSuccess(selectedParty, explanation, currentQuoteId){
    $("#explanation")
        .text("Efectivamente, esta frase pertenece al " + selectedParty + "    .         " +  explanation);
    showNextButton(currentQuoteId);
    $("#PP").prop('disabled', true);
    $("#PSOE").prop('disabled', true);
}

function showNextButton(currentQuoteId){
    var nextButton = $("<button id='next' type='button'>Siguiente</button>");
    nextButton.click( function(){
        var nextQuoteId = parseInt(currentQuoteId) + 1;
        cleanup();
        loadQuote(nextQuoteId, showQuoute);
    });
    $("#next")
        .html(nextButton);
}

function loadQuote(number, response){
    $.get("quotes/" + number, response);
}

function cleanup(){
    $("#sentence").text("Cargando la siguiente frase...");
    $("#explanation").text("");
    $("#PP").prop('disabled', true);
    $("#PSOE").prop('disabled', true);
    $("#next")
            .html("");
}