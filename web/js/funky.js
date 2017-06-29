/**
 * Created by Magnus on 2017-06-22.
 */
$( document ).ready(function() {

    $json = '{"test";"test"}';
    var jqxhr = $.getJSON( "/funkyleds/api_test_get_config.json", $json, function( data ) {
        console.log(data);
        drawSVG(data);

    })
        .fail(function(jqXHR, textStatus, error) {
            console.error(error );
        });

});


function drawSVG(json) {

    var svg = $('#display');
    svg.attr("viewBox", "0 -" + json.display.height + " " + json.display.width + " " + json.display.height);

    var flip = $('#flip');
    flip.attr("transform", "scale(1,-1)");

    var leds = new Array();

    for (i = 0; i < json.leds.length; i++) {
        var led = json.leds[i];
        var ledEl = document.createElementNS("http://www.w3.org/2000/svg", "circle");
        ledEl.setAttribute('id', 'led_id_' + json.leds[i].id);
        ledEl.setAttribute('cx', json.leds[i].x);
        ledEl.setAttribute('cy', json.leds[i].y);
        ledEl.setAttribute('r', "1");
        ledEl.setAttribute('stroke', "black");
        ledEl.setAttribute('stroke-width', "1px");
        ledEl.setAttribute('vector-effect', "non-scaling-stroke");
        ledEl.setAttribute('fill', rgbToHex(json.leds[i].r, json.leds[i].g, json.leds[i].b));

        leds.push(ledEl);
        flip.append(ledEl);

        var picker = new CP(ledEl, false);
        picker.on("change", function(color) {
            console.log(color);
        });

    }
}

function rgbToHex(r, g, b) {
    return "#" + componentToHex(r) + componentToHex(g) + componentToHex(b);
}

function componentToHex(c) {
    var hex = c.toString(16);
    return hex.length == 1 ? "0" + hex : hex;
}

function onLedColorChange(){

}