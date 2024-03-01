// API key koji se dobija od OpenWeatherMap
let apiKey = "3726fe9cef4ab9d506ee77d8d8f7f3d1";

// PRIMER ZA TRENUTNO VREME: api.openweathermap.org/data/2.5/weather?units=metric&appid=1a1a1a1a1a&q=London
let currentWeatherURL = "http://api.openweathermap.org/data/2.5/weather?units=metric&appid=" + apiKey;

// PRIMER ZA 5 DANA: api.openweathermap.org/data/2.5/forecast?units=metric&appid=1a1a1a1a1a&q=London
let fiveDayForecastURL = "http://api.openweathermap.org/data/2.5/forecast?units=metric&appid=" + apiKey;

// PRIMER IKONICE: http://openweathermap.org/img/w/10d.png
let iconURL = "http://openweathermap.org/img/w/"

// *** NAPOMENA: Obavezno dodati protokol http:// na pocetak svakog URL-a ***

let resultsContainer = document.getElementById('resultsContainer');
let searchForm = document.getElementById('searchForm');
let locationInput = document.getElementById('txtLocation');
let locationInfo = document.getElementById('locationInfo');
let conditionsInfo = document.getElementById('conditionsInfo');
let currentWeatherTbody = document.getElementById('currentWeatherTbody');
let forecastTable = document.getElementById('forecastTable');
let clearSearchButton = document.getElementById('btnClear');


// Na pocetku je div sa vremenskom prognozom sakriven (Ovo je moglo i iz CSS-a da se uradi).
resultsContainer.style.display = 'none';


searchForm.addEventListener('submit', function (e) {
    e.preventDefault();

    let location = locationInput.value.trim();
    if (location == '') {
        alert('Morate uneti lokaciju za koju zelite da dobijete prognozu');
    } else {
        // *** PRVI AJAX: Trenutni vremenski uslovi ***
        let request1 = new XMLHttpRequest();

        request1.onreadystatechange = function () {
            if (this.readyState == 4) {
                if (this.status == 200) {
                    let response1 = JSON.parse(request1.responseText);
                    console.log(response1)

                    // U <h3> element upisujemo ime pronadjenog grada i trenutnu temperaturu
                    let locationName = response1.name;
                    let temp = response1.main.temp;
                    locationInfo.innerHTML = locationName + '&nbsp;&nbsp;' + temp.toFixed(0) + ' &#8451;';

                    // U <h4> naslov ide opis i ikonica trenutnih vremenskih uslova
                    let condition = response1.weather[0].main;
                    conditionsInfo.innerText = condition;

                    let icon = response1.weather[0].icon;
                    let conditionImg = document.createElement('img');
                    conditionImg.setAttribute('src', iconURL + icon + '.png');
                    conditionsInfo.appendChild(conditionImg);

                    // Tabela ispod ima samo jedan red i u njega upisujemo ostatak podataka
                    // NAPOMENA: Ovde zbog ustede vremena i koda koristimo kreiranje elemenata preko 
                    // funkcije innerHTML umesto sa `document.createElement`           
                    let newRow =
                        '<tr>' +
                        '<td>' + response1.main.temp_min.toFixed(0) + ' &#8451;</td>' +
                        '<td>' + response1.main.temp_max.toFixed(0) + ' &#8451;</td>' +
                        '<td>' + response1.main.pressure + ' hPa</td>' +
                        '<td>' + response1.main.humidity + ' %</td>' +
                        '<td>' + response1.wind.speed + ' m/s</td>' +
                        '</tr>';
                    currentWeatherTbody.innerHTML = newRow;

                    resultsContainer.style.display = 'block';
                }
            }
        }
        request1.open('GET', currentWeatherURL + '&q=' + location);
        request1.send();


        // *** DRUGI AJAX: Progonoza za sledecih 5 dana ***
        let request2 = new XMLHttpRequest();

        request2.onreadystatechange = function () {
            if (this.readyState == 4) {
                if (this.status == 200) {
                    let response2 = JSON.parse(request2.responseText);

                    console.log(response2);

                    // Obrisemo rezultate prethodnih pretraga
                    let tableBody = forecastTable.querySelector('tbody');
                    tableBody.innerHTML = '';

                    // OpenWeatherMap API nam vremensku prognozu za 5 dana vraca u vremenskim intervalima od po 3 sata
                    // Vremenski uslovi za svaki od tih intervala su u json listi pod nazivom 'list'
                    // Kroz listu mozemo prolaziti klasicnom for petljom sa indeksom ili novijom for of petljom:
                    for (let item of response2.list) {
                        // Za svaki interval kreiramo po jedan red u tabeli
                        let newTr = document.createElement('tr');

                        // I za svaki podatak koji nas zanima, kreiramo po jednu kolonu
                        // NAPOMENA: Ovde zbog ustede vremena i koda koristimo kreiranje elemenata preko 
                        // funkcije innerHTML umesto sa `document.createElement`
                        newTr.innerHTML =
                            '<td class="align-middle">' + item.dt_txt + '</td>' +
                            '<td class="align-middle">' + item.main.temp.toFixed(0) + ' &#8451;</td>' +
                            '<td class="align-middle">' + item.weather[0].main + '<img src="' + iconURL + item.weather[0].icon + '.png"></td>' +
                            '<td class="align-middle">' + item.main.temp_min.toFixed(0) + ' &#8451;</td>' +
                            '<td class="align-middle">' + item.main.temp_max.toFixed(0) + ' &#8451;</td>' +
                            '<td class="align-middle">' + item.main.pressure + ' hPa</td>' +
                            '<td class="align-middle">' + item.main.humidity + ' %</td>' +
                            '<td class="align-middle">' + item.wind.speed.toFixed(0) + ' m/s</td>';

                        tableBody.appendChild(newTr);
                    }

                }
            }
        }
        request2.open('GET', fiveDayForecastURL + '&q=' + location);
        request2.send();
    }
});


// Klik na dugme za ponistavanje pretrage brise sve dinamcki kreirane elemente i sakriva div sa rezultatima
clearSearchButton.addEventListener('click', function (e) {
    currentWeatherTbody = '';
    forecastTable.querySelector('tbody').innerHTML = '';
    
    locationInput.value = '';
    resultsContainer.style.display = 'none';
});