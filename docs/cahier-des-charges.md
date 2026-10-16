# ChessBall — cahier des charges du binôme

> CPOO1 — 3INFO, 2026-2027. **Gabarit à compléter** du TD2 au TP5, qui vit
> dans le dépôt avec le code. Les sections 1 et 2
> reprennent les deux documents du client tels quels (ils ne disent pas
> tout, c'est voulu). Tout le reste est à vous : votre tableau des
> décisions (TD2) confronté aux décisions du club (TP3, TP4), le glossaire,
> les modèles, les scénarios, la
> traçabilité vers le code et les tests. Un diagramme se donne par son
> source (`.puml`, `.uvl`, dans ce dossier) **et** son image exportée
> (`.svg`), référencée ici par `![titre](fichier.svg)`.
>
> Remplacez chaque « *À compléter* » ; supprimez les consignes en italique
> quand la section est faite. Vos décisions portent un numéro D1, D2… ;
> celles du club J1… J8 (jeu, publiées au TP3) et S1… S9 (service, publiées
> au TP4) : citez-les dans les modèles, les tests et le rapport.

| Section | Séance | Rempli ? |
|---|---|---|
| 3. Tableau des décisions (jeu), décisions du club et leur place dans le code | TD2 → TP3 | |
| 4. Acteurs et périmètre | TD2 | |
| 5. Glossaire | TD2 | |
| 6. Modèle du domaine et diagrammes d'objets | TD2 (papier) → studio ; TP4 (service) | |
| 7. Stories, critères, scénarios | TD2 → TP5 | |
| 8. Machine à états de la partie | TD2 | |
| 9. Décisions sur le service et cas d'utilisation | TD2 → TP4 | |
| 10. Variantes : le feature model | TP4 | |
| 11. Points de variation dans le code | TP4–TP5 | |
| 12. Traçabilité modèle ↔ code ↔ tests | TP3–TP5 | |
| 13. Rapport de test | TP5 | |

## 1. Le jeu, tel que le client l'a écrit

*Copie conforme de `regles-chessball.pdf`. Ne pas corriger ce texte : ses
zones d'ombre sont tranchées en §3.*

ChessBall est un jeu de football sur un petit damier, à deux joueurs.
Chacun dirige une équipe de cinq pièces et cherche à pousser l'unique
ballon jusqu'à la ligne de but adverse ; le premier qui y parvient gagne la
partie.

Le matériel : un plateau de 7 colonnes (`a` à `g`) sur 6 rangées (`1` à `6`,
de bas en haut) ; deux équipes, les Bleus et les Rouges, de cinq pièces
chacune (deux attaquants, trois défenseurs) ; un ballon, qui n'appartient à
personne. Les lignes de but sont les rangées `1` et `6`, coins compris (la
rangée `6` est la ligne de but des Bleus, où les Rouges marquent ; la rangée
`1` celle des Rouges, où les Bleus marquent). Les zones de touche sont les
huit cases des bords gauche et droit entre les deux lignes de but (`a2` à
`a5`, `g2` à `g5`) : le ballon ne peut jamais y être poussé ; les pièces
peuvent s'y trouver.

La mise en place : défenseurs bleus b6 d6 f6, attaquants bleus c5 e5, ballon
d4, attaquants rouges c2 e2, défenseurs rouges b1 d1 f1. Les Bleus jouent en
premier.

Le tour de jeu : à tour de rôle, exactement une action avec exactement une
de ses pièces, dans l'une des huit directions, d'une case à sa voisine. Une
case est libre quand elle est sur le plateau et ne contient ni pièce ni
ballon. Quatre actions : le déplacement (toute pièce, vers une case voisine
libre) ; la poussée du ballon (toute pièce : si le ballon est sur la case
voisine et que la case suivante est libre et n'est pas une zone de touche,
la pièce prend la place du ballon et le ballon avance d'une case ; une ligne
de but est une destination autorisée) ; le tacle (défenseurs seulement : si
une pièce adverse est sur la case voisine et que la case suivante est libre,
le défenseur prend sa place et l'adversaire est repoussé d'une case ; le
ballon ne bouge pas) ; le saut (attaquants seulement : si la case voisine
est occupée par une pièce et que la case suivante est libre, l'attaquant
saute par-dessus ; ce qui est sauté ne bouge pas ; une seule case).

Pas de représailles immédiates : quand un défenseur `T` vient de tacler une
pièce `V`, si `V` est un défenseur, `V` ne peut pas tacler `T` à ce tour ;
si `V` est un attaquant, `V` ne peut pas sauter par-dessus `T` à ce tour.
Tout le reste est permis, et le souvenir s'efface au coup suivant.

Marquer et gagner : la partie se termine immédiatement quand le ballon
s'arrête sur une ligne de but ; rangée `1`, les Bleus ont gagné ; rangée
`6`, les Rouges. Il n'y a pas de score : un but, c'est la victoire.

## 2. Le service, tel que le client l'a écrit

*Copie conforme de `service-chessball.pdf` (joint au sujet du TD2).*

Le club ChessBall grandit et nous voulons un petit service en ligne pour
organiser la vie du club : pas une interface graphique pour l'instant, juste
le « cerveau » du service. Les joueurs et joueuses s'inscrivent avec un
pseudonyme. Une fois inscrit·e, on peut demander à jouer : le service apparie
alors deux personnes disponibles et crée un match. Nous tenons à ce que
l'appariement soit équitable : c'est plus intéressant quand les adversaires
sont de force comparable. À la fin d'un match, le service enregistre le
résultat (victoire, défaite, ou nul) et met à jour un classement « à la Elo,
comme aux échecs ». Tout le monde doit pouvoir consulter le classement du
club à tout moment. Il arrive que quelqu'un déclare forfait avant ou pendant
un match. Par ailleurs, un joueur ne devrait pas disputer trop de matchs à la
fois.

## 3. Tableau des décisions (jeu) et décisions du club — TD2, TP3

**3.1 Vos décisions, confrontées à celles du club.**

*Au TD2 : une ligne par ambiguïté repérée, numérotée D1, D2… (au moins
six) : ce que dit le texte (citation, ou « rien sur… »), au moins deux
lectures, votre décision et sa justification en une ligne. Au TP3 : la
décision du club qui y répond (J1–J8) et l'écart, avec ce qu'il change
dans vos modèles. Une décision du club qui ne répond à aucune de vos lignes
s'ajoute en bas, colonne D vide : c'est une ambiguïté que vous n'aviez pas
vue.*

| D | Ce que dit le texte | Lectures possibles | Votre décision, justifiée (TD2) | Club (TP3) | Écart, et ce qu'il change |
|---|---|---|---|---|---|
| D1 | *À compléter* | | | | |
| D2 | | | | | |

**3.2 Les décisions du club dans le code** (TP3 Q11). *Pour chaque décision
J1 à J8 : la ou les classes et méthodes qu'elle touche (y compris celles du
TP4, et y compris par une vérification qu'on n'écrit pas), la séance où
elle est codée, le test qui la vérifie.*

| J | Classe(s) et méthode(s) | Codée quand | Vérifiée par |
|---|---|---|---|
| J1 | *À compléter* | | |
| J2 | | | |

## 4. Acteurs et périmètre — TD2

**Acteurs.** *À compléter : qui interagit avec le jeu, avec le service ; y
a-t-il un arbitre humain ?*

**Hors périmètre pour ce semestre.** *À compléter : la liste explicite (IHM
graphique, réseau, persistance, IA « forte », protocole des moteurs
officiels…).*

## 5. Glossaire — TD2

*Une définition = une phrase, sans circularité. Les termes du glossaire sont
ceux du diagramme de classes (§6) et du code (§12) : même mot partout.*

| Terme | Définition |
|---|---|
| Plateau | *À compléter* |
| Case | |
| Case libre | |
| Ligne de but | |
| Zone de touche | |
| Pièce | |
| Attaquant | |
| Défenseur | |
| Ballon | |
| Coup (déplacement, poussée, tacle, saut) | |
| Trait | |
| Tour | |
| Mémoire de tacle (représailles) | |
| But | |
| Partie | |
| Rencontre | |
| *(service, TP4)* Compte, Joueur, Appariement, Classement, Forfait | |

## 6. Modèle du domaine et diagrammes d'objets — TD2, TP3, TP4

**6.1 Le modèle du domaine** (`domaine.puml` / `domaine.svg`). *Le
diagramme du TD2, mis au propre dans PlantUML Studio (le TP3
code directement la conception imposée, qui en découle). Concepts,
associations nommées, cardinalités dans les deux sens, compositions
justifiées, le ballon et sa contrainte (jamais sur une case occupée par une
pièce). Étendu au service au TP4 (Compte, Joueur, Match, Appariement,
Classement) : quelle association entre le Match du service et la Partie du
moteur ?*

![Modèle du domaine](domaine.svg)

**6.2 Décisions de modélisation.** *Le ballon est-il une pièce, et
pourquoi pas ; ce qui est composition et ce qui ne l'est pas ; où vivent le
trait et la mémoire de tacle ; les termes du glossaire qui ne sont pas des
classes (ligne de but, zone de touche…), et pourquoi.*

**6.3 Diagrammes d'objets** (`depart.puml`, `apres.puml`). *La position de
départ simplifiée, puis la position après une poussée : ils instancient
§6.1, sinon c'est §6.1 qui est faux.*

![Position de départ](depart.svg)

## 7. Stories, critères, scénarios — TD2, TP5

**7.1 User stories.** *« En tant que… je veux… afin de… », au moins deux
acteurs.*

**7.2 Critères d'acceptation** *d'au moins une story.*

**7.3 Scénarios Gherkin.** *Nominal, refus, cas limite. Chaque scénario
nomme le test qui le joue (§12).*

```gherkin
Scénario: À compléter
  Étant donné …
  Quand …
  Alors …
```

**7.4 Contrat** *d'une opération (préconditions / postconditions) : ce que le
contrat dit que le scénario ne dit pas.*

## 8. Machine à états de la partie — TD2

*`etats-partie.puml` / `.svg` : trait qui alterne, coup refusé, période sans
représailles après un tacle, but, fins sans but (celles de votre tableau
au TD2, celles du club J1 et J2 à partir du TP3). Événement et garde sur
chaque transition.*

![Machine à états de la partie](etats-partie.svg)

## 9. Décisions sur le service et cas d'utilisation — TD2, TP4

*Même forme qu'en §3 : vos décisions sur le service (TD2, s'il y en a),
puis celles du club (S1–S9, TP4) et l'écart.*

| D | Ce que dit le texte | Lectures possibles | Votre décision, justifiée (TD2) | Club (TP4) | Écart, et ce qu'il change |
|---|---|---|---|---|---|
| D… | *À compléter (« force comparable » ? « trop de matchs » ? « à la Elo » ? forfait ? pseudo ? qui joue les Bleus ?)* | | | | |

**Cas d'utilisation du service.** *Le diagramme (`service-uc.puml`), et pour
« Demander un match » (rédigé au CM1) la liste de ses chemins, chacun avec le
test qui le joue (§12). Optionnel : un second cas d'utilisation au format du
cours (sept rubriques).*

## 10. Variantes : le feature model — TP4

*`chessball.uvl` / `.svg`, les contraintes du client, le nombre de
configurations valides (votre calcul, puis le compteur d'UVL Studio).*

![Feature model](chessball.svg)

## 11. Points de variation dans le code — TP4, TP5

*Quels points de variation du feature model existent déjà dans le code (la
règle de nul, l'IA adverse, l'appariement, qui commence…), sous quelle forme
(paramètre, interface + implémentations : le patron Stratégie), et lesquels
n'y sont pas.*

## 12. Traçabilité modèle ↔ code ↔ tests — TP3 à TP5

*Une ligne par élément de modèle : la classe Java qui le réalise, le ou les
tests qui l'attestent. C'est cette table qui prouve que le code vient du
modèle.*

| Élément du modèle (§) | Classe(s) Java | Test(s) |
|---|---|---|
| Équipe *aligne* 5 pièces, 2 attaquants et 3 défenseurs (§6.1) | *À compléter* | |
| Le ballon est sur exactement une case, jamais avec une pièce (§6.1) | | |
| Coup refusé ⇒ rien ne change (§8) | | |
| Représailles interdites après un tacle (§8) | | |
| Scénario « … » (§7.3) | | |
| J1 aucun coup légal (§3) | | |
| S1 appariement (§9) | | |

## 13. Rapport de test — TP5

*Résumez ici en cinq lignes les chiffres finaux : couverture de
branches par paquet, score de mutation par paquet, nombre de tests, et les
nombres de coups légaux obtenus sur les positions de référence.*
