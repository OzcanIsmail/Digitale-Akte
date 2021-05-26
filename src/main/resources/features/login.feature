#language: de

Funktionalität: US:0001 Anmeldung Funktionalität

  @all @login
  Szenariogrundriss: Einloggen mit den gültigen Anmeldedaten
    Gegeben sei User ist auf Anmeldungseite
    Wenn User seine "<Username>" und "<Password>" eingibt
    Und klickt User Anmelden Button an
    Dann soll Anmeldung erfolgreich sein
    Beispiele:
      | Username | Password |
      | Admin    | admin    |