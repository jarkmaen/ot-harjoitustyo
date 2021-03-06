# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on perinteinen miinaharava-peli, jossa tarkoituksena on etsiä kaikki pelialueelle piilotetut miinat. Alussa pelialue on täynny avaamattomia ruutuja ja peli alkaa kun pelaaja klikkaa ensimmäisen ruudun auki. Avatuissa ruuduissa lukee numero, mikä kertoo kuinka monta pommia on sen kyseisen ruudun ympärillä. Käyttäen tätä tietoa, pelaaja pystyy päättelemään mitä ruutuja kannattaisi seuraavaksi avata, ja mitä vältellä. Jos pelaaja klikkaa ruutua mikä sisältää pommin, peli päättyy. Voittaakseen pelin, pelaajan täytyy avata kaikki ruudut jotka eivät sisällä pommia.

## Perusversion toiminnallisuudet

#### Päävalikko
- Sovelluksen avautuessa näytetään päävalikko, josta pelaajan on mahdollista valita pelin vaikeustaso, sekä nähdä eri tasojen nopeimmat selvitys tulokset. **[tehty]**
  - Peli sisältää kolme vaikeustasoa (easy-medium-hard). Vaikeustaso vaikuttaa pelilaudan kokoon, sekä miinojen määrään. **[tehty]**
    - Helpolla (easy) vaikeustasolla pelialueen koko on 9x9 ruutua ja miinoja on 10. **[tehty]**
    - Keskivaikealla (medium) vaikeustasolla pelialueen koko on 16x16 ruutua ja miinoja on 40. **[tehty]**
    - Vaikealla (hard) vaikeustasolla pelialueen koko on 30x16 ruutua ja miinoja on 99. **[tehty]**
  - Jokaisen vaikeustaso napin vieressä on listattu sen kentän nopeimmat selvitysajat. Jos vaikeustasoa ei ole kertaakaan läpäisty, niin lista ei sisällä mitään ja näyttää vain tyhjää. **[tehty]**

#### Pelin aikana
- Peliruudun yläreunasta löytyy nappi, laskuri ja ajastin. **[tehty]**
  - Nappia painamalla pääsee takaisin päävalikkoon. **[tehty]**
  - Laskuri kertoo kuinka monta pommia on vielä löytämättä. **[tehty]**
  - Ajastin näyttää kuinka kauan nykyinen peli on kestänyt. **[tehty]**
- Ruudun klikkaaminen vasemmalla hiiren painikkeella muuttaa valitun ruudun avatuksi. Ruutu voi joko sisältää numeron tai pommin. **[tehty]**
- Ruudun klikkaaminen oikealla hiiren painikkeella asettaa ruudun päälle lipun. Lipun tarkoituksena on helpottaa pelaajaa muistamaan missä on mahdollinen pommivaara. **[tehty]**
- Ruutu joka sisältää numeron kertoo kuinka monta pommia on sen kyseisen ruudun ympärillä. **[tehty]**
- Jos pelaaja klikkaa ruutua mikä sisältää pommin, peli päättyy välittömästi. **[tehty]**
- Pelin päätyttyä voittoon ajastin keskeytetään ja peli kysyy pelaajan nimeä. Pelaajan nimi ja aika tallennetaan parhaiden tulosten listalle. **[tehty]**

## Jatkokehitysideoita

- Voi itse asettaa pelialueen koon ja pommien määrän.
- Peli avaa automaattisesti kaikki vierekkäiset ruudut joiden numerona on 0.
- Tulosten tallentaminen verkkossa sijaitsevaan high-score taulukolle.
