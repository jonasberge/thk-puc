# TH Köln - PuC

Materialien zum Modul "Programmiersprachen und Compilerbau"
an der Technischen Hochschule Köln.

## Taschenrechner

| Datum | Paket
|---|---
| 14.04.2021 | [`calculator`](src/main/kotlin/calculator)

* Abstrakte Syntaxbäume (AST) zur Berechnung einfacher mathematischer Ausdrücke
  mit Addition, Multiplikation und Negation von Zahlen und Ausdrücken
  [[AddExpr](src/main/kotlin/calculator/AddExpr.kt),
  [AddMulExpr](src/main/kotlin/calculator/AddMulExpr.kt),
  [AddMulNegExpr](src/main/kotlin/calculator/AddMulNegExpr.kt)].
* Vereinfachung von Ausdrücken:
  - Umwandlung einer Negation in eine Multiplikation mit -1.
    [[RemoveNegConverter](src/main/kotlin/calculator/RemoveNegConverter.kt)]
  - Umwandlung einer Multiplikation in reine Addition.
    [[RemoveMulConverter](src/main/kotlin/calculator/RemoveMulConverter.kt)]

Notizen:
* [01-calculator-simplify-multiplication.jpg](notes/01-calculator-simplify-multiplication.jpg)
