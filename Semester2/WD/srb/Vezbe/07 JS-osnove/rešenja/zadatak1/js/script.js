function proveraUnosa(forma) {
    if (forma.korisnickoIme.value === "") {
        document.getElementById("korisnickoIme").focus();
        alert("Polje korisnicko ime ne sme da bude prazno!");
        return false;
    } else if (forma.sifra.value === "") {
        document.getElementById("sifra").focus();
        alert("Polje sifra ne sme da bude prazno!");
        return false;
    } else if (forma.ponoviSifru.value === "") {
        document.getElementById("ponoviSifru").focus();
        alert("Polje sifra ne sme da bude prazno!");
        return false;
    } else if (forma.sifra.value !== forma.ponoviSifru.value) {
        document.getElementById("ponoviSifru").focus();
        alert("Vrednosti unosa polja sifre i ponovo ukucaj sifru moraju da se poklapaju");
        return false;
    } else if (forma.ime.value === "") {
        document.getElementById("ime").focus();
        alert("Polje ime ne sme da bude prazno!");
        return false;
    } else if (forma.prezime.value === "") {
        document.getElementById("prezime").focus();
        alert("Polje prezime ne sme da bude prazno!");
        return false;
    } else if (forma.JMBG.value === "") {
        document.getElementById("JMBG").focus();
        alert("Polje JMBG ne sme da bude prazno!");
        return false;
    } else if (isNaN(forma.JMBG.value)) {
        document.getElementById("JMBG").focus();
        alert("Vrednosti JMBG mora da bude broj!");
        return false;
    } else {
        alert("Uspesan unos!");
        return true;
    }
}

