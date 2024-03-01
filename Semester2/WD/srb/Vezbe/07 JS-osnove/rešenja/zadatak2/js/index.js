//funkcija koja ocitava vrednost URL parametra sa prosledjenim imenom
function getParamValue(name) {
    console.log(window.location.toString())
    let location = decodeURI(window.location.toString());
    console.log(decodeURI(window.location.toString()));
    let index = location.indexOf("?") + 1;
    let subs = location.substring(index, location.length);
    let splitted = subs.split("&");

    for (i = 0; i < splitted.length; i++) {
        let s = splitted[i].split("=");
        let pName = s[0];
        let pValue = s[1];
        if (pName == name) {
            return pValue;
        }
    }
}

/*
	Kada se stranica ucita, procitamo ime prijavljenog korisnika
	iz 'user' parametra URL-a i upisemo ga u HTML element ciji je id 'userSpan'.
*/
let user = getParamValue('user');
let userSpan = document.getElementById('userSpan');
userSpan.innerText = user;