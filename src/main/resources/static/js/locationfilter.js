 $('document').ready(function () { // Line 1
    $('#ddlCountryAdd').on('change',function () { // Line 2
        $('#ddlStateAdd').empty().append('<option value="null">-SELECT-</option>'); // Line 3
        var countryid = $(this).val(); //Line 4
        var href = "http://localhost:8080/parameters/country/" + countryid //Line 5
        $.get(href, function (country, status) { // Line 6
            var states = country.states; // Line 7
            for (var i = 0; i <= states.length-1; i++) { // Line 8
                $('#ddlStateAdd').append('<option value="' + states[i].id + '">' + states[i].name + '</option>'); // Line 9
            }
        })
    })
})

