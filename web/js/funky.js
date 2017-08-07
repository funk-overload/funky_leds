/**
 * Created by Magnus on 2017-06-22.
 */
$( document ).ready(function() {

    $json = '{"test";"test"}';
    var jqxhr = $.getJSON( "/funkyleds/api_test_get_config.json", $json, function( data ) {
        console.log(data);


    })
        .fail(function(jqXHR, textStatus, error) {
            console.error(error );
        });

});




function rgbToHex(r, g, b) {
    return "#" + componentToHex(r) + componentToHex(g) + componentToHex(b);
}

function componentToHex(c) {
    var hex = c.toString(16);
    return hex.length == 1 ? "0" + hex : hex;
}

function onLedColorChange(){

}